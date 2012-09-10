package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUse;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissSystemUseService {
	public Long saveMissSystemUse(MissSystemUse transientInstance) throws DataAccessException;
	@SuppressWarnings("rawtypes")
	public  List searchMissSystemUse(MissSystemUse persistentInstance,	Pagging pagging)throws DataAccessException  ;
}
