// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:06:21 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   WelcomeController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissTodo;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@SessionAttributes(value={"UserMissContact"})
public class WelcomeController
{

   /* @Autowired
    public WelcomeController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired WelcomeController #######################");
        this.missExamService = missExamService;
    }
*/
    @RequestMapping(value={"/template/todolist"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getToDoList(Model model, @RequestParam(value="pageNo", required=false) String pageNoStr)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        logger.debug((new StringBuilder(" name  ===>")).append(name).toString());
        logger.debug((new StringBuilder(" auth.getAuthorities() ====>")).append(auth.getAuthorities()).toString());
        int pageNo = 1;
        if(pageNoStr != null && !pageNoStr.equals(""))
            pageNo = Integer.parseInt(pageNoStr);
        Pagging page = new Pagging();
        page.setPageNo(pageNo);
        page.setPageSize(20);
        MissTodo missTodo = new MissTodo();
        missTodo.setPagging(page);
        VResultMessage vresult = missExamService.searchMissTodo(missTodo);
        model.addAttribute("todolists", vresult.getResultListObj());
        model.addAttribute("totals", vresult.getMaxRow());
        model.addAttribute("pageObj", page);
        return "exam/template/home";
    }

    @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm(Model model)
    {
        int pageNo = 1;
        DateTime dt = new DateTime();
        Pagging page = new Pagging();
        page.setPageNo(pageNo);
        page.setPageSize(20);
        MissTodo missTodo = new MissTodo();
        missTodo.setPagging(page);
        VResultMessage vresult = missExamService.searchMissTodo(missTodo);
        MissContact missContact= missExamService.findMissContactByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("todolists", vresult.getResultListObj());
        model.addAttribute("totals", vresult.getMaxRow());
        model.addAttribute("UserMissContact", missContact);
        model.addAttribute("pageObj", page);
        model.addAttribute("systemDate", format1.format(new Date()));
        return "exam/common";
    }

   
  
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
