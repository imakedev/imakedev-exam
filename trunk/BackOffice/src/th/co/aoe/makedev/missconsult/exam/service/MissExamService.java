// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:14:17 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MissExamService.java

package th.co.aoe.makedev.missconsult.exam.service;

import java.util.List;

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

public interface MissExamService {
	// public findByUsername;
	public abstract MissCandidate findMissCandidateByName(String name);
	//for check answer
	 public abstract List<MissTest> findMissTest(MissTest missTest);
	public abstract Long saveMissExamGroup(MissExamGroup missexamgroup);

	public abstract int updateMissExamGroup(MissExamGroup missexamgroup);

	public abstract int deleteMissExamGroup(MissExamGroup missexamgroup);

	public abstract MissExamGroup findMissExamGroupById(Long long1);

	public abstract VResultMessage searchMissExamGroup(
			MissExamGroup missexamgroup);

	public abstract Long saveMissAccount(MissAccount missaccount);

	public abstract int updateMissAccount(MissAccount missaccount);

	public abstract int updateMissAccountLogo(MissAccount transientInstance);

	public abstract int deleteMissAccount(MissAccount missaccount, String s);

	public abstract MissAccount findMissAccountById(Long long1);

	public abstract VResultMessage searchMissAccount(MissAccount missaccount);

	public abstract MissAccount refillMissAccount(MissAccount missaccount);

	public abstract Long saveMissAccountSeriesMap(
			MissAccountSeriesMap missaccountseriesmap);

	public abstract int updateMissAccountSeriesMap(
			MissAccountSeriesMap missaccountseriesmap);

	public abstract int deleteMissAccountSeriesMap(
			MissAccountSeriesMap missaccountseriesmap);

	public abstract MissAccountSeriesMap findMissAccountSeriesMapById(Long long1);

	@SuppressWarnings("rawtypes")
	public abstract List findMissAccountSeriesMapByMaId(
			Long maId);
	@SuppressWarnings("rawtypes")
	public abstract List findMissAccountSeriesMapByRole
			(Long maId,Long rcId); 

	public abstract VResultMessage searchMissAccountSeriesMap(
			MissAccountSeriesMap missaccountseriesmap);

	public abstract MissCandidate saveMissCandidate(MissCandidate misscandidate);

	public abstract int updateMissCandidate(MissCandidate misscandidate);

	public abstract int updateMissCandidatePhoto(MissCandidate transientInstance);

	public abstract int deleteMissCandidate(MissCandidate misscandidate,
			String s);

	public abstract MissCandidate findMissCandidateById(Long long1);
	
	public abstract MissCandidate findMissCandidateByCitizendIdAndEmail(
			String citizendId, String email);
	

	public abstract VResultMessage searchMissCandidate(
			MissCandidate misscandidate);

	public abstract Long saveMissChoice(MissChoice misschoice);

	public abstract int updateMissChoice(MissChoice misschoice);

	public abstract int deleteMissChoice(MissChoice misschoice);

	public abstract MissChoice findMissChoiceById(Long long1);

	public abstract VResultMessage searchMissChoice(MissChoice misschoice);

	public abstract Long saveMissEvaluationTemplate(
			MissEvaluationTemplate missevaluationtemplate);

	public abstract int updateMissEvaluationTemplate(
			MissEvaluationTemplate missevaluationtemplate);

	public abstract int deleteMissEvaluationTemplate(
			MissEvaluationTemplate missevaluationtemplate);

	public abstract MissEvaluationTemplate findMissEvaluationTemplateById(
			Long long1);

	public abstract VResultMessage searchMissEvaluationTemplate(
			MissEvaluationTemplate missevaluationtemplate);

	public abstract Long saveMissExam(MissExam missexam);

	public abstract int updateMissExam(MissExam missexam);

	public abstract int deleteMissExam(MissExam missexam, String s);

	public abstract MissExam findMissExamById(Long long1);

	public abstract VResultMessage searchMissExam(MissExam missexam);

	public abstract int copyMissExam(MissExam missexam);

	public abstract int createEmptyMissExam(MissExam missexam);
	@SuppressWarnings("rawtypes")
	public abstract List listMissExam();

	public abstract Long saveMissExamType(MissExamType missexamtype);

