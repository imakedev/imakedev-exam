package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMapPK;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;


public interface MissSeriesMapService {
		public Long saveMissSeriesMap(MissSeriesMap transientInstance) throws DataAccessException;
		public int updateMissSeriesMap(MissSeriesMap transientInstance) throws DataAccessException ;
		public int deleteMissSeriesMap(MissSeriesMap persistentInstance) throws DataAccessException ;	
		public MissSeriesMap findMissSeriesMapById(MissSeriesMapPK id)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissSeriesMap(MissSeriesMap persistentInstance,	Pagging pagging)throws DataAccessException  ;
}