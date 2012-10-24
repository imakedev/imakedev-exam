package th.co.aoe.makedev.missconsult.domain;

import java.io.Serializable;


public class MissEptMessageConfig  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String memcLang;
	private int memcOrder;
	private String memcDesc; 
	private String memcKey; 
	private String memcMessage;
	public String getMemcLang() {
		return memcLang;
	}
	public void setMemcLang(String memcLang) {
		this.memcLang = memcLang;
	}
	public int getMemcOrder() {
		return memcOrder;
	}
	public void setMemcOrder(int memcOrder) {
		this.memcOrder = memcOrder;
	}
	public String getMemcDesc() {
		return memcDesc;
	}
	public void setMemcDesc(String memcDesc) {
		this.memcDesc = memcDesc;
	}
	public String getMemcKey() {
		return memcKey;
	}
	public void setMemcKey(String memcKey) {
		this.memcKey = memcKey;
	}
	public String getMemcMessage() {
		return memcMessage;
	}
	public void setMemcMessage(String memcMessage) {
		this.memcMessage = memcMessage;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
