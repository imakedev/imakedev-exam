package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_EPT_CAREER database table.
 * 
 */
@Embeddable
public class MissEptCareerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MEC_CODE")
	private String mecCode;

	@Column(name="MEC_ORDER")
	private int mecOrder;

	@Column(name="MEC_LANG")
	private String mecLang;

	public MissEptCareerPK() {
	}
	public String getMecCode() {
		return this.mecCode;
	}
	public void setMecCode(String mecCode) {
		this.mecCode = mecCode;
	}
	public int getMecOrder() {
		return this.mecOrder;
	}
	public void setMecOrder(int mecOrder) {
		this.mecOrder = mecOrder;
	}
	public String getMecLang() {
		return this.mecLang;
	}
	public void setMecLang(String mecLang) {
		this.mecLang = mecLang;
	}
}