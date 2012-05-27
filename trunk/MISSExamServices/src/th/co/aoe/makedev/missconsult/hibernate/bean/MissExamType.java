package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_EXAM_TYPE database table.
 * 
 */
@Entity
@Table(name="MISS_EXAM_TYPE")
public class MissExamType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MET_ID")
	private Long metId;

	@Column(name="MET_NAME")
	private String metName;

	//bi-directional many-to-one association to MissExam
	/*@OneToMany(mappedBy="missExamType")
	private Set<MissExam> missExams;*/

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