// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:05:15 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PaperController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.exam.form.PaperForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissExam;
import th.co.aoe.makedev.missconsult.xstream.MissQuestion;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.MissTest;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;
import th.co.aoe.makedev.missconsult.xstream.MissTodo;

@Controller
@RequestMapping(value = { "/paper" })
@SessionAttributes(value = { "UserMissContact", "paperForm" })
public class PaperController {
	private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	private static ResourceBundle bundle;
	static{
		bundle =  ResourceBundle.getBundle( "config" );				
	}
	// private static Logger logger = Logger.getRootLogger();
	@Autowired
	private MissExamService missExamService;

	@RequestMapping(value = "/exam/info", method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String getCandidateInfo(Model model) {

		PaperForm paperForm = null;
		if (model.containsAttribute("paperForm"))
			paperForm = (PaperForm) model.asMap().get("paperForm");
		else
			paperForm = new PaperForm();

		paperForm.setMcaBirthDate("");
		model.addAttribute("missIndustryMasterList",
				missExamService.listMissIndustryMaster());
		model.addAttribute("missCareerMasterList",
				missExamService.listMissCareerMaster(null));// missCandidate.getMissAccount().getMaId()));
		model.addAttribute("missPositionMasterList",
				missExamService.listMissPositionMaster());
		model.addAttribute("missDepartmentMasterList",
				missExamService.listMissDepartmentMaster());
		List missSeries = null;// missExamService.listMissSery();
		Long maId = null;
		if (model.containsAttribute("UserMissContact")) {
			MissContact missContact = (MissContact) model.asMap().get(
					"UserMissContact");
			maId = missContact.getMcontactRef();
			missSeries = missExamService.findMissAccountSeriesMapByRole(maId,
					missContact.getRcId());
		}

		model.addAttribute("missSeries", missSeries);
		model.addAttribute("paperForm", paperForm);
		return "exam/template/dopaperCandidateInfo";
	}

	@RequestMapping(value = "/exam", method = RequestMethod.POST)
	public String getExam(HttpServletRequest request,
			@ModelAttribute(value = "paperForm") PaperForm paperForm,
			BindingResult result, Model model) {
		paperForm.getMissCandidate().setSection("1");
		if (paperForm.getMcaBirthDate() != null
				&& paperForm.getMcaBirthDate().trim().length() > 0)
			try {
				paperForm.getMissCandidate().setMcaBirthDate(
						format1.parse(paperForm.getMcaBirthDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		paperForm.getMissCandidate().setSection("1");
		missExamService.updateMissCandidate(paperForm.getMissCandidate());
		MissTest checkTest = new MissTest();
		MissExam missExam = new MissExam();
		missExam.setMeId(paperForm.getExamId());
		checkTest.setMissExam(missExam);
		checkTest.setMissSery(paperForm.getMissSery());
		//System.out.println("paperForm.getSetId()->"+paperForm.getSetId());
	//	System.out.println("SecurityContextHolder getMcaUsername->"+paperForm.getMissCandidate().getMcaUsername());
	//	MissCandidate missCandidate=missExamService.findMissCandidateById(paperForm.getMissCandidate().getMcaId());
	//	System.out.println("SecurityContextHolder missCandidate->"+missCandidate.getMcaUsername());
		checkTest.setUserid(paperForm.getMissCandidate().getMcaUsername());
		model.addAttribute("missExam", missExamService.findMissExamById(paperForm.getExamId()));
		model.addAttribute("missSery", missExamService.findMissSeryById(paperForm.getMissSery().getMsId()));
		/*System.out.println("getExamId->"+paperForm.getExamId());
		System.out.println("getMissSery->"+paperForm.getMissSery().getMsId());*/
		//List<MissTest> checkTests = missExamService.findMissTest(checkTest);
		List<MissQuestion>  missQuestions = missExamService.listMissQuestionsWithChoices(paperForm.getExamId());
		if(paperForm.getSetId()==2){
			List<MissQuestion> missQuestions1 =new ArrayList<MissQuestion>();
			List<MissQuestion> missQuestions2 =new ArrayList<MissQuestion>();
			List<MissQuestion>  missQuestions_result=new ArrayList<MissQuestion>(missQuestions.size());
			int index=0;
			for (MissQuestion missQuestion : missQuestions) {
				if(index%2==0){
					missQuestions1.add(missQuestion);
				}else
					missQuestions2.add(missQuestion);
				index++;
			}
			for (MissQuestion missQuestion1 : missQuestions1) {
				missQuestions_result.add(missQuestion1);
			}
			for (MissQuestion missQuestion2 : missQuestions2) {
				missQuestions_result.add(missQuestion2);
			}
			model.addAttribute("missQuestions", missQuestions_result);
		}else
			model.addAttribute("missQuestions", missQuestions);
		paperForm.setMcaSeries(paperForm.getMissSery().getMsId());
		model.addAttribute("paperForm", paperForm);
	
		return "exam/template/dopaper";
	}
	@RequestMapping(value = "/saveExam", method = RequestMethod.POST)
	public String postExam(HttpServletRequest request,
			@ModelAttribute(value = "paperForm") PaperForm paperForm,
			BindingResult result, Model model) {
		@SuppressWarnings("rawtypes")
		Enumeration e_num=request.getParameterNames();
		List<String> mqIdsList=new ArrayList<String>();
		List<String> mcIdsList=new ArrayList<String>();
		while (e_num.hasMoreElements()) {
			String param_name = (String) e_num.nextElement();
			if(param_name.startsWith("choice_")){
			//	if(!request.getParameter(param_name).equals("0")){
					mqIdsList.add(param_name.split("_")[1]);
					mcIdsList.add(request.getParameter(param_name));
					//System.out.println("param_name->"+param_name+",value->"+request.getParameter(param_name));
					
			//	}
			}
			/*if(param_name.startsWith("msIdCheckbox_radio_")){
				if(!request.getParameter(param_name).equals("0")){
					msIdsList.add(request.getParameter(param_name));
				}
			}*/
		}
		String[] mqIdRadio = new String[mqIdsList.size()];
		String[] mcIdRadio = new String[mcIdsList.size()];
		mqIdRadio = mqIdsList.toArray(mqIdRadio);
		mcIdRadio= mcIdsList.toArray(mcIdRadio);
		MissSery missSery=missExamService.findMissSeryById(paperForm.getMcaSeries());
		MissCandidate missCandidate=missExamService.findMissCandidateById(paperForm.getMissCandidate().getMcaId());
		MissExam missExam=missExamService.findMissExamById(paperForm.getExamId());
		
		MissTest misstest =new MissTest();
		misstest.setMissCandidate(missCandidate);
		misstest.setMissSery(missSery);
		misstest.setMissExam(missExam);
		misstest.setMqIdArray(mqIdRadio);
		misstest.setMcIdArray(mcIdRadio);
		
		missExamService.saveMissTestPaper(misstest);
		/*System.out.println("getExamId->"+paperForm.getExamId());
		System.out.println("getMcaSeries->"+paperForm.getMcaSeries());
		System.out.println("getMissCandidate->"+paperForm.getMissCandidate());
		System.out.println("getMcaId->"+paperForm.getMissCandidate().getMcaId());*/
		

	/*	System.out.println("missSery->"+missSery);
		System.out.println("getMissAccount->"+missCandidate.getMissAccount());
		System.out.println("getMissAccount->"+missCandidate.getMcaUsername());*/
		 MissTestResult missTestResult = new MissTestResult();
		 missTestResult.setMeId(paperForm.getExamId());
		 missTestResult.setMsId(paperForm.getMcaSeries());
		 
		 missTestResult.setUserid(missCandidate.getMcaUsername());
		 java.sql.Timestamp timeStampEndTime = new java.sql.Timestamp(new Date().getTime());
		 missTestResult.setMtrStartTime(timeStampEndTime);
		 missTestResult.setMtrTestDate(new Date());
		 missTestResult.setMtrStatus("0"); // 0=start test(Not finished ) ,1=test finish(Finished),2 =send response(Responsed)
		 missTestResult.setMtrRespondedStatus("0");
		 missExamService.startMissTestResult(missTestResult);
		// save MissTestResult
		  
		 missTestResult.setMtrEndTime(timeStampEndTime);
		 missTestResult.setMtrStatus("1"); // 0=start test,1=test finish,2 =send response
		 missTestResult.setMtrRespondedStatus("0");
		 missTestResult.setRootPath(bundle.getString("evaluationPath"));
		 missExamService.processMissTestResult(missTestResult);
		// MissTestResult
		 int ref=missExamService.saveOrUpdateMissTestResult(missTestResult);
		
		 // save To do List
		 MissTodo missTodo =new MissTodo();
		 missTodo.setMissAccount(paperForm.getMissCandidate().getMissAccount());
		 missTodo.setMtodoRef(Long.valueOf(ref));
		 missTodo.setMtodoType("1"); // 1 = send to approver
		 
		 missTodo.setMtodoTask(paperForm.getMissCandidate().getMissSery().getMsSeriesName()+" ("+paperForm.getMissCandidate().getMcaFirstName() +" "+paperForm.getMissCandidate().getMcaLastName()+")"); // sery (username)
		 missExamService.saveOrUpdateMissTodo(missTodo);
		return "exam/template/dopaperResult";
	}
	@RequestMapping(value = { "/getcandidateinfo" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public @ResponseBody
	MissCandidate getcandidateinfo(Model model, HttpServletRequest request) {
		MissCandidate missCandidate = missExamService
				.findMissCandidateByName(request.getParameter("username"));
		Long maId = null;
		if (model.containsAttribute("UserMissContact")) {
			MissContact missContact = (MissContact) model.asMap().get(
					"UserMissContact");
			maId = missContact.getMcontactRef();
		}
		if(maId==null)
			return null;
		else if (maId.intValue() ==1 || // maId != null
				( missCandidate != null
				&& missCandidate.getMissAccount() != null
				&& missCandidate.getMissAccount().getMaId() != null
				&& missCandidate.getMissAccount().getMaId().intValue() == maId
						.intValue() )  )
			return missCandidate;
		else
			return null;
	}
}
