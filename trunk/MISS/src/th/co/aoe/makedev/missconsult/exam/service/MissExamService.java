package th.co.aoe.makedev.missconsult.exam.service;

import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissAttach;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissCareerMaster;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster;
import th.co.aoe.makedev.missconsult.xstream.MissExam;
import th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster;
import th.co.aoe.makedev.missconsult.xstream.MissManual;
import th.co.aoe.makedev.missconsult.xstream.MissPositionMaster;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.xstream.MissSeryProblem;
import th.co.aoe.makedev.missconsult.xstream.MissSystemUse;
import th.co.aoe.makedev.missconsult.xstream.MissTest;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;
import th.co.aoe.makedev.missconsult.xstream.MissTodo;

public interface MissExamService {
	public abstract MissCandidate findMissCandidateByName(String name);
	public abstract int updateMissCandidate(MissCandidate missCandidate);
	public abstract MissExam getMissExamInfo(MissExam missExam);
	
	//for save MissTest
	public abstract int saveOrUpdateMissTest(MissTest missTest);
	
	
	
	//for check answer
	public abstract List<MissTest> findMissTest(MissTest missTest);
	
	public abstract int saveOrUpdateMissTestResult(MissTestResult missTestResult);
	public abstract int startMissTestResult(MissTestResult missTestResult);
	public abstract int checkMissTestResult(MissTestResult missTestResult);	
	public abstract int processMissTestResult(MissTestResult missTestResult);
	
	// to do list
	public abstract int saveOrUpdateMissTodo(MissTodo missTodo);
	
	// File
	 public abstract MissAccount findMissAccountById(Long long1);
	 
	 public abstract MissCandidate findMissCandidateById(Long long1);
	 public abstract MissContact findMissContactById(Long long1);
	 public abstract MissManual findMissManualById(Long long1);
	 public abstract MissAttach findMissAttachById(String matModule,Long matRef,String hotlink);
	 public abstract MissSeriesAttach findMissSeriesAttachSearch(String matModule,Long matRef1,Long matRef2,String hotlink);
	 
	 public abstract List<MissCareerMaster> listMissCareerMaster(Long maId) ;
	 public abstract List<MissIndustryMaster> listMissIndustryMaster() ;
	 public abstract int saveMissSeryProblem(MissSeryProblem transientInstance);
	 public abstract int updateTimeOut (MissTestResult missTestResult);
	 
	 public abstract int saveMissSystemUse(MissSystemUse missSystemUse);
	 public abstract MissCandidate findMissCandidateByCitizendIdAndEmail(String citizendId,String email);
	 
		public abstract List<MissPositionMaster> listMissPositionMaster();
		
		public abstract List<MissDepartmentMaster> listMissDepartmentMaster(); 
}
