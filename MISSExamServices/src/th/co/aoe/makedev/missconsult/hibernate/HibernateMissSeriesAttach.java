package th.co.aoe.makedev.missconsult.hibernate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.managers.MissSeriesAttachService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissSeriesAttach  extends HibernateCommon implements MissSeriesAttachService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissSeriesAttach(MissSeriesAttach transientInstance)
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
	
	

	private int getSize(Session session, MissSeriesAttach instance) throws Exception{
		try {
			 
			StringBuffer sb =new StringBuffer(" select count(missSeriesAttach) from MissSeriesAttach missSeriesAttach ");
			
			Long msatId = instance.getMsatId();
			Long msatRef1=instance.getMsatRef1();
			Long msatRef2=instance.getMsatRef2();
			String msatModule=instance.getMsatModule();
			String msatHotlink=instance.getMsatHotlink();
			
			boolean iscriteria = false;
			if(msatId !=null && msatId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeriesAttach.msatId="+msatId+""):(" where missSeriesAttach.msatId="+msatId+""));
				  iscriteria = true;
			}
			if(msatRef1 !=null && msatRef1 > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeriesAttach.msatRef1="+msatRef1+""):(" where missSeriesAttach.msatRef1="+msatRef1+""));
				  iscriteria = true;
			}
			if(msatRef2 !=null && msatRef2 > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeriesAttach.msatRef2="+msatRef2+""):(" where missSeriesAttach.msatRef2="+msatRef2+""));
				  iscriteria = true;
			}
			if(msatModule !=null && msatModule.length()> 0 ){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeriesAttach.msatModule='"+msatModule+"'"):(" where missSeriesAttach.msatModule='"+msatModule+"'"));
				  iscriteria = true;
			}if(msatHotlink !=null && msatHotlink.length()> 0 ){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeriesAttach.msatHotlink='"+msatHotlink+"'"):(" where missSeriesAttach.msatHotlink='"+msatHotlink+"'"));
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
	 public List searchMissSeriesAttach(MissSeriesAttach instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				Long msatId = instance.getMsatId();
				Long msatRef1=instance.getMsatRef1();
				Long msatRef2=instance.getMsatRef2();
				String msatModule=instance.getMsatModule();
				String msatHotlink=instance.getMsatHotlink();
				StringBuffer sb =new StringBuffer(" select missSeriesAttach from MissSeriesAttach missSeriesAttach ");
				
				boolean iscriteria = false;
				if(msatId !=null && msatId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeriesAttach.msatId="+msatId+""):(" where missSeriesAttach.msatId="+msatId+""));
					  iscriteria = true;
				}
				if(msatRef1 !=null && msatRef1 > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeriesAttach.msatRef1="+msatRef1+""):(" where missSeriesAttach.msatRef1="+msatRef1+""));
					  iscriteria = true;
				}
				if(msatRef2 !=null && msatRef2 > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeriesAttach.msatRef2="+msatRef2+""):(" where missSeriesAttach.msatRef2="+msatRef2+""));
					  iscriteria = true;
				}
				if(msatModule !=null && msatModule.length()> 0 ){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeriesAttach.msatModule='"+msatModule+"'"):(" where missSeriesAttach.msatModule='"+msatModule+"'"));
					  iscriteria = true;
				}if(msatHotlink !=null && msatHotlink.length()> 0 ){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeriesAttach.msatHotlink='"+msatHotlink+"'"):(" where missSeriesAttach.msatHotlink='"+msatHotlink+"'"));
					  iscriteria = true;
				}

				
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missSeriesAttach."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				//sb.append(" order by missSeriesAttach.msmOrder asc ");
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
	public int updateMissSeriesAttach(MissSeriesAttach transientInstance,String rootPath)
			throws DataAccessException {
		// TODO Auto-generated method stub
		int returnRecord=0;
		Session session=null;
		try{
		String query_ref2="";
		if(transientInstance.getMsatRef2()!=null && transientInstance.getMsatRef2().intValue()!=0){
			query_ref2=" and missSeriesAttach.msatRef2=:msatRef2 " ;
		}
		MissSeriesAttach missSeriesAttach = null;
		session=sessionAnnotationFactory.getCurrentSession();
		
		Query query=session.createQuery(" select missSeriesAttach from MissSeriesAttach missSeriesAttach " +
				" where missSeriesAttach.msatModule=:msatModule " +
				" and missSeriesAttach.msatRef1=:msatRef1 " +
				query_ref2+
				")");
		query.setParameter("msatModule", transientInstance.getMsatModule());
		query.setParameter("msatRef1", transientInstance.getMsatRef1());
		if(query_ref2.length()>0)
			query.setParameter("msatRef2", transientInstance.getMsatRef2());
		List list=query.list();
		logger.debug(" attach size="+list.size());
		if(list.size()>0){
			missSeriesAttach=(MissSeriesAttach)list.get(0);
			 missSeriesAttach.setMsatFileName(transientInstance.getMsatFileName());
			 missSeriesAttach.setMsatHotlink(transientInstance.getMsatHotlink());
			 missSeriesAttach.setMsatPath(transientInstance.getMsatPath());
			 returnRecord =update(session, missSeriesAttach);
		}else{
			Long returnId  = null;
			
				Object obj = session.save(transientInstance);
			
				if(obj!=null){
					returnId =(Long) obj;
				}
			returnRecord= returnId.intValue(); 
		 }
		if(transientInstance.getMsatModule()!=null && transientInstance.getMsatModule().equals("evaluation")){
			setConfig(session,rootPath+transientInstance.getMsatPath(),transientInstance.getMsatRef1(),transientInstance.getMsatRef2());
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session = null;
			} 
		}
		
		return returnRecord;
	}
	public void setConfig(Session session,String filename,Long msId,Long meId){ 
    	FileInputStream fileIn = null;
        //FileOutputStream fileOut = null;
        try
        {
            try {
				fileIn = new FileInputStream(filename);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            POIFSFileSystem fs = null;
			try {
				fs = new POIFSFileSystem(fileIn);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Workbook wb=null;
			try {
				wb = new HSSFWorkbook(fs);
				//wb = new XSSFWorkbook(fs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        //  Sheet sheet1 = wb.getSheetAt(0);          // getConfig
        //  Row row_code= sheet1.getRow(1);
         // Cell cell_code  =row_code.getCell(0);
        //  String columnReference=cell_code.getStringCellValue();
		//	Sheet  sheet1 = wb.getSheetAt(1); //get Code
         // CellReference cr = new CellReference(columnReference);
       /*   row_code= sheet1.getRow(cr.getRow());
          cell_code  =row_code.getCell(cr.getCol());*/
          
          NumberFormat    format  =    NumberFormat.getNumberInstance();
          format.setGroupingUsed(false);
          //get config
          Sheet sheet1_0 = wb.getSheetAt(0);
          int endRow=sheet1_0.getLastRowNum();
          Row r=null;
          List < th.co.aoe.makedev.missconsult.hibernate.bean.MissEvaluationConfig> missEvaluationConfigs=
        		  new ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissEvaluationConfig>();
          int order=1;
          StringBuffer sb=new StringBuffer();
          for(int i=7;i<=endRow;i++){
        	 r= sheet1_0.getRow(i); 
        	 sb.setLength(0);
        	 if(r.getCell(2).getBooleanCellValue()){  // 1=true,0=false;
        		 sb.append("1");
        	 }else
        		 sb.append("0");
        	 th.co.aoe.makedev.missconsult.hibernate.bean.MissEvaluationConfig  missEvaluationConfig =new th.co.aoe.makedev.missconsult.hibernate.bean.MissEvaluationConfig();
        	 th.co.aoe.makedev.missconsult.hibernate.bean.MissEvaluationConfigPK pk =new th.co.aoe.makedev.missconsult.hibernate.bean.MissEvaluationConfigPK();
        	// pk.setMcaId(mcaId);
        	 pk.setMsId(msId);
        	 //pk.setMeId(meId);
        	 pk.setMecType("2");
         	 missEvaluationConfig.setMecOrder(Long.valueOf(order++));
         	 missEvaluationConfig.setColumnIsShow(sb.toString());	
         	 pk.setColumnCode(r.getCell(0).getStringCellValue());  
         	 missEvaluationConfig.setColumnName(r.getCell(1).getStringCellValue());
         	 /* CellReference cr2 = new CellReference(r.getCell(1).getStringCellValue());
         	  row_code= sheet1_1.getRow(cr2.getRow());
              cell_code  =row_code.getCell(cr2.getCol());*/
              
             
           /*   missEvaluationConfig.setMtsValue(format.format(cell_code.getNumericCellValue()));*/
              missEvaluationConfig.setId(pk);
              missEvaluationConfigs.add(missEvaluationConfig);
          }	 
          Query query=session.createQuery("delete MissEvaluationConfig missEvaluationConfig " + 
					" where  " +
				//" missEvaluationConfig.id.meId=:meId and " +
					" missEvaluationConfig.id.msId=:msId and " +
					" missEvaluationConfig.id.mecType=:mecType ");
			//query.setParameter("mcaId", mcaId); 
			//query.setParameter("meId", meId);
			query.setParameter("msId", msId);
			query.setParameter("mecType", "2");
			query.executeUpdate();
			for (th.co.aoe.makedev.missconsult.hibernate.bean.MissEvaluationConfig missEvaluationConfig : missEvaluationConfigs) {
				session.save(missEvaluationConfig);
			}
        //  System.out.println(columnReference);
          
     /*     row_code= sheet1.getRow(2);
          cell_code  =row_code.getCell(4);
          NumberFormat    format  =    NumberFormat.getNumberInstance();
          // format.setMaximumIntegerDigits(99);
           format.setGroupingUsed(false);
          System.out.println(""+(format.format(cell_code.getNumericCellValue())));*/
         
        } finally {
           
            if (fileIn != null)
				try {
					fileIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissSeriesAttach(MissSeriesAttach persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 
	 


}
