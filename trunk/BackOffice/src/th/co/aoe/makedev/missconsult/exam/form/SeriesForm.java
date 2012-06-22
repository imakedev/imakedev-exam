// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:13:36 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SeriesForm.java

package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;
import th.co.aoe.makedev.missconsult.xstream.MissSery;

// Referenced classes of package th.co.aoe.makedev.missconsult.exam.form:
//            CommonForm

public class SeriesForm extends CommonForm
    implements Serializable
{

    public SeriesForm()
    {
        missSery = new MissSery();
    }

    public MissSery getMissSery()
    {
        return missSery;
    }

    public void setMissSery(MissSery missSery)
    {
        this.missSery = missSery;
    }

    public String[] getMissExam_selectbox()
    {
        return missExam_selectbox;
    }

    public void setMissExam_selectbox(String missExam_selectbox[])
    {
        this.missExam_selectbox = missExam_selectbox;
    }

    public String getMsSeriesName()
    {
        return msSeriesName;
    }

    public void setMsSeriesName(String msSeriesName)
    {
        this.msSeriesName = msSeriesName;
    }

    public Long getMsUnitCost()
    {
        return msUnitCost;
    }

    public void setMsUnitCost(Long msUnitCost)
    {
        this.msUnitCost = msUnitCost;
    }

    public String getMsIdArray()
    {
        return msIdArray;
    }

    public void setMsIdArray(String msIdArray)
    {
        this.msIdArray = msIdArray;
    }

    private static final long serialVersionUID = 1L;
    private MissSery missSery;
    private String msSeriesName;
    private Long msUnitCost;
    private String missExam_selectbox[];
    private String msIdArray;
}
