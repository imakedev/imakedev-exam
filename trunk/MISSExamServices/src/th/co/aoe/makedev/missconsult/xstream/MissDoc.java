package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_DOC database table.
 * 
 */
@XStreamAlias("MissDoc")
public class MissDoc  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mdId;

	private String mdDocFileName;

	private String mdDocHotlink;

	private String mdDocName;

	private String mdDocPath;

	public MissDoc() {
	}

	public Long getMdId() {
		return this.mdId;
	}

	public void setMdId(Long mdId) {
		this.mdId = mdId;
	}

	public String getMdDocFileName() {
		return this.mdDocFileName;
	}

	public void setMdDocFileName(String mdDocFileName) {
		this.mdDocFileName = mdDocFileName;
	}

	public String getMdDocHotlink() {
		return this.mdDocHotlink;
	}

	public void setMdDocHotlink(String mdDocHotlink) {
		this.mdDocHotlink = mdDocHotlink;
	}

	public String getMdDocName() {
		return this.mdDocName;
	}

	public void setMdDocName(String mdDocName) {
		this.mdDocName = mdDocName;
	}

	public String getMdDocPath() {
		return this.mdDocPath;
	}

	public void setMdDocPath(String mdDocPath) {
		this.mdDocPath = mdDocPath;
	}

}