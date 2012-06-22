// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/26/2012 11:58:44 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CandidateController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.form.CandidateForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.exam.utils.IMakeDevUtils;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/candidate"})
@SessionAttributes(value={"candidateForm"})
public class CandidateController
{

   /* @Autowired
    public CandidateController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired CandidateController #######################");
        this.missExamService = missExamService;
    }*/

    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String init(Model model)
    {
        model.addAttribute("missSeries", missExamService.listMissSery());
        CandidateForm candidateForm = new CandidateForm();
        candidateForm.getMissCandidate().getPagging().setPageSize(3);
        VResultMessage vresultMessage = missExamService.searchMissCandidate(candidateForm.getMissCandidate());
        model.addAttribute("missCandidates", vresultMessage.getResultListObj());
        candidateForm.getPaging().setPageSize(3);
        candidateForm.setPageCount(IMakeDevUtils.calculatePage(candidateForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("candidateForm", candidateForm);
        return "exam/template/candidateSearch";
    }

    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doSearch(HttpServletRequest request, @ModelAttribute(value="candidateForm") CandidateForm candidateForm, BindingResult result, Model model)
    {
        String mode = candidateForm.getMode();
        logger.debug((new StringBuilder("xxxxxxxxxxxxxxxxxx candidateForm.getMcaSeries()=")).append(candidateForm.getMcaSeries()).toString());
        candidateForm.getMissCandidate().setMcaStatus(candidateForm.getMcaStatus());
        MissSery missSery = null;
        if(candidateForm.getMcaSeries() != null && !candidateForm.getMcaSeries().equals("-1"))
        {
            missSery = new MissSery();
            missSery.setMsId(Long.valueOf(Long.parseLong(candidateForm.getMcaSeries())));
        }
        MissAccount missAccount = null;
        candidateForm.getMissCandidate().setMissSery(missSery);
        if(candidateForm.getMcaCompanyName() != null && candidateForm.getMcaCompanyName().trim().length() > 0)
        {
            missAccount = new MissAccount();
            missAccount.setMaName(candidateForm.getMcaCompanyName());
        }
        candidateForm.getMissCandidate().setMissAccount(missAccount);
        candidateForm.getMissCandidate().setMcaUsername(candidateForm.getMcaUsername());
        candidateForm.getMissCandidate().setMcaPassword(candidateForm.getMcaPassword());
        if(mode != null && mode.equals("deleteItems"))
        {
            candidateForm.getMissCandidate().setMcaIds(candidateForm.getMcaIdArray());
            missExamService.deleteMissCandidate(candidateForm.getMissCandidate(), ServiceConstant.MISS_CANDIDATE_ITEMS_DELETE);// "deleteMissCandidateItems");
            candidateForm.getPaging().setPageNo(1);
        } else
        if(mode != null && mode.equals("delete")){
            missExamService.deleteMissCandidate(candidateForm.getMissCandidate(), ServiceConstant.MISS_CANDIDATE_DELETE);//"deleteMissCandidate");
            candidateForm.getPaging().setPageNo(1);
        }else
        if(mode != null && mode.equals("doBack"))
            if(model.containsAttribute("candidateForm"))
                candidateForm = (CandidateForm)model.asMap().get("candidateForm");
            else
                candidateForm = new CandidateForm();
        model.addAttribute("missSeries", missExamService.listMissSery());
        candidateForm.getPaging().setPageSize(3);
        logger.debug((new StringBuilder("xxxx=candidateForm.getMissCandidate().getPagging()=")).append(candidateForm.getMissCandidate().getPagging()).toString());
        logger.debug((new StringBuilder("xxxx=candidateForm.getPaging()=")).append(candidateForm.getPaging()).toString());
        candidateForm.getMissCandidate().setPagging(candidateForm.getPaging());
        VResultMessage vresultMessage = missExamService.searchMissCandidate(candidateForm.getMissCandidate());
        candidateForm.setPageCount(IMakeDevUtils.calculatePage(candidateForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("missCandidates", vresultMessage.getResultListObj());
        model.addAttribute("candidateForm", candidateForm);
        return "exam/template/candidateSearch";
    }

    @RequestMapping(value={"/item/{mcaId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getItem(@PathVariable String mcaId, Model model)
    {
        CandidateForm candidateForm = null;
        if(model.containsAttribute("candidateForm"))
            candidateForm = (CandidateForm)model.asMap().get("candidateForm");
        else
            candidateForm = new CandidateForm();
        candidateForm.setMode("edit");
        MissCandidate missCandidate = missExamService.findMissCandidateById(Long.valueOf(Long.parseLong(mcaId)));
        if(missCandidate != null && missCandidate.getMcaBirthDate() != null)
            candidateForm.setMcaBirthDate(format1.format(missCandidate.getMcaBirthDate()));
        candidateForm.setMissCandidate(missCandidate);
        model.addAttribute("candidateForm", candidateForm);
        model.addAttribute("missSeries", missExamService.listMissSery());
        model.addAttribute("display", "display: none");
        return "exam/template/candidateAccount";
    }

    @RequestMapping(value={"/new"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm(Model model)
    {
        CandidateForm candidateForm = null;
        if(model.containsAttribute("candidateForm"))
            candidateForm = (CandidateForm)model.asMap().get("candidateForm");
        else
            candidateForm = new CandidateForm();
        candidateForm.setMissCandidate(new MissCandidate());
        candidateForm.setMode("new");
        model.addAttribute("display", "display: none");
        return "exam/template/candidateAccount";
    }

    @RequestMapping(value={"/action/{section}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doAction(HttpServletRequest request, @PathVariable String section, @ModelAttribute(value="candidateForm") CandidateForm candidateForm, BindingResult result, Model model)
    {
        String mode = candidateForm.getMode();
        String message = "";
        logger.debug((new StringBuilder(" aoeeeeeeeeeeee =")).append(section).toString());
        candidateForm.getMissCandidate().setSection(section);
        Long id = null;
        if(candidateForm.getMcaBirthDate() != null && candidateForm.getMcaBirthDate().trim().length() > 0)
            try
            {
                candidateForm.getMissCandidate().setMcaBirthDate(format1.parse(candidateForm.getMcaBirthDate()));
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }
        if(mode != null)
            if(mode.equals("new"))
            {
                id = missExamService.saveMissCandidate(candidateForm.getMissCandidate());
                candidateForm.getMissCandidate().setMcaId(id);
                candidateForm.setMode("edit");
                message = "Save success !";
            } else
            if(mode.equals("edit"))
            {
                missExamService.updateMissCandidate(candidateForm.getMissCandidate());
                id = candidateForm.getMissCandidate().getMcaId();
                message = "Update success !";
            }
        MissCandidate missCandidate = missExamService.findMissCandidateById(id);
        candidateForm.setMissCandidate(missCandidate);
        model.addAttribute("message", message);
        model.addAttribute("display", "display: block");
        candidateForm.getMissCandidate().setSection(section);
        model.addAttribute("missSeries", missExamService.listMissSery());
        model.addAttribute("candidateForm", candidateForm);
        return "exam/template/candidateAccount";
    }

    @RequestMapping(value={"/export"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public void export(HttpServletRequest request, HttpServletResponse response)
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("new sheet");
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setBorderBottom((short)1);
        style.setBottomBorderColor((short)8);
        style.setBorderLeft((short)1);
        style.setLeftBorderColor((short)8);
        style.setBorderRight((short)1);
        style.setRightBorderColor((short)8);
        style.setBorderTop((short)1);
        style.setTopBorderColor((short)8);
        style.setAlignment((short)2);
        style.setWrapText(true);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("\u0E41\u0E1A\u0E1A\u0E41\u0E2A\u0E14\u0E07\u0E23\u0E32\u0E22\u0E25\u0E30\u0E40\u0E2D\u0E35\u0E22\u0E14\u0E01\u0E32\u0E23\u0E43\u0E0A\u0E49\u0E07\u0E32\u0E19\u0E40\u0E25\u0E02\u0E2B\u0E21\u0E32\u0E22\u0E42\u0E17\u0E23\u0E04\u0E21\u0E19\u0E32\u0E04\u0E21\u0E2A\u0E33\u0E2B\u0E23\u0E31\u0E1A\u0E01\u0E32\u0E23\u0E43\u0E2B\u0E49\u0E1A\u0E23\u0E34\u0E01\u0E32\u0E23\u0E42\u0E17\u0E23\u0E28\u0E31\u0E1E\u0E17\u0E4C\u0E40\u0E04\u0E25\u0E34\u0E48\u0E19\u0E17\u0E35\u0E48*");
        cell.setCellStyle(style);
        for(int i = 1; i <= 12; i++)
            row.createCell(i).setCellStyle(style);

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellStyle(style);
        for(int i = 1; i <= 12; i++)
            if(i == 1)
            {
                cell = row.createCell(i);
                cell.setCellValue("\u0E08\u0E33\u0E19\u0E27\u0E19\u0E01\u0E32\u0E23\u0E43\u0E0A\u0E49\u0E07\u0E32\u0E19\u0E40\u0E25\u0E02\u0E2B\u0E21\u0E32\u0E22(\u0E2B\u0E21\u0E32\u0E22\u0E40\u0E25\u0E02)");
                cell.setCellStyle(style);
            } else
            {
                row.createCell(i).setCellStyle(style);
            }

        row = sheet.createRow(2);
        for(int i = 0; i <= 12; i++)
        {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            if(i < 3)
                cell.setCellValue(i + 1);
            else
                cell.setCellValue(i);
        }

        row = sheet.createRow(3);
        for(int i = 0; i <= 12; i++)
        {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            if(i == 0)
                cell.setCellValue("\u0E01\u0E25\u0E38\u0E48\u0E21\u0E40\u0E25\u0E02\u0E2B\u0E21\u0E32\u0E22\u0E17\u0E35\u0E48\u0E44\u0E14\u0E49\u0E23\u0E31\u0E1A\u0E08\u0E31\u0E14\u0E2A\u0E23\u0E23");
            else
            if(i == 1)
                cell.setCellValue("\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48\u0E40\u0E1B\u0E34\u0E14\u0E43\u0E0A\u0E49\u0E07\u0E32\u0E19\u0E01\u0E25\u0E38\u0E48\u0E21\u0E40\u0E25\u0E02\u0E2B\u0E21\u0E32\u0E22 (Date of Activation)");
            else
            if(i == 2)
                cell.setCellValue("\u0E40\u0E25\u0E02\u0E2B\u0E21\u0E32\u0E22\u0E17\u0E35\u0E48\u0E40\u0E1B\u0E34\u0E14\u0E43\u0E0A\u0E49\u0E07\u0E32\u0E19 (Active Number)");
        }

        row = sheet.createRow(4);
        for(int i = 0; i <= 12; i++)
        {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            if(i == 2)
                cell.setCellValue("\u0E41\u0E1A\u0E1A\u0E0A\u0E33\u0E23\u0E30\u0E04\u0E48\u0E32\u0E1A\u0E23\u0E34\u0E01\u0E32\u0E23\u0E23\u0E32\u0E22\u0E40\u0E14\u0E37\u0E2D\u0E19(Post Paid)");
            else
            if(i == 3)
                cell.setCellValue("\u0E41\u0E1A\u0E1A\u0E0A\u0E33\u0E23\u0E30\u0E04\u0E48\u0E32\u0E1A\u0E23\u0E34\u0E01\u0E32\u0E23\u0E25\u0E48\u0E27\u0E07\u0E2B\u0E19\u0E49\u0E32(Pre- Paid)");
            else
            if(i == 4)
                cell.setCellValue("\u0E40\u0E25\u0E02\u0E2B\u0E21\u0E32\u0E22\u0E17\u0E35\u0E48\u0E22\u0E31\u0E07\u0E44\u0E21\u0E48\u0E40\u0E1B\u0E34\u0E14\u0E43\u0E0A\u0E49\u0E07\u0E32\u0E19 (not yet Activated Number)");
            else
            if(i == 5)
                cell.setCellValue("\u0E40\u0E25\u0E02\u0E2B\u0E21\u0E32\u0E22\u0E17\u0E35\u0E48\u0E2D\u0E22\u0E39\u0E48\u0E23\u0E30\u0E2B\u0E27\u0E48\u0E32\u0E07\u0E01\u0E32\u0E23\u0E17\u0E33Quarantine");
            else
            if(i == 6)
                cell.setCellValue("\u0E40\u0E25\u0E02\u0E2B\u0E21\u0E32\u0E22\u0E17\u0E35\u0E48\u0E2D\u0E22\u0E39\u0E48\u0E23\u0E30\u0E2B\u0E27\u0E48\u0E32\u0E07\u0E01\u0E32\u0E23\u0E17\u0E33 SIM");
            else
            if(i == 7)
                cell.setCellValue("\u0E40\u0E25\u0E02\u0E2B\u0E21\u0E32\u0E22\u0E17\u0E35\u0E48\u0E43\u0E0A\u0E49\u0E07\u0E32\u0E19\u0E17\u0E32\u0E07\u0E40\u0E17\u0E04\u0E19\u0E34\u0E04");
            else
            if(i == 8)
                cell.setCellValue("\u0E40\u0E25\u0E02\u0E2B\u0E21\u0E32\u0E22\u0E17\u0E35\u0E48\u0E19\u0E33\u0E01\u0E25\u0E31\u0E1A\u0E21\u0E32\u0E43\u0E0A\u0E49\u0E07\u0E32\u0E19\u0E43\u0E2B\u0E21\u0E48(Reused Numbers)");
            else
            if(i == 9)
                cell.setCellValue("\u0E40\u0E25\u0E02\u0E2B\u0E21\u0E32\u0E22\u0E2A\u0E33\u0E23\u0E2D\u0E07");
            else
            if(i == 10)
                cell.setCellValue("\u0E2D\u0E37\u0E48\u0E19\u0E46(\u0E42\u0E1B\u0E23\u0E14\u0E23\u0E30\u0E1A\u0E38)");
            else
            if(i == 11)
                cell.setCellValue("\u0E40\u0E25\u0E02\u0E2B\u0E21\u0E32\u0E22\u0E04\u0E07\u0E40\u0E2B\u0E25\u0E37\u0E2D");
            else
            if(i == 12)
                cell.setCellValue("\u0E20\u0E39\u0E21\u0E34\u0E20\u0E32\u0E04\u0E17\u0E35\u0E48\u0E43\u0E0A\u0E49\u0E07\u0E32\u0E19");
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 11));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 1, 1));
        response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=\u0E17\u0E14\u0E2A\u0E2D\u0E1A.xls");
        ServletOutputStream servletOutputStream = null;
        try
        {
            servletOutputStream = response.getOutputStream();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            wb.write(servletOutputStream);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            servletOutputStream.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            servletOutputStream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
