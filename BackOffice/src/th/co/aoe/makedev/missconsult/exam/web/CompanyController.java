// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:04:09 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CompanyController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.sql.Timestamp;
import java.text.ParseException;
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
import th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.MissTheme;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

import com.google.gson.Gson;

@Controller
@RequestMapping(value={"/company"})
@SessionAttributes(value={"companyForm","contactForm","UserMissContact"})
public class CompanyController
{
	private static int PAGE_SIZE=20;
  /*  @Autowired 
    public CompanyController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired CompanyController #######################");
        this.missExamService = missExamService;
    }*/
	// 0=admin,1=miss,2=company
    private String account_type="2";
    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String init(Model model)
    {
        model.addAttribute("missExams", missExamService.listMissExam());
        CompanyForm companyForm = null;
     /*   $("#sortItemSelect").val($("#orderBy").val());
    	$("#sortOrderSelect").val($("#sortBy").val()); */ 
        companyForm = new CompanyForm();
        companyForm.getPaging().setOrderBy("MA_NAME");
        companyForm.getPaging().setSortBy("asc");
        companyForm.getPaging().setPageSize(PAGE_SIZE);
        companyForm.getMissAccount().setPagging(companyForm.getPaging());
        companyForm.getMissAccount().setMaType("0");
        VResultMessage vresultMessage = missExamService.searchMissAccount(companyForm.getMissAccount());
        model.addAttribute("missAccounts", vresultMessage.getResultListObj());
        companyForm.getPaging().setPageSize(PAGE_SIZE);
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
       // companyForm.getMissAccount().setMaRegisterType(companyForm.getMaRegisterType());
        companyForm.getMissAccount().setMaRegisterType("Company");
         
        companyForm.getMissAccount().setMaRegisterNo(companyForm.getMaRegisterNo());
        companyForm.getMissAccount().setMaContactName(companyForm.getMaContactName());
        companyForm.getMissAccount().setMaName(companyForm.getMaName());
        companyForm.getMissAccount().setMaPhone(companyForm.getMaDayTimePhone());
        companyForm.getMissAccount().setMaRegisterFrom(null);
        companyForm.getMissAccount().setMaRegisterTo(null);
        if(companyForm.getMaRegisterFrom() != null && companyForm.getMaRegisterFrom().trim().length() > 0)
            try
            {
            	companyForm.getMissAccount().setMaRegisterFrom(new Timestamp(format1.parse(companyForm.getMaRegisterFrom()).getTime()));
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }
        if(companyForm.getMaRegisterTo() != null && companyForm.getMaRegisterTo().trim().length() > 0)
            try
            {
            	companyForm.getMissAccount().setMaRegisterTo(new Timestamp(format1.parse(companyForm.getMaRegisterTo()).getTime()));
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }
        
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
        companyForm.getPaging().setPageSize(PAGE_SIZE);
     /*   logger.debug((new StringBuilder("xxxx=companyForm.getMissAccount().getPagging()=")).append(companyForm.getMissAccount().getPagging()).toString());
        logger.debug((new StringBuilder("xxxx=companyForm.getPaging()=")).append(companyForm.getPaging()).toString());*/
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
       Long usedUnit= missAccount.getMaUsedUnit()!=null?missAccount.getMaUsedUnit():0l;
       Long totalUnit= missAccount.getMaTotalUnit()!=null?missAccount.getMaTotalUnit():0l; 
       missAccount.setMaAvailableUnit(totalUnit - usedUnit);
       missAccount.setMaUsedUnit(usedUnit);
       if(missAccount.getMissTheme()==null){
       	MissTheme missTheme =new MissTheme();
       	missTheme.setMtId(1l);
       	missAccount.setMissTheme(missTheme);
       }
      
        companyForm.setMissAccount(missAccount);
        model.addAttribute("companyForm", companyForm);
       // model.addAttribute("roleContacts", missExamService.listRoleContactBymaId(Long.valueOf(Long.parseLong(maId))));
       // missExamService.listRoleMappingByrcId(rcId)
      //  List<RoleType> roleTypes= missExamService.listRoleTypes();
        /*if(roleTypes!=null && roleTypes.size()>0){
        	for (RoleType roleType : roleTypes) {
        		 
			}
        }*/
       // model.addAttribute("roleTypes", roleTypes);
        @SuppressWarnings("unchecked")
		List<MissTheme> missThemes = missExamService.listMissTheme(new MissTheme());
    	model.addAttribute("missThemes",missThemes); 
    	model.addAttribute("missIndustryMasterList", missExamService.listMissIndustryMaster());
        model.addAttribute("display", "display: none");
        return "exam/template/companyAccount";
    }

