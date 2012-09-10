package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountMapping;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissAccountMappingService {
	public Long saveMissAccountMapping(MissAccountMapping transientInstance) throws DataAccessException;
	@SuppressWarnings("rawtypes")
	public  List searchMissAccountMapping(MissAccountMapping persistentInstance,	Pagging pagging)throws DataAccessException  ;
}
