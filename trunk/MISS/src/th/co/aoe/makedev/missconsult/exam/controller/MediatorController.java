package th.co.aoe.makedev.missconsult.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import th.co.aoe.makedev.missconsult.exam.service.MissExamService;

@Controller
@RequestMapping("/")
public class MediatorController {
	@Autowired
	private MissExamService missExamService;
	@RequestMapping
	public String getHomePage() {
		//return "home";
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
