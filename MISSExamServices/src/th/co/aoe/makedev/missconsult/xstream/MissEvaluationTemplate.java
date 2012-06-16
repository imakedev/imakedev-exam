package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_EVALUATION_TEMPLATE database table.
 * 
 */
@XStreamAlias("MissEvaluationTemplate")
public class MissEvaluationTemplate  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long msId;

	private Long meId;

	private Long mqId;

	private Long mcId;
	
	private Long axis1;

	private Long axis2;

	private Long axis3;

	private Long axis4;

	private Long axis5;

	private Long axis6;

	private Long axis7;

	private Long axis8;

	@XStreamAlias("missExam")
	private MissExam missExam;

    public MissEvaluationTemplate() {
    }


	public Long getAxis1() {
		return this.axis1;
	}

	public void setAxis1(Long axis1) {
		this.axis1 = axis1;
	}

	public Long getAxis2() {
		return this.axis2;
	}

	public void setAxis2(Long axis2) {
		this.axis2 = axis2;
	}

	public Long getAxis3() {
		return this.axis3;
	}

	public void setAxis3(Long axis3) {
		this.axis3 = axis3;
	}

	public Long getAxis4() {
		return this.axis4;
	}

	public void setAxis4(Long axis4) {
		this.axis4 = axis4;
	}

	public Long getAxis5() {
		return this.axis5;
	}

	public void setAxis5(Long axis5) {
		this.axis5 = axis5;
	}

	public Long getAxis6() {
		return this.axis6;
	}

	public void setAxis6(Long axis6) {
		this.axis6 = axis6;
	}

	public Long getAxis7() {
		return this.axis7;
	}

	public void setAxis7(Long axis7) {
		this.axis7 = axis7;
	}

	public Long getAxis8() {
		return this.axis8;
	}

	public void setAxis8(Long axis8) {
		this.axis8 = axis8;
	}

	public MissExam getMissExam() {
		return this.missExam;
	}

	public void setMissExam(MissExam missExam) {
		this.missExam = missExam;
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


	public Long getMqId() {
		return mqId;
	}


	public void setMqId(Long mqId) {
		this.mqId = mqId;
	}


	public Long getMcId() {
		return mcId;
	}


	public void setMcId(Long mcId) {
		this.mcId = mcId;
	}
	
}