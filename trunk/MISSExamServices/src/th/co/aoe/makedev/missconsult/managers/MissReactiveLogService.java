package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissReactiveLogService {
	public Long saveMissReactiveLog(MissReactiveLog transientInstance) throws DataAccessException;
	@SuppressWarnings("rawtypes")
	public  List searchMissReactiveLog(MissReactiveLog persistentInstance,	Pagging pagging)throws DataAccessException  ;
}
