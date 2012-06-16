package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_EVALUATION_MESSAGE database table.
 * 
 */
@XStreamAlias("MissEvaluationTemplate")
public class MissEvaluationMessage  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long msId;

	private Long meId;

	private String code1Message;

	private String code2Message;

	private String code3Message;

	private String code4Message;

	private String code5Message;

	private String code6Message;

	private String code7Message;

	private String code8Message;

    public MissEvaluationMessage() {
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

	public Long getMsId() {
		return msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public Long getMeId() {
		return meId;
	}

	public void setMeId(Long meId) {
		this.meId = meId;
	}

}