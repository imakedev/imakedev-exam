package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_ACCOUNT database table.
 * 
 */
@XStreamAlias("MissAccount")
public class MissAccount extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long maId;

	private String maAddress;

	private String maBackgroundColor;

	private String maBackgroundPicture;
	
	@XStreamAlias("maContactBirthDate")
	private Date maContactBirthDate;

	private String maContactDepartment;

	private String maContactEmail;

	private String maContactFax;

	private String maContactGender;

	private String maContactLastname;

	private String maContactName;

	private String maContactPhone;

	private String maContactPicture;
	
	private String maContactPicturePath;

	private String maContactTitle;

	private String maCustomizeColor;

	private String maCustomizeLogo;

	private String maCustomizePassMessage;

	private String maCustomizeRejectMessage;

	private String maCustomizeRetestMessage;

	private String maEmail;

	@XStreamAlias("maExpire")
	private Timestamp maExpire;

	private String maFax;

	private String maLogo;

	private String maName;

	private String maPassword;

	private String maPhone;

	private Long maTotalUnit;

	private String maType;

	private Long maUsedUnit;

	private String maUsername;

	private String maCustomizeSlogan;

	private String maRegisterType;

	private String maRegisterNo;
	
	@XStreamAlias("maRegisterTo")
	private Timestamp maRegisterTo;
	
	@XStreamAlias("maRegisterFrom")
	private Timestamp maRegisterFrom;
	
	@XStreamAlias("maRegisterDate")
	private Timestamp maRegisterDate;
//	private String maSex;

	//ext 
	private String section; 
		private String[] meIds;
	//	private String testStr;
		private String maIds;
	//private String maContactBirthDateStr;
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

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String[] getMeIds() {
		return meIds;
	}

	public void setMeIds(String[] meIds) {
		this.meIds = meIds;
	}

	public String getMaIds() {
		return maIds;
	}

	public void setMaIds(String maIds) {
		this.maIds = maIds;
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

	public String getMaContactPicturePath() {
		return maContactPicturePath;
	}

	public void setMaContactPicturePath(String maContactPicturePath) {
		this.maContactPicturePath = maContactPicturePath;
	}

/*	public String getMaSex() {
		return maSex;
	}

	public void setMaSex(String maSex) {
		this.maSex = maSex;
	}*/

/*	public String getMaContactBirthDateStr() {
		return maContactBirthDateStr;
	}

	public void setMaContactBirthDateStr(String maContactBirthDateStr) {
		this.maContactBirthDateStr = maContactBirthDateStr;
	}*/
	

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