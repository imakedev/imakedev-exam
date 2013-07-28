package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.sql.Timestamp;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_TEST_SURVEY database table.
 * 
 */
@XStreamAlias("MissTestSurvey")
public class MissTestSurvey  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mtsKey;

	private Long maId;

	private Long msId;

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