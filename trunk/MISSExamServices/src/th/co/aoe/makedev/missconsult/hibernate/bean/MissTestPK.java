package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The primary key class for the MISS_SERIES_MAP database table.
 * 
 */
@Embeddable
public class MissTestPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to MissCandidate
    @ManyToOne
	@JoinColumn(name="MCA_ID")
	private MissCandidate missCandidate;

	//bi-directional many-to-one association to MissChoice
   /* @ManyToOne
	@JoinColumn(name="MC_ID")
	private MissChoice missChoice;*/

	//bi-directional many-to-one association to MissExam
    @ManyToOne
	@JoinColumn(name="ME_ID")
	private MissExam missExam;

	//bi-directional many-to-one association to MissQuestion
    @ManyToOne
	@JoinColumn(name="MQ_ID",insertable=false,updatable=false)
	private MissQuestion missQuestion;

	@Column(name="MC_NO")
	private Long mcNo;
  /*  @ManyToOne
   	@JoinColumn(name="MQ_ID")
   	private MissQuestion missQuestion;*/

	//bi-directional many-to-one association to MissSery
   @ManyToOne
	@JoinColumn(name="MS_ID")
	private MissSery missSery;
   /*  
    @ManyToOne
  		@JoinColumns({
  			@JoinColumn(name="MC_NO",referencedColumnName="MC_NO"),
  			@JoinColumn(name="MQ_ID",referencedColumnName="MQ_ID")
  			})
  		private MissChoice missChoice;*/


	public MissCandidate getMissCandidate() {
		return missCandidate;
	}

	public void setMissCandidate(MissCandidate missCandidate) {
		this.missCandidate = missCandidate;
	}

/*	public MissChoice getMissChoice() {
		return missChoice;
	}

	public void setMissChoice(MissChoice missChoice) {
		this.missChoice = missChoice;
	}*/

	public MissExam getMissExam() {
		return missExam;
	}

	public Long getMcNo() {
		return mcNo;
	}

	public void setMcNo(Long mcNo) {
		this.mcNo = mcNo;
	}

	public void setMissExam(MissExam missExam) {
		this.missExam = missExam;
	}

	public MissQuestion getMissQuestion() {
		return missQuestion;
	}

	public void setMissQuestion(MissQuestion missQuestion) {
		this.missQuestion = missQuestion;
	}

	public MissSery getMissSery() {
		return missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}

}
