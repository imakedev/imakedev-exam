package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface RoleSeriesMappingService {
	public Long saveRoleSeriesMapping(RoleSeriesMapping transientInstance) throws DataAccessException;
	public int updateRoleSeriesMapping(Long rcId,String[] msIds) throws DataAccessException ;
	public int deleteRoleSeriesMapping(RoleSeriesMapping persistentInstance) throws DataAccessException ;	
	public RoleSeriesMapping findRoleSeriesMappingById(Long mmId)throws DataAccessException  ;
	@SuppressWarnings("rawtypes")
	public  List searchRoleSeriesMapping(RoleSeriesMapping persistentInstance,	Pagging pagging)throws DataAccessException  ;
	public  List listRoleSeriesMappingByrcId(Long rcId) throws DataAccessException ;
}
