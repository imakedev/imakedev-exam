package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_DEPARTMENT_MASTER database table.
 * 
 */
@Entity
@Table(name="MISS_DEPARTMENT_MASTER")
public class MissDepartmentMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MDM_ID")
	private Long mdmId;

	@Column(name="MDM_NAME")
	private String mdmName;

	@Column(name="MDM_REF")
	private Long mdmRef;

	public MissDepartmentMaster() {
	}

	public Long getMdmId() {
		return this.mdmId;
	}

	public void setMdmId(Long mdmId) {
		this.mdmId = mdmId;
	}

	public String getMdmName() {
		return this.mdmName;
	}

	public void setMdmName(String mdmName) {
		this.mdmName = mdmName;
	}

	public Long getMdmRef() {
		return this.mdmRef;
	}

	public void setMdmRef(Long mdmRef) {
		this.mdmRef = mdmRef;
	}

}