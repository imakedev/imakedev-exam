package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_EXAM database table.
 * 
 */
@Entity
@Table(name="MISS_EXAM")
public class MissExam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ME_ID")
	private Long meId;

	@Column(name="ME_FIX_ANSWER_ORDER")
	private String meFixAnswerOrder;
	@Lob
	@Column(name="ME_INSTRUCTION")
	private String meInstruction;
	@Lob
	@Column(name="ME_INTRODUCTION")
	private String meIntroduction;

	@Column(name="ME_NAME")
	private String meName;

	@Column(name="ME_TIME_LIMIT")
	private Long meTimeLimit;

	//bi-directional many-to-one association to MissEvaluationTemplate
	/*@OneToMany(mappedBy="missExam")
	private Set<MissEvaluationTemplate> missEvaluationTemplates;
*/
	//bi-directional many-to-one association to MissExamGroup
    @ManyToOne
	@JoinColumn(name="MEG_ID")
	private MissExamGroup missExamGroup;

	//bi-directional many-to-one association to MissExamType
    @ManyToOne
	@JoinColumn(name="MET_ID")
	private MissExamType missExamType;

	/*//bi-directional many-to-one association to MissQuestion
	@OneToMany(mappedBy="missExam")
	private Set<MissQuestion> missQuestions;

	//bi-directional many-to-one association to MissTest
	@OneToMany(mappedBy="missExam")
	private Set<MissTest> missTests;*/

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