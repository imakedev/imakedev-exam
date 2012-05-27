package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_EXAM_GROUP database table.
 * 
 */
@Entity
@Table(name="MISS_EXAM_GROUP")
public class MissExamGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MEG_ID")
	private Long megId;

	@Column(name="MEG_NAME")
	private String megName;

	//bi-directional many-to-one association to MissExam
/*	@OneToMany(mappedBy="missExamGroup")
	private Set<MissExam> missExams;*/

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