package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


/**
 * The persistent class for the MISS_SURVEY_SEND database table.
 * 
 */
@XStreamAlias("MissSurveySend")
public class MissSurveySend extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mssId;

	private String msEmail;
	
	@XStreamAlias("missSery")
	private MissSery missSery;
	
	// for send 
	private Long maId;
	
	@XStreamImplicit(itemFieldName="userEmail")
	private List<List<String>> userEmail;
	
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

	public Long getMaId() {
		return maId;
	}

	public void setMaId(Long maId) {
		this.maId = maId;
	}

	public List<List<String>> getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(List<List<String>> userEmail) {
		this.userEmail = userEmail;
	}

	public MissSery getMissSery() {
		return this.missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}
	
}