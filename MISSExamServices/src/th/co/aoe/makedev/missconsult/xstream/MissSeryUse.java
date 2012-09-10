package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_SERY_USE database table.
 * 
 */
@XStreamAlias("MissSeryUse")
public class MissSeryUse  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long msId;

	private Long mcaId;

	private java.util.Date msuDdateTime;

	private Long msuWeek;

    public MissSeryUse() {
    }


	
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



	public java.util.Date getMsuDdateTime() {
		return msuDdateTime;
	}



	public void setMsuDdateTime(java.util.Date msuDdateTime) {
		this.msuDdateTime = msuDdateTime;
	}



	public Long getMsuWeek() {
		return this.msuWeek;
	}

	public void setMsuWeek(Long msuWeek) {
		this.msuWeek = msuWeek;
	}

}