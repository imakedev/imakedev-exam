package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_ACCOUNT_SERIES_MAP database table.
 * 
 */
@Entity
@Table(name="MISS_ACCOUNT_SERIES_MAP")
public class MissAccountSeriesMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissAccountSeriesMapPK id;

	@Column(name="MASM_EXPIRE")
	private Timestamp masmExpire;

	@Column(name="MASM_ORDER_UNIT")
	private Long masmOrderUnit;

	@Column(name="MASM_STATUS")
	private String masmStatus;
	
	@Column(name="MASM_AVAILABLE")
	private String masmAvailable;
	

	//bi-directional many-to-one association to MissAccount
    @ManyToOne
	@JoinColumn(name="MA_ID",insertable=false,updatable=false) 
	private MissAccount missAccount;

	//bi-directional many-to-one association to MissSery
    @ManyToOne
	@JoinColumn(name="MS_ID",insertable=false,updatable=false)
	private MissSery missSery;

    public MissAccountSeriesMap() {
    }

	public MissAccountSeriesMapPK getId() {
		return this.id;
	}

	public void setId(MissAccountSeriesMapPK id) {
		this.id = id;
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

	public String getMasmAvailable() {
		return masmAvailable;
	}

	public void setMasmAvailable(String masmAvailable) {
		this.masmAvailable = masmAvailable;
	}
	
}