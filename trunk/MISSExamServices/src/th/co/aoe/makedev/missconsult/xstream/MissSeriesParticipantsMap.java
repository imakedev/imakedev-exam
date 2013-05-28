package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_SERIES_PARTICIPANTS_MAP database table.
 * 
 */
@XStreamAlias("MissSeriesParticipantsMap")
public class MissSeriesParticipantsMap extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	@XStreamAlias("missSery")
	private MissSery missSery;

	private Long msId;
 
	private String mspmGroupName;
 
	private Long mspmOrder;
 
	private Long mspmGroupAmount;
	
	private String[] mspmGroupNameArray;
	 
	private Integer[] mspmOrderArray;
 
	private Integer[] mspmGroupAmountArray;

	public MissSery getMissSery() {
		return missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}

	public Long getMsId() {
		return msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public String getMspmGroupName() {
		return mspmGroupName;
	}

	public void setMspmGroupName(String mspmGroupName) {
		this.mspmGroupName = mspmGroupName;
	}

	public Long getMspmOrder() {
		return mspmOrder;
	}

	public void setMspmOrder(Long mspmOrder) {
		this.mspmOrder = mspmOrder;
	}

	public Long getMspmGroupAmount() {
		return mspmGroupAmount;
	}

	public void setMspmGroupAmount(Long mspmGroupAmount) {
		this.mspmGroupAmount = mspmGroupAmount;
	}

	public String[] getMspmGroupNameArray() {
		return mspmGroupNameArray;
	}

	public void setMspmGroupNameArray(String[] mspmGroupNameArray) {
		this.mspmGroupNameArray = mspmGroupNameArray;
	}

	public Integer[] getMspmOrderArray() {
		return mspmOrderArray;
	}

	public void setMspmOrderArray(Integer[] mspmOrderArray) {
		this.mspmOrderArray = mspmOrderArray;
	}

	public Integer[] getMspmGroupAmountArray() {
		return mspmGroupAmountArray;
	}

	public void setMspmGroupAmountArray(Integer[] mspmGroupAmountArray) {
		this.mspmGroupAmountArray = mspmGroupAmountArray;
	}
	
	
}