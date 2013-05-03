package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissExam;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;


public interface MissExamService {
		public Long saveMissExam(MissExam transientInstance) throws DataAccessException;
		public int updateMissExam(MissExam transientInstance,String section) throws DataAccessException ;
		public int createEmptyMissExam(MissExam transientInstance,int questionCountEmpty,int choiceCountEmpty,Long meTimeLimit) throws DataAccessException ;
		public int copyMissExam(MissExam transientInstance) throws DataAccessException ;
		
		public int deleteMissExam(MissExam persistentInstance) throws DataAccessException ;	
		public MissExam findMissExamById(Long meId)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissExam(MissExam persistentInstance,	Pagging pagging)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List listMissExam()throws DataAccessException  ;
		
	
}
