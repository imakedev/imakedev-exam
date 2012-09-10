package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissSeryOrderService {
	public Long saveMissSeryOrder(MissSeryOrder transientInstance) throws DataAccessException;
	@SuppressWarnings("rawtypes")
	public  List searchMissSeryOrder(MissSeryOrder persistentInstance,	Pagging pagging)throws DataAccessException  ;
}
