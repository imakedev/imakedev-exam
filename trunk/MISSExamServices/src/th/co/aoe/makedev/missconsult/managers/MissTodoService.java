package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissTodoService {
	// MISS_EXAM_GROUP
		public Long saveMissTodo(MissTodo transientInstance) throws DataAccessException;
		public int updateMissTodo(MissTodo transientInstance) throws DataAccessException ;
		public int deleteMissTodo(MissTodo persistentInstance) throws DataAccessException ;	
		public MissTodo findMissTodoById(Long mtodoId)throws DataAccessException  ;
		public String findCandidateEmailFrom(MissTodo missTodo);
		@SuppressWarnings("rawtypes")
		public  List searchMissTodo(MissTodo persistentInstance,	Pagging pagging)throws DataAccessException  ;
}