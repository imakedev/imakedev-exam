package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the MISS_SERY_PROBLEM database table.
 * 
 */
@Embeddable
public class MissSeryProblemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MS_ID")
	private Long msId;

	@Column(name="MCA_ID")
	private Long mcaId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="MSP_DATE_TIME")
	private java.util.Date mspDateTime;

    public MissSeryProblemPK() {
    }
	public Long getMsId() {
		return this.msId;
	}
	public void setMsId(Long msId) {
		this.msId = msId;
	}
	public Long getMcaId() {
		return this.mcaId;
	}
	public void setMcaId(Long mcaId) {
		this.mcaId = mcaId;
	}
	public java.util.Date getMspDateTime() {
		return this.mspDateTime;
	}
	public void setMspDateTime(java.util.Date mspDateTime) {
		this.mspDateTime = mspDateTime;
	} 
}