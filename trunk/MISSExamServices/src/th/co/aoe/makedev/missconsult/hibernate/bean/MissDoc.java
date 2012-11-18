package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_DOC database table.
 * 
 */
@Entity
@Table(name="MISS_DOC")
public class MissDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MD_ID")
	private Long mdId;

	@Column(name="MD_DOC_FILE_NAME")
	private String mdDocFileName;

	@Column(name="MD_DOC_HOTLINK")
	private String mdDocHotlink;

	@Column(name="MD_DOC_NAME")
	private String mdDocName;

	@Column(name="MD_DOC_PATH")
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