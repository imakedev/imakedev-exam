package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface MissIndustryMasterService {
	@SuppressWarnings("rawtypes")
	public  List listMissIndustryMaster() throws DataAccessException ;
}
