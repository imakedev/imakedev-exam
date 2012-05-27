package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_SERIES_MAP database table.
 * 
 */
@XStreamAlias("MissSeriesMap")
public class MissSeriesMap extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long meId;

	private Long msId;
	
	private Long msmOrder;

    public MissSeriesMap() {
    }

	public Long getMeId() {
		return meId;
	}

	public void setMeId(Long meId) {
		this.meId = meId;
	}

	public Long getMsId() {
		return msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public Long getMsmOrder() {
		return msmOrder;
	}

	public void setMsmOrder(Long msmOrder) {
		this.msmOrder = msmOrder;
	}


	
}