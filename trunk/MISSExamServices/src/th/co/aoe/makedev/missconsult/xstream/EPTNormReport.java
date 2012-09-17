package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
@XStreamAlias("EPTNormReport")
public class EPTNormReport  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mode;
	private String query;
	private String maId;
	
	@XStreamImplicit(itemFieldName="companyList")
	private List<List<String>> companyList;
	
	private String eptCount; //1a
	
	@XStreamImplicit(itemFieldName="liePercent")
	private List<List<String>> liePercent; //1b
	
	@XStreamImplicit(itemFieldName="groupPercent")
	private List<String> groupPercent; // 1c
	
	@XStreamImplicit(itemFieldName="careerPercent")
	private List<List<String>> careerPercent; //1d
	
	@XStreamImplicit(itemFieldName="genderPercent")
	private List<List<String>> genderPercent; //1e
	
	@XStreamImplicit(itemFieldName="industryPercent")
	private List<List<String>> industryPercent; //1f
	
	@XStreamImplicit(itemFieldName="agePercent")
	private List<List<String>> agePercent; // 1g
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getMaId() {
		return maId;
	}
	public void setMaId(String maId) {
		this.maId = maId;
	}
	public List<List<String>> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<List<String>> companyList) {
		this.companyList = companyList;
	}
	public String getEptCount() {
		return eptCount;
	}
	public void setEptCount(String eptCount) {
		this.eptCount = eptCount;
	}
	public List<List<String>> getLiePercent() {
		return liePercent;
	}
	public void setLiePercent(List<List<String>> liePercent) {
		this.liePercent = liePercent;
	}
	public List<String> getGroupPercent() {
		return groupPercent;
	}
	public void setGroupPercent(List<String> groupPercent) {
		this.groupPercent = groupPercent;
	}
	public List<List<String>> getCareerPercent() {
		return careerPercent;
	}
	public void setCareerPercent(List<List<String>> careerPercent) {
		this.careerPercent = careerPercent;
	}
	public List<List<String>> getGenderPercent() {
		return genderPercent;
	}
	public void setGenderPercent(List<List<String>> genderPercent) {
		this.genderPercent = genderPercent;
	}
	public List<List<String>> getIndustryPercent() {
		return industryPercent;
	}
	public void setIndustryPercent(List<List<String>> industryPercent) {
		this.industryPercent = industryPercent;
	}
	public List<List<String>> getAgePercent() {
		return agePercent;
	}
	public void setAgePercent(List<List<String>> agePercent) {
		this.agePercent = agePercent;
	}
}

