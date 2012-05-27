package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissTemplateService {
		public Long saveMissTemplate(MissTemplate transientInstance) throws DataAccessException;
		public int updateMissTemplate(MissTemplate transientInstance) throws DataAccessException ;
		public int deleteMissTemplate(MissTemplate persistentInstance) throws DataAccessException ;	
		public MissTemplate findMissTemplateById(Long mtId)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissTemplate(MissTemplate persistentInstance,	Pagging pagging)throws DataAccessException  ;
}