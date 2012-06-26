package th.co.aoe.makedev.missconsult.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissExam;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult;
import th.co.aoe.makedev.missconsult.managers.MissTestResultService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissTestResult  extends HibernateCommon implements MissTestResultService {
	private static String schema="";
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	//private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static ResourceBundle bundle;
	static{
		bundle =  ResourceBundle.getBundle( "jdbc" );	
		schema=bundle.getString("schema");
	}
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissTestResult findMissTestResultById(Long mtrId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissTestResult missTestResult = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.mtrId=:mtrId");
		query.setParameter("mtrId", mtrId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missTestResult=(MissTestResult)obj;
		}
	  return missTestResult;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissTestResult(MissTestResult transientInstance)
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
	
	

	private int getSize(Session session, MissTestResult instance) throws Exception{
		try {
			Long msId=instance.getMsId();
			MissCandidate missCandidate=instance.getMissCandidate();
			String mcaUsername=missCandidate.getMcaUsername();
			String mcaFirstName=missCandidate.getMcaFirstName();
			String mcaLastName=missCandidate.getMcaLastName();
			String mcaPosition=missCandidate.getMcaPosition();
			String mcaDepartMent=missCandidate.getMcaDepartment();
			String mtrStartTime="";
					if(instance.getMtrStartTime()!=null){
						mtrStartTime=format.format(instance.getMtrStartTime());
					}
			String mtrEndTime="";
					if(instance.getMtrEndTime()!=null){
						mtrEndTime=format.format(instance.getMtrEndTime());
					}
		
			
			String maName=missCandidate.getMissAccount().getMaName();
			 
					 
			//StringBuffer sb =new StringBuffer(" select count(missTestResult) from MissTestResult missTestResult ");
		
			StringBuffer sb =new StringBuffer("select count(*) from ( select result.MTR_ID,result.MCA_ID,result.MS_ID,result.ME_ID,result.MTR_TEST_DATE," +
					" result.MTR_START_TIME,result.MTR_END_TIME,result.MTR_STATUS," +
					" result.MTR_RESULT_CODE,candidate.MCA_USERNAME,candidate.MCA_FIRST_NAME,candidate.MCA_LAST_NAME, " +
					" candidate.MCA_POSITION ,candidate.MCA_DEPARTMENT ,account.MA_NAME from " +
					" "+schema+".MISS_TEST_RESULT  as result left join " +
					" "+schema+".MISS_CANDIDATE candidate on result.MCA_ID=candidate.MCA_ID " +
					" left join "+schema+".MISS_ACCOUNT  account on candidate.MA_ID=account.MA_ID" +
					"  ");
			
			boolean iscriteria = false;
			if(msId !=null && msId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and result.MS_ID="+msId+""):(" where result.MS_ID="+msId+""));
				  iscriteria = true;
			}
			if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and LOWER(candidate.MCA_USERNAME) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_USERNAME) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
				 iscriteria = true;
			}
			if(mcaFirstName !=null && mcaFirstName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and LOWER(candidate.MCA_FIRST_NAME) like '%"+mcaFirstName.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_FIRST_NAME) like '%"+mcaFirstName.trim().toLowerCase()+"%'"));
				 iscriteria = true;
			}
			if(mcaLastName !=null && mcaLastName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and LOWER(candidate.MCA_LAST_NAME) like '%"+mcaLastName.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_LAST_NAME) like '%"+mcaLastName.trim().toLowerCase()+"%'"));
				 iscriteria = true;
			}
			if(mcaPosition !=null && mcaPosition.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and LOWER(candidate.MCA_POSITION) like '%"+mcaPosition.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_POSITION) like '%"+mcaPosition.trim().toLowerCase()+"%'"));
				 iscriteria = true;
			}
			if(mcaDepartMent !=null && mcaDepartMent.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and LOWER(candidate.MCA_DEPARTMENT) like '%"+mcaDepartMent.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_DEPARTMENT) like '%"+mcaDepartMent.trim().toLowerCase()+"%'"));
				 iscriteria = true;
			}
			if(maName !=null && maName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and LOWER(account.MA_NAME) like '%"+maName.trim().toLowerCase()+"%'"):(" where LOWER(account.MA_NAME) like '%"+maName.trim().toLowerCase()+"%'"));
				 iscriteria = true;
			}
			if(mtrStartTime.length()>0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and result.MTR_START_TIME > '"+mtrStartTime.trim()+"'"):(" where result.MTR_START_TIME > '"+mtrStartTime.trim()+"'"));
				 iscriteria = true;
			}
			if(mtrEndTime.length()>0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and result.MTR_END_TIME < '"+mtrEndTime.trim()+"'"):(" where result.MTR_END_TIME < '"+mtrEndTime.trim()+"'"));
				 iscriteria = true;
			}
			sb.append("  ) as x ");
			Query query =session.createSQLQuery(sb.toString());
				 return ((java.math.BigInteger)query.uniqueResult()).intValue();
			//return 0;
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
	 public List searchMissTestResult(MissTestResult instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				/*Long megId = instance.getMegId();
				String megName = instance.getMegName();
			*/
				Long msId=instance.getMsId();
				MissCandidate missCandidate=instance.getMissCandidate();
				String mcaUsername=missCandidate.getMcaUsername();
				String mcaFirstName=missCandidate.getMcaFirstName();
				String mcaLastName=missCandidate.getMcaLastName();
				String mcaPosition=missCandidate.getMcaPosition();
				String mcaDepartMent=missCandidate.getMcaDepartment();
				String mtrStartTime="";
						if(instance.getMtrStartTime()!=null){
							mtrStartTime=format.format(instance.getMtrStartTime());
						}
				String mtrEndTime="";
						if(instance.getMtrEndTime()!=null){
							mtrEndTime=format.format(instance.getMtrEndTime());
						}
			
				
				String maName=missCandidate.getMissAccount().getMaName();
			
				StringBuffer sb =new StringBuffer(" select result.MTR_ID,result.MCA_ID,result.MS_ID,result.ME_ID,result.MTR_TEST_DATE," +
						" result.MTR_START_TIME,result.MTR_END_TIME,result.MTR_STATUS," +
						" result.MTR_RESULT_CODE,candidate.MCA_USERNAME,candidate.MCA_FIRST_NAME,candidate.MCA_LAST_NAME, " +
						" candidate.MCA_POSITION ,candidate.MCA_DEPARTMENT ,account.MA_NAME from " +
						" "+schema+".MISS_TEST_RESULT  as result left join " +
						" "+schema+".MISS_CANDIDATE candidate on result.MCA_ID=candidate.MCA_ID " +
						" left join "+schema+".MISS_ACCOUNT  account on candidate.MA_ID=account.MA_ID" +
						"  ");
				/*
				 * where result.MCA_ID=21 and result.MTR_START_TIME > '2012-06-20'
 and result.MTR_START_TIME < '2012-06-20 23:59:59'
				 */ 
				//SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				boolean iscriteria = false;
				if(msId !=null && msId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and result.MS_ID="+msId+""):(" where result.MS_ID="+msId+""));
					  iscriteria = true;
				}
				if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and LOWER(candidate.MCA_USERNAME) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_USERNAME) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}
				if(mcaFirstName !=null && mcaFirstName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and LOWER(candidate.MCA_FIRST_NAME) like '%"+mcaFirstName.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_FIRST_NAME) like '%"+mcaFirstName.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}
				if(mcaLastName !=null && mcaLastName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and LOWER(candidate.MCA_LAST_NAME) like '%"+mcaLastName.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_LAST_NAME) like '%"+mcaLastName.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}
				if(mcaPosition !=null && mcaPosition.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and LOWER(candidate.MCA_POSITION) like '%"+mcaPosition.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_POSITION) like '%"+mcaPosition.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}
				if(mcaDepartMent !=null && mcaDepartMent.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and LOWER(candidate.MCA_DEPARTMENT) like '%"+mcaDepartMent.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_DEPARTMENT) like '%"+mcaDepartMent.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}
				if(maName !=null && maName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and LOWER(account.MA_NAME) like '%"+maName.trim().toLowerCase()+"%'"):(" where LOWER(account.MA_NAME) like '%"+maName.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}
				if(mtrStartTime.length()>0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and result.MTR_START_TIME > '"+mtrStartTime.trim()+"'"):(" where result.MTR_START_TIME > '"+mtrStartTime.trim()+"'"));
					 iscriteria = true;
				}
				if(mtrEndTime.length()>0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and result.MTR_END_TIME < '"+mtrEndTime.trim()+"'"):(" where result.MTR_END_TIME < '"+mtrEndTime.trim()+"'"));
					 iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missTestResult."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				Query query =session.createSQLQuery(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session, instance)); 
				 logger.info(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
				 query.setFirstResult(pagging.getPageSize() * (pagging.getPageNo() - 1));
				 query.setMaxResults(pagging.getPageSize());
				 List result = query.list();
					int size_result = result.size();
					for (int j = 0; j < size_result; j++) {
						Object[] obj = (Object[]) result.get(j);
						th.co.aoe.makedev.missconsult.xstream.MissTestResult missTestResult = new th.co.aoe.makedev.missconsult.xstream.MissTestResult();
						th.co.aoe.makedev.missconsult.xstream.MissCandidate candidate =new th.co.aoe.makedev.missconsult.xstream.MissCandidate();
						candidate.setMcaUsername(obj[9] != null ? obj[9] + "" : "");
						candidate.setMcaFirstName(obj[10] != null ? obj[10] + "" : "");
						candidate.setMcaLastName(obj[11] != null ? obj[11] + "" : "");
						candidate.setMcaPosition(obj[12] != null ? obj[12] + "" : "");
						candidate.setMcaDepartment(obj[13] != null ? obj[13] + "" : "");
						missTestResult.setMtrTestDate(obj[4] != null ?(java.sql.Date)obj[4]:null);
						candidate.setPagging(null);
						missTestResult.setMissCandidate(candidate);
						missTestResult.setPagging(null);
						
						List<String> axisValues = new ArrayList<String>(4);
						axisValues.add("?");
						axisValues.add("?");
						axisValues.add("?");
						axisValues.add("?");
						missTestResult.setAxisValues(axisValues);
						
						/*missTestResult.setm
						ownerresultDTO.setKpiOwnerKey(obj[0] != null ? new BigDecimal(
								obj[0] + "") : null);
						ownerresultDTO.setKpiOwnerName(obj[1] != null ? obj[1] + "" : "");*/
						result.set(j, missTestResult);
					}
				// List l = query.list();   
				 transList.add(result); 
			 	 transList.add(size); 
				return transList;
			} catch (Exception re) {
				//re.printStackTrace();
				logger.error("find by property name failed", re);
				 
			}
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissTestResult(MissTestResult transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissTestResult(MissTestResult persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Override
	public int processMissTestResult() throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Long saveOrUpdateMissTestResult(String userid,
			MissTestResult missTestResult) throws DataAccessException {
		// TODO Auto-generated method stub

		MissCandidate missCandidate = null;
		Long returnId  = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){		
			missCandidate=(MissCandidate)obj;
			logger.debug("xxxxxxxxxx="+missCandidate.getMcaId().intValue());
			missTestResult.setMissCandidate(missCandidate);
			query=session.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.missCandidate.mcaId=:mcaId and " +
					" missTestResult.meId=:meId and "+
					" missTestResult.msId=:msId  ");
			query.setParameter("mcaId", missCandidate.getMcaId());
			query.setParameter("meId", missTestResult.getMeId());
			query.setParameter("msId", missTestResult.getMsId());
			
			/*
			java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());*/
			List list=query.list();
			if(list!=null && list.size()>0){//update 
			
				logger.debug("size="+list.size());
				logger.debug("MCA_ID="+missTestResult.getMissCandidate().getMcaId());
				logger.debug("ME_ID="+missTestResult.getMeId());
				logger.debug("MS_ID="+missTestResult.getMsId());
				//session.update(missTestResultUpdate);
				String startTimesql="";
						if(missTestResult.getMtrStartTime()!=null)
							startTimesql=" missTestResult.mtrStartTime =:mtrStartTime , ";
				String endTimesql="";
						if(missTestResult.getMtrEndTime()!=null)
							endTimesql=", missTestResult.mtrEndTime =:mtrEndTime  ";
				query=session.createQuery("update MissTestResult missTestResult " +
						" set missTestResult.mtrResultCode =:mtrResultCode ," +
						startTimesql+
					//	" missTestResult.mtrTestDate =:mtrTestDate ," +
						" missTestResult.mtrStatus =:mtrStatus " +
						endTimesql+
						" where missTestResult.missCandidate.mcaId=:mcaId and " +
						" missTestResult.meId=:meId and " +
						" missTestResult.msId=:msId ");
				
				query.setParameter("mcaId", missCandidate.getMcaId()); 
				query.setParameter("meId", missTestResult.getMeId());
				query.setParameter("msId", missTestResult.getMsId());
				query.setParameter("mtrStatus", missTestResult.getMtrStatus());  
				if(startTimesql.length()>0)
					query.setParameter("mtrStartTime", missTestResult.getMtrStartTime());
				if(endTimesql.length()>0)
					query.setParameter("mtrEndTime", missTestResult.getMtrEndTime());  
				query.setParameter("mtrResultCode", missTestResult.getMtrResultCode()); 
				//query.setParameter("mtrTestDate", missTestResult.getMtrTestDate());
				returnId = Long.parseLong((query.executeUpdate())+"");
			}else{ //save
				try{
					obj = session.save(missTestResult);
				
					if(obj!=null){
						//returnId =(th.co.aoe.makedev.missconsult.hibernate.bean.MissTestPK) obj;
						returnId=1l;
					}
				} finally {
						if (session != null) {
							session = null;
						} 
				}
			}
    
		}
		// TODO Auto-generated method stub
		return returnId;
	
	}
	@Override
	public int startMissTestResult(String userid,
			MissTestResult missTestResult) throws DataAccessException {
		// TODO Auto-generated method stub

		MissCandidate missCandidate = null;
		MissExam missExam = null;
		Long returnId  = null;
		int timelimit=0;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj=query.uniqueResult(); 
	

		// period of 1 year and 7 days
		
		if(obj!=null){		
			missCandidate=(MissCandidate)obj;
			 query=session.createQuery(" select missExam from MissExam missExam where missExam.meId=:meId");
			 query.setParameter("meId", missTestResult.getMeId());
			 Object obj2=query.uniqueResult();
			 if(obj2!=null){	
				 missExam=(MissExam)obj2;
				 timelimit= missExam.getMeTimeLimit().intValue();
				 timelimit=timelimit*60; //standardSeconds
				 missTestResult.setMissCandidate(missCandidate);
					query=session.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.missCandidate.mcaId=:mcaId and " +
							" missTestResult.meId=:meId and "+
							" missTestResult.msId=:msId  ");
					query.setParameter("mcaId", missCandidate.getMcaId());
					query.setParameter("meId", missTestResult.getMeId());
					query.setParameter("msId", missTestResult.getMsId());
					 
					List list=query.list();
					if(list!=null && list.size()>0){//update 
						MissTestResult testResult=(MissTestResult)list.get(0);
						Long time=testResult.getMtrStartTime().getTime();
						java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
						//Long time=testResult.getMtrStartTime().getTime();
						DateTime start = new DateTime(time);
						DateTime end = new DateTime(now.getTime());
						logger.debug(" ================== old time"+timelimit);
						//start=start.minusMinutes(timelimit);
						Interval interval = new Interval(start, end);
						logger.debug(" ================== interval time"+interval.toDuration().getStandardMinutes());
						timelimit=timelimit-(int)interval.toDuration().getStandardSeconds();
						
					}else{ //save
						try{
							obj = session.save(missTestResult);
						
							if(obj!=null){
								//returnId =(th.co.aoe.makedev.missconsult.hibernate.bean.MissTestPK) obj;
								returnId=1l;
							}
						} finally {
								if (session != null) {
									session = null;
								} 
						}
						
					}
			 }
			
    
		}
		logger.debug("timelimit="+timelimit);
		// TODO Auto-generated method stub
		return timelimit;
	
	}
	@Override
	public int checkMissTestResult(String userid,
			MissTestResult missTestResult) throws DataAccessException {
		// TODO Auto-generated method stub
		MissCandidate missCandidate = null;
		int tested=0; // 0=not yet test finish, 1=  test finish
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj=query.uniqueResult(); 
	
			 if(obj!=null){	
				 missCandidate=(MissCandidate)obj;
					query=session.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.missCandidate.mcaId=:mcaId and " +
							" missTestResult.msId=:msId  ");
					query.setParameter("mcaId", missCandidate.getMcaId());
					query.setParameter("msId", missCandidate.getMissSery().getMsId());
					 
					@SuppressWarnings("unchecked")
					List<MissTestResult> list=(List<MissTestResult>)query.list();
					if(list!=null && list.size()>0){// check all exam tested  
						for (MissTestResult result : list) {
							if(result.getMtrEndTime()==null){
								tested=0;
								break;
							}
							tested=1;
						}
					}
			 }
		logger.debug("tested="+tested);
		// TODO Auto-generated method stub
		return tested;
	}
}