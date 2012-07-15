package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.Column;

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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissChoicePK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissExam;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate;
import th.co.aoe.makedev.missconsult.managers.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissExam  extends HibernateCommon implements MissExamService {
	private static String schema="";
	private static ResourceBundle bundle;
	static{
		bundle =  ResourceBundle.getBundle( "jdbc" );	
		schema=bundle.getString("schema");
	}
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissExam findMissExamById(Long meId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissExam missExam = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missExam from MissExam missExam where missExam.meId=:meId");
		query.setParameter("meId", meId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missExam=(MissExam)obj;
		}
	  return missExam;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissExam(MissExam transientInstance)
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
	
	

	private int getSize(Session session, MissExam instance) throws Exception{
		try {
			 
			Long megId = (instance.getMissExamGroup()!=null)?instance.getMissExamGroup().getMegId():null;
			String meName = instance.getMeName();
			 
			StringBuffer sb =new StringBuffer(" select count(missExam) from MissExam missExam ");
			
			boolean iscriteria = false;
			if(megId !=null && megId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missExam.missExamGroup.megId="+megId+""):(" where missExam.missExamGroup.megId="+megId+""));
				  iscriteria = true;
			}
			if(meName !=null && meName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missExam.meName) like '%"+meName.trim().toLowerCase()+"%'"):(" where lcase(missExam.meName) like '%"+meName.trim().toLowerCase()+"%'"));
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
	 public List searchMissExam(MissExam instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				Long megId = (instance.getMissExamGroup()!=null)?instance.getMissExamGroup().getMegId():null;
				String meName = instance.getMeName();
			
			 //  logger.debug("megIDDD="+megId);
				StringBuffer sb =new StringBuffer(" select missExam from MissExam missExam ");
				
				boolean iscriteria = false;
				if(megId !=null && megId.intValue() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missExam.missExamGroup.megId="+megId+""):(" where missExam.missExamGroup.megId="+megId+""));
					  iscriteria = true;
				}
				if(meName !=null && meName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missExam.meName) like '%"+meName.trim().toLowerCase()+"%'"):(" where lcase(missExam.meName) like '%"+meName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missExam."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
	public int updateMissExam(MissExam transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query=null;
		if(section.equals("0") ){
			query=session.createQuery("update MissExam missExam " +
					" set missExam.meName =:meName," +
					" missExam.meTimeLimit =:meTimeLimit ," +
					" missExam.missExamGroup.megId =:megId, " + 
					" missExam.meIntroduction =:meIntroduction, " +
					" missExam.meInstruction =:meInstruction " +
					" where missExam.meId ="+transientInstance.getMeId());
			query.setParameter("meName", transientInstance.getMeName());
			query.setParameter("meTimeLimit", transientInstance.getMeTimeLimit());
			query.setParameter("megId", transientInstance.getMissExamGroup().getMegId());
			query.setParameter("meIntroduction", transientInstance.getMeIntroduction());
			query.setParameter("meInstruction", transientInstance.getMeInstruction());
			return query.executeUpdate();
		}
			else if(section.equals("2") ){
				query=session.createQuery("update MissExam missExam " +
						" set missExam.meFixAnswerOrder =:meFixAnswerOrder" + 
						" where missExam.meId ="+transientInstance.getMeId());
				query.setParameter("meFixAnswerOrder", transientInstance.getMeFixAnswerOrder());
				return query.executeUpdate();
		//return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	return 0;
	}
	public int createEmptyMissExam(MissExam transientInstance,int questionCountEmpty,int choiceCountEmpty,Long meTimeLimit)
			throws DataAccessException {
		// TODO Auto-generated method stub
		int updateCount=0;
	
		Session session=sessionAnnotationFactory.getCurrentSession();
		logger.debug("meName=="+transientInstance.getMeName());
		logger.debug("questionCountEmpty=="+questionCountEmpty);
		logger.debug("choiceCountEmpty=="+choiceCountEmpty);
		MissExamType missType=new MissExamType();
		missType.setMetId(1l);
	
			transientInstance.setMissExamType(missType);
			transientInstance.setMeTimeLimit(meTimeLimit);
			Long returnId  = null;
			try{
				Object objCopy = session.save(transientInstance);				
				if(objCopy!=null){
					returnId =(Long) objCopy;
					transientInstance.setMeId(returnId);
					for (int i = 0; i < questionCountEmpty; i++) {
						MissQuestion missQuestion=new MissQuestion();
						missQuestion.setMissExam(transientInstance);
						missQuestion.setMqNo(Long.valueOf(i+1));
						Object objQuestionId = session.save(missQuestion);
						if(objQuestionId!=null){
							missQuestion.setMqId((Long)objQuestionId);
							for (int j = 0; j < choiceCountEmpty; j++) {
								 MissChoice missChoice=new MissChoice();
								 MissChoicePK pk =new MissChoicePK();
								 pk.setMcNo(Long.valueOf(j+1));
								 pk.setMqId(missQuestion.getMqId());
								 missChoice.setId(pk);
								// missChoice.setMissQuestion(missQuestion);
								 session.save(missChoice);
							}
						}
					} 
					updateCount=1;
				}
				
			} finally {
					if (session != null) {
						session = null;
					} 
			}
			
			
	return updateCount;
	}
	
	public int copyMissExam(MissExam transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		int updateCount=0;
		MissExam missExam = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missExam from MissExam missExam where missExam.meId=:meId");
		query.setParameter("meId", transientInstance.getMeId());
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missExam=(MissExam)obj;
			MissExam missExamCopy=new MissExam();
			BeanUtils.copyProperties(missExam, missExamCopy, new String[]{"missExamGroup","missExamType"});
			missExamCopy.setMeName(missExamCopy.getMeName()+" Copy");
			if(missExam.getMissExamGroup()!=null && missExam.getMissExamGroup().getMegId()!=null && missExam.getMissExamGroup().getMegId().intValue()!=0){
				MissExamGroup missGroupCopy=new MissExamGroup();
				BeanUtils.copyProperties(missExam.getMissExamGroup(), missGroupCopy);
				missExamCopy.setMissExamGroup(missGroupCopy);
			}
			if(missExam.getMissExamType()!=null && missExam.getMissExamType().getMetId()!=null && missExam.getMissExamType().getMetId().intValue()!=0){
				MissExamType missTypeCopy=new MissExamType();
				BeanUtils.copyProperties(missExam.getMissExamType(), missTypeCopy);
				missExamCopy.setMissExamType(missTypeCopy);
			}
			Long returnId  = null;
			try{
				Object objCopy = session.save(missExamCopy);				
				String[] ignore=new String[]{"missExam","missTemplate"};
				String[] ignore_choice=new String[]{"missQuestion"};
				if(objCopy!=null){
					returnId =(Long) objCopy;
					missExamCopy.setMeId(returnId);
					StringBuffer sb =new StringBuffer(" select missQuestion from MissQuestion missQuestion where missQuestion.missExam.meId="+missExam.getMeId());
					query =session.createQuery(sb.toString());
					List<MissQuestion> list =query.list();   
					for (MissQuestion missQuestion : list) {
						MissQuestion missQuestionCopy=new MissQuestion();
						BeanUtils.copyProperties(missQuestion, missQuestionCopy, ignore);
						missQuestionCopy.setMissExam(missExamCopy);
						if(missQuestion.getMissTemplate()!=null && missQuestion.getMissTemplate().getMtId()!=null && missQuestion.getMissTemplate().getMtId().intValue()!=0){
							MissTemplate missTypeCopy=new MissTemplate();
							BeanUtils.copyProperties(missQuestion.getMissTemplate(), missTypeCopy);
							missQuestionCopy.setMissTemplate(missTypeCopy);
						}
						Object objQuestionCopy = session.save(missQuestionCopy);
						if(objQuestionCopy!=null){
							missQuestionCopy.setMqId((Long)objQuestionCopy);
							 sb =new StringBuffer(" select missChoice from MissChoice missChoice where missChoice.missQuestion.mqId="+missQuestion.getMqId());
							 query =session.createQuery(sb.toString());
							 
							 List<MissChoice> listChoice =query.list();  
							 //logger.error("listChoice   =>"+listChoice);
							 for (MissChoice missChoice : listChoice) {
								 MissChoice missChoiceCopy=new MissChoice();
								 MissChoicePK pk =new MissChoicePK();
								 pk.setMcNo(missChoice.getId().getMcNo());
								 pk.setMqId( missQuestionCopy.getMqId());
								 BeanUtils.copyProperties(missChoice, missChoiceCopy, ignore_choice);
								 //logger.error("listChoice   =>"+pk.toString()+","+pk.getMcNo()+","+pk.getMqId());
								// BeanUtils.copyProperties(missChoice.getId(), pk);
								 missChoiceCopy.setId(pk);
								 //missChoiceCopy.setMissQuestion(missQuestionCopy);
								 
								/* query= session.createQuery("insert into MissChoice missChoice set missChoice.mcLang=:mcLang " +
								 		" ,missChoice.mcMultipleChoose=:mcMultipleChoose ,missChoice.mcName=:mcName " +
								 		",missChoice.mcScore=:mcScore, missChoice.id.mcNo=:mcNo , missChoice.id.mqId=:mqId");*/
								/* query= session.createQuery("insert into MissChoice (mcLang ," +
									 		" mcMultipleChoose,mcName " +
									 		",mcScore,id.mcNo ,id.mqId) ?,?,?,?,?,?");*/
								// INSERT INTO `MISS_CONSULT_EXAM2`.`MISS_CHOICE` (`MC_NAME`, `MC_LANG`, `MC_SCORE`, `MC_MULTIPLE_CHOOSE`, `MQ_ID`, `MC_NO`) VALUES ('C', 'lang', '1', 1, 34, 3);


								/* query =session.createSQLQuery("INSERT INTO "+schema+".`MISS_CHOICE`" +
								 		" (`MC_NAME`, `MC_LANG`,`MC_SCORE`,`MC_MULTIPLE_CHOOSE`, `MQ_ID`, `MC_NO`)" +
								 		" VALUES (?, ?, ?, ?,?, ?);");
								 
								 query.setParameter(0, missChoice.getMcName());
								 query.setParameter(1, missChoice.getMcLang());
								 query.setParameter(2, missChoice.getMcScore());
								 query.setParameter(3, missChoice.getMcMultipleChoose());
								 query.setParameter(4,  missQuestionCopy.getMqId());
								 query.setParameter(5,missChoice.getId().getMcNo());
								 query.executeUpdate();*/
								 session.save(missChoiceCopy);
							 }
						}
						
					}
					   
				}
				
			} finally {
					if (session != null) {
						session = null;
					} 
			}
			updateCount=1;
		}	
	return updateCount;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissExam(MissExam persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Override
	public List listMissExam() throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missExam from MissExam missExam ");
		return query.list(); 	 
	}
	 

}