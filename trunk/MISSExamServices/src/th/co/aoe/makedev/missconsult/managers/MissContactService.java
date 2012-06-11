package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissContact;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissContactService {
	public Long saveMissContact(MissContact transientInstance) throws DataAccessException;
	public int updateMissContact(MissContact transientInstance,String section) throws DataAccessException ;
	public int updateMissContactPhoto(MissContact transientInstance,String section) throws DataAccessException ;
	public int deleteMissContact(MissContact persistentInstance) throws DataAccessException ;	
	public MissContact findMissContactById(Long mcontactId)throws DataAccessException  ;
	@SuppressWarnings("rawtypes")
	public  List searchMissContact(MissContact persistentInstance,	Pagging pagging)throws DataAccessException  ;
	@SuppressWarnings("rawtypes")
	public  List listContacts(Long long1,String mcontactType)throws DataAccessException  ;
}