	public abstract int updateMissExamType(MissExamType missexamtype);

	public abstract int deleteMissExamType(MissExamType missexamtype);

	public abstract MissExamType findMissExamTypeById(Long long1);

	public abstract VResultMessage searchMissExamType(MissExamType missexamtype);

	public abstract Long saveMissQuestion(MissQuestion missquestion);

	public abstract int updateMissQuestion(MissQuestion missquestion,
			String service);

	public abstract int deleteMissQuestion(MissQuestion missquestion);  
	public abstract int setOrderItems(Long meId,String[] mqNo_array,String mqId_array[]);

	public abstract MissQuestion findMissQuestionById(Long long1);

	public abstract VResultMessage searchMissQuestion(MissQuestion missquestion);
	@SuppressWarnings("rawtypes")
	public abstract List listMissQuestions(Long meId);
	@SuppressWarnings("rawtypes")
	public abstract List listMissQuestionsWithChoices(Long meId);

	public abstract Long saveMissSeriesMap(MissSeriesMap missseriesmap);

	public abstract int updateMissSeriesMap(MissSeriesMap missseriesmap);

	public abstract int deleteMissSeriesMap(MissSeriesMap missseriesmap);

	public abstract MissSeriesMap findMissSeriesMapById(Long long1);

	public abstract VResultMessage searchMissSeriesMap(
			MissSeriesMap missseriesmap);

	public abstract Long saveMissSery(MissSery misssery);

	public abstract int updateMissSery(MissSery misssery);

	public abstract int deleteMissSery(MissSery misssery, String s);

	public abstract MissSery findMissSeryById(Long long1);

	public abstract VResultMessage searchMissSery(MissSery misssery);
	@SuppressWarnings("rawtypes")
	public abstract List listMissSery(Long msType);

	public abstract Long saveMissSurveySend(MissSurveySend misssurveysend);

	public abstract int updateMissSurveySend(MissSurveySend misssurveysend);

	public abstract int deleteMissSurveySend(MissSurveySend misssurveysend);

	public abstract MissSurveySend findMissSurveySendById(Long long1);

	public abstract VResultMessage searchMissSurveySend(
			MissSurveySend misssurveysend);

	public abstract Long saveMissTemplate(MissTemplate misstemplate);

	public abstract int updateMissTemplate(MissTemplate misstemplate);

	public abstract int deleteMissTemplate(MissTemplate misstemplate);

	public abstract MissTemplate findMissTemplateById(Long long1);

	public abstract VResultMessage searchMissTemplate(MissTemplate misstemplate);

	public abstract Long saveMissTest(MissTest misstest);
	public abstract Long saveMissTestPaper(MissTest misstest);
	
	public abstract int updateMissTest(MissTest misstest);

	public abstract int deleteMissTest(MissTest misstest);

	public abstract MissTest findMissTestById(Long long1);

	public abstract VResultMessage searchMissTest(MissTest misstest);

	public abstract Long saveMissTestResult(MissTestResult misstestresult);

	public abstract int updateMissTestResult(MissTestResult misstestresult);

	public abstract int deleteMissTestResult(MissTestResult misstestresult);

	public abstract MissTestResult findMissTestResultById(Long long1);

	public abstract VResultMessage searchMissTestResult(
			MissTestResult misstestresult);

	public abstract Long saveMissTodo(MissTodo misstodo);

	public abstract int updateMissTodo(MissTodo misstodo);

	public abstract int deleteMissTodo(MissTodo misstodo);

	public abstract MissTodo findMissTodoById(Long long1);

	public abstract VResultMessage searchMissTodo(MissTodo misstodo);

	// public abstract int updateMissTodoStatus(MissTodo misstodo);

	public abstract Long saveMissAttach(MissAttach missAttach);

	public abstract int updateMissAttach(MissAttach missAttach);

	public abstract int deleteMissAttach(MissAttach missAttach);

	public abstract MissAttach findMissAttachById(String matModule,
			Long matRef, String hotlink);

	public abstract VResultMessage searchMissAttach(MissAttach missAttach);

	public abstract Long saveMissContact(MissContact missContact);

	public abstract int updateMissContact(MissContact missContact);

	public abstract int updateMissContactPhoto(MissContact transientInstance);

