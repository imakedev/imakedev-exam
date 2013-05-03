package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_POSITION_MASTER database table.
 * 
 */
@XStreamAlias("MissPositionMaster")
public class MissPositionMaster extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	 
	private Long mpmId;
 
	private String mpmName;

	//bi-directional many-to-one association to MissDepartmentMaster
	@XStreamAlias("missDepartmentMaster")
	private MissDepartmentMaster missDepartmentMaster;
	
	private Integer mpmOrder; 

	public Integer getMpmOrder() {
		return mpmOrder;
	}

	public void setMpmOrder(Integer mpmOrder) {
		this.mpmOrder = mpmOrder;
	}

	public MissPositionMaster() {
	}

	public Long getMpmId() {
		return this.mpmId;
	}

	public void setMpmId(Long mpmId) {
		this.mpmId = mpmId;
	}

	public String getMpmName() {
		return this.mpmName;
	}

	public void setMpmName(String mpmName) {
		this.mpmName = mpmName;
	}

	public MissDepartmentMaster getMissDepartmentMaster() {
		return this.missDepartmentMaster;
	}

	public void setMissDepartmentMaster(MissDepartmentMaster missDepartmentMaster) {
		this.missDepartmentMaster = missDepartmentMaster;
	}

}