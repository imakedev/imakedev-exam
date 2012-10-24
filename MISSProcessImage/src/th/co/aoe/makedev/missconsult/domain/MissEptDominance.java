package th.co.aoe.makedev.missconsult.domain;

import java.io.Serializable;


public class MissEptDominance implements Serializable {
	private static final long serialVersionUID = 1L;

	private int mtrId;
	private String mepDominance;
	private String mepSubDominance;

	public MissEptDominance() {
	}

	public int getMtrId() {
		return this.mtrId;
	}

	public void setMtrId(int mtrId) {
		this.mtrId = mtrId;
	}

	public String getMepDominance() {
		return this.mepDominance;
	}

	public void setMepDominance(String mepDominance) {
		this.mepDominance = mepDominance;
	}

	public String getMepSubDominance() {
		return this.mepSubDominance;
	}

	public void setMepSubDominance(String mepSubDominance) {
		this.mepSubDominance = mepSubDominance;
	}


}