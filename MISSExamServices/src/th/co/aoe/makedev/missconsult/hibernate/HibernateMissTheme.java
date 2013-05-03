package th.co.aoe.makedev.missconsult.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme;
import th.co.aoe.makedev.missconsult.managers.MissThemeService;
@Repository
@Transactional
public class HibernateMissTheme extends HibernateCommon implements MissThemeService {
//	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly=true)
	public List listMissTheme(MissTheme persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery("select missTheme from MissTheme missTheme order by missTheme.mtId asc ");
		return query.list();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public MissTheme findMissThemeById(Long maId,Long mtId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissTheme missTheme = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missTheme from MissTheme missTheme where missTheme.mtId=:mtId");
		query.setParameter("mtId", mtId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missTheme=(MissTheme)obj;
			query=session.createQuery("update MissAccount missAccount " +
					" set missAccount.missTheme.mtId =:mtId " +
					" where missAccount.maId ="+maId);
			query.setParameter("mtId", mtId);
			query.executeUpdate();
		}
	  return missTheme;
	}
}
