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
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/manual"})
@SessionAttributes(value={"manualForm","UserMissContact"})
public class MissManualController {
	   private static Logger logger = Logger.getRootLogger();
	   private static int PAGE_SIZE=100;
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
	    	  manualForm.getMissManual().getPagging().setPageSize(PAGE_SIZE);
	    	 
	    	  if(model.containsAttribute("UserMissContact")){
	          	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
	          	if(missContact.getIsMC()!=null && missContact.getIsMC().equals("0")){
	          		 /* MissAccount missAccount = new MissAccount(); 
	          		 missAccount.setMaId(missContact.getMcontactRef());*/
	          		// candidateForm.getMissCandidate().setMissAccount(missAccount);
	          		manualForm.getMissManual().setMaId(missContact.getMcontactRef());
	          	}
	          }
	    	  
	         VResultMessage vresultMessage = missExamService.searchMissManual(manualForm.getMissManual());
	         model.addAttribute("missManuals", vresultMessage.getResultListObj());
	         manualForm.getPaging().setPageSize(PAGE_SIZE);
	         manualForm.setPageCount(IMakeDevUtils.calculatePage(manualForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
	       
	        model.addAttribute("manualForm", manualForm);
	        return "exam/template/manual";
	    }
}
