package th.co.aoe.makedev.missconsult.managers;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptCareer;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptMessageConfig;

public interface ImportTestService {
	public Long saveMissEptCareer(MissEptCareer missEptCareer
			) throws DataAccessException;
	public Long saveMissEptMessageConfig(MissEptMessageConfig missEptMessageConfig
			);
}
