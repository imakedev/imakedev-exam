package th.co.aoe.makedev.missconsult.domain;

import java.io.Serializable;

public class MissEptCareer  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String mecCareerName;
	private String mecCode;
	private int mecOrder;
	private String mecLang;
	public String getMecCareerName() {
		return mecCareerName;
	}
	public void setMecCareerName(String mecCareerName) {
		this.mecCareerName = mecCareerName;
	}
	public String getMecCode() {
		return mecCode;
	}
	public void setMecCode(String mecCode) {
		this.mecCode = mecCode;
	}
	public int getMecOrder() {
		return mecOrder;
	}
	public void setMecOrder(int mecOrder) {
		this.mecOrder = mecOrder;
	}
	public String getMecLang() {
		return mecLang;
	}
	public void setMecLang(String mecLang) {
		this.mecLang = mecLang;
	}
	
}
