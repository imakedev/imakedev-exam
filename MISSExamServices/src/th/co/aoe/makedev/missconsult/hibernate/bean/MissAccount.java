package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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

    @Temporal( TemporalType.DATE)
	@Column(name="MA_CONTACT_BIRTH_DATE")
	private Date maContactBirthDate;

	@Column(name="MA_CONTACT_DEPARTMENT")
	private String maContactDepartment;

	@Column(name="MA_CONTACT_EMAIL")
	private String maContactEmail;

	@Column(name="MA_CONTACT_FAX")
	private String maContactFax;

	@Column(name="MA_CONTACT_GENDER")
	private String maContactGender;

	@Column(name="MA_CONTACT_LASTNAME")
	private String maContactLastname;

	@Column(name="MA_CONTACT_NAME")
	private String maContactName;

	@Column(name="MA_CONTACT_PHONE")
	private String maContactPhone;

	@Column(name="MA_CONTACT_PICTURE")
	private String maContactPicture;

	@Column(name="MA_CONTACT_TITLE")
	private String maContactTitle;

	@Column(name="MA_CUSTOMIZE_COLOR")
	private String maCustomizeColor;

	@Column(name="MA_CUSTOMIZE_LOGO")
	private String maCustomizeLogo;
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

	public Date getMaContactBirthDate() {
		return this.maContactBirthDate;
	}

	public void setMaContactBirthDate(Date maContactBirthDate) {
		this.maContactBirthDate = maContactBirthDate;
	}

	public String getMaContactDepartment() {
		return this.maContactDepartment;
	}

	public void setMaContactDepartment(String maContactDepartment) {
		this.maContactDepartment = maContactDepartment;
	}

	public String getMaContactEmail() {
		return this.maContactEmail;
	}

	public void setMaContactEmail(String maContactEmail) {
		this.maContactEmail = maContactEmail;
	}

	public String getMaContactFax() {
		return this.maContactFax;
	}

	public void setMaContactFax(String maContactFax) {
		this.maContactFax = maContactFax;
	}

	public String getMaContactGender() {
		return this.maContactGender;
	}

	public void setMaContactGender(String maContactGender) {
		this.maContactGender = maContactGender;
	}

	public String getMaContactLastname() {
		return this.maContactLastname;
	}

	public void setMaContactLastname(String maContactLastname) {
		this.maContactLastname = maContactLastname;
	}

	public String getMaContactName() {
		return this.maContactName;
	}

	public void setMaContactName(String maContactName) {
		this.maContactName = maContactName;
	}

	public String getMaContactPhone() {
		return this.maContactPhone;
	}

	public void setMaContactPhone(String maContactPhone) {
		this.maContactPhone = maContactPhone;
	}

	public String getMaContactPicture() {
		return this.maContactPicture;
	}

	public void setMaContactPicture(String maContactPicture) {
		this.maContactPicture = maContactPicture;
	}

	public String getMaContactTitle() {
		return this.maContactTitle;
	}

	public void setMaContactTitle(String maContactTitle) {
		this.maContactTitle = maContactTitle;
	}

	public String getMaCustomizeColor() {
		return this.maCustomizeColor;
	}

	public void setMaCustomizeColor(String maCustomizeColor) {
		this.maCustomizeColor = maCustomizeColor;
	}

	public String getMaCustomizeLogo() {
		return this.maCustomizeLogo;
	}

	public void setMaCustomizeLogo(String maCustomizeLogo) {
		this.maCustomizeLogo = maCustomizeLogo;
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

	/*public Set<MissAccountSeriesMap> getMissAccountSeriesMaps() {
		return this.missAccountSeriesMaps;
	}

	public void setMissAccountSeriesMaps(Set<MissAccountSeriesMap> missAccountSeriesMaps) {
		this.missAccountSeriesMaps = missAccountSeriesMaps;
	}
	
	public Set<MissCandidate> getMissCandidates() {
		return this.missCandidates;
	}

	public void setMissCandidates(Set<MissCandidate> missCandidates) {
		this.missCandidates = missCandidates;
	}
	
	public Set<MissTodo> getMissTodos() {
		return this.missTodos;
	}

	public void setMissTodos(Set<MissTodo> missTodos) {
		this.missTodos = missTodos;
	}*/
	
}