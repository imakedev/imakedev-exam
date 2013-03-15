package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleSeriesMappingPK  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="rc_id")
	private Long rcId; 

	@Column(name="MS_ID")
	private Long msId;

	public Long getRcId() {
		return rcId;
	}
	public RoleSeriesMappingPK() {
		//super();
		
	}
	/*public RoleSeriesMappingPK(Long rcId, Long msId) {
		super();
		this.rcId = rcId;
		this.msId = msId;
	}*/

	public void setRcId(Long rcId) {
		this.rcId = rcId;
	}

	public Long getMsId() {
		return msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}
 

}
