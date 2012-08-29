package th.co.aoe.makedev.missconsult.exam.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value={"/reportmanagement"})
@SessionAttributes(value={"reportManagementForm"})
public class ReportManagementController {
	 @RequestMapping(value={"/eptNormReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String eptNormReport(Model model)
	    {
	        return "exam/template/ept_norm_report";
	    }
	 @RequestMapping(value={"/customerReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String customerReport(Model model)
	    {
	        return "exam/template/customer_report";
	    }
	 @RequestMapping(value={"/serviceReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String serviceReport(Model model)
	    {
	        return "exam/template/service_report";
	    }
	 @RequestMapping(value={"/productReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String productReport(Model model)
	    {
	        return "exam/template/product_report";
	    }
	 @RequestMapping(value={"/consultantReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	 public String consultantReport(Model model)
	    {
	        return "exam/template/consultant_report";
	    }
}
