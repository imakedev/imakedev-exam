package th.co.aoe.makedev.missconsult.domain;

import java.io.Serializable;

public class MissEptEvalBehavioralValue  implements Serializable {
	private static final long serialVersionUID = 1L;
	private  String meebvKey;
	private  int meebvValue;
	private  int meebvOrder;
	private  String message1;
	private  String message2;
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
	public int getMeebvOrder() {
		return meebvOrder;
	}
	public void setMeebvOrder(int meebvOrder) {
		this.meebvOrder = meebvOrder;
	}
	public String getMessage1() {
		return message1;
	}
	public void setMessage1(String message1) {
		this.message1 = message1;
	}
	public String getMessage2() {
		return message2;
	}
	public void setMessage2(String message2) {
		this.message2 = message2;
	}

}
