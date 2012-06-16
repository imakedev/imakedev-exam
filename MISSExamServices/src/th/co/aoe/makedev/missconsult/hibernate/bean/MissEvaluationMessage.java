package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_EVALUATION_MESSAGE database table.
 * 
 */
@Entity
@Table(name="MISS_EVALUATION_MESSAGE")
public class MissEvaluationMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissEvaluationMessagePK id;

	@Column(name="CODE1_MESSAGE")
	private String code1Message;

	@Column(name="CODE2_MESSAGE")
	private String code2Message;

	@Column(name="CODE3_MESSAGE")
	private String code3Message;

	@Column(name="CODE4_MESSAGE")
	private String code4Message;

	@Column(name="CODE5_MESSAGE")
	private String code5Message;

	@Column(name="CODE6_MESSAGE")
	private String code6Message;

	@Column(name="CODE7_MESSAGE")
	private String code7Message;

	@Column(name="CODE8_MESSAGE")
	private String code8Message;

    public MissEvaluationMessage() {
    }

	public MissEvaluationMessagePK getId() {
		return this.id;
	}

	public void setId(MissEvaluationMessagePK id) {
		this.id = id;
	}
	
	public String getCode1Message() {
		return this.code1Message;
	}

	public void setCode1Message(String code1Message) {
		this.code1Message = code1Message;
	}

	public String getCode2Message() {
		return this.code2Message;
	}

	public void setCode2Message(String code2Message) {
		this.code2Message = code2Message;
	}

	public String getCode3Message() {
		return this.code3Message;
	}

	public void setCode3Message(String code3Message) {
		this.code3Message = code3Message;
	}

	public String getCode4Message() {
		return this.code4Message;
	}

	public void setCode4Message(String code4Message) {
		this.code4Message = code4Message;
	}

	public String getCode5Message() {
		return this.code5Message;
	}

	public void setCode5Message(String code5Message) {
		this.code5Message = code5Message;
	}

	public String getCode6Message() {
		return this.code6Message;
	}

	public void setCode6Message(String code6Message) {
		this.code6Message = code6Message;
	}

	public String getCode7Message() {
		return this.code7Message;
	}

	public void setCode7Message(String code7Message) {
		this.code7Message = code7Message;
	}

	public String getCode8Message() {
		return this.code8Message;
	}

	public void setCode8Message(String code8Message) {
		this.code8Message = code8Message;
	}

}