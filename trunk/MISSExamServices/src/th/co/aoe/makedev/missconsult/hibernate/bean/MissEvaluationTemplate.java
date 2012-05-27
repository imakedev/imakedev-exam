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
 * The persistent class for the MISS_EVALUATION_TEMPLATE database table.
 * 
 */
@Entity
@Table(name="MISS_EVALUATION_TEMPLATE")
public class MissEvaluationTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MET_ID")
	private Long metId;

	@Column(name="MET_NAME")
	private String metName;

	@Column(name="MET_XSLT_FILE")
	private String metXsltFile;

	//bi-directional many-to-one association to MissExam
    @ManyToOne
	@JoinColumn(name="ME_ID")
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