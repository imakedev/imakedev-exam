package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_EVALUATION_TEMPLATE database table.
 * 
 */
@Entity
@Table(name="MISS_EVALUATION_TEMPLATE")
public class MissEvaluationTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissEvaluationTemplatePK id;

	@Column(name="AXIS_1")
	private Long axis1;

	@Column(name="AXIS_2")
	private Long axis2;

	@Column(name="AXIS_3")
	private Long axis3;

	@Column(name="AXIS_4")
	private Long axis4;

	@Column(name="AXIS_5")
	private Long axis5;

	@Column(name="AXIS_6")
	private Long axis6;

	@Column(name="AXIS_7")
	private Long axis7;

	@Column(name="AXIS_8")
	private Long axis8;

	//bi-directional many-to-one association to MissExam
    @ManyToOne
	@JoinColumn(name="ME_ID",insertable=false,updatable=false)
	private MissExam missExam;

    public MissEvaluationTemplate() {
    }

	public MissEvaluationTemplatePK getId() {
		return this.id;
	}

	public void setId(MissEvaluationTemplatePK id) {
		this.id = id;
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
	
}