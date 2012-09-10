package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_ACCOUNT_GROUP database table.
 * 
 */
@XStreamAlias("MissAccountGroup")
public class MissAccountGroup  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L; 
	 
	private Long magId;

	private String magDesc;

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