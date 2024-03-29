// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:14:40 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MissExamServiceImpl.java

package th.co.aoe.makedev.missconsult.exam.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.exam.utils.IMakeDevUtils;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap;
import th.co.aoe.makedev.missconsult.xstream.MissAttach;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissChoice;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissEvaluationTemplate;
import th.co.aoe.makedev.missconsult.xstream.MissExam;
import th.co.aoe.makedev.missconsult.xstream.MissExamGroup;
import th.co.aoe.makedev.missconsult.xstream.MissExamType;
import th.co.aoe.makedev.missconsult.xstream.MissManual;
import th.co.aoe.makedev.missconsult.xstream.MissQuestion;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesMap;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.MissSurveySend;
import th.co.aoe.makedev.missconsult.xstream.MissTemplate;
import th.co.aoe.makedev.missconsult.xstream.MissTest;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;
import th.co.aoe.makedev.missconsult.xstream.MissTodo;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
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
        missExamGroup.setServiceName("saveMissExamGroup");
        VResultMessage resultMessage = postMessage(missExamGroup, missExamGroup.getClass().getName(), "missExamGroup", true);
        missExamGroup = (MissExamGroup)resultMessage.getResultListObj().get(0);
        return missExamGroup.getMegId();
    }

    public int updateMissExamGroup(MissExamGroup missExamGroup)
    {
        missExamGroup.setServiceName("updateMissExamGroup");
        VResultMessage resultMessage = postMessage(missExamGroup, missExamGroup.getClass().getName(), "missExamGroup", true);
        missExamGroup = (MissExamGroup)resultMessage.getResultListObj().get(0);
        return missExamGroup.getUpdateRecord().intValue();
    }

    public int deleteMissExamGroup(MissExamGroup missExamGroup)
    {
        missExamGroup.setServiceName("deleteMissExamGroup");
        VResultMessage resultMessage = postMessage(missExamGroup, missExamGroup.getClass().getName(), "missExamGroup", true);
        missExamGroup = (MissExamGroup)resultMessage.getResultListObj().get(0);
        return missExamGroup.getUpdateRecord().intValue();
    }

    public MissExamGroup findMissExamGroupById(Long megId)
    {
        MissExamGroup missExamGroup = new MissExamGroup();
        missExamGroup.setMegId(megId);
        missExamGroup.setServiceName("findMissExamGroupById");
        VResultMessage resultMessage = postMessage(missExamGroup, missExamGroup.getClass().getName(), "missExamGroup", true);
        return (MissExamGroup)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissExamGroup(MissExamGroup missExamGroup)
    {
        missExamGroup.setServiceName("searchMissExamGroup");
        return postMessage(missExamGroup, missExamGroup.getClass().getName(), "missExamGroup", true);
    }

    public Long saveMissAccount(MissAccount missAccount)
    {
        missAccount.setServiceName("saveMissAccount");
        VResultMessage resultMessage = postMessage(missAccount, missAccount.getClass().getName(), "missAccount", true);
        missAccount = (MissAccount)resultMessage.getResultListObj().get(0);
        return missAccount.getMaId();
    }

    public int updateMissAccount(MissAccount missAccount)
    {
        missAccount.setServiceName("updateMissAccount");
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
        missAccount.setServiceName("findMissAccountById");
        VResultMessage resultMessage = postMessage(missAccount, missAccount.getClass().getName(), "missAccount", true);
        return (MissAccount)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissAccount(MissAccount missAccount)
    {
        missAccount.setServiceName("searchMissAccount");
        return postMessage(missAccount, missAccount.getClass().getName(), "missAccount", true);
    }

    public Long saveMissAccountSeriesMap(MissAccountSeriesMap missAccountSeriesMap)
    {
        missAccountSeriesMap.setServiceName("saveMissAccountSeriesMap");
        VResultMessage resultMessage = postMessage(missAccountSeriesMap, missAccountSeriesMap.getClass().getName(), "missAccountSeriesMap", true);
        missAccountSeriesMap = (MissAccountSeriesMap)resultMessage.getResultListObj().get(0);
        return missAccountSeriesMap.getMaId();
    }

    public int updateMissAccountSeriesMap(MissAccountSeriesMap missAccountSeriesMap)
    {
        missAccountSeriesMap.setServiceName("updateMissAccountSeriesMap");
        VResultMessage resultMessage = postMessage(missAccountSeriesMap, missAccountSeriesMap.getClass().getName(), "missAccountSeriesMap", true);
        missAccountSeriesMap = (MissAccountSeriesMap)resultMessage.getResultListObj().get(0);
        return missAccountSeriesMap.getUpdateRecord().intValue();
    }

    public int deleteMissAccountSeriesMap(MissAccountSeriesMap missAccountSeriesMap)
    {
        missAccountSeriesMap.setServiceName("deleteMissAccountSeriesMap");
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
        missAccountSeriesMap.setServiceName("searchMissAccountSeriesMap");
        return postMessage(missAccountSeriesMap, missAccountSeriesMap.getClass().getName(), "missAccountSeriesMap", true);
    }

    public Long saveMissCandidate(MissCandidate missCandidate)
    {
        missCandidate.setServiceName("saveMissCandidate");
        VResultMessage resultMessage = postMessage(missCandidate, missCandidate.getClass().getName(), "missCandidate", true);
        missCandidate = (MissCandidate)resultMessage.getResultListObj().get(0);
        return missCandidate.getMcaId();
    }

    public int updateMissCandidate(MissCandidate missCandidate)
    {
        missCandidate.setServiceName("updateMissCandidate");
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
        missCandidate.setServiceName("searchMissCandidate");
        return postMessage(missCandidate, missCandidate.getClass().getName(), "missCandidate", true);
    }

    public Long saveMissChoice(MissChoice missChoice)
    {
        missChoice.setServiceName("saveMissChoice");
        VResultMessage resultMessage = postMessage(missChoice, missChoice.getClass().getName(), "missChoice", true);
        missChoice = (MissChoice)resultMessage.getResultListObj().get(0);
        return missChoice.getMcId();
    }

    public int updateMissChoice(MissChoice missChoice)
    {
        missChoice.setServiceName("updateMissChoice");
        VResultMessage resultMessage = postMessage(missChoice, missChoice.getClass().getName(), "missChoice", true);
        missChoice = (MissChoice)resultMessage.getResultListObj().get(0);
        return missChoice.getUpdateRecord().intValue();
    }

    public int deleteMissChoice(MissChoice missChoice)
    {
        missChoice.setServiceName("deleteMissChoice");
        VResultMessage resultMessage = postMessage(missChoice, missChoice.getClass().getName(), "missChoice", true);
        missChoice = (MissChoice)resultMessage.getResultListObj().get(0);
        return missChoice.getUpdateRecord().intValue();
    }

    public MissChoice findMissChoiceById(Long megId)
    {
        MissChoice missChoice = new MissChoice();
        missChoice.setMcId(megId);
        missChoice.setServiceName("findMissChoiceById");
        VResultMessage resultMessage = postMessage(missChoice, missChoice.getClass().getName(), "missChoice", true);
        return (MissChoice)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissChoice(MissChoice missChoice)
    {
        missChoice.setServiceName("searchMissChoice");
        return postMessage(missChoice, missChoice.getClass().getName(), "missChoice", true);
    }

    public Long saveMissEvaluationTemplate(MissEvaluationTemplate missEvaluationTemplate)
    {
        missEvaluationTemplate.setServiceName("saveMissEvaluationTemplate");
        VResultMessage resultMessage = postMessage(missEvaluationTemplate, missEvaluationTemplate.getClass().getName(), "missEvaluationTemplate", true);
        missEvaluationTemplate = (MissEvaluationTemplate)resultMessage.getResultListObj().get(0);
       // return missEvaluationTemplate.getMetId();
        return 0l;
    }

    public int updateMissEvaluationTemplate(MissEvaluationTemplate missEvaluationTemplate)
    {
        missEvaluationTemplate.setServiceName("updateMissEvaluationTemplate");
        VResultMessage resultMessage = postMessage(missEvaluationTemplate, missEvaluationTemplate.getClass().getName(), "missEvaluationTemplate", true);
        missEvaluationTemplate = (MissEvaluationTemplate)resultMessage.getResultListObj().get(0);
        return missEvaluationTemplate.getUpdateRecord().intValue();
    }

    public int deleteMissEvaluationTemplate(MissEvaluationTemplate missEvaluationTemplate)
    {
        missEvaluationTemplate.setServiceName("deleteMissEvaluationTemplate");
        VResultMessage resultMessage = postMessage(missEvaluationTemplate, missEvaluationTemplate.getClass().getName(), "missEvaluationTemplate", true);
        missEvaluationTemplate = (MissEvaluationTemplate)resultMessage.getResultListObj().get(0);
        return missEvaluationTemplate.getUpdateRecord().intValue();
    }

    public MissEvaluationTemplate findMissEvaluationTemplateById(Long megId)
    {
        MissEvaluationTemplate missEvaluationTemplate = new MissEvaluationTemplate();
       // missEvaluationTemplate.setMetId(megId);
        missEvaluationTemplate.setServiceName("findMissEvaluationTemplateById");
        VResultMessage resultMessage = postMessage(missEvaluationTemplate, missEvaluationTemplate.getClass().getName(), "missEvaluationTemplate", true);
        return (MissEvaluationTemplate)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissEvaluationTemplate(MissEvaluationTemplate missEvaluationTemplate)
    {
        missEvaluationTemplate.setServiceName("searchMissEvaluationTemplate");
        return postMessage(missEvaluationTemplate, missEvaluationTemplate.getClass().getName(), "missEvaluationTemplate", true);
    }

    public Long saveMissExam(MissExam missExam)
    {
        missExam.setServiceName("saveMissExam");
        VResultMessage resultMessage = postMessage(missExam, missExam.getClass().getName(), "missExam", true);
        missExam = (MissExam)resultMessage.getResultListObj().get(0);
        return missExam.getMeId();
    }

    public int updateMissExam(MissExam missExam)
    {
        missExam.setServiceName("updateMissExam");
        VResultMessage resultMessage = postMessage(missExam, missExam.getClass().getName(), "missExam", true);
       // System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx="+resultMessage);
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
        missExam.setServiceName("findMissExamById");
        VResultMessage resultMessage = postMessage(missExam, missExam.getClass().getName(), "missExam", true);
        return (MissExam)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissExam(MissExam missExam)
    {
        missExam.setServiceName("searchMissExam");
        return postMessage(missExam, missExam.getClass().getName(), "missExam", true);
    }

    public List listMissExam()
    {
        MissExam missExam = new MissExam();
        missExam.setServiceName("listMissExam");
        VResultMessage resultMessage = postMessage(missExam, missExam.getClass().getName(), "missExam", true);
        return resultMessage.getResultListObj();
    }

    public List listMissSery()
    {
        MissSery missSery = new MissSery();
        missSery.setServiceName("listMissSeries");
        VResultMessage resultMessage = postMessage(missSery, missSery.getClass().getName(), "missSery", true);
        return resultMessage.getResultListObj();
    }

    public Long saveMissExamType(MissExamType missExamType)
    {
        missExamType.setServiceName("saveMissExamType");
        VResultMessage resultMessage = postMessage(missExamType, missExamType.getClass().getName(), "missExamType", true);
        missExamType = (MissExamType)resultMessage.getResultListObj().get(0);
        return missExamType.getMetId();
    }

    public int updateMissExamType(MissExamType missExamType)
    {
        missExamType.setServiceName("updateMissExamType");
        VResultMessage resultMessage = postMessage(missExamType, missExamType.getClass().getName(), "missExamType", true);
      
        missExamType = (MissExamType)resultMessage.getResultListObj().get(0);
        return missExamType.getUpdateRecord().intValue();
    }

    public int deleteMissExamType(MissExamType missExamType)
    {
        missExamType.setServiceName("deleteMissExamType");
        VResultMessage resultMessage = postMessage(missExamType, missExamType.getClass().getName(), "missExamType", true);
        missExamType = (MissExamType)resultMessage.getResultListObj().get(0);
        return missExamType.getUpdateRecord().intValue();
    }

    public MissExamType findMissExamTypeById(Long megId)
    {
        MissExamType missExamType = new MissExamType();
        missExamType.setMetId(megId);
        missExamType.setServiceName("findMissExamTypeById");
        VResultMessage resultMessage = postMessage(missExamType, missExamType.getClass().getName(), "missExamType", true);
        return (MissExamType)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissExamType(MissExamType missExamType)
    {
        missExamType.setServiceName("searchMissExamType");
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
        missQuestion.setServiceName("deleteMissQuestion");
        VResultMessage resultMessage = postMessage(missQuestion, missQuestion.getClass().getName(), "missQuestion", true);
        missQuestion = (MissQuestion)resultMessage.getResultListObj().get(0);
        return missQuestion.getUpdateRecord().intValue();
    }

    public MissQuestion findMissQuestionById(Long megId)
    {
        MissQuestion missQuestion = new MissQuestion();
        missQuestion.setMqId(megId);
        missQuestion.setServiceName("findMissQuestionById");
        VResultMessage resultMessage = postMessage(missQuestion, missQuestion.getClass().getName(), "missQuestion", true);
        return (MissQuestion)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissQuestion(MissQuestion missQuestion)
    {
        missQuestion.setServiceName("searchMissQuestion");
        return postMessage(missQuestion, missQuestion.getClass().getName(), "missQuestion", true);
    }
    
    public   List listMissQuestions(Long meId){
    	
    	 MissExam missExam = new MissExam();
    	 missExam.setMeId(meId);
    	 
    	 MissQuestion missQuestion =new MissQuestion();
    	 missQuestion.setServiceName(ServiceConstant.MISS_QUESTION_LIST);
         missQuestion.setMissExam(missExam);
         VResultMessage resultMessage = postMessage(missQuestion, missQuestion.getClass().getName(), "missQuestion", true);
         return resultMessage.getResultListObj();
    }

    public Long saveMissSeriesMap(MissSeriesMap missSeriesMap)
    {
        return null;
    }

    public int updateMissSeriesMap(MissSeriesMap missSeriesMap)
    {
        missSeriesMap.setServiceName("updateMissSeriesMap");
        VResultMessage resultMessage = postMessage(missSeriesMap, missSeriesMap.getClass().getName(), "missSeriesMap", true);
        missSeriesMap = (MissSeriesMap)resultMessage.getResultListObj().get(0);
        return missSeriesMap.getUpdateRecord().intValue();
    }

    public int deleteMissSeriesMap(MissSeriesMap missSeriesMap)
    {
        missSeriesMap.setServiceName("deleteMissSeriesMap");
        VResultMessage resultMessage = postMessage(missSeriesMap, missSeriesMap.getClass().getName(), "missSeriesMap", true);
        missSeriesMap = (MissSeriesMap)resultMessage.getResultListObj().get(0);
        return missSeriesMap.getUpdateRecord().intValue();
    }

    public MissSeriesMap findMissSeriesMapById(Long megId)
    {
        MissSeriesMap missSeriesMap = new MissSeriesMap();
        missSeriesMap.setMsId(megId);
        missSeriesMap.setServiceName("findMissSeriesMapById");
        VResultMessage resultMessage = postMessage(missSeriesMap, missSeriesMap.getClass().getName(), "missSeriesMap", true);
        return (MissSeriesMap)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissSeriesMap(MissSeriesMap missSeriesMap)
    {
        missSeriesMap.setServiceName("searchMissSeriesMap");
        return postMessage(missSeriesMap, missSeriesMap.getClass().getName(), "missSeriesMap", true);
    }

    public Long saveMissSery(MissSery missSery)
    {
        missSery.setServiceName("saveMissSery");
        VResultMessage resultMessage = postMessage(missSery, missSery.getClass().getName(), "missSery", true);
        missSery = (MissSery)resultMessage.getResultListObj().get(0);
        return missSery.getMsId();
    }

    public int updateMissSery(MissSery missSery)
    {
        missSery.setServiceName("updateMissSery");
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
        missSery.setServiceName("findMissSeryById");
        VResultMessage resultMessage = postMessage(missSery, missSery.getClass().getName(), "missSery", true);
        return (MissSery)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissSery(MissSery missSery)
    {
        missSery.setServiceName("searchMissSery");
        return postMessage(missSery, missSery.getClass().getName(), "missSery", true);
    }

    public Long saveMissSurveySend(MissSurveySend missSurveySend)
    {
        missSurveySend.setServiceName("saveMissSurveySend");
        VResultMessage resultMessage = postMessage(missSurveySend, missSurveySend.getClass().getName(), "missSurveySend", true);
        missSurveySend = (MissSurveySend)resultMessage.getResultListObj().get(0);
        return missSurveySend.getMssId();
    }

    public int updateMissSurveySend(MissSurveySend missSurveySend)
    {
        missSurveySend.setServiceName("updateMissSurveySend");
        VResultMessage resultMessage = postMessage(missSurveySend, missSurveySend.getClass().getName(), "missSurveySend", true);
        missSurveySend = (MissSurveySend)resultMessage.getResultListObj().get(0);
        return missSurveySend.getUpdateRecord().intValue();
    }

    public int deleteMissSurveySend(MissSurveySend missSurveySend)
    {
        missSurveySend.setServiceName("deleteMissSurveySend");
        VResultMessage resultMessage = postMessage(missSurveySend, missSurveySend.getClass().getName(), "missSurveySend", true);
        missSurveySend = (MissSurveySend)resultMessage.getResultListObj().get(0);
        return missSurveySend.getUpdateRecord().intValue();
    }

    public MissSurveySend findMissSurveySendById(Long megId)
    {
        MissSurveySend missSurveySend = new MissSurveySend();
        missSurveySend.setMssId(megId);
        missSurveySend.setServiceName("findMissSurveySendById");
        VResultMessage resultMessage = postMessage(missSurveySend, missSurveySend.getClass().getName(), "missSurveySend", true);
        return (MissSurveySend)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissSurveySend(MissSurveySend missSurveySend)
    {
        missSurveySend.setServiceName("searchMissSurveySend");
        return postMessage(missSurveySend, missSurveySend.getClass().getName(), "missSurveySend", true);
    }

    public Long saveMissTemplate(MissTemplate missTemplate)
    {
        missTemplate.setServiceName("saveMissTemplate");
        VResultMessage resultMessage = postMessage(missTemplate, missTemplate.getClass().getName(), "missTemplate", true);
        missTemplate = (MissTemplate)resultMessage.getResultListObj().get(0);
        return missTemplate.getMtId();
    }

    public int updateMissTemplate(MissTemplate missTemplate)
    {
        missTemplate.setServiceName("updateMissTemplate");
        VResultMessage resultMessage = postMessage(missTemplate, missTemplate.getClass().getName(), "missTemplate", true);
        missTemplate = (MissTemplate)resultMessage.getResultListObj().get(0);
        return missTemplate.getUpdateRecord().intValue();
    }

    public int deleteMissTemplate(MissTemplate missTemplate)
    {
        missTemplate.setServiceName("deleteMissTemplate");
        VResultMessage resultMessage = postMessage(missTemplate, missTemplate.getClass().getName(), "missTemplate", true);
        missTemplate = (MissTemplate)resultMessage.getResultListObj().get(0);
        return missTemplate.getUpdateRecord().intValue();
    }

    public MissTemplate findMissTemplateById(Long megId)
    {
        MissTemplate missTemplate = new MissTemplate();
        missTemplate.setMtId(megId);
        missTemplate.setServiceName("findMissTemplateById");
        VResultMessage resultMessage = postMessage(missTemplate, missTemplate.getClass().getName(), "missTemplate", true);
        return (MissTemplate)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissTemplate(MissTemplate missTemplate)
    {
        missTemplate.setServiceName("searchMissTemplate");
        return postMessage(missTemplate, missTemplate.getClass().getName(), "missTemplate", true);
    }

    public Long saveMissTest(MissTest missTest)
    {
        missTest.setServiceName("saveMissTest");
        VResultMessage resultMessage = postMessage(missTest, missTest.getClass().getName(), "missTest", true);
        missTest = (MissTest)resultMessage.getResultListObj().get(0);
        return missTest.getMtestId();
    }

    public int updateMissTest(MissTest missTest)
    {
        missTest.setServiceName("updateMissTest");
        VResultMessage resultMessage = postMessage(missTest, missTest.getClass().getName(), "missTest", true);
        missTest = (MissTest)resultMessage.getResultListObj().get(0);
        return missTest.getUpdateRecord().intValue();
    }

    public int deleteMissTest(MissTest missTest)
    {
        missTest.setServiceName("deleteMissTest");
        VResultMessage resultMessage = postMessage(missTest, missTest.getClass().getName(), "missTest", true);
        missTest = (MissTest)resultMessage.getResultListObj().get(0);
        return missTest.getUpdateRecord().intValue();
    }

    public MissTest findMissTestById(Long megId)
    {
        MissTest missTest = new MissTest();
        missTest.setMtestId(megId);
        missTest.setServiceName("findMissTestById");
        VResultMessage resultMessage = postMessage(missTest, missTest.getClass().getName(), "missTest", true);
        return (MissTest)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissTest(MissTest missTest)
    {
        missTest.setServiceName("searchMissTest");
        return postMessage(missTest, missTest.getClass().getName(), "missTest", true);
    }

    public Long saveMissTestResult(MissTestResult missTestResult)
    {
        missTestResult.setServiceName("saveMissTestResult");
        VResultMessage resultMessage = postMessage(missTestResult, missTestResult.getClass().getName(), "missTestResult", true);
        missTestResult = (MissTestResult)resultMessage.getResultListObj().get(0);
        return missTestResult.getMtrId();
    }

    public int updateMissTestResult(MissTestResult missTestResult)
    {
        missTestResult.setServiceName("updateMissTestResult");
        VResultMessage resultMessage = postMessage(missTestResult, missTestResult.getClass().getName(), "missTestResult", true);
        missTestResult = (MissTestResult)resultMessage.getResultListObj().get(0);
        return missTestResult.getUpdateRecord().intValue();
    }

    public int deleteMissTestResult(MissTestResult missTestResult)
    {
        missTestResult.setServiceName("deleteMissTestResult");
        VResultMessage resultMessage = postMessage(missTestResult, missTestResult.getClass().getName(), "missTestResult", true);
        missTestResult = (MissTestResult)resultMessage.getResultListObj().get(0);
        return missTestResult.getUpdateRecord().intValue();
    }

    public MissTestResult findMissTestResultById(Long megId)
    {
        MissTestResult missTestResult = new MissTestResult();
        missTestResult.setMtrId(megId);
        missTestResult.setServiceName("findMissTestResultById");
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
        missTodo.setServiceName("saveMissTodo");
        VResultMessage resultMessage = postMessage(missTodo, missTodo.getClass().getName(), "missTodo", true);
        missTodo = (MissTodo)resultMessage.getResultListObj().get(0);
        return missTodo.getMtodoId();
    }

    public int updateMissTodo(MissTodo missTodo)
    {
        missTodo.setServiceName("updateMissTodo");
        VResultMessage resultMessage = postMessage(missTodo, missTodo.getClass().getName(), "missTodo", true);
        missTodo = (MissTodo)resultMessage.getResultListObj().get(0);
        return missTodo.getUpdateRecord().intValue();
    }

    public int deleteMissTodo(MissTodo missTodo)
    {
        missTodo.setServiceName("deleteMissTodo");
        VResultMessage resultMessage = postMessage(missTodo, missTodo.getClass().getName(), "missTodo", true);
        missTodo = (MissTodo)resultMessage.getResultListObj().get(0);
        return missTodo.getUpdateRecord().intValue();
    }

    public MissTodo findMissTodoById(Long megId)
    {
        MissTodo missTodo = new MissTodo();
        missTodo.setMtodoId(megId);
        missTodo.setServiceName("findMissTodoById");
        VResultMessage resultMessage = postMessage(missTodo, missTodo.getClass().getName(), "missTodo", true);
        return (MissTodo)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissTodo(MissTodo missTodo)
    {
        missTodo.setServiceName("searchMissTodo");
        return postMessage(missTodo, missTodo.getClass().getName(), "missTodo", true);
    }
 
     
    public Long saveMissAttach(MissAttach missAttach)
    {
        missAttach.setServiceName("saveMissAttach");
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
        missAttach.setServiceName("deleteMissAttach");
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
        missAttach.setServiceName("findMissAttachById");
        VResultMessage resultMessage = postMessage(missAttach, missAttach.getClass().getName(), "missAttach", true);
        return (MissAttach)resultMessage.getResultListObj().get(0);
    }

    public VResultMessage searchMissAttach(MissAttach missAttach)
    {
        missAttach.setServiceName("searchMissAttach");
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
	public VResultMessage searchMissContact(MissContact missContact) {
		// TODO Auto-generated method stub
		missContact.setServiceName(ServiceConstant.MISS_CONTACT_SEARCH);
        return postMessage(missContact, missContact.getClass().getName(), "missContact", true);
	}
	 
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
    public static void main(String args[])
    {
        MissExamServiceImpl main = new MissExamServiceImpl();
        System.out.println(IMakeDevUtils.calculatePage(3, 7));
        DateTime dt = new DateTime();
        System.out.println(dt.getDayOfMonth());
        System.out.println(dt.dayOfMonth().getAsShortText(Locale.US));
        System.out.println(dt.monthOfYear().getAsShortText(Locale.CANADA));
        System.out.println(dt.year().get());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(format.format(dt.toDate()));
        Calendar c = Calendar.getInstance();
        c.setTime(dt.toDate());
        c.add(5, 1);
        java.util.Date dt2 = c.getTime();
        System.out.println("xxxxxxxxxxxxxxxxxxxxx");
        DateTime dt22 = new DateTime(dt2);
        System.out.println(dt22.getDayOfMonth());
        System.out.println(dt22.dayOfMonth().getAsShortText(Locale.US));
        System.out.println(dt22.monthOfYear().getAsShortText(Locale.CANADA));
        System.out.println(dt22.year().get());
        System.out.println(format.format(dt22.toDate()));
    }

    private void testMissAccount()
    {
        MissAccount missAccount = findMissAccountById(new Long(1L));
        System.out.println(missAccount);
        missAccount.setMaAddress((new StringBuilder(String.valueOf(missAccount.getMaAddress()))).append(" update record").toString());
        updateMissAccount(missAccount);
        MissAccount missAccountSave = new MissAccount();
        BeanUtils.copyProperties(missAccount, missAccountSave);
        missAccountSave.setMaId(null);
        saveMissAccount(missAccountSave);
        MissAccount missAccountSearch = new MissAccount();
        missAccountSearch.setMaName("search");
        VResultMessage vresult = searchMissAccount(missAccountSearch);
        System.out.println(vresult);
        MissAccount missAccountDelete = new MissAccount();
        missAccountDelete.setMaId(new Long(1L));
    }

    private void testMissAccountSeriesMap()
    {
        MissAccountSeriesMap missAccountSeriesMap = findMissAccountSeriesMapById(new Long(1L));
        System.out.println(missAccountSeriesMap);
        missAccountSeriesMap.setMasmOrderUnit(Long.valueOf(missAccountSeriesMap.getMasmOrderUnit().longValue() + 1L));
        updateMissAccountSeriesMap(missAccountSeriesMap);
        MissAccountSeriesMap missAccountSeriesMapSave = new MissAccountSeriesMap();
        BeanUtils.copyProperties(missAccountSeriesMap, missAccountSeriesMapSave);
        missAccountSeriesMapSave.setMaId(null);
        saveMissAccountSeriesMap(missAccountSeriesMapSave);
        MissAccountSeriesMap missAccountSeriesMapSearch = new MissAccountSeriesMap();
        missAccountSeriesMapSearch.setMasmStatus("1");
        VResultMessage vresult = searchMissAccountSeriesMap(missAccountSeriesMapSearch);
        System.out.println(vresult);
        MissAccountSeriesMap missAccountSeriesMapDelete = new MissAccountSeriesMap();
        missAccountSeriesMapDelete.setMaId(new Long(1L));
        deleteMissAccountSeriesMap(missAccountSeriesMapDelete);
    }

    private void testMissCandidate()
    {
        MissCandidate missCandidate = findMissCandidateById(new Long(1L));
        System.out.println(missCandidate);
        missCandidate.setMcaDepartment((new StringBuilder(String.valueOf(missCandidate.getMcaDepartment()))).append(" update").toString());
        updateMissCandidate(missCandidate);
        MissCandidate missCandidateSave = new MissCandidate();
        BeanUtils.copyProperties(missCandidate, missCandidateSave);
        missCandidateSave.setMcaId(null);
        saveMissCandidate(missCandidateSave);
        MissCandidate missCandidateSearch = new MissCandidate();
        VResultMessage vresult = searchMissCandidate(missCandidateSearch);
        System.out.println(vresult);
        MissCandidate missCandidateDelete = new MissCandidate();
    }

    private void testMissChoice()
    {
        MissChoice missChoice = findMissChoiceById(new Long(1L));
        System.out.println(missChoice);
        updateMissChoice(missChoice);
        MissChoice missChoiceSave = new MissChoice();
        saveMissChoice(missChoiceSave);
        MissChoice missChoiceSearch = new MissChoice();
        VResultMessage vresult = searchMissChoice(missChoiceSearch);
        System.out.println(vresult);
        MissChoice missChoiceDelete = new MissChoice();
        deleteMissChoice(missChoiceDelete);
    }

    private void testMissEvaluationTemplate()
    {
        MissEvaluationTemplate missEvaluationTemplate = findMissEvaluationTemplateById(new Long(1L));
        System.out.println(missEvaluationTemplate);
        updateMissEvaluationTemplate(missEvaluationTemplate);
        MissEvaluationTemplate missEvaluationTemplateSave = new MissEvaluationTemplate();
        saveMissEvaluationTemplate(missEvaluationTemplateSave);
        MissEvaluationTemplate missEvaluationTemplateSearch = new MissEvaluationTemplate();
        VResultMessage vresult = searchMissEvaluationTemplate(missEvaluationTemplateSearch);
        System.out.println(vresult);
        MissEvaluationTemplate missEvaluationTemplateDelete = new MissEvaluationTemplate();
        deleteMissEvaluationTemplate(missEvaluationTemplateDelete);
    }

    private void testMissExam()
    {
        MissExam missExam = findMissExamById(new Long(1L));
        System.out.println(missExam);
        updateMissExam(missExam);
        MissExam missExamSave = new MissExam();
        saveMissExam(missExamSave);
        MissExam missExamSearch = new MissExam();
        VResultMessage vresult = searchMissExam(missExamSearch);
        System.out.println(vresult);
        MissExam missExamDelete = new MissExam();
    }

    private void testMissExamGroup()
    {
        MissExamGroup missExamGroupSearch = new MissExamGroup();
        VResultMessage vresult = searchMissExamGroup(missExamGroupSearch);
        System.out.println(vresult.getResultListObj());
    }

    private void testMissExamType()
    {
        MissExamType missExamType = findMissExamTypeById(new Long(1L));
        System.out.println(missExamType);
        updateMissExamType(missExamType);
        MissExamType missExamTypeSave = new MissExamType();
        saveMissExamType(missExamTypeSave);
        MissExamType missExamTypeSearch = new MissExamType();
        VResultMessage vresult = searchMissExamType(missExamTypeSearch);
        System.out.println(vresult);
        MissExamType missExamTypeDelete = new MissExamType();
        deleteMissExamType(missExamTypeDelete);
    }

    private void testMissQuestion()
    {
        MissQuestion missQuestion = findMissQuestionById(new Long(1L));
        System.out.println(missQuestion);
        //updateMissQuestion(missQuestion);
        MissQuestion missQuestionSave = new MissQuestion();
        //saveMissQuestion(missQuestionSave);
        MissQuestion missQuestionSearch = new MissQuestion();
        VResultMessage vresult = searchMissQuestion(missQuestionSearch);
        System.out.println(vresult);
        MissQuestion missQuestionDelete = new MissQuestion();
        deleteMissQuestion(missQuestionDelete);
    }

    private void testMissSeriesMap()
    {
        MissSeriesMap missSeriesMap = findMissSeriesMapById(new Long(1L));
        System.out.println(missSeriesMap);
        updateMissSeriesMap(missSeriesMap);
        MissSeriesMap missSeriesMapSave = new MissSeriesMap();
        saveMissSeriesMap(missSeriesMapSave);
        MissSeriesMap missSeriesMapSearch = new MissSeriesMap();
        VResultMessage vresult = searchMissSeriesMap(missSeriesMapSearch);
        System.out.println(vresult);
        MissSeriesMap missSeriesMapDelete = new MissSeriesMap();
        deleteMissSeriesMap(missSeriesMapDelete);
    }

    private void testMissSery()
    {
        MissSery missSery = findMissSeryById(new Long(1L));
        System.out.println(missSery);
        updateMissSery(missSery);
        MissSery missSerySave = new MissSery();
        saveMissSery(missSerySave);
        MissSery missSerySearch = new MissSery();
        VResultMessage vresult = searchMissSery(missSerySearch);
        System.out.println(vresult);
        MissSery missSeryDelete = new MissSery();
        deleteMissSery(missSeryDelete, "");
    }

    private void testMissSurveySend()
    {
        MissSurveySend missSurveySend = findMissSurveySendById(new Long(1L));
        System.out.println(missSurveySend);
        updateMissSurveySend(missSurveySend);
        MissSurveySend missSurveySendSave = new MissSurveySend();
        saveMissSurveySend(missSurveySendSave);
        MissSurveySend missSurveySendSearch = new MissSurveySend();
        VResultMessage vresult = searchMissSurveySend(missSurveySendSearch);
        System.out.println(vresult);
        MissSurveySend missSurveySendDelete = new MissSurveySend();
        deleteMissSurveySend(missSurveySendDelete);
    }

    private void testMissTemplate()
    {
        MissTemplate missTemplate = findMissTemplateById(new Long(1L));
        System.out.println(missTemplate);
        updateMissTemplate(missTemplate);
        MissTemplate missTemplateSave = new MissTemplate();
        saveMissTemplate(missTemplateSave);
        MissTemplate missTemplateSearch = new MissTemplate();
        VResultMessage vresult = searchMissTemplate(missTemplateSearch);
        System.out.println(vresult);
        MissTemplate missTemplateDelete = new MissTemplate();
        deleteMissTemplate(missTemplateDelete);
    }

    private void testMissTest()
    {
        MissTest missTest = findMissTestById(new Long(1L));
        System.out.println(missTest);
        updateMissTest(missTest);
        MissTest missTestSave = new MissTest();
        saveMissTest(missTestSave);
        MissTest missTestSearch = new MissTest();
        VResultMessage vresult = searchMissTest(missTestSearch);
        System.out.println(vresult);
        MissTest missTestDelete = new MissTest();
        deleteMissTest(missTestDelete);
    }

    private void testMissTestResult()
    {
        MissTestResult missTestResult = findMissTestResultById(new Long(1L));
        System.out.println(missTestResult);
        updateMissTestResult(missTestResult);
        MissTestResult missTestResultSave = new MissTestResult();
        saveMissTestResult(missTestResultSave);
        MissTestResult missTestResultSearch = new MissTestResult();
        VResultMessage vresult = searchMissTestResult(missTestResultSearch);
        System.out.println(vresult);
        MissTestResult missTestResultDelete = new MissTestResult();
        deleteMissTestResult(missTestResultDelete);
    }

    private void testMissTodo()
    {
        MissTodo missTodoSearch = new MissTodo();
        Pagging paging = new Pagging();
        paging.setPageNo(2);
        paging.setPageSize(2);
        missTodoSearch.setPagging(paging);
        VResultMessage vresult = searchMissTodo(missTodoSearch);
        System.out.println((new StringBuilder("xx")).append(vresult.getResultListObj()).toString());
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

}
