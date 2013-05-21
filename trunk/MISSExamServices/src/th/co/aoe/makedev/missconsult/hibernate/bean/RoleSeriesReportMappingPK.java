package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the role_series_report_mapping database table.
 * 
 */
@Embeddable
public class RoleSeriesReportMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="rc_id")
	private Long rcId;

	@Column(name="MS_ID")
	private Long msId;

	@Column(name="MS_ORDER")
	private Long msOrder;

	public RoleSeriesReportMappingPK() {
	}
	public Long getRcId() {
		return this.rcId;
	}
	public void setRcId(Long rcId) {
		this.rcId = rcId;
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
 
}