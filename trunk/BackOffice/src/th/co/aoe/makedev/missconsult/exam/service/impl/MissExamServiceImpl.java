// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:14:40 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MissExamServiceImpl.java

package th.co.aoe.makedev.missconsult.exam.service.impl;

import java.util.List;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.ConsultantReport;
import th.co.aoe.makedev.missconsult.xstream.CustomerReport;
import th.co.aoe.makedev.missconsult.xstream.EPTNormReport;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissAccountGroup;
import th.co.aoe.makedev.missconsult.xstream.MissAccountMapping;
import th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap;
import th.co.aoe.makedev.missconsult.xstream.MissAttach;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissCareerMaster;
import th.co.aoe.makedev.missconsult.xstream.MissChoice;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster;
import th.co.aoe.makedev.missconsult.xstream.MissDoc;
import th.co.aoe.makedev.missconsult.xstream.MissEvaluationTemplate;
import th.co.aoe.makedev.missconsult.xstream.MissExam;
import th.co.aoe.makedev.missconsult.xstream.MissExamGroup;
import th.co.aoe.makedev.missconsult.xstream.MissExamType;
import th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster;
import th.co.aoe.makedev.missconsult.xstream.MissManual;
import th.co.aoe.makedev.missconsult.xstream.MissPositionMaster;
import th.co.aoe.makedev.missconsult.xstream.MissQuestion;
import th.co.aoe.makedev.missconsult.xstream.MissReactiveLog;
import th.co.aoe.makedev.missconsult.xstream.MissReportAttach;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesMap;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.MissSeryOrder;
import th.co.aoe.makedev.missconsult.xstream.MissSeryProblem;
import th.co.aoe.makedev.missconsult.xstream.MissSeryUse;
import th.co.aoe.makedev.missconsult.xstream.MissSurveySend;
import th.co.aoe.makedev.missconsult.xstream.MissSystemUse;
import th.co.aoe.makedev.missconsult.xstream.MissTemplate;
import th.co.aoe.makedev.missconsult.xstream.MissTest;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;
import th.co.aoe.makedev.missconsult.xstream.MissTheme;
import th.co.aoe.makedev.missconsult.xstream.MissTodo;
import th.co.aoe.makedev.missconsult.xstream.ProductReport;
import th.co.aoe.makedev.missconsult.xstream.RoleContact;
import th.co.aoe.makedev.missconsult.xstream.RoleMapping;
import th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping;
import th.co.aoe.makedev.missconsult.xstream.RoleType;
import th.co.aoe.makedev.missconsult.xstream.ServiceReport;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

// Referenced classes of package th.co.aoe.makedev.missconsult.exam.service.impl:
//            PostCommon

