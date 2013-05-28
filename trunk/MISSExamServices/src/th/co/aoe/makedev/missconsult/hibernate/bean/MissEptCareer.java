package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_EPT_CAREER database table.
 * 
 */
@Entity
@Table(name="MISS_EPT_CAREER")
public class MissEptCareer implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissEptCareerPK id;

	@Column(name="MEC_CAREER_NAME")
	private String mecCareerName;

	public MissEptCareer() {
	}

	public MissEptCareerPK getId() {
		return this.id;
	}

	public void setId(MissEptCareerPK id) {
		this.id = id;
	}

	public String getMecCareerName() {
		return this.mecCareerName;
	}

	public void setMecCareerName(String mecCareerName) {
		this.mecCareerName = mecCareerName;
	}

}