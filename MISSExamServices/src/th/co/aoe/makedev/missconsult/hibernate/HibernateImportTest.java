package th.co.aoe.makedev.missconsult.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptCareer;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptMessageConfig;
import th.co.aoe.makedev.missconsult.managers.ImportTestService;
@Repository
@Transactional
public class HibernateImportTest implements ImportTestService {
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Override
	public Long saveMissEptCareer(MissEptCareer missEptCareer)
			throws DataAccessException {
		// TODO Auto-generated method stub
		sessionAnnotationFactory.getCurrentSession().saveOrUpdate(missEptCareer);
		return null;
	}

	@Override
	public Long saveMissEptMessageConfig(
			MissEptMessageConfig missEptMessageConfig)
			 {
		// TODO Auto-generated method stub
		try {
			sessionAnnotationFactory.getCurrentSession().saveOrUpdate(missEptMessageConfig);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(missEptMessageConfig.getId().getCode());
		}
		
		return null;
	}

}
