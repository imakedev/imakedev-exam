// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:12:59 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MissExamForm.java

package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;
import th.co.aoe.makedev.missconsult.xstream.MissExam;

// Referenced classes of package th.co.aoe.makedev.missconsult.exam.form:
//            CommonForm

public class MissExamForm extends CommonForm
    implements Serializable
{

    public MissExamForm()
    {
        missExam = new MissExam();
    }

    public MissExam getMissExam()
    {
        return missExam;
    }

    public void setMissExam(MissExam missExam)
    {
        this.missExam = missExam;
    }

    public String getMegId()
    {
        return megId;
    }

    public void setMegId(String megId)
    {
        this.megId = megId;
    }

    public String getMeIdArray()
    {
        return meIdArray;
    }

    public void setMeIdArray(String meIdArray)
    {
        this.meIdArray = meIdArray;
    }

    private static final long serialVersionUID = 1L;
    private MissExam missExam;
    private String megId;
    private String meIdArray;
}
