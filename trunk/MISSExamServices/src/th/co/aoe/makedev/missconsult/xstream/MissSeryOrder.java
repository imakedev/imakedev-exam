package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_SERY_ORDER database table.
 * 
 */
@XStreamAlias("MissSeryOrder")
public class MissSeryOrder  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long msId;

	private Long maId;

	private java.util.Date msoDateTime;

	private Long msoAmount;

	private Long msoWeek;

    public MissSeryOrder() {
    }


	
	public Long getMsId() {
		return msId;
	}



	public void setMsId(Long msId) {
		this.msId = msId;
	}



	public Long getMaId() {
		return maId;
	}



	public void setMaId(Long maId) {
		this.maId = maId;
	}



	public java.util.Date getMsoDateTime() {
		return msoDateTime;
	}



	public void setMsoDateTime(java.util.Date msoDateTime) {
		this.msoDateTime = msoDateTime;
	}



	public Long getMsoAmount() {
		return this.msoAmount;
	}

	public void setMsoAmount(Long msoAmount) {
		this.msoAmount = msoAmount;
	}

	public Long getMsoWeek() {
		return this.msoWeek;
	}

	public void setMsoWeek(Long msoWeek) {
		this.msoWeek = msoWeek;
	}

}