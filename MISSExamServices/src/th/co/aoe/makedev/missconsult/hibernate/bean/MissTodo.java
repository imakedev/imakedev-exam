package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_TODO database table.
 * 
 */
@Entity
@Table(name="MISS_TODO")
public class MissTodo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MTODO_ID")
	private Long mtodoId;

	@Column(name="MTODO_RESPONSE")
	private String mtodoResponse;

	@Column(name="MTODO_TASK")
	private String mtodoTask;

	@Column(name="MTODO_TYPE")
	private String mtodoType;
	
	@Column(name="MTODO_REF")
	private Long mtodoRef;
	
	public Long getMtodoRef() {
		return mtodoRef;
	}

	public void setMtodoRef(Long mtodoRef) {
		this.mtodoRef = mtodoRef;
	}

	//bi-directional many-to-one association to MissAccount
    @ManyToOne
	@JoinColumn(name="MA_ID")
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