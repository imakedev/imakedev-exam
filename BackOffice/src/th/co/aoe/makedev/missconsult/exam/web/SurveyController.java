// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:05:58 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SurveyController.java

package th.co.aoe.makedev.missconsult.exam.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.exam.form.SurveyForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;

@Controller
@RequestMapping(value={"/survey"})
@SessionAttributes(value={"surveyForm"})
public class SurveyController
{

   /* @Autowired
    public SurveyController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired SurveyController #######################");
        this.missExamService = missExamService;
    }*/

    @RequestMapping(value={"/init"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getToDoList(Model model)
    {
        logger.debug((new StringBuilder("testtttttttttt")).append(missExamService).toString());
        model.addAttribute("aoe", "chatchai");
        System.out.println("aoee");
        return "exam/template/surveySend";
    }

    @RequestMapping(value={"/sendmail"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String sendMail(@ModelAttribute(value="surveyForm") SurveyForm surveyForm, BindingResult result, Model model)
    {
        logger.debug((new StringBuilder("testtttttttttt")).append(missExamService).toString());
        model.addAttribute("aoe", "chatchai");
        System.out.println("aoee");
        return "exam/template/surveySend";
    }

    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
