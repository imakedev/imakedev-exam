package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_MANUAL database table.
 * 
 */
@XStreamAlias("MissManual")
public class MissManual extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mmId;

	private String mmFileName;

	private String mmHotlink;

	private String mmPath;

	@XStreamAlias("missSery")
	private MissSery missSery;

	//ext 
		private String section; 
		private String mmIds;
		private List<MissSery> missSeries; 
	//private Long mc
    public MissManual() {
    }

	public Long getMmId() {
		return this.mmId;
	}

	public void setMmId(Long mmId) {
		this.mmId = mmId;
	}


	public String getMmFileName() {
		return mmFileName;
	}

	public void setMmFileName(String mmFileName) {
		this.mmFileName = mmFileName;
	}

	public String getMmHotlink() {
		return this.mmHotlink;
	}

	public void setMmHotlink(String mmHotlink) {
		this.mmHotlink = mmHotlink;
	}

	public String getMmPath() {
		return this.mmPath;
	}

	public void setMmPath(String mmPath) {
		this.mmPath = mmPath;
	}

	public MissSery getMissSery() {
		return this.missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getMmIds() {
		return mmIds;
	}

	public void setMmIds(String mmIds) {
		this.mmIds = mmIds;
	}

	public List<MissSery> getMissSeries() {
		return missSeries;
	}

	public void setMissSeries(List<MissSery> missSeries) {
		this.missSeries = missSeries;
	}


	
}