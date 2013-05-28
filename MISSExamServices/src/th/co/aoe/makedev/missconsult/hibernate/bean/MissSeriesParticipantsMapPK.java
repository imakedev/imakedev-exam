package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_SERIES_PARTICIPANTS_MAP database table.
 * 
 */
@Embeddable
public class MissSeriesParticipantsMapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MS_ID")
	private Long msId;

	@Column(name="MSPM_GROUP_NAME")
	private String mspmGroupName;

	@Column(name="MSPM_ORDER")
	private Long mspmOrder;

	@Column(name="MSPM_GROUP_AMOUNT")
	private Long mspmGroupAmount;

	public MissSeriesParticipantsMapPK() {
	}
	public Long getMsId() {
		return this.msId;
	}
	public void setMsId(Long msId) {
		this.msId = msId;
	}
	public String getMspmGroupName() {
		return this.mspmGroupName;
	}
	public void setMspmGroupName(String mspmGroupName) {
		this.mspmGroupName = mspmGroupName;
	}
	public Long getMspmOrder() {
		return this.mspmOrder;
	}
	public void setMspmOrder(Long mspmOrder) {
		this.mspmOrder = mspmOrder;
	}
	public Long getMspmGroupAmount() {
		return this.mspmGroupAmount;
	}
	public void setMspmGroupAmount(Long mspmGroupAmount) {
		this.mspmGroupAmount = mspmGroupAmount;
	}

	
	
}