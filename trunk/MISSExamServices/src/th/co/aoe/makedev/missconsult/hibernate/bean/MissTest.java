package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_TEST database table.
 * 
 */
@Entity
@Table(name="MISS_TEST")
public class MissTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MTEST_ID")
	private Long mtestId;

	@Column(name="MTEST_ANS")
	private String mtestAns;

	@Column(name="MTEST_SET")
	private String mtestSet;

	@Column(name="MTEST_STATUS")
	private String mtestStatus;

	//bi-directional many-to-one association to MissCandidate
    @ManyToOne
	@JoinColumn(name="MCA_ID")
	private MissCandidate missCandidate;

	//bi-directional many-to-one association to MissChoice
    @ManyToOne
	@JoinColumn(name="MC_ID")
	private MissChoice missChoice;

	//bi-directional many-to-one association to MissExam
    @ManyToOne
	@JoinColumn(name="ME_ID")
	private MissExam missExam;

	//bi-directional many-to-one association to MissQuestion
    @ManyToOne
	@JoinColumn(name="MQ_ID")
	private MissQuestion missQuestion;

	//bi-directional many-to-one association to MissSery
    @ManyToOne
	@JoinColumn(name="MS_ID")
	private MissSery missSery;

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
	
}