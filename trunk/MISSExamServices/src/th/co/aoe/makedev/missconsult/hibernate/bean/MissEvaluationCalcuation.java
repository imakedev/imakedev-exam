package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_EVALUATION_CALCUATION database table.
 * 
 */
@Entity
@Table(name="MISS_EVALUATION_CALCUATION")
public class MissEvaluationCalcuation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissEvaluationCalcuationPK id;

	@Column(name="AXIS1_FOMULA")
	private String axis1Fomula;

	@Column(name="AXIS1_SEARCH_RESULT")
	private String axis1SearchResult;

	@Column(name="AXIS1_SHORT_NAME")
	private String axis1ShortName;

	@Column(name="AXIS2_FOMULA")
	private String axis2Fomula;

	@Column(name="AXIS2_SEARCH_RESULT")
	private String axis2SearchResult;

	@Column(name="AXIS2_SHORT_NAME")
	private String axis2ShortName;

	@Column(name="AXIS3_FOMULA")
	private String axis3Fomula;

	@Column(name="AXIS3_SEARCH_RESULT")
	private String axis3SearchResult;

	@Column(name="AXIS3_SHORT_NAME")
	private String axis3ShortName;

	@Column(name="AXIS4_FOMULA")
	private String axis4Fomula;

	@Column(name="AXIS4_SEARCH_RESULT")
	private String axis4SearchResult;

	@Column(name="AXIS4_SHORT_NAME")
	private String axis4ShortName;

	@Column(name="AXIS5_FOMULA")
	private String axis5Fomula;

	@Column(name="AXIS5_SEARCH_RESULT")
	private String axis5SearchResult;

	@Column(name="AXIS5_SHORT_NAME")
	private String axis5ShortName;

	@Column(name="AXIS6_FOMULA")
	private String axis6Fomula;

	@Column(name="AXIS6_SEARCH_RESULT")
	private String axis6SearchResult;

	@Column(name="AXIS6_SHORT_NAME")
	private String axis6ShortName;

	@Column(name="AXIS7_FOMULA")
	private String axis7Fomula;

	@Column(name="AXIS7_SEARCH_RESULT")
	private String axis7SearchResult;

	@Column(name="AXIS7_SHORT_NAME")
	private String axis7ShortName;

	@Column(name="AXIS8_FOMULA")
	private String axis8Fomula;

	@Column(name="AXIS8_SEARCH_RESULT")
	private String axis8SearchResult;

	@Column(name="AXIS8_SHORT_NAME")
	private String axis8ShortName;

    public MissEvaluationCalcuation() {
    }

	public MissEvaluationCalcuationPK getId() {
		return this.id;
	}

	public void setId(MissEvaluationCalcuationPK id) {
		this.id = id;
	}
	
	public String getAxis1Fomula() {
		return this.axis1Fomula;
	}

	public void setAxis1Fomula(String axis1Fomula) {
		this.axis1Fomula = axis1Fomula;
	}

	public String getAxis1SearchResult() {
		return this.axis1SearchResult;
	}

	public void setAxis1SearchResult(String axis1SearchResult) {
		this.axis1SearchResult = axis1SearchResult;
	}

	public String getAxis1ShortName() {
		return this.axis1ShortName;
	}

	public void setAxis1ShortName(String axis1ShortName) {
		this.axis1ShortName = axis1ShortName;
	}

	public String getAxis2Fomula() {
		return this.axis2Fomula;
	}

	public void setAxis2Fomula(String axis2Fomula) {
		this.axis2Fomula = axis2Fomula;
	}

	public String getAxis2SearchResult() {
		return this.axis2SearchResult;
	}

	public void setAxis2SearchResult(String axis2SearchResult) {
		this.axis2SearchResult = axis2SearchResult;
	}

	public String getAxis2ShortName() {
		return this.axis2ShortName;
	}

	public void setAxis2ShortName(String axis2ShortName) {
		this.axis2ShortName = axis2ShortName;
	}

	public String getAxis3Fomula() {
		return this.axis3Fomula;
	}

	public void setAxis3Fomula(String axis3Fomula) {
		this.axis3Fomula = axis3Fomula;
	}

	public String getAxis3SearchResult() {
		return this.axis3SearchResult;
	}

	public void setAxis3SearchResult(String axis3SearchResult) {
		this.axis3SearchResult = axis3SearchResult;
	}

	public String getAxis3ShortName() {
		return this.axis3ShortName;
	}

	public void setAxis3ShortName(String axis3ShortName) {
		this.axis3ShortName = axis3ShortName;
	}

	public String getAxis4Fomula() {
		return this.axis4Fomula;
	}

	public void setAxis4Fomula(String axis4Fomula) {
		this.axis4Fomula = axis4Fomula;
	}

	public String getAxis4SearchResult() {
		return this.axis4SearchResult;
	}

	public void setAxis4SearchResult(String axis4SearchResult) {
		this.axis4SearchResult = axis4SearchResult;
	}

	public String getAxis4ShortName() {
		return this.axis4ShortName;
	}

	public void setAxis4ShortName(String axis4ShortName) {
		this.axis4ShortName = axis4ShortName;
	}

	public String getAxis5Fomula() {
		return this.axis5Fomula;
	}

	public void setAxis5Fomula(String axis5Fomula) {
		this.axis5Fomula = axis5Fomula;
	}

	public String getAxis5SearchResult() {
		return this.axis5SearchResult;
	}

	public void setAxis5SearchResult(String axis5SearchResult) {
		this.axis5SearchResult = axis5SearchResult;
	}

	public String getAxis5ShortName() {
		return this.axis5ShortName;
	}

	public void setAxis5ShortName(String axis5ShortName) {
		this.axis5ShortName = axis5ShortName;
	}

	public String getAxis6Fomula() {
		return this.axis6Fomula;
	}

	public void setAxis6Fomula(String axis6Fomula) {
		this.axis6Fomula = axis6Fomula;
	}

	public String getAxis6SearchResult() {
		return this.axis6SearchResult;
	}

	public void setAxis6SearchResult(String axis6SearchResult) {
		this.axis6SearchResult = axis6SearchResult;
	}

	public String getAxis6ShortName() {
		return this.axis6ShortName;
	}

	public void setAxis6ShortName(String axis6ShortName) {
		this.axis6ShortName = axis6ShortName;
	}

	public String getAxis7Fomula() {
		return this.axis7Fomula;
	}

	public void setAxis7Fomula(String axis7Fomula) {
		this.axis7Fomula = axis7Fomula;
	}

	public String getAxis7SearchResult() {
		return this.axis7SearchResult;
	}

	public void setAxis7SearchResult(String axis7SearchResult) {
		this.axis7SearchResult = axis7SearchResult;
	}

	public String getAxis7ShortName() {
		return this.axis7ShortName;
	}

	public void setAxis7ShortName(String axis7ShortName) {
		this.axis7ShortName = axis7ShortName;
	}

	public String getAxis8Fomula() {
		return this.axis8Fomula;
	}

	public void setAxis8Fomula(String axis8Fomula) {
		this.axis8Fomula = axis8Fomula;
	}

	public String getAxis8SearchResult() {
		return this.axis8SearchResult;
	}

	public void setAxis8SearchResult(String axis8SearchResult) {
		this.axis8SearchResult = axis8SearchResult;
	}

	public String getAxis8ShortName() {
		return this.axis8ShortName;
	}

	public void setAxis8ShortName(String axis8ShortName) {
		this.axis8ShortName = axis8ShortName;
	}

}