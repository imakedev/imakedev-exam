// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:05:15 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PaperController.java

package th.co.aoe.makedev.missconsult.exam.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.exam.form.MissExamForm;
import th.co.aoe.makedev.missconsult.exam.form.PaperForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;

@Controller
@RequestMapping(value={"/paper"})
@SessionAttributes(value={"paperForm"})
public class PaperController
{
    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;
    
    @RequestMapping( value="/exam/info",method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String getCandidateInfo(Model model) {
		//String userid=SecurityContextHolder.getContext().getAuthentication().getName();
		//0=not yet test finish, 1=  test finish
    	PaperForm paperForm = null;
			 if(model.containsAttribute("paperForm"))
				 paperForm = (PaperForm)model.asMap().get("paperForm");
	         else
	        	 paperForm = new PaperForm();
		/*	 MissCandidate missCandidate= missExamService.findMissCandidateByName(userid);
			 if(missCandidate != null && missCandidate.getMcaBirthDate() != null)
				 paperForm.setMcaBirthDate(format1.format(missCandidate.getMcaBirthDate()));
			 paperForm.setMissCandidate(missCandidate);*/
			 //SecurityContextHolder.getContext().getAuthentication().ggetName().get;
			 model.addAttribute("paperForm", paperForm);
			 model.addAttribute("missCareerMasterList", missExamService.listMissCareerMaster());
			return "exam/candidateInfo";
	}
	@RequestMapping(value="/exam/template/{mcaId}/{msId}/{set}", method = RequestMethod.GET)
    public String getExamTemplate(HttpServletRequest request,Model model ,@PathVariable String mcaId,@PathVariable Long msId,@PathVariable Long set) {
		PaperForm paperForm = null;
		 if(model.containsAttribute("paperForm"))
			 paperForm = (PaperForm)model.asMap().get("paperForm");
       else
    	   paperForm = new PaperForm();
        return "exam/template/exam";
    }
	@RequestMapping(value="/exam/template", method = RequestMethod.POST)
    public String postExamTemplate(HttpServletRequest request, @ModelAttribute(value="missExamForm") MissExamForm missExamForm, BindingResult result,Model model) {
		PaperForm paperForm = null;
		 if(model.containsAttribute("paperForm"))
			 paperForm = (PaperForm)model.asMap().get("paperForm");
       else
    	   paperForm = new PaperForm();
        return "exam/template/examMessage";
    }
    //return "exam/candidateInfo";

}
