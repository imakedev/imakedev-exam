package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_SERIES_MAP database table.
 * 
 */
@Embeddable
public class MissSeriesMapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ME_ID")
	private Long meId;
/*	@ManyToOne
	@JoinColumn(name="ME_ID")
	private MissExam missExam;*/

	@Column(name="MS_ID")
	private Long msId;

	/*@ManyToOne
	@JoinColumn(name="MS_ID")	 
	private MissSery missSery;*/
	
    public MissSeriesMapPK() {
    }
	public Long getMeId() {
		return this.meId;
	}
	public void setMeId(Long meId) {
		this.meId = meId;
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
		if (!(other instanceof MissSeriesMapPK)) {
			return false;
		}
		MissSeriesMapPK castOther = (MissSeriesMapPK)other;
		return 
			(this.meId == castOther.meId)
			&& (this.msId == castOther.msId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.meId;
		hash = hash * prime + this.msId;
		
		return hash;
    }*/
}