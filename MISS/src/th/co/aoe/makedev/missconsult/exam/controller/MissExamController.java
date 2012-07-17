package th.co.aoe.makedev.missconsult.exam.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.form.MissExamForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissChoice;
import th.co.aoe.makedev.missconsult.xstream.MissQuestion;
import th.co.aoe.makedev.missconsult.xstream.MissTest;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;

@Controller
@SessionAttributes( { "missExamForm" ,"systemDate","timelimit"})
public class MissExamController {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER); 
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	private static ResourceBundle bundle;
	static{
		bundle =  ResourceBundle.getBundle( "config" );				
	}
	@Autowired
	private MissExamService missExamService;
//	private static ResourceBundle bundle;
	/*static{
		  //bundle =  ResourceBundle.getBundle( "org/restlet/example/book/restlet/ch8/mailApplication" );
	//	bundle =  ResourceBundle.getBundle( "config" );				
	}*/
	/*@Autowired
	public MissExamController(MissExamService missExamService) {
		logger.debug("########################### @Autowired MissExamController #######################");
		this.missExamService = missExamService;
	}*/

	/*@InitBinder
	public void initBinder(WebDataBinder binder
			) {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor()); 
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}*/
	@RequestMapping(value="/exam/info", method = RequestMethod.GET)
    public String getExamInfo(Model model) {
		logger.debug("into getExamInfo");
	//	logger.debug(model.asMap().get("missExamForm"));
		/*MissExamForm missExamForm = null;
		 if(model.containsAttribute("missExamForm"))
			 missExamForm = (MissExamForm)model.asMap().get("missExamForm");
        else
       	 missExamForm = new MissExamForm();*/
		MissExamForm missExamForm =  (MissExamForm)model.asMap().get("missExamForm");
		/*missExamForm.setExamIndex(0);
		missExamForm.setQuestionIndex(0);*/
		model.addAttribute("missExamForm", missExamForm);
		model.addAttribute("missExam", missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()));
        return "exam/examInfo";
    }
	@RequestMapping(value="/exam/info", method = RequestMethod.POST) 
    public String postExamInfo(HttpServletRequest request, @ModelAttribute(value="missExamForm") MissExamForm missExamForm, BindingResult result, Model model){
		logger.debug("get path="+missExamForm.getMissCandidate().getMcaPictureFileName());
		missExamForm.getMissCandidate().setSection("1");
	
		//examIndex
	/*	missExamForm.setExamIndex(0);
		missExamForm.setQuestionIndex(0);*/
		 if(missExamForm.getMcaBirthDate() != null && missExamForm.getMcaBirthDate().trim().length() > 0)
	            try
	            {
	            	missExamForm.getMissCandidate().setMcaBirthDate(format1.parse(missExamForm.getMcaBirthDate()));
	            }
	            catch(ParseException e)
	            {
	                e.printStackTrace();
	            }
		missExamService.updateMissCandidate(missExamForm.getMissCandidate());
		model.addAttribute("missExamForm", missExamForm);
		model.addAttribute("missExam", missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()));
		logger.debug(model.asMap().get("missExamForm"));
        return "exam/examInfo";
    }
	@RequestMapping(value="/exam", method = RequestMethod.GET)
    public String getExam(HttpServletRequest request,Model model) {
		logger.debug(" request examIndex="+request.getParameter("examIndex"));
		logger.debug(" request questionIndex="+request.getParameter("questionIndex"));
		MissExamForm missExamForm = null;
		 if(model.containsAttribute("missExamForm")){
			 missExamForm = (MissExamForm)model.asMap().get("missExamForm");
			 MissTestResult missTestResult = new MissTestResult();
			 missTestResult.setMeId(missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMeId());
			 missTestResult.setMsId((missExamForm.getMissCandidate().getMissSery().getMsId()));
			 missTestResult.setMsId((missExamForm.getMissCandidate().getMissSery().getMsId()));
			 missTestResult.setUserid(SecurityContextHolder.getContext().getAuthentication().getName());
			 java.sql.Timestamp timeStampEndTime = new java.sql.Timestamp(new Date().getTime());
			 missTestResult.setMtrStartTime(timeStampEndTime);
			 missTestResult.setMtrTestDate(new Date());
			 missTestResult.setMtrStatus("0"); // 0=start test(Not finished ) ,1=test finish(Finished),2 =send response(Responsed)
			 missTestResult.setMtrRespondedStatus("0");
			 int timelimit=missExamService.startMissTestResult(missTestResult);
			 model.addAttribute("timelimit", timelimit);
		 }
       else{
      	 missExamForm = new MissExamForm();
      	 return "redirect:/";
       }
		 // not yet check null
		 model.addAttribute("missQuestion", missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMissQuestions().get(missExamForm.getQuestionIndex()));
		 model.addAttribute("questionTotal", missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMissQuestions().size());
		 //model.addAttribute("questionTotal", missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMissQuestions().size());
		/*// Ok
		MyUserDetails user=(MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//SecurityContextHolder.getContext().getAuthentication().stAuthentication(user);
		MyUser myUser=user.getMyUser();
		myUser.setFullName("xx");
		user.setMyUser(myUser);
		// Not Ok
		//SecurityContextHolder.getContext().setAuthentication(SecurityContextHolder.getContext().getAuthentication());
*/        return "exam/exam";
    }
	@RequestMapping(value="/exam/template", method = RequestMethod.GET)
    public String getExamTemplate(HttpServletRequest request,Model model) {
		logger.debug("into get Template");
		int examIndex=request.getParameter("examIndex")!=null?Integer.parseInt(request.getParameter("examIndex")):0;
		int questionIndex=request.getParameter("questionIndex")!=null?Integer.parseInt(request.getParameter("questionIndex")):0;
		logger.debug(" request examIndex="+examIndex);
		logger.debug(" request questionIndex="+questionIndex);
		MissExamForm missExamForm = null;
		 if(model.containsAttribute("missExamForm"))
			 missExamForm = (MissExamForm)model.asMap().get("missExamForm");
       else
      	 missExamForm = new MissExamForm();
		 missExamForm.setExamIndex(examIndex);
		 missExamForm.setQuestionIndex(questionIndex);
		 MissTest checkTest= new MissTest();
			checkTest.setMissExam(missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()));
			checkTest.setMissQuestion(missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMissQuestions().get(missExamForm.getQuestionIndex()));
			checkTest.setMissSery(missExamForm.getMissCandidate().getMissSery());
			checkTest.setUserid(SecurityContextHolder.getContext().getAuthentication().getName());
			List<MissTest> checkTests=missExamService.findMissTest(checkTest);
			List<MissChoice> missChoices= missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMissQuestions().get(missExamForm.getQuestionIndex()).getMissChoices();
			logger.debug(" checkTests is "+checkTests);
			logger.debug(" missChoices is "+missChoices);
			String answered="";
			if(missChoices!=null && missChoices.size()>0)
			for (MissChoice missChoice : missChoices) {
				logger.debug(" missChoice "+missChoice.getMcName()+", no="+missChoice.getMcNo());
				if(checkTests!=null && checkTests.size()>0)
				for (MissTest missTest : checkTests) {
					logger.debug(" missTest "+missTest.getMissChoice().getMcNo());
					missChoice.setChoiceSelect(null);
					//if(missChoice.getMcId().intValue()==missTest.getMissChoice().getMcId().intValue()) {
					if(missChoice.getMcNo().intValue()==missTest.getMissChoice().getMcNo().intValue()) {
					//	logger.debug(" choiceSelect is "+missChoice.getMcId().intValue());
						//missChoice.setChoiceSelect(missChoice.getMcId().intValue()+"");
						missChoice.setChoiceSelect(missChoice.getMcNo().intValue()+"");
						//answered="1";
					}
				}
			}
			checkTest.setMissQuestion(null);
			List<MissTest> answeredList=missExamService.findMissTest(checkTest);
			if(answeredList!=null && answeredList.size()>0)
				for (MissTest missTest : answeredList){
					answered=answered+missTest.getMissQuestion().getMqId()+",";
				} 
		  if(answered.length()>0)
			  answered=answered.substring(0,answered.length()-1);
		 model.addAttribute("answered", answered);
		 model.addAttribute("missQuestion", missExamForm.getMissCandidate().getMissSery().getMissExams().get(examIndex).getMissQuestions().get(questionIndex));
		 model.addAttribute("questionTotal", missExamForm.getMissCandidate().getMissSery().getMissExams().get(examIndex).getMissQuestions().size());
        return "exam/template/exam";
    }
	@RequestMapping(value="/exam/template", method = RequestMethod.POST)
	public String postExamTemplate(HttpServletRequest request, @ModelAttribute(value="missExamForm") MissExamForm missExamForm, BindingResult result, Model model){
		logger.debug("into Post Template");
		/*int examIndex=request.getParameter("examIndex")!=null?Integer.parseInt(request.getParameter("examIndex")):0;
		int questionIndex=request.getParameter("questionIndex")!=null?Integer.parseInt(request.getParameter("questionIndex")):0;
		*/
		String mode=missExamForm.getMode();
		logger.debug("mode="+mode);
		int questionIndex=missExamForm.getQuestionIndex();
		if(mode.equals("prev")){
			missExamForm.setQuestionIndex(questionIndex-1);
		}else if(mode.equals("next")){
			missExamForm.setQuestionIndex(questionIndex+1);
		}else if(mode.equals("goToPage")){
			//missExamForm.setQuestionIndex(questionIndex+1);
			questionIndex=missExamForm.getOldQuestionIndex();
		}
		
		//logger.debug("==================================== questionIndex="+questionIndex);
		String[] mcScores=request.getParameterValues("mcScore");
		if(mcScores!=null && mcScores.length>0){
			List<MissTest> missTests = new ArrayList(mcScores.length);
			MissTest selectTest= new MissTest();
			for (int i = 0; i < mcScores.length; i++) {
				MissTest missTest=new MissTest();
				MissChoice choice=new MissChoice();
				MissQuestion missQuestion=missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMissQuestions().get(questionIndex);
				//choice.setMcId(Long.parseLong(mcScores[i]));
				choice.setMqId(missQuestion.getMqId());
				choice.setMcNo(Long.parseLong(mcScores[i]));
				missTest.setMissExam(missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()));
				missTest.setMissQuestion(missQuestion);
				missTest.setMissSery(missExamForm.getMissCandidate().getMissSery());
				missTest.setUserid(SecurityContextHolder.getContext().getAuthentication().getName());
				missTest.setMissChoice(choice);
				missTests.add(missTest);
			}
			selectTest.setMissTests(missTests);
			missExamService.saveOrUpdateMissTest(selectTest);
		
		}
	if(mode!=null && !mode.equals("finish")){
		MissTest checkTest= new MissTest();
		checkTest.setMissExam(missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()));
		checkTest.setMissQuestion(missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMissQuestions().get(missExamForm.getQuestionIndex()));
		checkTest.setMissSery(missExamForm.getMissCandidate().getMissSery());
		checkTest.setUserid(SecurityContextHolder.getContext().getAuthentication().getName());
		List<MissTest> checkTests=missExamService.findMissTest(checkTest);
		List<MissChoice> missChoices= missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMissQuestions().get(missExamForm.getQuestionIndex()).getMissChoices();
		logger.debug(" checkTests is "+checkTests);
		logger.debug(" missChoices is "+missChoices);
		String answered="";
		if(missChoices!=null && missChoices.size()>0)
		for (MissChoice missChoice : missChoices) {
			logger.debug(" missChoice "+missChoice.getMcName()+", id="+missChoice.getMcNo());
			if(checkTests!=null && checkTests.size()>0)
			for (MissTest missTest : checkTests) {
				logger.debug(" missTest "+missTest.getMissChoice().getMcNo());
				missChoice.setChoiceSelect(null);
				if(missChoice.getMcNo().intValue()==missTest.getMissChoice().getMcNo().intValue()) {
					logger.debug(" choiceSelect is "+missChoice.getMcNo().intValue());
					missChoice.setChoiceSelect(missChoice.getMcNo().intValue()+"");
					//answered="1";
				}
			}
		}
		checkTest.setMissQuestion(null);
		List<MissTest> answeredList=missExamService.findMissTest(checkTest);
		if(answeredList!=null && answeredList.size()>0)
			for (MissTest missTest : answeredList){
				answered=answered+missTest.getMissQuestion().getMqId()+",";
			} 
	  if(answered.length()>0)
		  answered=answered.substring(0,answered.length()-1);
		logger.debug("mcScores="+mcScores);
		logger.debug(" request examIndex="+missExamForm.getExamIndex());
		logger.debug(" request questionIndex="+missExamForm.getQuestionIndex());
	
		 model.addAttribute("missQuestion", missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMissQuestions().get(missExamForm.getQuestionIndex()));
		 model.addAttribute("questionTotal", missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMissQuestions().size());
		 model.addAttribute("missExamForm",missExamForm);
		 model.addAttribute("answered", answered);
        return "exam/template/exam";
	 }else{
		 MissTestResult missTestResult = new MissTestResult();
		 missTestResult.setMeId(missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMeId());
		 missTestResult.setMsId((missExamForm.getMissCandidate().getMissSery().getMsId()));
		// missTestResult.setMsId((missExamForm.getMissCandidate().getMissSery().getMsId()));
		 missTestResult.setUserid(SecurityContextHolder.getContext().getAuthentication().getName());
		 java.sql.Timestamp timeStampEndTime = new java.sql.Timestamp(new Date().getTime());
		 missTestResult.setMtrEndTime(timeStampEndTime);
		 missTestResult.setMtrStatus("1"); // 0=start test,1=test finish,2 =send response
		 missTestResult.setMtrRespondedStatus("0");
		 missTestResult.setRootPath(bundle.getString("evaluationPath"));
		 missExamService.processMissTestResult(missTestResult);
		
		 missExamService.saveOrUpdateMissTestResult(missTestResult);
		 //0 
		 if(missExamForm.getExamIndex()<(missExamForm.getMissCandidate().getMissSery().getMissExams().size()-1)){
			 logger.debug("before "+missExamForm.getExamIndex());
			 missExamForm.setExamIndex(missExamForm.getExamIndex()+1);
			 missExamForm.setQuestionIndex(0);
			 logger.debug("affter "+missExamForm.getExamIndex());
			 model.addAttribute("missExamForm",missExamForm);
			 return "redirect:/exam/info";
		 }else 
			 return "exam/examMessage";
	 }
    }
	@RequestMapping(value="/exam", method = RequestMethod.POST)
    public String postExam(Model model) {
	/*	logger.debug("testtttttttttt"+missExamService);
		model.addAttribute("aoe", "chatchai");
		System.out.println("aoee==>"+missExamService);*/
	
        return "exam/exam";
	}
	@RequestMapping(value="/exam/message", method = RequestMethod.GET)
    public String getExamMessage(Model model) {
	/*	logger.debug("testtttttttttt"+missExamService);
		model.addAttribute("aoe", "chatchai");
		System.out.println("aoee==>"+missExamService);*/
        return "exam/examMessage";
    } 
}
