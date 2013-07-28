package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the MISS_TEST_RESULT_SURVEY database table.
 * 
 */
@Entity
@Table(name="MISS_TEST_RESULT_SURVEY")
public class MissTestResultSurvey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MTRS_ID")
	private Long mtrsId;

	@Column(name="MCA_ID")
	private Long mcaId;

	@Column(name="ME_ID")
	private Long meId;

	@Column(name="MS_ID")
	private Long msId;

	@Column(name="MTRS_END_TIME")
	private Timestamp mtrsEndTime;

	@Column(name="MTRS_HIDE_STATUS")
	private String mtrsHideStatus;

	@Column(name="MTRS_IGNORED")
	private String mtrsIgnored;

	@Column(name="MTRS_RESPONDED_STATUS")
	private String mtrsRespondedStatus;

	@Column(name="MTRS_RESULT_CODE")
	private String mtrsResultCode;

	@Column(name="MTRS_START_TIME")
	private Timestamp mtrsStartTime;

	@Column(name="MTRS_STATUS")
	private String mtrsStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="MTRS_TEST_DATE")
	private Date mtrsTestDate;

	@Column(name="MTS_KEY")
	private String mtsKey;

	public MissTestResultSurvey() {
	}

	public Long getMtrsId() {
		return this.mtrsId;
	}

	public void setMtrsId(Long mtrsId) {
		this.mtrsId = mtrsId;
	}

	public Long getMcaId() {
		return this.mcaId;
	}

	public void setMcaId(Long mcaId) {
		this.mcaId = mcaId;
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

	public Timestamp getMtrsEndTime() {
		return this.mtrsEndTime;
	}

	public void setMtrsEndTime(Timestamp mtrsEndTime) {
		this.mtrsEndTime = mtrsEndTime;
	}

	public String getMtrsHideStatus() {
		return this.mtrsHideStatus;
	}

	public void setMtrsHideStatus(String mtrsHideStatus) {
		this.mtrsHideStatus = mtrsHideStatus;
	}

	public String getMtrsIgnored() {
		return this.mtrsIgnored;
	}

	public void setMtrsIgnored(String mtrsIgnored) {
		this.mtrsIgnored = mtrsIgnored;
	}

	public String getMtrsRespondedStatus() {
		return this.mtrsRespondedStatus;
	}

	public void setMtrsRespondedStatus(String mtrsRespondedStatus) {
		this.mtrsRespondedStatus = mtrsRespondedStatus;
	}

	public String getMtrsResultCode() {
		return this.mtrsResultCode;
	}

	public void setMtrsResultCode(String mtrsResultCode) {
		this.mtrsResultCode = mtrsResultCode;
	}

	public Timestamp getMtrsStartTime() {
		return this.mtrsStartTime;
	}

	public void setMtrsStartTime(Timestamp mtrsStartTime) {
		this.mtrsStartTime = mtrsStartTime;
	}

	public String getMtrsStatus() {
		return this.mtrsStatus;
	}

	public void setMtrsStatus(String mtrsStatus) {
		this.mtrsStatus = mtrsStatus;
	}

	public Date getMtrsTestDate() {
		return this.mtrsTestDate;
	}

	public void setMtrsTestDate(Date mtrsTestDate) {
		this.mtrsTestDate = mtrsTestDate;
	}

	public String getMtsKey() {
		return this.mtsKey;
	}

	public void setMtsKey(String mtsKey) {
		this.mtsKey = mtsKey;
	}

}