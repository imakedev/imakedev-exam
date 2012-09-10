package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_SERY_PROBLEM database table.
 * 
 */
@XStreamAlias("MissSeryProblem")
public class MissSeryProblem  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long msId;

	private Long mcaId;

	private java.util.Date mspDateTime;

	private String mspMessage;

	private String mspType;

	private Long mspWeek;

    public Long getMsId() {
		return msId;
	}
	public void setMsId(Long msId) {
		this.msId = msId;
	}
	public Long getMcaId() {
		return mcaId;
	}
	public void setMcaId(Long mcaId) {
		this.mcaId = mcaId;
	}
	public java.util.Date getMspDateTime() {
		return mspDateTime;
	}
	public void setMspDateTime(java.util.Date mspDateTime) {
		this.mspDateTime = mspDateTime;
	}
	public MissSeryProblem() {
    }
	public String getMspMessage() {
		return this.mspMessage;
	}

	public void setMspMessage(String mspMessage) {
		this.mspMessage = mspMessage;
	}

	public String getMspType() {
		return this.mspType;
	}

	public void setMspType(String mspType) {
		this.mspType = mspType;
	}

	public Long getMspWeek() {
		return this.mspWeek;
	}

	public void setMspWeek(Long mspWeek) {
		this.mspWeek = mspWeek;
	}

}