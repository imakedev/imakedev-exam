package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_TEST_RESULT database table.
 * 
 */
@XStreamAlias("MissTestResult")
public class MissTestResult  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mtrId;

	private Long meId;

	private Long msId;

	private Timestamp mtrEndTime;

	private String mtrResultCode;

	private Timestamp mtrStartTime;

	private String mtrStatus;

	private Date mtrTestDate;
	
	@XStreamAlias("missCandidate")
	private MissCandidate missCandidate;

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