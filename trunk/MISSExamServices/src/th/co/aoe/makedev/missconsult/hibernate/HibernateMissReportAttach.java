package th.co.aoe.makedev.missconsult.hibernate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttachPK;
import th.co.aoe.makedev.missconsult.managers.MissReportAttachService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissReportAttach extends HibernateCommon implements
		MissReportAttachService {

	private static final Logger logger = Logger
			.getLogger(ServiceConstant.LOG_APPENDER);
	private static final String[] ignored = { "id","missSery" };
	// private static final SecureRandom random = new SecureRandom();
	private SessionFactory sessionAnnotationFactory;

	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}

	public void setSessionAnnotationFactory(
			SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}

	@Transactional(readOnly = true)
	public MissReportAttach findMissReportAttachById(Long msId, Long msOrder,
			String mraLang, String hotlink) throws DataAccessException {
		// TODO Auto-generated method stub
		MissReportAttach missReportAttach = null;
		Session session = sessionAnnotationFactory.getCurrentSession();
 
		Query query = session
				.createQuery(" select missReportAttach from MissReportAttach missReportAttach "
						+ " where missReportAttach.id.msId=:msId "
						+ " and missReportAttach.id.msOrder=:msOrder "
						+ " and missReportAttach.id.mraLang=:mraLang ");
						//+ " and missReportAttach.matHotlink=:matHotlink ");
		query.setParameter("msId", msId);
		query.setParameter("msOrder", msOrder);
		query.setParameter("mraLang", mraLang);
		//query.setParameter("matHotlink", hotlink);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		if (list.size() > 0) {
			missReportAttach = (MissReportAttach) list.get(0);
		}
		/*
		 * Object obj=query.uniqueResult(); if(obj!=null){
		 * missReportAttach=(MissReportAttach)obj; }
		 */
		return missReportAttach;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public Long saveMissReportAttach(MissReportAttach transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Long returnId = null;
		// 73gqqnghrkvfq202q6696gc35o
		// String big=new String(130, random).toString(32);
		try {
			Object obj = session.save(transientInstance);

			if (obj != null) {
				returnId = (Long) obj;
			}
		} finally {
			if (session != null) {
				session = null;
			}
		}
		return returnId;

	}

	/*
	 * private int getSize(Session session, MissReportAttach instance) throws
	 * Exception{ try {
	 * 
	 * return 0;
	 * 
	 * } catch (HibernateException re) { logger.error("HibernateException",re);
	 * throw re; } catch (Exception e) { logger.error("Exception",e); throw e; }
	 * }
	 */
	@SuppressWarnings({ "rawtypes" })
	@Transactional(readOnly = true)
	public List searchMissReportAttach(MissReportAttach instance,
			Pagging pagging) throws DataAccessException {
		ArrayList transList = new ArrayList();

		return transList;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public int updateMissReportAttach(MissReportAttach transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissReportAttach missReportAttach = null;
		Session session = sessionAnnotationFactory.getCurrentSession();

		Query query = session
				.createQuery(" select missReportAttach from MissReportAttach missReportAttach "
						+ " where missReportAttach.id.msId=:msId "
						+ " and missReportAttach.id.msOrder=:msOrder "
						+ " and missReportAttach.id.mraLang=:mraLang " +
						// " and missReportAttach.mraHotlink=:mraHotlink " +
						" ");
		query.setParameter("msId", transientInstance.getId().getMsId());
		query.setParameter("msOrder", transientInstance.getId().getMsOrder());
		query.setParameter("mraLang", transientInstance.getId().getMraLang());
		// query.setParameter("mraHotlink", transientInstance.getMraHotlink());
		@SuppressWarnings("rawtypes")
		List list = query.list();
		// System.out.println(" mraHotlink="+
		// transientInstance.getMraHotlink());
		// System.out.println(" attach size="+list.size());
		if (list.size() > 0) { 
			missReportAttach = (MissReportAttach) list.get(0);
			if(missReportAttach.getMraPath()!=null && missReportAttach.getMraPath().length()>0){
				 File file_delete=new File("/opt/attach/reportTemplate/"+missReportAttach.getMraPath().trim());
				 if(file_delete.exists())
					 file_delete.delete(); 
			}
			
			missReportAttach.setMraFileName(transientInstance.getMraFileName());
			missReportAttach.setMraHotlink(transientInstance.getMraHotlink());
			missReportAttach.setMraPath(transientInstance.getMraPath());
			missReportAttach.setMraReportName(transientInstance
					.getMraReportName());
			/*
			 * missReportAttach.setMatRef(Long.parseLong(id));
			 * missReportAttach.setMatModule(module);
			 */
			// BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);
			return update(session, missReportAttach);
		} else {
			Long returnId = null;
			MissReportAttachPK pk = null;
			try {
				Object obj = session.save(transientInstance);

				if (obj != null) {
					pk = (MissReportAttachPK) obj;
					returnId = pk.getMsOrder();
				}
			} finally {
				if (session != null) {
					session = null;
				}
			}
			return returnId.intValue();
		}

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public int deleteMissReportAttach(MissReportAttach persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(),
				persistentInstance);
	}

	@Override
	public List getTemplateMissReportAttach(Long msId,int numberReport)
			throws DataAccessException {
		// TODO Auto-generated method stub
		 

		Session session=sessionAnnotationFactory.getCurrentSession();
		List  xmissReportAttachS=
					new ArrayList(numberReport);
		for(int i=1;i<=numberReport;i++){
			List<th.co.aoe.makedev.missconsult.xstream.MissReportAttach>  xmissReportAttach=
					new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissReportAttach>(2);
		List<MissReportAttach>  missReportAttach_thai_list= listObject(session, "select missReportAttach from MissReportAttach missReportAttach where missReportAttach.id.msId="+msId+" " +
				" and missReportAttach.id.msOrder=" +i+" and missReportAttach.id.mraLang='0' "+
				" order by missReportAttach.id.msOrder , missReportAttach.id.mraLang ");
		List<MissReportAttach>  missReportAttach_eng_list= listObject(session, "select missReportAttach from MissReportAttach missReportAttach where missReportAttach.id.msId="+msId+" " +
				" and missReportAttach.id.msOrder=" +i+" and missReportAttach.id.mraLang='1' "+
				" order by missReportAttach.id.msOrder , missReportAttach.id.mraLang ");
		th.co.aoe.makedev.missconsult.xstream.MissReportAttach xmissReportAttach_thai=new th.co.aoe.makedev.missconsult.xstream.MissReportAttach();
		th.co.aoe.makedev.missconsult.xstream.MissReportAttach xmissReportAttach_eng=new th.co.aoe.makedev.missconsult.xstream.MissReportAttach();
		if(missReportAttach_thai_list!=null && missReportAttach_thai_list.size()>0){
			MissReportAttach missReportAttach_thai= missReportAttach_thai_list.get(0); 
			BeanUtils.copyProperties(missReportAttach_thai, xmissReportAttach_thai,ignored);
			xmissReportAttach_thai.setMraLang(missReportAttach_thai.getId().getMraLang());
			xmissReportAttach_thai.setMsId(missReportAttach_thai.getId().getMsId());
			xmissReportAttach_thai.setMsOrder(missReportAttach_thai.getId().getMsOrder());
		} 
			
		if(missReportAttach_eng_list!=null && missReportAttach_eng_list.size()>0){
			MissReportAttach missReportAttach_eng= missReportAttach_eng_list.get(0);
			BeanUtils.copyProperties(missReportAttach_eng, xmissReportAttach_eng,ignored);
			xmissReportAttach_eng.setMraLang(missReportAttach_eng.getId().getMraLang());
			xmissReportAttach_eng.setMsId(missReportAttach_eng.getId().getMsId());
			xmissReportAttach_eng.setMsOrder(missReportAttach_eng.getId().getMsOrder());
		} 
		xmissReportAttach.add(xmissReportAttach_thai);
		xmissReportAttach.add(xmissReportAttach_eng);
		xmissReportAttachS.add(xmissReportAttach);
		}
		return xmissReportAttachS;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	@Override
	public int deleteTemplateMissReportAttach(Long msId, Long msOrder)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession(); 
		Query query = session
				.createQuery(" delete MissReportAttach missReportAttach   "
						+ " where missReportAttach.id.msId=:msId "
						+ " and missReportAttach.id.msOrder=:msOrder " +
						// " and missReportAttach.mraHotlink=:mraHotlink " +
						" ");
		query.setParameter("msId", msId);
		query.setParameter("msOrder", msOrder); 
		query.executeUpdate();
		  query = session
				.createQuery(" select missReportAttach from MissReportAttach missReportAttach "
						+ " where missReportAttach.id.msId=:msId "
						+ " and missReportAttach.id.msOrder >  "+msOrder +
						// " and missReportAttach.mraHotlink=:mraHotlink " +
						" ");
		  query.setParameter("msId", msId); 
		
		// query.setParameter("mraHotlink", transientInstance.getMraHotlink()); 
		List<MissReportAttach> list = query.list();
		 // System.out.println("list size->"+list.size());
		 for (MissReportAttach missReportAttach : list) {
			 query = session
						.createQuery(" update MissReportAttach missReportAttach   "
								+ " set missReportAttach.id.msOrder="+(missReportAttach.getId().getMsOrder().intValue()-1)  
								+ " where missReportAttach.id.msId=:msId "
								+ " and missReportAttach.id.msOrder=:msOrder "
								+ " and missReportAttach.id.mraLang=:mraLang " + 
								" ");
			 	query.setParameter("msId", missReportAttach.getId().getMsId());
			 	query.setParameter("msOrder", missReportAttach.getId().getMsOrder());
			 	query.setParameter("mraLang", missReportAttach.getId().getMraLang()); 
				query.executeUpdate();
		}
		return 0;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	@Override
	public int updateReportNameMissReportAttach(Long msId, Long msOrder,String mraLang,String reportName)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession(); 
		Query   query = session
						.createQuery(" update MissReportAttach missReportAttach   "
								+ " set missReportAttach.mraReportName=:mraReportName"  
								+ " where missReportAttach.id.msId=:msId "
								+ " and missReportAttach.id.msOrder=:msOrder "
								+ " and missReportAttach.id.mraLang=:mraLang " + 
								" ");
	 		query.setParameter("mraReportName", reportName);
			 	query.setParameter("msId", msId);
			 	query.setParameter("msOrder", msOrder);
			 	query.setParameter("mraLang",mraLang); 
				query.executeUpdate();
		 
		return 0;
	}
	@Override
	public List getTemplateMissReportAttachForRole(Long msId,int numberReport)
			throws DataAccessException {
		// TODO Auto-generated method stub
		 

		Session session=sessionAnnotationFactory.getCurrentSession();
		List  xmissReportAttachS=
					new ArrayList();
		boolean have_attach_thai=false;
		boolean have_attach_eng=false;
		for(int i=1;i<=numberReport;i++){
			  have_attach_thai=false;
			  have_attach_eng=false;
			List<th.co.aoe.makedev.missconsult.xstream.MissReportAttach>  xmissReportAttach=
					new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissReportAttach>(2);
		List<MissReportAttach>  missReportAttach_thai_list= listObject(session, "select missReportAttach from MissReportAttach missReportAttach where missReportAttach.id.msId="+msId+" " +
				" and missReportAttach.id.msOrder=" +i+" and missReportAttach.id.mraLang='0' "+
				" order by missReportAttach.id.msOrder , missReportAttach.id.mraLang ");
		List<MissReportAttach>  missReportAttach_eng_list= listObject(session, "select missReportAttach from MissReportAttach missReportAttach where missReportAttach.id.msId="+msId+" " +
				" and missReportAttach.id.msOrder=" +i+" and missReportAttach.id.mraLang='1' "+
				" order by missReportAttach.id.msOrder , missReportAttach.id.mraLang ");
		th.co.aoe.makedev.missconsult.xstream.MissReportAttach xmissReportAttach_thai=new th.co.aoe.makedev.missconsult.xstream.MissReportAttach();
		th.co.aoe.makedev.missconsult.xstream.MissReportAttach xmissReportAttach_eng=new th.co.aoe.makedev.missconsult.xstream.MissReportAttach();
		if(missReportAttach_thai_list!=null && missReportAttach_thai_list.size()>0){
			MissReportAttach missReportAttach_thai= missReportAttach_thai_list.get(0); 
			BeanUtils.copyProperties(missReportAttach_thai, xmissReportAttach_thai,ignored);
			xmissReportAttach_thai.setMraLang(missReportAttach_thai.getId().getMraLang());
			xmissReportAttach_thai.setMsId(missReportAttach_thai.getId().getMsId());
			xmissReportAttach_thai.setMsOrder(missReportAttach_thai.getId().getMsOrder());
			have_attach_thai=true;
		} 
			
		if(missReportAttach_eng_list!=null && missReportAttach_eng_list.size()>0){
			MissReportAttach missReportAttach_eng= missReportAttach_eng_list.get(0);
			BeanUtils.copyProperties(missReportAttach_eng, xmissReportAttach_eng,ignored);
			xmissReportAttach_eng.setMraLang(missReportAttach_eng.getId().getMraLang());
			xmissReportAttach_eng.setMsId(missReportAttach_eng.getId().getMsId());
			xmissReportAttach_eng.setMsOrder(missReportAttach_eng.getId().getMsOrder());
			have_attach_eng=true;
		} 
		if(have_attach_thai || have_attach_eng){
			xmissReportAttach.add(xmissReportAttach_thai);
			xmissReportAttach.add(xmissReportAttach_eng);
			xmissReportAttachS.add(xmissReportAttach);
		} 
		}
		return xmissReportAttachS;
	}
}
