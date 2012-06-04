package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_ATTACH database table.
 * 
 */
@XStreamAlias("MissAttach")
public class MissAttach  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long matId;

	private String matFileName;

	private String matHotlink;

	private String matModule;

	private String matPath;

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