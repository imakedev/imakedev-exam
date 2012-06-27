package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_QUESTION database table.
 * 
 */
@Entity
@Table(name="MISS_QUESTION")
public class MissQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MQ_ID")
	private Long mqId;

	@Column(name="MQ_IMG_ENG1")
	private String mqImgEng1;

	@Column(name="MQ_IMG_ENG2")
	private String mqImgEng2;

	@Column(name="MQ_IMG_ENG3")
	private String mqImgEng3;

	@Column(name="MQ_IMG_TH1")
	private String mqImgTh1;

	@Column(name="MQ_IMG_TH2")
	private String mqImgTh2;

	@Column(name="MQ_IMG_TH3")
	private String mqImgTh3;
	@Lob
	@Column(name="MQ_NAME_ENG1")
	private String mqNameEng1;
    @Lob
	@Column(name="MQ_NAME_ENG2")
	private String mqNameEng2;
    @Lob
	@Column(name="MQ_NAME_ENG3")
	private String mqNameEng3;
    @Lob
	@Column(name="MQ_NAME_TH1")
	private String mqNameTh1;
    @Lob
	@Column(name="MQ_NAME_TH2")
	private String mqNameTh2;
    @Lob
	@Column(name="MQ_NAME_TH3")
	private String mqNameTh3;
    
	@Column(name="MQ_CHOOSE")
	private Long mqChoose; 

	@Column(name="MQ_NO")
	private Long mqNo;

	/*//bi-directional many-to-one association to MissChoice
	@OneToMany(mappedBy="missQuestion")
	private Set<MissChoice> missChoices;*/

	public Long getMqNo() {
		return mqNo;
	}

	public void setMqNo(Long mqNo) {
		this.mqNo = mqNo;
	}

	//bi-directional many-to-one association to MissExam
    @ManyToOne
	@JoinColumn(name="ME_ID")
	private MissExam missExam;

	//bi-directional many-to-one association to MissTemplate
    @ManyToOne
	@JoinColumn(name="MT_ID")
	private MissTemplate missTemplate;

	/*//bi-directional many-to-one association to MissTest
	@OneToMany(mappedBy="missQuestion")
	private Set<MissTest> missTests;
*/
    public MissQuestion() {
    }

	public Long getMqId() {
		return this.mqId;
	}

	public void setMqId(Long mqId) {
		this.mqId = mqId;
	}

	public String getMqImgEng1() {
		return this.mqImgEng1;
	}

	public void setMqImgEng1(String mqImgEng1) {
		this.mqImgEng1 = mqImgEng1;
	}

	public String getMqImgEng2() {
		return this.mqImgEng2;
	}

	public void setMqImgEng2(String mqImgEng2) {
		this.mqImgEng2 = mqImgEng2;
	}

	public String getMqImgEng3() {
		return this.mqImgEng3;
	}

	public void setMqImgEng3(String mqImgEng3) {
		this.mqImgEng3 = mqImgEng3;
	}

	public String getMqImgTh1() {
		return this.mqImgTh1;
	}

	public void setMqImgTh1(String mqImgTh1) {
		this.mqImgTh1 = mqImgTh1;
	}

	public String getMqImgTh2() {
		return this.mqImgTh2;
	}

	public void setMqImgTh2(String mqImgTh2) {
		this.mqImgTh2 = mqImgTh2;
	}

	public String getMqImgTh3() {
		return this.mqImgTh3;
	}

	public void setMqImgTh3(String mqImgTh3) {
		this.mqImgTh3 = mqImgTh3;
	}

	public String getMqNameEng1() {
		return this.mqNameEng1;
	}

	public void setMqNameEng1(String mqNameEng1) {
		this.mqNameEng1 = mqNameEng1;
	}

	public String getMqNameEng2() {
		return this.mqNameEng2;
	}

	public void setMqNameEng2(String mqNameEng2) {
		this.mqNameEng2 = mqNameEng2;
	}

	public String getMqNameEng3() {
		return this.mqNameEng3;
	}

	public void setMqNameEng3(String mqNameEng3) {
		this.mqNameEng3 = mqNameEng3;
	}

	public String getMqNameTh1() {
		return this.mqNameTh1;
	}

	public void setMqNameTh1(String mqNameTh1) {
		this.mqNameTh1 = mqNameTh1;
	}

	public String getMqNameTh2() {
		return this.mqNameTh2;
	}

	public void setMqNameTh2(String mqNameTh2) {
		this.mqNameTh2 = mqNameTh2;
	}

	public String getMqNameTh3() {
		return this.mqNameTh3;
	}

	public void setMqNameTh3(String mqNameTh3) {
		this.mqNameTh3 = mqNameTh3;
	}

	/*public Set<MissChoice> getMissChoices() {
		return this.missChoices;
	}

	public void setMissChoices(Set<MissChoice> missChoices) {
		this.missChoices = missChoices;
	}*/
	
	public MissExam getMissExam() {
		return this.missExam;
	}

	public void setMissExam(MissExam missExam) {
		this.missExam = missExam;
	}
	
	public MissTemplate getMissTemplate() {
		return this.missTemplate;
	}

	public void setMissTemplate(MissTemplate missTemplate) {
		this.missTemplate = missTemplate;
	}

	public Long getMqChoose() {
		return mqChoose;
	}

	public void setMqChoose(Long mqChoose) {
		this.mqChoose = mqChoose;
	}
	
/*	public Set<MissTest> getMissTests() {
		return this.missTests;
	}

	public void setMissTests(Set<MissTest> missTests) {
		this.missTests = missTests;
	}*/
	
}