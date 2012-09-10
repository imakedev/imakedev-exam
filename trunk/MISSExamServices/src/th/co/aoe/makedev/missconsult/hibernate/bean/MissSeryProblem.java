package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_SERY_PROBLEM database table.
 * 
 */
@Entity
@Table(name="MISS_SERY_PROBLEM")
public class MissSeryProblem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissSeryProblemPK id;

	@Column(name="MSP_MESSAGE")
	private String mspMessage;

	@Column(name="MSP_TYPE")
	private String mspType;

	@Column(name="MSP_WEEK")
	private Long mspWeek;

    public MissSeryProblem() {
    }

	public MissSeryProblemPK getId() {
		return this.id;
	}

	public void setId(MissSeryProblemPK id) {
		this.id = id;
	}
	
	public String getMspMessage() {
		return this.mspMessage;
	}

	public void setMspMessage(String mspMessage) {
		this.mspMessage = mspMessage;
	}

	public String getMspType() {
		return this.mspType;
	}

	public void setMspType(String mspType) {
		this.mspType = mspType;
	}

	public Long getMspWeek() {
		return this.mspWeek;
	}

	public void setMspWeek(Long mspWeek) {
		this.mspWeek = mspWeek;
	}

}