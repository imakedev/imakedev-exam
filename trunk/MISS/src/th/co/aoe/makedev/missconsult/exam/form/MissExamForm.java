package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissSery;

/*import th.co.vlink.xstream.BpsGroup;
import th.co.vlink.xstream.BpsTerm;*/

public class MissExamForm extends CommonForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MissCandidate missCandidate;
	private int examIndex;
	private int questionIndex;
	private MissSery missSery;
	private String mcaBirthDate;
	
	public int getQuestionIndex() {
		return questionIndex;
	}
	public void setQuestionIndex(int questionIndex) {
		this.questionIndex = questionIndex;
	}
	public String getMcaBirthDate() {
		return mcaBirthDate;
	}
	public void setMcaBirthDate(String mcaBirthDate) {
		this.mcaBirthDate = mcaBirthDate;
	}
	public MissExamForm(){
		missCandidate=new MissCandidate();
		missSery=new MissSery();
	}
	public MissCandidate getMissCandidate() {
		return missCandidate;
	}
	public void setMissCandidate(MissCandidate missCandidate) {
		this.missCandidate = missCandidate;
	}
	public MissSery getMissSery() {
		return missSery;
	}
	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}
	public int getExamIndex() {
		return examIndex;
	}
	public void setExamIndex(int examIndex) {
		this.examIndex = examIndex;
	}

}
