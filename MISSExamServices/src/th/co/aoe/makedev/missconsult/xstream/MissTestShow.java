package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_TEST_SHOW database table.
 * 
 */
@XStreamAlias("MissTestShow")
public class MissTestShow  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mcaId;

	private Long msId;

	private Long meId;


	private String mtsColumn;

	private String mtsType;

	private String mtsValue;
	private Long mtsOrder;
	private String columnIsShow;

	public String getColumnIsShow() {
		return columnIsShow;
	}

	public void setColumnIsShow(String columnIsShow) {
		this.columnIsShow = columnIsShow;
	}

	public Long getMtsOrder() {
		return mtsOrder;
	}

	public void setMtsOrder(Long mtsOrder) {
		this.mtsOrder = mtsOrder;
	}
    public MissTestShow() {
    }

	

	public Long getMcaId() {
		return mcaId;
	}



	public void setMcaId(Long mcaId) {
		this.mcaId = mcaId;
	}



	public Long getMsId() {
		return msId;
	}



	public void setMsId(Long msId) {
		this.msId = msId;
	}



	public Long getMeId() {
		return meId;
	}



	public void setMeId(Long meId) {
		this.meId = meId;
	}



	public String getMtsColumn() {
		return this.mtsColumn;
	}

	public void setMtsColumn(String mtsColumn) {
		this.mtsColumn = mtsColumn;
	}

	public String getMtsType() {
		return this.mtsType;
	}

	public void setMtsType(String mtsType) {
		this.mtsType = mtsType;
	}

	public String getMtsValue() {
		return this.mtsValue;
	}

	public void setMtsValue(String mtsValue) {
		this.mtsValue = mtsValue;
	}

}