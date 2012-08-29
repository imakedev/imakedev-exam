package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_THEME database table.
 * 
 */
@XStreamAlias("MissTheme")
public class MissTheme extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mtId;

	private String mtBgColor;

	private String mtLogo;

	private String mtTr;

	private String mtWaterWall;
	private String mtName;
	private String mtSamePlePicture;
	private String mtTrColor;
	
	private Long maId;
	 

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




	public Long getMaId() {
		return maId;
	}




	public void setMaId(Long maId) {
		this.maId = maId;
	}




	public void setMtSamePlePicture(String mtSamePlePicture) {
		this.mtSamePlePicture = mtSamePlePicture;
	}




	public String getMtTrColor() {
		return mtTrColor;
	}




	public void setMtTrColor(String mtTrColor) {
		this.mtTrColor = mtTrColor;
	}

	
}