    @RequestMapping(value={"/messaage/{maId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public String getMessage(@PathVariable String maId, Model model)
    {
        MissAccount missAccount = missExamService.findMissAccountById(Long.valueOf(Long.parseLong(maId)));
        Gson gson=new Gson();
		return gson.toJson(missAccount);
    }
    @RequestMapping(value={"/item/unit/{maId}/{msId}/{amount}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
    public String postItemUnit(HttpServletRequest request, @PathVariable String maId,
    		@PathVariable String msId,@PathVariable String amount, Model model)
    { 
    	 CompanyForm companyForm = null;
         if(model.containsAttribute("companyForm"))
             companyForm = (CompanyForm)model.asMap().get("companyForm");
         else
             companyForm = new CompanyForm();
         companyForm.setMode("edit");
         Long amountL=Long.valueOf(amount);
         Long msIdL=Long.valueOf(msId);
         Long maIdL=Long.valueOf(maId);
         MissSery missSeryOrder=missExamService.findMissSeryById(msIdL);
     	Long orderUnit=missSeryOrder.getMsUnitCost()*amountL;
    	MissAccountSeriesMap missAccountSeriesMap = new MissAccountSeriesMap();
    	missAccountSeriesMap.setMsId(msIdL);
    	missAccountSeriesMap.setMaId(maIdL);
    	missAccountSeriesMap.setMasmOrderUnit(orderUnit);
      Long returnRecord=  missExamService.saveMissAccountSeriesMap(missAccountSeriesMap);
      String message="Order success !";
     String message_class="success";
      if(returnRecord!=null && returnRecord.intValue()==0){
    	  message_class="error";
    	  message="Can't Order [Unit not enough] "; 
      }else{   
    	  MissCandidate missCandidate = new MissCandidate();
          MissSery missSery = new MissSery();
          MissAccount missAccount = new MissAccount();
          missAccount.setMaId(maIdL);
          missSery.setMsId(msIdL);
          missCandidate.setMissAccount(missAccount);
          missCandidate.setMissSery(missSery);
          missCandidate.setAmount(amountL+"");
          missCandidate = missExamService.saveMissCandidate(missCandidate);
         // Long updateRecord = missCandidate.getMcaId();
      }
        //missAccount = missExamService.refillMissAccount(missAccount);
        MissAccount missAccount= missExamService.findMissAccountById(Long.valueOf(maId));
      Long maTotalUnit=missAccount.getMaTotalUnit()!=null?missAccount.getMaTotalUnit():0l;
      Long maUsedUnit=missAccount.getMaUsedUnit()!=null?missAccount.getMaUsedUnit():0l;
      missAccount.setMaAvailableUnit(maTotalUnit-maUsedUnit);
      //  missCandidate.setUpdateRecord(Integer.valueOf(updateRecord.intValue()));
        // return missAccount;
       /* Gson gson=new Gson();
		return gson.toJson(missAccount);*/
      companyForm.setMissAccount(missAccount);
      model.addAttribute("message",message);
      model.addAttribute("message_class",message_class);
      
      model.addAttribute("display", "display: block");
      model.addAttribute("companyForm", companyForm);
    //  model.addAttribute("display", "display: none");
      return "exam/template/unitListSection";
    }
    @RequestMapping(value={"/item/unit/{maId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getItemUnit(@PathVariable String maId, Model model)
    {
        CompanyForm companyForm = null;
        if(model.containsAttribute("companyForm"))
            companyForm = (CompanyForm)model.asMap().get("companyForm");
        else
            companyForm = new CompanyForm();
        companyForm.setMode("edit");
        MissAccount missAccount = missExamService.findMissAccountById(Long.valueOf(Long.parseLong(maId)));
      //  logger.debug("missAccount == > "+missAccount.getMissSeryList());
     /*   if(missAccount != null && missAccount.getMaContactBirthDate() != null)
            companyForm.setMaContactBirthDate(format1.format(missAccount.getMaContactBirthDate()));*/
       Long usedUnit= missAccount.getMaUsedUnit()!=null?missAccount.getMaUsedUnit():0l;
       Long totalUnit= missAccount.getMaTotalUnit()!=null?missAccount.getMaTotalUnit():0l; 
       missAccount.setMaAvailableUnit(totalUnit - usedUnit);
       missAccount.setMaUsedUnit(usedUnit);
        companyForm.setMissAccount(missAccount);
        model.addAttribute("companyForm", companyForm);
        model.addAttribute("display", "display: none");
        return "exam/template/unitListSection";
    } 
    @RequestMapping(value={"/item/refile/{maId}/{amount}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
  //  public MissAccount doRefill(HttpServletRequest request, @PathVariable String maId,@PathVariable String amount,Model model)
    public String doRefill(HttpServletRequest request, @PathVariable String maId,@PathVariable String amount,Model model)
    {
    
        MissAccount missAccount = new MissAccount();
        missAccount.setMaId(Long.valueOf(maId));
        missAccount.setRefill(Long.valueOf(amount));
       
        missAccount = missExamService.refillMissAccount(missAccount);
      Long maTotalUnit=missAccount.getMaTotalUnit()!=null?missAccount.getMaTotalUnit():0l;
      Long maUsedUnit=missAccount.getMaUsedUnit()!=null?missAccount.getMaUsedUnit():0l;
      missAccount.setMaAvailableUnit(maTotalUnit-maUsedUnit);
      missAccount.setMaUsedUnit(maUsedUnit);
      //  missCandidate.setUpdateRecord(Integer.valueOf(updateRecord.intValue()));
        // return missAccount;
        Gson gson=new Gson();
		return gson.toJson(missAccount);
    }
    @RequestMapping(value={"/new"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm(Model model)
    {
        CompanyForm companyForm = null;
        if(model.containsAttribute("companyForm"))
            companyForm = (CompanyForm)model.asMap().get("companyForm");
        else
            companyForm = new CompanyForm();
        
        MissAccount missAccount =new MissAccount();
        if(missAccount.getMissTheme()==null){
        	MissTheme missTheme =new MissTheme();
        	missTheme.setMtId(1l);
        	missAccount.setMissTheme(missTheme);
        }
        companyForm.setMissAccount(missAccount);
        companyForm.setMode("new");
        model.addAttribute("display", "display: none");
        @SuppressWarnings("unchecked")
		List<MissTheme> missThemes = missExamService.listMissTheme(new MissTheme());
    	model.addAttribute("missThemes",missThemes);
    	model.addAttribute("missIndustryMasterList", missExamService.listMissIndustryMaster());
        return "exam/template/companyAccount";
    }

    @RequestMapping(value={"/action/{section}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doAction(HttpServletRequest request, @PathVariable String section, @ModelAttribute(value="companyForm") CompanyForm companyForm, BindingResult result, Model model)
    {
        String mode = companyForm.getMode();
        String message = "";
        String message_class="";
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
       // logger.debug((new StringBuilder(" debugggggggggg getMaId=")).append(companyForm.getMissAccount().getMaId()).toString());
        if(mode != null)
            if(mode.equals("new"))
            {
                companyForm.getMissAccount().setMaRegisterType("Company");
                companyForm.getMissAccount().setMaTotalUnit(Long.valueOf(0L));
                id = missExamService.saveMissAccount(companyForm.getMissAccount());
                companyForm.getMissAccount().setMaId(id);
                companyForm.setMode("edit");
                message_class="success";
                message = "Save success !";
            } else
            if(mode.equals("edit"))
            {
            	
                missExamService.updateMissAccount(companyForm.getMissAccount());
                id = companyForm.getMissAccount().getMaId();
                message_class="success";
                message = "Update success !";
            }
        MissAccount missAccount = missExamService.findMissAccountById(id);
        if(missAccount.getMissTheme()==null){
        	MissTheme missTheme =new MissTheme();
        	missTheme.setMtId(1l);
        	missAccount.setMissTheme(missTheme);
        }
        companyForm.setMissAccount(missAccount);
        model.addAttribute("message", message);
        model.addAttribute("message_class", message_class);
        model.addAttribute("display", "display: block");
        companyForm.getMissAccount().setSection(section);
        model.addAttribute("companyForm", companyForm);
        @SuppressWarnings("unchecked")
		List<MissTheme> missThemes = missExamService.listMissTheme(new MissTheme());
    	model.addAttribute("missThemes",missThemes);
    	model.addAttribute("missIndustryMasterList", missExamService.listMissIndustryMaster());
        return "exam/template/companyAccount";
    }

    @RequestMapping(value={"/candidate/create"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   // @ResponseBody
   // public String doCreateCandidate(HttpServletRequest request, Model model)
    public @ResponseBody MissCandidate doCreateCandidate(HttpServletRequest request, Model model) 
 //   public MissCandidate doCreateCandidate(HttpServletRequest request, Model model)
    { 
    	//String userid=SecurityContextHolder.getContext().getAuthentication().getName();
    	Long mssery_candidate_hidden=Long.valueOf(request.getParameter("mssery_candidate_hidden"));
    	Long company_candidate_hidden=Long.valueOf(request.getParameter("company_candidate_hidden"));
    	Long amount=Long.valueOf(request.getParameter("amount"));
    	MissSery missSeryOrder=missExamService.findMissSeryById(mssery_candidate_hidden);
    	Long orderUnit=missSeryOrder.getMsUnitCost()*amount;
    	//System.out.println("userid="+userid);
    	MissAccountSeriesMap missAccountSeriesMap = new MissAccountSeriesMap();
    	missAccountSeriesMap.setMsId(mssery_candidate_hidden);
    	missAccountSeriesMap.setMaId(company_candidate_hidden);
    	//missAccountSeriesMap.setMasmOrderUnit(Long.valueOf(request.getParameter("amount")));
    	missAccountSeriesMap.setMasmOrderUnit(orderUnit);
      Long returnRecord=  missExamService.saveMissAccountSeriesMap(missAccountSeriesMap);
      MissCandidate missCandidate = new MissCandidate();
    //  String message="Order success !";
    // String message_class="success";
     //System.out.println("returnRecord===>"+returnRecord);
      if(returnRecord!=null && returnRecord.intValue()==0){
    	 // message_class="error";
    	//  message="Can't Order "; 
    	  missCandidate.setMissAccount(missExamService.findMissAccountById(company_candidate_hidden));
    	  missCandidate.setMissSery(missSeryOrder);
    	  missCandidate.setUpdateRecord(-1);
      }else{ 
        MissSery missSery = new MissSery();
        MissAccount missAccount = new MissAccount();
        //System.out.println("company_candidate_hidden="+request.getParameter("company_candidate_hidden"));
        //System.out.println("mssery_candidate_hidden="+request.getParameter("mssery_candidate_hidden"));
        missAccount.setMaId(company_candidate_hidden);
        missSery.setMsId(mssery_candidate_hidden);
        missCandidate.setMissAccount(missAccount);
        missCandidate.setMissSery(missSery);
        missCandidate.setAmount(request.getParameter("amount"));
        //Long updateRecord = missExamService.saveMissCandidate(missCandidate);
        missCandidate = missExamService.saveMissCandidate(missCandidate);
        //System.out.println("missCandidate.getMcaId="+missCandidate.getMcaId());
      //  logger.debug((new StringBuilder(" updateRecord=")).append(updateRecord).toString());
        missCandidate.setUpdateRecord(Integer.valueOf(missCandidate.getMcaId().intValue()));
      }
       // Gson gson=new Gson();
		//return gson.toJson(missCandidate);
        return missCandidate;
       // return missCandidate;
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
    	@SuppressWarnings("rawtypes")
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
        
    	@SuppressWarnings("rawtypes")
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
         contactForm.setMcontactBirthDate("");
    //	if(meId!=null && !meId.equals("0")){
    		missContact = missExamService.findMissContactById(Long.parseLong(mcontactId));
    //	} 
    		if(missContact != null && missContact.getMcontactBirthDate() != null)
    			contactForm.setMcontactBirthDate(format11.format(missContact.getMcontactBirthDate()));
    		else
    			contactForm.setMcontactBirthDate("");
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
        contactForm.setMcontactBirthDate(null);
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
       String message_class="";
        logger.debug("xxxxxxxxxxxxxxxxxxxxxxx yyyyyyyyyyyyyyyyy doContactAction mode="+mode);
        Long id = null;
        if(mode != null){
        	if(contactForm.getMcontactBirthDate() != null && contactForm.getMcontactBirthDate().trim().length() > 0)
                try
                {
                	contactForm.getMissContact().setMcontactBirthDate(format2.parse(contactForm.getMcontactBirthDate()));
                }
                catch(ParseException e)
                {
                    e.printStackTrace();
                }
        	int count=missExamService.countMissContactByUsername(contactForm.getMissContact().getMcontactUsername(),contactForm.getMissContact().getMcontactId());
        	
        if(count>0){
        	message_class="error";
        	 message = "Duplicate Username !";
        }else{
        	if(mode.equals("new"))
        			{
            	contactForm.getMissContact().setMcontactType(account_type);
                id = missExamService.saveMissContact(contactForm.getMissContact());
                contactForm.getMissContact().setMcontactId(id);
                contactForm.setMode("edit");
                message_class="success";
                message = "Save success !";
            } else
            if(mode.equals("edit"))
            {
            	missExamService.updateMissContact(contactForm.getMissContact());
                id = contactForm.getMissContact().getMcontactId();
                message_class="success";
                message = "Update success !";
            }
         }
       
       }
        model.addAttribute("roleContacts", missExamService.listRoleContactBymaId(Long.parseLong(contactForm.getMaId())));
        model.addAttribute("message", message);
        model.addAttribute("message_class", message_class);
        model.addAttribute("display", "display: block");
        model.addAttribute("contactForm", contactForm);
        
        return "exam/template/contactManagementSection";
    }
    @RequestMapping(value={"/theme/{maId}/{mtId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public String getTheme(@PathVariable Long maId,@PathVariable Long mtId, Model model)
    { 
        MissTheme missTheme = missExamService.findMissThemeById(maId,mtId);
        if(maId!=null && maId.intValue()!=1)
        if(model.containsAttribute("UserMissContact")){
        	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
        	missContact.setMissTheme(missTheme); 
        }
        Gson gson=new Gson();
		return gson.toJson(missTheme);
    }
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat format11 = new SimpleDateFormat("MM-dd-yyyy");
    private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
