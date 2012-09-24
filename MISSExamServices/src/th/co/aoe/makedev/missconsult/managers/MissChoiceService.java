package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissChoiceService {
		public Long saveMissChoice(MissChoice transientInstance) throws DataAccessException;
		public int updateMissChoice(MissChoice transientInstance) throws DataAccessException ;
		public int deleteMissChoice(Long mqId,String lang) throws DataAccessException ;	
		public MissChoice findMissChoiceById(Long mcId)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissChoice(MissChoice persistentInstance,	Pagging pagging)throws DataAccessException  ;
}