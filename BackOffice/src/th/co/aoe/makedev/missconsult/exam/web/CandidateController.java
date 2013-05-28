// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/26/2012 11:58:44 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CandidateController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.form.CandidateForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.exam.utils.IMakeDevUtils;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/candidate"})
@SessionAttributes(value={"candidateForm","UserMissContact"})
public class CandidateController
{
	private static int PAGE_SIZE=20;

   /* @Autowired 
    public CandidateController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired CandidateController #######################");
        this.missExamService = missExamService;
    }*/

    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String init(Model model,SecurityContextHolderAwareRequestWrapper srequest)
    {
    	@SuppressWarnings("rawtypes")
		List missSeries=null;// missExamService.listMissSery();
   	    Long maId=null;
        //model.addAttribute("missSeries", missExamService.listMissSery());
        if(model.containsAttribute("UserMissContact")){
         	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
         //	missSeries=
         	//List<MissAccountSeriesMap> missAccountSeriesMaps
         	maId=missContact.getMcontactRef();
         	missSeries= missExamService.findMissAccountSeriesMapByRole(maId,missContact.getRcId());
         }
        model.addAttribute("missSeries",missSeries);
        CandidateForm candidateForm = new CandidateForm();
        candidateForm.getMissCandidate().getPagging().setPageSize(PAGE_SIZE);
        
        if(model.containsAttribute("UserMissContact")){
        	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
        	if(missContact.getIsMC()!=null && missContact.getIsMC().equals("0")){
        		  MissAccount missAccount = new MissAccount(); 
        		 missAccount.setMaId(missContact.getMcontactRef());
        		 candidateForm.getMissCandidate().setMissAccount(missAccount);
        	}
        }
        int roleMC=0;
  	  if(srequest.isUserInRole("ROLE_MANAGE_MISSCONSULT"))
  		  roleMC=1;
  	  candidateForm.getMissCandidate().setRoleMC(roleMC);
        VResultMessage vresultMessage = missExamService.searchMissCandidate(candidateForm.getMissCandidate());
        model.addAttribute("missCandidates", vresultMessage.getResultListObj());
        candidateForm.getPaging().setPageSize(PAGE_SIZE);
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
        missAccount = new MissAccount();
        if(candidateForm.getMcaCompanyName() != null && candidateForm.getMcaCompanyName().trim().length() > 0)
        {
            missAccount.setMaName(candidateForm.getMcaCompanyName());
           
        }
        if(model.containsAttribute("UserMissContact")){
        	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
        	if(missContact.getIsMC()!=null && missContact.getIsMC().equals("0")){
        		 missAccount.setMaId(missContact.getMcontactRef());
        	}
        }
      /*  if(candidateForm.getMaId() != null && candidateForm.getMaId().intValue()!=0)
        {
            missAccount.setMaId(candidateForm.getMaId());
           
        }*/
        candidateForm.getMissCandidate().setMissAccount(missAccount);
        candidateForm.getMissCandidate().setMcaUsername(candidateForm.getMcaUsername());
        candidateForm.getMissCandidate().setMcaPassword(candidateForm.getMcaPassword());
        candidateForm.getMissCandidate().setMcaFirstName(candidateForm.getMcaFirstName());
        candidateForm.getMissCandidate().setMcaLastName(candidateForm.getMcaLastName());
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
        @SuppressWarnings("rawtypes")
		List missSeries=null;// missExamService.listMissSery();
   	    Long maId=null;
        //model.addAttribute("missSeries", missExamService.listMissSery());
        if(model.containsAttribute("UserMissContact")){
         	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
         //	missSeries=
         	//List<MissAccountSeriesMap> missAccountSeriesMaps
         	maId=missContact.getMcontactRef();
         	missSeries= missExamService.findMissAccountSeriesMapByRole(maId,missContact.getRcId());
         }
        model.addAttribute("missSeries",missSeries);
        //model.addAttribute("missSeries", missExamService.listMissSery());
        candidateForm.getPaging().setPageSize(PAGE_SIZE);
        logger.debug((new StringBuilder("xxxx=candidateForm.getMissCandidate().getPagging()=")).append(candidateForm.getMissCandidate().getPagging()).toString());
        logger.debug((new StringBuilder("xxxx=candidateForm.getPaging()=")).append(candidateForm.getPaging()).toString());
        candidateForm.getMissCandidate().setPagging(candidateForm.getPaging());
        int roleMC=0;
    	  if(request.isUserInRole("ROLE_MANAGE_MISSCONSULT"))
    		  roleMC=1;
    	  candidateForm.getMissCandidate().setRoleMC(roleMC);
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
        candidateForm.setMcaBirthDate("");
        candidateForm.setMode("edit");
        MissCandidate missCandidate = missExamService.findMissCandidateById(Long.valueOf(Long.parseLong(mcaId)));
        if(missCandidate != null && missCandidate.getMcaBirthDate() != null)
            candidateForm.setMcaBirthDate(format1.format(missCandidate.getMcaBirthDate()));
        candidateForm.setMissCandidate(missCandidate);
        model.addAttribute("candidateForm", candidateForm);
        @SuppressWarnings("rawtypes")
		List missSeries=null;// missExamService.listMissSery();
   	    Long maId=null;
        //model.addAttribute("missSeries", missExamService.listMissSery());
        if(model.containsAttribute("UserMissContact")){
         	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
         //	missSeries=
         	//List<MissAccountSeriesMap> missAccountSeriesMaps
         	maId=missContact.getMcontactRef();
         	missSeries= missExamService.findMissAccountSeriesMapByRole(maId,missContact.getRcId());
         }
        model.addAttribute("missSeries",missSeries);
        //model.addAttribute("missSeries", missExamService.listMissSery());
        model.addAttribute("display", "display: none");
        model.addAttribute("message_class", "success"); 
        model.addAttribute("missIndustryMasterList", missExamService.listMissIndustryMaster());
		 model.addAttribute("missCareerMasterList", missExamService.listMissCareerMaster(missCandidate.getMissAccount().getMaId()));
		 model.addAttribute("missPositionMasterList", missExamService.listMissPositionMaster());
		 model.addAttribute("missDepartmentMasterList", missExamService.listMissDepartmentMaster());
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
        model.addAttribute("message_class", "success"); 
        model.addAttribute("missIndustryMasterList", missExamService.listMissIndustryMaster());
		 model.addAttribute("missCareerMasterList", missExamService.listMissCareerMaster(null));
        return "exam/template/candidateAccount";
    }

