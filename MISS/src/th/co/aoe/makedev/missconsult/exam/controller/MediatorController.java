package th.co.aoe.makedev.missconsult.exam.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.form.MissExamForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;

@Controller
@RequestMapping("/")
@SessionAttributes(value={"missExamForm","systemDate","timelimit"})
public class MediatorController {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER); 
	 private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	 private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	 //private static SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy/HH/mm/ss");
	@Autowired
	private MissExamService missExamService;
	@RequestMapping( method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String getCandidateInfo(Model model,HttpServletRequest request,HttpServletResponse response) {
		String language=request.getParameter("language");
		logger.info("language1="+language);
		if(language!=null && language.length()>0){
	    	 LocaleEditor localeEditor = new LocaleEditor();
	         localeEditor.setAsText(language);
 
	         LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
	         localeResolver.setLocale(request, response,
	             (Locale) localeEditor.getValue());
	    	}
		logger.info("into init local1 "+LocaleContextHolder.getLocale().getDisplayLanguage());
		String userid=SecurityContextHolder.getContext().getAuthentication().getName();
		//0=not yet test finish, 1=  test finish
			MissExamForm missExamForm = null;
			 if(model.containsAttribute("missExamForm"))
				 missExamForm = (MissExamForm)model.asMap().get("missExamForm");
	         else
	        	 missExamForm = new MissExamForm();
			 MissCandidate missCandidate= missExamService.findMissCandidateByName(userid);
			 missExamForm.setMcaBirthDate("");
			 if(missCandidate != null && missCandidate.getMcaBirthDate() != null)
				 missExamForm.setMcaBirthDate(format2.format(missCandidate.getMcaBirthDate()));
			 missExamForm.setMissCandidate(missCandidate);
			 //SecurityContextHolder.getContext().getAuthentication().ggetName().get;
			 model.addAttribute("missExamForm", missExamForm);
			 Date date=new Date();
			 model.addAttribute("systemDate", format1.format(date));
			 model.addAttribute("missIndustryMasterList", missExamService.listMissIndustryMaster());
			 model.addAttribute("missCareerMasterList", missExamService.listMissCareerMaster());
		//	 model.addAttribute("timelimit", format2.format(date));
			return "exam/candidateInfo";
	}
	
	@RequestMapping(value="/user")
	public String getUserPage() {
		return "user";
	}
	
	@RequestMapping(value="/admin")
	public String getAdminPage() {
		return "admin";
	}
	@RequestMapping(value="/miss")
	public String getMissPage() {
		return "exam/index";
	}
}
