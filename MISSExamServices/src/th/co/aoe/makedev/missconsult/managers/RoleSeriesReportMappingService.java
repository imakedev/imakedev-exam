package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface RoleSeriesReportMappingService { 
	public int updateRoleSeriesReportMapping(Long rcId,Long msId,String[] msIds) throws DataAccessException ;  
	@SuppressWarnings("rawtypes")
	public  List listRoleSeriesReportMappingByrcId(Long rcId,Long msId) throws DataAccessException ;
}
