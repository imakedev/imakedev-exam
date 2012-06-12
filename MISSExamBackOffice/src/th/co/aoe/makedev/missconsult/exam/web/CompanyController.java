// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:04:09 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CompanyController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.form.CompanyForm;
import th.co.aoe.makedev.missconsult.exam.form.ContactForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.exam.utils.IMakeDevUtils;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/company"})
@SessionAttributes(value={"companyForm","contactForm"})
public class CompanyController
{

    @Autowired
    public CompanyController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired CompanyController #######################");
        this.missExamService = missExamService;
    }
    private String account_type="2";
    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String init(Model model)
    {
        model.addAttribute("missExams", missExamService.listMissExam());
        CompanyForm companyForm = null;
        companyForm = new CompanyForm();
        companyForm.getPaging().setPageSize(3);
        companyForm.getMissAccount().setPagging(companyForm.getPaging());
        companyForm.getMissAccount().setMaType("0");
        VResultMessage vresultMessage = missExamService.searchMissAccount(companyForm.getMissAccount());
        model.addAttribute("missAccounts", vresultMessage.getResultListObj());
        companyForm.getPaging().setPageSize(3);
        companyForm.setPageCount(IMakeDevUtils.calculatePage(companyForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("missSeries", missExamService.listMissSery());
        model.addAttribute("companyForm", companyForm);
        return "exam/template/companySearch";
    }

    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doSearch(HttpServletRequest request, @ModelAttribute(value="companyForm") CompanyForm companyForm, BindingResult result, Model model)
    {
        String mode = companyForm.getMode();
        String missExam_selectboxes[] = request.getParameterValues("missExam_selectbox");
        companyForm.getMissAccount().setMaRegisterType(companyForm.getMaRegisterType());
        companyForm.getMissAccount().setMaRegisterNo(companyForm.getMaRegisterNo());
      //  companyForm.getMissAccount().setMaContactName(companyForm.getMaContactName());
        companyForm.getMissAccount().setMaName(companyForm.getMaName());
        companyForm.getMissAccount().setMaPhone(companyForm.getMaDayTimePhone());
        if(mode != null && mode.equals("deleteItems"))
        {
            companyForm.getMissAccount().setMaIds(companyForm.getMaIdArray());
            missExamService.deleteMissAccount(companyForm.getMissAccount(), "deleteMissAccountItems");
            companyForm.getPaging().setPageNo(1);
        } else
        if(mode != null && mode.equals("delete")){
            missExamService.deleteMissAccount(companyForm.getMissAccount(), "deleteMissAccount");
            companyForm.getPaging().setPageNo(1);
        }
        else
        if(mode != null && mode.equals("doBack"))
        {
            if(model.containsAttribute("companyForm"))
                companyForm = (CompanyForm)model.asMap().get("companyForm");
            else
                companyForm = new CompanyForm();
            missExam_selectboxes = companyForm.getMissExam_selectbox();
        }
        model.addAttribute("missExams", missExamService.listMissExam());
        companyForm.setMissExam_selectbox(missExam_selectboxes);
        companyForm.getMissAccount().setMeIds(missExam_selectboxes);
        companyForm.getPaging().setPageSize(3);
        logger.debug((new StringBuilder("xxxx=companyForm.getMissAccount().getPagging()=")).append(companyForm.getMissAccount().getPagging()).toString());
        logger.debug((new StringBuilder("xxxx=companyForm.getPaging()=")).append(companyForm.getPaging()).toString());
        companyForm.getMissAccount().setPagging(companyForm.getPaging());
        VResultMessage vresultMessage = missExamService.searchMissAccount(companyForm.getMissAccount());
        String meIdArray = "";
        if(missExam_selectboxes != null && missExam_selectboxes.length > 0)
        {
            int meId_size = missExam_selectboxes.length;
            String meIds[] = missExam_selectboxes;
            for(int i = 0; i < meId_size; i++)
                if(i != meId_size - 1)
                    meIdArray = (new StringBuilder(String.valueOf(meIdArray))).append(meIds[i]).append(",").toString();
                else
                    meIdArray = (new StringBuilder(String.valueOf(meIdArray))).append(meIds[i]).toString();

        }
        companyForm.setPageCount(IMakeDevUtils.calculatePage(companyForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("meIdArray", meIdArray);
        model.addAttribute("missAccounts", vresultMessage.getResultListObj());
        model.addAttribute("missSeries", missExamService.listMissSery());
        model.addAttribute("companyForm", companyForm);
        return "exam/template/companySearch";
    }

    @RequestMapping(value={"/item/{maId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getItem(@PathVariable String maId, Model model)
    {
        CompanyForm companyForm = null;
        if(model.containsAttribute("companyForm"))
            companyForm = (CompanyForm)model.asMap().get("companyForm");
        else
            companyForm = new CompanyForm();
        companyForm.setMode("edit");
        MissAccount missAccount = missExamService.findMissAccountById(Long.valueOf(Long.parseLong(maId)));
     /*   if(missAccount != null && missAccount.getMaContactBirthDate() != null)
            companyForm.setMaContactBirthDate(format1.format(missAccount.getMaContactBirthDate()));*/
        companyForm.setMissAccount(missAccount);
        model.addAttribute("companyForm", companyForm);
        model.addAttribute("display", "display: none");
        return "exam/template/companyAccount";
    }

    @RequestMapping(value={"/new"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm(Model model)
    {
        CompanyForm companyForm = null;
        if(model.containsAttribute("companyForm"))
            companyForm = (CompanyForm)model.asMap().get("companyForm");
        else
            companyForm = new CompanyForm();
        companyForm.setMissAccount(new MissAccount());
        companyForm.setMode("new");
        model.addAttribute("display", "display: none");
        return "exam/template/companyAccount";
    }

    @RequestMapping(value={"/action/{section}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doAction(HttpServletRequest request, @PathVariable String section, @ModelAttribute(value="companyForm") CompanyForm companyForm, BindingResult result, Model model)
    {
        String mode = companyForm.getMode();
        String message = "";
        logger.debug((new StringBuilder(" aoeeeeeeeeeeee section=")).append(section).append(",mode=").append(mode).toString());
        companyForm.getMissAccount().setSection(section);
        companyForm.getMissAccount().setMaType("0");
        Long id = null;
      /*  if(companyForm.getMaContactBirthDate() != null && companyForm.getMaContactBirthDate().trim().length() > 0)
            try
            {
                companyForm.getMissAccount().setMaContactBirthDate(format1.parse(companyForm.getMaContactBirthDate()));
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }*/
        logger.debug((new StringBuilder(" debugggggggggg getMaId=")).append(companyForm.getMissAccount().getMaId()).toString());
        if(mode != null)
            if(mode.equals("new"))
            {
                companyForm.getMissAccount().setMaRegisterType("Company");
                companyForm.getMissAccount().setMaTotalUnit(Long.valueOf(100L));
                id = missExamService.saveMissAccount(companyForm.getMissAccount());
                companyForm.getMissAccount().setMaId(id);
                companyForm.setMode("edit");
                message = "Save success !";
            } else
            if(mode.equals("edit"))
            {
                missExamService.updateMissAccount(companyForm.getMissAccount());
                id = companyForm.getMissAccount().getMaId();
                message = "Update success !";
            }
        MissAccount missAccount = missExamService.findMissAccountById(id);
        companyForm.setMissAccount(missAccount);
        model.addAttribute("message", message);
        model.addAttribute("display", "display: block");
        companyForm.getMissAccount().setSection(section);
        model.addAttribute("companyForm", companyForm);
        return "exam/template/companyAccount";
    }

    @RequestMapping(value={"/candidate/create"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public MissCandidate doCreateCandidate(HttpServletRequest request, Model model)
    {
        System.out.println((new StringBuilder(" xxxxxxxx ")).append(request.getParameter("amount")).toString());
        MissCandidate missCandidate = new MissCandidate();
        MissSery missSery = new MissSery();
        MissAccount missAccount = new MissAccount();
        missAccount.setMaId(Long.valueOf(Long.parseLong(request.getParameter("company_candidate_hidden"))));
        missSery.setMsId(Long.valueOf(Long.parseLong(request.getParameter("mssery_candidate_hidden"))));
        missCandidate.setMissAccount(missAccount);
        missCandidate.setMissSery(missSery);
        missCandidate.setAmount(request.getParameter("amount"));
        Long updateRecord = missExamService.saveMissCandidate(missCandidate);
        logger.debug((new StringBuilder(" updateRecord=")).append(updateRecord).toString());
        missCandidate.setUpdateRecord(Integer.valueOf(updateRecord.intValue()));
        return missCandidate;
    }

    @RequestMapping(value={"/account/{maId}/contacts"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String contactsList(@PathVariable String maId, @ModelAttribute(value="contactForm") ContactForm contactForm, Model model)
    {
    	
    	/* ContactForm contactForm = null;
         if(model.containsAttribute("contactForm"))
         	contactForm = (ContactForm)model.asMap().get("contactForm");
         else
         	contactForm = new ContactForm();*/
        
         String mode = contactForm.getMode();
         logger.debug("into xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx mode="+mode);
         if(mode != null && mode.equals("deleteItems"))
         {
        	 logger.debug("Start DELETE mode="+contactForm.getMcontactIdArray());
        	 contactForm.getMissContact().setMcontactIds(contactForm.getMcontactIdArray());
             missExamService.deleteMissContact(contactForm.getMissContact(),ServiceConstant.MISS_CONTACT_ITEMS_DELETE);
             logger.debug("End DELETE mode="+contactForm.getMcontactIdArray());
         }else if(mode != null && mode.equals("delete")){
             missExamService.deleteMissContact(contactForm.getMissContact(), ServiceConstant.MISS_CONTACT_DELETE);
         }
         contactForm.getPaging().setPageNo(1);
    	List missContacts=null;
    	if(maId!=null && !maId.equals("0")){
    		missContacts = missExamService.listContacts(Long.parseLong(maId),account_type);
    		contactForm.setMaId(maId);
    	}
    	contactForm.setMode("edit");
    	contactForm.getMissContact().setMcontactType(account_type);
        model.addAttribute("missContacts", missContacts);
        model.addAttribute("contactForm", contactForm);
        return "exam/template/contactListSection";
    }
    @RequestMapping(value={"/account/{maId}/contacts"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getContactList(@PathVariable String maId, Model model)
    {
    	logger.debug("into getQuestionList xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx "+maId);
    	ContactForm contactForm = null;
         if(model.containsAttribute("contactForm"))
        	 contactForm = (ContactForm)model.asMap().get("contactForm");
         else
        	 contactForm = new ContactForm();
         contactForm.setMode("edit");
        
    	List missContacts=null;
    	if(maId!=null && !maId.equals("0")){
    		missContacts = missExamService.listContacts(Long.parseLong(maId),account_type);
    		contactForm.setMaId(maId);
    	} 
    	contactForm.getMissContact().setMcontactType(account_type);
        model.addAttribute("missContacts", missContacts);
        model.addAttribute("contactForm", contactForm);
        return "exam/template/contactListSection";
    }
    @RequestMapping(value={"/account/{maId}/contact/{mcontactId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getContactItem(@PathVariable String maId,@PathVariable String mcontactId, Model model)
    {
    	logger.debug("into xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    	ContactForm contactForm = null;
         if(model.containsAttribute("contactForm"))
        	 contactForm = (ContactForm)model.asMap().get("contactForm");
         else
        	 contactForm = new ContactForm();
         contactForm.setMode("edit");
         MissContact missContact=null;
    //	if(meId!=null && !meId.equals("0")){
    		missContact = missExamService.findMissContactById(Long.parseLong(mcontactId));
    //	}
    		model.addAttribute("display", "display: none");
      //  model.addAttribute("missContacts", missContacts);
    		contactForm.setMissContact(missContact);
    	//missExamService
        model.addAttribute("contactForm", contactForm);
        return "exam/template/contactManagementSection";
    } 
    @RequestMapping(value={"/account/{maId}/contact/new"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNetContactItem(Model model,@PathVariable String maId)
    {
    	ContactForm contactForm = null;
        if(model.containsAttribute("contactForm"))
        	contactForm = (ContactForm)model.asMap().get("contactForm");
        else
        	contactForm = new ContactForm();
        MissContact missContact =new MissContact();
        missContact.setMcontactType(account_type);
        missContact.setMcontactRef(Long.parseLong(maId));
        contactForm.setMissContact(missContact);
        contactForm.setMode("new");
        model.addAttribute("display", "display: none");
        return "exam/template/contactManagementSection";
    }
    @RequestMapping(value={"/action/account/contact"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doContactAction(HttpServletRequest request, @ModelAttribute(value="contactForm") ContactForm contactForm, BindingResult result, Model model)
    
    {
        String mode = contactForm.getMode();
        String message = ""; 
        logger.debug("xxxxxxxxxxxxxxxxxxxxxxx yyyyyyyyyyyyyyyyy doContactAction mode="+mode);
        Long id = null;
        if(mode != null)
            if(mode.equals("new"))
            {
            	contactForm.getMissContact().setMcontactType(account_type);
                id = missExamService.saveMissContact(contactForm.getMissContact());
                contactForm.getMissContact().setMcontactId(id);
                contactForm.setMode("edit");
                message = "Save success !";
            } else
            if(mode.equals("edit"))
            {
            	missExamService.updateMissContact(contactForm.getMissContact());
                id = contactForm.getMissContact().getMcontactId();
                message = "Update success !";
            }
        
        model.addAttribute("message", message);
        model.addAttribute("display", "display: block");
        model.addAttribute("contactForm", contactForm);
        
        return "exam/template/contactManagementSection";
    }
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    private static Logger logger = Logger.getRootLogger();
    private MissExamService missExamService;

}
