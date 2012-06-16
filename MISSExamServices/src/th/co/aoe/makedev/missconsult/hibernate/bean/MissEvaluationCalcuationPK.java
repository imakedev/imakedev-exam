package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_EVALUATION_CALCUATION database table.
 * 
 */
@Embeddable
public class MissEvaluationCalcuationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MS_ID")
	private Long msId;

	@Column(name="ME_ID")
	private Long meId;

    public MissEvaluationCalcuationPK() {
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

	 
}