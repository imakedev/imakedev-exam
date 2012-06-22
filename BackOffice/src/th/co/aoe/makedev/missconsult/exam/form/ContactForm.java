package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.MissContact;

public class ContactForm extends CommonForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MissContact missContact;
	private String mcontactBirthDate;
	private String maId;
	
	private String mcontactIdArray;

	public ContactForm() {
		missContact = new MissContact();
	}

	public MissContact getMissContact() {
		return missContact;
	}

	public void setMissContact(MissContact missContact) {
		this.missContact = missContact;
	}

	public String getMaId() {
		return maId;
	}

	public void setMaId(String maId) {
		this.maId = maId;
	}

	public String getMcontactBirthDate() {
		return mcontactBirthDate;
	}

	public void setMcontactBirthDate(String mcontactBirthDate) {
		this.mcontactBirthDate = mcontactBirthDate;
	}

	public String getMcontactIdArray() {
		return mcontactIdArray;
	}

	public void setMcontactIdArray(String mcontactIdArray) {
		this.mcontactIdArray = mcontactIdArray;
	}
	
}
