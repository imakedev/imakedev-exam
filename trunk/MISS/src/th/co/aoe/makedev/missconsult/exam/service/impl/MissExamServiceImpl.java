package th.co.aoe.makedev.missconsult.exam.service.impl;

import java.util.List;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissAttach;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissExam;
import th.co.aoe.makedev.missconsult.xstream.MissManual;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.xstream.MissTest;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissExamServiceImpl extends PostCommon implements MissExamService {

	public MissCandidate findMissCandidateByName(String name) {
		MissCandidate missCandidate = new MissCandidate();
		missCandidate.setMcaUsername(name);
		missCandidate
				.setServiceName(ServiceConstant.MISS_CANDIDATE_FIND_BY_NAME);
		VResultMessage resultMessage = postMessage(missCandidate, missCandidate
				.getClass().getName(), "missCandidate", true);
		return (MissCandidate) resultMessage.getResultListObj().get(0);
	}

	public int getMissExamInfo(MissCandidate missCandidate) {
		missCandidate.setServiceName(ServiceConstant.MISS_CANDIDATE_UPDATE);
		VResultMessage resultMessage = postMessage(missCandidate, missCandidate
				.getClass().getName(), "missCandidate", true);
		missCandidate = (MissCandidate) resultMessage.getResultListObj().get(0);
		return missCandidate.getUpdateRecord().intValue();
	}

	@Override
	public int updateMissCandidate(MissCandidate missCandidate) {
		// TODO Auto-generated method stub
		missCandidate.setServiceName(ServiceConstant.MISS_CANDIDATE_UPDATE);
		VResultMessage resultMessage = postMessage(missCandidate, missCandidate
				.getClass().getName(), "missCandidate", true);
		missCandidate = (MissCandidate) resultMessage.getResultListObj().get(0);
		return missCandidate.getUpdateRecord().intValue();
	}

	@Override
	public MissExam getMissExamInfo(MissExam missExam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveOrUpdateMissTest(MissTest missTest) {
		// TODO Auto-generated method stub
		missTest.setServiceName(ServiceConstant.MISS_TEST_SAVE);
		VResultMessage resultMessage = postMessage(missTest, missTest
				.getClass().getName(), "missTest", true);
		missTest = (MissTest) resultMessage.getResultListObj().get(0);
		return missTest.getUpdateRecord().intValue();
	}

	@Override
	public List<MissTest> findMissTest(MissTest missTest) {
		// TODO Auto-generated method stub
		missTest.setServiceName(ServiceConstant.MISS_TEST_FIND_ANSWERED);
		VResultMessage resultMessage = postMessage(missTest, missTest
				.getClass().getName(), "missTest", true);
		List<MissTest> missTests=null;
		if(resultMessage!=null && resultMessage.getResultListObj()!=null && resultMessage.getResultListObj().size()>0){
			missTests=resultMessage.getResultListObj();
		} 
		return missTests;
	}
	
	@Override
	public int saveOrUpdateMissTestResult(MissTestResult missTestResult) {
		// TODO Auto-generated method stub
		missTestResult.setServiceName(ServiceConstant.MISS_TEST_RESULT_SAVE);
		VResultMessage resultMessage = postMessage(missTestResult, missTestResult
				.getClass().getName(), "missTestResult", true);
		missTestResult = (MissTestResult) resultMessage.getResultListObj().get(0);
		return missTestResult.getUpdateRecord().intValue();
	}

	public MissAccount findMissAccountById(Long megId) {
		MissAccount missAccount = new MissAccount();
		missAccount.setMaId(megId);
		missAccount.setServiceName("findMissAccountById");
		VResultMessage resultMessage = postMessage(missAccount, missAccount
				.getClass().getName(), "missAccount", true);
		return (MissAccount) resultMessage.getResultListObj().get(0);
	}

	public MissCandidate findMissCandidateById(Long megId) {
		MissCandidate missCandidate = new MissCandidate();
		missCandidate.setMcaId(megId);
		missCandidate.setServiceName("findMissCandidateById");
		VResultMessage resultMessage = postMessage(missCandidate, missCandidate
				.getClass().getName(), "missCandidate", true);
		return (MissCandidate) resultMessage.getResultListObj().get(0);
	}

	@Override
	public MissContact findMissContactById(Long long1) {
		// TODO Auto-generated method stub
		MissContact missContact = new MissContact();
		missContact.setMcontactId(long1);
		missContact.setServiceName(ServiceConstant.MISS_CONTACT_FIND_BY_ID);
		VResultMessage resultMessage = postMessage(missContact, missContact
				.getClass().getName(), "missContact", true);
		return (MissContact) resultMessage.getResultListObj().get(0);
	}

	@Override
	public MissManual findMissManualById(Long long1) {
		// TODO Auto-generated method stub
		MissManual missManual = new MissManual();
		missManual.setMmId(long1);
		missManual.setServiceName(ServiceConstant.MISS_MANUAL_FIND_BY_ID);
		VResultMessage resultMessage = postMessage(missManual, missManual
				.getClass().getName(), "missManual", true);
		if (resultMessage != null && resultMessage.getResultListObj() != null
				&& resultMessage.getResultListObj().size() > 0)
			return (MissManual) resultMessage.getResultListObj().get(0);
		return null;
	}

	public MissAttach findMissAttachById(String matModule, Long matRef,
			String hotlink) {
		MissAttach missAttach = new MissAttach();
		missAttach.setMatModule(matModule);
		missAttach.setMatHotlink(hotlink);
		missAttach.setMatRef(matRef);
		missAttach.setServiceName("findMissAttachById");
		VResultMessage resultMessage = postMessage(missAttach, missAttach
				.getClass().getName(), "missAttach", true);
		return (MissAttach) resultMessage.getResultListObj().get(0);
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
		missSeriesAttach
				.setServiceName(ServiceConstant.MISS_SERIES_ATTACH_SEARCH);
		VResultMessage resultMessage = postMessage(missSeriesAttach,
				missSeriesAttach.getClass().getName(), "missSeriesAttach", true);
		if (resultMessage != null && resultMessage.getResultListObj() != null
				&& resultMessage.getResultListObj().size() > 0)
			return (MissSeriesAttach) resultMessage.getResultListObj().get(0);
		else
			return null;
	}

	@Override
	public int startMissTestResult(MissTestResult missTestResult) {
		// TODO Auto-generated method stub 
		missTestResult.setServiceName(ServiceConstant.MISS_TEST_RESULT_START);
		VResultMessage resultMessage = postMessage(missTestResult, missTestResult
				.getClass().getName(), "missTestResult", true);
		missTestResult = (MissTestResult) resultMessage.getResultListObj().get(0);
		return missTestResult.getUpdateRecord().intValue();
	}

}
