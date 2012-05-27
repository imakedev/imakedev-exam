package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_SURVEY_SEND database table.
 * 
 */
@XStreamAlias("MissSurveySend")
public class MissSurveySend extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mssId;

	private String msEmail;
	
	@XStreamAlias("maExpire")
	private MissSery missSery;

    public MissSurveySend() {
    }

	public Long getMssId() {
		return this.mssId;
	}

	public void setMssId(Long mssId) {
		this.mssId = mssId;
	}

	public String getMsEmail() {
		return this.msEmail;
	}

	public void setMsEmail(String msEmail) {
		this.msEmail = msEmail;
	}

	public MissSery getMissSery() {
		return this.missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}
	
}