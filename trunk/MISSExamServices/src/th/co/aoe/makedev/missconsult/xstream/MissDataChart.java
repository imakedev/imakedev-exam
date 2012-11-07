package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_DATA_CHART database table.
 * 
 */

@XStreamAlias("MissDataChart")
public class MissDataChart extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mtrId;
	private String mdcKey;
	private String mdcSwfName;

	private String mdcData;
	private String mdcType;
	/*private String mdcHeight;
	private String mdcWidth;*/
	
	@XStreamAlias("missTestResult")
	private MissTestResult missTestResult;

    public MissDataChart() {
    }
	public String getMdcData() {
		return this.mdcData;
	}

	public void setMdcData(String mdcData) {
		this.mdcData = mdcData;
	}
 

	public String getMdcType() {
		return mdcType;
	}
	public void setMdcType(String mdcType) {
		this.mdcType = mdcType;
	}
	public MissTestResult getMissTestResult() {
		return this.missTestResult;
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
	public String getMdcKey() {
		return mdcKey;
	}
	public void setMdcKey(String mdcKey) {
		this.mdcKey = mdcKey;
	}
	public String getMdcSwfName() {
		return mdcSwfName;
	}
	public void setMdcSwfName(String mdcSwfName) {
		this.mdcSwfName = mdcSwfName;
	}
	
}