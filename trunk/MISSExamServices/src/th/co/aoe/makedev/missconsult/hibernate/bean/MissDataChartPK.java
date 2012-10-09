package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MISS_DATA_CHART database table.
 * 
 */
@Embeddable
public class MissDataChartPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MTR_ID")
	private Long mtrId;

	@Column(name="MDC_KEY")
	private String mdcKey;

	@Column(name="MDC_SWF_NAME")
	private String mdcSwfName;

    public MissDataChartPK() {
    }
	public Long getMtrId() {
		return this.mtrId;
	}
	public void setMtrId(Long mtrId) {
		this.mtrId = mtrId;
	}
	public String getMdcKey() {
		return this.mdcKey;
	}
	public void setMdcKey(String mdcKey) {
		this.mdcKey = mdcKey;
	}
	public String getMdcSwfName() {
		return this.mdcSwfName;
	}
	public void setMdcSwfName(String mdcSwfName) {
		this.mdcSwfName = mdcSwfName;
	}

	 
}