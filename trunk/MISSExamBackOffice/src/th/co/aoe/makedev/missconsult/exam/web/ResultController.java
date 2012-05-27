// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:05:27 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ResultController.java

package th.co.aoe.makedev.missconsult.exam.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import th.co.aoe.makedev.missconsult.exam.service.MissExamService;

@Controller
@RequestMapping(value={"/result"})
public class ResultController
{

    @Autowired
    public ResultController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired ResultController #######################");
        this.missExamService = missExamService;
    }

    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getToDoList(Model model)
    {
        logger.debug((new StringBuilder("testtttttttttt")).append(missExamService).toString());
        model.addAttribute("aoe", "chatchai");
        System.out.println("aoee");
        return "exam/template/testResultSearch";
    }

    @RequestMapping(value={"/viewAnswer/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getToDoList(@PathVariable String mtrId, Model model)
    {
        logger.debug((new StringBuilder("testtttttttttt")).append(missExamService).toString());
        model.addAttribute("aoe", "chatchai");
        System.out.println("aoee");
        return "exam/template/viewTestResult";
    }

    @RequestMapping(value={"/report/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm()
    {
        return "exam/template/testResultReport";
    }

    @RequestMapping(value={"/response/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String response()
    {
        return "exam/template/testResponse";
    }

    @RequestMapping(value={"/summary/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String viewAnswer()
    {
        return "exam/template/nopage";
    }

    private static Logger logger = Logger.getRootLogger();
    private MissExamService missExamService;

}
