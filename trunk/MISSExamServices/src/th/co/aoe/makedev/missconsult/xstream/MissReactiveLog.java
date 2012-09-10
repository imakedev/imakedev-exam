package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_REACTIVE_LOG database table.
 * 
 */
@XStreamAlias("MissReactiveLog")
public class MissReactiveLog  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long msId;

	private Long mcaId;

	private java.util.Date mrlDateTime;

	private Long mrlWeek;

    public MissReactiveLog() {
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


	public java.util.Date getMrlDateTime() {
		return mrlDateTime;
	}


	public void setMrlDateTime(java.util.Date mrlDateTime) {
		this.mrlDateTime = mrlDateTime;
	}


	public Long getMrlWeek() {
		return this.mrlWeek;
	}

	public void setMrlWeek(Long mrlWeek) {
		this.mrlWeek = mrlWeek;
	}

}