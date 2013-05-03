package th.co.aoe.makedev.missconsult.exam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.EPTNormReport;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/reportExport"})
@SessionAttributes(value={"UserMissContact"})
public class ReportExportController {
//	private static Logger logger = Logger.getRootLogger();
	@Autowired
	private MissExamService missExamService;
	 @RequestMapping(value={"/eptNormReport/{maId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String eptNormReport(Model model,@PathVariable Long maId)
	    {
		 MissAccount missAccount= missExamService.findMissAccountById(1l);
		 model.addAttribute("missAccount",missAccount);
		 model.addAttribute("maId",maId);
	        return "exam/report/ept_norm_report";
	    }
	 @RequestMapping(value={"/eptNormReportListCompany"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public @ResponseBody EPTNormReport eptNormReportListCompany(Model model)
	    {
		 //Gson gson=new Gson();
		 EPTNormReport eptNormReport = new EPTNormReport();
		 eptNormReport.setMode(ServiceConstant.MANAGE_REPORT_MODE_SECTION);
		 eptNormReport.setQuery("");
		 return missExamService.findCompanies(eptNormReport);
	    }
	 @RequestMapping(value={"/eptNormReportFind/{maId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public  @ResponseBody EPTNormReport eptNormReportFind(Model model,@PathVariable String maId)
	    {
		 //Gson gson=new Gson();
		 EPTNormReport eptNormReport = new EPTNormReport();
		 eptNormReport.setMode(ServiceConstant.MANAGE_REPORT_MODE_ALL);
		 eptNormReport.setMaId(maId);
		 return missExamService.findEPTNormReport(eptNormReport);
	    }
	 @RequestMapping(value={"/compareTest/{mcaSeries}/{mtrIds}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String compareTest(Model model,@PathVariable String mcaSeries,@PathVariable String mtrIds)
	    {  
		 Long maId=1l;
		 if(model.containsAttribute("UserMissContact")){
	         	MissContact missContact= (MissContact)model.asMap().get("UserMissContact"); 
	         	maId= missContact.getMcontactRef();
		 }
		 MissAccount missAccount= missExamService.findMissAccountById(maId);
		 model.addAttribute("missAccount",missAccount);
		 model.addAttribute("mcaSeries",mcaSeries);
		 model.addAttribute("mtrIds",mtrIds);
	        return "exam/report/compare_report";
	    }
	 @SuppressWarnings("rawtypes")
	@RequestMapping(value={"/compare/{msId}/{mtrIds}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	   public  @ResponseBody Object[]  compare(Model model,@PathVariable Long msId,@PathVariable String mtrIds)
			    {
				 //Gson gson=new Gson();
		   //MissTestResult[] missTestResults=new MissTestResult[2];
		   Object[] missTestResults=new Object[3];
		   MissTestResult missTestResult1 =null;
		   MissTestResult missTestResult2  =null;
		  String[] mtrId_array= mtrIds.split("_");
		  MissTestResult missTestResult=new MissTestResult();
		  missTestResult.setMsId(msId);
		  missTestResult.setMtrIds(mtrId_array[0]);
		   VResultMessage vresultMessage = missExamService.searchMissTestResult(missTestResult);
		   missTestResult1 = (MissTestResult)((java.util.ArrayList)vresultMessage.getResultListObj().get(0)).get(0);
		   
		   missTestResult.setMtrIds(mtrId_array[1]);
		   vresultMessage = missExamService.searchMissTestResult(missTestResult);
		   missTestResult2 = (MissTestResult)((java.util.ArrayList)vresultMessage.getResultListObj().get(0)).get(0);
		     
		  // model.addAttribute("axisHeaders",);
		   missTestResults[0]=missTestResult1;
		   missTestResults[1]=missTestResult2;
		   missTestResults[2]= vresultMessage.getResultListObj().get(1);
		 
		return missTestResults;
		}
	 @SuppressWarnings("unchecked")
	@RequestMapping(value={"/compareexport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})  
	    public void compareexport(HttpServletRequest request, HttpServletResponse response ,@RequestParam(required=false) String mcaSeries,
	    		@RequestParam(required=false) String mtrIds){
	    		//@RequestParam(required=false) Long meId,@RequestParam(required=false) Long msId,@RequestParam(required=false) Long mcaId){
	    	try{
			 String  reportPath= "/opt/attach/compare/compare.jasper"; 
			 JasperPrint jasperPrint=null;
			 @SuppressWarnings("rawtypes")
			Map p =new HashMap();
			
			 p.put("URL",  "http://203.150.20.37:8080/MISSProcessImage/compareTest?mcaSeries="+mcaSeries+"&mtrIds="+mtrIds);
			 //"http://203.150.20.37/MISSProcessImage/compareTest?mcaSeries="+maId);
				 
			try {
				
				jasperPrint = JasperFillManager.fillReport(reportPath, p,new JREmptyDataSource());
				//jasperPrint = JasperFillManager.fillReport(reportPath, p, con);
				/*jasperPrint.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
				jasperPrint.setProperty("net.sf.jasperreports.default.font.name", defaultPDFFont);*/
				 
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		   //  String fileName="เทส.pdf";
			 response.addHeader("Content-disposition", "attachment; filename=compare.pdf");  
			/* response.setHeader("Content-Disposition", "inline; filename="
						+ fileName);*/
		       ServletOutputStream servletOutputStream=null;
			try {
				servletOutputStream = response.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		       try {
				JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		    try {
				servletOutputStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		       try {
				servletOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		       
	    	}catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}finally{
				 
			}
		   
	    }
}
