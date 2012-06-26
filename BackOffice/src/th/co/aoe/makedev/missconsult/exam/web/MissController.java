// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:04:58 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MissController.java

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
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.form.ContactForm;
import th.co.aoe.makedev.missconsult.exam.form.MissForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissContact;

@Controller
@RequestMapping(value={"/miss"})
@SessionAttributes(value={"missForm","contactForm"})
public class MissController
{
	private String account_type="1";
   /* @Autowired
    public MissController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired MissController #######################");
        this.missExamService = missExamService;
    }*/

    @RequestMapping(value={"/account"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String loadAccount(Model model)
    {
        MissForm missForm = null;
        if(model.containsAttribute("missForm"))
            missForm = (MissForm)model.asMap().get("missForm");
        else
            missForm = new MissForm();
        MissAccount missAccount = missExamService.findMissAccountById(Long.valueOf(1L));
        missForm.setMissAccount(missAccount);
      /*  if(missAccount != null && missAccount.getMaContactBirthDate() != null)
            missForm.setMaContactBirthDate(format1.format(missAccount.getMaContactBirthDate()));*/
        model.addAttribute("display", "display: none");
        missForm.getMissAccount().setSection("0");
        model.addAttribute("missForm", missForm);
        return "exam/template/missAccount";
    }

    @RequestMapping(value={"/action/{section}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doAction(HttpServletRequest request, @PathVariable String section, @ModelAttribute(value="missForm") MissForm missForm, BindingResult result, Model model)
    {
        String mode = missForm.getMode();
        String message = "";
        missForm.getMissAccount().setSection(section);
        /*if(missForm.getMaContactBirthDate() != null && missForm.getMaContactBirthDate().trim().length() > 0)
            try
            {
                missForm.getMissAccount().setMaContactBirthDate(format1.parse(missForm.getMaContactBirthDate()));
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }*/
        missExamService.updateMissAccount(missForm.getMissAccount());
        message = "Update success !";
        MissAccount missAccount = missExamService.findMissAccountById(Long.valueOf(1L));
        missForm.setMissAccount(missAccount);
        model.addAttribute("message", message);
        model.addAttribute("display", "display: block");
        missForm.getMissAccount().setSection(section);
        model.addAttribute("missForm", missForm);
        return "exam/template/missAccount";
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
        model.addAttribute("missContacts", missContacts);
        contactForm.getMissContact().setMcontactType(account_type);
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
    		model.addAttribute("roleContacts", missExamService.listRoleContactBymaId(Long.parseLong(maId)));
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
        model.addAttribute("roleContacts", missExamService.listRoleContactBymaId(Long.parseLong(maId)));
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
    	model.addAttribute("roleContacts", missExamService.listRoleContactBymaId(Long.parseLong(contactForm.getMaId())));
        model.addAttribute("message", message);
        model.addAttribute("display", "display: block");
        model.addAttribute("contactForm", contactForm);
        
        return "exam/template/contactManagementSection";
    }
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
