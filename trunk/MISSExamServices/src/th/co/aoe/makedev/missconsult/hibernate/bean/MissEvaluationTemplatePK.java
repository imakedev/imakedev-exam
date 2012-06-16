package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_EVALUATION_TEMPLATE database table.
 * 
 */
@Embeddable
public class MissEvaluationTemplatePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MS_ID")
	private Long msId;

	@Column(name="ME_ID")
	private Long meId;

	@Column(name="MQ_ID")
	private Long mqId;

	@Column(name="MC_ID")
	private Long mcId;

    public MissEvaluationTemplatePK() {
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
	public void setMeId(Long meId) {
		this.meId = meId;
	}
	public Long getMqId() {
		return this.mqId;
	}
	public void setMqId(Long mqId) {
		this.mqId = mqId;
	}
	public Long getMcId() {
		return this.mcId;
	}
	public void setMcId(Long mcId) {
		this.mcId = mcId;
	}

}