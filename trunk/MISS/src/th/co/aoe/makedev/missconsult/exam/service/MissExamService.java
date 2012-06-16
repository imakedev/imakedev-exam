package th.co.aoe.makedev.missconsult.exam.service;

import java.util.List;

 
import th.co.aoe.makedev.missconsult.xstream.MissExamGroup;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;


/*import th.co.vlink.xstream.BpsAttachFile;
import th.co.vlink.xstream.BpsGroup;
import th.co.vlink.xstream.BpsTerm;
import th.co.vlink.xstream.BpsTermLog;
import th.co.vlink.xstream.BpsTermVersion;
import th.co.vlink.xstream.common.VResultMessage;*/

public interface MissExamService {
	// MISS_EXAM_GROUP
	public Long saveMissExamGroup(MissExamGroup missExamGroup) ;
	public int updateMissExamGroup(MissExamGroup missExamGroup)  ; 
	public int deleteMissExamGroup(MissExamGroup missExamGroup)  ;	
	public MissExamGroup findMissExamGroupById(Long megId)  ; 
	public  List searchMissExamGroup(MissExamGroup missExamGroup,	Pagging pagging)  ;
	
/*	public int saveBpsGroup(BpsGroup bpsGroup);
	public int updateBpsGroup(BpsGroup bpsGroup);
	public int deleteBpsGroup(String key);	
	public BpsGroup findBpsGroupById(String key);
	public VResultMessage searchBpsGroup(BpsGroup bpsGroup);
	public int checkDuplicateGroup(String groupName);*/

	/*// BPS_TERM
	public int saveBpsTerm(BpsTerm bpsTerm);
	public int updateBpsTerm(BpsTerm bpsTerm);
	public int updateBpsTermVersion(BpsTerm bpsTerm);
	public int deleteBpsTerm(String key);
	public BpsTerm findBpsTermById(String bpgId);
	public VResultMessage searchBpsTerm(BpsTerm bpsTerm);

	
	// BPS_TERM_LOG
	public int deleteBpsTermLog(String key);
	public BpsTermLog findBpsTermLogById(String bpgId);
	public VResultMessage searchBpsTermLog(BpsTermLog bpsTermLog);
	public int updateBpsTermLogStatus(Long bptlId,String status);
	
	
	// BPS_TERM_VERSION
	public VResultMessage searchBpsTermVersion(BpsTermVersion bpsTermVersion) ;
	public BpsTermVersion findBpsTermVersionById(String bpgId) ;
	// BPS_FILE
	public int saveBpsAttachFile(BpsAttachFile bpsAttachFile);
	public int deleteBpsAttachFile(String key) ;
	public BpsAttachFile findBpsAttachFileById(String bpgId);
	public VResultMessage searchBpsAttachFile(BpsAttachFile bpsAttachFile) ;
	public int updateBpsAttachFile(Long bptlId,BpsTerm bpsTerm);
	public int sendToMail(String form, String to,String subjectMail,String term,String Category,String source,
			String definition,String detail,String additional);
	public List getAllUsers(String uid);*/
}
