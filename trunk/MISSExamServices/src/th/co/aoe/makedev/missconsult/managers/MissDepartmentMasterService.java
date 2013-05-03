package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface MissDepartmentMasterService {
	@SuppressWarnings("rawtypes")
	public  List listMissDepartmentMaster() throws DataAccessException ;
}
