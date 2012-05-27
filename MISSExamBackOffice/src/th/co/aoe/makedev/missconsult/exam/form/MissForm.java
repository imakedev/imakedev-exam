// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:13:13 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MissForm.java

package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;

// Referenced classes of package th.co.aoe.makedev.missconsult.exam.form:
//            CommonForm

public class MissForm extends CommonForm
    implements Serializable
{

    public MissForm()
    {
        missAccount = new MissAccount();
    }

    public MissAccount getMissAccount()
    {
        return missAccount;
    }

    public void setMissAccount(MissAccount missAccount)
    {
        this.missAccount = missAccount;
    }

    public String getMaContactBirthDate()
    {
        return maContactBirthDate;
    }

    public void setMaContactBirthDate(String maContactBirthDate)
    {
        this.maContactBirthDate = maContactBirthDate;
    }

    private static final long serialVersionUID = 1L;
    private MissAccount missAccount;
    private String maContactBirthDate;
}
