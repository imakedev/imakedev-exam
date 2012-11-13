package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the MISS_CANDIDATE database table.
 * 
 */
@Entity
@Table(name="MISS_CANDIDATE")
public class MissCandidate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MCA_ID")
	private Long mcaId;

    @Temporal( TemporalType.DATE)
	@Column(name="MCA_BIRTH_DATE")
	private Date mcaBirthDate;

	@Column(name="MCA_CITIZEN_ID")
	private String mcaCitizenId;

	@Column(name="MCA_DEPARTMENT")
	private String mcaDepartment;

	@Column(name="MCA_EMAIL")
	private String mcaEmail;

	@Column(name="MCA_FIRST_NAME")
	private String mcaFirstName;

	@Column(name="MCA_GENDER")
	private String mcaGender;

	@Column(name="MCA_LAST_NAME")
	private String mcaLastName;

	@Column(name="MCA_LASTLOGIN")
	private Timestamp mcaLastlogin;

	@Column(name="MCA_PASSWORD")
	private String mcaPassword;

	@Column(name="MCA_PHONE")
	private String mcaPhone;

	@Column(name="MCA_PICTURE_FILE_NAME")
	private String mcaPictureFileName;
	
	@Column(name="MCA_PICTURE_PATH")
	private String mcaPicturePath;

	@Column(name="MCA_STATUS")
	private String mcaStatus;

	@Column(name="MCA_TITLE")
	private String mcaTitle;

	@Column(name="MCA_TYPE")
	private String mcaType;

	@Column(name="MCA_USERNAME")
	private String mcaUsername;

	//bi-directional many-to-one association to MissAccount
    @ManyToOne
	@JoinColumn(name="MA_ID")
	private MissAccount missAccount;

	//bi-directional many-to-one association to MissSery
    @ManyToOne
	@JoinColumn(name="MS_ID")
	private MissSery missSery;
    
    @ManyToOne
 	@JoinColumn(name="MCM_ID")
 	private MissCareerMaster missCareerMaster; 

 	//bi-directional many-to-one association to MissIndustryMaster
     @ManyToOne
 	@JoinColumn(name="MIM_ID")
 	private MissIndustryMaster missIndustryMaster;
     
    @Column(name="MIM_EXT")
  	private String mimExt;
     
	@Column(name="MCA_PICTURE_HOTLINK")
	private String mcaPictureHotlink;
  

	@Column(name="MCA_POSITION")
	private String mcaPosition;

	@Column(name="MCA_TITLE_TYPE")
	private String mcaTitleType;
	

	
	
	
	 
	
	/*//bi-directional many-to-one association to MissTest
	@OneToMany(mappedBy="missCandidate")
	private Set<MissTest> missTests;*/

	/*//bi-directional many-to-one association to MissTestResult
	@OneToMany(mappedBy="missCandidate")
	private Set<MissTestResult> missTestResults;*/

    

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

	

	public String getMcaPosition() {
		return mcaPosition;
	}

	public void setMcaPosition(String mcaPosition) {
		this.mcaPosition = mcaPosition;
	}

	public String getMcaTitleType() {
		return mcaTitleType;
	}

	public void setMcaTitleType(String mcaTitleType) {
		this.mcaTitleType = mcaTitleType;
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

	public String getMimExt() {
		return mimExt;
	}

	public void setMimExt(String mimExt) {
		this.mimExt = mimExt;
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