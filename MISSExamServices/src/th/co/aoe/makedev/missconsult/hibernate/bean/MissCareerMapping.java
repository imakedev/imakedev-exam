package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the MISS_CAREER_MASTER database table.
 * 
 */
@Entity
@Table(name="MISS_CAREER_MAPPING")
public class MissCareerMapping implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private MissCareerMappingPK id;
	public MissCareerMappingPK getId() {
		return id;
	}
	public void setId(MissCareerMappingPK id) {
		this.id = id;
	}
	
	
}
