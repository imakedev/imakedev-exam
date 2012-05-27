package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_EXAM_TYPE database table.
 * 
 */
@XStreamAlias("MissExamType")
public class MissExamType extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long metId;

	private String metName;

    public MissExamType() {
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
/*
	public Set<MissExam> getMissExams() {
		return this.missExams;
	}

	public void setMissExams(Set<MissExam> missExams) {
		this.missExams = missExams;
	}*/
	
}