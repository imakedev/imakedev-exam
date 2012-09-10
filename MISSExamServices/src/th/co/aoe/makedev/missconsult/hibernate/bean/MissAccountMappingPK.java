package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_ACCOUNT_MAPPING database table.
 * 
 */
@Embeddable
public class MissAccountMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MAG_ID")
	private Long magId;

	@Column(name="MA_ID")
	private Long maId;

    public MissAccountMappingPK() {
    }
	public Long getMagId() {
		return this.magId;
	}
	public void setMagId(Long magId) {
		this.magId = magId;
	}
	public Long getMaId() {
		return this.maId;
	}
	public void setMaId(Long maId) {
		this.maId = maId;
	}
}