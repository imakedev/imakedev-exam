package th.co.aoe.makedev.missconsult.exam.web;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.form.ReportManagementForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.ConsultantReport;
import th.co.aoe.makedev.missconsult.xstream.CustomerReport;
import th.co.aoe.makedev.missconsult.xstream.EPTNormReport;
import th.co.aoe.makedev.missconsult.xstream.ProductReport;
import th.co.aoe.makedev.missconsult.xstream.ServiceReport;

import com.google.gson.Gson;

@Controller
@RequestMapping(value={"/reportmanagement"})
@SessionAttributes(value={"reportManagementForm"})
public class ReportManagementController {
	private static Logger logger = Logger.getRootLogger();
	@Autowired
	private MissExamService missExamService;
	 @RequestMapping(value={"/eptNormReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String eptNormReport(Model model)
	    {
	        return "exam/template/ept_norm_report";
	    }
	 @RequestMapping(value={"/eptNormReportListCompany"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String eptNormReportListCompany(Model model)
	    {
		 Gson gson=new Gson();
		 EPTNormReport eptNormReport = new EPTNormReport();
		 eptNormReport.setMode(ServiceConstant.MANAGE_REPORT_MODE_SECTION);
		 eptNormReport.setQuery("");
		 return gson.toJson(missExamService.findCompanies(eptNormReport));
	    }
	 @RequestMapping(value={"/eptNormReport/{maId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String eptNormReportFind(Model model,@PathVariable String maId)
	    {
		 Gson gson=new Gson();
		 EPTNormReport eptNormReport = new EPTNormReport();
		 eptNormReport.setMode(ServiceConstant.MANAGE_REPORT_MODE_ALL);
		 eptNormReport.setMaId(maId);
		 return gson.toJson(missExamService.findEPTNormReport(eptNormReport));
	    }
	 @RequestMapping(value={"/customerReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String customerReport(Model model)
	    {
	        return "exam/template/customer_report";
	    }
	 @RequestMapping(value={"/customerReportListGroup"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String customerReportListGroup(Model model)
	    {
		 Gson gson=new Gson();
		 CustomerReport customerReport = new CustomerReport();
		 customerReport.setMode(ServiceConstant.MANAGE_REPORT_MODE_SECTION);
		 customerReport.setQuery("");
		 return gson.toJson(missExamService.findCustomerReport(customerReport));
	    }
	 @RequestMapping(value={"/customerReport/{magId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String customerReportFind(Model model,@PathVariable String magId)
	    {
		 Gson gson=new Gson();
		 CustomerReport customerReport = new CustomerReport();
		 customerReport.setMode(ServiceConstant.MANAGE_REPORT_MODE_ALL);
		 customerReport.setMagId(magId);
		 return gson.toJson(missExamService.findCustomerReport(customerReport));
	    }
	 @RequestMapping(value={"/serviceReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String serviceReport(Model model)
	    {
		 ReportManagementForm reportManagementForm=null;
		 if(!model.containsAttribute("reportManagementForm")){
			  reportManagementForm =new ReportManagementForm();
			  //0=year,1=all			 
	      }else{
	    	    reportManagementForm= (ReportManagementForm)model.asMap().get("reportManagementForm");
	      }
		 DateTime datetime=new DateTime(new Date().getTime());   
		 reportManagementForm.getServiceReport().setMonth(datetime.monthOfYear().get()+"");
		 reportManagementForm.getServiceReport().setYear(datetime.year().get()+"");
		 model.addAttribute("reportManagementForm", reportManagementForm);
	        return "exam/template/service_report";
	    }
	 @RequestMapping(value={"/productReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String productReport(Model model)
	    {
		 ReportManagementForm reportManagementForm=null;
		 if(!model.containsAttribute("reportManagementForm")){
			  reportManagementForm =new ReportManagementForm();
			  //0=year,1=all
			/*reportManagementForm.setProductReport_mode("1");
			reportManagementForm.setProductReport_year("2012");*/
	      }else{
	    	    reportManagementForm= (ReportManagementForm)model.asMap().get("reportManagementForm");
	      }
		 reportManagementForm.getProductReport().setMode(ServiceConstant.MANAGE_REPORT_MODE_ALL); 
		 DateTime datetime=new DateTime(new Date().getTime());    
		 reportManagementForm.getProductReport().setYear(datetime.year().get()+"");
		 model.addAttribute("reportManagementForm", reportManagementForm);
		 model.addAttribute("reportManagement",   missExamService.findProductReport(reportManagementForm.getProductReport()));
	        return "exam/template/product_report";
	    }
	 @RequestMapping(value={"/productReportWithYear/{year}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 @ResponseBody
	 public String productReportWithYear(Model model,@PathVariable String year)
	    {  
		 Gson gson=new Gson();
		 ProductReport productReport = new ProductReport();
		 productReport.setMode(ServiceConstant.MANAGE_REPORT_MODE_SECTION);
		 productReport.setYear(year);
			return gson.toJson(missExamService.findProductReport(productReport));
	    }
	 
	 @RequestMapping(value={"/serviceReportWithYear/{mode}/{month}/{year}"}, 
			 method={org.springframework.web.bind.annotation.RequestMethod.GET})
	/* @ResponseBody
	 public String serviceReportWithYear(HttpServletResponse response,Model model,@PathVariable String mode,@PathVariable String month,@PathVariable String year)
	    { */ 
	/* public  String serviceReportWithYear(HttpServletResponse response,Model model,@PathVariable String mode,@PathVariable String month,@PathVariable String year)
		    {*/
	 public   @ResponseBody ServiceReport serviceReportWithYear(HttpServletResponse response,Model model,@PathVariable String mode,@PathVariable String month,@PathVariable String year)
	    {
		// Gson gson=new Gson();
		 ServiceReport serviceReport = new ServiceReport();
		 serviceReport.setMonth(month);
		 serviceReport.setYear(year);
		 serviceReport.setMode(mode);
		 ServiceReport report=missExamService.findServiceReport(serviceReport);
		// String value= gson.toJson(report);
		// System.out.println("value="+value);
		// model.addAttribute("value", value);
		// return "exam/report/data";
		 return report;
	    }
	 @RequestMapping(value={"/consultantReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String consultantReport(Model model)
	    {
	        return "exam/template/consultant_report";
	    }
	 @RequestMapping(value={"/consultantReportListSale"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String consultantReportListSale(Model model)
	    {
		 Gson gson=new Gson();
		 ConsultantReport consultantReport = new ConsultantReport();
		 consultantReport.setMode(ServiceConstant.MANAGE_REPORT_MODE_SECTION);
		 consultantReport.setQuery("");
		 return gson.toJson(missExamService.findConsultantReport(consultantReport));
	    }
	 @RequestMapping(value={"/customerReport/{mode}/{mcontactId}/{month}/{year}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String consultantReportFind(Model model,@PathVariable String mode,@PathVariable String mcontactId,@PathVariable String month,@PathVariable String year)
	    {
		 Gson gson=new Gson();
		 ConsultantReport consultantReport = new ConsultantReport();
		 consultantReport.setMode(mode);
		 consultantReport.setMcontactId(mcontactId);
		 consultantReport.setMonth(month);
		 consultantReport.setYear(year);
		 return gson.toJson(missExamService.findConsultantReport(consultantReport));
	    }
}
