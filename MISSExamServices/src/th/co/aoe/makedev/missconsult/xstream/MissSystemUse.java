package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.sql.Timestamp;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_SYSTEM_USE database table.
 * 
 */
@XStreamAlias("MissSystemUse")
public class MissSystemUse  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long msystemType;

	private String msystemUserId;

	private java.util.Date msystemDateTimeLogin;

	private String msystemBrowserBand;

	private String msystemBrowserVersion;
	private String msystemBrowserFullVersion;

	private Timestamp msystemDateTimeLogout;

	private Long msystemWeek;

    public MissSystemUse() {
    }

	
	public String getMsystemBrowserBand() {
		return this.msystemBrowserBand;
	}

	public void setMsystemBrowserBand(String msystemBrowserBand) {
		this.msystemBrowserBand = msystemBrowserBand;
	}

	public String getMsystemBrowserVersion() {
		return this.msystemBrowserVersion;
	}

	public void setMsystemBrowserVersion(String msystemBrowserVersion) {
		this.msystemBrowserVersion = msystemBrowserVersion;
	}

	public Timestamp getMsystemDateTimeLogout() {
		return this.msystemDateTimeLogout;
	}

	public void setMsystemDateTimeLogout(Timestamp msystemDateTimeLogout) {
		this.msystemDateTimeLogout = msystemDateTimeLogout;
	}

	public Long getMsystemWeek() {
		return this.msystemWeek;
	}

	public void setMsystemWeek(Long msystemWeek) {
		this.msystemWeek = msystemWeek;
	}


	public Long getMsystemType() {
		return msystemType;
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
		return msystemDateTimeLogin;
	}


	public void setMsystemDateTimeLogin(java.util.Date msystemDateTimeLogin) {
		this.msystemDateTimeLogin = msystemDateTimeLogin;
	}


	public String getMsystemBrowserFullVersion() {
		return msystemBrowserFullVersion;
	}


	public void setMsystemBrowserFullVersion(String msystemBrowserFullVersion) {
		this.msystemBrowserFullVersion = msystemBrowserFullVersion;
	}

}