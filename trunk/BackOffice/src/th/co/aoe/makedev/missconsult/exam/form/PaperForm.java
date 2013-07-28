// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:13:20 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PaperForm.java

package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissSery;

public class PaperForm
    implements Serializable
{
	private MissCandidate missCandidate;
	private MissSery missSery;
	private Long mcaSeries;
	/*private int examIndex;
	private int questionIndex;
	private int oldQuestionIndex;
	private  ;*/
	private String mcaBirthDate;
	private Long examId;
	private int setId;
	//private String mcaUsername;

    public PaperForm()
    {
    	missCandidate=new MissCandidate();    	 
    	missSery=new MissSery();
    }

    public Long getMcaSeries() {
		return mcaSeries;
	}

	public void setMcaSeries(Long mcaSeries) {
		this.mcaSeries = mcaSeries;
	}

	private static final long serialVersionUID = 1L;

	public MissCandidate getMissCandidate() {
		return missCandidate;
	}

	public void setMissCandidate(MissCandidate missCandidate) {
		this.missCandidate = missCandidate;
	}

	public String getMcaBirthDate() {
		return mcaBirthDate;
	}

	public void setMcaBirthDate(String mcaBirthDate) {
		this.mcaBirthDate = mcaBirthDate;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public int getSetId() {
		return setId;
	}

	public void setSetId(int setId) {
		this.setId = setId;
	}

	public MissSery getMissSery() {
		return missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}
    
}
