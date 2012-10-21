package th.co.aoe.makedev.missconsult.domain;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the MISS_EPT_PLUS_WORK_WHEEL_MESSAGE database table.
 * 
 */
public class MissEptPlusWorkWheelMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private int mtrId;

	private String mepwwmLang;

	private int mepwwmOrder;

	private String mepwwmCharecter1;

	private String mepwwmCharecter2;

	private String mepwwmPercent;

	private String mepwwmRole;

	private String mepwwmSample;

	private String mepwwmType;

	private BigDecimal mepwwmValue;

	//bi-directional many-to-one association to MissTestResult

	public MissEptPlusWorkWheelMessage() {
	}

	

	public String getMepwwmCharecter1() {
		return this.mepwwmCharecter1;
	}

	public void setMepwwmCharecter1(String mepwwmCharecter1) {
		this.mepwwmCharecter1 = mepwwmCharecter1;
	}

	public String getMepwwmCharecter2() {
		return this.mepwwmCharecter2;
	}

	public void setMepwwmCharecter2(String mepwwmCharecter2) {
		this.mepwwmCharecter2 = mepwwmCharecter2;
	}

	public String getMepwwmPercent() {
		return this.mepwwmPercent;
	}

	public void setMepwwmPercent(String mepwwmPercent) {
		this.mepwwmPercent = mepwwmPercent;
	}

	public String getMepwwmRole() {
		return this.mepwwmRole;
	}

	public void setMepwwmRole(String mepwwmRole) {
		this.mepwwmRole = mepwwmRole;
	}

	public String getMepwwmSample() {
		return this.mepwwmSample;
	}

	public void setMepwwmSample(String mepwwmSample) {
		this.mepwwmSample = mepwwmSample;
	}

	public String getMepwwmType() {
		return this.mepwwmType;
	}

	public void setMepwwmType(String mepwwmType) {
		this.mepwwmType = mepwwmType;
	}

	public BigDecimal getMepwwmValue() {
		return this.mepwwmValue;
	}

	public void setMepwwmValue(BigDecimal mepwwmValue) {
		this.mepwwmValue = mepwwmValue;
	}



	public int getMtrId() {
		return mtrId;
	}



	public void setMtrId(int mtrId) {
		this.mtrId = mtrId;
	}



	public String getMepwwmLang() {
		return mepwwmLang;
	}



	public void setMepwwmLang(String mepwwmLang) {
		this.mepwwmLang = mepwwmLang;
	}



	public int getMepwwmOrder() {
		return mepwwmOrder;
	}



	public void setMepwwmOrder(int mepwwmOrder) {
		this.mepwwmOrder = mepwwmOrder;
	}


}