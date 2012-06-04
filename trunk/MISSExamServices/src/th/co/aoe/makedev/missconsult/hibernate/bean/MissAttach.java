package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_ATTACH database table.
 * 
 */
@Entity
@Table(name="MISS_ATTACH")
public class MissAttach implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MAT_ID")
	private Long matId;

	@Column(name="MAT_FILE_NAME")
	private String matFileName;

	@Column(name="MAT_HOTLINK")
	private String matHotlink;

	@Column(name="MAT_MODULE")
	private String matModule;

	@Column(name="MAT_PATH")
	private String matPath;

	@Column(name="MAT_REF")
	private Long matRef;

    public MissAttach() {
    }

	public Long getMatId() {
		return this.matId;
	}

	public void setMatId(Long matId) {
		this.matId = matId;
	}

	public String getMatFileName() {
		return this.matFileName;
	}

	public void setMatFileName(String matFileName) {
		this.matFileName = matFileName;
	}

	public String getMatHotlink() {
		return this.matHotlink;
	}

	public void setMatHotlink(String matHotlink) {
		this.matHotlink = matHotlink;
	}

	public String getMatModule() {
		return this.matModule;
	}

	public void setMatModule(String matModule) {
		this.matModule = matModule;
	}

	public String getMatPath() {
		return this.matPath;
	}

	public void setMatPath(String matPath) {
		this.matPath = matPath;
	}

	public Long getMatRef() {
		return this.matRef;
	}

	public void setMatRef(Long matRef) {
		this.matRef = matRef;
	}

}