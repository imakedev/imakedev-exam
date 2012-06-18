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
	
	private String[] mcIds;

	@XStreamAlias("missQuestion")
	private MissQuestion missQuestion;
	
	//ext
	private String choiceSelect;
	private String valueSelect;
	/*@XStreamImplicit
	@XStreamAlias("missChoices")
	private List<MissChoice> missChoices;*/
 
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

	public String[] getMcIds() {
		return mcIds;
	}

	public void setMcIds(String[] mcIds) {
		this.mcIds = mcIds;
	}

	public String getChoiceSelect() {
		return choiceSelect;
	}

	public void setChoiceSelect(String choiceSelect) {
		this.choiceSelect = choiceSelect;
	}

	public String getValueSelect() {
		return valueSelect;
	}

	public void setValueSelect(String valueSelect) {
		this.valueSelect = valueSelect;
	}

	/*public List<MissChoice> getMissChoices() {
		return missChoices;
	}

	public void setMissChoices(List<MissChoice> missChoices) {
		this.missChoices = missChoices;
	}*/
	
/*	public Set<MissTest> getMissTests() {
		return this.missTests;
	}

	public void setMissTests(Set<MissTest> missTests) {
		this.missTests = missTests;
	}*/
	
}