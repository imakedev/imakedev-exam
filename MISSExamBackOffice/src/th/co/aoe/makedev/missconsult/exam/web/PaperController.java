// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:05:15 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PaperController.java

package th.co.aoe.makedev.missconsult.exam.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import th.co.aoe.makedev.missconsult.exam.service.MissExamService;

@Controller
@RequestMapping(value={"/paper"})
public class PaperController
{

    @Autowired
    public PaperController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired PaperController #######################");
        this.missExamService = missExamService;
    }

    private static Logger logger = Logger.getRootLogger();
    private MissExamService missExamService;

}
