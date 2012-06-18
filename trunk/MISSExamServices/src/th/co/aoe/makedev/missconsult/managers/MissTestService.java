package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissTest;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissTestService  { 
		public Long saveMissTest(MissTest transientInstance) throws DataAccessException;
		public Long saveOrUpdateMissTest(String userid,MissTest transientInstance) throws DataAccessException;
		public int updateMissTest(MissTest transientInstance) throws DataAccessException ;
		public int deleteMissTest(MissTest persistentInstance) throws DataAccessException ;	
		public MissTest findMissTestById(Long mtestId)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissTest(MissTest persistentInstance,	Pagging pagging)throws DataAccessException  ;
		public  List findMissTestAnswer(String userid,MissTest persistentInstance)throws DataAccessException  ;
		
}