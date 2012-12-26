package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_ACCOUNT_SERIES_MAP database table.
 * 
 */
@Embeddable
public class MissCareerMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MA_ID")
	private Long maId; 

	@Column(name="MCM_ID")
	private Long mcmId;

	public Long getMaId() {
		return maId;
	}

	public void setMaId(Long maId) {
		this.maId = maId;
	}

	public Long getMcmId() {
		return mcmId;
	}

	public void setMcmId(Long mcmId) {
		this.mcmId = mcmId;
	} 
	
	
}
