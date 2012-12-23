package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_SURVEY_SEND database table.
 * 
 */
@Entity
@Table(name="MISS_SURVEY_SEND")
public class MissSurveySend implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MSS_ID")
	private Long mssId;

	@Column(name="MS_EMAIL")
	private String msEmail;
	
	@Column(name="MS_NAME")
	private String msName;

	//bi-directional many-to-one association to MissSery
    @ManyToOne
	@JoinColumn(name="MS_ID")
	private MissSery missSery;
    
    @ManyToOne
   	@JoinColumn(name="MCA_ID")
   	private MissCandidate missCandidate;

    public MissSurveySend() {
    }

	public Long getMssId() {
		return this.mssId;
	}

	public void setMssId(Long mssId) {
		this.mssId = mssId;
	}

	public String getMsEmail() {
		return this.msEmail;
	}

	public void setMsEmail(String msEmail) {
		this.msEmail = msEmail;
	}

	public MissSery getMissSery() {
		return this.missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}

	public MissCandidate getMissCandidate() {
		return missCandidate;
	}

	public void setMissCandidate(MissCandidate missCandidate) {
		this.missCandidate = missCandidate;
	}

	public String getMsName() {
		return msName;
	}

	public void setMsName(String msName) {
		this.msName = msName;
	}
	
}