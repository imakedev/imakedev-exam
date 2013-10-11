package th.co.aoe.makedev.missconsult.hibernate;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissContact;
import th.co.aoe.makedev.missconsult.hibernate.bean.UserContact;
import th.co.aoe.makedev.missconsult.managers.MissContactService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissContact extends HibernateCommon implements MissContactService {

	//private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	//private static final SecureRandom random = new SecureRandom();
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissContact findMissContactById(Long mcontactId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissContact missContact = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missContact from MissContact missContact where missContact.mcontactId=:mcontactId");
		query.setParameter("mcontactId", mcontactId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missContact=(MissContact)obj;
		}
	  return missContact;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissContact(MissContact transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
		//String password=new BigInteger(40, random).toString(32);
		//73gqqnghrkvfq202q6696gc35o
		//String big=new String(130, random).toString(32);
		try{
			Object obj = session.save(transientInstance);
		
			if(obj!=null){
				returnId =(Long) obj;
				/*Query query=session.createQuery("update MissContact missContact " +
						" set missContact.mcaUsername =:mcaUsername , " +
						" missContact.mcaStatus ='2' ," +
						" missContact.mcaPassword ='"+password+"' " +
						" where missContact.mcaId ="+returnId);
				query.setParameter("mcaUsername", "MCA0000"+returnId);
				query.executeUpdate();*/
			}
			String password=transientInstance.getMcontactPassword();
			MessageDigest mda=null;
			try {
				mda = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte [] digesta = mda.digest(password.getBytes());

			password=new String(Hex.encodeHex(digesta));
			UserContact userContact=new UserContact();
			userContact.setUsername(transientInstance.getMcontactUsername());
			userContact.setMcontactUsername(transientInstance.getMcontactUsername());
			userContact.setPassword(password);
			userContact.setMcontactId(returnId);
			session.save(userContact);
		} finally {
				if (session != null) {
					session = null;
				} 
		}
		return returnId; 
		
		
	} 
/*
	private int getSize(Session session, MissContact instance) throws Exception{
		try {
		 
				 return 0;
		 
		} catch (HibernateException re) {
			logger.error("HibernateException",re);
			throw re;
		} catch (Exception e) {
			logger.error("Exception",e);
			throw e;
		}
	}*/
	 @SuppressWarnings({ "rawtypes" })
	 @Transactional(readOnly=true)
	 public List searchMissContact(MissContact instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
		 
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissContact(MissContact transientInstance,String section)
			throws DataAccessException {
		Session session=sessionAnnotationFactory.getCurrentSession();
		int canUpdate = 0;
			try{
				//session.createQuery("select userContact UserContact userContact")
				/*  Query query =session.createQuery("select userContact from UserContact userContact  " +
					 		"where userContact.mcontactId=:mcontactId "); 
				  query.setParameter("mcontactId",transientInstance.getMcontactId());
				  Object obj =query.uniqueResult();
				  UserContact contact=null;
				  if(obj!=null)
					  contact=(UserContact)obj;
				  //contact.getId()
				    List<MissExam> missExams= (List<MissExam>)query.qlist();*/
					 
				    
				
				session.update(transientInstance);
				 Query query=session.createQuery("update UserContact userContact " +
						" set userContact.password =:password " +
						//" where userContact.username =:username");
						" , userContact.mcontactUsername =:mcontactUsername " +
						" , userContact.username =:username " +
						 " where userContact.mcontactId =:mcontactId");
				query.setParameter("username",transientInstance.getMcontactUsername());
				query.setParameter("mcontactUsername",transientInstance.getMcontactUsername());
				  query.setParameter("mcontactId",transientInstance.getMcontactId());
				String password = transientInstance.getMcontactPassword();

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
				query.executeUpdate();
				canUpdate =1;
				}catch (Exception e) {
					// TODO: handle exception
				} finally {
					if (session != null) {
						session = null;
					} 
				}
				return canUpdate;
		//return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissContactPhoto(MissContact transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query  query = session
				.createQuery(" select missContact from MissContact missContact "+
						" where missContact.mcontactId ="+transientInstance.getMcontactId());
				List list = query.list();
				if (list.size() > 0) { 
					MissContact missContact = (MissContact) list.get(0);
					if(missContact.getMcontactPicturePath()!=null && missContact.getMcontactPicturePath().length()>0){
						 File file_delete=new File("/opt/attach/contactImg/"+missContact.getMcontactPicturePath().trim());
						 if(file_delete.exists())
							 file_delete.delete(); 
					} 
				}
		
			query=session.createQuery("update MissContact missContact " +
					" set missContact.mcontactPicturePath =:mcontactPicturePath," +
					" missContact.mcontactPictureFileName =:mcontactPictureFileName ," +
					" missContact.mcontactPictureHotlink =:mcontactPictureHotlink " +
					" where missContact.mcontactId ="+transientInstance.getMcontactId());
			query.setParameter("mcontactPicturePath", transientInstance.getMcontactPicturePath());
			query.setParameter("mcontactPictureFileName", transientInstance.getMcontactPictureFileName());
			query.setParameter("mcontactPictureHotlink", transientInstance.getMcontactPictureHotlink());
			return query.executeUpdate();
	}

	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissContact(MissContact persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		String username="";
		int canUpdate = 0;
		try{
			Query query=session.createQuery(" select missContact from MissContact missContact where missContact.mcontactId=:mcontactId");
			query.setParameter("mcontactId", persistentInstance.getMcontactId());
			Object obj=query.uniqueResult();
			if(obj!=null){
				MissContact missContact=(MissContact)obj;
				username=missContact.getMcontactUsername();
				session.delete(missContact);
			}
			
		
		
	   if(username!=null && username.length()>0){
		   query=session.createQuery("delete UserContact user where user.username =:username");
		   query.setParameter("username",username);
			//int result = query.executeUpdate();
		    query.executeUpdate();
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
	@SuppressWarnings("rawtypes")
	@Override
	public List listContacts(Long long1, String mcontactType)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missContact from MissContact missContact where missContact.mcontactRef=:mcontactRef and " +
				" missContact.mcontactType=:mcontactType ");
		query.setParameter("mcontactRef", long1);
		query.setParameter("mcontactType", mcontactType);
		return query.list(); 
	}
	@Override
	public th.co.aoe.makedev.missconsult.xstream.MissContact findMissContactByUsername(String username)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissContact missContact = null;
		th.co.aoe.makedev.missconsult.xstream.MissContact xntcCalendarReturn=null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missContact from MissContact missContact where missContact.mcontactUsername=:mcontactUsername");
		query.setParameter("mcontactUsername", username);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missContact=(MissContact)obj;
			 xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissContact();
			BeanUtils.copyProperties(missContact,xntcCalendarReturn);
			 query=session.createQuery(" select missAccount from MissAccount missAccount where missAccount.maId=:maId");
				query.setParameter("maId", missContact.getMcontactRef());
				obj=query.uniqueResult(); 	
				String isMC="0";
				if(obj!=null){
					th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount missAccount=
							(th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount)obj;
					isMC=missAccount.getMaType();
					xntcCalendarReturn.setMaCustomizeLogoPath(missAccount.getMaCustomizeLogoHotlink());
					//missAccount.setmaCustomizeLogoMCPath; // for default
					 query=session.createQuery(" select missAccount from MissAccount missAccount where missAccount.maId=1");
					 obj=query.uniqueResult(); 
					 if(obj!=null){
						 th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount mc=
									(th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount)obj;
							xntcCalendarReturn.setMaCustomizeLogoMCPath(mc.getMaCustomizeLogoHotlink());
					 }
					xntcCalendarReturn.setMaCustomizeHeadColor(missAccount.getMaCustomizeHeadColor());
					xntcCalendarReturn.setMaCustomizeColor(missAccount.getMaCustomizeColor());
					xntcCalendarReturn.setMaBackgroundColor(missAccount.getMaBackgroundColor());
					xntcCalendarReturn.setMaGrade(missAccount.getMaGrade());
					th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme missTheme=null;
					th.co.aoe.makedev.missconsult.xstream.MissTheme xmissTheme=new th.co.aoe.makedev.missconsult.xstream.MissTheme();
					if(missAccount.getMissTheme()==null){
						query=session.createQuery(" select missTheme from MissTheme missTheme where missTheme.mtId=1");
						 obj=query.uniqueResult(); 
						 if(obj!=null){
							 missTheme=	(th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme)obj;
						 }
					}else{
						missTheme=missAccount.getMissTheme(); 
					}
					 BeanUtils.copyProperties(missTheme,xmissTheme);
					 xntcCalendarReturn.setMissTheme(xmissTheme);
				}
			xntcCalendarReturn.setIsMC(isMC);
		}
	  return xntcCalendarReturn;
	}
	@Override
	public int countMissContactByUsername(String username,Long id)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery("select count(missContact) from MissContact missContact " +
				" where missContact.mcontactUsername=:mcontactUsername " +
				((id!=null)?"and missContact.mcontactId!=:mcontactId":"")+
				""); 
		query.setParameter("mcontactUsername", username);
		if(id!=null)
			query.setParameter("mcontactId", id);
		return ((Long)query.uniqueResult()).intValue();
	}
	 

 
}
