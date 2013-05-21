package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

public class SeriesManagementSectionForm  extends CommonForm
implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeriesManagementSectionForm()
	    { 
	    }
	private Long msId_section;
	private Long msOrder_section;
	private String mraReportName_section;
	private String mraLang_section;

	public Long getMsId_section() {
		return msId_section;
	}
	public void setMsId_section(Long msId_section) {
		this.msId_section = msId_section;
	}
	public Long getMsOrder_section() {
		return msOrder_section;
	}
	public void setMsOrder_section(Long msOrder_section) {
		this.msOrder_section = msOrder_section;
	}
	public String getMraReportName_section() {
		return mraReportName_section;
	}
	public void setMraReportName_section(String mraReportName_section) {
		this.mraReportName_section = mraReportName_section;
	}
	public String getMraLang_section() {
		return mraLang_section;
	}
	public void setMraLang_section(String mraLang_section) {
		this.mraLang_section = mraLang_section;
	} 

}
