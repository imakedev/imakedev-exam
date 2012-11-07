package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_EPT_DOMINANCE database table.
 * 
 */
@Entity
@Table(name="MISS_EPT_DOMINANCE")
public class MissEptDominance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MTR_ID")
	private Long mtrId;

	@Column(name="MEP_DOMINANCE")
	private String mepDominance;

	@Column(name="MEP_SUB_DOMINANCE")
	private String mepSubDominance;

	//bi-directional one-to-one association to MissTestResult
	@OneToOne
	@JoinColumn(name="MTR_ID")
	private MissTestResult missTestResult;

	public MissEptDominance() {
	}

	public Long getMtrId() {
		return this.mtrId;
	}

	public void setMtrId(Long mtrId) {
		this.mtrId = mtrId;
	}

	public String getMepDominance() {
		return this.mepDominance;
	}

	public void setMepDominance(String mepDominance) {
		this.mepDominance = mepDominance;
	}

	public String getMepSubDominance() {
		return this.mepSubDominance;
	}

	public void setMepSubDominance(String mepSubDominance) {
		this.mepSubDominance = mepSubDominance;
	}

	public MissTestResult getMissTestResult() {
		return this.missTestResult;
	}

	public void setMissTestResult(MissTestResult missTestResult) {
		this.missTestResult = missTestResult;
	}

}