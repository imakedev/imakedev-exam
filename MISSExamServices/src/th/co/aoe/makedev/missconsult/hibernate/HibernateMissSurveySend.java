package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSery;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend;
import th.co.aoe.makedev.missconsult.managers.MissSurveySendService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissSurveySend  extends HibernateCommon implements MissSurveySendService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissSurveySend findMissSurveySendById(Long megId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissSurveySend missSurveySend = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missSurveySend from MissSurveySend missSurveySend where missSurveySend.megId=:megId");
		query.setParameter("megId", megId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missSurveySend=(MissSurveySend)obj;
		}
	  return missSurveySend;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissSurveySend(MissSurveySend transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
		try{
			Object obj = session.save(transientInstance);
		
			if(obj!=null){
				returnId =(Long) obj;
			}
		} finally {
				if (session != null) {
					session = null;
				} 
		}
		return returnId; 
	}
	
	

	private int getSize(Session session, MissSurveySend instance) throws Exception{
		try {
			 
			/*Long megId = instance.getMegId();
			String megName = instance.getMegName();
			 
			 
			StringBuffer sb =new StringBuffer(" select count(missSurveySend) from MissSurveySend missSurveySend ");
			
			boolean iscriteria = false;
			if(megId !=null && megId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSurveySend.megId="+megId+""):(" where missSurveySend.megId="+megId+""));
				  iscriteria = true;
			}
			if(megName !=null && megName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missSurveySend.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missSurveySend.megName) like '%"+megName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			Query query =session.createQuery(sb.toString());
				 return ((Long)query.uniqueResult()).intValue(); */
			return 0;
		} catch (HibernateException re) {
			logger.error("HibernateException",re);
			throw re;
		} catch (Exception e) {
			logger.error("Exception",e);
			throw e;
		}
	}
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 @Transactional(readOnly=true)
	 public List searchMissSurveySend(MissSurveySend instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
			/*	Long megId = instance.getMegId();
				String megName = instance.getMegName();
			
			
				StringBuffer sb =new StringBuffer(" select missSurveySend from MissSurveySend missSurveySend ");
				
				boolean iscriteria = false;
				if(megId !=null && megId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSurveySend.megId="+megId+""):(" where missSurveySend.megId="+megId+""));
					  iscriteria = true;
				}
				if(megName !=null && megName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missSurveySend.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missSurveySend.megName) like '%"+megName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missSurveySend."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				Query query =session.createQuery(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session, instance)); 
				 logger.debug(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
				 query.setFirstResult(pagging.getPageSize() * (pagging.getPageNo() - 1));
				 query.setMaxResults(pagging.getPageSize());
				 
				 List l = query.list();   
				 transList.add(l); 
			 	 transList.add(size); */
				return transList;
			} catch (Exception re) {
				//re.printStackTrace();
				logger.error("find by property name failed", re);
				 
			}
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissSurveySend(MissSurveySend transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissSurveySend(MissSurveySend persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	@Override	
	public List<List<String>> sendSurvey(MissSurveySend persistentInstance,Long maId,List<List<String>> userEmail)
			throws DataAccessException {
		 
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();

		int returnRecord=0;
		List<List<String>>  listUserEmailReturn = null ;
		Query query=session.createQuery(" select missSery from MissSery missSery where missSery.msId=:msId " +
				" "); 
		query.setParameter("msId", persistentInstance.getMissSery().getMsId());
		MissSery missSery = (MissSery)query.uniqueResult();
		Long msUnitCost =(missSery.getMsUnitCost()!=null && missSery.getMsUnitCost().intValue()!=0)?missSery.getMsUnitCost():0l;
		Long masmAvailable=0l;
		/*SELECT * FROM MISS_CONSULT_EXAM.MISS_CANDIDATE where mca_status=2 and ma_id=7 
				and ms_id=10*/
		query=session.createQuery(" select count(missCandidate) from MissCandidate missCandidate where missCandidate.mcaStatus=2 " +
				" and missCandidate.missSery.msId=:msId and missCandidate.missAccount.maId=:maId ");
		query.setParameter("maId", maId);
		query.setParameter("msId", persistentInstance.getMissSery().getMsId());
		java.lang.Long count=(java.lang.Long)query.uniqueResult();
		/*query=session.createQuery(" select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap where missAccountSeriesMap.id.maId=:maId " +
				" and missAccountSeriesMap.id.msId=:msId");
		query.setParameter("maId", maId);
		query.setParameter("msId", persistentInstance.getMissSery().getMsId());
		List<MissAccountSeriesMap> list=query.list();
		if(list!=null && list.size()>0){
			MissAccountSeriesMap missAccountSeriesMap = list.get(0);
			masmAvailable=(missAccountSeriesMap.getMasmAvailable()!=null && missAccountSeriesMap.getMasmAvailable().length()>0)?Long.valueOf(missAccountSeriesMap.getMasmAvailable()):0l;
		
		} 
		*/
		 	int userEmailSize=userEmail.size();
			int msUnitCostTotal=msUnitCost.intValue()*userEmailSize;
			//System.out.println(" available="+count);
			//System.out.println(" wanted="+msUnitCostTotal);
			//if(masmAvailable.intValue()>=msUnitCostTotal){
			if(count.intValue()>=msUnitCostTotal){
				query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaStatus=2 " +
						" and missCandidate.missSery.msId=:msId and missCandidate.missAccount.maId=:maId ");
				query.setParameter("maId", maId);
				query.setParameter("msId", persistentInstance.getMissSery().getMsId());
				 query.setFirstResult(0);
				 query.setMaxResults(userEmailSize);
				List missCandidateList= query.list();
				int index=0;
			    Long[] mcaIds=new Long[userEmailSize]; 
			   // List<List<String>>  listUserEmailReturn =new ArrayList<List<String>>(userEmailSize);
			    listUserEmailReturn = new ArrayList<List<String>>(userEmail.size());
				for (List<String> listUserEmail : userEmail) {
					/*System.out.println("name="+list.get(0));			
					System.out.println("email="+list.get(1));*/ 
					MissCandidate missCandidate=(MissCandidate)missCandidateList.get(index);
					
					 List<String> candidates = new ArrayList<String>(4);
					 candidates.add(missCandidate.getMcaUsername());
					 candidates.add(missCandidate.getMcaPassword());
					 candidates.add(listUserEmail.get(0));
					 candidates.add(listUserEmail.get(1));
					 listUserEmailReturn.add(candidates);
	    			  
					
					mcaIds[index++]=missCandidate.getMcaId();
					MissSurveySend missSurveySend =new MissSurveySend();
					missSurveySend.setMsEmail(listUserEmail.get(1));
					missSurveySend.setMissSery(persistentInstance.getMissSery());
					missSurveySend.setMissCandidate(missCandidate);
					missSurveySend.setMsName(listUserEmail.get(0));
					session.save(missSurveySend);
				} 
				String mcaIdStr="(";
				//String mcaIdStr="";
				if(mcaIds.length>0){
					for (int i = 0; i < mcaIds.length; i++) {
						if(i==userEmailSize-1){
							mcaIdStr=mcaIdStr+"'"+mcaIds[i]+"'";
						}else
							mcaIdStr=mcaIdStr+"'"+mcaIds[i]+"',";
					}
					mcaIdStr=mcaIdStr+")";
					//mcaIdStr=mcaIdStr+"";
				}
				// update seryMap 
				query=session.createQuery(" update MissCandidate missCandidate " +
						" set missCandidate.mcaStatus='3' " +
						" where missCandidate.mcaId in  " +mcaIdStr+
						"");
			 
				//query.setParameter(0, mcaIds); 
				
				/*query=session.createQuery(" update MissAccountSeriesMap missAccountSeriesMap " +
						" set missAccountSeriesMap.masmAvailable =:masmAvailable " +
						" where missAccountSeriesMap.id.maId=:maId " +
						" and missAccountSeriesMap.id.msId=:msId");
				query.setParameter("masmAvailable",(masmAvailable.intValue()-msUnitCostTotal)+"");
				query.setParameter("maId", maId);
				query.setParameter("msId", persistentInstance.getMissSery().getMsId());*/
				returnRecord=query.executeUpdate();
				returnRecord=1;
			}else{
				//returnRecord=-1;
			} 
		//System.out.println(persistentInstance.getMissSery().getMsId());
		//System.out.println(returnRecord);
		// 1= success , 0 not success
		return listUserEmailReturn;
	}
	 

}