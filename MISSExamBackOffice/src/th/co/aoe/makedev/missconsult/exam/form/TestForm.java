// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:13:51 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TestForm.java

package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.MissExam;
import th.co.aoe.makedev.missconsult.xstream.MissQuestion;

// Referenced classes of package th.co.aoe.makedev.missconsult.exam.form:
//            CommonForm

public class TestForm extends CommonForm
    implements Serializable
{

    public TestForm()
    {
    	missExam =new MissExam();
    	missQuestion=new MissQuestion();
    }

    private static final long serialVersionUID = 1L;
  /*  private String mcaStatus;
    private String mcaSeries;
    private String mcaUsername;
    private String mcaPassword;
    private String mcaCompanyName;*/
    private MissExam missExam;
    private MissQuestion missQuestion;
    private String modeQuestion;
    
    //for search
    private String megId;
    private String meIdArray;

	public MissExam getMissExam() {
		return missExam;
		
	}

	public void setMissExam(MissExam missExam) {
		this.missExam = missExam;
	}

	public String getMegId() {
		return megId;
	}

	public void setMegId(String megId) {
		this.megId = megId;
	}

	public String getMeIdArray() {
		return meIdArray;
	}

	public void setMeIdArray(String meIdArray) {
		this.meIdArray = meIdArray;
	}

	public MissQuestion getMissQuestion() {
		return missQuestion;
	}

	public void setMissQuestion(MissQuestion missQuestion) {
		this.missQuestion = missQuestion;
	}

	public String getModeQuestion() {
		return modeQuestion;
	}

	public void setModeQuestion(String modeQuestion) {
		this.modeQuestion = modeQuestion;
	}

	/*public String getMesIdArray() {
		return mesIdArray;
	}

	public void setMesIdArray(String mesIdArray) {
		this.mesIdArray = mesIdArray;
	}
    */
}
