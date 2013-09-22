package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_DATA_IMAGE database table.
 * 
 */
@XStreamAlias("MissDataImage")
public class MissDataImage extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@XStreamAlias("missTestResult")
	private MissTestResult missTestResult;
 
	private Long mtrId; 
	private String mdiColRowRef; 
	private String mdiColRowExpand; 
	private String mdiEndpoint;
	public MissTestResult getMissTestResult() {
		return missTestResult;
	}
	public void setMissTestResult(MissTestResult missTestResult) {
		this.missTestResult = missTestResult;
	}
	public Long getMtrId() {
		return mtrId;
	}
	public void setMtrId(Long mtrId) {
		this.mtrId = mtrId;
	}
	public String getMdiColRowRef() {
		return mdiColRowRef;
	}
	public void setMdiColRowRef(String mdiColRowRef) {
		this.mdiColRowRef = mdiColRowRef;
	}
	public String getMdiColRowExpand() {
		return mdiColRowExpand;
	}
	public void setMdiColRowExpand(String mdiColRowExpand) {
		this.mdiColRowExpand = mdiColRowExpand;
	}
	public String getMdiEndpoint() {
		return mdiEndpoint;
	}
	public void setMdiEndpoint(String mdiEndpoint) {
		this.mdiEndpoint = mdiEndpoint;
	}
	
	
}