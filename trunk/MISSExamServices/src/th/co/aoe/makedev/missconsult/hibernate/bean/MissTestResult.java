package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_TEST_RESULT database table.
 * 
 */
@Entity
@Table(name="MISS_TEST_RESULT")
public class MissTestResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MTR_ID")
	private Long mtrId;

	@Column(name="MTEST_ID")
	private Long mtestId;

	@Column(name="MTR_CODE")
	private String mtrCode;

	@Column(name="MTR_RESPONSE_STATUS")
	private String mtrResponseStatus;

	@Column(name="MTR_RESULT")
	private String mtrResult;

	@Column(name="MTR_TEST_DATE")
	private Timestamp mtrTestDate;

	//bi-directional many-to-one association to MissCandidate
    @ManyToOne
	@JoinColumn(name="MCA_ID")
	private MissCandidate missCandidate;

    public MissTestResult() {
    }

	public Long getMtrId() {
		return this.mtrId;
	}

	public void setMtrId(Long mtrId) {
		this.mtrId = mtrId;
	}

	public Long getMtestId() {
		return this.mtestId;
	}

	public void setMtestId(Long mtestId) {
		this.mtestId = mtestId;
	}

	public String getMtrCode() {
		return this.mtrCode;
	}

	public void setMtrCode(String mtrCode) {
		this.mtrCode = mtrCode;
	}

	public String getMtrResponseStatus() {
		return this.mtrResponseStatus;
	}

	public void setMtrResponseStatus(String mtrResponseStatus) {
		this.mtrResponseStatus = mtrResponseStatus;
	}

	public String getMtrResult() {
		return this.mtrResult;
	}

	public void setMtrResult(String mtrResult) {
		this.mtrResult = mtrResult;
	}

	public Timestamp getMtrTestDate() {
		return this.mtrTestDate;
	}

	public void setMtrTestDate(Timestamp mtrTestDate) {
		this.mtrTestDate = mtrTestDate;
	}

	public MissCandidate getMissCandidate() {
		return this.missCandidate;
	}

	public void setMissCandidate(MissCandidate missCandidate) {
		this.missCandidate = missCandidate;
	}
	
}