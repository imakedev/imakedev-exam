// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:13:28 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ResultForm.java

package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.MissTestResult;

public class ResultForm extends CommonForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private MissTestResult missTestResult;
	private String testFrom;
	private String testTo;
	private String mcaSeries;
	private String mcaUsername;
	private String mcaFirstName;
	private String mcaLastName;
	private String mcaPosition;
	private String mcaDepartment;
	private String mcaCompanyName;
	private String mtrIdArray;
	
	public MissTestResult getMissTestResult() {
		return missTestResult;
	}

	public void setMissTestResult(MissTestResult missTestResult) {
		this.missTestResult = missTestResult;
	}

	public String getTestFrom() {
		return testFrom;
	}

	public void setTestFrom(String testFrom) {
		this.testFrom = testFrom;
	}

	public String getTestTo() {
		return testTo;
	}

	public void setTestTo(String testTo) {
		this.testTo = testTo;
	}

	public String getMcaSeries() {
		return mcaSeries;
	}

	public void setMcaSeries(String mcaSeries) {
		this.mcaSeries = mcaSeries;
	}

	public String getMcaUsername() {
		return mcaUsername;
	}

	public void setMcaUsername(String mcaUsername) {
		this.mcaUsername = mcaUsername;
	}

	public String getMcaFirstName() {
		return mcaFirstName;
	}

	public void setMcaFirstName(String mcaFirstName) {
		this.mcaFirstName = mcaFirstName;
	}

	public String getMcaLastName() {
		return mcaLastName;
	}

	public void setMcaLastName(String mcaLastName) {
		this.mcaLastName = mcaLastName;
	}

	

	public String getMcaPosition() {
		return mcaPosition;
	}

	public void setMcaPosition(String mcaPosition) {
		this.mcaPosition = mcaPosition;
	}

	public String getMcaDepartment() {
		return mcaDepartment;
	}

	public void setMcaDepartment(String mcaDepartment) {
		this.mcaDepartment = mcaDepartment;
	}

	public String getMcaCompanyName() {
		return mcaCompanyName;
	}

	public void setMcaCompanyName(String mcaCompanyName) {
		this.mcaCompanyName = mcaCompanyName;
	}

	public String getMtrIdArray() {
		return mtrIdArray;
	}

	public void setMtrIdArray(String mtrIdArray) {
		this.mtrIdArray = mtrIdArray;
	}



	public ResultForm() {
		missTestResult = new MissTestResult();
	}

}
