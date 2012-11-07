package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MissEptEvalBehavioralValuePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	@Column(name="MTR_ID")
	private  Long mtrId;
	
	@Column(name="MEEBV_ORDER")
	private  int meebvOrder; 
	
	@Column(name="MEEBG_ID")
	private  Long meebgId;

	public Long getMtrId() {
		return mtrId;
	}

	public void setMtrId(Long mtrId) {
		this.mtrId = mtrId;
	}

	public int getMeebvOrder() {
		return meebvOrder;
	}

	public void setMeebvOrder(int meebvOrder) {
		this.meebvOrder = meebvOrder;
	}

	public Long getMeebgId() {
		return meebgId;
	}

	public void setMeebgId(Long meebgId) {
		this.meebgId = meebgId;
	} 
	
}
