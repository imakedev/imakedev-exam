package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_POSITION_MASTER database table.
 * 
 */
@Entity
@Table(name="MISS_POSITION_MASTER")
public class MissPositionMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MPM_ID")
	private Long mpmId;

	@Column(name="MPM_NAME")
	private String mpmName;

	//bi-directional many-to-one association to MissDepartmentMaster
	@ManyToOne
	@JoinColumn(name="MDM_ID")
	private MissDepartmentMaster missDepartmentMaster;

	public MissPositionMaster() {
	}

	public Long getMpmId() {
		return this.mpmId;
	}

	public void setMpmId(Long mpmId) {
		this.mpmId = mpmId;
	}

	public String getMpmName() {
		return this.mpmName;
	}

	public void setMpmName(String mpmName) {
		this.mpmName = mpmName;
	}

	public MissDepartmentMaster getMissDepartmentMaster() {
		return this.missDepartmentMaster;
	}

	public void setMissDepartmentMaster(MissDepartmentMaster missDepartmentMaster) {
		this.missDepartmentMaster = missDepartmentMaster;
	}

}