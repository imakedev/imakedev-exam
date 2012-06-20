package th.co.aoe.makedev.missconsult.exam.service;

import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissAttach;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissExam;
import th.co.aoe.makedev.missconsult.xstream.MissManual;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.xstream.MissTest;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;

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
	
	
	
	// File
	 public abstract MissAccount findMissAccountById(Long long1);
	 
	 public abstract MissCandidate findMissCandidateById(Long long1);
	 public abstract MissContact findMissContactById(Long long1);
	 public abstract MissManual findMissManualById(Long long1);
	 public abstract MissAttach findMissAttachById(String matModule,Long matRef,String hotlink);
	 public abstract MissSeriesAttach findMissSeriesAttachSearch(String matModule,Long matRef1,Long matRef2,String hotlink);
}
