package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;
@XStreamAlias("ConsultantReport")
public class ConsultantReport  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mode;
	private String mcontactId;
	private String query;
	private String month;
	private String year;
	
	@XStreamImplicit(itemFieldName="salesList")
	private List<List<String>> salesList; //
	
	@XStreamImplicit(itemFieldName="loginStat")
	private List<List<String>> loginStat; //5a
	
	@XStreamImplicit(itemFieldName="reactiveStat")
	private List<List<String>> reactiveStat; //5b
	
	@XStreamImplicit(itemFieldName="orderStat")
	private List<List<String>> orderStat; //5c
	
	@XStreamImplicit(itemFieldName="saleStat")
	private List<List<String>> saleStat; //5d
	
	@XStreamImplicit(itemFieldName="updateStat")
	private List<List<String>> updateStat; //5e
	
	@XStreamImplicit(itemFieldName="birthdayStat")
	private List<List<String>> birthdayStat; //5f
	
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMcontactId() {
		return mcontactId;
	}
	public void setMcontactId(String mcontactId) {
		this.mcontactId = mcontactId;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<List<String>> getSalesList() {
		return salesList;
	}
	public void setSalesList(List<List<String>> salesList) {
		this.salesList = salesList;
	}
	public List<List<String>> getLoginStat() {
		return loginStat;
	}
	public void setLoginStat(List<List<String>> loginStat) {
		this.loginStat = loginStat;
	}
	public List<List<String>> getReactiveStat() {
		return reactiveStat;
	}
	public void setReactiveStat(List<List<String>> reactiveStat) {
		this.reactiveStat = reactiveStat;
	}
	public List<List<String>> getOrderStat() {
		return orderStat;
	}
	public void setOrderStat(List<List<String>> orderStat) {
		this.orderStat = orderStat;
	}
	public List<List<String>> getSaleStat() {
		return saleStat;
	}
	public void setSaleStat(List<List<String>> saleStat) {
		this.saleStat = saleStat;
	}
	public List<List<String>> getUpdateStat() {
		return updateStat;
	}
	public void setUpdateStat(List<List<String>> updateStat) {
		this.updateStat = updateStat;
	}
	public List<List<String>> getBirthdayStat() {
		return birthdayStat;
	}
	public void setBirthdayStat(List<List<String>> birthdayStat) {
		this.birthdayStat = birthdayStat;
	}
}
