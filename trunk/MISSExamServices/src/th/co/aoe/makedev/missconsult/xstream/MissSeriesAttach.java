package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_SERIES_ATTACH database table.
 * 
 */
@XStreamAlias("missSeriesAttach")
public class MissSeriesAttach extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1;

	private Long msatId;

	private String msatFileName;

	private String msatHotlink;

	private String msatModule;

	private String msatPath;

	private Long msatRef1;

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