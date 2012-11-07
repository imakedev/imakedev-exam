package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_EPT_PLUS_WORK_WHEEL_MESSAGE database table.
 * 
 */
@Embeddable
public class MissEptPlusWorkWheelMessagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MTR_ID")
	private Long mtrId;

	@Column(name="MEPWWM_LANG")
	private String mepwwmLang;

	@Column(name="MEPWWM_ORDER")
	private int mepwwmOrder;

	public MissEptPlusWorkWheelMessagePK() {
	}
	public Long getMtrId() {
		return this.mtrId;
	}
	public void setMtrId(Long mtrId) {
		this.mtrId = mtrId;
	}
	public String getMepwwmLang() {
		return this.mepwwmLang;
	}
	public void setMepwwmLang(String mepwwmLang) {
		this.mepwwmLang = mepwwmLang;
	}
	public int getMepwwmOrder() {
		return this.mepwwmOrder;
	}
	public void setMepwwmOrder(int mepwwmOrder) {
		this.mepwwmOrder = mepwwmOrder;
	}
}