    @RequestMapping(value={"/action/{section}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doAction(HttpServletRequest request, @PathVariable String section, @ModelAttribute(value="candidateForm") CandidateForm candidateForm, BindingResult result, Model model)
    {
        String mode = candidateForm.getMode();
        String message = "";
        String message_class="";
      //  logger.debug((new StringBuilder(" aoeeeeeeeeeeee =")).append(section).toString());
        candidateForm.getMissCandidate().setSection(section);
        Long id = null;
        if(candidateForm.getMcaBirthDate() != null && candidateForm.getMcaBirthDate().trim().length() > 0)
            try
            {
                candidateForm.getMissCandidate().setMcaBirthDate(format2.parse(candidateForm.getMcaBirthDate()));
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }
        if(mode != null)
            if(mode.equals("new"))
            {
            	MissCandidate missCandidate  = missExamService.saveMissCandidate(candidateForm.getMissCandidate());
            //    id = missExamService.saveMissCandidate(candidateForm.getMissCandidate());
            	id=missCandidate.getMcaId();
                candidateForm.getMissCandidate().setMcaId(id);
                candidateForm.setMode("edit");
                message_class="success";
                message = "Save success !";
            } else
            if(mode.equals("edit"))
            {
            	int recordReturn=  missExamService.updateMissCandidate(candidateForm.getMissCandidate());
                id = candidateForm.getMissCandidate().getMcaId();
                if(recordReturn!=-1){
                	message_class="success";
                	message = "Update success !";
                }else{
                	message_class="error";
                	message = "Can't Update !";
                }
                
            }
            	
        MissCandidate missCandidate = missExamService.findMissCandidateById(id);
        candidateForm.setMissCandidate(missCandidate);
        model.addAttribute("message", message);
        model.addAttribute("message_class", message_class);
        model.addAttribute("display", "display: block");
        candidateForm.getMissCandidate().setSection(section);
        @SuppressWarnings("rawtypes")
		List missSeries=null;// missExamService.listMissSery();
   	    Long maId=null;
        //model.addAttribute("missSeries", missExamService.listMissSery());
        if(model.containsAttribute("UserMissContact")){
         	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
         //	missSeries=
         	//List<MissAccountSeriesMap> missAccountSeriesMaps
         	maId=missContact.getMcontactRef();
         	missSeries= missExamService.findMissAccountSeriesMapByRole(maId,missContact.getRcId());
         }
        model.addAttribute("missSeries",missSeries);
       // model.addAttribute("missSeries", missExamService.listMissSery());
        model.addAttribute("candidateForm", candidateForm);
        model.addAttribute("missIndustryMasterList", missExamService.listMissIndustryMaster());
		 model.addAttribute("missCareerMasterList", missExamService.listMissCareerMaster(missCandidate.getMissAccount().getMaId()));
		 model.addAttribute("missPositionMasterList", missExamService.listMissPositionMaster());
		 model.addAttribute("missDepartmentMasterList", missExamService.listMissDepartmentMaster());
        return "exam/template/candidateAccount";
    }
    
    @RequestMapping(value={"/getcandidateinfo"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public  @ResponseBody MissCandidate getcandidateinfo(Model model,HttpServletRequest request)
	    {
		 return missExamService.findMissCandidateByCitizendIdAndEmail(request.getParameter("citizendID"), request.getParameter("email"));
	    }
    @SuppressWarnings("deprecation")
	@RequestMapping(value={"/export"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public void export(HttpServletRequest request, HttpServletResponse response)
    {
    	
    	String mcaIds=request.getParameter("id");
    	MissCandidate missCandidate =new MissCandidate();
    	missCandidate.setMcaIds(mcaIds);
    	 VResultMessage vresultMessage = missExamService.exportMissCandidate(missCandidate); 
    	// vresultMessage.getResultListObj()
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Candidate");
      //  HSSFRow row = sheet.createRow(0);
      //  HSSFCellStyle style = wb.createCellStyle(); 
     
        int indexRow = 0;  
	    HSSFCellStyle cellStyle = wb.createCellStyle();
	    HSSFCellStyle cellStyle2 = wb.createCellStyle();
	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 
	  
	    cellStyle.setFillBackgroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());     
	    cellStyle.setWrapText(true);
	    
	    cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    cellStyle2.setWrapText(true); 
	   
	 
				//Header 5
			    HSSFRow row = sheet.createRow(indexRow);
			    HSSFCell cell = row.createCell((short)0);
			   
			    cell = row.createCell((short)0);	    
			    cell.setCellValue("No");
			     cell.setCellStyle(cellStyle);	   
			    cell = row.createCell((short)1);	    
			    cell.setCellValue("Username/Password");
			    cell.setCellStyle(cellStyle); 
			    
			    cell = row.createCell((short)2);	    
			    cell.setCellValue("Company");
			    cell.setCellStyle(cellStyle);
			    
			    cell = row.createCell((short)3);	    
			    cell.setCellValue("Series");
			    cell.setCellStyle(cellStyle);
			    
			    indexRow++;
			   
			    sheet.setColumnWidth((short)0,(short)((50*8)/((double)1/20) ));
			    sheet.setColumnWidth((short)1,(short)((50*8)/((double)1/20) ));
			    sheet.setColumnWidth((short)2,(short)((50*8)/((double)1/20) ));
			    sheet.setColumnWidth((short)3,(short)((50*8)/((double)1/20) )); 
			   @SuppressWarnings("unchecked")
			List<MissCandidate> missCandidates= vresultMessage.getResultListObj();
			   int rowIndex=1;
			   for (MissCandidate missCandidate2 : missCandidates) {
				   row = sheet.createRow(indexRow);
				    indexRow++;
				    cell = row.createCell((short)0);	    
				     cell.setCellValue(rowIndex++);
				    cell.setCellStyle(cellStyle2); 
				    cell = row.createCell((short)1);	    
				    cell.setCellValue(missCandidate2.getMcaUsername()+" / "+missCandidate2.getMcaPassword());
				    cell.setCellStyle(cellStyle2); 
				    
				    cell = row.createCell((short)2);	    
				     cell.setCellValue(missCandidate2.getMissAccount().getMaName());
				    cell.setCellStyle(cellStyle2);
				    cell = row.createCell((short)3);	    
				    cell.setCellValue(missCandidate2.getMissSery().getMsSeriesName());
				    cell.setCellStyle(cellStyle2); 
			 } 
        response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=Candidate.xls");
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

    //private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    //04-17-1983
    private static SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
    private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
