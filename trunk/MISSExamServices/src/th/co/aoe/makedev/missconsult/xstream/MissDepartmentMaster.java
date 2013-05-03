package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_DEPARTMENT_MASTER database table.
 * 
 */
@XStreamAlias("MissDepartmentMaster")
public class MissDepartmentMaster extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private Long mdmId;
 
	private String mdmName;
 
	private Long mdmRef;
	
	private Integer mdmOrder; 

	public Integer getMdmOrder() {
		return mdmOrder;
	}

	public void setMdmOrder(Integer mdmOrder) {
		this.mdmOrder = mdmOrder;
	}

	public MissDepartmentMaster() {
	}

	public Long getMdmId() {
		return this.mdmId;
	}

	public void setMdmId(Long mdmId) {
		this.mdmId = mdmId;
	}

	public String getMdmName() {
		return this.mdmName;
	}

	public void setMdmName(String mdmName) {
		this.mdmName = mdmName;
	}

	public Long getMdmRef() {
		return this.mdmRef;
	}

	public void setMdmRef(Long mdmRef) {
		this.mdmRef = mdmRef;
	}

}