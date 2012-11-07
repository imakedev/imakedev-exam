package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_EPT_ATTITUDE_DETECTOR_REPORT database table.
 * 
 */
@Embeddable
public class MissEptAttitudeDetectorReportPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MTR_ID")
	private Long mtrId;

	@Column(name="MEADR_LANG")
	private String meadrLang;

	@Column(name="MEADR_ORDER")
	private int meadrOrder;

	public MissEptAttitudeDetectorReportPK() {
	}
	public Long getMtrId() {
		return this.mtrId;
	}
	public void setMtrId(Long mtrId) {
		this.mtrId = mtrId;
	}
	public String getMeadrLang() {
		return this.meadrLang;
	}
	public void setMeadrLang(String meadrLang) {
		this.meadrLang = meadrLang;
	}
	public int getMeadrOrder() {
		return this.meadrOrder;
	}
	public void setMeadrOrder(int meadrOrder) {
		this.meadrOrder = meadrOrder;
	} 
}