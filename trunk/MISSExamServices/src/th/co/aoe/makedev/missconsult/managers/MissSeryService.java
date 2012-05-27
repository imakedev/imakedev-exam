package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissSery;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;


public interface MissSeryService {
		public Long saveMissSery(MissSery transientInstance,String[]  meIds) throws DataAccessException;
		public int updateMissSery(MissSery transientInstance,String[]  meIds) throws DataAccessException ;
		public int deleteMissSery(MissSery persistentInstance) throws DataAccessException ;	
		public MissSery findMissSeryById(Long msId)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List searchMissSery(MissSery persistentInstance,	Pagging pagging,String[] meIds)throws DataAccessException  ;
		@SuppressWarnings("rawtypes")
		public  List listMissSery()throws DataAccessException  ;
}