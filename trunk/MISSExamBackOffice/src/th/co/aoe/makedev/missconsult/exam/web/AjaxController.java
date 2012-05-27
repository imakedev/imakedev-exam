// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:03:59 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AjaxController.java

package th.co.aoe.makedev.missconsult.exam.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissTodo;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/ajax"})
public class AjaxController
{

    @Autowired
    public AjaxController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired TestController #######################");
        this.missExamService = missExamService;
    }

    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getToDoList(Model model)
    {
        int pageNo = 1;
        Pagging page = new Pagging();
        page.setPageNo(pageNo);
        page.setPageSize(20);
        MissTodo missTodo = new MissTodo();
        missTodo.setPagging(page);
        VResultMessage vresult = missExamService.searchMissTodo(missTodo);
        model.addAttribute("todolists", vresult.getResultListObj());
        model.addAttribute("totals", vresult.getMaxRow());
        model.addAttribute("pageObj", page);
        return "exam/test";
    }

    private static Logger logger = Logger.getRootLogger();
    private MissExamService missExamService;

}
