package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissSeryUseService {
	public Long saveMissSeryUse(MissSeryUse transientInstance) throws DataAccessException;
	@SuppressWarnings("rawtypes")
	public  List searchMissSeryUse(MissSeryUse persistentInstance,	Pagging pagging)throws DataAccessException  ;
}
