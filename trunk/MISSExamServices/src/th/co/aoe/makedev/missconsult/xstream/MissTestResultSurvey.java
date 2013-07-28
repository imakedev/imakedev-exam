package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_TEST_RESULT_SURVEY database table.
 * 
 */
@XStreamAlias("MissTestResultSurvey")
public class MissTestResultSurvey  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mtrsId;

	private Long mcaId;

	private Long meId;

	private Long msId;

	private Timestamp mtrsEndTime;

	private String mtrsHideStatus;

	private String mtrsIgnored;

	private String mtrsRespondedStatus;

	private String mtrsResultCode;

	private Timestamp mtrsStartTime;

	private String mtrsStatus;

	private Date mtrsTestDate;

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