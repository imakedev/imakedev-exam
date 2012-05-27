package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;


public interface MissCandidateService { 
		public Long saveMissCandidate(MissCandidate transientInstance) throws DataAccessException;
		public int updateMissCandidate(MissCandidate transientInstance,String section) throws DataAccessException ;
		public int deleteMissCandidate(MissCandidate persistentInstance) throws DataAccessException ;	
		public MissCandidate findMissCandidateById(Long mcaId)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissCandidate(MissCandidate persistentInstance,	Pagging pagging)throws DataAccessException  ;
}