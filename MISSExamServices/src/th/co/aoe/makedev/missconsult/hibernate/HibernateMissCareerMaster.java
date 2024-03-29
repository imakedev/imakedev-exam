package th.co.aoe.makedev.missconsult.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.managers.MissCareerMasterService;

@Repository
@Transactional
public class HibernateMissCareerMaster  extends HibernateCommon implements MissCareerMasterService {

	//private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	//private static final SecureRandom random = new SecureRandom();
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true)
	@Override
	public List listMissCareerMaster(Long mcmRef) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=null;
		List list=null;
		if(mcmRef!=null && mcmRef.intValue()!=0){
			
			 query=session.createQuery(" select master from MissCareerMapping mapping , MissCareerMaster master " +
					" where mapping.id.mcmId = master.mcmId and mapping.id.maId="+mcmRef.intValue());
			 list=query.list();
			 if(list.size()==0){
				 query=session.createQuery(" select missCareerMaster from MissCareerMaster missCareerMaster where " +
							"( missCareerMaster.mcmRef is null or  missCareerMaster.mcmRef !=1 )");
					list=query.list();
			 }
				// list=null;
		}else{ 
			query=session.createQuery(" select missCareerMaster from MissCareerMaster missCareerMaster where " +
					"( missCareerMaster.mcmRef is null or  missCareerMaster.mcmRef !=1 )");
			list=query.list();
		}
		
		return list;
	}

}
