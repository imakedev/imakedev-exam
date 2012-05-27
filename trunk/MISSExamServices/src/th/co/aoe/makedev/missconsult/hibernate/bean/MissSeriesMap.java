package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_SERIES_MAP database table.
 * 
 */
@Entity
@Table(name="MISS_SERIES_MAP")
public class MissSeriesMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissSeriesMapPK id;
	
	@Column(name="MSM_ORDER")
	private Long msmOrder;
	
    public MissSeriesMap() {
    }

	public MissSeriesMapPK getId() {
		return this.id;
	}

	public void setId(MissSeriesMapPK id) {
		this.id = id;
	}

	public Long getMsmOrder() {
		return msmOrder;
	}

	public void setMsmOrder(Long msmOrder) {
		this.msmOrder = msmOrder;
	}
	
}