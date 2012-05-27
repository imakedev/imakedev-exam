package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_EXAM database table.
 * 
 */
@XStreamAlias("MissExam")
public class MissExam extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long meId;

	private String meFixAnswerOrder;

	private String meInstruction;

	private String meIntroduction;

	private String meName;

	private Long meTimeLimit;

	@XStreamAlias("missExamGroup")
	private MissExamGroup missExamGroup;

	@XStreamAlias("missExamType")
	private MissExamType missExamType;
	
	//ext 
	private String section;  
	private String meIds;

    public MissExam() {
    }

	public Long getMeId() {
		return this.meId;
	}

	public void setMeId(Long meId) {
		this.meId = meId;
	}

	public String getMeFixAnswerOrder() {
		return this.meFixAnswerOrder;
	}

	public void setMeFixAnswerOrder(String meFixAnswerOrder) {
		this.meFixAnswerOrder = meFixAnswerOrder;
	}

	public String getMeInstruction() {
		return this.meInstruction;
	}

	public void setMeInstruction(String meInstruction) {
		this.meInstruction = meInstruction;
	}

	public String getMeIntroduction() {
		return this.meIntroduction;
	}

	public void setMeIntroduction(String meIntroduction) {
		this.meIntroduction = meIntroduction;
	}

	public String getMeName() {
		return this.meName;
	}

	public void setMeName(String meName) {
		this.meName = meName;
	}

	public Long getMeTimeLimit() {
		return this.meTimeLimit;
	}

	public void setMeTimeLimit(Long meTimeLimit) {
		this.meTimeLimit = meTimeLimit;
	}

/*	public Set<MissEvaluationTemplate> getMissEvaluationTemplates() {
		return this.missEvaluationTemplates;
	}

	public void setMissEvaluationTemplates(Set<MissEvaluationTemplate> missEvaluationTemplates) {
		this.missEvaluationTemplates = missEvaluationTemplates;
	}
	*/
	public MissExamGroup getMissExamGroup() {
		return this.missExamGroup;
	}

	public void setMissExamGroup(MissExamGroup missExamGroup) {
		this.missExamGroup = missExamGroup;
	}
	
	public MissExamType getMissExamType() {
		return this.missExamType;
	}

	public void setMissExamType(MissExamType missExamType) {
		this.missExamType = missExamType;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getMeIds() {
		return meIds;
	}

	public void setMeIds(String meIds) {
		this.meIds = meIds;
	}
	
	/*public Set<MissQuestion> getMissQuestions() {
		return this.missQuestions;
	}

	public void setMissQuestions(Set<MissQuestion> missQuestions) {
		this.missQuestions = missQuestions;
	}
	
	public Set<MissTest> getMissTests() {
		return this.missTests;
	}

	public void setMissTests(Set<MissTest> missTests) {
		this.missTests = missTests;
	}*/
	
}