package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the MISS_SYSTEM_USE database table.
 * 
 */
@Embeddable
public class MissSystemUsePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MSYSTEM_TYPE")
	private Long msystemType;

	@Column(name="MSYSTEM_USER_ID")
	private String msystemUserId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="MSYSTEM_DATE_TIME_LOGIN")
	private java.util.Date msystemDateTimeLogin;

    public MissSystemUsePK() {
    }
	public Long getMsystemType() {
		return this.msystemType;
	}
	public void setMsystemType(Long msystemType) {
		this.msystemType = msystemType;
	}
	 
	public String getMsystemUserId() {
		return msystemUserId;
	}
	public void setMsystemUserId(String msystemUserId) {
		this.msystemUserId = msystemUserId;
	}
	public java.util.Date getMsystemDateTimeLogin() {
		return this.msystemDateTimeLogin;
	}
	public void setMsystemDateTimeLogin(java.util.Date msystemDateTimeLogin) {
		this.msystemDateTimeLogin = msystemDateTimeLogin;
	} 
}