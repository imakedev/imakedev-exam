package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;



public interface MissQuestionService {
		public Long saveMissQuestion(MissQuestion transientInstance) throws DataAccessException;
		public int updateMissQuestion(MissQuestion transientInstance) throws DataAccessException ;
		public int deleteMissQuestion(MissQuestion persistentInstance) throws DataAccessException ;	
		public  th.co.aoe.makedev.missconsult.xstream.MissQuestion findMissQuestionById(Long mqId)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissQuestion(MissQuestion persistentInstance,	Pagging pagging)throws DataAccessException  ;
		public List listMissQuestions(Long meId) throws DataAccessException;
		
		
}