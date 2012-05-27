package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;


public interface MissExamGroupService {
	public Long saveMissExamGroup(MissExamGroup transientInstance) throws DataAccessException;
	public int updateMissExamGroup(MissExamGroup transientInstance) throws DataAccessException ;
	public int deleteMissExamGroup(MissExamGroup persistentInstance) throws DataAccessException ;	
	public MissExamGroup findMissExamGroupById(Long megId)throws DataAccessException  ;
	@SuppressWarnings("rawtypes")
	public  List searchMissExamGroup(MissExamGroup persistentInstance,	Pagging pagging)throws DataAccessException  ;
}
