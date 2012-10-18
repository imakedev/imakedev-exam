package th.co.aoe.makedev.missconsult.domain;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the MISS_EPT_EVAL_BEHAVIORAL_GROUP database table.
 * 
 */

public class MissEptEvalBehavioralGroup implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private Long meebgId;

	private String meebgLang;
	
	private String meebgGroup;

	private List<MissEptEvalBehavioralValue> missEptEvalBehavioralValues;
	public MissEptEvalBehavioralGroup() {
	}


	public String getMeebgGroup() {
		return this.meebgGroup;
	}

	public void setMeebgGroup(String meebgGroup) {
		this.meebgGroup = meebgGroup;
	}


	public Long getMeebgId() {
		return meebgId;
	}


	public void setMeebgId(Long meebgId) {
		this.meebgId = meebgId;
	}


	public String getMeebgLang() {
		return meebgLang;
	}


	public void setMeebgLang(String meebgLang) {
		this.meebgLang = meebgLang;
	}


	public List<MissEptEvalBehavioralValue> getMissEptEvalBehavioralValues() {
		return missEptEvalBehavioralValues;
	}


	public void setMissEptEvalBehavioralValues(
			List<MissEptEvalBehavioralValue> missEptEvalBehavioralValues) {
		this.missEptEvalBehavioralValues = missEptEvalBehavioralValues;
	}

}