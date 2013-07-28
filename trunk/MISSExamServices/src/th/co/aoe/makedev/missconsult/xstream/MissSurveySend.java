package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


/**
 * The persistent class for the MISS_SURVEY_SEND database table.
 * 
 */
@XStreamAlias("MissSurveySend")
public class MissSurveySend extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mssId;

	private String msEmail;
	
	private String msName; 
 
 	private String mspmGroupName;
 
 	private Timestamp mssCreatedTime;
 	 
 	private String mssStatus;
 	 
 	private String mtsKey;
	
	@XStreamAlias("missSery")
	private MissSery missSery;
	
	@XStreamAlias("missCandidate")
	private MissCandidate missCandidate;
	
	// for send 
	private Long maId;
	
	@XStreamImplicit(itemFieldName="userEmail")
	private List<List<String>> userEmail;
	
	@XStreamAlias("MissSurveySendList")
	private List<MissSurveySend> MissSurveySendList;
	
    public MissSurveySend() {
    }

	public Long getMssId() {
		return this.mssId;
	}

	public void setMssId(Long mssId) {
		this.mssId = mssId;
	}

	public String getMsEmail() {
		return this.msEmail;
	}

	public void setMsEmail(String msEmail) {
		this.msEmail = msEmail;
	}

	public Long getMaId() {
		return maId;
	}

	public void setMaId(Long maId) {
		this.maId = maId;
	}

	public List<List<String>> getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(List<List<String>> userEmail) {
		this.userEmail = userEmail;
	}

	public MissSery getMissSery() {
		return this.missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}

	public MissCandidate getMissCandidate() {
		return missCandidate;
	}

	public void setMissCandidate(MissCandidate missCandidate) {
		this.missCandidate = missCandidate;
	}

	public String getMsName() {
		return msName;
	}

	public void setMsName(String msName) {
		this.msName = msName;
	}

	public String getMspmGroupName() {
		return mspmGroupName;
	}

	public void setMspmGroupName(String mspmGroupName) {
		this.mspmGroupName = mspmGroupName;
	}

	public Timestamp getMssCreatedTime() {
		return mssCreatedTime;
	}

	public void setMssCreatedTime(Timestamp mssCreatedTime) {
		this.mssCreatedTime = mssCreatedTime;
	}

	public String getMssStatus() {
		return mssStatus;
	}

	public void setMssStatus(String mssStatus) {
		this.mssStatus = mssStatus;
	}

	public String getMtsKey() {
		return mtsKey;
	}

	public void setMtsKey(String mtsKey) {
		this.mtsKey = mtsKey;
	}

	public List<MissSurveySend> getMissSurveySendList() {
		return MissSurveySendList;
	}

	public void setMissSurveySendList(List<MissSurveySend> missSurveySendList) {
		MissSurveySendList = missSurveySendList;
	}
	 
	
	
	
}