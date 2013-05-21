package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the role_series_report_mapping database table.
 * 
 */
@XStreamAlias("RoleSeriesReportMapping")
public class RoleSeriesReportMapping  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

 
	private Long rcId; 
	private Long msId; 
	private Long msOrder;
	private String[] msOrders;
	public Long getRcId() {
		return rcId;
	}
	public void setRcId(Long rcId) {
		this.rcId = rcId;
	}
	public Long getMsId() {
		return msId;
	}
	public void setMsId(Long msId) {
		this.msId = msId;
	}
	public Long getMsOrder() {
		return msOrder;
	}
	public void setMsOrder(Long msOrder) {
		this.msOrder = msOrder;
	}
	public String[] getMsOrders() {
		return msOrders;
	}
	public void setMsOrders(String[] msOrders) {
		this.msOrders = msOrders;
	}

}