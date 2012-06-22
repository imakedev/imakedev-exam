package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.MissManual;

public class ManualForm extends CommonForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private MissManual missManual;
	public ManualForm(){
		missManual=new MissManual();
	}
	/*private String msIds;
	public String getMsIds() {
		return msIds;
	}
	public void setMsIds(String msIds) {
		this.msIds = msIds;
	}*/
	public MissManual getMissManual() {
		return missManual;
	}
	public void setMissManual(MissManual missManual) {
		this.missManual = missManual;
	}
	
}
