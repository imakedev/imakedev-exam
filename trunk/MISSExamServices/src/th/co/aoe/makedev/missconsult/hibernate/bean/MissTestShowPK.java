package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_TEST_SHOW database table.
 * 
 */
@Embeddable
public class MissTestShowPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MCA_ID")
	private Long mcaId;

	@Column(name="MS_ID")
	private Long msId;

	/*@Column(name="ME_ID")
	private Long meId;*/

	@Column(name="MTS_COLUMN")
	private String mtsColumn;

	@Column(name="MTS_TYPE")
	private String mtsType;

    public String getMtsColumn() {
		return mtsColumn;
	}
	public void setMtsColumn(String mtsColumn) {
		this.mtsColumn = mtsColumn;
	}
	public String getMtsType() {
		return mtsType;
	}
	public void setMtsType(String mtsType) {
		this.mtsType = mtsType;
	}
	public MissTestShowPK() {
    }
	public Long getMcaId() {
		return this.mcaId;
	}
	public void setMcaId(Long mcaId) {
		this.mcaId = mcaId;
	}
	public Long getMsId() {
		return this.msId;
	}
	public void setMsId(Long msId) {
		this.msId = msId;
	}
	/*public Long getMeId() {
		return this.meId;
	}
	public void setMeId(Long meId) {
		this.meId = meId;
	}*/

}