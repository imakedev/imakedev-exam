package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


/**
 * The persistent class for the MISS_CANDIDATE database table.
 * 
 */
@XStreamAlias("MissCandidate")
public class MissCandidate extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mcaId;

	@XStreamAlias("mcaBirthDate")
	private Date mcaBirthDate;

	private String mcaCitizenId;

	private String mcaDepartment;

	private String mcaEmail;

	private String mcaFirstName;

	private String mcaGender;

	private String mcaLastName;

	@XStreamAlias("mcaLastlogin")
	private Timestamp mcaLastlogin;

	private String mcaPassword;

	private String mcaPhone;

	//private String mcaPicture;
	private String mcaPictureFileName;
	
	private String mcaPicturePath;

	private String mcaStatus;

	private String mcaTitle;

	private String mcaType;

	private String mcaUsername;

	@XStreamAlias("missAccount")
	private MissAccount missAccount;

	@XStreamAlias("missSery")
	private MissSery missSery;
	
	@XStreamAlias("missCareerMaster")
	private MissCareerMaster missCareerMaster; 
	
	@XStreamAlias("missIndustryMaster")
	private MissIndustryMaster missIndustryMaster;
	private String mcaPictureHotlink;
	private String mcaPosition;
	private String mcaTitleType;
	 
	//ext 
	private String section; 
	private String mcaIds;
	private String amount;
	
	@XStreamImplicit(itemFieldName="missSeriesMap")
	private List<MissSeriesMap> missSeriesMap;
			
    public MissCandidate() {
    }

	public Long getMcaId() {
		return this.mcaId;
	}

	public void setMcaId(Long mcaId) {
		this.mcaId = mcaId;
	}

	public Date getMcaBirthDate() {
		return this.mcaBirthDate;
	}

	public void setMcaBirthDate(Date mcaBirthDate) {
		this.mcaBirthDate = mcaBirthDate;
	}

	public String getMcaCitizenId() {
		return this.mcaCitizenId;
	}

	public void setMcaCitizenId(String mcaCitizenId) {
		this.mcaCitizenId = mcaCitizenId;
	}

	public String getMcaDepartment() {
		return this.mcaDepartment;
	}

	public void setMcaDepartment(String mcaDepartment) {
		this.mcaDepartment = mcaDepartment;
	}

	public String getMcaEmail() {
		return this.mcaEmail;
	}

	public void setMcaEmail(String mcaEmail) {
		this.mcaEmail = mcaEmail;
	}

	public String getMcaFirstName() {
		return this.mcaFirstName;
	}

	public void setMcaFirstName(String mcaFirstName) {
		this.mcaFirstName = mcaFirstName;
	}

	public String getMcaGender() {
		return this.mcaGender;
	}

	public void setMcaGender(String mcaGender) {
		this.mcaGender = mcaGender;
	}

	public String getMcaLastName() {
		return this.mcaLastName;
	}

	public void setMcaLastName(String mcaLastName) {
		this.mcaLastName = mcaLastName;
	}

	public Timestamp getMcaLastlogin() {
		return this.mcaLastlogin;
	}

	public void setMcaLastlogin(Timestamp mcaLastlogin) {
		this.mcaLastlogin = mcaLastlogin;
	}

	public String getMcaPassword() {
		return this.mcaPassword;
	}

	public void setMcaPassword(String mcaPassword) {
		this.mcaPassword = mcaPassword;
	}

	public String getMcaPhone() {
		return this.mcaPhone;
	}

	public void setMcaPhone(String mcaPhone) {
		this.mcaPhone = mcaPhone;
	}


	public String getMcaPictureFileName() {
		return mcaPictureFileName;
	}

	public void setMcaPictureFileName(String mcaPictureFileName) {
		this.mcaPictureFileName = mcaPictureFileName;
	}

	public String getMcaStatus() {
		return this.mcaStatus;
	}

	public void setMcaStatus(String mcaStatus) {
		this.mcaStatus = mcaStatus;
	}

	public String getMcaTitle() {
		return this.mcaTitle;
	}

	public void setMcaTitle(String mcaTitle) {
		this.mcaTitle = mcaTitle;
	}

	public String getMcaType() {
		return this.mcaType;
	}

	public void setMcaType(String mcaType) {
		this.mcaType = mcaType;
	}

	public String getMcaUsername() {
		return this.mcaUsername;
	}

	public void setMcaUsername(String mcaUsername) {
		this.mcaUsername = mcaUsername;
	}

	public MissAccount getMissAccount() {
		return this.missAccount;
	}

	public void setMissAccount(MissAccount missAccount) {
		this.missAccount = missAccount;
	}
	
	public MissSery getMissSery() {
		return this.missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getMcaIds() {
		return mcaIds;
	}

	public void setMcaIds(String mcaIds) {
		this.mcaIds = mcaIds;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMcaPicturePath() {
		return mcaPicturePath;
	}

	public void setMcaPicturePath(String mcaPicturePath) {
		this.mcaPicturePath = mcaPicturePath;
	}

	public String getMcaPictureHotlink() {
		return mcaPictureHotlink;
	}

	public void setMcaPictureHotlink(String mcaPictureHotlink) {
		this.mcaPictureHotlink = mcaPictureHotlink;
	}

	
	public String getMcaTitleType() {
		return mcaTitleType;
	}

	public void setMcaTitleType(String mcaTitleType) {
		this.mcaTitleType = mcaTitleType;
	}

	public List<MissSeriesMap> getMissSeriesMap() {
		return missSeriesMap;
	}

	public void setMissSeriesMap(List<MissSeriesMap> missSeriesMap) {
		this.missSeriesMap = missSeriesMap;
	}

	public String getMcaPosition() {
		return mcaPosition;
	}

	public void setMcaPosition(String mcaPosition) {
		this.mcaPosition = mcaPosition;
	}

	public MissCareerMaster getMissCareerMaster() {
		return missCareerMaster;
	}

	public void setMissCareerMaster(MissCareerMaster missCareerMaster) {
		this.missCareerMaster = missCareerMaster;
	}

	public MissIndustryMaster getMissIndustryMaster() {
		return missIndustryMaster;
	}

	public void setMissIndustryMaster(MissIndustryMaster missIndustryMaster) {
		this.missIndustryMaster = missIndustryMaster;
	}

	
	/*public Set<MissTest> getMissTests() {
		return this.missTests;
	}

	public void setMissTests(Set<MissTest> missTests) {
		this.missTests = missTests;
	}
	
	public Set<MissTestResult> getMissTestResults() {
		return this.missTestResults;
	}

	public void setMissTestResults(Set<MissTestResult> missTestResults) {
		this.missTestResults = missTestResults;
	}*/
	
}