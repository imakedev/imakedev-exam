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
		@SuppressWarnings("rawtypes")
		public  List searchMissTestResult(MissTestResult persistentInstance,	Pagging pagging)throws DataAccessException  ;
		public int processMissTestResult()throws DataAccessException  ;
}