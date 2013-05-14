package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_REPORT_ATTACH database table.
 * 
 */
@XStreamAlias("MissReportAttach")
public class MissReportAttach  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

 
	private Long msId; 
	private Long msOrder;
	private String mraLang;
 
	private String mraFileName;
 
	private String mraHotlink;
 
	private String mraPath;
 
	private String mraReportName;
 

	@XStreamAlias("missSery")
	private MissSery missSery;

	public MissReportAttach() {
	}

	public Long getMsId() {
		return msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public Long getMsOrder() {
		return msOrder;
	}

	public void setMsOrder(Long msOrder) {
		this.msOrder = msOrder;
	}

	public String getMraFileName() {
		return mraFileName;
	}

	public void setMraFileName(String mraFileName) {
		this.mraFileName = mraFileName;
	}

	public String getMraHotlink() {
		return mraHotlink;
	}

	public void setMraHotlink(String mraHotlink) {
		this.mraHotlink = mraHotlink;
	}

	public String getMraPath() {
		return mraPath;
	}

	public void setMraPath(String mraPath) {
		this.mraPath = mraPath;
	}

	public String getMraReportName() {
		return mraReportName;
	}

	public void setMraReportName(String mraReportName) {
		this.mraReportName = mraReportName;
	}

	public MissSery getMissSery() {
		return missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}

	public String getMraLang() {
		return mraLang;
	}

	public void setMraLang(String mraLang) {
		this.mraLang = mraLang;
	}

	 
}