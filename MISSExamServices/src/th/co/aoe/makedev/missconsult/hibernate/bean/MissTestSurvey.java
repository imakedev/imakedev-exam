package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_TEST_SURVEY database table.
 * 
 */
@Entity
@Table(name="MISS_TEST_SURVEY")
public class MissTestSurvey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MTS_KEY")
	private String mtsKey;

	@Column(name="MA_ID")
	private Long maId;

	@Column(name="MS_ID")
	private Long msId;

	@Column(name="MTS_CREATED_TIME")
	private Timestamp mtsCreatedTime;

	public MissTestSurvey() {
	}

	public String getMtsKey() {
		return this.mtsKey;
	}

	public void setMtsKey(String mtsKey) {
		this.mtsKey = mtsKey;
	}

	public Long getMaId() {
		return this.maId;
	}

	public void setMaId(Long maId) {
		this.maId = maId;
	}

	public Long getMsId() {
		return this.msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public Timestamp getMtsCreatedTime() {
		return this.mtsCreatedTime;
	}

	public void setMtsCreatedTime(Timestamp mtsCreatedTime) {
		this.mtsCreatedTime = mtsCreatedTime;
	}

}