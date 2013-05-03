package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface MissPositionMasterService {
	@SuppressWarnings("rawtypes")
	public  List listMissPositionMaster() throws DataAccessException ;
}
