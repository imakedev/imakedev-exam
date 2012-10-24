package th.co.aoe.makedev.missconsult.domain;

import java.io.Serializable;


public class MissEptAttitudeDetectorReport  implements Serializable {
	private static final long serialVersionUID = 1L;
	private int mtrId;
	private String meadrLang;
	private int meadrOrder;
	private String meadrDetail;
	private String meadrKey;
	private String meadrTopic;
	public int getMtrId() {
		return mtrId;
	}
	public void setMtrId(int mtrId) {
		this.mtrId = mtrId;
	}
	public String getMeadrLang() {
		return meadrLang;
	}
	public void setMeadrLang(String meadrLang) {
		this.meadrLang = meadrLang;
	}
	public int getMeadrOrder() {
		return meadrOrder;
	}
	public void setMeadrOrder(int meadrOrder) {
		this.meadrOrder = meadrOrder;
	}
	public String getMeadrDetail() {
		return meadrDetail;
	}
	public void setMeadrDetail(String meadrDetail) {
		this.meadrDetail = meadrDetail;
	}
	public String getMeadrKey() {
		return meadrKey;
	}
	public void setMeadrKey(String meadrKey) {
		this.meadrKey = meadrKey;
	}
	public String getMeadrTopic() {
		return meadrTopic;
	}
	public void setMeadrTopic(String meadrTopic) {
		this.meadrTopic = meadrTopic;
	}
	
	
}
