package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_INDUSTRY_MASTER database table.
 * 
 */
@Entity
@Table(name="MISS_INDUSTRY_MASTER")
public class MissIndustryMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MIM_ID")
	private Long mimId;

	@Column(name="MIM_NAME")
	private String mimName;

	/*//bi-directional many-to-one association to MissCandidate
	@OneToMany(mappedBy="missIndustryMaster")
	private Set<MissCandidate> missCandidates;*/

    public MissIndustryMaster() {
    }

	public Long getMimId() {
		return this.mimId;
	}

	public void setMimId(Long mimId) {
		this.mimId = mimId;
	}

	public String getMimName() {
		return this.mimName;
	}

	public void setMimName(String mimName) {
		this.mimName = mimName;
	}
/*
	public Set<MissCandidate> getMissCandidates() {
		return this.missCandidates;
	}

	public void setMissCandidates(Set<MissCandidate> missCandidates) {
		this.missCandidates = missCandidates;
	}
	*/
}