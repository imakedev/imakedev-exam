package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissExamTypeService {
		public Long saveMissExamType(MissExamType transientInstance) throws DataAccessException;
		public int updateMissExamType(MissExamType transientInstance) throws DataAccessException ;
		public int deleteMissExamType(MissExamType persistentInstance) throws DataAccessException ;	
		public MissExamType findMissExamTypeById(Long metId)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissExamType(MissExamType persistentInstance,	Pagging pagging)throws DataAccessException  ;
}