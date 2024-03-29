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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo;
import th.co.aoe.makedev.missconsult.managers.MissTodoService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissTodo  extends HibernateCommon implements MissTodoService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissTodo findMissTodoById(Long megId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissTodo missTodo = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missTodo from MissTodo missTodo where missTodo.megId=:megId");
		query.setParameter("megId", megId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missTodo=(MissTodo)obj;
		}
	  return missTodo;
	}
	@Transactional(readOnly=true)
	public String findCandidateEmailFrom(MissTodo missTodo)
			throws DataAccessException {
		String email=null;
		// TODO Auto-generated method stub
		MissTestResult missTestResult=null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.mtrId=:mtrId");
		query.setParameter("mtrId", missTodo.getMtodoRef());
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missTestResult=(MissTestResult)obj;
			email=missTestResult.getMissCandidate().getMcaEmail();
		}
	  return email;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissTodo(MissTodo transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Long returnId  = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		StringBuffer sb =new StringBuffer(" select missTodo from MissTodo missTodo where missTodo.missAccount.maId=:maId " +
				" and missTodo.mtodoType=:mtodoType and mtodoRef=:mtodoRef");
		Query query = session.createQuery(sb.toString());
		query.setParameter("maId", transientInstance.getMissAccount().getMaId());
		query.setParameter("mtodoType", transientInstance.getMtodoType());
		query.setParameter("mtodoRef", transientInstance.getMtodoRef());
		@SuppressWarnings("rawtypes")
		List list=query.list();
		if(list.size()==0){
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
		}else{
			returnId= Long.valueOf(0);	
		}
		return returnId;
		 
	}
	
	

	private int getSize(Session session, MissTodo instance) throws Exception{
		try {
			MissAccount account = instance.getMissAccount();
			Long maId= null;
			if(account!=null)
				maId=account.getMaId();
		//	String megName = instance.getMegName();
			 
		
			/*StringBuffer sb =new StringBuffer(" select count(missTodo) from MissTodo missTodo where ( missTodo.mtodoResponse!='1'" +
					" or  missTodo.mtodoResponse is null or missTodo.mtodoResponse='0' ) ");*/
			StringBuffer sb =new StringBuffer("SELECT count(*)	FROM  "+ServiceConstant.SCHEMA+".MISS_TODO missTodo " +
					" WHERE   EXISTS ( select *     from  "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result" +
							"   where result.mtr_id = missTodo.mtodo_ref ) and ( missTodo.mtodo_response != '1' " +
					" or  missTodo.mtodo_response is null  or missTodo.mtodo_response='0' ) ");
			boolean iscriteria = true;
			if(maId !=null && maId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
			//	 sb.append(iscriteria?(" and missTodo.missAccount.maId="+maId+""):(" where missTodo.missAccount.maId="+maId+""));
				 sb.append(iscriteria?(" and missTodo.ma_id="+maId+""):(" where missTodo.ma_id="+maId+""));
				 iscriteria = true;
			}
		/*	if(megName !=null && megName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missTodo.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missTodo.megName) like '%"+megName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}*/
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
	 public List searchMissTodo(MissTodo instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				MissAccount account = instance.getMissAccount();
				Long maId= null;
				if(account!=null)
					maId=account.getMaId(); 
				//System.out.println("maId->"+maId);
			/*	SELECT count(*)
				FROM MISS_CONSULT_EXAM.MISS_TODO todo 
				WHERE   not EXISTS ( select *
				              from MISS_CONSULT_EXAM.MISS_TEST_RESULT result
				              where result.mtr_id = todo.mtodo_ref);
			*/
				/*StringBuffer sb =new StringBuffer(" select missTodo from MissTodo missTodo where ( missTodo.mtodoResponse!='1'" +
						" or  missTodo.mtodoResponse is null  or missTodo.mtodoResponse='0' ) ");*/
				 
				StringBuffer sb =new StringBuffer("SELECT missTodo.MTODO_ID,missTodo.MTODO_TASK,missTodo.MTODO_TYPE," +
						"missTodo.MA_ID,missTodo.MTODO_RESPONSE,missTodo.MTODO_REF	FROM  "+ServiceConstant.SCHEMA+".MISS_TODO missTodo " +
						" WHERE   EXISTS ( select *     from  "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result" +
								"   where result.mtr_id = missTodo.mtodo_ref ) and ( missTodo.mtodo_response != '1' " +
						" or  missTodo.mtodo_response is null  or missTodo.mtodo_response='0' )  ");
				
				boolean iscriteria = true;
				if(maId !=null && maId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					// sb.append(iscriteria?(" and missTodo.missAccount.maId="+maId+""):(" where missTodo.missAccount.maId="+maId+""));
					 sb.append(iscriteria?(" and missTodo.ma_id="+maId+""):(" where missTodo.ma_id="+maId+""));
					  iscriteria = true;
				}
				 
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missTodo."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				//Query query =session.createQuery(sb.toString());
				Query query =session.createSQLQuery(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session, instance)); 
				 logger.debug(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
				 query.setFirstResult(pagging.getPageSize() * (pagging.getPageNo() - 1));
				 query.setMaxResults(pagging.getPageSize());
				 
				 List<Object[]> l = query.list(); 
				 List result=new ArrayList(l.size()); 
				 for (Object[] objects : l) {
					 MissTodo todo=new MissTodo();
					 todo.setMtodoId(Long.valueOf((java.lang.Integer)objects[0]));
					 todo.setMtodoTask((java.lang.String)objects[1]);
					 todo.setMtodoType((java.lang.String)objects[2]);
					 //todo.setType((java.lang.String)objects[2]);
					 todo.setMtodoResponse((java.lang.String)objects[4]);
					 todo.setMtodoRef(Long.valueOf((java.lang.Integer)objects[5]));
					 result.add(todo);
				//	System.out.println(objects.getClass());
					 
				  }
				 /*for (int i = 0; i < l.size(); i++) {
					Object[] obj=l.get(i);
				}*/
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
	public int updateMissTodo(MissTodo transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		//return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery("update MissTodo missTodo " +
				" set missTodo.mtodoResponse =:mtodoResponse " + 
				" where missTodo.mtodoId=:mtodoId ");
	query.setParameter("mtodoResponse",transientInstance.getMtodoResponse());
	query.setParameter("mtodoId", transientInstance.getMtodoId());
		return query.executeUpdate();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissTodo(MissTodo persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 

}