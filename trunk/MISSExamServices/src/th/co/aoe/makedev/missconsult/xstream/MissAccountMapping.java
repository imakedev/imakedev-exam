package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_ACCOUNT_MAPPING database table.
 * 
 */
@XStreamAlias("MissAccountMapping")
public class MissAccountMapping  extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long magId;

	private Long maId;

	//bi-directional many-to-one association to MissAccountGroup
	@XStreamAlias("missAccountGroup")
	private MissAccountGroup missAccountGroup;

    public MissAccountMapping() {
    }

	public Long getMagId() {
		return magId;
	}

	public void setMagId(Long magId) {
		this.magId = magId;
	}

	public Long getMaId() {
		return maId;
	}

	public void setMaId(Long maId) {
		this.maId = maId;
	}

	public MissAccountGroup getMissAccountGroup() {
		return this.missAccountGroup;
	}

	public void setMissAccountGroup(MissAccountGroup missAccountGroup) {
		this.missAccountGroup = missAccountGroup;
	}
	
}