	public abstract int deleteMissContact(MissContact missContact,
			String service);

	public abstract MissContact findMissContactById(Long long1);

	public abstract MissContact findMissContactByUsername(String username);
	
	public abstract int countMissContactByUsername(String username,Long id); 

	public abstract VResultMessage searchMissContact(MissContact missContact);
	@SuppressWarnings("rawtypes")
	public abstract List listContacts(Long long1, String mcontactType);

	public abstract Long saveMissManual(MissManual missManual);

	public abstract int updateMissManual(MissManual missManual);

	public abstract int deleteMissManual(MissManual missManual, String service);

	public abstract MissManual findMissManualById(Long long1);

	public abstract VResultMessage searchMissManual(MissManual missManual);
	
	public abstract Long saveMissDoc(MissDoc missDoc);
	public abstract int updateMissDoc(MissDoc missDoc) ;
	public abstract int deleteMissDoc(MissDoc missDoc) ;	
	public abstract MissDoc findMissDocById(Long mdId)  ;
	public abstract  VResultMessage searchMissDoc(MissDoc missDoc)  ;
	
	

	public abstract int updateMissSeriesAttach(MissSeriesAttach missSeriesAttach);

	public abstract int deleteMissSeriesAttach(MissSeriesAttach missSeriesAttach);

	public abstract MissSeriesAttach findMissSeriesAttachSearch(
			String matModule, Long matRef1, Long matRef2, String hotlink);

	// Add
	/*
	 * Service.java RService.java RService.java
	 */
	public abstract Long saveRoleContact(RoleContact roleContact);

	public abstract int updateRoleContact(RoleContact roleContact);

	public abstract int deleteRoleContact(RoleContact roleContact,
			String service);

	public abstract RoleContact findRoleContactById(Long rcId);

	public abstract VResultMessage searchRoleContact(RoleContact roleContact);
	@SuppressWarnings("rawtypes")
	public abstract List listRoleContactBymaId(Long maId);

	public abstract Long saveRoleMapping(RoleMapping roleMapping);

	public abstract int updateRoleMapping(RoleMapping roleMapping);

	public abstract int deleteRoleMapping(RoleMapping roleMapping,
			String service);

	public abstract RoleMapping findRoleMappingById(Long rcId);

	public abstract VResultMessage searchRoleMapping(RoleMapping roleContact);

	@SuppressWarnings("rawtypes")
	public abstract List listRoleMappingByrcId(Long rcId);
	
	//Start RoleSeriesMapping
	public abstract Long saveRoleSeriesMapping(RoleSeriesMapping roleSeriesMapping);

	public abstract int updateRoleSeriesMapping(RoleSeriesMapping roleSeriesMapping);

	public abstract int deleteRoleSeriesMapping(RoleSeriesMapping roleSeriesMapping,
			String service);

	public abstract RoleSeriesMapping findRoleSeriesMappingById(Long rcId);

	public abstract VResultMessage searchRoleSeriesMapping(RoleSeriesMapping roleContact);

	@SuppressWarnings("rawtypes")
	public abstract List listRoleSeriesMappingByrcId(Long rcId);
	//End RoleSeriesMapping

	public abstract Long saveRoleType(RoleType roleType);

	public abstract int updateRoleType(RoleType roleType);

	public abstract int deleteRoleType(RoleType roleType, String service);

	public abstract RoleType findRoleTypeById(Long rcId);

	public abstract VResultMessage searchRoleType(RoleType roleContact);

	@SuppressWarnings("rawtypes")
	public abstract List listRoleTypeByRcId(Long rcId);

	@SuppressWarnings("rawtypes")
	public abstract List listRoleTypes(Long maId);

	public abstract int getQuestionOrdered(Long meId);

	public abstract int updateStatusMissTestResult(Long mtrId, String column,
			String value);
	public abstract int updateStatusMissTestResult(String mtrIds, String column, String value);
	
	@SuppressWarnings("rawtypes")
	public abstract List listMissTheme(MissTheme missTheme);

	public abstract MissTheme findMissThemeById(Long maId, Long mtId);

	public abstract List<MissCareerMaster> listMissCareerMaster(Long maId);

	public abstract List<MissIndustryMaster> listMissIndustryMaster();
	
