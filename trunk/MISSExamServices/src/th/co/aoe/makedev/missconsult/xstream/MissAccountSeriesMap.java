package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.sql.Timestamp;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_ACCOUNT_SERIES_MAP database table.
 * 
 */
@XStreamAlias("MissAccountSeriesMap")
public class MissAccountSeriesMap extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long maId;

	private Long msId;

	@XStreamAlias("masmExpire")
	private Timestamp masmExpire;

	private Long masmOrderUnit;

	private String masmStatus;

	@XStreamAlias("missAccount")
	private MissAccount missAccount;

	@XStreamAlias("missSery")
	private MissSery missSery;

    public MissAccountSeriesMap() {
    }

	
	
	public Long getMaId() {
		return maId;
	}



	public void setMaId(Long maId) {
		this.maId = maId;
	}



	public Long getMsId() {
		return msId;
	}



	public void setMsId(Long msId) {
		this.msId = msId;
	}



	public Timestamp getMasmExpire() {
		return this.masmExpire;
	}

	public void setMasmExpire(Timestamp masmExpire) {
		this.masmExpire = masmExpire;
	}

	public Long getMasmOrderUnit() {
		return this.masmOrderUnit;
	}

	public void setMasmOrderUnit(Long masmOrderUnit) {
		this.masmOrderUnit = masmOrderUnit;
	}

	public String getMasmStatus() {
		return this.masmStatus;
	}

	public void setMasmStatus(String masmStatus) {
		this.masmStatus = masmStatus;
	}

	public MissAccount getMissAccount() {
		return this.missAccount;
	}

	public void setMissAccount(MissAccount missAccount) {
		this.missAccount = missAccount;
	}
	
	public MissSery getMissSery() {
		return this.missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}
	
}