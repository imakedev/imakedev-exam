package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_CHOICE database table.
 * 
 */
@XStreamAlias("MissChoice")
public class MissChoice extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mcId;

	private String mcLang;

	private Long mcMultipleChoose;

	private String mcName;

	private String mcScore;

	@XStreamAlias("missQuestion")
	private MissQuestion missQuestion;

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