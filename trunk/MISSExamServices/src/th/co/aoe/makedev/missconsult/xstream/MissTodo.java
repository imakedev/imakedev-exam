package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_TODO database table.
 * 
 */
@XStreamAlias("MissTodo")
public class MissTodo extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mtodoId;

	private String mtodoResponse;

	private String mtodoTask;

	private String mtodoType;
	
	private Long mtodoRef;

	public Long getMtodoRef() {
		return mtodoRef;
	}

	public void setMtodoRef(Long mtodoRef) {
		this.mtodoRef = mtodoRef;
	}

	@XStreamAlias("missAccount")
	private MissAccount missAccount;

    public MissTodo() {
    }

	public Long getMtodoId() {
		return this.mtodoId;
	}

	public void setMtodoId(Long mtodoId) {
		this.mtodoId = mtodoId;
	}

	public String getMtodoResponse() {
		return this.mtodoResponse;
	}

	public void setMtodoResponse(String mtodoResponse) {
		this.mtodoResponse = mtodoResponse;
	}

	public String getMtodoTask() {
		return this.mtodoTask;
	}

	public void setMtodoTask(String mtodoTask) {
		this.mtodoTask = mtodoTask;
	}

	public String getMtodoType() {
		return this.mtodoType;
	}

	public void setMtodoType(String mtodoType) {
		this.mtodoType = mtodoType;
	}

	public MissAccount getMissAccount() {
		return this.missAccount;
	}

	public void setMissAccount(MissAccount missAccount) {
		this.missAccount = missAccount;
	}
	
}