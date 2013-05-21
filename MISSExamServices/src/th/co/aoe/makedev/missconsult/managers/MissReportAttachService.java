package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public interface MissReportAttachService {
	public Long saveMissReportAttach(MissReportAttach transientInstance) throws DataAccessException;
	public int updateMissReportAttach(MissReportAttach transientInstance) throws DataAccessException ;
	public int deleteMissReportAttach(MissReportAttach persistentInstance) throws DataAccessException ;
	 
	public MissReportAttach findMissReportAttachById( Long msId,Long msOrder,String mraLang,String hotlink)throws DataAccessException  ;
	@SuppressWarnings("rawtypes")
	public  List searchMissReportAttach(MissReportAttach persistentInstance,	Pagging pagging)throws DataAccessException  ;
	@SuppressWarnings("rawtypes")
	public  List getTemplateMissReportAttach(Long msId,int numberReport)throws DataAccessException  ;
	public  int deleteTemplateMissReportAttach(Long msId,Long msOrder)throws DataAccessException  ;
	public int updateReportNameMissReportAttach(Long msId, Long msOrder,String mraLang,String reportName) ;
	@SuppressWarnings("rawtypes")
	public List getTemplateMissReportAttachForRole(Long msId,int numberReport)throws DataAccessException  ;
}
