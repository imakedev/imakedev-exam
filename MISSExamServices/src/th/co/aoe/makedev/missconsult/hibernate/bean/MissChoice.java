package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_CHOICE database table.
 * 
 */
@Entity
@Table(name="MISS_CHOICE")
public class MissChoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissChoicePK id;



	@Column(name="MC_MULTIPLE_CHOOSE")
	private Long mcMultipleChoose;

	@Column(name="MC_NAME")
	private String mcName;
	
	@Column(name="MC_NAME_ENG")
	private String mcNameEng;

	public String getMcNameEng() {
		return mcNameEng;
	}

	public void setMcNameEng(String mcNameEng) {
		this.mcNameEng = mcNameEng;
	}

	@Column(name="MC_SCORE")
	private String mcScore;
	
	/*@Column(name="MC_NO")
	private Long mcNo;*/
	//bi-directional many-to-one association to MissQuestion
    @ManyToOne
	@JoinColumn(name="MQ_ID",insertable=false,updatable=false)
	private MissQuestion missQuestion;

	//bi-directional many-to-one association to MissTest
	/*@OneToMany(mappedBy="missChoice")
	private Set<MissTest> missTests;*/

    public MissChoice() {
    }

	/*public Long getMcId() {
		return this.mcId;
	}

	public void setMcId(Long mcId) {
		this.mcId = mcId;
	}*/


	public MissChoicePK getId() {
		return id;
	}

	public void setId(MissChoicePK id) {
		this.id = id;
	}


	public Long getMcMultipleChoose() {
		return this.mcMultipleChoose;
	}

	public void setMcMultipleChoose(Long mcMultipleChoose) {
		this.mcMultipleChoose = mcMultipleChoose;
	}

	public String getMcName() {
		return this.mcName;
	}

	public void setMcName(String mcName) {
		this.mcName = mcName;
	}

	public String getMcScore() {
		return this.mcScore;
	}

	public void setMcScore(String mcScore) {
		this.mcScore = mcScore;
	}

	public MissQuestion getMissQuestion() {
		return this.missQuestion;
	}

	public void setMissQuestion(MissQuestion missQuestion) {
		this.missQuestion = missQuestion;
	}

	/*public Long getMcNo() {
		return mcNo;
	}

	public void setMcNo(Long mcNo) {
		this.mcNo = mcNo;
	}*/
	
/*	public Set<MissTest> getMissTests() {
		return this.missTests;
	}

	public void setMissTests(Set<MissTest> missTests) {
		this.missTests = missTests;
	}*/
	
}