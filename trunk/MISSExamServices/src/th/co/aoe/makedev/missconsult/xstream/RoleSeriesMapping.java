package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("RoleSeriesMapping")
public class RoleSeriesMapping extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long rcId; 

	private Long msId;
	
	private String[] msIds;
	
	private Long maId;


	public String getMsName() {
		return msName;
	}

	public void setMsName(String msName) {
		this.msName = msName;
	}

	private String msName;
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

	public String[] getMsIds() {
		return msIds;
	}

	public void setMsIds(String[] msIds) {
		this.msIds = msIds;
	}

	public Long getMaId() {
		return maId;
	}

	public void setMaId(Long maId) {
		this.maId = maId;
	}

}
