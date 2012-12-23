package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissExam;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSery;
import th.co.aoe.makedev.missconsult.managers.MissSeryService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissSery  extends HibernateCommon implements MissSeryService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissSery findMissSeryById(Long msId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissSery missSery = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missSery from MissSery missSery where missSery.msId=:msId");
		query.setParameter("msId", msId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missSery=(MissSery)obj;
		}
	  return missSery;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissSery(MissSery transientInstance,String[] meIds)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
	//	Long msId=	transientInstance.getMsId(); 
	//	Query query=session.createQuery("delete MissSeriesMap missSeriesMap where missSeriesMap.id.msId "+msId.intValue());
	//	int result = query.executeUpdate()
				
		Long returnId  = null;
		try{
			Object obj = session.save(transientInstance);
		
			if(obj!=null){
				returnId =(Long) obj;
				if(meIds!=null && meIds.length>0){
					int meId_size=meIds.length;
					for (int i = 0; i < meId_size; i++) {
						th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap missSeriesMap =
								 new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap();
						th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMapPK pk =
								new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMapPK();
						pk.setMsId(returnId);
						pk.setMeId(Long.parseLong(meIds[i]));
						missSeriesMap.setMsmOrder(new Long(i+1));
						missSeriesMap.setId(pk);
						session.save(missSeriesMap);
					}
				}
			}
		} finally {
				if (session != null) {
					session = null;
				} 
		} 
		return returnId; 
	}
	
	

	private int getSize(Session session, MissSery instance,String[] meIds) throws Exception{
		try {
			 
			Long msUnitCost = instance.getMsUnitCost();
			String msSeriesName = instance.getMsSeriesName();
		
		
			//StringBuffer sb =new StringBuffer(" select count(missSery) from MissSery missSery ");
			/*StringBuffer sb =new StringBuffer(" select count(x) from ( select count(missSery) from MissSeriesMap missMap ,  MissSery missSery " +
					" where missMap.id.msId=missSery.msId ");*/
			/*StringBuffer sb =new StringBuffer(" select count(*) from ( select   missSery.*   from MISS_SERIES missSery  left  join   MISS_SERIES_MAP missMap on missSery.MS_ID = missMap.MS_ID  " +
					"  ");*/
			StringBuffer sb =new StringBuffer(" select count(*) from ( select xx.* from ( select missSery.*,missMap.ME_ID  from MISS_SERIES missSery " +
					"  left outer  join   MISS_SERIES_MAP missMap on missSery.MS_ID = missMap.MS_ID " +
					" ) as xx  ");
			// select count(*) from (select missSery.* from MISS_SERIES missSery   left  join   MISS_SERIES_MAP missMap on missSery.MS_ID = missMap.MS_ID  group by missSery.MS_SERIES_NAME ) as x


			boolean iscriteria = false;
			if(meIds!=null && meIds.length>0){
				String meIdStr="(";
				int meId_size=meIds.length;
				for (int i = 0; i < meId_size; i++) {
					if(i!=meId_size-1){
						meIdStr=meIdStr+meIds[i]+",";
					}else{
						meIdStr=meIdStr+meIds[i]+")";
					}
				}
			//	logger.debug(" meIds = "+meIdStr);
				 sb.append(iscriteria?(" and xx.ME_ID in "+meIdStr+""):(" where xx.ME_ID in "+meIdStr+""));
				  iscriteria = true;
			}
			if(msUnitCost !=null && msUnitCost > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and xx.MS_UNIT_COST="+msUnitCost+""):(" where xx.MS_UNIT_COST="+msUnitCost+""));
				  iscriteria = true;
			}
			if(msSeriesName !=null && msSeriesName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(xx.MS_SERIES_NAME) like '%"+msSeriesName.trim().toLowerCase()+"%'"):(" where lcase(xx.MS_SERIES_NAME) like '%"+msSeriesName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			//sb.append(" group by missSery.msSeriesName ) as x  ");
			sb.append(" group by xx.MS_SERIES_NAME  ) as x");
			Query query =session.createSQLQuery(sb.toString());
			
				 return ((java.math.BigInteger)query.uniqueResult()).intValue(); 
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
	 public List searchMissSery(MissSery instance,Pagging pagging,String[] meIds) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				Long msUnitCost = instance.getMsUnitCost();
				String msSeriesName = instance.getMsSeriesName();
			
			
				//StringBuffer sb =new StringBuffer(" select missSery from MissSery missSery ");
				/*StringBuffer sb =new StringBuffer(" select missSery from MissSeriesMap missMap ,  MissSery missSery " +
						" where missMap.id.msId=missSery.msId ");*/
				/*StringBuffer sb =new StringBuffer(" select missSery.* from MISS_SERIES missSery  " +
						" left  join   MISS_SERIES_MAP missMap on missSery.MS_ID = missMap.MS_ID ");*/
				StringBuffer sb =new StringBuffer(" select xx.* from ( select missSery.*,missMap.ME_ID  from MISS_SERIES missSery " +
						"  left outer  join   MISS_SERIES_MAP missMap on missSery.MS_ID = missMap.MS_ID " +
						" ) as xx  ");

				/*SELECT  se.ms_id,se.ms_series_name  FROM MISS_CONSULT_EXAM2.MISS_SERIES_MAP map 
				left join MISS_SERIES se on map.ms_id= se.ms_id 
				where map.me_id in(5,6) and se.ms_series_name like '%Se%' 
				group by se.ms_series_name */
				//boolean iscriteria = false;
				logger.debug("  meIds = "+meIds);
				boolean iscriteria = false;
				if(meIds!=null && meIds.length>0){
					String meIdStr="(";
					int meId_size=meIds.length;
					for (int i = 0; i < meId_size; i++) {
						if(i!=meId_size-1){
							meIdStr=meIdStr+meIds[i]+",";
						}else{
							meIdStr=meIdStr+meIds[i]+")";
						}
					}
					 
					logger.debug(" meIds = "+meIdStr);
					// sb.append(iscriteria?(" and missMap.id.meId in "+meIdStr+""):(" where missMap.id.meId in "+meIdStr+""));
					 sb.append(iscriteria?(" and xx.ME_ID in "+meIdStr+""):(" where xx.ME_ID in "+meIdStr+""));
					  iscriteria = true;
				}
				if(msUnitCost !=null && msUnitCost > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and xx.MS_UNIT_COST="+msUnitCost+""):(" where xx.MS_UNIT_COST="+msUnitCost+""));
					  iscriteria = true;
				}
				if(msSeriesName !=null && msSeriesName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(xx.MS_SERIES_NAME) like '%"+msSeriesName.trim().toLowerCase()+"%'"):(" where lcase(xx.MS_SERIES_NAME) like '%"+msSeriesName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				} 
				//sb.append(" group by missSery.msSeriesName ");
				sb.append(" group by xx.MS_SERIES_NAME ");
				
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
					sb.append( " order by xx."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
			  }
				//System.out.println(sb.toString());
				Query query =session.createSQLQuery(sb.toString());
				// set pagging.
				 String size= String.valueOf(getSize(session, instance,meIds)); 
				  logger.debug("sizex="+size); 
				
				 //String size="0";
				 logger.debug("pagging.getPageSize()="+pagging.getPageSize());
				 query.setFirstResult(pagging.getPageSize() * (pagging.getPageNo() - 1));
				 query.setMaxResults(pagging.getPageSize());
				
				 //select * from MISS_CONSULT_EXAM2.MISS_EXAM
				 //WHERE ME_ID = 5
				//th.co.aoe.makedev.missconsult.xstream.MissExam = 
			//	 List<MissSery> l = (List<MissSery>)query.list();   
				 List l =query.list();
				/* List result = query.list();
					int size = result.size();
					for (int i = 0; i < size; i++) {
						Object[] obj= (Object[])result.get(i);
						FinancialIndicationDTO financialIndicationDTO =new FinancialIndicationDTO();
						financialIndicationDTO.setRatioGroupName(obj[0]!=null?obj[0]+"":"");
						financialIndicationDTO.setRatioName(obj[1]!=null?obj[1]+"":"");
						financialIndicationDTO.setThisPeriodValue(obj[2]!=null?obj[2]+"":"");
						financialIndicationDTO.setTrendFlag(obj[3]!=null?obj[3]+"":""); 
						result.set(i, financialIndicationDTO);
					}   */
					
				// logger.debug(" aoe test="+l);
			 	 List<th.co.aoe.makedev.missconsult.xstream.MissSery> missSeries = null;
				// Object[]  missSeries = null;
				// Ljava.lang.Object
				 if(l!=null && l.size()>0){
					 int size_l=l.size();
					// size = String.valueOf(size_l); 
					 logger.debug(" size="+size);
					 missSeries = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>(
							 size_l);
					 //for (MissSery element : l) {
					 for (int i = 0; i < size_l; i++) {
						 Object[] obj= (Object[])l.get(i);
						// logger.debug("obj[0] class="+obj[0].getClass());
						 th.co.aoe.makedev.missconsult.xstream.MissSery xmissSery =new th.co.aoe.makedev.missconsult.xstream.MissSery ();
						 xmissSery.setMsId(obj[0]!=null?Long.parseLong(obj[0]+"") :null);
						 xmissSery.setMsSeriesName(obj[1]!=null?(obj[1]+"") :null);
						 xmissSery.setMsUnitCost(obj[2]!=null?Long.parseLong(obj[2]+"") :null);
						 //BeanUtils.copyProperties(element, xmissSery);
							xmissSery.setPagging(null);
						/* select * from MISS_CONSULT_EXAM2.MISS_SERIES_MAP map left join 
						 MISS_EXAM exam on map.me_id=exam.me_id where map.ms_id=1*/
						 query =session.createQuery("select missExam from MissSeriesMap missMap, MissExam missExam " +
						 		"where missMap.id.meId=missExam.meId  and  missMap.id.msId= "+xmissSery.getMsId()+
						 		" order by missMap.msmOrder asc ");
						List<MissExam> missExams= (List<MissExam>)query.list();
						 String str="";
						 int index=0;
						 int missExamsSize=missExams.size();
							for (th.co.aoe.makedev.missconsult.hibernate.bean.MissExam missExam : missExams) {
								str=str+((index==(missExamsSize-1))?missExam.getMeName():missExam.getMeName()+" , ");
								index++;
							}
							xmissSery.setTestStr(str);
						//	logger.debug(" testStr="+str);
							missSeries.add(xmissSery);
					}
				 }
				 transList.add(missSeries); 
			 	 transList.add(size); 
				return transList;
			} catch (Exception re) {
				//re.printStackTrace();
				logger.error("find by property name failed", re);
				 
			}
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissSery(MissSery transientInstance,String[] meIds)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Long msId=	transientInstance.getMsId();
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery("delete MissSeriesMap missSeriesMap where missSeriesMap.id.msId ="+msId.intValue());
		int result = query.executeUpdate();
		int canUpdate = 0;
		try{
			session.update(transientInstance);
			if(meIds!=null && meIds.length>0){
				int meId_size=meIds.length;
				for (int i = 0; i < meId_size; i++) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap missSeriesMap =
							 new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap();
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMapPK pk =
							new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMapPK();
					pk.setMsId(msId);
					pk.setMeId(Long.parseLong(meIds[i]));
					missSeriesMap.setMsmOrder(new Long(i+1));
					missSeriesMap.setId(pk);
					session.save(missSeriesMap);
				}
			}
			
			canUpdate =1;
			}catch (Exception e) {
				// TODO: handle exception
			} finally {
				if (session != null) {
					session = null;
				} 
			}
			return canUpdate;
		//return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissSery(MissSery persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Long msId=persistentInstance.getMsId();
		Session session=sessionAnnotationFactory.getCurrentSession();		
		int canUpdate = 0;
		try{
		session.delete(persistentInstance);
		Query query=session.createQuery("delete MissSeriesMap missSeriesMap where missSeriesMap.id.msId ="+msId.intValue());
		int result = query.executeUpdate();
		canUpdate =1;
		}finally {
			if (session != null) {
				session = null;
			} 
		}	
		return canUpdate;
		//return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissSery> getxMissSeryObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSery> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissSery> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSery missSery : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissSery xmissSery =new th.co.aoe.makedev.missconsult.xstream.MissSery ();
			BeanUtils.copyProperties(missSery, xmissSery);
			xmissSery.setPagging(null);
			xntcCalendars.add(xmissSery);
		}
		return xntcCalendars;
	}
 
	@Override
	public List listMissSery() throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missSery from MissSery missSery ");
		return query.list(); 	 
	}

}