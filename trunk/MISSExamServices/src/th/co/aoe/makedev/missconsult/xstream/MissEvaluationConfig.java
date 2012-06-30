package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_EVALUATION_CONFIG database table.
 * 
 */
@XStreamAlias("MissEvaluationConfig")
public class MissEvaluationConfig extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long msId;

	private Long meId;

	private String columnCode;

	private String columnIsShow;

	private String columnName;

	private String mecType;
	private Long mecOrder;
    public Long getMecOrder() {
		return mecOrder;
	}

	public void setMecOrder(Long mecOrder) {
		this.mecOrder = mecOrder;
	}

	public MissEvaluationConfig() {
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

	public String getColumnCode() {
		return columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}

	public String getColumnIsShow() {
		return columnIsShow;
	}

	public void setColumnIsShow(String columnIsShow) {
		this.columnIsShow = columnIsShow;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getMecType() {
		return mecType;
	}

	public void setMecType(String mecType) {
		this.mecType = mecType;
	}


}