package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_ACCOUNT database table.
 * 
 */
@Entity
@Table(name="MISS_ACCOUNT")
public class MissAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MA_ID")
	private Long maId;

	@Column(name="MA_ADDRESS")
	private String maAddress;

	@Column(name="MA_BACKGROUND_COLOR")
	private String maBackgroundColor;

	@Column(name="MA_BACKGROUND_PICTURE")
	private String maBackgroundPicture;
	
	@Column(name="MA_GRADE")
	private String maGrade;

	@Column(name="MA_CUSTOMIZE_COLOR")
	private String maCustomizeColor;

	@Column(name="MA_CUSTOMIZE_LOGO_PATH")
	private String maCustomizeLogoPath;
	

	@Column(name="MA_CUSTOMIZE_LOGO_FILE_NAME")
	private String maCustomizeLogoFileName;

	@Column(name="MA_CUSTOMIZE_LOGO_HOTLIK")
	private String maCustomizeLogoHotlink;
	
	@Lob()
	@Column(name="MA_CUSTOMIZE_PASS_MESSAGE")
	private String maCustomizePassMessage;
	@Lob()
	@Column(name="MA_CUSTOMIZE_REJECT_MESSAGE")
	private String maCustomizeRejectMessage;
	@Lob()
	@Column(name="MA_CUSTOMIZE_RETEST_MESSAGE")
	private String maCustomizeRetestMessage;

	@Column(name="MA_EMAIL")
	private String maEmail;

	@Column(name="MA_EXPIRE")
	private Timestamp maExpire;

	@Column(name="MA_FAX")
	private String maFax;

	@Column(name="MA_LOGO")
	private String maLogo;

 
	@Column(name="MA_NAME")
	private String maName;

	@Column(name="MA_PASSWORD")
	private String maPassword;

	@Column(name="MA_PHONE")
	private String maPhone;

	@Column(name="MA_TOTAL_UNIT")
	private Long maTotalUnit;

	@Column(name="MA_TYPE")
	private String maType; // 0=miss ,1 = company

	@Column(name="MA_USED_UNIT")
	private Long maUsedUnit;

	@Column(name="MA_USERNAME")
	private String maUsername;
	
	
	@Column(name="MA_CUSTOMIZE_SLOGAN")
	private String maCustomizeSlogan;

	@Column(name="MA_REGISTER_TYPE")
	private String maRegisterType;

	@Column(name="MA_REGISTER_NO")
	private String maRegisterNo;
	
	@Column(name="MA_REGISTER_TO")
	private Timestamp maRegisterTo;
	
	@Column(name="MA_REGISTER_FROM")
	private Timestamp maRegisterFrom;
	 
	@Column(name="MA_REGISTER_DATE")
	private Timestamp maRegisterDate;
	
