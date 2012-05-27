// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:14:17 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MissExamService.java

package th.co.aoe.makedev.missconsult.exam.service;

import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissChoice;
import th.co.aoe.makedev.missconsult.xstream.MissEvaluationTemplate;
import th.co.aoe.makedev.missconsult.xstream.MissExam;
import th.co.aoe.makedev.missconsult.xstream.MissExamGroup;
import th.co.aoe.makedev.missconsult.xstream.MissExamType;
import th.co.aoe.makedev.missconsult.xstream.MissQuestion;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesMap;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.MissSurveySend;
import th.co.aoe.makedev.missconsult.xstream.MissTemplate;
import th.co.aoe.makedev.missconsult.xstream.MissTest;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;
import th.co.aoe.makedev.missconsult.xstream.MissTodo;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public interface MissExamService
{

    public abstract Long saveMissExamGroup(MissExamGroup missexamgroup);

    public abstract int updateMissExamGroup(MissExamGroup missexamgroup);

    public abstract int deleteMissExamGroup(MissExamGroup missexamgroup);

    public abstract MissExamGroup findMissExamGroupById(Long long1);

    public abstract VResultMessage searchMissExamGroup(MissExamGroup missexamgroup);

    public abstract Long saveMissAccount(MissAccount missaccount);

    public abstract int updateMissAccount(MissAccount missaccount);

    public abstract int deleteMissAccount(MissAccount missaccount, String s);

    public abstract MissAccount findMissAccountById(Long long1);

    public abstract VResultMessage searchMissAccount(MissAccount missaccount);

    public abstract Long saveMissAccountSeriesMap(MissAccountSeriesMap missaccountseriesmap);

    public abstract int updateMissAccountSeriesMap(MissAccountSeriesMap missaccountseriesmap);

    public abstract int deleteMissAccountSeriesMap(MissAccountSeriesMap missaccountseriesmap);

    public abstract MissAccountSeriesMap findMissAccountSeriesMapById(Long long1);

    public abstract VResultMessage searchMissAccountSeriesMap(MissAccountSeriesMap missaccountseriesmap);

    public abstract Long saveMissCandidate(MissCandidate misscandidate);

    public abstract int updateMissCandidate(MissCandidate misscandidate);

    public abstract int deleteMissCandidate(MissCandidate misscandidate, String s);

    public abstract MissCandidate findMissCandidateById(Long long1);

    public abstract VResultMessage searchMissCandidate(MissCandidate misscandidate);

    public abstract Long saveMissChoice(MissChoice misschoice);

    public abstract int updateMissChoice(MissChoice misschoice);

    public abstract int deleteMissChoice(MissChoice misschoice);

    public abstract MissChoice findMissChoiceById(Long long1);

    public abstract VResultMessage searchMissChoice(MissChoice misschoice);

    public abstract Long saveMissEvaluationTemplate(MissEvaluationTemplate missevaluationtemplate);

    public abstract int updateMissEvaluationTemplate(MissEvaluationTemplate missevaluationtemplate);

    public abstract int deleteMissEvaluationTemplate(MissEvaluationTemplate missevaluationtemplate);

    public abstract MissEvaluationTemplate findMissEvaluationTemplateById(Long long1);

    public abstract VResultMessage searchMissEvaluationTemplate(MissEvaluationTemplate missevaluationtemplate);

    public abstract Long saveMissExam(MissExam missexam);

    public abstract int updateMissExam(MissExam missexam);

    public abstract int deleteMissExam(MissExam missexam, String s);

    public abstract MissExam findMissExamById(Long long1);

    public abstract VResultMessage searchMissExam(MissExam missexam);

    public abstract List listMissExam();

    public abstract Long saveMissExamType(MissExamType missexamtype);

    public abstract int updateMissExamType(MissExamType missexamtype);

    public abstract int deleteMissExamType(MissExamType missexamtype);

    public abstract MissExamType findMissExamTypeById(Long long1);

    public abstract VResultMessage searchMissExamType(MissExamType missexamtype);

    public abstract Long saveMissQuestion(MissQuestion missquestion);

    public abstract int updateMissQuestion(MissQuestion missquestion);

    public abstract int deleteMissQuestion(MissQuestion missquestion);

    public abstract MissQuestion findMissQuestionById(Long long1);

    public abstract VResultMessage searchMissQuestion(MissQuestion missquestion);
    
    public abstract List listMissQuestions(Long meId);

    public abstract Long saveMissSeriesMap(MissSeriesMap missseriesmap);

    public abstract int updateMissSeriesMap(MissSeriesMap missseriesmap);

    public abstract int deleteMissSeriesMap(MissSeriesMap missseriesmap);

    public abstract MissSeriesMap findMissSeriesMapById(Long long1);

    public abstract VResultMessage searchMissSeriesMap(MissSeriesMap missseriesmap);

    public abstract Long saveMissSery(MissSery misssery);

    public abstract int updateMissSery(MissSery misssery);

    public abstract int deleteMissSery(MissSery misssery, String s);

    public abstract MissSery findMissSeryById(Long long1);

    public abstract VResultMessage searchMissSery(MissSery misssery);

    public abstract List listMissSery();

    public abstract Long saveMissSurveySend(MissSurveySend misssurveysend);

    public abstract int updateMissSurveySend(MissSurveySend misssurveysend);

    public abstract int deleteMissSurveySend(MissSurveySend misssurveysend);

    public abstract MissSurveySend findMissSurveySendById(Long long1);

    public abstract VResultMessage searchMissSurveySend(MissSurveySend misssurveysend);

    public abstract Long saveMissTemplate(MissTemplate misstemplate);

    public abstract int updateMissTemplate(MissTemplate misstemplate);

    public abstract int deleteMissTemplate(MissTemplate misstemplate);

    public abstract MissTemplate findMissTemplateById(Long long1);

    public abstract VResultMessage searchMissTemplate(MissTemplate misstemplate);

    public abstract Long saveMissTest(MissTest misstest);

    public abstract int updateMissTest(MissTest misstest);

    public abstract int deleteMissTest(MissTest misstest);

    public abstract MissTest findMissTestById(Long long1);

    public abstract VResultMessage searchMissTest(MissTest misstest);

    public abstract Long saveMissTestResult(MissTestResult misstestresult);

    public abstract int updateMissTestResult(MissTestResult misstestresult);

    public abstract int deleteMissTestResult(MissTestResult misstestresult);

    public abstract MissTestResult findMissTestResultById(Long long1);

    public abstract VResultMessage searchMissTestResult(MissTestResult misstestresult);

    public abstract Long saveMissTodo(MissTodo misstodo);

    public abstract int updateMissTodo(MissTodo misstodo);

    public abstract int deleteMissTodo(MissTodo misstodo);

    public abstract MissTodo findMissTodoById(Long long1);

    public abstract VResultMessage searchMissTodo(MissTodo misstodo);
}
