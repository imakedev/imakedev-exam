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

	//bi-directional many-to-one association to MissSery
    @ManyToOne
	@JoinColumn(name="MS_ID")
	private MissSery missSery;

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
	
}