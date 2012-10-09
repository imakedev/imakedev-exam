package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_EVALUATION_CONFIG database table.
 * 
 */
@Entity
@Table(name="MISS_EVALUATION_CONFIG")
public class MissEvaluationConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissEvaluationConfigPK id;


	@Column(name="COLUMN_IS_SHOW")
	private String columnIsShow;
	@Column(name="ME_ID")
	private Long meId;
	@Column(name="COLUMN_NAME")
	private String columnName;

	@Column(name="MEC_ORDER")
	private Long mecOrder;
    public MissEvaluationConfig() {
    }

	public MissEvaluationConfigPK getId() {
		return this.id;
	}

	public void setId(MissEvaluationConfigPK id) {
		this.id = id;
	}

	public String getColumnIsShow() {
		return this.columnIsShow;
	}

	public void setColumnIsShow(String columnIsShow) {
		this.columnIsShow = columnIsShow;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Long getMecOrder() {
		return mecOrder;
	}

	public void setMecOrder(Long mecOrder) {
		this.mecOrder = mecOrder;
	}

	public Long getMeId() {
		return meId;
	}

	public void setMeId(Long meId) {
		this.meId = meId;
	}


}