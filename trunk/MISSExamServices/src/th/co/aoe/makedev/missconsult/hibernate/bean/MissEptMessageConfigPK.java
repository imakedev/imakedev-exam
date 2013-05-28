package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the MISS_EPT_MESSAGE_CONFIG database table.
 * 
 */
@Embeddable
public class MissEptMessageConfigPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CODE")
	private String code;

	@Column(name="MEMC_LANG")
	private String memcLang;

	@Column(name="MEMC_ORDER")
	private int memcOrder;

	public MissEptMessageConfigPK() {
	}
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMemcLang() {
		return this.memcLang;
	}
	public void setMemcLang(String memcLang) {
		this.memcLang = memcLang;
	}
	public int getMemcOrder() {
		return this.memcOrder;
	}
	public void setMemcOrder(int memcOrder) {
		this.memcOrder = memcOrder;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MissEptMessageConfigPK)) {
			return false;
		}
		MissEptMessageConfigPK castOther = (MissEptMessageConfigPK)other;
		return 
			this.code.equals(castOther.code)
			&& this.memcLang.equals(castOther.memcLang)
			&& (this.memcOrder == castOther.memcOrder);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.code.hashCode();
		hash = hash * prime + this.memcLang.hashCode();
		hash = hash * prime + this.memcOrder;
		
		return hash;
	}
}