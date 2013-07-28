package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissChoicePK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion;
import th.co.aoe.makedev.missconsult.managers.MissQuestionService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissQuestion  extends HibernateCommon implements MissQuestionService {

	//private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
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
			for(int i=1;i<=2;i++){
			query=session.createQuery(" select missChoice from MissChoice missChoice where missChoice.missQuestion.mqId=:mqId and missChoice.id.mcLang=:mcLang  order by missChoice.id.mcNo asc ");
			query.setParameter("mqId", mqId);
			query.setParameter("mcLang", i+"");
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
			 if(i==1)
				 xmissQuestion.setMissChoices(xmissChoices);
			 else
				 xmissQuestion.setMissChoicesEng(xmissChoices);
			
			}
			
		}
	  return xmissQuestion;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissQuestion(MissQuestion transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" select max(question.mqNo) from MissQuestion question where question.missExam.meId="+transientInstance.getMissExam().getMeId());
	  //  query.setParameter("", arg1) 
		Long qhNo=1l;
		Object objMax=query.uniqueResult();
		if(objMax!=null){
			qhNo=(Long)objMax+1;
		}
		transientInstance.setMqNo(qhNo);
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
	
	

	 
	 @SuppressWarnings({ "rawtypes" })
	 @Transactional(readOnly=true)
	 public List searchMissQuestion(MissQuestion instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			 
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
	@SuppressWarnings("rawtypes")
	@Override
	public List listMissQuestions(Long meId) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missQuestion from MissQuestion missQuestion where  missQuestion.missExam.meId="+meId.intValue() +" order by missQuestion.mqNo asc ");
		return query.list(); 
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List listMissQuestionsWithChoices(Long meId) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missQuestion from MissQuestion missQuestion where  missQuestion.missExam.meId="+meId.intValue() +" order by missQuestion.mqNo asc ");
		List<th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion> missQuestions =query.list(); 
		List<th.co.aoe.makedev.missconsult.xstream.MissQuestion> xmissQuestions =new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissQuestion>(missQuestions.size()); 
		for (MissQuestion missQuestion : missQuestions) {
			th.co.aoe.makedev.missconsult.xstream.MissQuestion xmissQuestion = new th.co.aoe.makedev.missconsult.xstream.MissQuestion();
			BeanUtils.copyProperties(missQuestion, xmissQuestion,ignore_id);
			xmissQuestion.setPagging(null); 
			for(int i=1;i<=2;i++){
				query=session.createQuery(" select missChoice from MissChoice missChoice where missChoice.missQuestion.mqId=:mqId and missChoice.id.mcLang=:mcLang  order by missChoice.id.mcNo asc ");
				query.setParameter("mqId", missQuestion.getMqId());
				query.setParameter("mcLang", i+"");
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
				 if(i==1)
					 xmissQuestion.setMissChoices(xmissChoices);
				 else
					 xmissQuestion.setMissChoicesEng(xmissChoices);
				
				}
			xmissQuestions.add(xmissQuestion);
		}
		return 	xmissQuestions;
	}
	@Override
	public int getQuestionOrdered(Long meId) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select count(missQuestion) from MissQuestion missQuestion where missQuestion.mqNo is null and" +
				"  missQuestion.missExam.meId="+meId.intValue());
		int count=((Long)query.uniqueResult()).intValue();
		return  count;
	}
	private  boolean isNumeric(String str)
	{
	  return str.matches("\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	@Override 
	public int setOrderItems(Long meId,String[] mqNo_array,String mqId_array[]) throws DataAccessException {
 
		// TODO Auto-generated method stub

		Session session=sessionAnnotationFactory.getCurrentSession();
		//Query query=session.createQuery(" select missQuestion from MissQuestion missQuestion where  missQuestion.missExam.meId=15 order by missQuestion.mqId asc ");
		//SELECT * FROM MISS_QUESTION QUESTION WHERE QUESTION.MQ_NO IS NULL ORDER BY QUESTION.MQ_ID ASC 
		/*Query query=session.createQuery(" select missQuestion from MissQuestion missQuestion where  missQuestion.mqNo is null " +
				" and missQuestion.missExam.meId="+meId.intValue()+"  order by missQuestion.mqId asc ");
		 List<MissQuestion> list =query.list();*/
		Query query=null;
		 int index=1;
		if(mqNo_array!=null && mqId_array!=null && mqNo_array.length>0 && mqId_array.length>0){
			int size=mqId_array.length;
			for (int i = 0; i < size; i++) {
				if( isNumeric(mqNo_array[i].trim()) && isNumeric(mqId_array[i].trim()) ){
						query=session.createQuery("update MissQuestion missQuestion " +
							" set " +
							//" missQuestion.mqNameTh1 =:mqNameTh1 ," +
							//" missQuestion.mqChoose =1," +
							//" missQuestion.missTemplate.mtId =1 ," +
							" missQuestion.mqNo =:mqNo " +
							" where missQuestion.mqId=:mqId" );
				// query.setParameter("mqNameTh1", i+"");
						query.setParameter("mqNo",Long.valueOf(mqNo_array[i].trim()));
				 		query.setParameter("mqId", Long.valueOf(mqId_array[i].trim()));
				 		query.executeUpdate();
				 		index++;
				}
				 
			}
		}
		/* int i=1;
		 for (MissQuestion missQuestion : list) {
			 query=session.createQuery("update MissQuestion missQuestion " +
						" set " +
						//" missQuestion.mqNameTh1 =:mqNameTh1 ," +
						//" missQuestion.mqChoose =1," +
						//" missQuestion.missTemplate.mtId =1 ," +
						" missQuestion.mqNo =:mqNo " +
						" where missQuestion.mqId=:mqId" );
			// query.setParameter("mqNameTh1", i+"");
			 query.setParameter("mqNo",Long.valueOf(i));
			 query.setParameter("mqId", missQuestion.getMqId());
			 query.executeUpdate();
			i++;
		}*/
   return index;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	@Override
	public void setUpTestMissQuestion() throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		//Query query=session.createQuery(" select missQuestion from MissQuestion missQuestion where  missQuestion.missExam.meId=15 order by missQuestion.mqId asc ");
		//SELECT * FROM MISS_QUESTION QUESTION WHERE QUESTION.MQ_NO IS NULL ORDER BY QUESTION.MQ_ID ASC 
	//	Query query=session.createQuery(" select missQuestion from MissQuestion missQuestion where  missQuestion.mqNo is null  order by missQuestion.mqId asc ");
		Query query=session.createQuery(" select missQuestion from MissQuestion missQuestion where  missQuestion.missExam.meId=50  order by missQuestion.mqNo asc ");
		 @SuppressWarnings("unchecked")
		List<MissQuestion> list =query.list();
		 int i=1;
		 for (MissQuestion missQuestion : list) {
			 query=session.createQuery("update MissQuestion missQuestion " +
						" set missQuestion.mqNameTh1 =:mqNameTh1 " +
						/*" missQuestion.mqChoose =1," +
						" missQuestion.missTemplate.mtId =1 ," +
						" missQuestion.mqNo =:mqNo " +*/
						" where missQuestion.mqId=:mqId" );
			 query.setParameter("mqNameTh1", i+"");
			// query.setParameter("mqNo",Long.valueOf(i));
			 query.setParameter("mqId", missQuestion.getMqId());
			 query.executeUpdate();
			i++;
			StringBuffer sb=new StringBuffer();
			for(int j =0;j<4;j++){
				
				MissChoice choice =new MissChoice();
				MissChoicePK pk =new MissChoicePK();
				pk.setMcNo(Long.valueOf(j+1));
				pk.setMqId(missQuestion.getMqId());
				pk.setMcLang("1");
				sb.setLength(0);
				
				
				
				
				

				 /*if(j==0){
						sb.append("ไม่เหมือนฉันเลย");
					}else if(j==1){
						sb.append("นานๆครั้ง ฉันก็เป็นแบบนี้");
					}else if(j==2){
						sb.append("เหมือนฉันเลย");
					}*/ 

				/* if(j==0){
					sb.append("เห็นด้วยอย่างมาก");
				}else if(j==1){
					sb.append("เห็นด้วย");
				}else if(j==2){
					sb.append("ไม่เห็นด้วยมาก");
				}else if(j==3){
					sb.append("ไม่เห็นด้วย");
				}*/
				 if(j==0){
						sb.append("ไม่เห็นด้วยอย่างยิ่ง");
					}else if(j==1){
						sb.append("ไม่เห็นด้วย");
					}else if(j==2){
						sb.append("เห็นด้วย");
					}else if(j==3){
						sb.append("เห็นด้วยอย่างยิ่ง");
					}
				/* if(j==0){
						sb.append("1.");
					}else if(j==1){
						sb.append("2.");
					}else if(j==2){
						sb.append("3.");
					}else if(j==3){
						sb.append("4.");
					}else if(j==4){
						sb.append("5.");
					}*/
				  /* if(j==0){
						sb.append("เห็นด้วย");
					} else if(j==1){
						sb.append("ไม่เห็นด้วย");
					}*/
				/* if(j==0){
						sb.append("ชอบมากที่สุด");
					} else if(j==1){
						sb.append("ชอบบ้างแต่ไม่ทั้งหมด");
					}else if(j==2){
						sb.append("ไม่ชอบเลย");
					}*/
				/*else if(j==4){
					sb.append("เห็นด้วยอย่างยิ่ง");
				}*/
				/* query=session.createQuery("update MissChoice missChoice " +
							" set missChoice.mcName =:mcName " +
						
							" where missChoice.id.mqId=:mqId " +
							" and missChoice.id.mcNo=:mcNo " );
				 query.setParameter("mcName",sb.toString());
				 query.setParameter("mcNo",pk.getMcNo());
				 query.setParameter("mqId", pk.getMqId());
				 query.executeUpdate();
				*/
				choice.setMcName(sb.toString());
				choice.setId(pk);
				session.saveOrUpdate(choice);
				
			}
		}
			 
	}
	
	
	

}