	public abstract List<MissPositionMaster> listMissPositionMaster();
	
	public abstract List<MissDepartmentMaster> listMissDepartmentMaster(); 

	// Report management
	public abstract int saveMissSystemUse(MissSystemUse transientInstance);

	@SuppressWarnings("rawtypes")
	public abstract List searchMissSystemUse(MissSystemUse persistentInstance);

	public Long saveMissSeryUse(MissSeryUse transientInstance);

	@SuppressWarnings("rawtypes")
	public abstract List searchMissSeryUse(MissSeryUse persistentInstance);

	public abstract Long saveMissSeryProblem(MissSeryProblem transientInstance);

	@SuppressWarnings("rawtypes")
	public abstract List searchMissSeryProblem(
			MissSeryProblem persistentInstance);

	public abstract Long saveMissSeryOrder(MissSeryOrder transientInstance);

	@SuppressWarnings("rawtypes")
	public abstract List searchMissSeryOrder(MissSeryOrder persistentInstance);

	public abstract Long saveMissReactiveLog(MissReactiveLog transientInstance);

	@SuppressWarnings("rawtypes")
	public abstract List searchMissReactiveLog(
			MissReactiveLog persistentInstance);

	@SuppressWarnings("rawtypes")
	public abstract List searchMissAccountGroup(
			MissAccountGroup persistentInstance);

	@SuppressWarnings("rawtypes")
	public abstract List searchMissAccountMapping(
			MissAccountMapping persistentInstance);
	
	public abstract  List<List<String>> sendSurvey(MissSurveySend misssurveysend);
	
	 public abstract VResultMessage exportMissCandidate(MissCandidate missCandidate);
	    
	
//	public abstract ProductReport findProductReport(String mode,String year);
	public abstract EPTNormReport findEPTNormReport(EPTNormReport eptNormReport);
	public abstract EPTNormReport findCompanies(EPTNormReport eptNormReport); 
	public abstract CustomerReport findCustomerReport(CustomerReport customerReport)  ;
	public abstract CustomerReport findGroups(CustomerReport customerReport); 
	public abstract ProductReport findProductReport(ProductReport productReport);
	public abstract ServiceReport findServiceReport(ServiceReport serviceReport);
	public abstract ConsultantReport findConsultantReport(ConsultantReport consultantReport);
	public abstract ConsultantReport findSales(ConsultantReport consultantReport);
	public abstract String getEmailFromMissTodo(MissTodo missTodo);
	
	public abstract Long saveMissReportAttach(MissReportAttach missReportAttach);

	public abstract int updateMissReportAttach(MissReportAttach missReportAttach);

	//public abstract int deleteMissReportAttach(MissReportAttach missReportAttach);
	public abstract MissReportAttach findMissReportAttachById(Long msId,
			Long msOrder,String mraLang, String hotlink) ;
	 
	public abstract VResultMessage searchMissReportAttach(MissReportAttach missReportAttach);
	public abstract VResultMessage getTemplateMissReportAttach(MissReportAttach missReportAttach);
	public abstract VResultMessage deleteTemplateMissReportAttach(MissReportAttach missReportAttach);
	public abstract int updateReportNameMissReportAttach(MissReportAttach missReportAttach); 
	@SuppressWarnings("rawtypes")
	public abstract List getTemplateMissReportAttachForRole(MissReportAttach missReportAttach);
	
	public List listRoleSeriesReportMappingByrcId(Long rcId,Long msId); 
	public int updateRoleSeriesReportMapping(Long rcId, Long msId,String[] msOrders);
	public  List getMissSeriesParticipantsMap(Long msId,int numberParticipant)  ;
	public  List listMissSeriesParticipantsMap(Long msId)  ;
	
	public  int deleteMissSeriesParticipantsMap(Long msId,int mspmOrder)  ;
	public int updateMissSeriesParticipantsMap(Long msId, Integer[] mspmOrder,Integer[] mspmGroupAmount,String[] mspmGroupName) ;

	public abstract int saveOrUpdateMissTestResult(MissTestResult missTestResult);
	public abstract int startMissTestResult(MissTestResult missTestResult); 
	public abstract int processMissTestResult(MissTestResult missTestResult);
	public abstract int saveOrUpdateMissTodo(MissTodo missTodo);
	
}
