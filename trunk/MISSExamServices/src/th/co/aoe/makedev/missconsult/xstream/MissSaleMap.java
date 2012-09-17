package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_SALE_MAP database table.
 * 
 */
@XStreamAlias("missSaleMap")
public class MissSaleMap extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1;


	private Long mcontactId;

	private Long maId;

    public MissSaleMap() {
    }

	public Long getMcontactId() {
		return mcontactId;
	}

	public void setMcontactId(Long mcontactId) {
		this.mcontactId = mcontactId;
	}

	public Long getMaId() {
		return maId;
	}

	public void setMaId(Long maId) {
		this.maId = maId;
	}

	
}