// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:04:58 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MissController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

import th.co.aoe.makedev.missconsult.exam.form.MissForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;

@Controller
@RequestMapping(value={"/miss"})
@SessionAttributes(value={"missForm"})
public class MissController
{

    @Autowired
    public MissController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired MissController #######################");
        this.missExamService = missExamService;
    }

    @RequestMapping(value={"/account"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String loadAccount(Model model)
    {
        MissForm missForm = null;
        if(model.containsAttribute("missForm"))
            missForm = (MissForm)model.asMap().get("missForm");
        else
            missForm = new MissForm();
        MissAccount missAccount = missExamService.findMissAccountById(Long.valueOf(1L));
        missForm.setMissAccount(missAccount);
        if(missAccount != null && missAccount.getMaContactBirthDate() != null)
            missForm.setMaContactBirthDate(format1.format(missAccount.getMaContactBirthDate()));
        model.addAttribute("display", "display: none");
        missForm.getMissAccount().setSection("0");
        model.addAttribute("missForm", missForm);
        return "exam/template/missAccount";
    }

    @RequestMapping(value={"/action/{section}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doAction(HttpServletRequest request, @PathVariable String section, @ModelAttribute(value="missForm") MissForm missForm, BindingResult result, Model model)
    {
        String mode = missForm.getMode();
        String message = "";
        missForm.getMissAccount().setSection(section);
        if(missForm.getMaContactBirthDate() != null && missForm.getMaContactBirthDate().trim().length() > 0)
            try
            {
                missForm.getMissAccount().setMaContactBirthDate(format1.parse(missForm.getMaContactBirthDate()));
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }
        missExamService.updateMissAccount(missForm.getMissAccount());
        message = "Update success !";
        MissAccount missAccount = missExamService.findMissAccountById(Long.valueOf(1L));
        missForm.setMissAccount(missAccount);
        model.addAttribute("message", message);
        model.addAttribute("display", "display: block");
        missForm.getMissAccount().setSection(section);
        model.addAttribute("missForm", missForm);
        return "exam/template/missAccount";
    }

    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    private static Logger logger = Logger.getRootLogger();
    private MissExamService missExamService;

}