public class MissExamServiceImpl extends PostCommon
    implements MissExamService
{

    public MissExamServiceImpl()
    {
    }

    public Long saveMissExamGroup(MissExamGroup missExamGroup)
    {
        missExamGroup.setServiceName(ServiceConstant.MISS_EXAM_GROUP_SAVE);//"saveMissExamGroup");
        VResultMessage resultMessage = postMessage(missExamGroup, missExamGroup.getClass().getName(), "missExamGroup", true);
        missExamGroup = (MissExamGroup)resultMessage.getResultListObj().get(0);
        return missExamGroup.getMegId();
    }

    public int updateMissExamGroup(MissExamGroup missExamGroup)
    {
        missExamGroup.setServiceName(ServiceConstant.MISS_EXAM_GROUP_UPDATE);//"updateMissExamGroup");
        VResultMessage resultMessage = postMessage(missExamGroup, missExamGroup.getClass().getName(), "missExamGroup", true);
        missExamGroup = (MissExamGroup)resultMessage.getResultListObj().get(0);
        return missExamGroup.getUpdateRecord().intValue();
    }

    public int deleteMissExamGroup(MissExamGroup missExamGroup)
    {
        missExamGroup.setServiceName(ServiceConstant.MISS_EXAM_GROUP_DELETE);//"deleteMissExamGroup");
        VResultMessage resultMessage = postMessage(missExamGroup, missExamGroup.getClass().getName(), "missExamGroup", true);
        missExamGroup = (MissExamGroup)resultMessage.getResultListObj().get(0);
        return missExamGroup.getUpdateRecord().intValue();
    }

    public MissExamGroup findMissExamGroupById(Long megId)
    {
        MissExamGroup missExamGroup = new MissExamGroup();
        missExamGroup.setMegId(megId);
        missExamGroup.setServiceName(ServiceConstant.MISS_EXAM_GROUP_FIND_BY_ID);//"findMissExamGroupById");
        VResultMessage resultMessage = postMessage(missExamGroup, missExamGroup.getClass().getName(), "missExamGroup", true);
        return (MissExamGroup)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissExamGroup(MissExamGroup missExamGroup)
    {
        missExamGroup.setServiceName(ServiceConstant.MISS_EXAM_GROUP_SEARCH);//"searchMissExamGroup");
        return postMessage(missExamGroup, missExamGroup.getClass().getName(), "missExamGroup", true);
    }

    public Long saveMissAccount(MissAccount missAccount)
    {
        missAccount.setServiceName(ServiceConstant.MISS_ACCOUNT_SAVE);//"saveMissAccount");
        VResultMessage resultMessage = postMessage(missAccount, missAccount.getClass().getName(), "missAccount", true);
        missAccount = (MissAccount)resultMessage.getResultListObj().get(0);
        return missAccount.getMaId();
    }

    public int updateMissAccount(MissAccount missAccount)
    {
        missAccount.setServiceName(ServiceConstant.MISS_ACCOUNT_UPDATE);//"updateMissAccount");
        VResultMessage resultMessage = postMessage(missAccount, missAccount.getClass().getName(), "missAccount", true);
        missAccount = (MissAccount)resultMessage.getResultListObj().get(0);
        return missAccount.getUpdateRecord().intValue();
    }

    public int deleteMissAccount(MissAccount missAccount, String service)
    {
        missAccount.setServiceName(service);
        VResultMessage resultMessage = postMessage(missAccount, missAccount.getClass().getName(), "missAccount", true);
        missAccount = (MissAccount)resultMessage.getResultListObj().get(0);
        return missAccount.getUpdateRecord().intValue();
    }

    public MissAccount findMissAccountById(Long megId)
    {
        MissAccount missAccount = new MissAccount();
        missAccount.setMaId(megId);
        missAccount.setServiceName(ServiceConstant.MISS_ACCOUNT_FIND_BY_ID);//"findMissAccountById");
        VResultMessage resultMessage = postMessage(missAccount, missAccount.getClass().getName(), "missAccount", true);
        return (MissAccount)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissAccount(MissAccount missAccount)
    {
        missAccount.setServiceName(ServiceConstant.MISS_ACCOUNT_SEARCH);//"searchMissAccount");
        return postMessage(missAccount, missAccount.getClass().getName(), "missAccount", true);
    }

    public Long saveMissAccountSeriesMap(MissAccountSeriesMap missAccountSeriesMap)
    {
        missAccountSeriesMap.setServiceName(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_SAVE);
        VResultMessage resultMessage = postMessage(missAccountSeriesMap, missAccountSeriesMap.getClass().getName(), "missAccountSeriesMap", true);
        missAccountSeriesMap = (MissAccountSeriesMap)resultMessage.getResultListObj().get(0);
        return Long.valueOf(missAccountSeriesMap.getUpdateRecord());
    }

    public int updateMissAccountSeriesMap(MissAccountSeriesMap missAccountSeriesMap)
    {
        missAccountSeriesMap.setServiceName(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_UPDATE);//"updateMissAccountSeriesMap");
        VResultMessage resultMessage = postMessage(missAccountSeriesMap, missAccountSeriesMap.getClass().getName(), "missAccountSeriesMap", true);
        missAccountSeriesMap = (MissAccountSeriesMap)resultMessage.getResultListObj().get(0);
        return missAccountSeriesMap.getUpdateRecord().intValue();
    }

    public int deleteMissAccountSeriesMap(MissAccountSeriesMap missAccountSeriesMap)
    {
        missAccountSeriesMap.setServiceName(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_DELETE);//"deleteMissAccountSeriesMap");
        VResultMessage resultMessage = postMessage(missAccountSeriesMap, missAccountSeriesMap.getClass().getName(), "missAccountSeriesMap", true);
        missAccountSeriesMap = (MissAccountSeriesMap)resultMessage.getResultListObj().get(0);
        return missAccountSeriesMap.getUpdateRecord().intValue();
    }

    public MissAccountSeriesMap findMissAccountSeriesMapById(Long megId)
    {
        return null;
    }

    public VResultMessage searchMissAccountSeriesMap(MissAccountSeriesMap missAccountSeriesMap)
    {
        missAccountSeriesMap.setServiceName(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_SEARCH);//"searchMissAccountSeriesMap");
        return postMessage(missAccountSeriesMap, missAccountSeriesMap.getClass().getName(), "missAccountSeriesMap", true);
    }

    public MissCandidate saveMissCandidate(MissCandidate missCandidate)
    {
        missCandidate.setServiceName(ServiceConstant.MISS_CANDIDATE_SAVE);//"saveMissCandidate");
        VResultMessage resultMessage = postMessage(missCandidate, missCandidate.getClass().getName(), "missCandidate", true);
        missCandidate = (MissCandidate)resultMessage.getResultListObj().get(0);
        //return missCandidate.getMcaId(); 
        return missCandidate;
    }

    public int updateMissCandidate(MissCandidate missCandidate)
    {
        missCandidate.setServiceName(ServiceConstant.MISS_CANDIDATE_UPDATE);//"updateMissCandidate");
        VResultMessage resultMessage = postMessage(missCandidate, missCandidate.getClass().getName(), "missCandidate", true);
        missCandidate = (MissCandidate)resultMessage.getResultListObj().get(0);
        return missCandidate.getUpdateRecord().intValue();
    }

    public int deleteMissCandidate(MissCandidate missCandidate, String service)
    {
        missCandidate.setServiceName(service);
        VResultMessage resultMessage = postMessage(missCandidate, missCandidate.getClass().getName(), "missCandidate", true);
        missCandidate = (MissCandidate)resultMessage.getResultListObj().get(0);
        return missCandidate.getUpdateRecord().intValue();
    }

    public MissCandidate findMissCandidateById(Long megId)
    {
        MissCandidate missCandidate = new MissCandidate();
        missCandidate.setMcaId(megId);
        missCandidate.setServiceName(ServiceConstant.MISS_CANDIDATE_FIND_BY_ID);
        VResultMessage resultMessage = postMessage(missCandidate, missCandidate.getClass().getName(), "missCandidate", true);
        return (MissCandidate)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissCandidate(MissCandidate missCandidate)
    {
        missCandidate.setServiceName(ServiceConstant.MISS_CANDIDATE_SEARCH);//"searchMissCandidate");
        return postMessage(missCandidate, missCandidate.getClass().getName(), "missCandidate", true);
    }
    public VResultMessage exportMissCandidate(MissCandidate missCandidate)
    {
        missCandidate.setServiceName(ServiceConstant.MISS_CANDIDATE_EXPORT);
        return postMessage(missCandidate, missCandidate.getClass().getName(), "missCandidate", true);
    }

    public Long saveMissChoice(MissChoice missChoice)
    {
        missChoice.setServiceName(ServiceConstant.MISS_CHOICE_SAVE);//"saveMissChoice");
        VResultMessage resultMessage = postMessage(missChoice, missChoice.getClass().getName(), "missChoice", true);
        missChoice = (MissChoice)resultMessage.getResultListObj().get(0);
        return 1l;
    //    return missChoice.getMcId();
    }

    public int updateMissChoice(MissChoice missChoice)
    {
        missChoice.setServiceName(ServiceConstant.MISS_CHOICE_UPDATE);//"updateMissChoice");
        VResultMessage resultMessage = postMessage(missChoice, missChoice.getClass().getName(), "missChoice", true);
        missChoice = (MissChoice)resultMessage.getResultListObj().get(0);
        return missChoice.getUpdateRecord().intValue();
    }

    public int deleteMissChoice(MissChoice missChoice)
    {
        missChoice.setServiceName(ServiceConstant.MISS_CHOICE_DELETE);//"deleteMissChoice");
        VResultMessage resultMessage = postMessage(missChoice, missChoice.getClass().getName(), "missChoice", true);
        missChoice = (MissChoice)resultMessage.getResultListObj().get(0);
        return missChoice.getUpdateRecord().intValue();
    }

    public MissChoice findMissChoiceById(Long megId)
    {
        MissChoice missChoice = new MissChoice();
       // missChoice.setMcId(megId);
        missChoice.setServiceName(ServiceConstant.MISS_CHOICE_FIND_BY_ID);//"findMissChoiceById");
        VResultMessage resultMessage = postMessage(missChoice, missChoice.getClass().getName(), "missChoice", true);
        return (MissChoice)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissChoice(MissChoice missChoice)
    {
        missChoice.setServiceName(ServiceConstant.MISS_CHOICE_SEARCH);//"searchMissChoice");
        return postMessage(missChoice, missChoice.getClass().getName(), "missChoice", true);
    }

    public Long saveMissEvaluationTemplate(MissEvaluationTemplate missEvaluationTemplate)
    {
        missEvaluationTemplate.setServiceName(ServiceConstant.MISS_EVALUATION_TEMPLATE_SAVE);//"saveMissEvaluationTemplate");
        VResultMessage resultMessage = postMessage(missEvaluationTemplate, missEvaluationTemplate.getClass().getName(), "missEvaluationTemplate", true);
        missEvaluationTemplate = (MissEvaluationTemplate)resultMessage.getResultListObj().get(0);
       // return missEvaluationTemplate.getMetId();
        return 0l;
    }

    public int updateMissEvaluationTemplate(MissEvaluationTemplate missEvaluationTemplate)
    {
        missEvaluationTemplate.setServiceName(ServiceConstant.MISS_EVALUATION_TEMPLATE_UPDATE);//"updateMissEvaluationTemplate");
        VResultMessage resultMessage = postMessage(missEvaluationTemplate, missEvaluationTemplate.getClass().getName(), "missEvaluationTemplate", true);
        missEvaluationTemplate = (MissEvaluationTemplate)resultMessage.getResultListObj().get(0);
        return missEvaluationTemplate.getUpdateRecord().intValue();
    }

    public int deleteMissEvaluationTemplate(MissEvaluationTemplate missEvaluationTemplate)
    {
        missEvaluationTemplate.setServiceName(ServiceConstant.MISS_EVALUATION_TEMPLATE_DELETE);//"deleteMissEvaluationTemplate");
        VResultMessage resultMessage = postMessage(missEvaluationTemplate, missEvaluationTemplate.getClass().getName(), "missEvaluationTemplate", true);
        missEvaluationTemplate = (MissEvaluationTemplate)resultMessage.getResultListObj().get(0);
        return missEvaluationTemplate.getUpdateRecord().intValue();
    }

    public MissEvaluationTemplate findMissEvaluationTemplateById(Long megId)
    {
        MissEvaluationTemplate missEvaluationTemplate = new MissEvaluationTemplate();
       // missEvaluationTemplate.setMetId(megId);
        missEvaluationTemplate.setServiceName(ServiceConstant.MISS_EVALUATION_TEMPLATE_FIND_BY_ID);//"findMissEvaluationTemplateById");
        VResultMessage resultMessage = postMessage(missEvaluationTemplate, missEvaluationTemplate.getClass().getName(), "missEvaluationTemplate", true);
        return (MissEvaluationTemplate)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissEvaluationTemplate(MissEvaluationTemplate missEvaluationTemplate)
    {
        missEvaluationTemplate.setServiceName(ServiceConstant.MISS_EVALUATION_TEMPLATE_SEARCH);//"searchMissEvaluationTemplate");
        return postMessage(missEvaluationTemplate, missEvaluationTemplate.getClass().getName(), "missEvaluationTemplate", true);
    }

    public Long saveMissExam(MissExam missExam)
    {
        missExam.setServiceName(ServiceConstant.MISS_EXAM_SAVE);//"saveMissExam");
        VResultMessage resultMessage = postMessage(missExam, missExam.getClass().getName(), "missExam", true);
        missExam = (MissExam)resultMessage.getResultListObj().get(0);
        return missExam.getMeId();
    }

    public int updateMissExam(MissExam missExam)
    {
        missExam.setServiceName(ServiceConstant.MISS_EXAM_UPDATE);//"updateMissExam");
        VResultMessage resultMessage = postMessage(missExam, missExam.getClass().getName(), "missExam", true);
        missExam = (MissExam)resultMessage.getResultListObj().get(0);
        return missExam.getUpdateRecord().intValue();
    }
	@Override
	public int copyMissExam(MissExam missExam) {
		// TODO Auto-generated method stub
			missExam.setServiceName(ServiceConstant.MISS_EXAM_COPY);
	        VResultMessage resultMessage = postMessage(missExam, missExam.getClass().getName(), "missExam", true);
	        missExam = (MissExam)resultMessage.getResultListObj().get(0);
	        return missExam.getUpdateRecord().intValue();
	} 
	@Override
	public int createEmptyMissExam(MissExam missExam) {
		// TODO Auto-generated method stub
			missExam.setServiceName(ServiceConstant.MISS_EXAM_CREATE_EMPTY);
	        VResultMessage resultMessage = postMessage(missExam, missExam.getClass().getName(), "missExam", true);
	        missExam = (MissExam)resultMessage.getResultListObj().get(0);
	        return missExam.getUpdateRecord().intValue();
	}
    public int deleteMissExam(MissExam missExam, String service)
    
    {
        missExam.setServiceName(service);
        VResultMessage resultMessage = postMessage(missExam, missExam.getClass().getName(), "missExam", true);
        missExam = (MissExam)resultMessage.getResultListObj().get(0);
        return missExam.getUpdateRecord().intValue();
    }

    public MissExam findMissExamById(Long megId)
    {
        MissExam missExam = new MissExam();
        missExam.setMeId(megId);
        missExam.setServiceName(ServiceConstant.MISS_EXAM_FIND_BY_ID);//"findMissExamById");
        VResultMessage resultMessage = postMessage(missExam, missExam.getClass().getName(), "missExam", true);
        return (MissExam)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissExam(MissExam missExam)
    {
        missExam.setServiceName(ServiceConstant.MISS_EXAM_SEARCH);//"searchMissExam");
        return postMessage(missExam, missExam.getClass().getName(), "missExam", true);
    }

    @SuppressWarnings("rawtypes")
	public List listMissExam()
    {
        MissExam missExam = new MissExam();
        missExam.setServiceName(ServiceConstant.MISS_EXAM_LIST);//"listMissExam");
        VResultMessage resultMessage = postMessage(missExam, missExam.getClass().getName(), "missExam", true);
        return resultMessage.getResultListObj();
    }
    @SuppressWarnings("rawtypes")
    public List listMissSery()
    {
        MissSery missSery = new MissSery();
        missSery.setServiceName(ServiceConstant.MISS_SERIES_LIST);//"listMissSeries");
        VResultMessage resultMessage = postMessage(missSery, missSery.getClass().getName(), "missSery", true);
        return resultMessage.getResultListObj();
    }

    public Long saveMissExamType(MissExamType missExamType)
    {
        missExamType.setServiceName(ServiceConstant.MISS_EXAM_TYPE_SAVE);//"saveMissExamType");
        VResultMessage resultMessage = postMessage(missExamType, missExamType.getClass().getName(), "missExamType", true);
        missExamType = (MissExamType)resultMessage.getResultListObj().get(0);
        return missExamType.getMetId();
    }

    public int updateMissExamType(MissExamType missExamType)
    {
        missExamType.setServiceName(ServiceConstant.MISS_EXAM_TYPE_UPDATE);//"updateMissExamType");
        VResultMessage resultMessage = postMessage(missExamType, missExamType.getClass().getName(), "missExamType", true);
      
        missExamType = (MissExamType)resultMessage.getResultListObj().get(0);
        return missExamType.getUpdateRecord().intValue();
    }

    public int deleteMissExamType(MissExamType missExamType)
    {
        missExamType.setServiceName(ServiceConstant.MISS_EXAM_TYPE_DELETE);//"deleteMissExamType");
        VResultMessage resultMessage = postMessage(missExamType, missExamType.getClass().getName(), "missExamType", true);
        missExamType = (MissExamType)resultMessage.getResultListObj().get(0);
        return missExamType.getUpdateRecord().intValue();
    }

    public MissExamType findMissExamTypeById(Long megId)
    {
        MissExamType missExamType = new MissExamType();
        missExamType.setMetId(megId);
        missExamType.setServiceName(ServiceConstant.MISS_EXAM_TYPE_FIND_BY_ID);//"findMissExamTypeById");
        VResultMessage resultMessage = postMessage(missExamType, missExamType.getClass().getName(), "missExamType", true);
        return (MissExamType)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissExamType(MissExamType missExamType)
    {
        missExamType.setServiceName(ServiceConstant.MISS_EXAM_TYPE_SEARCH);//"searchMissExamType");
        return postMessage(missExamType, missExamType.getClass().getName(), "missExamType", true);
    }

    public Long saveMissQuestion(MissQuestion missQuestion)
    {
        //missQuestion.setServiceName("saveMissQuestion");
    	missQuestion.setServiceName(ServiceConstant.MISS_QUESTION_SAVE);//);
        VResultMessage resultMessage = postMessage(missQuestion, missQuestion.getClass().getName(), "missQuestion", true);
        missQuestion = (MissQuestion)resultMessage.getResultListObj().get(0);
        return missQuestion.getMqId();
    }

    public int updateMissQuestion(MissQuestion missQuestion,String service)
    {
        //missQuestion.setServiceName("updateMissQuestion");
    	missQuestion.setServiceName(service);
        VResultMessage resultMessage = postMessage(missQuestion, missQuestion.getClass().getName(), "missQuestion", true);
        missQuestion = (MissQuestion)resultMessage.getResultListObj().get(0);
        return missQuestion.getUpdateRecord().intValue();
    }

    public int deleteMissQuestion(MissQuestion missQuestion)
    {
        missQuestion.setServiceName(ServiceConstant.MISS_QUESTION_DELETE);//"deleteMissQuestion");
        VResultMessage resultMessage = postMessage(missQuestion, missQuestion.getClass().getName(), "missQuestion", true);
        missQuestion = (MissQuestion)resultMessage.getResultListObj().get(0);
        return missQuestion.getUpdateRecord().intValue();
    }

    public MissQuestion findMissQuestionById(Long megId)
    {
        MissQuestion missQuestion = new MissQuestion();
        missQuestion.setMqId(megId);
        missQuestion.setServiceName(ServiceConstant.MISS_QUESTION_FIND_BY_ID);//"findMissQuestionById");
        VResultMessage resultMessage = postMessage(missQuestion, missQuestion.getClass().getName(), "missQuestion", true);
        return (MissQuestion)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissQuestion(MissQuestion missQuestion)
    {
        missQuestion.setServiceName(ServiceConstant.MISS_QUESTION_SEARCH);//"searchMissQuestion");
        return postMessage(missQuestion, missQuestion.getClass().getName(), "missQuestion", true);
    }
    @SuppressWarnings("rawtypes")
    public   List listMissQuestions(Long meId){
    	
    	 MissExam missExam = new MissExam();
    	 missExam.setMeId(meId);
    	 
    	 MissQuestion missQuestion =new MissQuestion();
    	 missQuestion.setServiceName(ServiceConstant.MISS_QUESTION_LIST);
         missQuestion.setMissExam(missExam);
         VResultMessage resultMessage = postMessage(missQuestion, missQuestion.getClass().getName(), "missQuestion", true);
         return resultMessage.getResultListObj();
    }

    public int getQuestionOrdered(Long meId)
    {
    	 MissExam missExam = new MissExam();
    	 missExam.setMeId(meId);
    	 
    	 MissQuestion missQuestion =new MissQuestion();
    	 missQuestion.setServiceName(ServiceConstant.MISS_QUESTION_GET_ORDERED);
         missQuestion.setMissExam(missExam);
        VResultMessage resultMessage = postMessage(missQuestion, missQuestion.getClass().getName(), "missQuestion", true);
        missQuestion = (MissQuestion)resultMessage.getResultListObj().get(0);
        return missQuestion.getUpdateRecord().intValue();
    } 
    public int setOrderItems(Long meId,String[] mqNo_array,String mqId_array[])
    {
   	 MissExam missExam = new MissExam();
   	 missExam.setMeId(meId);
   	 missExam.setMqNos(mqNo_array);
   	 missExam.setMqIds(mqId_array);
   	 MissQuestion missQuestion =new MissQuestion();
   	 missQuestion.setServiceName(ServiceConstant.MISS_QUESTION_SET_ORDERED_ITEMS);
        missQuestion.setMissExam(missExam);
       VResultMessage resultMessage = postMessage(missQuestion, missQuestion.getClass().getName(), "missQuestion", true);
       missQuestion = (MissQuestion)resultMessage.getResultListObj().get(0);
       return missQuestion.getUpdateRecord().intValue();
   }
    
    public Long saveMissSeriesMap(MissSeriesMap missSeriesMap)
    {
        return null;
    }

    public int updateMissSeriesMap(MissSeriesMap missSeriesMap)
    {
        missSeriesMap.setServiceName(ServiceConstant.MISS_SERIES_MAP_UPDATE);//"updateMissSeriesMap");
        VResultMessage resultMessage = postMessage(missSeriesMap, missSeriesMap.getClass().getName(), "missSeriesMap", true);
        missSeriesMap = (MissSeriesMap)resultMessage.getResultListObj().get(0);
        return missSeriesMap.getUpdateRecord().intValue();
    }

    public int deleteMissSeriesMap(MissSeriesMap missSeriesMap)
    {
        missSeriesMap.setServiceName(ServiceConstant.MISS_SERIES_MAP_DELETE);//"deleteMissSeriesMap");
        VResultMessage resultMessage = postMessage(missSeriesMap, missSeriesMap.getClass().getName(), "missSeriesMap", true);
        missSeriesMap = (MissSeriesMap)resultMessage.getResultListObj().get(0);
        return missSeriesMap.getUpdateRecord().intValue();
    }

    public MissSeriesMap findMissSeriesMapById(Long megId)
    {
        MissSeriesMap missSeriesMap = new MissSeriesMap();
        missSeriesMap.setMsId(megId);
        missSeriesMap.setServiceName(ServiceConstant.MISS_SERIES_MAP_FIND_BY_ID);//"findMissSeriesMapById");
        VResultMessage resultMessage = postMessage(missSeriesMap, missSeriesMap.getClass().getName(), "missSeriesMap", true);
        return (MissSeriesMap)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissSeriesMap(MissSeriesMap missSeriesMap)
    {
        missSeriesMap.setServiceName(ServiceConstant.MISS_SERIES_MAP_SEARCH);//"searchMissSeriesMap");
        return postMessage(missSeriesMap, missSeriesMap.getClass().getName(), "missSeriesMap", true);
    }

    public Long saveMissSery(MissSery missSery)
    {
        missSery.setServiceName(ServiceConstant.MISS_SERIES_SAVE);//"saveMissSery");
        VResultMessage resultMessage = postMessage(missSery, missSery.getClass().getName(), "missSery", true);
        missSery = (MissSery)resultMessage.getResultListObj().get(0);
        return Long.valueOf(missSery.getUpdateRecord());
        //return missSery.getMsId();
    }

    public int updateMissSery(MissSery missSery)
    {
        missSery.setServiceName(ServiceConstant.MISS_SERIES_UPDATE);//"updateMissSery");
        VResultMessage resultMessage = postMessage(missSery, missSery.getClass().getName(), "missSery", true);
        missSery = (MissSery)resultMessage.getResultListObj().get(0);
        return missSery.getUpdateRecord().intValue();
    }

    public int deleteMissSery(MissSery missSery, String service)
    {
        missSery.setServiceName(service);
        VResultMessage resultMessage = postMessage(missSery, missSery.getClass().getName(), "missSery", true);
        missSery = (MissSery)resultMessage.getResultListObj().get(0);
        return missSery.getUpdateRecord().intValue();
    }

    public MissSery findMissSeryById(Long megId)
    {
        MissSery missSery = new MissSery();
        missSery.setMsId(megId);
        missSery.setServiceName(ServiceConstant.MISS_SERIES_FIND_BY_ID);//"findMissSeryById");
        VResultMessage resultMessage = postMessage(missSery, missSery.getClass().getName(), "missSery", true);
        return (MissSery)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissSery(MissSery missSery)
    {
        missSery.setServiceName(ServiceConstant.MISS_SERIES_SEARCH);//"searchMissSery");
        return postMessage(missSery, missSery.getClass().getName(), "missSery", true);
    }

    public Long saveMissSurveySend(MissSurveySend missSurveySend)
    {
        missSurveySend.setServiceName(ServiceConstant.MISS_SURVEY_SEND_SAVE);//"saveMissSurveySend");
        VResultMessage resultMessage = postMessage(missSurveySend, missSurveySend.getClass().getName(), "missSurveySend", true);
        missSurveySend = (MissSurveySend)resultMessage.getResultListObj().get(0);
        return missSurveySend.getMssId();
    }

    public int updateMissSurveySend(MissSurveySend missSurveySend)
    {
        missSurveySend.setServiceName(ServiceConstant.MISS_SURVEY_SEND_UPDATE);//"updateMissSurveySend");
        VResultMessage resultMessage = postMessage(missSurveySend, missSurveySend.getClass().getName(), "missSurveySend", true);
        missSurveySend = (MissSurveySend)resultMessage.getResultListObj().get(0);
        return missSurveySend.getUpdateRecord().intValue();
    }

    public int deleteMissSurveySend(MissSurveySend missSurveySend)
    {
        missSurveySend.setServiceName(ServiceConstant.MISS_SURVEY_SEND_DELETE);//"deleteMissSurveySend");
        VResultMessage resultMessage = postMessage(missSurveySend, missSurveySend.getClass().getName(), "missSurveySend", true);
        missSurveySend = (MissSurveySend)resultMessage.getResultListObj().get(0);
        return missSurveySend.getUpdateRecord().intValue();
    }

    public MissSurveySend findMissSurveySendById(Long megId)
    {
        MissSurveySend missSurveySend = new MissSurveySend();
        missSurveySend.setMssId(megId);
        missSurveySend.setServiceName(ServiceConstant.MISS_SURVEY_SEND_FIND_BY_ID);//"findMissSurveySendById");
        VResultMessage resultMessage = postMessage(missSurveySend, missSurveySend.getClass().getName(), "missSurveySend", true);
        return (MissSurveySend)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissSurveySend(MissSurveySend missSurveySend)
    {
        missSurveySend.setServiceName(ServiceConstant.MISS_SURVEY_SEND_SEARCH);//"searchMissSurveySend");
        return postMessage(missSurveySend, missSurveySend.getClass().getName(), "missSurveySend", true);
    }

    public Long saveMissTemplate(MissTemplate missTemplate)
    {
        missTemplate.setServiceName(ServiceConstant.MISS_TEMPLATE_SAVE);//"saveMissTemplate");
        VResultMessage resultMessage = postMessage(missTemplate, missTemplate.getClass().getName(), "missTemplate", true);
        missTemplate = (MissTemplate)resultMessage.getResultListObj().get(0);
        return missTemplate.getMtId();
    }

    public int updateMissTemplate(MissTemplate missTemplate)
    {
        missTemplate.setServiceName(ServiceConstant.MISS_TEMPLATE_UPDATE);//"updateMissTemplate");
        VResultMessage resultMessage = postMessage(missTemplate, missTemplate.getClass().getName(), "missTemplate", true);
        missTemplate = (MissTemplate)resultMessage.getResultListObj().get(0);
        return missTemplate.getUpdateRecord().intValue();
    }

    public int deleteMissTemplate(MissTemplate missTemplate)
    {
        missTemplate.setServiceName(ServiceConstant.MISS_TEMPLATE_DELETE);//"deleteMissTemplate");
        VResultMessage resultMessage = postMessage(missTemplate, missTemplate.getClass().getName(), "missTemplate", true);
        missTemplate = (MissTemplate)resultMessage.getResultListObj().get(0);
        return missTemplate.getUpdateRecord().intValue();
    }

    public MissTemplate findMissTemplateById(Long megId)
    {
        MissTemplate missTemplate = new MissTemplate();
        missTemplate.setMtId(megId);
        missTemplate.setServiceName(ServiceConstant.MISS_TEMPLATE_FIND_BY_ID);//"findMissTemplateById");
        VResultMessage resultMessage = postMessage(missTemplate, missTemplate.getClass().getName(), "missTemplate", true);
        return (MissTemplate)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissTemplate(MissTemplate missTemplate)
    {
        missTemplate.setServiceName(ServiceConstant.MISS_TEMPLATE_SEARCH);//"searchMissTemplate");
        return postMessage(missTemplate, missTemplate.getClass().getName(), "missTemplate", true);
    }

    public Long saveMissTest(MissTest missTest)
    {
        missTest.setServiceName(ServiceConstant.MISS_TEST_SAVE);//"saveMissTest");
        VResultMessage resultMessage = postMessage(missTest, missTest.getClass().getName(), "missTest", true);
        missTest = (MissTest)resultMessage.getResultListObj().get(0);
        return 1l;
        //  return missTest.getMtestId();
    }

    public int updateMissTest(MissTest missTest)
    {
        missTest.setServiceName(ServiceConstant.MISS_TEST_UPDATE);//"updateMissTest");
        VResultMessage resultMessage = postMessage(missTest, missTest.getClass().getName(), "missTest", true);
        missTest = (MissTest)resultMessage.getResultListObj().get(0);
        return missTest.getUpdateRecord().intValue();
    }

    public int deleteMissTest(MissTest missTest)
    {
        missTest.setServiceName(ServiceConstant.MISS_TEST_DELETE);//"deleteMissTest");
        VResultMessage resultMessage = postMessage(missTest, missTest.getClass().getName(), "missTest", true);
        missTest = (MissTest)resultMessage.getResultListObj().get(0);
        return missTest.getUpdateRecord().intValue();
    }

    public MissTest findMissTestById(Long megId)
    {
        MissTest missTest = new MissTest();
       // missTest.setMtestId(megId);
        missTest.setServiceName(ServiceConstant.MISS_TEST_FIND_BY_ID);//"findMissTestById");
        VResultMessage resultMessage = postMessage(missTest, missTest.getClass().getName(), "missTest", true);
        return (MissTest)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissTest(MissTest missTest)
    {
        missTest.setServiceName(ServiceConstant.MISS_TEST_SEARCH);//"searchMissTest");
        return postMessage(missTest, missTest.getClass().getName(), "missTest", true);
    }

    public Long saveMissTestResult(MissTestResult missTestResult)
    {
        missTestResult.setServiceName(ServiceConstant.MISS_TEST_RESULT_SAVE);//"saveMissTestResult");
        VResultMessage resultMessage = postMessage(missTestResult, missTestResult.getClass().getName(), "missTestResult", true);
        missTestResult = (MissTestResult)resultMessage.getResultListObj().get(0);
        return missTestResult.getMtrId();
    }

    public int updateMissTestResult(MissTestResult missTestResult)
    {
        missTestResult.setServiceName(ServiceConstant.MISS_TEST_RESULT_UPDATE);//"updateMissTestResult");
        VResultMessage resultMessage = postMessage(missTestResult, missTestResult.getClass().getName(), "missTestResult", true);
        missTestResult = (MissTestResult)resultMessage.getResultListObj().get(0);
        return missTestResult.getUpdateRecord().intValue();
    }

    public int deleteMissTestResult(MissTestResult missTestResult)
    {
        missTestResult.setServiceName(ServiceConstant.MISS_TEST_RESULT_DELETE);//"deleteMissTestResult");
        VResultMessage resultMessage = postMessage(missTestResult, missTestResult.getClass().getName(), "missTestResult", true);
        missTestResult = (MissTestResult)resultMessage.getResultListObj().get(0);
        return missTestResult.getUpdateRecord().intValue();
    }

    public MissTestResult findMissTestResultById(Long megId)
    {
        MissTestResult missTestResult = new MissTestResult();
        missTestResult.setMtrId(megId);
        missTestResult.setServiceName(ServiceConstant.MISS_TEST_RESULT_FIND_BY_ID);//"findMissTestResultById");
        VResultMessage resultMessage = postMessage(missTestResult, missTestResult.getClass().getName(), "missTestResult", true);
        return (MissTestResult)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissTestResult(MissTestResult missTestResult)
    {
        missTestResult.setServiceName(ServiceConstant.MISS_TEST_RESULT_SEARCH);
        return postMessage(missTestResult, missTestResult.getClass().getName(), "missTestResult", true);
    }

    public Long saveMissTodo(MissTodo missTodo)
    {
        missTodo.setServiceName(ServiceConstant.MISS_TODO_SAVE);//"saveMissTodo");
        VResultMessage resultMessage = postMessage(missTodo, missTodo.getClass().getName(), "missTodo", true);
        missTodo = (MissTodo)resultMessage.getResultListObj().get(0);
        return missTodo.getMtodoId();
    }

    public int updateMissTodo(MissTodo missTodo)
    {
        missTodo.setServiceName(ServiceConstant.MISS_TODO_UPDATE);//"updateMissTodo");
        VResultMessage resultMessage = postMessage(missTodo, missTodo.getClass().getName(), "missTodo", true);
        missTodo = (MissTodo)resultMessage.getResultListObj().get(0);
        return missTodo.getUpdateRecord().intValue();
    }

    public int deleteMissTodo(MissTodo missTodo)
    {
        missTodo.setServiceName(ServiceConstant.MISS_TODO_DELETE);//"deleteMissTodo");
        VResultMessage resultMessage = postMessage(missTodo, missTodo.getClass().getName(), "missTodo", true);
        missTodo = (MissTodo)resultMessage.getResultListObj().get(0);
        return missTodo.getUpdateRecord().intValue();
    }

    public MissTodo findMissTodoById(Long megId)
    {
        MissTodo missTodo = new MissTodo();
        missTodo.setMtodoId(megId);
        missTodo.setServiceName(ServiceConstant.MISS_TODO_FIND_BY_ID);//"findMissTodoById");
        VResultMessage resultMessage = postMessage(missTodo, missTodo.getClass().getName(), "missTodo", true);
        return (MissTodo)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissTodo(MissTodo missTodo)
    {
        missTodo.setServiceName(ServiceConstant.MISS_TODO_SEARCH);//"searchMissTodo");
        return postMessage(missTodo, missTodo.getClass().getName(), "missTodo", true);
    }
    public String getEmailFromMissTodo(MissTodo missTodo)
    {
        missTodo.setServiceName(ServiceConstant.MISS_TODO_FIND_MAIL);
        VResultMessage resultMessage = postMessage(missTodo, missTodo.getClass().getName(), "missTodo", true);
        missTodo = (MissTodo)resultMessage.getResultListObj().get(0);
        return missTodo.getCandidateEmail();
    }

     
    public Long saveMissAttach(MissAttach missAttach)
    {
        missAttach.setServiceName(ServiceConstant.MISS_ATTACH_SAVE);//"saveMissAttach");
        VResultMessage resultMessage = postMessage(missAttach, missAttach.getClass().getName(), "missAttach", true);
        missAttach = (MissAttach)resultMessage.getResultListObj().get(0);
        return missAttach.getMatId();
    }

    public int updateMissAttach(MissAttach missAttach)
    {
        missAttach.setServiceName(ServiceConstant.MISS_ATTACH_UPDATE);
        VResultMessage resultMessage = postMessage(missAttach, missAttach.getClass().getName(), "missAttach", true);
        missAttach = (MissAttach)resultMessage.getResultListObj().get(0);
        return missAttach.getUpdateRecord().intValue();
    }

    public int deleteMissAttach(MissAttach missAttach)
    {
        missAttach.setServiceName(ServiceConstant.MISS_ATTACH_DELETE);//"deleteMissAttach");
        VResultMessage resultMessage = postMessage(missAttach, missAttach.getClass().getName(), "missAttach", true);
        missAttach = (MissAttach)resultMessage.getResultListObj().get(0);
        return missAttach.getUpdateRecord().intValue();
    }

    public MissAttach findMissAttachById(String matModule,Long matRef,String hotlink)
    {
        MissAttach missAttach = new MissAttach();
        missAttach.setMatModule(matModule);
        missAttach.setMatHotlink(hotlink);
        missAttach.setMatRef(matRef);
        missAttach.setServiceName(ServiceConstant.MISS_ATTACH_FIND_BY_ID);//"findMissAttachById");
        VResultMessage resultMessage = postMessage(missAttach, missAttach.getClass().getName(), "missAttach", true);
        return (MissAttach)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissAttach(MissAttach missAttach)
    {
        missAttach.setServiceName(ServiceConstant.MISS_ATTACH_SEARCH);//"searchMissAttach");
        return postMessage(missAttach, missAttach.getClass().getName(), "missAttach", true);
    }
    
	@Override
	public Long saveMissContact(MissContact missContact) {
		// TODO Auto-generated method stub
		missContact.setServiceName(ServiceConstant.MISS_CONTACT_SAVE);
	    VResultMessage resultMessage = postMessage(missContact, missContact.getClass().getName(), "missContact", true);
	    missContact = (MissContact)resultMessage.getResultListObj().get(0);
	    return missContact.getMcontactId();
	}

	@Override
	public int updateMissContact(MissContact missContact) {
		// TODO Auto-generated method stub
		missContact.setServiceName(ServiceConstant.MISS_CONTACT_UPDATE);
        VResultMessage resultMessage = postMessage(missContact, missContact.getClass().getName(), "missContact", true);
        missContact = (MissContact)resultMessage.getResultListObj().get(0);
        return missContact.getUpdateRecord().intValue();
	}

	@Override
	public int deleteMissContact(MissContact missContact,String service) {
		// TODO Auto-generated method stub
		missContact.setServiceName(service);
        VResultMessage resultMessage = postMessage(missContact, missContact.getClass().getName(), "missContact", true);
        missContact = (MissContact)resultMessage.getResultListObj().get(0);
        return missContact.getUpdateRecord().intValue();
	}

	@Override
	public MissContact findMissContactById(Long long1) {
		// TODO Auto-generated method stub
		 MissContact missContact = new MissContact();
	        missContact.setMcontactId(long1);
	        missContact.setServiceName(ServiceConstant.MISS_CONTACT_FIND_BY_ID);
	        VResultMessage resultMessage = postMessage(missContact, missContact.getClass().getName(), "missContact", true);
	        return (MissContact)resultMessage.getResultListObj().get(0);
	}
	@Override
	public th.co.aoe.makedev.missconsult.xstream.MissContact findMissContactByUsername(String username) {
		// TODO Auto-generated method stub
		 MissContact missContact = new MissContact();
	        missContact.setMcontactUsername(username);
	        missContact.setServiceName(ServiceConstant.MISS_CONTACT_FIND_BY_USERNAME);
	        VResultMessage resultMessage = postMessage(missContact, missContact.getClass().getName(), "missContact", true);
	        return (MissContact)resultMessage.getResultListObj().get(0);
	}
	

	@Override
	public VResultMessage searchMissContact(MissContact missContact) {
		// TODO Auto-generated method stub
		missContact.setServiceName(ServiceConstant.MISS_CONTACT_SEARCH);
        return postMessage(missContact, missContact.getClass().getName(), "missContact", true);
	}
	@SuppressWarnings("rawtypes")
	public List listContacts(Long long1,String mcontactType)
	    {
		 
	        MissContact missContact = new MissContact();
	        missContact.setMcontactRef(long1);
	        missContact.setMcontactType(mcontactType);
	        missContact.setServiceName(ServiceConstant.MISS_CONTACT_LIST);
	        VResultMessage resultMessage = postMessage(missContact, missContact.getClass().getName(), "missContact", true);
	        return resultMessage.getResultListObj();
	    }
	@Override
	public Long saveMissManual(MissManual missManual) {
		// TODO Auto-generated method stub
		missManual.setServiceName(ServiceConstant.MISS_MANUAL_SAVE);
	    VResultMessage resultMessage = postMessage(missManual, missManual.getClass().getName(), "missManual", true);
	    missManual = (MissManual)resultMessage.getResultListObj().get(0);
	    return missManual.getMmId();
	}

	@Override
	public int updateMissManual(MissManual missManual) {
		// TODO Auto-generated method stub
		missManual.setServiceName(ServiceConstant.MISS_MANUAL_UPDATE);
        VResultMessage resultMessage = postMessage(missManual, missManual.getClass().getName(), "missManual", true);
        missManual = (MissManual)resultMessage.getResultListObj().get(0);
        return missManual.getUpdateRecord().intValue();
	}

	@Override
	public int deleteMissManual(MissManual missManual,String service) {
		// TODO Auto-generated method stub
		missManual.setServiceName(service);
        VResultMessage resultMessage = postMessage(missManual, missManual.getClass().getName(), "missManual", true);
        missManual = (MissManual)resultMessage.getResultListObj().get(0);
        return missManual.getUpdateRecord().intValue();
	}

	@Override
	public MissManual findMissManualById(Long long1) {
		// TODO Auto-generated method stub
		 MissManual missManual = new MissManual();
	        missManual.setMmId(long1);
	        missManual.setServiceName(ServiceConstant.MISS_MANUAL_FIND_BY_ID);
	        VResultMessage resultMessage = postMessage(missManual, missManual.getClass().getName(), "missManual", true);
	        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
	        	return (MissManual)resultMessage.getResultListObj().get(0);
	        return null;
	}

	@Override
	public VResultMessage searchMissManual(MissManual missManual) {
		// TODO Auto-generated method stub
		missManual.setServiceName(ServiceConstant.MISS_MANUAL_SEARCH);
        return postMessage(missManual, missManual.getClass().getName(), "missManual", true);
	}
	@Override
	public int updateMissAccountLogo(MissAccount missAccount) {
		// TODO Auto-generated method stub
		missAccount.setServiceName(ServiceConstant.MISS_ACCOUNT_UPDATE_LOGO);
	    VResultMessage resultMessage = postMessage(missAccount, missAccount.getClass().getName(), "missAccount", true);
	    missAccount = (MissAccount)resultMessage.getResultListObj().get(0);
	    return missAccount.getUpdateRecord().intValue();
	}

	@Override
	public int updateMissCandidatePhoto(MissCandidate missCandidate) {
		// TODO Auto-generated method stub
		missCandidate.setServiceName(ServiceConstant.MISS_CANDIDATE_UPDATE_PHOTO);
	    VResultMessage resultMessage = postMessage(missCandidate, missCandidate.getClass().getName(), "missCandidate", true);
	    missCandidate = (MissCandidate)resultMessage.getResultListObj().get(0);
	    return missCandidate.getUpdateRecord().intValue();
	}

	@Override
	public int updateMissContactPhoto(MissContact missContact) {
		// TODO Auto-generated method stub
		missContact.setServiceName(ServiceConstant.MISS_CONTACT_UPDATE_PHOTO);
	    VResultMessage resultMessage = postMessage(missContact, missContact.getClass().getName(), "missContact", true);
	    missContact = (MissContact)resultMessage.getResultListObj().get(0);
	    return missContact.getUpdateRecord().intValue();
	}

	@Override
	public MissAccount refillMissAccount(MissAccount missAccount) {
		// TODO Auto-generated method stub
		missAccount.setServiceName(ServiceConstant.MISS_ACCOUNT_REFILL);
	    VResultMessage resultMessage = postMessage(missAccount, missAccount.getClass().getName(), "missAccount", true);
        return (MissAccount)resultMessage.getResultListObj().get(0);
	}
  

	@Override
	public int updateMissSeriesAttach(MissSeriesAttach missSeriesAttach) {
		// TODO Auto-generated method stub
		  missSeriesAttach.setServiceName(ServiceConstant.MISS_SERIES_ATTACH_UPDATE);
	        VResultMessage resultMessage = postMessage(missSeriesAttach, missSeriesAttach.getClass().getName(), "missSeriesAttach", true);
	        missSeriesAttach = (MissSeriesAttach)resultMessage.getResultListObj().get(0);
	        return missSeriesAttach.getUpdateRecord().intValue();
	}

	@Override
	public int deleteMissSeriesAttach(MissSeriesAttach missSeriesAttach) {
		// TODO Auto-generated method stub
		  missSeriesAttach.setServiceName(ServiceConstant.MISS_SERIES_ATTACH_DELETE);
	        VResultMessage resultMessage = postMessage(missSeriesAttach, missSeriesAttach.getClass().getName(), "missSeriesAttach", true);
	        missSeriesAttach = (MissSeriesAttach)resultMessage.getResultListObj().get(0);
	        return missSeriesAttach.getUpdateRecord().intValue();
	}

	@Override
	public MissSeriesAttach findMissSeriesAttachSearch(String matModule,
			Long matRef1, Long matRef2, String hotlink) {
		// TODO Auto-generated method stub
		    MissSeriesAttach missSeriesAttach = new MissSeriesAttach();
	        missSeriesAttach.setMsatModule(matModule);
	        missSeriesAttach.setMsatHotlink(hotlink);
	        missSeriesAttach.setMsatRef1(matRef1);
	        missSeriesAttach.setMsatRef2(matRef2);
	        missSeriesAttach.setServiceName(ServiceConstant.MISS_SERIES_ATTACH_SEARCH);
	        VResultMessage resultMessage = postMessage(missSeriesAttach, missSeriesAttach.getClass().getName(), "missSeriesAttach", true);
	        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
	        	return (MissSeriesAttach)resultMessage.getResultListObj().get(0);
	        else
	        	return null;
	}
  
	
	@Override
	public Long saveRoleContact(RoleContact roleContact) {
		// TODO Auto-generated method stub
		roleContact.setServiceName(ServiceConstant.ROLE_CONTACT_SAVE);
	    VResultMessage resultMessage = postMessage(roleContact, roleContact.getClass().getName(), "roleContact", true);
	    roleContact = (RoleContact)resultMessage.getResultListObj().get(0);
	    return roleContact.getRcId();
	}

	@Override
	public int updateRoleContact(RoleContact roleContact) {
		// TODO Auto-generated method stub
		roleContact.setServiceName(ServiceConstant.ROLE_CONTACT_UPDATE);
        VResultMessage resultMessage = postMessage(roleContact, roleContact.getClass().getName(), "roleContact", true);
        roleContact = (RoleContact)resultMessage.getResultListObj().get(0);
        return roleContact.getUpdateRecord().intValue();
	}

	@Override
	public int deleteRoleContact(RoleContact roleContact, String service) {
		// TODO Auto-generated method stub
		roleContact.setServiceName(service);
        VResultMessage resultMessage = postMessage(roleContact, roleContact.getClass().getName(), "roleContact", true);
        roleContact = (RoleContact)resultMessage.getResultListObj().get(0);
        return roleContact.getUpdateRecord().intValue();
	}

	@Override
	public RoleContact findRoleContactById(Long rcId) {
		// TODO Auto-generated method stub
		RoleContact roleContact = new RoleContact();
		roleContact.setRcId(rcId);
		roleContact.setServiceName(ServiceConstant.ROLE_CONTACT_FIND_BY_ID);
	        VResultMessage resultMessage = postMessage(roleContact, roleContact.getClass().getName(), "roleContact", true);
	        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
	        	return (RoleContact)resultMessage.getResultListObj().get(0);
	        return null;
	}

	@Override
	public VResultMessage searchRoleContact(RoleContact roleContact) {
		// TODO Auto-generated method stub
		roleContact.setServiceName(ServiceConstant.ROLE_CONTACT_SEARCH);
        return postMessage(roleContact, roleContact.getClass().getName(), "roleContact", true);
	}

	@Override
	public Long saveRoleMapping(RoleMapping roleMapping) {
		// TODO Auto-generated method stub
		roleMapping.setServiceName(ServiceConstant.ROLE_MAPPING_SAVE);
	    VResultMessage resultMessage = postMessage(roleMapping, roleMapping.getClass().getName(), "roleMapping", true);
	    roleMapping = (RoleMapping)resultMessage.getResultListObj().get(0);
	    return roleMapping.getRcId();
	}

	@Override
	public int updateRoleMapping(RoleMapping roleMapping) {
		// TODO Auto-generated method stub
		roleMapping.setServiceName(ServiceConstant.ROLE_MAPPING_UPDATE);
        VResultMessage resultMessage = postMessage(roleMapping, roleMapping.getClass().getName(), "roleMapping", true);
        roleMapping = (RoleMapping)resultMessage.getResultListObj().get(0);
        return roleMapping.getUpdateRecord().intValue();
	}

	@Override
	public int deleteRoleMapping(RoleMapping roleMapping, String service) {
		// TODO Auto-generated method stub
		roleMapping.setServiceName(service);
        VResultMessage resultMessage = postMessage(roleMapping, roleMapping.getClass().getName(), "roleMapping", true);
        roleMapping = (RoleMapping)resultMessage.getResultListObj().get(0);
        return roleMapping.getUpdateRecord().intValue();
	}

	@Override
	public RoleMapping findRoleMappingById(Long rcId) {
		// TODO Auto-generated method stub
		RoleMapping roleMapping = new RoleMapping();
		roleMapping.setRcId(rcId);
		roleMapping.setServiceName(ServiceConstant.ROLE_MAPPING_FIND_BY_ID);
	        VResultMessage resultMessage = postMessage(roleMapping, roleMapping.getClass().getName(), "roleMapping", true);
	        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
	        	return (RoleMapping)resultMessage.getResultListObj().get(0);
	        return null;
	}

	@Override
	public VResultMessage searchRoleMapping(RoleMapping roleMapping) {
		// TODO Auto-generated method stub
		roleMapping.setServiceName(ServiceConstant.ROLE_MAPPING_SEARCH);
        return postMessage(roleMapping, roleMapping.getClass().getName(), "roleMapping", true);
	}


	@Override
	public Long saveRoleType(RoleType roleType) {
		// TODO Auto-generated method stub
		roleType.setServiceName(ServiceConstant.ROLE_TYPE_SAVE);
	    VResultMessage resultMessage = postMessage(roleType, roleType.getClass().getName(), "roleType", true);
	    roleType = (RoleType)resultMessage.getResultListObj().get(0);
	    return roleType.getRtId();
	}

	@Override
	public int updateRoleType(RoleType roleType) {
		// TODO Auto-generated method stub
		roleType.setServiceName(ServiceConstant.ROLE_TYPE_UPDATE);
        VResultMessage resultMessage = postMessage(roleType, roleType.getClass().getName(), "roleType", true);
        roleType = (RoleType)resultMessage.getResultListObj().get(0);
        return roleType.getUpdateRecord().intValue();
	}

	@Override
	public int deleteRoleType(RoleType roleType, String service) {
		// TODO Auto-generated method stub
		roleType.setServiceName(service);
        VResultMessage resultMessage = postMessage(roleType, roleType.getClass().getName(), "roleType", true);
        roleType = (RoleType)resultMessage.getResultListObj().get(0);
        return roleType.getUpdateRecord().intValue();
	}

	@Override
	public RoleType findRoleTypeById(Long rtId) {
		// TODO Auto-generated method stub
		RoleType roleType = new RoleType();
		roleType.setRtId(rtId);
		roleType.setServiceName(ServiceConstant.ROLE_TYPE_FIND_BY_ID);
	        VResultMessage resultMessage = postMessage(roleType, roleType.getClass().getName(), "roleType", true);
	        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
	        	return (RoleType)resultMessage.getResultListObj().get(0);
	        return null;
	}

	@Override
	public VResultMessage searchRoleType(RoleType roleType) {
		// TODO Auto-generated method stub
		roleType.setServiceName(ServiceConstant.ROLE_TYPE_SEARCH);
        return postMessage(roleType, roleType.getClass().getName(), "roleType", true);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List listRoleTypeByRcId(Long rcId) {
		// TODO Auto-generated method stub
		 RoleType roleType = new RoleType();
		 roleType.setRcId(rcId);
		 roleType.setServiceName(ServiceConstant.ROLE_TYPE_LIST_BY_RC_ID);
	        VResultMessage resultMessage = postMessage(roleType, roleType.getClass().getName(), "roleType", true);
	       return resultMessage.getResultListObj();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List listRoleContactBymaId(Long maId) {
		// TODO Auto-generated method stub
		 RoleContact roleContact = new RoleContact();
		 roleContact.setMaId(maId);
		 roleContact.setServiceName(ServiceConstant.ROLE_CONTACT_LIST_BY_MA_ID);
	        VResultMessage resultMessage = postMessage(roleContact, roleContact.getClass().getName(), "roleContact", true);
	       return resultMessage.getResultListObj();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List listRoleMappingByrcId(Long rcId) {
		// TODO Auto-generated method stub
		 RoleMapping roleMapping = new RoleMapping();
			 roleMapping.setRcId(rcId);
			 roleMapping.setServiceName(ServiceConstant.ROLE_MAPPING_LIST_BY_RC_ID);
		        VResultMessage resultMessage = postMessage(roleMapping, roleMapping.getClass().getName(), "roleMapping", true);
		       return resultMessage.getResultListObj();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List listRoleTypes(Long maId) {
		// TODO Auto-generated method stub
		 RoleType roleType = new RoleType();
		 roleType.setMaId(maId);
		 roleType.setServiceName(ServiceConstant.ROLE_TYPE_LIST);
	        VResultMessage resultMessage = postMessage(roleType, roleType.getClass().getName(), "roleType", true);
	      
	       return resultMessage.getResultListObj();
	}

	@Override
	public int updateStatusMissTestResult(Long mtrId, String column, String value) {
		// TODO Auto-generated method stub
		MissTestResult missTestResult =new MissTestResult();
		missTestResult.setMtrId(mtrId);
		missTestResult.setColumn(column);
		missTestResult.setValue(value);
		missTestResult.setServiceName(ServiceConstant.MISS_TEST_RESULT_UPDATE_STATUS);
        VResultMessage resultMessage = postMessage(missTestResult, missTestResult.getClass().getName(), "missTestResult", true);
        missTestResult = (MissTestResult)resultMessage.getResultListObj().get(0);
        return missTestResult.getUpdateRecord().intValue();
	}
	@Override
	public int updateStatusMissTestResult(String mtrIds, String column, String value) {
		// TODO Auto-generated method stub
		MissTestResult missTestResult =new MissTestResult();
		missTestResult.setMtrIds(mtrIds);
		missTestResult.setColumn(column);
		missTestResult.setValue(value);
		missTestResult.setServiceName(ServiceConstant.MISS_TEST_RESULT_UPDATE_STATUS_LIST);
        VResultMessage resultMessage = postMessage(missTestResult, missTestResult.getClass().getName(), "missTestResult", true);
        missTestResult = (MissTestResult)resultMessage.getResultListObj().get(0);
        return missTestResult.getUpdateRecord().intValue();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List listMissTheme(MissTheme missTheme) {
		// TODO Auto-generated method stub
		missTheme.setServiceName(ServiceConstant.MISS_THEME_LIST);
	    VResultMessage resultMessage = postMessage(missTheme, missTheme.getClass().getName(), "missTheme", true);
	    return resultMessage.getResultListObj();
	}
	@Override
	public MissTheme findMissThemeById(Long maId,Long mtId) {
		// TODO Auto-generated method stub
		MissTheme missTheme = new MissTheme();
		missTheme.setMtId(mtId);
		missTheme.setMaId(maId);
		missTheme.setServiceName(ServiceConstant.MISS_THEME_FIND_BY_ID);
	        VResultMessage resultMessage = postMessage(missTheme, missTheme.getClass().getName(), "missTheme", true);
	        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
	        	return (MissTheme)resultMessage.getResultListObj().get(0);
	        else
	        	return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MissCareerMaster> listMissCareerMaster(Long maId) {
		// TODO Auto-generated method stub
		MissCareerMaster missCareerMaster =new MissCareerMaster();
		missCareerMaster.setMcmRef(maId);
		missCareerMaster.setServiceName(ServiceConstant.CAREER_MASTER_LIST);
		VResultMessage resultMessage = postMessage(missCareerMaster, missCareerMaster
				.getClass().getName(), "missCareerMaster", true);
		List<MissCareerMaster> missCareerMasters=null;
		if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0){
			missCareerMasters=resultMessage.getResultListObj();
		} 
		return missCareerMasters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MissIndustryMaster> listMissIndustryMaster() {
		// TODO Auto-generated method stub
		MissIndustryMaster missIndustryMaster =new MissIndustryMaster();
		missIndustryMaster.setServiceName(ServiceConstant.INDUSTRY_MASTER_LIST);
		VResultMessage resultMessage = postMessage(missIndustryMaster, missIndustryMaster
				.getClass().getName(), "missIndustryMaster", true);
		List<MissIndustryMaster> missIndustryMasters=null;
		if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0){
			missIndustryMasters=resultMessage.getResultListObj();
		} 
		return missIndustryMasters;
	}

	@Override
	public int saveMissSystemUse(MissSystemUse missSystemUse) {
		// TODO Auto-generated method stub
		missSystemUse.setServiceName(ServiceConstant.MISS_SYSTEM_USE_SAVE);
	    VResultMessage resultMessage = postMessage(missSystemUse, missSystemUse.getClass().getName(), "missSystemUse", true);
	    missSystemUse = (MissSystemUse)resultMessage.getResultListObj().get(0);
	    return missSystemUse.getUpdateRecord().intValue();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List searchMissSystemUse(MissSystemUse missSystemUse) {
		// TODO Auto-generated method stub
		missSystemUse.setServiceName(ServiceConstant.MISS_SYSTEM_USE_SEARCH);
	    VResultMessage resultMessage = postMessage(missSystemUse, missSystemUse.getClass().getName(), "missSystemUse", true);
	    return resultMessage.getResultListObj();
	}

	@Override
	public Long saveMissSeryUse(MissSeryUse missSeryUse) {
		// TODO Auto-generated method stub
		missSeryUse.setServiceName(ServiceConstant.MISS_SERY_USE_SAVE);
	    VResultMessage resultMessage = postMessage(missSeryUse, missSeryUse.getClass().getName(), "missSeryUse", true);
	    missSeryUse = (MissSeryUse)resultMessage.getResultListObj().get(0);
	    return missSeryUse.getMsId();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List searchMissSeryUse(MissSeryUse missSeryUse) {
		// TODO Auto-generated method stub
		missSeryUse.setServiceName(ServiceConstant.MISS_SERY_USE_SEARCH);
	    VResultMessage resultMessage = postMessage(missSeryUse, missSeryUse.getClass().getName(), "missSeryUse", true);
	    return resultMessage.getResultListObj();
	}

	@Override
	public Long saveMissSeryProblem(MissSeryProblem missSeryProblem) {
		// TODO Auto-generated method stub
		missSeryProblem.setServiceName(ServiceConstant.MISS_SERY_PROBLEM_SAVE);
	    VResultMessage resultMessage = postMessage(missSeryProblem, missSeryProblem.getClass().getName(), "missSeryProblem", true);
	    missSeryProblem = (MissSeryProblem)resultMessage.getResultListObj().get(0);
	    return missSeryProblem.getMcaId();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List searchMissSeryProblem(MissSeryProblem missSeryProblem) {
		// TODO Auto-generated method stub
		missSeryProblem.setServiceName(ServiceConstant.MISS_SERY_PROBLEM_SEARCH);
	    VResultMessage resultMessage = postMessage(missSeryProblem, missSeryProblem.getClass().getName(), "missSeryProblem", true);
	    return resultMessage.getResultListObj();
	}

	@Override
	public Long saveMissSeryOrder(MissSeryOrder missSeryOrder) {
		// TODO Auto-generated method stub
		missSeryOrder.setServiceName(ServiceConstant.MISS_SERY_ORDER_SAVE);
	    VResultMessage resultMessage = postMessage(missSeryOrder, missSeryOrder.getClass().getName(), "missSeryOrder", true);
	    missSeryOrder = (MissSeryOrder)resultMessage.getResultListObj().get(0);
	    return missSeryOrder.getMsId();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List searchMissSeryOrder(MissSeryOrder missSeryOrder) {
		// TODO Auto-generated method stub
		missSeryOrder.setServiceName(ServiceConstant.MISS_SERY_ORDER_SEARCH);
	    VResultMessage resultMessage = postMessage(missSeryOrder, missSeryOrder.getClass().getName(), "missSeryOrder", true);
	    return resultMessage.getResultListObj();
	}

	@Override
	public Long saveMissReactiveLog(MissReactiveLog missReactiveLog) {
		// TODO Auto-generated method stub
		missReactiveLog.setServiceName(ServiceConstant.MISS_REACTIVE_LOG_SAVE);
	    VResultMessage resultMessage = postMessage(missReactiveLog, missReactiveLog.getClass().getName(), "missReactiveLog", true);
	    missReactiveLog = (MissReactiveLog)resultMessage.getResultListObj().get(0);
	    return missReactiveLog.getMcaId();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List searchMissReactiveLog(MissReactiveLog missReactiveLog) {
		// TODO Auto-generated method stub
		missReactiveLog.setServiceName(ServiceConstant.MISS_REACTIVE_LOG_SEARCH);
	    VResultMessage resultMessage = postMessage(missReactiveLog, missReactiveLog.getClass().getName(), "missReactiveLog", true);
	    return resultMessage.getResultListObj();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List searchMissAccountGroup(MissAccountGroup missAccountGroup) {
		// TODO Auto-generated method stub
		missAccountGroup.setServiceName(ServiceConstant.MISS_ACCOUNT_GROUP_SAVE);
	    VResultMessage resultMessage = postMessage(missAccountGroup, missAccountGroup.getClass().getName(), "missAccountGroup", true);
	    return resultMessage.getResultListObj();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List searchMissAccountMapping(MissAccountMapping missAccountMapping) {
		// TODO Auto-generated method stub
		missAccountMapping.setServiceName(ServiceConstant.MISS_ACCOUNT_MAPPING_SEARCH);
	    VResultMessage resultMessage = postMessage(missAccountMapping, missAccountMapping.getClass().getName(), "missAccountMapping", true);
	    return resultMessage.getResultListObj();
	}

	@Override
	//public ProductReport findProductReport(String mode, String year) {
	public ProductReport findProductReport(ProductReport productReport) {
		// TODO Auto-generated method stub
		/*ProductReport productReport = new ProductReport();
		productReport.setMode(mode);
		productReport.setYear(year);*/
		productReport.setServiceName(ServiceConstant.PRODUCT_REPORT_FIND);
	        VResultMessage resultMessage = postMessage(productReport, productReport.getClass().getName(), "productReport", true);
	        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
	        	return (ProductReport)resultMessage.getResultListObj().get(0);
	        else
	        	return null;
	}

	@Override
	//public  ServiceReport findServiceReport(String month, String year) {
	public  ServiceReport findServiceReport(ServiceReport serviceReport) {
		// TODO Auto-generated method stub
		/*ProductReport productReport = new ProductReport();
		productReport.setMode(month);
		productReport.setYear(year);*/
		serviceReport.setServiceName(ServiceConstant.SERVICE_REPORT_FIND);
	        VResultMessage resultMessage = postMessage(serviceReport, serviceReport.getClass().getName(), "serviceReport", true);
	        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
	        	return (ServiceReport)resultMessage.getResultListObj().get(0);
	        else
	        	return null;
	}

	@Override
	public EPTNormReport findEPTNormReport(EPTNormReport eptNormReport) {
		// TODO Auto-generated method stub
		eptNormReport.setServiceName(ServiceConstant.EPT_NORM_REPORT_FIND);
        VResultMessage resultMessage = postMessage(eptNormReport, eptNormReport.getClass().getName(), "eptNormReport", true);
        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
        	return (EPTNormReport)resultMessage.getResultListObj().get(0);
        else
        	return null;
	}

	@Override
	public EPTNormReport findCompanies(EPTNormReport eptNormReport) {
		// TODO Auto-generated method stub
		eptNormReport.setServiceName(ServiceConstant.EPT_NORM_REPORT_GET_COMPANIES);
        VResultMessage resultMessage = postMessage(eptNormReport, eptNormReport.getClass().getName(), "eptNormReport", true);
        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
        	return (EPTNormReport)resultMessage.getResultListObj().get(0);
        else
        	return null;
	}

	@Override
	public CustomerReport findCustomerReport(CustomerReport customerReport) {
		// TODO Auto-generated method stub
		customerReport.setServiceName(ServiceConstant.CUSTOMER_REPORT_FIND);
        VResultMessage resultMessage = postMessage(customerReport, customerReport.getClass().getName(), "customerReport", true);
        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
        	return (CustomerReport)resultMessage.getResultListObj().get(0);
        else
        	return null;
	}

	@Override
	public CustomerReport findGroups(CustomerReport customerReport) {
		// TODO Auto-generated method stub
		customerReport.setServiceName(ServiceConstant.CUSTOMER_REPORT_GET_GROUPS);
        VResultMessage resultMessage = postMessage(customerReport, customerReport.getClass().getName(), "customerReport", true);
        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
        	return (CustomerReport)resultMessage.getResultListObj().get(0);
        else
        	return null;
	}

	@Override
	public ConsultantReport findConsultantReport(
			ConsultantReport consultantReport) {
		// TODO Auto-generated method stub
		consultantReport.setServiceName(ServiceConstant.CONSULTANT_REPORT_FIND);
        VResultMessage resultMessage = postMessage(consultantReport, consultantReport.getClass().getName(), "consultantReport", true);
        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
        	return (ConsultantReport)resultMessage.getResultListObj().get(0);
        else
        	return null;
	}

	@Override
	public ConsultantReport findSales(ConsultantReport consultantReport) {
		// TODO Auto-generated method stub
		consultantReport.setServiceName(ServiceConstant.CONSULTANT_REPORT_GET_SALES);
        VResultMessage resultMessage = postMessage(consultantReport, consultantReport.getClass().getName(), "consultantReport", true);
        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
        	return (ConsultantReport)resultMessage.getResultListObj().get(0);
        else
        	return null;
	}

	@Override
	public  List<List<String>>  sendSurvey(MissSurveySend missSurveySend) {
		// TODO Auto-generated method stub
		missSurveySend.setServiceName(ServiceConstant.MISS_SURVEY_SEND);
	    VResultMessage resultMessage = postMessage(missSurveySend, missSurveySend.getClass().getName(), "missSurveySend", true);
	    missSurveySend = (MissSurveySend)resultMessage.getResultListObj().get(0);
	    return missSurveySend.getUserEmail();
	}

	@Override
	public int countMissContactByUsername(String username,Long id) {
		// TODO Auto-generated method stub
		 MissContact missContact = new MissContact();
		 missContact.setMcontactUsername(username);
		 missContact.setMcontactId(id);
		 missContact.setServiceName(ServiceConstant.MISS_CONTACT_COUNT_BY_USERNAME);
	    VResultMessage resultMessage = postMessage(missContact, missContact.getClass().getName(), "missContact", true);
	    missContact = (MissContact)resultMessage.getResultListObj().get(0);
	    return missContact.getUpdateRecord().intValue();
	}

	@Override
	public MissCandidate findMissCandidateByCitizendIdAndEmail(
			String citizendId, String email) {
		// TODO Auto-generated method stub
		MissCandidate missCandidate = new MissCandidate();
		missCandidate.setMcaCitizenId(citizendId);
		missCandidate.setMcaEmail(email);
		missCandidate.setServiceName(ServiceConstant.MISS_CONTACT_FIND_BY_CITIZENID_AND_EMAIL);
	    VResultMessage resultMessage = postMessage(missCandidate, missCandidate.getClass().getName(), "missCandidate", true);
	    if(resultMessage!=null && resultMessage.getResultListObj()!=null){
	    	  missCandidate = (MissCandidate)resultMessage.getResultListObj().get(0);
	    } 
	  
	    return missCandidate;
		
	}

	@Override
	public Long saveMissDoc(MissDoc missDoc) {
		// TODO Auto-generated method stub
		missDoc.setServiceName(ServiceConstant.MISS_DOC_SAVE);
	    VResultMessage resultMessage = postMessage(missDoc, missDoc.getClass().getName(), "missDoc", true);
	    missDoc = (MissDoc)resultMessage.getResultListObj().get(0);
	    return missDoc.getMdId();
	}

	@Override
	public int updateMissDoc(MissDoc missDoc) {
		// TODO Auto-generated method stub
		missDoc.setServiceName(ServiceConstant.MISS_DOC_UPDATE);
        VResultMessage resultMessage = postMessage(missDoc, missDoc.getClass().getName(), "missDoc", true);
        missDoc = (MissDoc)resultMessage.getResultListObj().get(0);
        return missDoc.getUpdateRecord().intValue();
	}

	@Override
	public int deleteMissDoc(MissDoc missDoc) {
		// TODO Auto-generated method stub
		missDoc.setServiceName(ServiceConstant.MISS_DOC_DELETE);
        VResultMessage resultMessage = postMessage(missDoc, missDoc.getClass().getName(), "missDoc", true);
        missDoc = (MissDoc)resultMessage.getResultListObj().get(0);
        return missDoc.getUpdateRecord().intValue();
	}

	@Override
	public MissDoc findMissDocById(Long mdId) {
		// TODO Auto-generated method stub
		 MissDoc missDoc = new MissDoc();
	        missDoc.setMdId(mdId);
	        missDoc.setServiceName(ServiceConstant.MISS_DOC_FIND_BY_ID);
	        VResultMessage resultMessage = postMessage(missDoc, missDoc.getClass().getName(), "missDoc", true);
	        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
	        	return (MissDoc)resultMessage.getResultListObj().get(0);
	        return null;
	}

	@Override
	public VResultMessage searchMissDoc(MissDoc missDoc) {
		// TODO Auto-generated method stub
		missDoc.setServiceName(ServiceConstant.MISS_DOC_SEARCH);
        return postMessage(missDoc, missDoc.getClass().getName(), "missDoc", true);
	}

	@Override
	public Long saveRoleSeriesMapping(RoleSeriesMapping roleSeriesMapping) {
		// TODO Auto-generated method stub
		roleSeriesMapping.setServiceName(ServiceConstant.ROLE_SERIES_MAPPING_SAVE);
	    VResultMessage resultMessage = postMessage(roleSeriesMapping, roleSeriesMapping.getClass().getName(), "roleSeriesMapping", true);
	    roleSeriesMapping = (RoleSeriesMapping)resultMessage.getResultListObj().get(0);
	    return roleSeriesMapping.getRcId();
	}

	@Override
	public int updateRoleSeriesMapping(RoleSeriesMapping roleSeriesMapping) {
		// TODO Auto-generated method stub
		roleSeriesMapping.setServiceName(ServiceConstant.ROLE_SERIES_MAPPING_UPDATE);
        VResultMessage resultMessage = postMessage(roleSeriesMapping, roleSeriesMapping.getClass().getName(), "roleSeriesMapping", true);
        roleSeriesMapping = (RoleSeriesMapping)resultMessage.getResultListObj().get(0);
        return roleSeriesMapping.getUpdateRecord().intValue();
	}

	@Override
	public int deleteRoleSeriesMapping(RoleSeriesMapping roleSeriesMapping,
			String service) {
		// TODO Auto-generated method stub
		roleSeriesMapping.setServiceName(service);
        VResultMessage resultMessage = postMessage(roleSeriesMapping, roleSeriesMapping.getClass().getName(), "roleSeriesMapping", true);
        roleSeriesMapping = (RoleSeriesMapping)resultMessage.getResultListObj().get(0);
        return roleSeriesMapping.getUpdateRecord().intValue();
	}

	@Override
	public RoleSeriesMapping findRoleSeriesMappingById(Long rcId) {
		// TODO Auto-generated method stub
		RoleSeriesMapping roleSeriesMapping = new RoleSeriesMapping();
		roleSeriesMapping.setRcId(rcId);
		roleSeriesMapping.setServiceName(ServiceConstant.ROLE_SERIES_MAPPING_FIND_BY_ID);
	        VResultMessage resultMessage = postMessage(roleSeriesMapping, roleSeriesMapping.getClass().getName(), "roleSeriesMapping", true);
	        if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0)
	        	return (RoleSeriesMapping)resultMessage.getResultListObj().get(0);
	        return null;
	}

	@Override
	public VResultMessage searchRoleSeriesMapping(RoleSeriesMapping roleSeriesMapping) {
		// TODO Auto-generated method stub
		roleSeriesMapping.setServiceName(ServiceConstant.ROLE_SERIES_MAPPING_SEARCH);
        return postMessage(roleSeriesMapping, roleSeriesMapping.getClass().getName(), "roleSeriesMapping", true);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List listRoleSeriesMappingByrcId(Long rcId) {
		// TODO Auto-generated method stub
		//return null;
		 RoleSeriesMapping roleSeriesMapping = new RoleSeriesMapping();
		 roleSeriesMapping.setRcId(rcId);
		 roleSeriesMapping.setServiceName(ServiceConstant.ROLE_SERIES_MAPPING_LIST_BY_RC_ID);
	        VResultMessage resultMessage = postMessage(roleSeriesMapping, roleSeriesMapping.getClass().getName(), "roleSeriesMapping", true);
	       return resultMessage.getResultListObj();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List findMissAccountSeriesMapByMaId(Long maId) {
		// TODO Auto-generated method stub
		MissAccountSeriesMap missAccountSeriesMap = new MissAccountSeriesMap();
	        missAccountSeriesMap.setMaId(maId);
	        missAccountSeriesMap.setServiceName(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_FIND_BY_MA_ID); 
	        VResultMessage resultMessage = postMessage(missAccountSeriesMap, missAccountSeriesMap.getClass().getName(), "missAccountSeriesMap", true);
		      
  return resultMessage.getResultListObj();
	        
	} 
	@SuppressWarnings("rawtypes")
	@Override
	public List findMissAccountSeriesMapByRole(Long maId,Long rcId) {
		// TODO Auto-generated method stub
		MissAccountSeriesMap missAccountSeriesMap = new MissAccountSeriesMap();
	        missAccountSeriesMap.setMaId(maId);
	        missAccountSeriesMap.setRcId(rcId);
	        missAccountSeriesMap.setServiceName(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_FIND_BY_ROLE); 
	        VResultMessage resultMessage = postMessage(missAccountSeriesMap, missAccountSeriesMap.getClass().getName(), "missAccountSeriesMap", true);
		      
  return resultMessage.getResultListObj();
	        
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public List<MissPositionMaster> listMissPositionMaster() { 
		// TODO Auto-generated method stub
				MissPositionMaster missPositionMaster =new MissPositionMaster();
				missPositionMaster.setServiceName(ServiceConstant.POSITION_MASTER_LIST);
				VResultMessage resultMessage = postMessage(missPositionMaster, missPositionMaster
						.getClass().getName(), "missPositionMaster", true);
				List<MissPositionMaster> missPositionMasters=null;
				if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0){
					missPositionMasters=resultMessage.getResultListObj();
				} 
				return missPositionMasters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MissDepartmentMaster> listMissDepartmentMaster() {
		// TODO Auto-generated method stub
		MissDepartmentMaster missDepartmentMaster =new MissDepartmentMaster();
		missDepartmentMaster.setServiceName(ServiceConstant.DEPARTMENT_MASTER_LIST);
		VResultMessage resultMessage = postMessage(missDepartmentMaster, missDepartmentMaster
				.getClass().getName(), "missDepartmentMaster", true);
		List<MissDepartmentMaster> missDepartmentMasters=null;
		if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0){
			missDepartmentMasters=resultMessage.getResultListObj();
		} 
		return missDepartmentMasters;
	}

	@Override
	public Long saveMissReportAttach(MissReportAttach missReportAttach) {
		// TODO Auto-generated method stub
		  missReportAttach.setServiceName(ServiceConstant.MISS_REPORT_ATTACH_SAVE);//"saveMissReportAttach");
	        VResultMessage resultMessage = postMessage(missReportAttach, missReportAttach.getClass().getName(), "missReportAttach", true);
	        missReportAttach = (MissReportAttach)resultMessage.getResultListObj().get(0);
	        return missReportAttach.getMsId();
	}

	@Override
	public int updateMissReportAttach(MissReportAttach missReportAttach) {
		// TODO Auto-generated method stub
		 missReportAttach.setServiceName(ServiceConstant.MISS_REPORT_ATTACH_UPDATE);
	        VResultMessage resultMessage = postMessage(missReportAttach, missReportAttach.getClass().getName(), "missReportAttach", true);
	        missReportAttach = (MissReportAttach)resultMessage.getResultListObj().get(0);
	        return missReportAttach.getUpdateRecord().intValue();
	}

	@Override
	public int deleteMissReportAttach(MissReportAttach missReportAttach) {
		// TODO Auto-generated method stub
		  missReportAttach.setServiceName(ServiceConstant.MISS_REPORT_ATTACH_DELETE);//"deleteMissReportAttach");
	        VResultMessage resultMessage = postMessage(missReportAttach, missReportAttach.getClass().getName(), "missReportAttach", true);
	        missReportAttach = (MissReportAttach)resultMessage.getResultListObj().get(0);
	        return missReportAttach.getUpdateRecord().intValue();
	}

	@Override
	public MissReportAttach findMissReportAttachById(Long msId,
			Long msOrder, String mraLang,String hotlink) {
		// TODO Auto-generated method stub
		 MissReportAttach missReportAttach = new MissReportAttach();
	        missReportAttach.setMsId(msId);
	        missReportAttach.setMsOrder(msOrder);
	        missReportAttach.setMraHotlink(hotlink);
	        missReportAttach.setMraLang(mraLang);
	        missReportAttach.setServiceName(ServiceConstant.MISS_REPORT_ATTACH_FIND_BY_ID);//"findMissReportAttachById");
	        VResultMessage resultMessage = postMessage(missReportAttach, missReportAttach.getClass().getName(), "missReportAttach", true);
	        return (MissReportAttach)resultMessage.getResultListObj().get(0);
	}

	@Override
	public VResultMessage searchMissReportAttach(
			MissReportAttach missReportAttach) {
		// TODO Auto-generated method stub
		missReportAttach.setServiceName(ServiceConstant.MISS_REPORT_ATTACH_SEARCH);//"searchMissReportAttach");
        return postMessage(missReportAttach, missReportAttach.getClass().getName(), "missReportAttach", true);
	} 
	
	  
	    
}
