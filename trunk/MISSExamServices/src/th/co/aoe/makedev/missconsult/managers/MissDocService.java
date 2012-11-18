package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissDoc;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissDocService {
	public Long saveMissDoc(MissDoc transientInstance) throws DataAccessException;
	public int updateMissDoc(MissDoc transientInstance) throws DataAccessException ;
	public int deleteMissDoc(MissDoc persistentInstance) throws DataAccessException ;	
	public MissDoc findMissDocById(Long mdId)throws DataAccessException  ;
	@SuppressWarnings("rawtypes")
	public  List searchMissDoc(MissDoc persistentInstance,	Pagging pagging)throws DataAccessException  ;
}
