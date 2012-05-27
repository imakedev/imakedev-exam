package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_EVALUATION_TEMPLATE database table.
 * 
 */
@XStreamAlias("MissEvaluationTemplate")
public class MissEvaluationTemplate extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long metId;

	private String metName;

	private String metXsltFile;
	
	@XStreamAlias("missExam")
	private MissExam missExam;

    public MissEvaluationTemplate() {
    }

	public Long getMetId() {
		return this.metId;
	}

	public void setMetId(Long metId) {
		this.metId = metId;
	}

	public String getMetName() {
		return this.metName;
	}

	public void setMetName(String metName) {
		this.metName = metName;
	}

	public String getMetXsltFile() {
		return this.metXsltFile;
	}

	public void setMetXsltFile(String metXsltFile) {
		this.metXsltFile = metXsltFile;
	}

	public MissExam getMissExam() {
		return this.missExam;
	}

	public void setMissExam(MissExam missExam) {
		this.missExam = missExam;
	}
	
}