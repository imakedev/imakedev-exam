package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_DATA_IMAGE database table.
 * 
 */
@Embeddable
public class MissDataImagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MTR_ID")
	private Long mtrId;

	@Column(name="MDI_COL_ROW_REF")
	private String mdiColRowRef;

	@Column(name="MDI_COL_ROW_EXPAND")
	private String mdiColRowExpand;

	@Column(name="MDI_ENDPOINT")
	private String mdiEndpoint;

	public MissDataImagePK() {
	}
	public Long getMtrId() {
		return this.mtrId;
	}
	public void setMtrId(Long mtrId) {
		this.mtrId = mtrId;
	}
	public String getMdiColRowRef() {
		return this.mdiColRowRef;
	}
	public void setMdiColRowRef(String mdiColRowRef) {
		this.mdiColRowRef = mdiColRowRef;
	}
	public String getMdiColRowExpand() {
		return this.mdiColRowExpand;
	}
	public void setMdiColRowExpand(String mdiColRowExpand) {
		this.mdiColRowExpand = mdiColRowExpand;
	}
	public String getMdiEndpoint() {
		return this.mdiEndpoint;
	}
	public void setMdiEndpoint(String mdiEndpoint) {
		this.mdiEndpoint = mdiEndpoint;
	}
 
 
}