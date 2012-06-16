package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissSeriesAttachService {
	public Long saveMissSeriesAttach(MissSeriesAttach transientInstance) throws DataAccessException;
	public int updateMissSeriesAttach(MissSeriesAttach transientInstance) throws DataAccessException ;
	public int deleteMissSeriesAttach(MissSeriesAttach persistentInstance) throws DataAccessException ;	
	@SuppressWarnings("rawtypes")
	public  List searchMissSeriesAttach(MissSeriesAttach persistentInstance,	Pagging pagging)throws DataAccessException  ;
}
