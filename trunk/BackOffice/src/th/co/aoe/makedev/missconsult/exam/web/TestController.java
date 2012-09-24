// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:06:10 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TestController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.form.TestForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.exam.utils.IMakeDevUtils;
import th.co.aoe.makedev.missconsult.xstream.MissChoice;
import th.co.aoe.makedev.missconsult.xstream.MissExam;
import th.co.aoe.makedev.missconsult.xstream.MissExamGroup;
import th.co.aoe.makedev.missconsult.xstream.MissQuestion;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/test"})
@SessionAttributes(value={"testForm"})
public class TestController
{
	private static int PAGE_SIZE=20;
    /*@Autowired
    public TestController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired TestController #######################");
        this.missExamService = missExamService;
    }*/
    private List getGroup(){
    Pagging page = new Pagging();
    page.setPageNo(1);
    page.setPageSize(PAGE_SIZE);
    MissExamGroup missExamGroup = new MissExamGroup();
    missExamGroup.setPagging(page);
    VResultMessage vresultMessage = missExamService.searchMissExamGroup(missExamGroup);
    return vresultMessage.getResultListObj();
}
    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String init(Model model)
    {
       /* Pagging page = new Pagging();
        page.setPageNo(1);
        page.setPageSize(20);
        MissExamGroup missExamGroup = new MissExamGroup();
        missExamGroup.setPagging(page);
        VResultMessage vresultMessage = missExamService.searchMissExamGroup(missExamGroup);*/
        model.addAttribute("missExamGroups", getGroup());
       
        TestForm testForm = new TestForm();
        testForm.getMissExam().getPagging().setPageSize(PAGE_SIZE);
        VResultMessage  vresultMessage = missExamService.searchMissExam(testForm.getMissExam());
        model.addAttribute("missExams", vresultMessage.getResultListObj());
        testForm.getPaging().setPageSize(PAGE_SIZE);
        testForm.setPageCount(IMakeDevUtils.calculatePage(testForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("testForm", testForm);
        return "exam/template/testSearch";
    }
    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doSearch(HttpServletRequest request, @ModelAttribute(value="testForm") TestForm testForm, BindingResult result, Model model)
    {
        String mode = testForm.getMode();
      //  String missExam_selectboxes[] = request.getParameterValues("missExam_selectbox");
        //testForm.getMissExam().setMsSeriesName();
        MissExamGroup missExamGroup =null;
        String megId = testForm.getMegId();
        if(megId!=null && !megId.equals("0")){
        	missExamGroup=new MissExamGroup();
        	missExamGroup.setMegId(Long.parseLong(megId));
        } 
        testForm.getMissExam().setMissExamGroup(missExamGroup);
        if(mode != null && mode.equals("deleteItems"))
        {
        	testForm.getMissExam().setMeIds(testForm.getMeIdArray());
            missExamService.deleteMissExam(testForm.getMissExam(), "deleteMissExamItems");
            testForm.getPaging().setPageNo(1);
        } else
        if(mode != null && mode.equals("delete")){
            missExamService.deleteMissExam(testForm.getMissExam(), "deleteMissExam");
            testForm.getPaging().setPageNo(1);
        }
       /* else
        if(mode != null && mode.equals("doBack"))
        {
            if(model.containsAttribute("testForm"))
            	testForm = (TestForm)model.asMap().get("testForm");
            else
            	testForm = new TestForm();
            missExam_selectboxes = testForm.getMissExam_selectbox();
        }*/
       // model.addAttribute("missExams", missExamService.listMissExam());
     /*   testForm.setMissExam_selectbox(missExam_selectboxes);
        testForm.getMissExam().setMeIds(missExam_selectboxes);*/
        testForm.getPaging().setPageSize(PAGE_SIZE);
        logger.debug((new StringBuilder("xxxx=testForm.getMissExam().getPagging()=")).append(testForm.getMissExam().getPagging()).toString());
        logger.debug((new StringBuilder("xxxx=testForm.getPaging()=")).append(testForm.getPaging()).toString());
        testForm.getMissExam().setPagging(testForm.getPaging());
        VResultMessage vresultMessage = missExamService.searchMissExam(testForm.getMissExam());
       /* String meIdArray = "";
        if(missExam_selectboxes != null && missExam_selectboxes.length > 0)
        {
            int meId_size = missExam_selectboxes.length;
            String meIds[] = missExam_selectboxes;
            for(int i = 0; i < meId_size; i++)
                if(i != meId_size - 1)
                    meIdArray = (new StringBuilder(String.valueOf(meIdArray))).append(meIds[i]).append(",").toString();
                else
                    meIdArray = (new StringBuilder(String.valueOf(meIdArray))).append(meIds[i]).toString();

        }*/
        testForm.setPageCount(IMakeDevUtils.calculatePage(testForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
       // model.addAttribute("meIdArray", meIdArray);
        model.addAttribute("missExams", vresultMessage.getResultListObj());
        model.addAttribute("missExamGroups", getGroup());
        model.addAttribute("testForm", testForm);
        return "exam/template/testSearch";
    }
    @RequestMapping(value={"/copy/{meId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String copyTest(Model model,@PathVariable String meId)
    {
        model.addAttribute("missExamGroups", getGroup());
        MissExam missexam=new MissExam();
        missexam.setMeId(Long.parseLong(meId));
        missExamService.copyMissExam(missexam);
        TestForm testForm = new TestForm();
        testForm.getMissExam().getPagging().setPageSize(PAGE_SIZE);
        VResultMessage  vresultMessage = missExamService.searchMissExam(testForm.getMissExam());
        model.addAttribute("missExams", vresultMessage.getResultListObj());
        testForm.getPaging().setPageSize(PAGE_SIZE);
        testForm.setPageCount(IMakeDevUtils.calculatePage(testForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("testForm", testForm);
        return "exam/template/testSearch";
    }
    @RequestMapping(value={"/createEmpty"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doCreateEmpty(HttpServletRequest request, @ModelAttribute(value="testForm") TestForm testForm, BindingResult result, Model model)
    {
    	String megEmptyId=request.getParameter("megEmptyId");
    	String meName=request.getParameter("meName");
    	String questionCountEmpty=request.getParameter("questionCountEmpty");
    	String choiceCountEmpty=request.getParameter("choiceCountEmpty");
        logger.debug("megEmptyId====================> "+megEmptyId);
        logger.debug("meName====================> "+meName);
        logger.debug("questionCountEmpty====================> "+questionCountEmpty);
        logger.debug("choiceCountEmpty====================> "+choiceCountEmpty);
    	//createEmptyMissExam
    	if(megEmptyId!=null && !megEmptyId.equals("0")){
    		MissExamGroup missExamGroup =new MissExamGroup();
    		missExamGroup.setMegId(Long.parseLong(megEmptyId));
    		testForm.getMissExam().setMissExamGroup(missExamGroup);
    	}
    	testForm.getMissExam().setMeName(meName);
    	testForm.getMissExam().setQuestionCountEmpty(questionCountEmpty);
    	testForm.getMissExam().setChoiceCountEmpty(choiceCountEmpty);
    	
    	// set time limit
    	testForm.getMissExam().setMeTimeLimit(120l);
    	 missExamService.createEmptyMissExam(testForm.getMissExam());
    	testForm = new TestForm();
        testForm.getPaging().setPageSize(PAGE_SIZE);
        testForm.getMissExam().setPagging(testForm.getPaging());
        VResultMessage vresultMessage = missExamService.searchMissExam(testForm.getMissExam());
     
        testForm.setPageCount(IMakeDevUtils.calculatePage(testForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("missExams", vresultMessage.getResultListObj());
        model.addAttribute("missExamGroups", getGroup());
        model.addAttribute("testForm", testForm);
        return "exam/template/testSearch";
    }
    @RequestMapping(value={"/exam/{meId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getItem(@PathVariable String meId, Model model)
    {

        TestForm testForm = null;
        if(model.containsAttribute("testForm"))
        	testForm = (TestForm)model.asMap().get("testForm");
        else
        	testForm = new TestForm();
        testForm.setMode("edit");
        if(meId!=null && meId.length()>0){
        	 MissExam missExam = missExamService.findMissExamById(Long.valueOf(Long.parseLong(meId)));
             testForm.setMissExam(missExam);
        }
       
      /*  MissSeriesMap missSeriesMap = new MissSeriesMap();
        missSeriesMap.setMsId(Long.valueOf(Long.parseLong(meId)));*/
     //   VResultMessage vresultMessage = missExamService.searchMissSeriesMap(missSeriesMap);
      //  List missSeriesMaps = vresultMessage.getResultListObj();
        /*StringBuffer sb = new StringBuffer();
        int index = 0;
        if(missSeriesMaps != null && missSeriesMaps.size() > 0)
        {
            int size = missSeriesMaps.size();
            for(Iterator iterator = missSeriesMaps.iterator(); iterator.hasNext();)
            {
                MissSeriesMap entry = (MissSeriesMap)iterator.next();
                if(index != size - 1)
                    sb.append((new StringBuilder()).append(entry.getMeId()).append(",").toString());
                else
                    sb.append((new StringBuilder()).append(entry.getMeId()).toString());
                index++;
            }

        }*/
      //  model.addAttribute("missSeriesMap", sb);
      //  model.addAttribute("missExams", missExamService.listMissExam());
        model.addAttribute("testForm", testForm);
        model.addAttribute("missExamGroups", getGroup());
        model.addAttribute("display", "display: none");
        return "exam/template/testManagement";
    }

    @RequestMapping(value={"/new"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm(Model model)
    {
    	TestForm testForm = null;
          if(model.containsAttribute("testForm"))
        	  testForm = (TestForm)model.asMap().get("testForm");
          else
        	  testForm = new TestForm();
          
          MissExam missExam=new MissExam();
          missExam.setMeTimeLimit(120l);
          testForm.setMissExam(missExam);
          testForm.setMode("new");
          model.addAttribute("missExamGroups", getGroup());
          model.addAttribute("display", "display: none");
        return "exam/template/testManagement";
    }

    @RequestMapping(value={"/exam/{meId}/question/new"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewQuestion(@PathVariable String meId, Model model)
    {
    	TestForm testForm = null;
        if(model.containsAttribute("testForm"))
      	  testForm = (TestForm)model.asMap().get("testForm");
        else
      	  testForm = new TestForm();
        testForm.getMissExam().setMeId(Long.parseLong(meId));
        MissQuestion missQuestion= new MissQuestion();
        missQuestion.setMissExam(testForm.getMissExam());
        testForm.setMissQuestion(missQuestion);
        testForm.setModeQuestion("new");
        testForm.setMcSize("1");
      //  model.addAttribute("missExamGroups", getGroup());
        model.addAttribute("display", "display: none");
        testForm.setLang("TH");
        //return "exam/template/questionManagementSection";
        return "exam/template/questionManagementSectionTH";
    }

  /*  @RequestMapping(value={"/exam/{meId}/question/{mqId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getQuestion(@PathVariable String meId, @PathVariable String mqId, Model model)
    {
        return "exam/template/questionManagement";
    }*/

    @RequestMapping(value={"/exam/{meId}/questions"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String questionListAction(@PathVariable String meId, @ModelAttribute(value="testForm") TestForm testForm, Model model)
    {
    	
    	/* TestForm testForm = null;
         if(model.containsAttribute("testForm"))
         	testForm = (TestForm)model.asMap().get("testForm");
         else
         	testForm = new TestForm();*/
    	
         String mode = testForm.getMode();
         logger.debug("into xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx mode="+mode);
        /* if(mode != null && mode.equals("deleteItems"))
         {
         	testForm.getMissExam().setMeIds(testForm.getMqIdArray());
             missExamService.deleteMissQuestion(testForm.getMissExam(), "deleteMissExamItems");
             testForm.getPaging().setPageNo(1);
         } else*/
         if(mode != null){ 
          if(mode.equals("delete")){
             missExamService.deleteMissQuestion(testForm.getMissQuestion());//, "deleteMissExam");
             testForm.getPaging().setPageNo(1);
         }else if(mode.equals("setOrderItems")){
        	 missExamService.setOrderItems(Long.parseLong(meId));//setOrderItems
        	 testForm.getPaging().setPageNo(1);
         }
      }
    	List missQuestions=null;
    	int countNotOrdered =0;
    	if(meId!=null && !meId.equals("0")){
    		missQuestions = missExamService.listMissQuestions(Long.parseLong(meId));
    		countNotOrdered =missExamService.getQuestionOrdered(Long.parseLong(meId));
    	}
    	 testForm.setMode("edit");
    	 model.addAttribute("countNotOrdered", countNotOrdered);
        model.addAttribute("missQuestions", missQuestions);
        model.addAttribute("testForm", testForm);
        return "exam/template/questionListSection";
    }
    @RequestMapping(value={"/exam/{meId}/questions"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getQuestionList(@PathVariable String meId, Model model)
    {
    	logger.debug("into xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    	 TestForm testForm = null;
         if(model.containsAttribute("testForm"))
         	testForm = (TestForm)model.asMap().get("testForm");
         else
         	testForm = new TestForm();
         testForm.setMode("edit");
        
    	List missQuestions=null;
    	int countNotOrdered =0;
    	if(meId!=null && !meId.equals("0")){
    		missQuestions = missExamService.listMissQuestions(Long.parseLong(meId));
    		countNotOrdered =missExamService.getQuestionOrdered(Long.parseLong(meId));
    	} 
    	 model.addAttribute("countNotOrdered", countNotOrdered);
        model.addAttribute("missQuestions", missQuestions);
        model.addAttribute("testForm", testForm);
        return "exam/template/questionListSection";
    }
    @RequestMapping(value={"/exam/{meId}/question/{mqId}/{lang}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getQuestionItem(@PathVariable String meId,@PathVariable String mqId,@PathVariable String lang, Model model)
    {
    	logger.debug("into xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    	 TestForm testForm = null;
         if(model.containsAttribute("testForm"))
         	testForm = (TestForm)model.asMap().get("testForm");
         else
         	testForm = new TestForm();
         testForm.setModeQuestion("edit");
         MissQuestion missQuestion=null;
    //	if(meId!=null && !meId.equals("0")){
    		missQuestion = missExamService.findMissQuestionById(Long.parseLong(mqId));
    //	}
    		List<MissChoice> missChoices=null;
    		if(lang.equals("TH"))
    			missChoices=missQuestion.getMissChoices();
    		else
    			missChoices=missQuestion.getMissChoicesEng();
    		String mcIdArray="";
    		int i=0;
    		int size=0;
    		if (missChoices!=null && missChoices.size()>0) {
    			size=missChoices.size();
				for (MissChoice missChoice : missChoices) {
					  //for(int i = 0; i < meId_size; i++)
			               /* if(i != (size - 1))
			                	mcIdArray = (new StringBuilder(String.valueOf(mcIdArray))).append(missChoice.getMcId().intValue()).append(",").toString();
			                else
			                	mcIdArray = (new StringBuilder(String.valueOf(mcIdArray))).append(missChoice.getMcId().intValue()).toString();*/
					 if(i != (size - 1))
		                	mcIdArray = mcIdArray+missChoice.getMcNo().intValue()+",";
		                else
		                	mcIdArray = mcIdArray+missChoice.getMcNo().intValue()+"";
				i++;
				}
				
			}
    		testForm.setMcSize(size+"");
    	testForm.setMcIdArray(mcIdArray);
    	testForm.setMissQuestion(missQuestion);
    	testForm.setLang(lang);
    	model.addAttribute("display", "display: none");
      //  model.addAttribute("missQuestions", missQuestions);
    	//missExamService
        model.addAttribute("testForm", testForm);
        return "exam/template/questionManagementSection"+lang;
    } 
    @RequestMapping(value={"/action/{section}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doAction(HttpServletRequest request,@PathVariable String section, @ModelAttribute(value="testForm") TestForm testForm, BindingResult result, Model model)
    {
        String mode = testForm.getMode();
        String message = "";
        testForm.getMissExam().setSection(section);
  
        Long id = null;
        if(mode != null)
            if(mode.equals("new"))
            {
                id = missExamService.saveMissExam(testForm.getMissExam());
                testForm.getMissExam().setMeId(id);
                testForm.setMode("edit");
                message = "Save success !";
            } else
            if(mode.equals("edit"))
            {
                missExamService.updateMissExam(testForm.getMissExam());
                id = testForm.getMissExam().getMeId();
               // logger.debug("idxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx="+id);
                message = "Update success !";
            }
      
        MissExam missExam = missExamService.findMissExamById(id);
        logger.debug("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx==>"+missExam);
        testForm.setMissExam(missExam);
        model.addAttribute("message", message);
        model.addAttribute("display", "display: block");
        testForm.getMissExam().setSection(section);
        model.addAttribute("missExamGroups", getGroup());
        model.addAttribute("testForm", testForm);
        
        return "exam/template/testManagement";
    }
    @RequestMapping(value={"/action/exam/question"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doQuestionAction(HttpServletRequest request, @ModelAttribute(value="testForm") TestForm testForm, BindingResult result, Model model)
    {
        String mode = testForm.getModeQuestion();
        String message = ""; 
        logger.debug("xxxxxxxxxxxxxxxxxxxxxxx doQuestionAction mode="+mode);
        Long id = null;
        testForm.getMissQuestion().setLang(testForm.getLang());
        if(mode != null)
            if(mode.equals("new"))
            {
                id = missExamService.saveMissQuestion(testForm.getMissQuestion());
                testForm.getMissQuestion().setMqId(id);
                testForm.setModeQuestion("edit");
                message = "Save success !";
            } else
            if(mode.equals("edit"))
            {
            	
                missExamService.updateMissQuestion(testForm.getMissQuestion(),ServiceConstant.MISS_QUESTION_UPDATE);
                id = testForm.getMissQuestion().getMqId();
                message = "Update success !";
            }
      
        String mcIdArrayStr = testForm.getMcIdArray();
        String mcIdNewArrayStr = testForm.getMcIdNewArray();
        Map mcIdMap=new HashMap();
        if(mcIdArrayStr!=null && mcIdArrayStr.length()>0){
        	String[] mcIds= mcIdArrayStr.split(",");
        	for (int i = 0; i < mcIds.length; i++) {
        		mcIdMap.put(mcIds[i],mcIds[i]);
			}
        }
        logger.debug("  mcIdMap="+mcIdMap.size());
        logger.debug("mcIdNewArrayStr======>"+mcIdNewArrayStr);
        List<MissChoice> updateChoices=new ArrayList<MissChoice>();
        List<MissChoice> addChoices=new ArrayList<MissChoice>();
        if(mcIdNewArrayStr!=null && mcIdNewArrayStr.length()>0){
        	//1 A 0 B
        	//0 B 1 A 0 C
        	String[] mcNewIds= mcIdNewArrayStr.split("12345i6789");
        	  logger.debug("  mcNewIds="+mcNewIds.length);
        	for(int i=0;i<mcNewIds.length;i++){
        		 logger.debug(" mcNewIds="+mcNewIds[i]);
        		String[] keyValue= mcNewIds[i].split("9876i54321");
        		  logger.debug("  key="+keyValue[0]+",value="+keyValue[1]); 
        		/*if(mcIdMap.containsKey(keyValue[0])){
        			mcIdMap.remove(keyValue[0]);
        			MissChoice choice=new MissChoice();
        			MissQuestion missQuestion=testForm.getMissQuestion();
        			//choice.setMcId(Long.parseLong(keyValue[0]));
        			choice.setMqId(missQuestion.getMqId());
        			choice.setMcNo(Long.parseLong(keyValue[0]));
        			choice.setMcName(keyValue[1]);
        			choice.setMissQuestion(missQuestion);
        			updateChoices.add(choice);
        		}else{*/
        			MissChoice choice=new MissChoice();
        			choice.setMqId(testForm.getMissQuestion().getMqId());
        			//choice.setMcNo(Long.parseLong(keyValue[0]));
        			choice.setMcNo(Long.valueOf(i+1));
        			choice.setMcName(keyValue[1]);
        			choice.setMissQuestion(testForm.getMissQuestion());
        			addChoices.add(choice);
        		//}
        	}
        }
       
        String[] deleteChoices = new String[mcIdMap.size()];
        mcIdMap.keySet().toArray(deleteChoices);
        logger.debug(" addChoices ="+addChoices.size());
        logger.debug(" updateChoices ="+updateChoices.size());
        logger.debug(" deleteChoices ="+deleteChoices.length);
        
        MissQuestion missQuestion=new MissQuestion();
        missQuestion.setMqId(id);
       // missQuestion.setServiceName(ServiceConstant.MISS_QUESTION_CHOICES_UPDATE);
        missQuestion.setMissChoicesAdd(addChoices);
        missQuestion.setMissChoicesUpdate(updateChoices);
        missQuestion.setMcIds(deleteChoices);
        missQuestion.setLang((testForm.getLang().equals("TH"))?"1":"2");
        missExamService.updateMissQuestion(missQuestion, ServiceConstant.MISS_QUESTION_CHOICES_UPDATE);
       // missExamService.saveMissChoice(misschoice)
         missQuestion = missExamService.findMissQuestionById(id);
         List<MissChoice> missChoices=null;
        if(testForm.getLang().equals("TH"))
        	missChoices=missQuestion.getMissChoices();
        else
        	missChoices=missQuestion.getMissChoicesEng();
		String mcIdArray="";
		int i=0;
		int size=0;
		if (missChoices!=null && missChoices.size()>0) {
			  size=missChoices.size();
			for (MissChoice missChoice : missChoices) {
				  //for(int i = 0; i < meId_size; i++)
		              /*  if(i != (size - 1))
		                	mcIdArray = (new StringBuilder(String.valueOf(mcIdArray))).append(missChoice.getMcId().intValue()).append(",").toString();
		                else
		                	mcIdArray = (new StringBuilder(String.valueOf(mcIdArray))).append(missChoice.getMcId().intValue()).toString();*/
				 if(i != (size - 1))
	                	mcIdArray = mcIdArray+missChoice.getMcNo().intValue()+",";
	                else
	                	mcIdArray = mcIdArray+missChoice.getMcNo().intValue()+"";
				 
				 /*  if(i != (size - 1))
	                	mcIdArray = (new StringBuilder(String.valueOf(mcIdArray))).append(missChoice.getMcId().intValue()).append(",").toString();
	                else
	                	mcIdArray = (new StringBuilder(String.valueOf(mcIdArray))).append(missChoice.getMcId().intValue()).toString();*/
			i++;
			}
		}
		testForm.setMcSize(size+"");
		testForm.setMcIdArray(mcIdArray);
        testForm.setMissQuestion(missQuestion);
        model.addAttribute("message", message);
        model.addAttribute("display", "display: block");
        model.addAttribute("testForm", testForm);
        
        return "exam/template/questionManagementSection"+testForm.getLang();
    }
    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
