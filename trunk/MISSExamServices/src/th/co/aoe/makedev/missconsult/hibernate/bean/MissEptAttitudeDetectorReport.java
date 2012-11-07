package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_EPT_ATTITUDE_DETECTOR_REPORT database table.
 * 
 */
@Entity
@Table(name="MISS_EPT_ATTITUDE_DETECTOR_REPORT")
public class MissEptAttitudeDetectorReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissEptAttitudeDetectorReportPK id;

	@Lob
	@Column(name="MEADR_DETAIL")
	private String meadrDetail;

	@Column(name="MEADR_KEY")
	private String meadrKey;

	@Column(name="MEADR_TOPIC")
	private String meadrTopic;

	//bi-directional many-to-one association to MissTestResult
	@ManyToOne
	@JoinColumn(name="MTR_ID",insertable=false,updatable=false)
	private MissTestResult missTestResult;

	public MissEptAttitudeDetectorReport() {
	}

	public MissEptAttitudeDetectorReportPK getId() {
		return this.id;
	}

	public void setId(MissEptAttitudeDetectorReportPK id) {
		this.id = id;
	}

	public String getMeadrDetail() {
		return this.meadrDetail;
	}

	public void setMeadrDetail(String meadrDetail) {
		this.meadrDetail = meadrDetail;
	}

	public String getMeadrKey() {
		return this.meadrKey;
	}

	public void setMeadrKey(String meadrKey) {
		this.meadrKey = meadrKey;
	}

	public String getMeadrTopic() {
		return this.meadrTopic;
	}

	public void setMeadrTopic(String meadrTopic) {
		this.meadrTopic = meadrTopic;
	}

	public MissTestResult getMissTestResult() {
		return this.missTestResult;
	}

	public void setMissTestResult(MissTestResult missTestResult) {
		this.missTestResult = missTestResult;
	}

}