package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;


public interface MissSurveySendService  {
		public Long saveMissSurveySend(MissSurveySend transientInstance) throws DataAccessException;
		public int updateMissSurveySend(MissSurveySend transientInstance) throws DataAccessException ;
		public int deleteMissSurveySend(MissSurveySend persistentInstance) throws DataAccessException ;
		public int sendSurvey(MissSurveySend persistentInstance,Long maId,List<List<String>> userEmail) throws DataAccessException ;	
		
		public MissSurveySend findMissSurveySendById(Long mssId)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissSurveySend(MissSurveySend persistentInstance,	Pagging pagging)throws DataAccessException  ;
}