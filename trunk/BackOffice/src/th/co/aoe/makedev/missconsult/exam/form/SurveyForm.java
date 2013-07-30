// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:13:44 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SurveyForm.java

package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

public class SurveyForm implements Serializable {
    private static final long serialVersionUID = 1L;
	public Long maId;
	public Long msId;
	public String[] survey_name;
	public String[] survey_email;
	public String[] survey_group;
	public int amountSend;
	public String subject;
	public String mailMessage;
	
	public String[] amountSendArray; 
	private Long participant_msId;
	public SurveyForm() {
	}

	public Long getMaId() {
		return maId;
	}

	public Long getMsId() {
		return msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public void setMaId(Long maId) {
		this.maId = maId;
	}

	public int getAmountSend() {
		return amountSend;
	}

	public void setAmountSend(int amountSend) {
		this.amountSend = amountSend;
	}

	public String[] getSurvey_name() {
		return survey_name;
	}

	public void setSurvey_name(String[] survey_name) {
		this.survey_name = survey_name;
	}

	public String[] getSurvey_email() {
		return survey_email;
	}

	public void setSurvey_email(String[] survey_email) {
		this.survey_email = survey_email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(String mailMessage) {
		this.mailMessage = mailMessage;
	}

	public String[] getSurvey_group() {
		return survey_group;
	}

	public void setSurvey_group(String[] survey_group) {
		this.survey_group = survey_group;
	}

	public String[] getAmountSendArray() {
		return amountSendArray;
	}

	public void setAmountSendArray(String[] amountSendArray) {
		this.amountSendArray = amountSendArray;
	}

	public Long getParticipant_msId() {
		return participant_msId;
	}

	public void setParticipant_msId(Long participant_msId) {
		this.participant_msId = participant_msId;
	}
	
}
