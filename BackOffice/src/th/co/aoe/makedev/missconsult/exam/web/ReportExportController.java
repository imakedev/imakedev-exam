package th.co.aoe.makedev.missconsult.exam.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.EPTNormReport;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;

@Controller
@RequestMapping(value={"/reportExport"})
public class ReportExportController {
	private static Logger logger = Logger.getRootLogger();
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
}
