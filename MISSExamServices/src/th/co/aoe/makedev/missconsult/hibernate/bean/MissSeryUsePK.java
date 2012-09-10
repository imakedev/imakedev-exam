package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the MISS_SERY_USE database table.
 * 
 */
@Embeddable
public class MissSeryUsePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MS_ID")
	private Long msId;

	@Column(name="MCA_ID")
	private Long mcaId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="MSU_DDATE_TIME")
	private java.util.Date msuDdateTime;

    public MissSeryUsePK() {
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
	public java.util.Date getMsuDdateTime() {
		return this.msuDdateTime;
	}
	public void setMsuDdateTime(java.util.Date msuDdateTime) {
		this.msuDdateTime = msuDdateTime;
	} 
}