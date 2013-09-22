package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_DATA_IMAGE database table.
 * 
 */
@Entity
@Table(name="MISS_DATA_IMAGE")
public class MissDataImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissDataImagePK id;

	//bi-directional many-to-one association to MissTestResult
	@ManyToOne
	@JoinColumn(name="MTR_ID",insertable=false,updatable=false)
	private MissTestResult missTestResult;

	public MissDataImage() {
	}

	public MissDataImagePK getId() {
		return this.id;
	}

	public void setId(MissDataImagePK id) {
		this.id = id;
	}

	public MissTestResult getMissTestResult() {
		return this.missTestResult;
	}

	public void setMissTestResult(MissTestResult missTestResult) {
		this.missTestResult = missTestResult;
	}

}