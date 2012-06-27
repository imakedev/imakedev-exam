// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:05:27 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ResultController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

import th.co.aoe.makedev.missconsult.exam.form.ResultForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.exam.utils.IMakeDevUtils;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/result"})
@SessionAttributes(value={"resultForm"})
public class ResultController
{
	  private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 

  /*  @Autowired
    public ResultController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired ResultController #######################");
        this.missExamService = missExamService;
    }
*/
    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String init(Model model)
    {
    	 List missSeries= missExamService.listMissSery();
    	  model.addAttribute("missSeries",missSeries);
    	 
    	  ResultForm resultForm = new ResultForm();
    	  resultForm.getMissTestResult().getPagging().setPageSize(3);
    	 
    	  if(missSeries!=null && missSeries.size()>0){
          		resultForm.getMissTestResult().setMsId(((MissSery)missSeries.get(0)).getMsId());
    	  }
    		MissCandidate missCandidate =new MissCandidate();
        	MissAccount missAccount=new MissAccount();
        	missAccount.setMaName(resultForm.getMcaCompanyName());
        	missCandidate.setMissAccount(missAccount);
        	missCandidate.setMcaUsername(resultForm.getMcaUsername());
        	missCandidate.setMcaFirstName(resultForm.getMcaFirstName());
        	missCandidate.setMcaLastName(resultForm.getMcaLastName());
        	missCandidate.setMcaPosition(resultForm.getMcaPosition());
        	missCandidate.setMcaDepartment(resultForm.getMcaDepartment());
        	missCandidate.setMissAccount(missAccount);
        	
        	resultForm.getMissTestResult().setMissCandidate(missCandidate);
         VResultMessage vresultMessage = missExamService.searchMissTestResult(resultForm.getMissTestResult());
         model.addAttribute("missTestResults", vresultMessage.getResultListObj());
          resultForm.getPaging().setPageSize(3);
          resultForm.setPageCount(IMakeDevUtils.calculatePage(resultForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
          List<String> axisHeaders=new ArrayList<String>(4);
          axisHeaders.add("Fa");
          axisHeaders.add("Im");
          axisHeaders.add("Pe");
          axisHeaders.add("Ju"); 
          model.addAttribute("axisHeaders", axisHeaders);
          model.addAttribute("resultForm", resultForm);
        return "exam/template/testResultSearch";
    }
    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doSearch(HttpServletRequest request, @ModelAttribute(value="resultForm") ResultForm resultForm, BindingResult result, Model model)
    {
        String mode = resultForm.getMode();
       // String missExam_selectboxes[] = request.getParameterValues("missExam_selectbox");
      /*  private Long msId;
        private Timestamp mtrEndTime;
        private Timestamp mtrStartTime;
    	private MissCandidate missCandidate;
    	private String mcaCompanyName;*/
    	if(resultForm.getMcaSeries()!=null){
    		resultForm.getMissTestResult().setMsId(Long.parseLong(resultForm.getMcaSeries()));
    	}
    	 
    	if(resultForm.getTestFrom()!=null && resultForm.getTestFrom().trim().length()>0){
    	            try
    	            {
    	            	Timestamp fromTS1 = new Timestamp(format1.parse(resultForm.getTestFrom()+" 00:00:00").getTime()); 
    	            	resultForm.getMissTestResult().setMtrStartTime(fromTS1);
    	            }
    	            catch(ParseException e)
    	            {
    	                e.printStackTrace();
    	            }
    	}
    	if(resultForm.getTestTo()!=null && resultForm.getTestTo().trim().length()>0){
    	            try
    	            {
    	            	Timestamp fromTS1 = new Timestamp(format1.parse(resultForm.getTestTo()+" 23:59:59").getTime()); 
    	            	resultForm.getMissTestResult().setMtrEndTime(fromTS1);
    	            }
    	            catch(ParseException e)
    	            {
    	                e.printStackTrace();
    	            }
    	}
    	MissCandidate missCandidate =new MissCandidate();
    	MissAccount missAccount=new MissAccount();
    	missAccount.setMaName(resultForm.getMcaCompanyName());
    	missCandidate.setMissAccount(missAccount);
    	missCandidate.setMcaUsername(resultForm.getMcaUsername());
    	missCandidate.setMcaFirstName(resultForm.getMcaFirstName());
    	missCandidate.setMcaLastName(resultForm.getMcaLastName());
    	missCandidate.setMcaPosition(resultForm.getMcaPosition());
    	missCandidate.setMcaDepartment(resultForm.getMcaDepartment());
    	missCandidate.setMissAccount(missAccount);
    	
    	resultForm.getMissTestResult().setMissCandidate(missCandidate);
        if(mode != null && mode.equals("deleteItems"))
        {
        	resultForm.getMissTestResult().setMtrIds(resultForm.getMtrIdArray());
        //    missExamService.deleteMissSery(resultForm.getMissSery(), "deleteMissSeryItems");
            resultForm.getPaging().setPageNo(1);
        } else
        if(mode != null && mode.equals("delete")){
         //   missExamService.deleteMissSery(resultForm.getMissSery(), "deleteMissSery");
            resultForm.getPaging().setPageNo(1);
        }else
      /*  if(mode != null && mode.equals("doBack"))
        {
            if(model.containsAttribute("resultForm"))
            	resultForm = (SeriesForm)model.asMap().get("resultForm");
            else
            	resultForm = new SeriesForm();
            missExam_selectboxes = resultForm.getMissExam_selectbox();
        }*/
       // resultForm.setMissExam_selectbox(missExam_selectboxes);
       // resultForm.getMissSery().setMeIds(missExam_selectboxes);
        resultForm.getPaging().setPageSize(3);
        resultForm.getMissTestResult().setPagging(resultForm.getPaging());
        VResultMessage vresultMessage = missExamService.searchMissTestResult(resultForm.getMissTestResult());
      
        resultForm.setPageCount(IMakeDevUtils.calculatePage(resultForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("missTestResults", vresultMessage.getResultListObj());
        model.addAttribute("missSeries", missExamService.listMissSery());
        List<String> axisHeaders=new ArrayList<String>(4);
        axisHeaders.add("Fa");
        axisHeaders.add("Im");
        axisHeaders.add("Pe");
        axisHeaders.add("Ju"); 
        model.addAttribute("axisHeaders", axisHeaders);
        model.addAttribute("resultForm", resultForm);
        return "exam/template/testResultSearch";
    }
    @RequestMapping(value={"/viewAnswer/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getToDoList(@PathVariable String mtrId, Model model)
    {
        logger.debug((new StringBuilder("testtttttttttt")).append(missExamService).toString());
        model.addAttribute("aoe", "chatchai");
        System.out.println("aoee");
        return "exam/template/viewTestResult";
    }

    @RequestMapping(value={"/report/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm()
    {
        return "exam/template/testResultReport";
    }

    @RequestMapping(value={"/response/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String response()
    {
        return "exam/template/testResponse";
    }

    @RequestMapping(value={"/summary/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String viewAnswer()
    {
        return "exam/template/nopage";
    }

    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
