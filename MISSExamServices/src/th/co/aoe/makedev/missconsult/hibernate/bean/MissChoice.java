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
 * The persistent class for the MISS_CHOICE database table.
 * 
 */
@Entity
@Table(name="MISS_CHOICE")
public class MissChoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MC_ID")
	private Long mcId;

	@Column(name="MC_LANG")
	private String mcLang;

	@Column(name="MC_MULTIPLE_CHOOSE")
	private Long mcMultipleChoose;

	@Column(name="MC_NAME")
	private String mcName;

	@Column(name="MC_SCORE")
	private String mcScore;

	//bi-directional many-to-one association to MissQuestion
    @ManyToOne
	@JoinColumn(name="MQ_ID")
	private MissQuestion missQuestion;

	//bi-directional many-to-one association to MissTest
	/*@OneToMany(mappedBy="missChoice")
	private Set<MissTest> missTests;*/

    public MissChoice() {
    }

	public Long getMcId() {
		return this.mcId;
	}

	public void setMcId(Long mcId) {
		this.mcId = mcId;
	}

	public String getMcLang() {
		return this.mcLang;
	}

	public void setMcLang(String mcLang) {
		this.mcLang = mcLang;
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
	
/*	public Set<MissTest> getMissTests() {
		return this.missTests;
	}

	public void setMissTests(Set<MissTest> missTests) {
		this.missTests = missTests;
	}*/
	
}