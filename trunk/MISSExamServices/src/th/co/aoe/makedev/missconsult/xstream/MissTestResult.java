package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.sql.Timestamp;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_TEST_RESULT database table.
 * 
 */
@XStreamAlias("MissTestResult")
public class MissTestResult extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mtrId;

	private Long mtestId;

	private String mtrCode;

	private String mtrResponseStatus;

	private String mtrResult;

	@XStreamAlias("mtrTestDate")
	private Timestamp mtrTestDate;

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