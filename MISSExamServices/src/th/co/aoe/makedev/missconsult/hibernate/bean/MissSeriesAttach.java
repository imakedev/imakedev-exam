package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_SERIES_ATTACH database table.
 * 
 */
@Entity
@Table(name="MISS_SERIES_ATTACH")
public class MissSeriesAttach implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MSAT_ID")
	private Long msatId;

	@Column(name="MSAT_FILE_NAME")
	private String msatFileName;

	@Column(name="MSAT_HOTLINK")
	private String msatHotlink;

	@Column(name="MSAT_MODULE")
	private String msatModule;

	@Column(name="MSAT_PATH")
	private String msatPath;

	@Column(name="MSAT_REF1")
	private Long msatRef1;

	@Column(name="MSAT_REF2")
	private Long msatRef2;

    public MissSeriesAttach() {
    }

	public Long getMsatId() {
		return this.msatId;
	}

	public void setMsatId(Long msatId) {
		this.msatId = msatId;
	}

	public String getMsatFileName() {
		return this.msatFileName;
	}

	public void setMsatFileName(String msatFileName) {
		this.msatFileName = msatFileName;
	}

	public String getMsatHotlink() {
		return this.msatHotlink;
	}

	public void setMsatHotlink(String msatHotlink) {
		this.msatHotlink = msatHotlink;
	}

	public String getMsatModule() {
		return this.msatModule;
	}

	public void setMsatModule(String msatModule) {
		this.msatModule = msatModule;
	}

	public String getMsatPath() {
		return this.msatPath;
	}

	public void setMsatPath(String msatPath) {
		this.msatPath = msatPath;
	}

	public Long getMsatRef1() {
		return this.msatRef1;
	}

	public void setMsatRef1(Long msatRef1) {
		this.msatRef1 = msatRef1;
	}

	public Long getMsatRef2() {
		return this.msatRef2;
	}

	public void setMsatRef2(Long msatRef2) {
		this.msatRef2 = msatRef2;
	}

}