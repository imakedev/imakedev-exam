package th.co.aoe.makedev.missconsult.exam.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.form.MissExamForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;

@Controller
@RequestMapping("/")
@SessionAttributes(value={"missExamForm","systemDate"})
public class MediatorController {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER); 
	 private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	@Autowired
	private MissExamService missExamService;
	@RequestMapping( method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String getCandidateInfo(Model model) {
		//return "home";
		MissExamForm missExamForm = null;
		 if(model.containsAttribute("missExamForm"))
			 missExamForm = (MissExamForm)model.asMap().get("missExamForm");
         else
        	 missExamForm = new MissExamForm();
		 MissCandidate missCandidate= missExamService.findMissCandidateByName(SecurityContextHolder.getContext().getAuthentication().getName());
		 missExamForm.setMissCandidate(missCandidate);
		 //SecurityContextHolder.getContext().getAuthentication().ggetName().get;
		 model.addAttribute("missExamForm", missExamForm);
		 model.addAttribute("systemDate", format1.format(new Date()));
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
