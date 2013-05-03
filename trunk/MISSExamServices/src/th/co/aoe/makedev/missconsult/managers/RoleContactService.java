package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.RoleContact;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface RoleContactService {
	public Long saveRoleContact(RoleContact transientInstance) throws DataAccessException;
	public int updateRoleContact(RoleContact transientInstance) throws DataAccessException ;
	public int deleteRoleContact(RoleContact persistentInstance) throws DataAccessException ;	
	public RoleContact findRoleContactById(Long mmId)throws DataAccessException  ;
	@SuppressWarnings("rawtypes")
	public  List searchRoleContact(RoleContact persistentInstance,	Pagging pagging)throws DataAccessException  ;
	@SuppressWarnings("rawtypes")
	public  List listRoleContactBymaId(Long maId) throws DataAccessException ;
}
