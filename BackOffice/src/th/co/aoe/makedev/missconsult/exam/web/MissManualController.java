package th.co.aoe.makedev.missconsult.exam.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.exam.form.ManualForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.exam.utils.IMakeDevUtils;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/manual"})
@SessionAttributes(value={"manualForm"})
public class MissManualController {
	   private static Logger logger = Logger.getRootLogger();
	   @Autowired
	    private MissExamService missExamService;
	 /* @Autowired
	    public MissManualController(MissExamService missExamService)
	    {
	        logger.debug("########################### @Autowired MissManualController #######################");
	        this.missExamService = missExamService;
	    }
*/
	    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	    public String search(Model model)
	    {
	    	  // get sery that company have 
	    	/* List missSeries= missExamService.listMissSery();
	    	  model.addAttribute("missSeries",missSeries);*/
	    	 
	    	  ManualForm manualForm = new ManualForm();
	    	  manualForm.getMissManual().getPagging().setPageSize(100);
	    	 
	    	 
	         VResultMessage vresultMessage = missExamService.searchMissManual(manualForm.getMissManual());
	         model.addAttribute("missManuals", vresultMessage.getResultListObj());
	         manualForm.getPaging().setPageSize(3);
	         manualForm.setPageCount(IMakeDevUtils.calculatePage(manualForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
	       
	        model.addAttribute("manualForm", manualForm);
	        return "exam/template/manual";
	    }
}
