package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_CONTACT database table.
 * 
 */
@XStreamAlias("MissContact")
public class MissContact  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mcontactId;
	private String mcontactUsername;
	private String mcontactPassword;
	private Date mcontactBirthDate;

	private String mcontactDepartment;

	private String mcontactEmail;

	private String mcontactEmail2;

	private String mcontactFax;

	private String mcontactGender;

	private String mcontactLastname;

	private String mcontactName;

	private String mcontactMobilePhone;
	
	private String mcontactPhone;

	private String mcontactPictureFileName;

	private String mcontactPictureHotlink;

	private String mcontactPicturePath;

	private String mcontactPostion;

	private String mcontactTitleType;

	private Long mcontactRef;

	private String mcontactTitle;

	private String mcontactType;
	private String mcontactIsAdmin;
	private Long rcId;
	@XStreamAlias("mcontactCreatedTime")
	private Timestamp mcontactCreatedTime;
	
	@XStreamAlias("mcontactUpdatedTime")
	private Timestamp mcontactUpdatedTime;
	//ext 
	private String isMC;
	private String maCustomizeLogoPath;
	private String maCustomizeLogoMCPath; // for default
	
	private String maCustomizeHeadColor;
	private String maCustomizeColor;
	private String maBackgroundColor;
	private String maGrade;
	
	private MissTheme missTheme;
	public String getMaGrade() {
		return maGrade;
	}

	public void setMaGrade(String maGrade) {
		this.maGrade = maGrade;
	}

	public String getMaCustomizeLogoPath() {
		return maCustomizeLogoPath;
	}

	public void setMaCustomizeLogoPath(String maCustomizeLogoPath) {
		this.maCustomizeLogoPath = maCustomizeLogoPath;
	}

	public String getMaCustomizeLogoMCPath() {
		return maCustomizeLogoMCPath;
	}

	public void setMaCustomizeLogoMCPath(String maCustomizeLogoMCPath) {
		this.maCustomizeLogoMCPath = maCustomizeLogoMCPath;
	}

	public String getMaCustomizeHeadColor() {
		return maCustomizeHeadColor;
	}

	public void setMaCustomizeHeadColor(String maCustomizeHeadColor) {
		this.maCustomizeHeadColor = maCustomizeHeadColor;
	}

	public String getMaCustomizeColor() {
		return maCustomizeColor;
	}

	public void setMaCustomizeColor(String maCustomizeColor) {
		this.maCustomizeColor = maCustomizeColor;
	}

	public String getMaBackgroundColor() {
		return maBackgroundColor;
	}

	public void setMaBackgroundColor(String maBackgroundColor) {
		this.maBackgroundColor = maBackgroundColor;
	}

	public String getIsMC() {
		return isMC;
	}

	public void setIsMC(String isMC) {
		this.isMC = isMC;
	}

	public Long getRcId() {
		return rcId;
	}

	public void setRcId(Long rcId) {
		this.rcId = rcId;
	}

	//ext 
	private String section; 
	private String mcontactIds;
	
    public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getMcontactIds() {
		return mcontactIds;
	}

	public void setMcontactIds(String mcontactIds) {
		this.mcontactIds = mcontactIds;
	}

	public MissContact() {
    }

	public Long getMcontactId() {
		return this.mcontactId;
	}

	public void setMcontactId(Long mcontactId) {
		this.mcontactId = mcontactId;
	}

	public Date getMcontactBirthDate() {
		return this.mcontactBirthDate;
	}

	public void setMcontactBirthDate(Date mcontactBirthDate) {
		this.mcontactBirthDate = mcontactBirthDate;
	}

	public String getMcontactDepartment() {
		return this.mcontactDepartment;
	}

	public void setMcontactDepartment(String mcontactDepartment) {
		this.mcontactDepartment = mcontactDepartment;
	}

	public String getMcontactEmail() {
		return this.mcontactEmail;
	}

	public void setMcontactEmail(String mcontactEmail) {
		this.mcontactEmail = mcontactEmail;
	}

	public String getMcontactEmail2() {
		return this.mcontactEmail2;
	}

	public void setMcontactEmail2(String mcontactEmail2) {
		this.mcontactEmail2 = mcontactEmail2;
	}

	public String getMcontactFax() {
		return this.mcontactFax;
	}

	public void setMcontactFax(String mcontactFax) {
		this.mcontactFax = mcontactFax;
	}

	public String getMcontactGender() {
		return this.mcontactGender;
	}

	public void setMcontactGender(String mcontactGender) {
		this.mcontactGender = mcontactGender;
	}

	public String getMcontactLastname() {
		return this.mcontactLastname;
	}

	public void setMcontactLastname(String mcontactLastname) {
		this.mcontactLastname = mcontactLastname;
	}

	public String getMcontactName() {
		return this.mcontactName;
	}

	public void setMcontactName(String mcontactName) {
		this.mcontactName = mcontactName;
	}

	public String getMcontactPhone() {
		return this.mcontactPhone;
	}

	public void setMcontactPhone(String mcontactPhone) {
		this.mcontactPhone = mcontactPhone;
	}



	public String getMcontactPictureFileName() {
		return mcontactPictureFileName;
	}

	public void setMcontactPictureFileName(String mcontactPictureFileName) {
		this.mcontactPictureFileName = mcontactPictureFileName;
	}

	public String getMcontactPictureHotlink() {
		return this.mcontactPictureHotlink;
	}

	public void setMcontactPictureHotlink(String mcontactPictureHotlink) {
		this.mcontactPictureHotlink = mcontactPictureHotlink;
	}

	public String getMcontactPicturePath() {
		return this.mcontactPicturePath;
	}

	public void setMcontactPicturePath(String mcontactPicturePath) {
		this.mcontactPicturePath = mcontactPicturePath;
	}

	public String getMcontactPostion() {
		return this.mcontactPostion;
	}

	public void setMcontactPostion(String mcontactPostion) {
		this.mcontactPostion = mcontactPostion;
	}



	public String getMcontactTitleType() {
		return mcontactTitleType;
	}

	public void setMcontactTitleType(String mcontactTitleType) {
		this.mcontactTitleType = mcontactTitleType;
	}

	public Long getMcontactRef() {
		return this.mcontactRef;
	}

	public String getMcontactUsername() {
		return mcontactUsername;
	}

	public void setMcontactUsername(String mcontactUsername) {
		this.mcontactUsername = mcontactUsername;
	}

	public String getMcontactPassword() {
		return mcontactPassword;
	}

	public void setMcontactPassword(String mcontactPassword) {
		this.mcontactPassword = mcontactPassword;
	}

	public void setMcontactRef(Long mcontactRef) {
		this.mcontactRef = mcontactRef;
	}

	public String getMcontactTitle() {
		return this.mcontactTitle;
	}

	public void setMcontactTitle(String mcontactTitle) {
		this.mcontactTitle = mcontactTitle;
	}

	public String getMcontactType() {
		return this.mcontactType;
	}

	public void setMcontactType(String mcontactType) {
		this.mcontactType = mcontactType;
	}

	public String getMcontactIsAdmin() {
		return mcontactIsAdmin;
	}

	public void setMcontactIsAdmin(String mcontactIsAdmin) {
		this.mcontactIsAdmin = mcontactIsAdmin;
	}

	public MissTheme getMissTheme() {
		return missTheme;
	}

	public void setMissTheme(MissTheme missTheme) {
		this.missTheme = missTheme;
	}

	public Timestamp getMcontactCreatedTime() {
		return mcontactCreatedTime;
	}

	public void setMcontactCreatedTime(Timestamp mcontactCreatedTime) {
		this.mcontactCreatedTime = mcontactCreatedTime;
	}

	public Timestamp getMcontactUpdatedTime() {
		return mcontactUpdatedTime;
	}

	public void setMcontactUpdatedTime(Timestamp mcontactUpdatedTime) {
		this.mcontactUpdatedTime = mcontactUpdatedTime;
	}

	public String getMcontactMobilePhone() {
		return mcontactMobilePhone;
	}

	public void setMcontactMobilePhone(String mcontactMobilePhone) {
		this.mcontactMobilePhone = mcontactMobilePhone;
	}

}