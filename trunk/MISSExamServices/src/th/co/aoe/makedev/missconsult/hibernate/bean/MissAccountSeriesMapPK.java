package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_ACCOUNT_SERIES_MAP database table.
 * 
 */
@Embeddable
public class MissAccountSeriesMapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MA_ID")
	private Long maId;

	@Column(name="MS_ID")
	private Long msId;

    public MissAccountSeriesMapPK() {
    }
	public Long getMaId() {
		return this.maId;
	}
	public void setMaId(Long maId) {
		this.maId = maId;
	}
	public Long getMsId() {
		return this.msId;
	}
	public void setMsId(Long msId) {
		this.msId = msId;
	}

	/*public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MissAccountSeriesMapPK)) {
			return false;
		}
		MissAccountSeriesMapPK castOther = (MissAccountSeriesMapPK)other;
		return 
			(this.maId == castOther.maId)
			&& (this.msId == castOther.msId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.maId;
		hash = hash * prime + this.msId;
		
		return hash;
    }*/
}