package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MISS_EPT_PLUS_WORK_WHEEL_MESSAGE database table.
 * 
 */
@Entity
@Table(name="MISS_EPT_PLUS_WORK_WHEEL_MESSAGE")
public class MissEptPlusWorkWheelMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissEptPlusWorkWheelMessagePK id;

	@Column(name="MEPWWM_CHARECTER1")
	private String mepwwmCharecter1;

	@Lob
	@Column(name="MEPWWM_CHARECTER2")
	private String mepwwmCharecter2;

	@Column(name="MEPWWM_PERCENT")
	private String mepwwmPercent;

	@Column(name="MEPWWM_ROLE")
	private String mepwwmRole;

	@Lob
	@Column(name="MEPWWM_SAMPLE")
	private String mepwwmSample;

	@Column(name="MEPWWM_TYPE")
	private String mepwwmType;

	@Column(name="MEPWWM_VALUE")
	private double mepwwmValue;

	//bi-directional many-to-one association to MissTestResult
	@ManyToOne
	@JoinColumn(name="MTR_ID",insertable=false,updatable=false)
	private MissTestResult missTestResult;

	public MissEptPlusWorkWheelMessage() {
	}

	public MissEptPlusWorkWheelMessagePK getId() {
		return this.id;
	}

	public void setId(MissEptPlusWorkWheelMessagePK id) {
		this.id = id;
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

	public double getMepwwmValue() {
		return this.mepwwmValue;
	}

	public void setMepwwmValue(double mepwwmValue) {
		this.mepwwmValue = mepwwmValue;
	}

	public MissTestResult getMissTestResult() {
		return this.missTestResult;
	}

	public void setMissTestResult(MissTestResult missTestResult) {
		this.missTestResult = missTestResult;
	}

}