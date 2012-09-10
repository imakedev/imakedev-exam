package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_ACCOUNT_MAPPING database table.
 * 
 */
@Entity
@Table(name="MISS_ACCOUNT_MAPPING")
public class MissAccountMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissAccountMappingPK id;

	//bi-directional many-to-one association to MissAccountGroup
    @ManyToOne
	@JoinColumn(name="MAG_ID",insertable=false,updatable=false)
	private MissAccountGroup missAccountGroup;

    public MissAccountMapping() {
    }

	public MissAccountMappingPK getId() {
		return this.id;
	}

	public void setId(MissAccountMappingPK id) {
		this.id = id;
	}
	
	public MissAccountGroup getMissAccountGroup() {
		return this.missAccountGroup;
	}

	public void setMissAccountGroup(MissAccountGroup missAccountGroup) {
		this.missAccountGroup = missAccountGroup;
	}
	
}