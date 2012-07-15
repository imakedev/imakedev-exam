package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;


 
public interface MissAccountService  { 
		public Long saveMissAccount(MissAccount transientInstance) throws DataAccessException;
		public int updateMissAccount(MissAccount transientInstance,String section) throws DataAccessException ;
		public MissAccount refill(Long maId,Long refill) throws DataAccessException ;		
		public int updateMissAccountLogo(MissAccount transientInstance,String section) throws DataAccessException ;
		public int deleteMissAccount(MissAccount persistentInstance) throws DataAccessException ;	
		public MissAccount findMissAccountById(Long maId)throws DataAccessException  ;
		public List<th.co.aoe.makedev.missconsult.xstream.MissSery> listMissAccountSeriesMapByMaId(Long maId)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissAccount(MissAccount persistentInstance,	Pagging pagging)throws DataAccessException  ;
}