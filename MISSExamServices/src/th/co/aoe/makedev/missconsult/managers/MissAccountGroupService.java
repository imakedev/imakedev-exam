package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountGroup;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissAccountGroupService {
	public Long saveMissAccountGroup(MissAccountGroup transientInstance
			) throws DataAccessException;
	@SuppressWarnings("rawtypes")
	public  List searchMissAccountGroup(MissAccountGroup persistentInstance,	Pagging pagging)throws DataAccessException  ;
}
