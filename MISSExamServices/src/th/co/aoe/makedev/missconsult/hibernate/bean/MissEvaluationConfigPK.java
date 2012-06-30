package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_EVALUATION_CONFIG database table.
 * 
 */
@Embeddable
public class MissEvaluationConfigPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MS_ID")
	private Long msId;

	@Column(name="ME_ID")
	private Long meId;
	
	@Column(name="COLUMN_CODE")
	private String columnCode;

	@Column(name="MEC_TYPE")
	private String mecType;

    public MissEvaluationConfigPK() {
    }
	public Long getMsId() {
		return this.msId;
	}
	public void setMsId(Long msId) {
		this.msId = msId;
	}
	public Long getMeId() {
		return this.meId;
	}
	public String getColumnCode() {
		return columnCode;
	}
	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}
	public String getMecType() {
		return mecType;
	}
	public void setMecType(String mecType) {
		this.mecType = mecType;
	}
	public void setMeId(Long meId) {
		this.meId = meId;
	}
}