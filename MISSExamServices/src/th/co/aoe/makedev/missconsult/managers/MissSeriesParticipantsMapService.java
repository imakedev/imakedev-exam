/**
 * 
 */
package th.co.aoe.makedev.missconsult.managers;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * @author OS -> root
 * @author -> IMake
 * @project -> MISSExamServices
 * @time -> May 27, 2013 10:53:02 AM  
 */
public interface MissSeriesParticipantsMapService {
/*	public Long saveMissSeriesParticipantsMap(MissReportAttach transientInstance) throws DataAccessException;
	public int updateMissReportAttach(MissReportAttach transientInstance) throws DataAccessException ;
	public int deleteMissReportAttach(MissReportAttach persistentInstance) throws DataAccessException ;
	 
	public MissReportAttach findMissReportAttachById( Long msId,Long msOrder,String mraLang,String hotlink)throws DataAccessException  ;*/
	/*@SuppressWarnings("rawtypes")
	public  List searchMissReportAttach(MissSeriesParticipantsMap persistentInstance,	Pagging pagging)throws DataAccessException  ;*/
	 
	@SuppressWarnings("rawtypes")
	public  List getMissSeriesParticipantsMap(Long msId,int numberParticipant)throws DataAccessException  ;
	@SuppressWarnings("rawtypes")
	public List listMissSeriesParticipantsMap(Long msId)
			throws DataAccessException;
	public  int deleteMissSeriesParticipantsMap(Long msId,Long mspmOrder)throws DataAccessException  ;
	public int updateMissSeriesParticipantsMap(Long msId, Integer[] mspmOrder,Integer[] mspmGroupAmount,String[] mspmGroupName) ;
	/*@SuppressWarnings("rawtypes")
	public List getTemplateMissSeriesParticipantsMapForRole(Long msId,int numberReport)throws DataAccessException  ;*/
}
