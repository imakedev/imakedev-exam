package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_SERIES_PARTICIPANTS_MAP database table.
 * 
 */
@Entity
@Table(name="MISS_SERIES_PARTICIPANTS_MAP")
public class MissSeriesParticipantsMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissSeriesParticipantsMapPK id;

	//bi-directional many-to-one association to MissSery
	@ManyToOne
	@JoinColumn(name="MS_ID",insertable=false,updatable=false)
	private MissSery missSery;

	public MissSeriesParticipantsMap() {
	}

	public MissSeriesParticipantsMapPK getId() {
		return this.id;
	}

	public void setId(MissSeriesParticipantsMapPK id) {
		this.id = id;
	}

	public MissSery getMissSery() {
		return this.missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}

}