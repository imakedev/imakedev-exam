package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface MissCareerMasterService {
	@SuppressWarnings("rawtypes")
	public  List listMissCareerMaster(Long mcmRef) throws DataAccessException ;
}
