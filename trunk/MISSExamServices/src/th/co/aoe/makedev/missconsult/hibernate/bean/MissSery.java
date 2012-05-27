package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_SERIES database table.
 * 
 */
@Entity
@Table(name="MISS_SERIES")
public class MissSery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MS_ID")
	private Long msId;

	@Column(name="MS_SERIES_NAME")
	private String msSeriesName;

	@Column(name="MS_UNIT_COST")
	private Long msUnitCost;

	/*//bi-directional many-to-one association to MissAccountSeriesMap
	@OneToMany(mappedBy="missSery")
	private Set<MissAccountSeriesMap> missAccountSeriesMaps;
*/
	//bi-directional many-to-one association to MissCandidate
	/*@OneToMany(mappedBy="missSery")
	private Set<MissCandidate> missCandidates;
*/
/*	//bi-directional many-to-one association to MissSurveySend
	@OneToMany(mappedBy="missSery")
	private Set<MissSurveySend> missSurveySends;

	//bi-directional many-to-one association to MissTest
	@OneToMany(mappedBy="missSery")
	private Set<MissTest> missTests;*/

    public MissSery() {
    }

	public Long getMsId() {
		return this.msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public String getMsSeriesName() {
		return this.msSeriesName;
	}

	public void setMsSeriesName(String msSeriesName) {
		this.msSeriesName = msSeriesName;
	}

	public Long getMsUnitCost() {
		return this.msUnitCost;
	}

	public void setMsUnitCost(Long msUnitCost) {
		this.msUnitCost = msUnitCost;
	}

	/*public Set<MissAccountSeriesMap> getMissAccountSeriesMaps() {
		return this.missAccountSeriesMaps;
	}

	public void setMissAccountSeriesMaps(Set<MissAccountSeriesMap> missAccountSeriesMaps) {
		this.missAccountSeriesMaps = missAccountSeriesMaps;
	}
	
	public Set<MissCandidate> getMissCandidates() {
		return this.missCandidates;
	}

	public void setMissCandidates(Set<MissCandidate> missCandidates) {
		this.missCandidates = missCandidates;
	}
	
	public Set<MissSurveySend> getMissSurveySends() {
		return this.missSurveySends;
	}

	public void setMissSurveySends(Set<MissSurveySend> missSurveySends) {
		this.missSurveySends = missSurveySends;
	}
	
	public Set<MissTest> getMissTests() {
		return this.missTests;
	}

	public void setMissTests(Set<MissTest> missTests) {
		this.missTests = missTests;
	}*/
	
}