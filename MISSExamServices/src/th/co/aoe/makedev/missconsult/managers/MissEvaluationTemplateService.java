package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissEvaluationTemplate;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissEvaluationTemplateService {
		public Long saveMissEvaluationTemplate(MissEvaluationTemplate transientInstance) throws DataAccessException;
		public int updateMissEvaluationTemplate(MissEvaluationTemplate transientInstance) throws DataAccessException ;
		public int deleteMissEvaluationTemplate(MissEvaluationTemplate persistentInstance) throws DataAccessException ;	
		public MissEvaluationTemplate findMissEvaluationTemplateById(Long metId)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissEvaluationTemplate(MissEvaluationTemplate persistentInstance,	Pagging pagging)throws DataAccessException  ;
}