package th.co.aoe.makedev.missconsult.hibernate;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissExam;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLogPK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSery;
import th.co.aoe.makedev.missconsult.hibernate.bean.User;
import th.co.aoe.makedev.missconsult.managers.MissCandidateService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissCandidate  extends HibernateCommon implements MissCandidateService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private static 	final String[] ignore_id=new String[]{"missAccount","missSery","missCareerMaster","missIndustryMaster"};
	private static final String[] id_ignore_theme=new String[]{"missTheme","missIndustryMaster"};
	private static final SecureRandom random = new SecureRandom();
	private static final  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissCandidate findMissCandidateById(Long mcaId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissCandidate missCandidate = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaId=:mcaId");
		query.setParameter("mcaId", mcaId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missCandidate=(MissCandidate)obj;
		}
	  return missCandidate;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissCandidate(MissCandidate transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
		String password=new BigInteger(40, random).toString(32);
		//73gqqnghrkvfq202q6696gc35o
		//String big=new String(130, random).toString(32);
		Query query=session.createQuery(" select missSery from MissSery missSery where missSery.msId=:msId " +
				" "); 
		query.setParameter("msId", transientInstance.getMissSery().getMsId());
		MissSery missSery = (MissSery)query.uniqueResult();
		Long msUnitCost =(missSery.getMsUnitCost()!=null && missSery.getMsUnitCost().intValue()!=0)?missSery.getMsUnitCost():0l;
		Long masmAvailable=0l;
		query=session.createQuery(" select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap where missAccountSeriesMap.id.maId=:maId " +
				" and missAccountSeriesMap.id.msId=:msId");
		query.setParameter("maId", transientInstance.getMissAccount().getMaId());
		query.setParameter("msId", transientInstance.getMissSery().getMsId());
		@SuppressWarnings("unchecked")
		List<MissAccountSeriesMap> list=query.list();
		//System.err.println("==========================list===>"+list);
		if(list!=null && list.size()>0){
			MissAccountSeriesMap missAccountSeriesMap = list.get(0);
			masmAvailable=(missAccountSeriesMap.getMasmAvailable()!=null && missAccountSeriesMap.getMasmAvailable().length()>0)?Long.valueOf(missAccountSeriesMap.getMasmAvailable()):0l;
			//System.err.println("xxxxxxxx 1="+missAccountSeriesMap.getMissSery().getMsSeriesName());
			//System.err.println("xxxxxxxx 2="+missAccountSeriesMap.getMissAccount().getMaName());
		}
		/* System.err.println(" ===>"+transientInstance.getMissSery().getMsId());
		 System.err.println("==========================masmAvailable===>"+masmAvailable.intValue());
		System.err.println("==========================msUnitCost===>"+msUnitCost.intValue());*/
		 
			//change sery
			if(masmAvailable.intValue()>=msUnitCost.intValue()){
				try{
					Object obj = session.save(transientInstance);
				
					if(obj!=null){
						returnId =(Long) obj;
						query=session.createQuery("update MissCandidate missCandidate " +
								" set missCandidate.mcaUsername =:mcaUsername , " +
								" missCandidate.mcaStatus ='2' ," +
								" missCandidate.mcaPassword ='"+password+"' " +
								" where missCandidate.mcaId ="+returnId);
						query.setParameter("mcaUsername", "MCA0000"+returnId);
						query.executeUpdate();
						//password=transientInstance.getMcontactPassword();
						MessageDigest mda=null;
						try {
							mda = MessageDigest.getInstance("SHA-256");
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						byte [] digesta = mda.digest(password.getBytes());

						password=new String(Hex.encodeHex(digesta));
						User user=new User();
						user.setUsername("MCA0000"+returnId);
						user.setPassword(password);
						session.save(user);
						
						// update seryMap
						query=session.createQuery(" update MissAccountSeriesMap missAccountSeriesMap " +
								" set missAccountSeriesMap.masmAvailable =:masmAvailable " +
								" where missAccountSeriesMap.id.maId=:maId " +
								" and missAccountSeriesMap.id.msId=:msId");
						query.setParameter("masmAvailable",(masmAvailable.intValue()-msUnitCost.intValue())+"");
						query.setParameter("maId", transientInstance.getMissAccount().getMaId());
						query.setParameter("msId", transientInstance.getMissSery().getMsId());
						query.executeUpdate();
					}
				} finally {
						if (session != null) {
							session = null;
						} 
				}
			}else{
				returnId=-1l;
			}
		
		return returnId; 
		
		
	}
	
	

	private int getSize(Session session,int roleMC, MissCandidate instance) throws Exception{
		try {
			String mcaStatus=instance.getMcaStatus();
			String mcaSeries=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
					 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()+""):null;
			String mcaUsername=instance.getMcaUsername();
			String mcaPassword=instance.getMcaPassword();
			String mcaFirstName=instance.getMcaFirstName();
			String mcaLastName=instance.getMcaLastName();
			 
			String mcaCompanyName=(instance.getMissAccount()!=null && instance.getMissAccount().getMaName()!=null)?(instance.getMissAccount().getMaName()):null;
			Long maId=(instance.getMissAccount()!=null && instance.getMissAccount().getMaId()!=null)?(instance.getMissAccount().getMaId()):null;
		
			StringBuffer sb =new StringBuffer(" select count(missCandidate) from MissCandidate missCandidate ");
			boolean iscriteria = false;
			if(roleMC==1){
				sb.append( " where  ( missCandidate.mcaHideStatus !='0' or missCandidate.mcaHideStatus is null )  ");
				iscriteria=true;
			}
			if(mcaStatus !=null && !mcaStatus.equals("-1")){  
				//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
				 sb.append(iscriteria?(" and missCandidate.mcaStatus='"+mcaStatus+"'"):(" where missCandidate.mcaStatus='"+mcaStatus+"'"));
				  iscriteria = true;
			}
			if(mcaSeries !=null && mcaSeries.trim().length()>0){  
				//criteria.add(Expression.eq("mcaSeries", mcaSeries));	
				 sb.append(iscriteria?(" and missCandidate.missSery.msId="+mcaSeries+""):(" where missCandidate.missSery.msId="+mcaSeries+""));
				  iscriteria = true;
			}
			if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missCandidate.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(mcaPassword !=null && mcaPassword.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missCandidate.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(mcaCompanyName !=null && mcaCompanyName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missCandidate.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(mcaFirstName !=null && mcaFirstName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missCandidate.mcaFirstName) like '%"+mcaFirstName.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.mcaFirstName) like '%"+mcaFirstName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(mcaLastName !=null && mcaLastName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missCandidate.mcaLastName) like '%"+mcaLastName.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.mcaLastName) like '%"+mcaLastName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			} 
			if(maId !=null && maId.intValue()!=0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and missCandidate.missAccount.maId="+maId.intValue()+""):(" where missCandidate.missAccount.maId="+maId.intValue()+""));
				  iscriteria = true;
			}
			Query query =session.createQuery(sb.toString());
				 return ((Long)query.uniqueResult()).intValue(); 
		 
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
	 public List searchMissCandidate(MissCandidate instance,int roleMC,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
			 
				String mcaStatus=instance.getMcaStatus();
				String mcaSeries=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
						 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()+""):null;
				String mcaUsername=instance.getMcaUsername();
				String mcaPassword=instance.getMcaPassword();
				String mcaFirstName=instance.getMcaFirstName();
				String mcaLastName=instance.getMcaLastName();
				 
				String mcaCompanyName=(instance.getMissAccount()!=null && instance.getMissAccount().getMaName()!=null)?(instance.getMissAccount().getMaName()):null;
				Long maId=(instance.getMissAccount()!=null && instance.getMissAccount().getMaId()!=null)?(instance.getMissAccount().getMaId()):null;
				//StringBuffer sb =new StringBuffer(" select missCandidate from MissCandidate missCandidate "); 
				StringBuffer sb =new StringBuffer(" select missCandidate ," +
						" (select max(missSeryUse.id.msuDdateTime) from MissSeryUse missSeryUse" +
						" where missSeryUse.id.mcaId=missCandidate.mcaId) as msuDdateTime from MissCandidate missCandidate ");
				 /* sb.append("select missSeryUse from MissSeryUse missSeryUse " +
					  		"where missSeryUse.id.mcaId="+missCandidate.getMcaId().intValue()+" order by missSeryUse.id.msuDdateTime desc "
					  		 );	*/
				boolean iscriteria = false;
				if(roleMC==1){
					sb.append( " where  ( missCandidate.mcaHideStatus !='0' or missCandidate.mcaHideStatus is null )  ");
					iscriteria=true;
				}
				if(mcaStatus !=null && !mcaStatus.equals("-1")){  
					//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
					 sb.append(iscriteria?(" and missCandidate.mcaStatus='"+mcaStatus+"'"):(" where missCandidate.mcaStatus='"+mcaStatus+"'"));
					  iscriteria = true;
				}
				if(mcaSeries !=null && mcaSeries.trim().length()>0){  
					//criteria.add(Expression.eq("mcaSeries", mcaSeries));	
					 sb.append(iscriteria?(" and missCandidate.missSery.msId="+mcaSeries+""):(" where missCandidate.missSery.msId="+mcaSeries+""));
					  iscriteria = true;
				}
				if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missCandidate.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(mcaPassword !=null && mcaPassword.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missCandidate.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(mcaFirstName !=null && mcaFirstName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missCandidate.mcaFirstName) like '%"+mcaFirstName.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.mcaFirstName) like '%"+mcaFirstName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(mcaLastName !=null && mcaLastName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missCandidate.mcaLastName) like '%"+mcaLastName.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.mcaLastName) like '%"+mcaLastName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				} 
				if(mcaCompanyName !=null && mcaCompanyName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missCandidate.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(maId !=null && maId.intValue()!=0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and missCandidate.missAccount.maId="+maId.intValue()+""):(" where missCandidate.missAccount.maId="+maId.intValue()+""));
					  iscriteria = true;
				}
				
			if(pagging.getOrderBy().equals("msuDdateTime")){
				sb.append( " order by msuDdateTime "+pagging.getSortBy().toLowerCase());
			}else
			{
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
					sb.append( " order by missCandidate."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}
			}
				;
				
				Query query =session.createQuery(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session,roleMC, instance)); 
				 logger.debug(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
				 query.setFirstResult(pagging.getPageSize() * (pagging.getPageNo() - 1));
				 query.setMaxResults(pagging.getPageSize());
				 List<java.lang.Object> result=query.list();
				// if(result!=null && result.size()>0);
				// List<MissCandidate> l = query.list();   
				 /*if(l!=null && l.size()>0){
					 MissCandidate x =(MissCandidate)l.get(0);
				 }*/
				// StringBuffer sb =new StringBuffer(" select missCandidate from MissCandidate missCandidate ");
				 List<th.co.aoe.makedev.missconsult.xstream.MissCandidate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCandidate>(
						 result.size());
				  String masmAvailable;
				  for(int i=0;i<result.size();i++){
					  java.lang.Object[] objs=(java.lang.Object[])result.get(i);
					 // System.out.println("xxx"+objs[1].getClass());
					  MissCandidate missCandidate=(MissCandidate)objs[0];
				 /* }
				  for (MissCandidate missCandidate : l) {*/
					  sb.setLength(0);
					  sb.append("select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap " +
					  		"where missAccountSeriesMap.id.maId="+missCandidate.getMissAccount().getMaId().intValue()+" " +
					  		 " and missAccountSeriesMap.id.msId="+missCandidate.getMissSery().getMsId());
					  query =session.createQuery(sb.toString());
					  List<MissAccountSeriesMap> seryMap=query.list();
					  masmAvailable="0";
					  if(seryMap!=null && seryMap.size()>0){
						  MissAccountSeriesMap map =(MissAccountSeriesMap)seryMap.get(0);
						  if(map.getMasmAvailable()!=null && map.getMasmAvailable().length()>0)
							  masmAvailable=map.getMasmAvailable();
					  }
					  
					  sb.setLength(0);
					  sb.append("select missSeryUse from MissSeryUse missSeryUse " +
					  		"where missSeryUse.id.mcaId="+missCandidate.getMcaId().intValue()+" order by missSeryUse.id.msuDdateTime desc "
					  		 );					  
					  query =session.createQuery(sb.toString());
					  query.setFirstResult(0);
				      query.setMaxResults(1);
				      Object obj= query.uniqueResult();
				      String lastLogin="";
				      if(obj!=null){
				    	  th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse missSeryUse=(th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse)obj;
				    	  if(missSeryUse.getId().getMsuDdateTime()!=null){
				    		  lastLogin=format.format(missSeryUse.getId().getMsuDdateTime());
				    	  };
				      }
					  
					  th.co.aoe.makedev.missconsult.xstream.MissCandidate xmissCandidate=getxMissCandidateObject(missCandidate);
					  xmissCandidate.setMasmAvailable(masmAvailable);
					  xmissCandidate.setLastLogin(lastLogin);
					  xntcCalendars.add(xmissCandidate);
					  
					 // masmAvailable query.list();
				   }
				// SELECT * FROM MISS_CONSULT_EXAM3.MISS_ACCOUNT_SERIES_MAP;
				 transList.add(xntcCalendars); 
			 	 transList.add(size);
				return transList;
			} catch (Exception re) {
				//re.printStackTrace();
				logger.error("find by property name failed", re);
				 
			}
			return transList;
		}
	 private th.co.aoe.makedev.missconsult.xstream.MissCandidate getxMissCandidateObject(
				th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate missCandidate) {
		/*	List<th.co.aoe.makedev.missconsult.xstream.MissCandidate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCandidate>(
					ntcCalendars.size());*/
		//	for (th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate missCandidate : ntcCalendars) {
				th.co.aoe.makedev.missconsult.xstream.MissCandidate xmissCandidate =new th.co.aoe.makedev.missconsult.xstream.MissCandidate ();
				BeanUtils.copyProperties(missCandidate, xmissCandidate,ignore_id);
				xmissCandidate.setPagging(null);
				if(missCandidate.getMissSery()!=null && missCandidate.getMissSery().getMsId()!=null && missCandidate.getMissSery().getMsId().intValue()!=0){
					th.co.aoe.makedev.missconsult.xstream.MissSery missSery=new th.co.aoe.makedev.missconsult.xstream.MissSery();
					BeanUtils.copyProperties(missCandidate.getMissSery(), missSery);
					missSery.setPagging(null);
					xmissCandidate.setMissSery(missSery);
				}
				if(missCandidate.getMissCareerMaster()!=null && missCandidate.getMissCareerMaster().getMcmId()!=null && missCandidate.getMissCareerMaster().getMcmId().intValue()!=0){
					th.co.aoe.makedev.missconsult.xstream.MissCareerMaster missCareerMaster=new th.co.aoe.makedev.missconsult.xstream.MissCareerMaster();
					missCareerMaster.setMcmId(missCandidate.getMissCareerMaster().getMcmId());
					xmissCandidate.setMissCareerMaster(missCareerMaster);
				}
				if(missCandidate.getMissIndustryMaster()!=null && missCandidate.getMissIndustryMaster().getMimId()!=null && missCandidate.getMissIndustryMaster().getMimId().intValue()!=0){
					th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster missIndustryMaster=new th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster();
					missIndustryMaster.setMimId(missCandidate.getMissIndustryMaster().getMimId());
					xmissCandidate.setMissIndustryMaster(missIndustryMaster);
				}
				if(missCandidate.getMissAccount()!=null && missCandidate.getMissAccount().getMaId()!=null && missCandidate.getMissAccount().getMaId().intValue()!=0){
					th.co.aoe.makedev.missconsult.xstream.MissAccount missAccount=new th.co.aoe.makedev.missconsult.xstream.MissAccount();
					BeanUtils.copyProperties(missCandidate.getMissAccount(), missAccount,id_ignore_theme);
					if(missCandidate.getMissAccount().getMissTheme()!=null && missCandidate.getMissAccount().getMissTheme().getMtId()!=null){
						th.co.aoe.makedev.missconsult.xstream.MissTheme missTheme = new th.co.aoe.makedev.missconsult.xstream.MissTheme();						
						BeanUtils.copyProperties(missCandidate.getMissAccount().getMissTheme(),missTheme); 
						missAccount.setMissTheme(missTheme);
					}
					if(missCandidate.getMissAccount().getMissIndustryMaster()!=null && missCandidate.getMissAccount().getMissIndustryMaster().getMimId()!=null){
						th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster missIndustryMaster = new th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster();						
						BeanUtils.copyProperties(missCandidate.getMissAccount().getMissIndustryMaster(),missIndustryMaster); 
						missAccount.setMissIndustryMaster(missIndustryMaster);
					}
					 missAccount.setPagging(null);
					xmissCandidate.setMissAccount(missAccount);
				}
				
				//xntcCalendars.add(xmissCandidate);
			//}
			//return xntcCalendars;
			return xmissCandidate;
		} 
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissCandidate(MissCandidate transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query=null;
		//System.out.println("section->"+section);
		//logger.debug(" section==>"+transientInstance.getMcaFirstName());
		//int result = query.executeUpdate();
		/*Query query = session.createQuery("update Stock set stockName = :stockName" +
				" where stockCode = :stockCode");
query.setParameter("stockName", "DIALOG1");
query.setParameter("stockCode", "7277");
int result = query.executeUpdate();*/
		java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
		if(section.equals("0") ){
			int returnRecord=0;
			
			query=session.createQuery("update MissCandidate missCandidate " +
					" set missCandidate.mcaUsername =:mcaUsername," +
					" missCandidate.mcaPassword =:mcaPassword ," +
					" missCandidate.mcaUpdatedDate =:mcaUpdatedDate " +
				//	" missCandidate.missSery.msId=:msId " +
					" where missCandidate.mcaId ="+transientInstance.getMcaId());
			query.setParameter("mcaUsername", transientInstance.getMcaUsername());
			query.setParameter("mcaPassword", transientInstance.getMcaPassword());
			query.setParameter("mcaUpdatedDate", timeStampStartDate);
		//	query.setParameter("msId", transientInstance.getMissSery().getMsId());
			query.executeUpdate();
			
			 query=session.createQuery("update User user " +
					" set user.password =:password " +
					" where user.username =:username");
			query.setParameter("username",transientInstance.getMcaUsername());
			String password = transientInstance.getMcaPassword();

			MessageDigest mda=null;
			try {
				mda = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte [] digesta = mda.digest(password.getBytes());

			password=new String(Hex.encodeHex(digesta));
			query.setParameter("password", password);
			returnRecord=query.executeUpdate();
			
			// check sery
		if(transientInstance.getMissSery().getMsId()!=-1){
		
			query=session.createQuery(" select missSery from MissSery missSery where missSery.msId=:msId " +
					" "); 
			query.setParameter("msId", transientInstance.getMissSery().getMsId());
			MissSery missSery = (MissSery)query.uniqueResult();
			Long msUnitCost =(missSery.getMsUnitCost()!=null && missSery.getMsUnitCost().intValue()!=0)?missSery.getMsUnitCost():0l;
			Long masmAvailable=0l;
			query=session.createQuery(" select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap where missAccountSeriesMap.id.maId=:maId " +
					" and missAccountSeriesMap.id.msId=:msId");
			query.setParameter("maId", transientInstance.getMissAccount().getMaId());
			query.setParameter("msId", transientInstance.getMissSery().getMsId());
			@SuppressWarnings("unchecked")
			List<MissAccountSeriesMap> list=query.list();
			if(list!=null && list.size()>0){
				MissAccountSeriesMap missAccountSeriesMap = list.get(0);
				masmAvailable=(missAccountSeriesMap.getMasmAvailable()!=null && missAccountSeriesMap.getMasmAvailable().length()>0)?Long.valueOf(missAccountSeriesMap.getMasmAvailable()):0l;
			
			}
			query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaId=:mcaId " +
					" "); 
			query.setParameter("mcaId", transientInstance.getMcaId());
			MissCandidate missCandidate = (MissCandidate)query.uniqueResult();
			// check update sery
			if(missCandidate.getMissSery()!=null && missCandidate.getMissSery().getMsId()!=null && missCandidate.getMissSery().getMsId().intValue()!=transientInstance.getMissSery().getMsId().intValue()){
				//change sery
				if(masmAvailable.intValue()>=msUnitCost.intValue()){
					query=session.createQuery("update MissCandidate missCandidate " +
							" set "+
							" missCandidate.missSery.msId=:msId " +
							" where missCandidate.mcaId ="+transientInstance.getMcaId());
					query.setParameter("msId", transientInstance.getMissSery().getMsId());
					query.executeUpdate();
					
					// update seryMap
					query=session.createQuery(" update MissAccountSeriesMap missAccountSeriesMap " +
							" set missAccountSeriesMap.masmAvailable =:masmAvailable " +
							" where missAccountSeriesMap.id.maId=:maId " +
							" and missAccountSeriesMap.id.msId=:msId");
					query.setParameter("masmAvailable",(masmAvailable.intValue()-msUnitCost.intValue())+"");
					query.setParameter("maId", transientInstance.getMissAccount().getMaId());
					query.setParameter("msId", transientInstance.getMissSery().getMsId());
					returnRecord=query.executeUpdate();
				}else{
					returnRecord=-1;
				}
			  }
			}
			return returnRecord; 
		}else if(section.equals("1")){
			//logger.debug("getMissIndustryMaster="+transientInstance.getMissIndustryMaster().getMimId());
			Long mcmId=null;
			if(transientInstance.getMissIndustryMaster()!=null && transientInstance.getMissIndustryMaster().getMimId()!=null 
					&& transientInstance.getMissIndustryMaster().getMimId().intValue()!=0){
				mcmId=transientInstance.getMissIndustryMaster().getMimId();
			}
			logger.debug("getMissCareerMaster="+transientInstance.getMissCareerMaster().getMcmId());
			query=session.createQuery("update MissCandidate missCandidate " +
					" set missCandidate.mcaType =:mcaType ,  " +
					" missCandidate.mcaCitizenId =:mcaCitizenId ,  " +
					" missCandidate.mcaEmail =:mcaEmail ,  " +
					" missCandidate.mcaFirstName =:mcaFirstName ,  " + 
					" missCandidate.mcaLastName =:mcaLastName ,  " +
					" missCandidate.mcaGender =:mcaGender ,  " +					
					" missCandidate.mcaBirthDate =:mcaBirthDate ,  " +
					" missCandidate.mcaTitle =:mcaTitle ,  " + 
					" missCandidate.mcaPosition =:mcaPosition ,  " + 
					" missCandidate.mcaDepartment =:mcaDepartment ,  " + 
					" missCandidate.mcaPhone =:mcaPhone  ,  " +
					" missCandidate.mcaTitleType =:mcaTitleType  ,  " +
					((mcmId!=null)?(" missCandidate.missIndustryMaster.mimId =:mimId  , missCandidate.mimExt=:mimExt, " ):"")+
					" missCandidate.missCareerMaster.mcmId =:mcmId  ,  " +
					" missCandidate.mcaUpdatedDate =:mcaUpdatedDate " +
					" where missCandidate.mcaId ="+transientInstance.getMcaId());
			query.setParameter("mcaType", transientInstance.getMcaType());
			query.setParameter("mcaCitizenId", transientInstance.getMcaCitizenId());
			query.setParameter("mcaEmail", transientInstance.getMcaEmail());
			query.setParameter("mcaFirstName", transientInstance.getMcaFirstName());
			query.setParameter("mcaLastName", transientInstance.getMcaLastName());
			query.setParameter("mcaGender", transientInstance.getMcaGender());
			query.setParameter("mcaBirthDate", transientInstance.getMcaBirthDate());
			query.setParameter("mcaTitle", transientInstance.getMcaTitle());
			query.setParameter("mcaPosition", transientInstance.getMcaPosition());
			query.setParameter("mcaDepartment", transientInstance.getMcaDepartment());
			query.setParameter("mcaPhone", transientInstance.getMcaPhone());
			query.setParameter("mcaTitleType", transientInstance.getMcaTitleType());
			if(mcmId!=null){
				query.setParameter("mimId", transientInstance.getMissIndustryMaster().getMimId());
				query.setParameter("mimExt", transientInstance.getMimExt());
			}
			query.setParameter("mcmId", transientInstance.getMissCareerMaster().getMcmId());
			query.setParameter("mcaUpdatedDate", timeStampStartDate);
			//d
			return query.executeUpdate();
		}else if(section.equals("2")){
			query=session.createQuery("delete MissTest missTest " +
					" where missTest.id.missCandidate.mcaId ="+transientInstance.getMcaId()+
					" and  missTest.id.missSery.msId="+transientInstance.getMissSery().getMsId());
			query.executeUpdate();
			query=session.createQuery("delete MissTestResult missTestResult " +
					" where missTestResult.missCandidate.mcaId ="+transientInstance.getMcaId()+
					" and  missTestResult.msId="+transientInstance.getMissSery().getMsId());
			query.executeUpdate();
			query=session.createQuery("update MissCandidate missCandidate " +
					" set missCandidate.mcaStatus ='2' ,  " +
					" missCandidate.mcaUpdatedDate =:mcaUpdatedDate " +
					" where missCandidate.mcaId ="+transientInstance.getMcaId());
			query.setParameter("mcaUpdatedDate", timeStampStartDate);
			// save reactive log
			MissReactiveLogPK pk = new MissReactiveLogPK();
			pk.setMcaId(transientInstance.getMcaId());
			pk.setMsId(transientInstance.getMissSery().getMsId());
			
			//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
			DateTime datetime=new DateTime(timeStampStartDate.getTime());
			pk.setMrlDateTime(timeStampStartDate);
			MissReactiveLog reactiveLog=new MissReactiveLog();
			reactiveLog.setId(pk);
			reactiveLog.setMrlWeek(Long.valueOf(datetime.weekOfWeekyear().get())) ;		
			int returnId=query.executeUpdate();
			session.save(reactiveLog);
			return returnId;
		
		}
		return 0;
		//return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissCandidatePhoto(MissCandidate transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query  query = session
		.createQuery(" select missCandidate from MissCandidate missCandidate "+
				" where missCandidate.mcaId ="+transientInstance.getMcaId()); 
		List list = query.list();
		if (list.size() > 0) { 
			MissCandidate missCandidate = (MissCandidate) list.get(0);
			if(missCandidate.getMcaPicturePath()!=null && missCandidate.getMcaPicturePath().length()>0){
				 File file_delete=new File("/opt/attach/candidateImg/"+missCandidate.getMcaPicturePath().trim());
				 if(file_delete.exists())
					 file_delete.delete(); 
			} 
		}

			query=session.createQuery("update MissCandidate missCandidate " +
					" set missCandidate.mcaPictureHotlink =:mcaPictureHotlink," +
					" missCandidate.mcaPictureFileName =:mcaPictureFileName ," +
					" missCandidate.mcaPicturePath =:mcaPicturePath ," +
					" missCandidate.mcaUpdatedDate =:mcaUpdatedDate " +
					" where missCandidate.mcaId ="+transientInstance.getMcaId());
			query.setParameter("mcaPictureHotlink", transientInstance.getMcaPictureHotlink());
			query.setParameter("mcaPictureFileName", transientInstance.getMcaPictureFileName());
			query.setParameter("mcaPicturePath", transientInstance.getMcaPicturePath());
			query.setParameter("mcaUpdatedDate", timeStampStartDate);
			return query.executeUpdate();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissCandidate(MissCandidate persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		String username="";
		int canUpdate = 0;
		Long msId=null;
		Long msUnitCost=null;
		String mcaStatus=null;
		Long maId=null;
		Long maTotalUnit=null;
		try{
			Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaId=:mcaId");
			query.setParameter("mcaId", persistentInstance.getMcaId());
			Object obj=query.uniqueResult();
			if(obj!=null){
				MissCandidate missCandidate=(MissCandidate)obj;
				username=missCandidate.getMcaUsername();
				msId=missCandidate.getMissSery().getMsId();
				msUnitCost=missCandidate.getMissSery().getMsUnitCost();
				mcaStatus=missCandidate.getMcaStatus();
				maId=missCandidate.getMissAccount().getMaId();
				maTotalUnit=missCandidate.getMissAccount().getMaTotalUnit();
				session.delete(missCandidate);
				
				
				 if(mcaStatus!=null && mcaStatus.equals("2")  // refund
						 && msId!=null && maId!=null && msUnitCost!=null){ 
					   query=session.createQuery(" select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap" +
					   		"  where missAccountSeriesMap.id.maId=:maId and missAccountSeriesMap.id.msId=:msId");  
						query.setParameter("maId", maId);
						query.setParameter("msId",msId);
						 obj=query.uniqueResult();
						 if(obj!=null){
							 MissAccountSeriesMap missAccountSeriesMap=(MissAccountSeriesMap)obj;
							int masmAvailable= missAccountSeriesMap.getMasmAvailable()!=null?(Integer.parseInt(missAccountSeriesMap.getMasmAvailable())):0;
							
							masmAvailable=masmAvailable+msUnitCost.intValue();
							 query=session.createQuery("update MissAccountSeriesMap missAccountSeriesMap " +
										" set missAccountSeriesMap.masmAvailable =:masmAvailable  " + 
										" where missAccountSeriesMap.id.maId=:maId " +
										" and missAccountSeriesMap.id.msId=:msId ");
							 	query.setParameter("masmAvailable", masmAvailable+"");
							    query.setParameter("maId", maId);
								query.setParameter("msId",msId);
								query.executeUpdate();
								
							//	maTotalUnit = new Long(maTotalUnit.intValue()+msUnitCost.intValue());
								maTotalUnit =Long.valueOf(maTotalUnit.intValue()+msUnitCost.intValue());
								query=session.createQuery("update MissAccount missAccount " +
										" set missAccount.maTotalUnit =:maTotalUnit  " + 
										" where missAccount.maId=:maId " +
										" ");
							 	query.setParameter("maTotalUnit", maTotalUnit);
							    query.setParameter("maId", maId); 
								query.executeUpdate();
						 }
				   }
			}  
	   if(username!=null && username.length()>0){
		   query=session.createQuery("delete User user where user.username =:username");
		   query.setParameter("username",username);
			//int result = query.executeUpdate();
		   query.executeUpdate();
	   }
	  
	  /* 1=used,
			   2=avaliable
			   
		d 
		MissSery*/
		canUpdate =1;
		}finally {
			if (session != null) {
				session = null;
			} 
		}
		return canUpdate;
		//return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Override
	public MissCandidate findMissCandidateByName(String name)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissCandidate missCandidate = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:name");
		query.setParameter("name", name);
		logger.debug("xx="+name);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missCandidate=(MissCandidate)obj;
		} 
	  return missCandidate;
	}
	@Override
	public List<th.co.aoe.makedev.missconsult.xstream.MissExam> findMissExambySery(Long msId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		String[] idIgnore_exam=new String[]{"missExamGroup","missExamType"};
		String[] idIgnore_question=new String[]{"missExam","missTemplate"};
		String[] idIgnore_choice=new String[]{"missQuestion"};
		 
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missSeriesMap from MissSeriesMap missSeriesMap where missSeriesMap.id.msId=:msId order by missSeriesMap.msmOrder ");
		query.setParameter("msId", msId);
		
		@SuppressWarnings("unchecked")
		List<MissSeriesMap> missSeriesMaps=query.list();
		List<th.co.aoe.makedev.missconsult.xstream.MissExam> xmissExams =new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExam>(missSeriesMaps.size());
		for (MissSeriesMap missSeriesMap : missSeriesMaps) {
			query=session.createQuery(" select missExam from MissExam missExam where missExam.meId=:meId order by missExam.meId ");
			query.setParameter("meId", missSeriesMap.getId().getMeId());
			@SuppressWarnings("unchecked")
			List<MissExam> missExams=query.list();
			for (MissExam missExam : missExams) {
				th.co.aoe.makedev.missconsult.xstream.MissExam xmissExam=new th.co.aoe.makedev.missconsult.xstream.MissExam();
				BeanUtils.copyProperties(missExam, xmissExam, idIgnore_exam);
				xmissExam.setPagging(null);
				
				query=session.createQuery(" select missQuestion from MissQuestion missQuestion where missQuestion.missExam.meId=:meId order by missQuestion.mqId ");
				query.setParameter("meId", missExam.getMeId());
				@SuppressWarnings("unchecked")
				List<MissQuestion> questions = query.list();
				List<th.co.aoe.makedev.missconsult.xstream.MissQuestion> xmissQuestions =new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissQuestion>(questions.size());
				for (MissQuestion missQuestion : questions) {
					th.co.aoe.makedev.missconsult.xstream.MissQuestion xmissQuestion=new th.co.aoe.makedev.missconsult.xstream.MissQuestion();
					BeanUtils.copyProperties(missQuestion, xmissQuestion, idIgnore_question);
					xmissQuestion.setPagging(null);
					query=session.createQuery(" select missChoice from MissChoice missChoice where missChoice.missQuestion.mqId=:mqId " +
							" and missChoice.id.mcLang='1' order by missChoice.id.mcNo ");
					query.setParameter("mqId", missQuestion.getMqId());
					@SuppressWarnings("unchecked")
					List<MissChoice> choices = query.list();
					List<th.co.aoe.makedev.missconsult.xstream.MissChoice> xmissChoices =new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissChoice>(choices.size());
					for (MissChoice missChoice : choices) {
						th.co.aoe.makedev.missconsult.xstream.MissChoice xmissChoice=new th.co.aoe.makedev.missconsult.xstream.MissChoice();
						BeanUtils.copyProperties(missChoice, xmissChoice, idIgnore_choice);
						xmissChoice.setMqId(missChoice.getId().getMqId());
						xmissChoice.setMcNo(missChoice.getId().getMcNo());
						xmissChoice.setPagging(null);
						xmissChoices.add(xmissChoice);
					} 
					
					xmissQuestion.setMissChoices(xmissChoices); 
					
					query=session.createQuery(" select missChoice from MissChoice missChoice where missChoice.missQuestion.mqId=:mqId " +
							"and missChoice.id.mcLang='2' order by missChoice.id.mcNo ");
					query.setParameter("mqId", missQuestion.getMqId());
					@SuppressWarnings("unchecked")
					List<MissChoice> choicesEng = query.list();
					List<th.co.aoe.makedev.missconsult.xstream.MissChoice> xmissChoicesEng =new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissChoice>(choicesEng.size());
					for (MissChoice missChoice : choicesEng) {
						th.co.aoe.makedev.missconsult.xstream.MissChoice xmissChoice=new th.co.aoe.makedev.missconsult.xstream.MissChoice();
						BeanUtils.copyProperties(missChoice, xmissChoice, idIgnore_choice);
						xmissChoice.setMqId(missChoice.getId().getMqId());
						xmissChoice.setMcNo(missChoice.getId().getMcNo());
						xmissChoice.setPagging(null);
						xmissChoicesEng.add(xmissChoice);
					} 
					xmissQuestion.setMissChoicesEng(xmissChoicesEng);
					
					xmissQuestions.add(xmissQuestion);
				}
				xmissExam.setMissQuestions(xmissQuestions);
				xmissExams.add(xmissExam);
			}
		}
		return xmissExams;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List exportMissCandidate(String[] mcaIds) throws DataAccessException {
		// TODO Auto-generated method stub
 
		ArrayList  transList = new ArrayList ();
		Session session = sessionAnnotationFactory.getCurrentSession();
		try {
		 
			String inStr="(";
			int mcaSize=mcaIds.length;
			for (int i = 0; i <mcaSize ; i++) {
				inStr=inStr+((i==(mcaSize-1))?mcaIds[i]:(mcaIds[i]+","));
			}
			inStr=inStr+")";
			StringBuffer sb =new StringBuffer(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaId in "+inStr+"" +
					" order by missCandidate.mcaId asc ");
			  
			Query query =session.createQuery(sb.toString());
			  
			 List<MissCandidate> l = query.list();   
			 
			 List<th.co.aoe.makedev.missconsult.xstream.MissCandidate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCandidate>(
						l.size());
			//  String masmAvailable;
			  for (MissCandidate missCandidate : l) {  
				  th.co.aoe.makedev.missconsult.xstream.MissCandidate xmissCandidate=getxMissCandidateObject(missCandidate); 
				  xntcCalendars.add(xmissCandidate); 
			   }
			// SELECT * FROM MISS_CONSULT_EXAM3.MISS_ACCOUNT_SERIES_MAP;
			 transList.add(xntcCalendars); 
		 	 transList.add(xntcCalendars.size()+"");
			return transList;
		} catch (Exception re) {
			//re.printStackTrace();
			logger.error("find by property name failed", re);
			 
		}
		return transList;
	
	}
	@Override
	public MissAccount findMissAccountById(Long maId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissAccount missAccount = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missAccount from MissAccount missAccount where missAccount.maId=:maId");
		query.setParameter("maId", maId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missAccount=(MissAccount)obj;
		}
	  return missAccount;
	}
	@Override
	public MissSery findMissSeryById(Long msId) throws DataAccessException {
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
	@Override
	public MissCandidate findMissCandidateByCitizendIdAndEmail(
			String citizendId, String email) throws DataAccessException {
		// TODO Auto-generated method stub
		MissCandidate missCandidate = null;
				
		Session session=sessionAnnotationFactory.getCurrentSession();
		StringBuffer sb =new StringBuffer(" select missCandidate from MissCandidate missCandidate ");
		boolean iscriteria = false; 
		if(citizendId !=null && citizendId.length()>0){  
			//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
			 sb.append(iscriteria?(" and missCandidate.mcaCitizenId='"+citizendId+"'"):(" where missCandidate.mcaCitizenId='"+citizendId+"'"));
			  iscriteria = true;
		}
		if(email !=null && email.length()>0){  
			//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
			 sb.append(iscriteria?(" and missCandidate.mcaEmail='"+email+"'"):(" where missCandidate.mcaEmail='"+email+"'"));
			  iscriteria = true;
		}
		sb.append(" order by missCandidate.mcaId desc ");
		Query query=session.createQuery(sb.toString());
		 
		 query.setFirstResult(0);
		 query.setMaxResults(1);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missCandidate=(MissCandidate)obj;
		}
	  return missCandidate;
	} 
	 

}