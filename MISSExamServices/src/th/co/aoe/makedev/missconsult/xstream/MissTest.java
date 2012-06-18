package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


/**
 * The persistent class for the MISS_TEST database table.
 * 
 */
@XStreamAlias("MissTest")
public class MissTest extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mtestId;

	private String mtestAns;

	private String mtestSet;

	private String mtestStatus;

	@XStreamAlias("missCandidate")
	private MissCandidate missCandidate;

	@XStreamAlias("missChoice")
	private MissChoice missChoice;

	@XStreamAlias("missExam")
	private MissExam missExam;

	@XStreamAlias("missQuestion")
	private MissQuestion missQuestion;

	@XStreamAlias("missSery")
	private MissSery missSery;

	//ext 
	private String userid;
	

	@XStreamImplicit(itemFieldName="missTests")
	private List<MissTest> missTests;
	
    public MissTest() {
    }

	public Long getMtestId() {
		return this.mtestId;
	}

	public void setMtestId(Long mtestId) {
		this.mtestId = mtestId;
	}

	public String getMtestAns() {
		return this.mtestAns;
	}

	public void setMtestAns(String mtestAns) {
		this.mtestAns = mtestAns;
	}

	public String getMtestSet() {
		return this.mtestSet;
	}

	public void setMtestSet(String mtestSet) {
		this.mtestSet = mtestSet;
	}

	public String getMtestStatus() {
		return this.mtestStatus;
	}

	public void setMtestStatus(String mtestStatus) {
		this.mtestStatus = mtestStatus;
	}

	public MissCandidate getMissCandidate() {
		return this.missCandidate;
	}

	public void setMissCandidate(MissCandidate missCandidate) {
		this.missCandidate = missCandidate;
	}
	
	public MissChoice getMissChoice() {
		return this.missChoice;
	}

	public void setMissChoice(MissChoice missChoice) {
		this.missChoice = missChoice;
	}
	
	public MissExam getMissExam() {
		return this.missExam;
	}

	public void setMissExam(MissExam missExam) {
		this.missExam = missExam;
	}
	
	public MissQuestion getMissQuestion() {
		return this.missQuestion;
	}

	public void setMissQuestion(MissQuestion missQuestion) {
		this.missQuestion = missQuestion;
	}
	
	public MissSery getMissSery() {
		return this.missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}

	public List<MissTest> getMissTests() {
		return missTests;
	}

	public void setMissTests(List<MissTest> missTests) {
		this.missTests = missTests;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}