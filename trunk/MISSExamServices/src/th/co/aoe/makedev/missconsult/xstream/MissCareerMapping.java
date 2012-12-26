package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * The persistent class for the MISS_CANDIDATE database table.
 * 
 */
@XStreamAlias("MissCareerMapping")
public class MissCareerMapping  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long maId;
	private Long mcmId;
	public Long getMaId() {
		return maId;
	}
	public void setMaId(Long maId) {
		this.maId = maId;
	}
	public Long getMcmId() {
		return mcmId;
	}
	public void setMcmId(Long mcmId) {
		this.mcmId = mcmId;
	}
	

}
