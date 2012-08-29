package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_CAREER_MASTER database table.
 * 
 */
@Entity
@Table(name="MISS_CAREER_MASTER")
public class MissCareerMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MCM_ID")
	private Long mcmId;

	@Column(name="MCM_NAME")
	private String mcmName;
/*
	//bi-directional many-to-one association to MissCandidate
	@OneToMany(mappedBy="missCareerMaster")
	private Set<MissCandidate> missCandidates;
*/
    public MissCareerMaster() {
    }

	public Long getMcmId() {
		return this.mcmId;
	}

	public void setMcmId(Long mcmId) {
		this.mcmId = mcmId;
	}

	public String getMcmName() {
		return this.mcmName;
	}

	public void setMcmName(String mcmName) {
		this.mcmName = mcmName;
	}

	/*public Set<MissCandidate> getMissCandidates() {
		return this.missCandidates;
	}

	public void setMissCandidates(Set<MissCandidate> missCandidates) {
		this.missCandidates = missCandidates;
	}*/
	
}