/*	@Column(name="MA_SEX")
	private String maSex;*/

	//bi-directional many-to-one association to MissAccountSeriesMap
	/*@OneToMany(mappedBy="missAccount")
	private Set<MissAccountSeriesMap> missAccountSeriesMaps;*/

	//bi-directional many-to-one association to MissCandidate
	/*@OneToMany(mappedBy="missAccount")
	private Set<MissCandidate> missCandidates;*/

	//bi-directional many-to-one association to MissTodo
	/*@OneToMany(mappedBy="missAccount")
	private Set<MissTodo> missTodos;*/

   /* public String getMaSex() {
		return maSex;
	}

	public void setMaSex(String maSex) {
		this.maSex = maSex;
	}*/



	public MissAccount() {
    }

	public Long getMaId() {
		return this.maId;
	}

	public void setMaId(Long maId) {
		this.maId = maId;
	}

	public String getMaAddress() {
		return this.maAddress;
	}

	public void setMaAddress(String maAddress) {
		this.maAddress = maAddress;
	}

	public String getMaBackgroundColor() {
		return this.maBackgroundColor;
	}

	public void setMaBackgroundColor(String maBackgroundColor) {
		this.maBackgroundColor = maBackgroundColor;
	}

	public String getMaBackgroundPicture() {
		return this.maBackgroundPicture;
	}

	public void setMaBackgroundPicture(String maBackgroundPicture) {
		this.maBackgroundPicture = maBackgroundPicture;
	}


	public String getMaCustomizeColor() {
		return this.maCustomizeColor;
	}

	public void setMaCustomizeColor(String maCustomizeColor) {
		this.maCustomizeColor = maCustomizeColor;
	}


	public String getMaCustomizeLogoPath() {
		return maCustomizeLogoPath;
	}

	public void setMaCustomizeLogoPath(String maCustomizeLogoPath) {
		this.maCustomizeLogoPath = maCustomizeLogoPath;
	}

	public String getMaCustomizeLogoFileName() {
		return maCustomizeLogoFileName;
	}

	public void setMaCustomizeLogoFileName(String maCustomizeLogoFileName) {
		this.maCustomizeLogoFileName = maCustomizeLogoFileName;
	}

	public String getMaCustomizeLogoHotlink() {
		return maCustomizeLogoHotlink;
	}

	public void setMaCustomizeLogoHotlink(String maCustomizeLogoHotlink) {
		this.maCustomizeLogoHotlink = maCustomizeLogoHotlink;
	}

	public String getMaCustomizePassMessage() {
		return this.maCustomizePassMessage;
	}

	public void setMaCustomizePassMessage(String maCustomizePassMessage) {
		this.maCustomizePassMessage = maCustomizePassMessage;
	}

	public String getMaCustomizeRejectMessage() {
		return this.maCustomizeRejectMessage;
	}

	public void setMaCustomizeRejectMessage(String maCustomizeRejectMessage) {
		this.maCustomizeRejectMessage = maCustomizeRejectMessage;
	}

	public String getMaCustomizeRetestMessage() {
		return this.maCustomizeRetestMessage;
	}

	public void setMaCustomizeRetestMessage(String maCustomizeRetestMessage) {
		this.maCustomizeRetestMessage = maCustomizeRetestMessage;
	}

	public String getMaEmail() {
		return this.maEmail;
	}

	public void setMaEmail(String maEmail) {
		this.maEmail = maEmail;
	}

	public Timestamp getMaExpire() {
		return this.maExpire;
	}

	public void setMaExpire(Timestamp maExpire) {
		this.maExpire = maExpire;
	}

	public String getMaFax() {
		return this.maFax;
	}

	public void setMaFax(String maFax) {
		this.maFax = maFax;
	}

	public String getMaLogo() {
		return this.maLogo;
	}

	public void setMaLogo(String maLogo) {
		this.maLogo = maLogo;
	}

	public String getMaName() {
		return this.maName;
	}

	public void setMaName(String maName) {
		this.maName = maName;
	}

	public String getMaPassword() {
		return this.maPassword;
	}

	public void setMaPassword(String maPassword) {
		this.maPassword = maPassword;
	}

	public String getMaPhone() {
		return this.maPhone;
	}

	public void setMaPhone(String maPhone) {
		this.maPhone = maPhone;
	}

	public Long getMaTotalUnit() {
		return this.maTotalUnit;
	}

	public void setMaTotalUnit(Long maTotalUnit) {
		this.maTotalUnit = maTotalUnit;
	}

	public String getMaType() {
		return this.maType;
	}

	public void setMaType(String maType) {
		this.maType = maType;
	}

	public Long getMaUsedUnit() {
		return this.maUsedUnit;
	}

	public void setMaUsedUnit(Long maUsedUnit) {
		this.maUsedUnit = maUsedUnit;
	}

	public String getMaUsername() {
		return this.maUsername;
	}

	public void setMaUsername(String maUsername) {
		this.maUsername = maUsername;
	}

	public String getMaCustomizeSlogan() {
		return maCustomizeSlogan;
	}

	public void setMaCustomizeSlogan(String maCustomizeSlogan) {
		this.maCustomizeSlogan = maCustomizeSlogan;
	}

	public String getMaRegisterType() {
		return maRegisterType;
	}

	public void setMaRegisterType(String maRegisterType) {
		this.maRegisterType = maRegisterType;
	}

	public String getMaRegisterNo() {
		return maRegisterNo;
	}

	public void setMaRegisterNo(String maRegisterNo) {
		this.maRegisterNo = maRegisterNo;
	}

	public Timestamp getMaRegisterTo() {
		return maRegisterTo;
	}

	public void setMaRegisterTo(Timestamp maRegisterTo) {
		this.maRegisterTo = maRegisterTo;
	}

	public Timestamp getMaRegisterFrom() {
		return maRegisterFrom;
	}

	public void setMaRegisterFrom(Timestamp maRegisterFrom) {
		this.maRegisterFrom = maRegisterFrom;
	}

	public Timestamp getMaRegisterDate() {
		return maRegisterDate;
	}

	public void setMaRegisterDate(Timestamp maRegisterDate) {
		this.maRegisterDate = maRegisterDate;
	}

	public String getMaGrade() {
		return maGrade;
	}

	public void setMaGrade(String maGrade) {
		this.maGrade = maGrade;
	}

	
}