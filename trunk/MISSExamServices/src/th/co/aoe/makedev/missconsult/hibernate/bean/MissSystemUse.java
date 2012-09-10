package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_SYSTEM_USE database table.
 * 
 */
@Entity
@Table(name="MISS_SYSTEM_USE")
public class MissSystemUse implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissSystemUsePK id;

	@Column(name="MSYSTEM_BROWSER_BAND")
	private String msystemBrowserBand;

	@Column(name="MSYSTEM_BROWSER_VERSION")
	private String msystemBrowserVersion;
	
	@Column(name="MSYSTEM_BROWSER_FULL_VERSION")
	private String msystemBrowserFullVersion;

	@Column(name="MSYSTEM_DATE_TIME_LOGOUT")
	private Timestamp msystemDateTimeLogout;

	@Column(name="MSYSTEM_WEEK")
	private Long msystemWeek;

    public MissSystemUse() {
    }

	public MissSystemUsePK getId() {
		return this.id;
	}

	public void setId(MissSystemUsePK id) {
		this.id = id;
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

	public String getMsystemBrowserFullVersion() {
		return msystemBrowserFullVersion;
	}

	public void setMsystemBrowserFullVersion(String msystemBrowserFullVersion) {
		this.msystemBrowserFullVersion = msystemBrowserFullVersion;
	}

}