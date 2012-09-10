package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissSeryProblemService {
	public Long saveMissSeryProblem(MissSeryProblem transientInstance) throws DataAccessException;
	@SuppressWarnings("rawtypes")
	public  List searchMissSeryProblem(MissSeryProblem persistentInstance,	Pagging pagging)throws DataAccessException  ;
}
