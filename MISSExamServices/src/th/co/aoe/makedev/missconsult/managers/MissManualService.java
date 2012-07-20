package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissManual;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissManualService {
	public Long saveMissManual(MissManual transientInstance) throws DataAccessException;
	public int updateMissManual(MissManual transientInstance,String section) throws DataAccessException ;
	public int deleteMissManual(MissManual persistentInstance) throws DataAccessException ;	
	public MissManual findMissManualById(Long mmId)throws DataAccessException  ;
	@SuppressWarnings("rawtypes")
	public  List searchMissManual(MissManual persistentInstance,Long maId,	Pagging pagging)throws DataAccessException  ;
}
