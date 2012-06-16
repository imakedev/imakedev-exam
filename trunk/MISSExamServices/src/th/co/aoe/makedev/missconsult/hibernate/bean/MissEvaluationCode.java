package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_EVALUATION_CODE database table.
 * 
 */
@Entity
@Table(name="MISS_EVALUATION_CODE")
public class MissEvaluationCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissEvaluationCodePK id;

	@Column(name="CODE1_FOMULA")
	private String code1Fomula;

	@Column(name="CODE2_FOMULA")
	private String code2Fomula;

	@Column(name="CODE3_FOMULA")
	private String code3Fomula;

	@Column(name="CODE4_FOMULA")
	private String code4Fomula;

	@Column(name="CODE5_FOMULA")
	private String code5Fomula;

	@Column(name="CODE6_FOMULA")
	private String code6Fomula;

	@Column(name="CODE7_FOMULA")
	private String code7Fomula;

	@Column(name="CODE8_FOMULA")
	private String code8Fomula;

    public MissEvaluationCode() {
    }

	public MissEvaluationCodePK getId() {
		return this.id;
	}

	public void setId(MissEvaluationCodePK id) {
		this.id = id;
	}
	
	public String getCode1Fomula() {
		return this.code1Fomula;
	}

	public void setCode1Fomula(String code1Fomula) {
		this.code1Fomula = code1Fomula;
	}

	public String getCode2Fomula() {
		return this.code2Fomula;
	}

	public void setCode2Fomula(String code2Fomula) {
		this.code2Fomula = code2Fomula;
	}

	public String getCode3Fomula() {
		return this.code3Fomula;
	}

	public void setCode3Fomula(String code3Fomula) {
		this.code3Fomula = code3Fomula;
	}

	public String getCode4Fomula() {
		return this.code4Fomula;
	}

	public void setCode4Fomula(String code4Fomula) {
		this.code4Fomula = code4Fomula;
	}

	public String getCode5Fomula() {
		return this.code5Fomula;
	}

	public void setCode5Fomula(String code5Fomula) {
		this.code5Fomula = code5Fomula;
	}

	public String getCode6Fomula() {
		return this.code6Fomula;
	}

	public void setCode6Fomula(String code6Fomula) {
		this.code6Fomula = code6Fomula;
	}

	public String getCode7Fomula() {
		return this.code7Fomula;
	}

	public void setCode7Fomula(String code7Fomula) {
		this.code7Fomula = code7Fomula;
	}

	public String getCode8Fomula() {
		return this.code8Fomula;
	}

	public void setCode8Fomula(String code8Fomula) {
		this.code8Fomula = code8Fomula;
	}

}