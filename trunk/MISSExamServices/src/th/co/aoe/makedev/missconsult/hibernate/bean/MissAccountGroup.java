package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_ACCOUNT_GROUP database table.
 * 
 */
@Entity
@Table(name="MISS_ACCOUNT_GROUP")
public class MissAccountGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MAG_ID")
	private Long magId;

	@Column(name="MAG_DESC")
	private String magDesc;

	@Column(name="MAG_NAME")
	private String magName;

	//bi-directional many-to-one association to MissAccountMapping
	/*@OneToMany(mappedBy="missAccountGroup")
	private Set<MissAccountMapping> missAccountMappings;
*/
    public MissAccountGroup() {
    }

	public Long getMagId() {
		return this.magId;
	}

	public void setMagId(Long magId) {
		this.magId = magId;
	}

	public String getMagDesc() {
		return this.magDesc;
	}

	public void setMagDesc(String magDesc) {
		this.magDesc = magDesc;
	}

	public String getMagName() {
		return this.magName;
	}

	public void setMagName(String magName) {
		this.magName = magName;
	}

/*	public Set<MissAccountMapping> getMissAccountMappings() {
		return this.missAccountMappings;
	}

	public void setMissAccountMappings(Set<MissAccountMapping> missAccountMappings) {
		this.missAccountMappings = missAccountMappings;
	}*/
	
}