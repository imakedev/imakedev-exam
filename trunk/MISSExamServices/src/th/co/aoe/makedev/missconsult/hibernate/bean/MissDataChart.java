package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_DATA_CHART database table.
 * 
 */
@Entity
@Table(name="MISS_DATA_CHART")
public class MissDataChart implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissDataChartPK id;

    @Lob()
	@Column(name="MDC_DATA")
	private String mdcData;

    public String getMdcType() {
		return mdcType;
	}

	public void setMdcType(String mdcType) {
		this.mdcType = mdcType;
	}

	@Column(name="MDC_TYPE")
	private String mdcType;
	/*@Column(name="MDC_HEIGHT")
	private String mdcHeight;

	@Column(name="MDC_WIDTH")
	private String mdcWidth;*/

	//bi-directional many-to-one association to MissTestResult
    @ManyToOne
	@JoinColumn(name="MTR_ID",insertable=false,updatable=false)
	private MissTestResult missTestResult;

    public MissDataChart() {
    }

	public MissDataChartPK getId() {
		return this.id;
	}

	public void setId(MissDataChartPK id) {
		this.id = id;
	}
	
	public String getMdcData() {
		return this.mdcData;
	}

	public void setMdcData(String mdcData) {
		this.mdcData = mdcData;
	}

	 

	public MissTestResult getMissTestResult() {
		return this.missTestResult;
	}

	public void setMissTestResult(MissTestResult missTestResult) {
		this.missTestResult = missTestResult;
	}
	
}