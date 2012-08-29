package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_THEME database table.
 * 
 */
@Entity
@Table(name="MISS_THEME")
public class MissTheme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MT_ID")
	private Long mtId;

	@Column(name="MT_BG_COLOR")
	private String mtBgColor;

	@Column(name="MT_LOGO")
	private String mtLogo;

	@Column(name="MT_TR")
	private String mtTr;

	@Column(name="MT_WATER_WALL")
	private String mtWaterWall;
	
	@Column(name="MT_NAME")
	private String mtName;

	@Column(name="MT_SAMEPLE_PICTURE")
	private String mtSamePlePicture;
	
	@Column(name="MT_TR_COLOR")
	private String mtTrColor;

    public String getMtTrColor() {
		return mtTrColor;
	}


	public void setMtTrColor(String mtTrColor) {
		this.mtTrColor = mtTrColor;
	}


	public MissTheme() {
    }


	public String getMtBgColor() {
		return this.mtBgColor;
	}

	public void setMtBgColor(String mtBgColor) {
		this.mtBgColor = mtBgColor;
	}

	public String getMtLogo() {
		return this.mtLogo;
	}

	public void setMtLogo(String mtLogo) {
		this.mtLogo = mtLogo;
	}

	public String getMtTr() {
		return this.mtTr;
	}

	public void setMtTr(String mtTr) {
		this.mtTr = mtTr;
	}

	public String getMtWaterWall() {
		return this.mtWaterWall;
	}

	public void setMtWaterWall(String mtWaterWall) {
		this.mtWaterWall = mtWaterWall;
	}


	public Long getMtId() {
		return mtId;
	}


	public void setMtId(Long mtId) {
		this.mtId = mtId;
	}


	public String getMtName() {
		return mtName;
	}


	public void setMtName(String mtName) {
		this.mtName = mtName;
	}


	public String getMtSamePlePicture() {
		return mtSamePlePicture;
	}


	public void setMtSamePlePicture(String mtSamePlePicture) {
		this.mtSamePlePicture = mtSamePlePicture;
	}


	
}