// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:05:06 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MissExamController.java

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
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.exam.form.MissExamForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.exam.utils.IMakeDevUtils;
import th.co.aoe.makedev.missconsult.xstream.MissExam;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/appointments"})
@SessionAttributes(value={"missExamForm"})
public class MissExamController
{
	private static int PAGE_SIZE=20;
 /*   @Autowired
    public MissExamController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired MissExamController #######################");
        this.missExamService = missExamService;
    }*/

    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String init(Model model)
    {
        MissExamForm missExamForm = new MissExamForm();
        missExamForm.getMissExam().getPagging().setPageSize(PAGE_SIZE);
        VResultMessage vresultMessage = missExamService.searchMissExam(missExamForm.getMissExam());
        model.addAttribute("missExams", vresultMessage.getResultListObj());
        missExamForm.getPaging().setPageSize(PAGE_SIZE);
        missExamForm.setPageCount(IMakeDevUtils.calculatePage(missExamForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("missExamForm", missExamForm);
        return "exam/template/testSearch";
    }

    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doSearch(HttpServletRequest request, @ModelAttribute(value="missExamForm") MissExamForm missExamForm, BindingResult result, Model model)
    {
        String mode = missExamForm.getMode();
        if(mode != null && mode.equals("deleteItems"))
        {
            missExamForm.getMissExam().setMeIds(missExamForm.getMeIdArray());
            missExamService.deleteMissExam(missExamForm.getMissExam(), "deleteMissExamItems");
        } else
        if(mode != null && mode.equals("delete"))
            missExamService.deleteMissExam(missExamForm.getMissExam(), "deleteMissExam");
        else
        if(mode != null && mode.equals("doBack"))
            if(model.containsAttribute("missExamForm"))
                missExamForm = (MissExamForm)model.asMap().get("missExamForm");
            else
                missExamForm = new MissExamForm();
        missExamForm.getPaging().setPageSize(PAGE_SIZE);
        logger.debug((new StringBuilder("xxxx=missExamForm.getMissExam().getPagging()=")).append(missExamForm.getMissExam().getPagging()).toString());
        logger.debug((new StringBuilder("xxxx=missExamForm.getPaging()=")).append(missExamForm.getPaging()).toString());
        missExamForm.getMissExam().setPagging(missExamForm.getPaging());
        VResultMessage vresultMessage = missExamService.searchMissExam(missExamForm.getMissExam());
        missExamForm.setPageCount(IMakeDevUtils.calculatePage(missExamForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("missExams", vresultMessage.getResultListObj());
        model.addAttribute("missExamForm", missExamForm);
        return "exam/template/testSearch";
    }

    @RequestMapping(value={"/item/{meId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getItem(@PathVariable String meId, Model model)
    {
        MissExamForm missExamForm = null;
        if(model.containsAttribute("missExamForm"))
            missExamForm = (MissExamForm)model.asMap().get("missExamForm");
        else
            missExamForm = new MissExamForm();
        missExamForm.setMode("edit");
        MissExam missExam = missExamService.findMissExamById(Long.valueOf(Long.parseLong(meId)));
        missExamForm.setMissExam(missExam);
        model.addAttribute("missExamForm", missExamForm);
        model.addAttribute("display", "display: none");
        return "exam/template/companyAccount";
    }

    @RequestMapping(value={"/new"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm(Model model)
    {
        MissExamForm missExamForm = null;
        if(model.containsAttribute("missExamForm"))
            missExamForm = (MissExamForm)model.asMap().get("missExamForm");
        else
            missExamForm = new MissExamForm();
        missExamForm.setMissExam(new MissExam());
        missExamForm.setMode("new");
        model.addAttribute("display", "display: none");
        return "exam/template/testManagement";
    }

    @RequestMapping(value={"/action/{section}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doAction(HttpServletRequest request, @PathVariable String section, @ModelAttribute(value="missExamForm") MissExamForm missExamForm, BindingResult result, Model model)
    {
        String mode = missExamForm.getMode();
        String message = "";
        String message_class="";
        missExamForm.getMissExam().setSection(section);
        Long id = null;
        if(mode != null)
            if(mode.equals("new"))
            {
                id = missExamService.saveMissExam(missExamForm.getMissExam());
                missExamForm.getMissExam().setMeId(id);
                missExamForm.setMode("edit");
                message = "Save success !";
                message_class="success";
            } else
            if(mode.equals("edit"))
            {
                missExamService.updateMissExam(missExamForm.getMissExam());
                id = missExamForm.getMissExam().getMeId();
                message = "Update success !";
                message_class="success";
            }
        MissExam missExam = missExamService.findMissExamById(id);
        missExamForm.setMissExam(missExam);
        model.addAttribute("message", message);
        model.addAttribute("message_class", message_class);
        model.addAttribute("display", "display: block");
        missExamForm.getMissExam().setSection(section);
        model.addAttribute("missExamForm", missExamForm);
        return "exam/template/testManagement";
    }

    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
