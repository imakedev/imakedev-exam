package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the MISS_SERY_ORDER database table.
 * 
 */
@Embeddable
public class MissSeryOrderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MS_ID")
	private Long msId;

	@Column(name="MA_ID")
	private Long maId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="MSO_DATE_TIME")
	private java.util.Date msoDateTime;

    public MissSeryOrderPK() {
    }
	public Long getMsId() {
		return this.msId;
	}
	public void setMsId(Long msId) {
		this.msId = msId;
	}
	public Long getMaId() {
		return this.maId;
	}
	public void setMaId(Long maId) {
		this.maId = maId;
	}
	public java.util.Date getMsoDateTime() {
		return this.msoDateTime;
	}
	public void setMsoDateTime(java.util.Date msoDateTime) {
		this.msoDateTime = msoDateTime;
	} 
}