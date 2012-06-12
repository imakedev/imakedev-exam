package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissAttachService {
	public Long saveMissAttach(MissAttach transientInstance) throws DataAccessException;
	public int updateMissAttach(MissAttach transientInstance) throws DataAccessException ;
	public int deleteMissAttach(MissAttach persistentInstance) throws DataAccessException ;	
	public MissAttach findMissAttachById(String matModule,Long matRef,String hotlink)throws DataAccessException  ;
	@SuppressWarnings("rawtypes")
	public  List searchMissAttach(MissAttach persistentInstance,	Pagging pagging)throws DataAccessException  ;
}
