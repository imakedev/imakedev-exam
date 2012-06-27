package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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

	@Column(name="ME_ID")
	private Long meId;

	@Column(name="MS_ID")
	private Long msId;

	@Column(name="MTR_END_TIME")
	private Timestamp mtrEndTime;

	@Column(name="MTR_RESULT_CODE")
	private String mtrResultCode;

	@Column(name="MTR_START_TIME")
	private Timestamp mtrStartTime;

	@Column(name="MTR_STATUS")
	private String mtrStatus;

    @Temporal( TemporalType.DATE)
	@Column(name="MTR_TEST_DATE")
	private Date mtrTestDate;
    
	@Column(name="MTR_IGNORED")
	private String mtrIgnored;
    

	//bi-directional many-to-one association to MissCandidate
    @ManyToOne
	@JoinColumn(name="MCA_ID")
	private MissCandidate missCandidate;

    public String getMtrIgnored() {
		return mtrIgnored;
	}

	public void setMtrIgnored(String mtrIgnored) {
		this.mtrIgnored = mtrIgnored;
	}

	public MissTestResult() {
    }

	public Long getMtrId() {
		return this.mtrId;
	}

	public void setMtrId(Long mtrId) {
		this.mtrId = mtrId;
	}

	public Long getMeId() {
		return this.meId;
	}

	public void setMeId(Long meId) {
		this.meId = meId;
	}

	public Long getMsId() {
		return this.msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public Timestamp getMtrEndTime() {
		return this.mtrEndTime;
	}

	public void setMtrEndTime(Timestamp mtrEndTime) {
		this.mtrEndTime = mtrEndTime;
	}

	public String getMtrResultCode() {
		return this.mtrResultCode;
	}

	public void setMtrResultCode(String mtrResultCode) {
		this.mtrResultCode = mtrResultCode;
	}

	public Timestamp getMtrStartTime() {
		return this.mtrStartTime;
	}

	public void setMtrStartTime(Timestamp mtrStartTime) {
		this.mtrStartTime = mtrStartTime;
	}

	public String getMtrStatus() {
		return this.mtrStatus;
	}

	public void setMtrStatus(String mtrStatus) {
		this.mtrStatus = mtrStatus;
	}

	public Date getMtrTestDate() {
		return this.mtrTestDate;
	}

	public void setMtrTestDate(Date mtrTestDate) {
		this.mtrTestDate = mtrTestDate;
	}

	public MissCandidate getMissCandidate() {
		return this.missCandidate;
	}

	public void setMissCandidate(MissCandidate missCandidate) {
		this.missCandidate = missCandidate;
	}
	
}