package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

/*import th.co.vlink.xstream.BpsGroup;
import th.co.vlink.xstream.BpsTerm;*/

public class MissExamForm extends CommonForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mode;
	private String command;
	private String bpgGroupName;
	private String bptTerm;
	/*private BpsGroup bpsGroup;
	private BpsTerm bpsTerm;*/
	private String bpgId;
	private String searchBy;
	private Long bptId;
	 
	private String indexChar;
	private String orderColumn;
	private String orderBy;
	//private String searchMode;//  
	
	private Integer version;
	public MissExamForm(){
		/*bpsGroup =new BpsGroup();
		bpsTerm =new BpsTerm();*/
	}
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	/*public BpsGroup getBpsGroup() {
		return bpsGroup;
	}

	public void setBpsGroup(BpsGroup bpsGroup) {
		this.bpsGroup = bpsGroup;
	}*/
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	/*public BpsTerm getBpsTerm() {
		return bpsTerm;
	}
	public void setBpsTerm(BpsTerm bpsTerm) {
		this.bpsTerm = bpsTerm;
	}*/
	public String getBpgId() {
		return bpgId;
	}
	public void setBpgId(String bpgId) {
		this.bpgId = bpgId;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	public String getBpgGroupName() {
		return bpgGroupName;
	}
	public void setBpgGroupName(String bpgGroupName) {
		this.bpgGroupName = bpgGroupName;
	}
	public String getBptTerm() {
		return bptTerm;
	}
	public void setBptTerm(String bptTerm) {
		this.bptTerm = bptTerm;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getIndexChar() {
		return indexChar;
	}
	public void setIndexChar(String indexChar) {
		this.indexChar = indexChar;
	}
	public String getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Long getBptId() {
		return bptId;
	}
	public void setBptId(Long bptId) {
		this.bptId = bptId;
	}
	

}
