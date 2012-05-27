package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_TEMPLATE database table.
 * 
 */
@XStreamAlias("MissTemplate")
public class MissTemplate extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mtId;

	private String mtName;

	private String mtTemplate;

    public MissTemplate() {
    }

	public Long getMtId() {
		return this.mtId;
	}

	public void setMtId(Long mtId) {
		this.mtId = mtId;
	}

	public String getMtName() {
		return this.mtName;
	}

	public void setMtName(String mtName) {
		this.mtName = mtName;
	}

	public String getMtTemplate() {
		return this.mtTemplate;
	}

	public void setMtTemplate(String mtTemplate) {
		this.mtTemplate = mtTemplate;
	}

	/*public Set<MissQuestion> getMissQuestions() {
		return this.missQuestions;
	}

	public void setMissQuestions(Set<MissQuestion> missQuestions) {
		this.missQuestions = missQuestions;
	}*/
	
}