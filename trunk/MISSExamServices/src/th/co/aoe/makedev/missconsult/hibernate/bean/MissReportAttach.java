package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_REPORT_ATTACH database table.
 * 
 */
@Entity
@Table(name="MISS_REPORT_ATTACH")
public class MissReportAttach implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissReportAttachPK id;

	@Column(name="MRA_FILE_NAME")
	private String mraFileName;

	@Column(name="MRA_HOTLINK")
	private String mraHotlink;

	@Column(name="MRA_PATH")
	private String mraPath;

	@Column(name="MRA_REPORT_NAME")
	private String mraReportName;

	//bi-directional many-to-one association to MissSery
	@ManyToOne
	@JoinColumn(name="MS_ID",insertable=false,updatable=false)
	private MissSery missSery;

	public MissReportAttach() {
	}

	public MissReportAttachPK getId() {
		return this.id;
	}

	public void setId(MissReportAttachPK id) {
		this.id = id;
	}

	public String getMraFileName() {
		return this.mraFileName;
	}

	public void setMraFileName(String mraFileName) {
		this.mraFileName = mraFileName;
	}

	public String getMraHotlink() {
		return this.mraHotlink;
	}

	public void setMraHotlink(String mraHotlink) {
		this.mraHotlink = mraHotlink;
	}

	public String getMraPath() {
		return this.mraPath;
	}

	public void setMraPath(String mraPath) {
		this.mraPath = mraPath;
	}

	public String getMraReportName() {
		return this.mraReportName;
	}

	public void setMraReportName(String mraReportName) {
		this.mraReportName = mraReportName;
	}

	public MissSery getMissSery() {
		return this.missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}

}