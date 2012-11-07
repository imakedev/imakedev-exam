package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_EPT_TRAITS_DETECTOR database table.
 * 
 */
@Embeddable
public class MissEptTraitsDetectorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MTR_ID")
	private Long mtrId;

	@Column(name="METD_LANG")
	private String metdLang;

	@Column(name="METD_ORDER")
	private int metdOrder;

	public MissEptTraitsDetectorPK() {
	}
	public Long getMtrId() {
		return this.mtrId;
	}
	public void setMtrId(Long mtrId) {
		this.mtrId = mtrId;
	}
	public String getMetdLang() {
		return this.metdLang;
	}
	public void setMetdLang(String metdLang) {
		this.metdLang = metdLang;
	}
	public int getMetdOrder() {
		return this.metdOrder;
	}
	public void setMetdOrder(int metdOrder) {
		this.metdOrder = metdOrder;
	}   
}