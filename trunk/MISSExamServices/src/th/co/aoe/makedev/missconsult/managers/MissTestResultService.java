package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissTestResultService {
		public Long saveMissTestResult(MissTestResult transientInstance) throws DataAccessException;
		public int updateMissTestResult(MissTestResult transientInstance) throws DataAccessException ;
		public int deleteMissTestResult(MissTestResult persistentInstance) throws DataAccessException ;	
		public MissTestResult findMissTestResultById(Long mtrId)throws DataAccessException  ;
		public List findMissTestShow( Long mcaId,Long msId,Long meId)throws DataAccessException;
		@SuppressWarnings("rawtypes")
		public  List searchMissTestResult(MissTestResult persistentInstance,	Pagging pagging)throws DataAccessException  ;
		public int processMissTestResult(MissTestResult persistentInstance,String userid,String rootPath)throws DataAccessException  ; 
		public Long saveOrUpdateMissTestResult(String userid,MissTestResult transientInstance) throws DataAccessException;
		public int startMissTestResult(String userid,MissTestResult transientInstance) throws DataAccessException;
		public int checkMissTestResult(String userid,MissTestResult transientInstance) throws DataAccessException;
		public int updateStatus(Long mtrId,String column,String value) throws DataAccessException;
		
}