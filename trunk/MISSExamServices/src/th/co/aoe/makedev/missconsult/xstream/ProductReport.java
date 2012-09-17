package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("ProductReport")
public class ProductReport extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private String year;
	private String mode;
	
	@XStreamImplicit(itemFieldName="seryUses")
	private List<String> seryUses;
	
	@XStreamImplicit(itemFieldName="seryOrders")
	private List<String> seryOrders;
	
	@XStreamImplicit(itemFieldName="seryProblems")
	private List<String> seryProblems;
	
	@XStreamImplicit(itemFieldName="seryMaxUses")
	private List<String> seryMaxUses;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<String> getSeryUses() {
		return seryUses;
	}

	public void setSeryUses(List<String> seryUses) {
		this.seryUses = seryUses;
	}

	public List<String> getSeryOrders() {
		return seryOrders;
	}

	public void setSeryOrders(List<String> seryOrders) {
		this.seryOrders = seryOrders;
	}

	public List<String> getSeryProblems() {
		return seryProblems;
	}

	public void setSeryProblems(List<String> seryProblems) {
		this.seryProblems = seryProblems;
	}

	public List<String> getSeryMaxUses() {
		return seryMaxUses;
	}

	public void setSeryMaxUses(List<String> seryMaxUses) {
		this.seryMaxUses = seryMaxUses;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
}
