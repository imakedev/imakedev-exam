package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * The persistent class for the MISS_ACCOUNT_SERIES_MAP database table.
 * 
 */
@Entity
@Table(name="MISS_EPT_EVAL_BEHAVIORAL_VALUE")
public class MissEptEvalBehavioralValue  implements Serializable {
	private static final long serialVersionUID = 1L;
	/*@Id
	@Column(name="MTR_ID")
	private  Long mtrId;*/
	@EmbeddedId
	private MissEptEvalBehavioralValuePK id;
	 
	@Column(name="MEEB_KEY")
	private  String meebvKey;
	
	public MissEptEvalBehavioralValuePK getId() {
		return id;
	}
	public void setId(MissEptEvalBehavioralValuePK id) {
		this.id = id;
	}
	@Column(name="MEEBV_VALUE")
	private  int meebvValue;
	 
	/*@Column(name="MEEBV_ORDER")
	private  int meebvOrder; 
	
	@Column(name="MEEBG_ID")
	private  Long meebgId; 
	 */

	public String getMeebvKey() {
		return meebvKey;
	}
	public void setMeebvKey(String meebvKey) {
		this.meebvKey = meebvKey;
	}
	public int getMeebvValue() {
		return meebvValue;
	}
	public void setMeebvValue(int meebvValue) {
		this.meebvValue = meebvValue;
	}
	/*public int getMeebvOrder() {
		return meebvOrder;
	}
	public void setMeebvOrder(int meebvOrder) {
		this.meebvOrder = meebvOrder;
	}
	public Long getMtrId() {
		return mtrId;
	}
	public void setMtrId(Long mtrId) {
		this.mtrId = mtrId;
	}
	public Long getMeebgId() {
		return meebgId;
	}
	public void setMeebgId(Long meebgId) {
		this.meebgId = meebgId;
	}
	*/
}
