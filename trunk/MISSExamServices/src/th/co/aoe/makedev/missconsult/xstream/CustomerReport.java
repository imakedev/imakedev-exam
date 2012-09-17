package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
@XStreamAlias("CustomerReport")
public class CustomerReport  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mode;
	private String magId;
	private String query;
	@XStreamImplicit(itemFieldName="groupList")
	private List<List<String>> groupList; //2a
	
	@XStreamImplicit(itemFieldName="industryPercent")
	private List<List<String>> industryPercent; //2b
	
	@XStreamImplicit(itemFieldName="orderStat")
	private List<List<String>> orderStat; // 2c
	
	@XStreamImplicit(itemFieldName="usedStat")
	private List<List<String>> usedStat; // 2d
	
	@XStreamImplicit(itemFieldName="lastLogin")
	private List<List<String>> lastLogin; // 2e
	
	@XStreamImplicit(itemFieldName="deadstock")
	private List<List<String>> deadstock; // 2h 
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMagId() {
		return magId;
	}
	public void setMagId(String magId) {
		this.magId = magId;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public List<List<String>> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<List<String>> groupList) {
		this.groupList = groupList;
	}
	public List<List<String>> getIndustryPercent() {
		return industryPercent;
	}
	public void setIndustryPercent(List<List<String>> industryPercent) {
		this.industryPercent = industryPercent;
	}
	public List<List<String>> getOrderStat() {
		return orderStat;
	}
	public void setOrderStat(List<List<String>> orderStat) {
		this.orderStat = orderStat;
	}
	public List<List<String>> getUsedStat() {
		return usedStat;
	}
	public void setUsedStat(List<List<String>> usedStat) {
		this.usedStat = usedStat;
	}
	public List<List<String>> getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(List<List<String>> lastLogin) {
		this.lastLogin = lastLogin;
	}
	public List<List<String>> getDeadstock() {
		return deadstock;
	}
	public void setDeadstock(List<List<String>> deadstock) {
		this.deadstock = deadstock;
	}
}
