package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_TEST_RESULT database table.
 * 
 */
@XStreamAlias("MissTestResult")
public class MissTestResult  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mtrId;

	private Long meId;

	private Long msId;

	private Timestamp mtrEndTime;

	private String mtrResultCode;

	private Timestamp mtrStartTime;

	private String mtrStatus;
	private String mtrRespondedStatus;

	private Date mtrTestDate;
	private String mtrHideStatus; 
	
	private String mtrAge;
	private String mtrVersion;
	 
	//EXT
	private String msatPath;
	private int lieScore;
	private int totalScore;
	private String column;
	private String value;
	private int roleMC; //0=false, 1 =ture;
	
	private boolean showAll; //true =show all
	
	private Long msOrder;
	private String mraLang; 

	
	public Long getMsOrder() {
		return msOrder;
	}

	public void setMsOrder(Long msOrder) {
		this.msOrder = msOrder;
	}

	public String getMraLang() {
		return mraLang;
	}

	public void setMraLang(String mraLang) {
		this.mraLang = mraLang;
	}

	public boolean isShowAll() {
		return showAll;
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}

	public int getRoleMC() {
		return roleMC;
	}

	public void setRoleMC(int roleMC) {
		this.roleMC = roleMC;
	}

	//private Map<String,String> paramMap;
	private List<MissTestShow> missTestShows;
	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMtrRespondedStatus() {
		return mtrRespondedStatus;
	}

	public void setMtrRespondedStatus(String mtrRespondedStatus) {
		this.mtrRespondedStatus = mtrRespondedStatus;
	}

	public int getLieScore() {
		return lieScore;
	}

	public void setLieScore(int lieScore) {
		this.lieScore = lieScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public String getMsatPath() {
		return msatPath;
	}

	public void setMsatPath(String msatPath) {
		this.msatPath = msatPath;
	}

	private byte[] images;
	public byte[] getImages() {
		return images;
	}

	public void setImages(byte[] images) {
		this.images = images;
	}

	@XStreamAlias("missCandidate")
	private MissCandidate missCandidate;
	private String mtrIgnored;
	//ext
	private String userid;
	private String mcaCompanyName;
	private String mtrIds;
	private List<String> axisValues;
	private String rootPath;
	
    public MissTestResult() {
    }

	public Long getMtrId() {
		return this.mtrId;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public void setMtrId(Long mtrId) {
		this.mtrId = mtrId;
	}

	public Long getMeId() {
		return this.meId;
	}

	public void setMeId(Long meId) {
		this.meId = meId;
	}

	public Long getMsId() {
		return this.msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public Timestamp getMtrEndTime() {
		return this.mtrEndTime;
	}

	public void setMtrEndTime(Timestamp mtrEndTime) {
		this.mtrEndTime = mtrEndTime;
	}

	public String getMtrResultCode() {
		return this.mtrResultCode;
	}

	public void setMtrResultCode(String mtrResultCode) {
		this.mtrResultCode = mtrResultCode;
	}

	public Timestamp getMtrStartTime() {
		return this.mtrStartTime;
	}

	public void setMtrStartTime(Timestamp mtrStartTime) {
		this.mtrStartTime = mtrStartTime;
	}

	public String getMtrStatus() {
		return this.mtrStatus;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setMtrStatus(String mtrStatus) {
		this.mtrStatus = mtrStatus;
	}

	public Date getMtrTestDate() {
		return this.mtrTestDate;
	}

	public void setMtrTestDate(Date mtrTestDate) {
		this.mtrTestDate = mtrTestDate;
	}

	public MissCandidate getMissCandidate() {
		return this.missCandidate;
	}

	public String getMcaCompanyName() {
		return mcaCompanyName;
	}

	public void setMcaCompanyName(String mcaCompanyName) {
		this.mcaCompanyName = mcaCompanyName;
	}

	public void setMissCandidate(MissCandidate missCandidate) {
		this.missCandidate = missCandidate;
	}

	public String getMtrIds() {
		return mtrIds;
	}

	public void setMtrIds(String mtrIds) {
		this.mtrIds = mtrIds;
	}

	public List<String> getAxisValues() {
		return axisValues;
	}

	public void setAxisValues(List<String> axisValues) {
		this.axisValues = axisValues;
	}

	public String getMtrIgnored() {
		return mtrIgnored;
	}

	public void setMtrIgnored(String mtrIgnored) {
		this.mtrIgnored = mtrIgnored;
	}

	public List<MissTestShow> getMissTestShows() {
		return missTestShows;
	}

	public void setMissTestShows(List<MissTestShow> missTestShows) {
		this.missTestShows = missTestShows;
	}

	public String getMtrHideStatus() {
		return mtrHideStatus;
	}

	public void setMtrHideStatus(String mtrHideStatus) {
		this.mtrHideStatus = mtrHideStatus;
	}

	public String getMtrAge() {
		return mtrAge;
	}

	public void setMtrAge(String mtrAge) {
		this.mtrAge = mtrAge;
	}

	public String getMtrVersion() {
		return mtrVersion;
	}

	public void setMtrVersion(String mtrVersion) {
		this.mtrVersion = mtrVersion;
	}

	 
	
}