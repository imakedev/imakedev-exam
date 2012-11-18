// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:12:40 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CompanyForm.java

package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

// Referenced classes of package th.co.aoe.makedev.missconsult.exam.form:
//            CommonForm

public class CompanyForm extends CommonForm
    implements Serializable
{

	private Pagging paging;
    public CompanyForm()
    {
    	 paging = new Pagging();
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

    public String getMaRegisterType()
    {
        return maRegisterType;
    }

    public void setMaRegisterType(String maRegisterType)
    {
        this.maRegisterType = maRegisterType;
    }

    public String getMaRegisterNo()
    {
        return maRegisterNo;
    }

    public void setMaRegisterNo(String maRegisterNo)
    {
        this.maRegisterNo = maRegisterNo;
    }

    public String getMaRegisterFrom()
    {
        return maRegisterFrom;
    }

    public void setMaRegisterFrom(String maRegisterFrom)
    {
        this.maRegisterFrom = maRegisterFrom;
    }

    public String getMaRegisterTo()
    {
        return maRegisterTo;
    }

    public void setMaRegisterTo(String maRegisterTo)
    {
        this.maRegisterTo = maRegisterTo;
    }

    public String getMaContactName()
    {
        return maContactName;
    }

    public void setMaContactName(String maContactName)
    {
        this.maContactName = maContactName;
    }

    public String getMaDayTimePhone()
    {
        return maDayTimePhone;
    }

    public void setMaDayTimePhone(String maDayTimePhone)
    {
        this.maDayTimePhone = maDayTimePhone;
    }

    public String getMaName()
    {
        return maName;
    }

    public void setMaName(String maName)
    {
        this.maName = maName;
    }

    public String[] getMissExam_selectbox()
    {
        return missExam_selectbox;
    }

    public void setMissExam_selectbox(String missExam_selectbox[])
    {
        this.missExam_selectbox = missExam_selectbox;
    }

    public String getMaIdArray()
    {
        return maIdArray;
    }

    public void setMaIdArray(String maIdArray)
    {
        this.maIdArray = maIdArray;
    }

    public String getMaContactBirthDate()
    {
        return maContactBirthDate;
    }

    public void setMaContactBirthDate(String maContactBirthDate)
    {
        this.maContactBirthDate = maContactBirthDate;
    }

    public String getRefill() {
		return refill;
	}

	public void setRefill(String refill) {
		this.refill = refill;
	}

	public Long getRcId() {
		return rcId;
	}

	public void setRcId(Long rcId) {
		this.rcId = rcId;
	}

	public String getRcActionId() {
		return rcActionId;
	}

	public void setRcActionId(String rcActionId) {
		this.rcActionId = rcActionId;
	}

	public Pagging getPaging() {
		return paging;
	}

	public void setPaging(Pagging paging) {
		this.paging = paging;
	}

	private static final long serialVersionUID = 1L;
    private MissAccount missAccount;
    private String maContactBirthDate;
    private String maRegisterType;
    private String maRegisterNo;
    private String maRegisterFrom;
    private String maRegisterTo;
    private String maContactName;
    private String maDayTimePhone;
    private String maName;
    private String missExam_selectbox[];
    private String maIdArray;
    
    private String refill;
    private Long rcId;
    private String rcActionId;
    
}
