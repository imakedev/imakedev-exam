package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the role_series_report_mapping database table.
 * 
 */
@Entity
@Table(name="role_series_report_mapping")
public class RoleSeriesReportMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoleSeriesReportMappingPK id;

	public RoleSeriesReportMapping() {
	}

	public RoleSeriesReportMappingPK getId() {
		return this.id;
	}

	public void setId(RoleSeriesReportMappingPK id) {
		this.id = id;
	}

}