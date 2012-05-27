// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:11:05 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MissExamAjax.java

package th.co.aoe.makedev.missconsult.exam.ajax;

import java.util.List;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.*;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public class MissExamAjax
{

    public MissExamAjax()
    {
        WebContext ctx = WebContextFactory.get();
        javax.servlet.ServletContext servletContext = ctx.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        missExamService = (MissExamService)wac.getBean("missExamService");
    }

    public Long saveMissExamType(MissExamType transientInstance)
    {
        return null;
    }

    public int updateMissExamType(MissExamType transientInstance)
    {
        return 0;
    }

    public int deleteMissExamType(MissExamType persistentInstance)
    {
        return 0;
    }

    public MissExamType findMissExamTypeById(Long megId)
    {
        return missExamService.findMissExamTypeById(megId);
    }

    public List searchMissExamType(MissExamType persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissExam(MissExam transientInstance)
    {
        return null;
    }

    public int updateMissExam(MissExam transientInstance)
    {
        return 0;
    }

    public int deleteMissExam(MissExam persistentInstance)
    {
        return 0;
    }

    public MissExam findMissExamById(Long megId)
    {
        return missExamService.findMissExamById(megId);
    }

    public List searchMissExam(MissExam persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissExamGroup(MissExamGroup transientInstance)
    {
        return null;
    }

    public int updateMissExamGroup(MissExamGroup transientInstance)
    {
        return 0;
    }

    public int deleteMissExamGroup(MissExamGroup persistentInstance)
    {
        return 0;
    }

    public MissExamGroup findMissExamGroupById(Long megId)
    {
        return missExamService.findMissExamGroupById(megId);
    }

    public List searchMissExamGroup(MissExamGroup persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissAccount(MissAccount transientInstance)
    {
        return null;
    }

    public int updateMissAccount(MissAccount transientInstance)
    {
        return 0;
    }

    public int deleteMissAccount(MissAccount persistentInstance)
    {
        return 0;
    }

    public MissAccount findMissAccountById(Long megId)
    {
        return missExamService.findMissAccountById(megId);
    }

    public List searchMissAccount(MissAccount persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissAccountSeriesMap(MissAccountSeriesMap transientInstance)
    {
        return null;
    }

    public int updateMissAccountSeriesMap(MissAccountSeriesMap transientInstance)
    {
        return 0;
    }

    public int deleteMissAccountSeriesMap(MissAccountSeriesMap persistentInstance)
    {
        return 0;
    }

    public MissAccountSeriesMap findMissAccountSeriesMapById(Long megId)
    {
        return missExamService.findMissAccountSeriesMapById(megId);
    }

    public List searchMissAccountSeriesMap(MissAccountSeriesMap persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissCandidate(MissCandidate transientInstance)
    {
        return null;
    }

    public int updateMissCandidate(MissCandidate transientInstance)
    {
        return 0;
    }

    public int deleteMissCandidate(MissCandidate persistentInstance)
    {
        return 0;
    }

    public MissCandidate findMissCandidateById(Long megId)
    {
        return missExamService.findMissCandidateById(megId);
    }

    public List searchMissCandidate(MissCandidate persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissChoice(MissChoice transientInstance)
    {
        return null;
    }

    public int updateMissChoice(MissChoice transientInstance)
    {
        return 0;
    }

    public int deleteMissChoice(MissChoice persistentInstance)
    {
        return 0;
    }

    public MissChoice findMissChoiceById(Long megId)
    {
        return missExamService.findMissChoiceById(megId);
    }

    public List searchMissChoice(MissChoice persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissEvaluationTemplate(MissEvaluationTemplate transientInstance)
    {
        return null;
    }

    public int updateMissEvaluationTemplate(MissEvaluationTemplate transientInstance)
    {
        return 0;
    }

    public int deleteMissEvaluationTemplate(MissEvaluationTemplate persistentInstance)
    {
        return 0;
    }

    public MissEvaluationTemplate findMissEvaluationTemplateById(Long megId)
    {
        return missExamService.findMissEvaluationTemplateById(megId);
    }

    public List searchMissEvaluationTemplate(MissEvaluationTemplate persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissQuestion(MissQuestion transientInstance)
    {
        return null;
    }

    public int updateMissQuestion(MissQuestion transientInstance)
    {
        return 0;
    }

    public int deleteMissQuestion(MissQuestion persistentInstance)
    {
        return 0;
    }

    public MissQuestion findMissQuestionById(Long megId)
    {
        return missExamService.findMissQuestionById(megId);
    }

    public List searchMissQuestion(MissQuestion persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissSeriesMap(MissSeriesMap transientInstance)
    {
        return null;
    }

    public int updateMissSeriesMap(MissSeriesMap transientInstance)
    {
        return 0;
    }

    public int deleteMissSeriesMap(MissSeriesMap persistentInstance)
    {
        return 0;
    }

    public MissSeriesMap findMissSeriesMapById(Long megId)
    {
        return missExamService.findMissSeriesMapById(megId);
    }

    public List searchMissSeriesMap(MissSeriesMap persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissSery(MissSery transientInstance)
    {
        return null;
    }

    public int updateMissSery(MissSery transientInstance)
    {
        return 0;
    }

    public int deleteMissSery(MissSery persistentInstance)
    {
        return 0;
    }

    public MissSery findMissSeryById(Long megId)
    {
        return missExamService.findMissSeryById(megId);
    }

    public List searchMissSery(MissSery persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissSurveySend(MissSurveySend transientInstance)
    {
        return null;
    }

    public int updateMissSurveySend(MissSurveySend transientInstance)
    {
        return 0;
    }

    public int deleteMissSurveySend(MissSurveySend persistentInstance)
    {
        return 0;
    }

    public MissSurveySend findMissSurveySendById(Long megId)
    {
        return missExamService.findMissSurveySendById(megId);
    }

    public List searchMissSurveySend(MissSurveySend persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissTemplate(MissTemplate transientInstance)
    {
        return null;
    }

    public int updateMissTemplate(MissTemplate transientInstance)
    {
        return 0;
    }

    public int deleteMissTemplate(MissTemplate persistentInstance)
    {
        return 0;
    }

    public MissTemplate findMissTemplateById(Long megId)
    {
        return missExamService.findMissTemplateById(megId);
    }

    public List searchMissTemplate(MissTemplate persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissTest(MissTest transientInstance)
    {
        return null;
    }

    public int updateMissTest(MissTest transientInstance)
    {
        return 0;
    }

    public int deleteMissTest(MissTest persistentInstance)
    {
        return 0;
    }

    public MissTest findMissTestById(Long megId)
    {
        return missExamService.findMissTestById(megId);
    }

    public List searchMissTest(MissTest persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissTestResult(MissTestResult transientInstance)
    {
        return null;
    }

    public int updateMissTestResult(MissTestResult transientInstance)
    {
        return 0;
    }

    public int deleteMissTestResult(MissTestResult persistentInstance)
    {
        return 0;
    }

    public MissTestResult findMissTestResultById(Long megId)
    {
        return missExamService.findMissTestResultById(megId);
    }

    public List searchMissTestResult(MissTestResult persistentInstance, Pagging pagging)
    {
        return null;
    }

    public Long saveMissTodo(MissTodo transientInstance)
    {
        return null;
    }

    public int updateMissTodo(MissTodo transientInstance)
    {
        return 0;
    }

    public int deleteMissTodo(MissTodo persistentInstance)
    {
        return 0;
    }

    public MissTodo findMissTodoById(Long megId)
    {
        return missExamService.findMissTodoById(megId);
    }

    public List searchMissTodo(MissTodo persistentInstance, Pagging pagging)
    {
        return null;
    }

    private MissExamService missExamService;
}
