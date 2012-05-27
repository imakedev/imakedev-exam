package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_EXAM_GROUP database table.
 * 
 */
@XStreamAlias("MissExamGroup")
public class MissExamGroup extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long megId;

	private String megName;

    public MissExamGroup() {
    }

	public Long getMegId() {
		return this.megId;
	}

	public void setMegId(Long megId) {
		this.megId = megId;
	}

	public String getMegName() {
		return this.megName;
	}

	public void setMegName(String megName) {
		this.megName = megName;
	}

	/*public Set<MissExam> getMissExams() {
		return this.missExams;
	}

	public void setMissExams(Set<MissExam> missExams) {
		this.missExams = missExams;
	}*/
	
}