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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissChoicePK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion;
import th.co.aoe.makedev.missconsult.managers.MissQuestionService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissQuestion  extends HibernateCommon implements MissQuestionService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private static final String[] ignore_id=new String[]{"missTemplate","missExam","missChoices"};
	private static final String[] ignore_exam_id=new String[]{"missExamGroup","missExamType"};
	private static final String[] ignore_question_id=new String[]{"missQuestion"}; 
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public th.co.aoe.makedev.missconsult.xstream.MissQuestion findMissQuestionById(Long mqId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissQuestion missQuestion = null;
		th.co.aoe.makedev.missconsult.xstream.MissQuestion xmissQuestion =null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missQuestion from MissQuestion missQuestion where missQuestion.mqId=:mqId");
		query.setParameter("mqId", mqId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missQuestion=(MissQuestion)obj;
			xmissQuestion = new th.co.aoe.makedev.missconsult.xstream.MissQuestion();
			BeanUtils.copyProperties(missQuestion,xmissQuestion,ignore_id);	
			xmissQuestion.setPagging(null);
			
			if(missQuestion.getMissExam()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissExam missExam = new th.co.aoe.makedev.missconsult.xstream.MissExam();
				BeanUtils.copyProperties(missQuestion.getMissExam(),missExam,ignore_exam_id); 
				xmissQuestion.setMissExam(missExam);
			}
			if(missQuestion.getMissTemplate()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissTemplate missTemplate = new th.co.aoe.makedev.missconsult.xstream.MissTemplate();
				BeanUtils.copyProperties(missQuestion.getMissTemplate(),missTemplate); 
				xmissQuestion.setMissTemplate(missTemplate);
			}
			query=session.createQuery(" select missChoice from MissChoice missChoice where missChoice.missQuestion.mqId=:mqId order by missChoice.id.mcNo asc ");
			query.setParameter("mqId", mqId);
			@SuppressWarnings("unchecked")
			List<MissChoice> missChoices= (List<MissChoice>) query.list();
			List<th.co.aoe.makedev.missconsult.xstream.MissChoice>	 xmissChoices= new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissChoice>();
			for (MissChoice missChoice : missChoices) {
				th.co.aoe.makedev.missconsult.xstream.MissChoice  xmissChoice= new th.co.aoe.makedev.missconsult.xstream.MissChoice();
				BeanUtils.copyProperties(missChoice, xmissChoice,ignore_question_id);
				xmissChoice.setMcNo(missChoice.getId().getMcNo());
				xmissChoice.setPagging(null);
				xmissChoices.add(xmissChoice);
			}
			xmissQuestion.setMissChoices(xmissChoices);
			
		}
	  return xmissQuestion;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissQuestion(MissQuestion transientInstance)
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
	
	

	private int getSize(Session session, MissQuestion instance) throws Exception{
		try {
			 
		/*	Long megId = instance.getMegId();
			String megName = instance.getMegName();
			 
			StringBuffer sb =new StringBuffer(" select count(missQuestion) from MissQuestion missQuestion ");
			
			boolean iscriteria = false;
			if(megId !=null && megId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missQuestion.megId="+megId+""):(" where missQuestion.megId="+megId+""));
				  iscriteria = true;
			}
			if(megName !=null && megName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missQuestion.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missQuestion.megName) like '%"+megName.trim().toLowerCase()+"%'"));
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
	 public List searchMissQuestion(MissQuestion instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				/*Long megId = instance.getMegId();
				String megName = instance.getMegName();
			
			
				StringBuffer sb =new StringBuffer(" select missQuestion from MissQuestion missQuestion ");
				
				boolean iscriteria = false;
				if(megId !=null && megId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missQuestion.megId="+megId+""):(" where missQuestion.megId="+megId+""));
					  iscriteria = true;
				}
				if(megName !=null && megName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missQuestion.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missQuestion.megName) like '%"+megName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missQuestion."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
	public int updateMissQuestion(MissQuestion transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissQuestion(MissQuestion persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Override
	public List listMissQuestions(Long meId) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missQuestion from MissQuestion missQuestion where  missQuestion.missExam.meId="+meId.intValue());
		return query.list(); 	
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	@Override
	public void setUpTestMissQuestion() throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missQuestion from MissQuestion missQuestion where  missQuestion.missExam.meId=15 order by missQuestion.mqId asc ");
		 List<MissQuestion> list =query.list();
		 int i=1;
		 for (MissQuestion missQuestion : list) {
			 query=session.createQuery("update MissQuestion missQuestion " +
						" set missQuestion.mqNameTh1 =:mqNameTh1 ," +
						" missQuestion.mqChoose =1," +
						" missQuestion.missTemplate.mtId =1 ," +
						" missQuestion.mqNo =:mqNo " +
						" where missQuestion.mqId=:mqId" );
			 query.setParameter("mqNameTh1", i+"");
			 query.setParameter("mqNo",Long.valueOf(i));
			 query.setParameter("mqId", missQuestion.getMqId());
			 query.executeUpdate();
			i++;
			StringBuffer sb=new StringBuffer();
			for(int j =0;j<5;j++){
				MissChoice choice =new MissChoice();
				MissChoicePK pk =new MissChoicePK();
				pk.setMcNo(Long.valueOf(j+1));
				pk.setMqId(missQuestion.getMqId());
				sb.setLength(0);
				 if(j==0){
					sb.append("ไม่เห็นด้วยอย่างยิ่ง");
				}else if(j==1){
					sb.append("ไม่เห็นด้วย");
				}else if(j==2){
					sb.append("ไม่มีความเห็น");
				}else if(j==3){
					sb.append("เห็นด้วย");
				}else if(j==4){
					sb.append("เห็นด้วยอย่างยิ่ง");
				}
				
				choice.setMcName(sb.toString());
				choice.setId(pk);
				session.save(choice);
			}
		}
			 
	}
	

}