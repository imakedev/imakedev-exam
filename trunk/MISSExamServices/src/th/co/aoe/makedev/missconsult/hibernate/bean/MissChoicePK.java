package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_CHOICE database table.
 * 
 */
@Embeddable
public class MissChoicePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MC_NO")
	private Long mcNo;

	@Column(name="MQ_ID")
	private Long mqId;

    public MissChoicePK() {
    }
	public Long getMcNo() {
		return this.mcNo;
	}
	public void setMcNo(Long mcNo) {
		this.mcNo = mcNo;
	}
	public Long getMqId() {
		return this.mqId;
	}
	public void setMqId(Long mqId) {
		this.mqId = mqId;
	}

	
    
	
}