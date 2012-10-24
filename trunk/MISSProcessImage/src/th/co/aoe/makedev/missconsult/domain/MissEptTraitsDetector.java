package th.co.aoe.makedev.missconsult.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class MissEptTraitsDetector implements Serializable {
	private static final long serialVersionUID = 1L;

	private int mtrId;

	private String metdLang;

	private int metdOrder;

	private String metdName;

	private BigDecimal metdValue;

	public int getMtrId() {
		return mtrId;
	}

	public void setMtrId(int mtrId) {
		this.mtrId = mtrId;
	}

	public String getMetdLang() {
		return metdLang;
	}

	public void setMetdLang(String metdLang) {
		this.metdLang = metdLang;
	}

	public int getMetdOrder() {
		return metdOrder;
	}

	public void setMetdOrder(int metdOrder) {
		this.metdOrder = metdOrder;
	}

	public String getMetdName() {
		return metdName;
	}

	public void setMetdName(String metdName) {
		this.metdName = metdName;
	}

	public BigDecimal getMetdValue() {
		return metdValue;
	}

	public void setMetdValue(BigDecimal metdValue) {
		this.metdValue = metdValue;
	}


}
