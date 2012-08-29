package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_CAREER_MASTER database table.
 * 
 */
@XStreamAlias("MissCareerMaster")
public class MissCareerMaster extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;;

	private Long mcmId;

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