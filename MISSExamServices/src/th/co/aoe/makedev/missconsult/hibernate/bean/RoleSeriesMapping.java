package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="role_series_mapping")
public class RoleSeriesMapping implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	@EmbeddedId
	private RoleSeriesMappingPK id;
	public RoleSeriesMappingPK getId() {
		return id;
	}
	public void setId(RoleSeriesMappingPK id) {
		this.id = id;
	}

}
