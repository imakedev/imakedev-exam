package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_SALE_MAP database table.
 * 
 */
@Embeddable
public class MissSaleMapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MCONTACT_ID")
	private Long mcontactId;

	@Column(name="MA_ID")
	private Long maId;

    public MissSaleMapPK() {
    }
	public Long getMcontactId() {
		return this.mcontactId;
	}
	public void setMcontactId(Long mcontactId) {
		this.mcontactId = mcontactId;
	}
	public Long getMaId() {
		return this.maId;
	}
	public void setMaId(Long maId) {
		this.maId = maId;
	}

	
}