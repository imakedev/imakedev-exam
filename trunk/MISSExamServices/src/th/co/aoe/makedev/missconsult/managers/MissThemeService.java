package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme;

public interface MissThemeService {
	public  List listMissTheme(MissTheme persistentInstance)throws DataAccessException  ;
	public MissTheme findMissThemeById(Long maId,Long mtId)throws DataAccessException ;
}
