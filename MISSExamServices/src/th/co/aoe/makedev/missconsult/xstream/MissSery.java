package th.co.aoe.makedev.missconsult.xstream;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import th.co.aoe.makedev.missconsult.xstream.common.VServiceXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the MISS_SERIES database table.
 * 
 */
@XStreamAlias("MissSery")
public class MissSery extends VServiceXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long msId;

	private String msSeriesName;

	private Long msUnitCost;
	
	private Long msType;
	//ext
	private String[] meIds;
	private String testStr;
	private String msIds;

	private String manualFile;
	private String manualFileHotlink;
	private String templateFile;
	private String templateFileHotlink;
	private String evalFile;
	private String evalFileHotlink;
	private List<MissExam> missExams;
	
	//for map
	@XStreamAlias("masmExpire")
	private Timestamp masmExpire;

	private Long masmOrderUnit;

	private String masmStatus;

	private String masmAvailable;
	
	private String masmCandidateAvailable;

	private String groupStr;
	
    public String getGroupStr() {
		return groupStr;
	}

	public void setGroupStr(String groupStr) {
		this.groupStr = groupStr;
	}

	public Timestamp getMasmExpire() {
		return masmExpire;
	}

	public void setMasmExpire(Timestamp masmExpire) {
		this.masmExpire = masmExpire;
	}

	public Long getMasmOrderUnit() {
		return masmOrderUnit;
	}

	public void setMasmOrderUnit(Long masmOrderUnit) {
		this.masmOrderUnit = masmOrderUnit;
	}

	public String getMasmStatus() {
		return masmStatus;
	}

	public void setMasmStatus(String masmStatus) {
		this.masmStatus = masmStatus;
	}

	public String getMasmAvailable() {
		return masmAvailable;
	}

	public void setMasmAvailable(String masmAvailable) {
		this.masmAvailable = masmAvailable;
	}

	public MissSery() {
    }

	public Long getMsId() {
		return this.msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public String getMsSeriesName() {
		return this.msSeriesName;
	}

	public void setMsSeriesName(String msSeriesName) {
		this.msSeriesName = msSeriesName;
	}

	public Long getMsUnitCost() {
		return this.msUnitCost;
	}

	public void setMsUnitCost(Long msUnitCost) {
		this.msUnitCost = msUnitCost;
	}

	public String[] getMeIds() {
		return meIds;
	}

	public void setMeIds(String[] meIds) {
		this.meIds = meIds;
	}

	public String getTestStr() {
		return testStr;
	}

	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}

	public String getMsIds() {
		return msIds;
	}

	public void setMsIds(String msIds) {
		this.msIds = msIds;
	}

	public String getManualFile() {
		return manualFile;
	}

	public void setManualFile(String manualFile) {
		this.manualFile = manualFile;
	}

	public String getTemplateFile() {
		return templateFile;
	}

	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

	public String getManualFileHotlink() {
		return manualFileHotlink;
	}

	public void setManualFileHotlink(String manualFileHotlink) {
		this.manualFileHotlink = manualFileHotlink;
	}

	public String getTemplateFileHotlink() {
		return templateFileHotlink;
	}

	public void setTemplateFileHotlink(String templateFileHotlink) {
		this.templateFileHotlink = templateFileHotlink;
	}

	public List<MissExam> getMissExams() {
		return missExams;
	}

	public void setMissExams(List<MissExam> missExams) {
		this.missExams = missExams;
	}

	public String getEvalFile() {
		return evalFile;
	}

	public void setEvalFile(String evalFile) {
		this.evalFile = evalFile;
	}

	public String getEvalFileHotlink() {
		return evalFileHotlink;
	}

	public void setEvalFileHotlink(String evalFileHotlink) {
		this.evalFileHotlink = evalFileHotlink;
	}

	public String getMasmCandidateAvailable() {
		return masmCandidateAvailable;
	}

	public void setMasmCandidateAvailable(String masmCandidateAvailable) {
		this.masmCandidateAvailable = masmCandidateAvailable;
	}

	public Long getMsType() {
		return msType;
	}

	public void setMsType(Long msType) {
		this.msType = msType;
	}

	/*public Set<MissAccountSeriesMap> getMissAccountSeriesMaps() {
		return this.missAccountSeriesMaps;
	}

	public void setMissAccountSeriesMaps(Set<MissAccountSeriesMap> missAccountSeriesMaps) {
		this.missAccountSeriesMaps = missAccountSeriesMaps;
	}
	
	public Set<MissCandidate> getMissCandidates() {
		return this.missCandidates;
	}

	public void setMissCandidates(Set<MissCandidate> missCandidates) {
		this.missCandidates = missCandidates;
	}
	
	public Set<MissSurveySend> getMissSurveySends() {
		return this.missSurveySends;
	}

	public void setMissSurveySends(Set<MissSurveySend> missSurveySends) {
		this.missSurveySends = missSurveySends;
	}
	
	public Set<MissTest> getMissTests() {
		return this.missTests;
	}

	public void setMissTests(Set<MissTest> missTests) {
		this.missTests = missTests;
	}*/
	
}