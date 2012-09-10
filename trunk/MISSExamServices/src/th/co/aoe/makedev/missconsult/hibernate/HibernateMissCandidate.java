package th.co.aoe.makedev.missconsult.hibernate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
	private static final SecureRandom random = new SecureRandom();
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
		//System.out.println(big);
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
		List<MissAccountSeriesMap> list=query.list();
		//System.err.println("==========================list===>"+list);
		if(list!=null && list.size()>0){
			MissAccountSeriesMap missAccountSeriesMap = list.get(0);
			masmAvailable=(missAccountSeriesMap.getMasmAvailable()!=null && missAccountSeriesMap.getMasmAvailable().length()>0)?Long.valueOf(missAccountSeriesMap.getMasmAvailable()):0l;
			//System.err.println("xxxxxxxx 1="+missAccountSeriesMap.getMissSery().getMsSeriesName());
			//System.err.println("xxxxxxxx 2="+missAccountSeriesMap.getMissAccount().getMaName());
		}
		//System.err.println("==========================masmAvailable===>"+masmAvailable.intValue());
		//System.err.println("==========================msUnitCost===>"+msUnitCost.intValue());
		
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
	
	

	private int getSize(Session session, MissCandidate instance) throws Exception{
		try {
			String mcaStatus=instance.getMcaStatus();
			String mcaSeries=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
					 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()+""):null;
			String mcaUsername=instance.getMcaUsername();
			String mcaPassword=instance.getMcaPassword();
			String mcaCompanyName=(instance.getMissAccount()!=null && instance.getMissAccount().getMaName()!=null)?(instance.getMissAccount().getMaName()):null;
			Long maId=(instance.getMissAccount()!=null && instance.getMissAccount().getMaId()!=null)?(instance.getMissAccount().getMaId()):null;
		
			StringBuffer sb =new StringBuffer(" select count(missCandidate) from MissCandidate missCandidate ");
			
			boolean iscriteria = false;
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
	 public List searchMissCandidate(MissCandidate instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
			 
				String mcaStatus=instance.getMcaStatus();
				String mcaSeries=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
						 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()+""):null;
				String mcaUsername=instance.getMcaUsername();
				String mcaPassword=instance.getMcaPassword();
				 
				String mcaCompanyName=(instance.getMissAccount()!=null && instance.getMissAccount().getMaName()!=null)?(instance.getMissAccount().getMaName()):null;
				Long maId=(instance.getMissAccount()!=null && instance.getMissAccount().getMaId()!=null)?(instance.getMissAccount().getMaId()):null;
				StringBuffer sb =new StringBuffer(" select missCandidate from MissCandidate missCandidate ");
				boolean iscriteria = false;
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
				if(maId !=null && maId.intValue()!=0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and missCandidate.missAccount.maId="+maId.intValue()+""):(" where missCandidate.missAccount.maId="+maId.intValue()+""));
					  iscriteria = true;
				}
				
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missCandidate."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				Query query =session.createQuery(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session, instance)); 
				 logger.debug(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
				 query.setFirstResult(pagging.getPageSize() * (pagging.getPageNo() - 1));
				 query.setMaxResults(pagging.getPageSize());
				 
				 List l = query.list();   
				 transList.add(l); 
			 	 transList.add(size);
				return transList;
			} catch (Exception re) {
				//re.printStackTrace();
				logger.error("find by property name failed", re);
				 
			}
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissCandidate(MissCandidate transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query=null;
		//logger.debug(" section==>"+transientInstance.getMcaFirstName());
		//int result = query.executeUpdate();
		/*Query query = session.createQuery("update Stock set stockName = :stockName" +
				" where stockCode = :stockCode");
query.setParameter("stockName", "DIALOG1");
query.setParameter("stockCode", "7277");
int result = query.executeUpdate();*/
		if(section.equals("0") ){
			int returnRecord=0;
			
			query=session.createQuery("update MissCandidate missCandidate " +
					" set missCandidate.mcaUsername =:mcaUsername," +
					" missCandidate.mcaPassword =:mcaPassword " +
				//	" missCandidate.missSery.msId=:msId " +
					" where missCandidate.mcaId ="+transientInstance.getMcaId());
			query.setParameter("mcaUsername", transientInstance.getMcaUsername());
			query.setParameter("mcaPassword", transientInstance.getMcaPassword());
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
			logger.debug("getMissIndustryMaster="+transientInstance.getMissIndustryMaster().getMimId());
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
					" missCandidate.missIndustryMaster.mimId =:mimId   , " +
					" missCandidate.missCareerMaster.mcmId =:mcmId    " +
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
			query.setParameter("mimId", transientInstance.getMissIndustryMaster().getMimId());
			query.setParameter("mcmId", transientInstance.getMissCareerMaster().getMcmId());
			//d
			return query.executeUpdate();
		}else if(section.equals("2")){
			// System.out.println("xxxxxxxxxxxxxxxxxxx");
			query=session.createQuery("delete MissTest missTest " +
					" where missTest.id.missCandidate.mcaId ="+transientInstance.getMcaId()+
					" and  missTest.id.missSery.msId="+transientInstance.getMissSery().getMsId());
			query.executeUpdate();
			query=session.createQuery("delete MissTestResult missTestResult " +
					" where missTestResult.missCandidate.mcaId ="+transientInstance.getMcaId()+
					" and  missTestResult.msId="+transientInstance.getMissSery().getMsId());
			query.executeUpdate();
			query=session.createQuery("update MissCandidate missCandidate " +
					" set missCandidate.mcaStatus ='2' " +
					" where missCandidate.mcaId ="+transientInstance.getMcaId());
			
			// save reactive log
			MissReactiveLogPK pk = new MissReactiveLogPK();
			pk.setMcaId(transientInstance.getMcaId());
			pk.setMsId(transientInstance.getMissSery().getMsId());
			
			java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
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
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query=null;
			query=session.createQuery("update MissCandidate missCandidate " +
					" set missCandidate.mcaPictureHotlink =:mcaPictureHotlink," +
					" missCandidate.mcaPictureFileName =:mcaPictureFileName ," +
					" missCandidate.mcaPicturePath =:mcaPicturePath " +
					" where missCandidate.mcaId ="+transientInstance.getMcaId());
			query.setParameter("mcaPictureHotlink", transientInstance.getMcaPictureHotlink());
			query.setParameter("mcaPictureFileName", transientInstance.getMcaPictureFileName());
			query.setParameter("mcaPicturePath", transientInstance.getMcaPicturePath());
			return query.executeUpdate();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissCandidate(MissCandidate persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		String username="";
		int canUpdate = 0;
		try{
			Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaId=:mcaId");
			query.setParameter("mcaId", persistentInstance.getMcaId());
			Object obj=query.uniqueResult();
			if(obj!=null){
				MissCandidate missCandidate=(MissCandidate)obj;
				username=missCandidate.getMcaUsername();
				session.delete(missCandidate);
			}
			
	
		
	   if(username!=null && username.length()>0){
		   query=session.createQuery("delete User user where user.username =:username");
		   query.setParameter("username",username);
			int result = query.executeUpdate();
	   }
		
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
		List<MissSeriesMap> missSeriesMaps=query.list();
		List<th.co.aoe.makedev.missconsult.xstream.MissExam> xmissExams =new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExam>(missSeriesMaps.size());
		for (MissSeriesMap missSeriesMap : missSeriesMaps) {
			query=session.createQuery(" select missExam from MissExam missExam where missExam.meId=:meId order by missExam.meId ");
			query.setParameter("meId", missSeriesMap.getId().getMeId());
			List<MissExam> missExams=query.list();
			for (MissExam missExam : missExams) {
				th.co.aoe.makedev.missconsult.xstream.MissExam xmissExam=new th.co.aoe.makedev.missconsult.xstream.MissExam();
				BeanUtils.copyProperties(missExam, xmissExam, idIgnore_exam);
				xmissExam.setPagging(null);
				
				query=session.createQuery(" select missQuestion from MissQuestion missQuestion where missQuestion.missExam.meId=:meId order by missQuestion.mqId ");
				query.setParameter("meId", missExam.getMeId());
				List<MissQuestion> questions = query.list();
				List<th.co.aoe.makedev.missconsult.xstream.MissQuestion> xmissQuestions =new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissQuestion>(questions.size());
				for (MissQuestion missQuestion : questions) {
					th.co.aoe.makedev.missconsult.xstream.MissQuestion xmissQuestion=new th.co.aoe.makedev.missconsult.xstream.MissQuestion();
					BeanUtils.copyProperties(missQuestion, xmissQuestion, idIgnore_question);
					xmissQuestion.setPagging(null);
					query=session.createQuery(" select missChoice from MissChoice missChoice where missChoice.missQuestion.mqId=:mqId order by missChoice.id.mcNo ");
					query.setParameter("mqId", missQuestion.getMqId());
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
					xmissQuestions.add(xmissQuestion);
				}
				xmissExam.setMissQuestions(xmissQuestions);
				xmissExams.add(xmissExam);
			}
		}
		return xmissExams;
	}
	 

}