package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMapPK;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;


public interface MissAccountSeriesMapService {
		public Long saveMissAccountSeriesMap(MissAccountSeriesMap transientInstance) throws DataAccessException;
		public int updateMissAccountSeriesMap(MissAccountSeriesMap transientInstance) throws DataAccessException ;
		public int deleteMissAccountSeriesMap(MissAccountSeriesMap persistentInstance) throws DataAccessException ;	
		public MissAccountSeriesMap findMissAccountSeriesMapById(MissAccountSeriesMapPK id)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissAccountSeriesMap(MissAccountSeriesMap persistentInstance,	Pagging pagging)throws DataAccessException  ;
}