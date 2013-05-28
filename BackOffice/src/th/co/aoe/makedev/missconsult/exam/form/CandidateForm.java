// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:12:24 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CandidateForm.java

package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.MissCandidate;

// Referenced classes of package th.co.aoe.makedev.missconsult.exam.form:
//            CommonForm

public class CandidateForm extends CommonForm
    implements Serializable
{

    public CandidateForm()
    {
        missCandidate = new MissCandidate();
    }

    public MissCandidate getMissCandidate()
    {
        return missCandidate;
    }

    public void setMissCandidate(MissCandidate missCandidate)
    {
        this.missCandidate = missCandidate;
    }

    public String getMcaStatus()
    {
        return mcaStatus;
    }

    public void setMcaStatus(String mcaStatus)
    {
        this.mcaStatus = mcaStatus;
    }

    public String getMcaSeries()
    {
        return mcaSeries;
    }

    public void setMcaSeries(String mcaSeries)
    {
        this.mcaSeries = mcaSeries;
    }

    public String getMcaUsername()
    {
        return mcaUsername;
    }

    public void setMcaUsername(String mcaUsername)
    {
        this.mcaUsername = mcaUsername;
    }

    public String getMcaPassword()
    {
        return mcaPassword;
    }

    public void setMcaPassword(String mcaPassword)
    {
        this.mcaPassword = mcaPassword;
    }

    public String getMcaCompanyName()
    {
        return mcaCompanyName;
    }

    public void setMcaCompanyName(String mcaCompanyName)
    {
        this.mcaCompanyName = mcaCompanyName;
    }

    public String getMcaIdArray()
    {
        return mcaIdArray;
    }

    public void setMcaIdArray(String mcaIdArray)
    {
        this.mcaIdArray = mcaIdArray;
    }

    public String getMcaBirthDate()
    {
        return mcaBirthDate;
    }

    public void setMcaBirthDate(String mcaBirthDate)
    {
        this.mcaBirthDate = mcaBirthDate;
    }

    public Long getMaId() {
		return maId;
	}

	public void setMaId(Long maId) {
		this.maId = maId;
	}

	private static final long serialVersionUID = 1L;
    private MissCandidate missCandidate;
    private String mcaBirthDate;
    private String mcaStatus;
    private String mcaSeries;
    private String mcaUsername;
    private String mcaPassword;
    private String mcaCompanyName;
    private String mcaFirstName;  
	private String mcaLastName;
    private Long maId;
    private String mcaIdArray;
	public String getMcaFirstName() {
		return mcaFirstName;
	}

	public void setMcaFirstName(String mcaFirstName) {
		this.mcaFirstName = mcaFirstName;
	}

	public String getMcaLastName() {
		return mcaLastName;
	}

	public void setMcaLastName(String mcaLastName) {
		this.mcaLastName = mcaLastName;
	}
}
