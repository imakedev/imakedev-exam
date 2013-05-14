package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_REPORT_ATTACH database table.
 * 
 */
@Embeddable
public class MissReportAttachPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MS_ID")
	private Long msId;

	@Column(name="MS_ORDER")
	private Long msOrder;
	
	@Column(name="MRA_LANG")
	private String mraLang;

	public MissReportAttachPK() {
	}
	public Long getMsId() {
		return this.msId;
	}
	public void setMsId(Long msId) {
		this.msId = msId;
	}
	public Long getMsOrder() {
		return this.msOrder;
	}
	public void setMsOrder(Long msOrder) {
		this.msOrder = msOrder;
	}
	public String getMraLang() {
		return mraLang;
	}
	public void setMraLang(String mraLang) {
		this.mraLang = mraLang;
	}

 

	 
}