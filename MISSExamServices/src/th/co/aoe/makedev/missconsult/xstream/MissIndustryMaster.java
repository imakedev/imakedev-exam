package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_INDUSTRY_MASTER database table.
 * 
 */
@XStreamAlias("MissIndustryMaster")
public class MissIndustryMaster extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;;

	
	private Long mimId;

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