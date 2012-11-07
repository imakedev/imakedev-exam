package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_EPT_TRAITS_DETECTOR database table.
 * 
 */
@Entity
@Table(name="MISS_EPT_TRAITS_DETECTOR")
public class MissEptTraitsDetector implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissEptTraitsDetectorPK id;

	@Column(name="METD_NAME")
	private String metdName;

	@Column(name="METD_VALUE")
	private Double metdValue;

	//bi-directional many-to-one association to MissTestResult
	@ManyToOne
	@JoinColumn(name="MTR_ID",insertable=false,updatable=false)
	private MissTestResult missTestResult;

	public MissEptTraitsDetector() {
	}

	public MissEptTraitsDetectorPK getId() {
		return this.id;
	}

	public void setId(MissEptTraitsDetectorPK id) {
		this.id = id;
	}

	public String getMetdName() {
		return this.metdName;
	}

	public void setMetdName(String metdName) {
		this.metdName = metdName;
	}

	public Double getMetdValue() {
		return this.metdValue;
	}

	public void setMetdValue(Double metdValue) {
		this.metdValue = metdValue;
	}

	public MissTestResult getMissTestResult() {
		return this.missTestResult;
	}

	public void setMissTestResult(MissTestResult missTestResult) {
		this.missTestResult = missTestResult;
	}

}