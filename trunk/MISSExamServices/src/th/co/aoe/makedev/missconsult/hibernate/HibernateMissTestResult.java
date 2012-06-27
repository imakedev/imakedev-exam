package th.co.aoe.makedev.missconsult.hibernate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
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
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissExam;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult;
import th.co.aoe.makedev.missconsult.managers.MissTestResultService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissTestResult  extends HibernateCommon implements MissTestResultService {
	private static String schema="";
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	//private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static ResourceBundle bundle;
	static{
		bundle =  ResourceBundle.getBundle( "jdbc" );	
		schema=bundle.getString("schema");
	}
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissTestResult findMissTestResultById(Long mtrId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissTestResult missTestResult = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.mtrId=:mtrId");
		query.setParameter("mtrId", mtrId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missTestResult=(MissTestResult)obj;
		}
	  return missTestResult;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissTestResult(MissTestResult transientInstance)
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
	
	

	private int getSize(Session session, MissTestResult instance) throws Exception{
		try {
			Long msId=instance.getMsId();
			MissCandidate missCandidate=instance.getMissCandidate();
			String mcaUsername=missCandidate.getMcaUsername();
			String mcaFirstName=missCandidate.getMcaFirstName();
			String mcaLastName=missCandidate.getMcaLastName();
			String mcaPosition=missCandidate.getMcaPosition();
			String mcaDepartMent=missCandidate.getMcaDepartment();
			String mtrStartTime="";
					if(instance.getMtrStartTime()!=null){
						mtrStartTime=format.format(instance.getMtrStartTime());
					}
			String mtrEndTime="";
					if(instance.getMtrEndTime()!=null){
						mtrEndTime=format.format(instance.getMtrEndTime());
					}
		
			
			String maName=missCandidate.getMissAccount().getMaName();
			 
					 
			//StringBuffer sb =new StringBuffer(" select count(missTestResult) from MissTestResult missTestResult ");
		
			StringBuffer sb =new StringBuffer("select count(*) from ( select result.MTR_ID,result.MCA_ID,result.MS_ID,result.ME_ID,result.MTR_TEST_DATE," +
					" result.MTR_START_TIME,result.MTR_END_TIME,result.MTR_STATUS," +
					" result.MTR_RESULT_CODE,candidate.MCA_USERNAME,candidate.MCA_FIRST_NAME,candidate.MCA_LAST_NAME, " +
					" candidate.MCA_POSITION ,candidate.MCA_DEPARTMENT ,account.MA_NAME from " +
					" "+schema+".MISS_TEST_RESULT  as result left join " +
					" "+schema+".MISS_CANDIDATE candidate on result.MCA_ID=candidate.MCA_ID " +
					" left join "+schema+".MISS_ACCOUNT  account on candidate.MA_ID=account.MA_ID" +
					"  ");
			
			boolean iscriteria = false;
			if(msId !=null && msId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and result.MS_ID="+msId+""):(" where result.MS_ID="+msId+""));
				  iscriteria = true;
			}
			if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and LOWER(candidate.MCA_USERNAME) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_USERNAME) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
				 iscriteria = true;
			}
			if(mcaFirstName !=null && mcaFirstName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and LOWER(candidate.MCA_FIRST_NAME) like '%"+mcaFirstName.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_FIRST_NAME) like '%"+mcaFirstName.trim().toLowerCase()+"%'"));
				 iscriteria = true;
			}
			if(mcaLastName !=null && mcaLastName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and LOWER(candidate.MCA_LAST_NAME) like '%"+mcaLastName.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_LAST_NAME) like '%"+mcaLastName.trim().toLowerCase()+"%'"));
				 iscriteria = true;
			}
			if(mcaPosition !=null && mcaPosition.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and LOWER(candidate.MCA_POSITION) like '%"+mcaPosition.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_POSITION) like '%"+mcaPosition.trim().toLowerCase()+"%'"));
				 iscriteria = true;
			}
			if(mcaDepartMent !=null && mcaDepartMent.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and LOWER(candidate.MCA_DEPARTMENT) like '%"+mcaDepartMent.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_DEPARTMENT) like '%"+mcaDepartMent.trim().toLowerCase()+"%'"));
				 iscriteria = true;
			}
			if(maName !=null && maName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and LOWER(account.MA_NAME) like '%"+maName.trim().toLowerCase()+"%'"):(" where LOWER(account.MA_NAME) like '%"+maName.trim().toLowerCase()+"%'"));
				 iscriteria = true;
			}
			if(mtrStartTime.length()>0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and result.MTR_START_TIME > '"+mtrStartTime.trim()+"'"):(" where result.MTR_START_TIME > '"+mtrStartTime.trim()+"'"));
				 iscriteria = true;
			}
			if(mtrEndTime.length()>0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and result.MTR_END_TIME < '"+mtrEndTime.trim()+"'"):(" where result.MTR_END_TIME < '"+mtrEndTime.trim()+"'"));
				 iscriteria = true;
			}
			sb.append("  ) as x ");
			Query query =session.createSQLQuery(sb.toString());
				 return ((java.math.BigInteger)query.uniqueResult()).intValue();
			//return 0;
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
	 public List searchMissTestResult(MissTestResult instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				/*Long megId = instance.getMegId();
				String megName = instance.getMegName();
			*/
				Long msId=instance.getMsId();
				MissCandidate missCandidate=instance.getMissCandidate();
				String mcaUsername=missCandidate.getMcaUsername();
				String mcaFirstName=missCandidate.getMcaFirstName();
				String mcaLastName=missCandidate.getMcaLastName();
				String mcaPosition=missCandidate.getMcaPosition();
				String mcaDepartMent=missCandidate.getMcaDepartment();
				String mtrStartTime="";
						if(instance.getMtrStartTime()!=null){
							mtrStartTime=format.format(instance.getMtrStartTime());
						}
				String mtrEndTime="";
						if(instance.getMtrEndTime()!=null){
							mtrEndTime=format.format(instance.getMtrEndTime());
						}
			
				
				String maName=missCandidate.getMissAccount().getMaName();
			
				StringBuffer sb =new StringBuffer(" select result.MTR_ID,result.MCA_ID,result.MS_ID,result.ME_ID,result.MTR_TEST_DATE," +
						" result.MTR_START_TIME,result.MTR_END_TIME,result.MTR_STATUS," +
						" result.MTR_RESULT_CODE,candidate.MCA_USERNAME,candidate.MCA_FIRST_NAME,candidate.MCA_LAST_NAME, " +
						" candidate.MCA_POSITION ,candidate.MCA_DEPARTMENT ,account.MA_NAME from " +
						" "+schema+".MISS_TEST_RESULT  as result left join " +
						" "+schema+".MISS_CANDIDATE candidate on result.MCA_ID=candidate.MCA_ID " +
						" left join "+schema+".MISS_ACCOUNT  account on candidate.MA_ID=account.MA_ID" +
						"  ");
				/*
				 * where result.MCA_ID=21 and result.MTR_START_TIME > '2012-06-20'
 and result.MTR_START_TIME < '2012-06-20 23:59:59'
				 */ 
				//SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				boolean iscriteria = false;
				if(msId !=null && msId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and result.MS_ID="+msId+""):(" where result.MS_ID="+msId+""));
					  iscriteria = true;
				}
				if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and LOWER(candidate.MCA_USERNAME) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_USERNAME) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}
				if(mcaFirstName !=null && mcaFirstName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and LOWER(candidate.MCA_FIRST_NAME) like '%"+mcaFirstName.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_FIRST_NAME) like '%"+mcaFirstName.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}
				if(mcaLastName !=null && mcaLastName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and LOWER(candidate.MCA_LAST_NAME) like '%"+mcaLastName.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_LAST_NAME) like '%"+mcaLastName.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}
				if(mcaPosition !=null && mcaPosition.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and LOWER(candidate.MCA_POSITION) like '%"+mcaPosition.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_POSITION) like '%"+mcaPosition.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}
				if(mcaDepartMent !=null && mcaDepartMent.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and LOWER(candidate.MCA_DEPARTMENT) like '%"+mcaDepartMent.trim().toLowerCase()+"%'"):(" where LOWER(candidate.MCA_DEPARTMENT) like '%"+mcaDepartMent.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}
				if(maName !=null && maName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and LOWER(account.MA_NAME) like '%"+maName.trim().toLowerCase()+"%'"):(" where LOWER(account.MA_NAME) like '%"+maName.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}
				if(mtrStartTime.length()>0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and result.MTR_START_TIME > '"+mtrStartTime.trim()+"'"):(" where result.MTR_START_TIME > '"+mtrStartTime.trim()+"'"));
					 iscriteria = true;
				}
				if(mtrEndTime.length()>0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and result.MTR_END_TIME < '"+mtrEndTime.trim()+"'"):(" where result.MTR_END_TIME < '"+mtrEndTime.trim()+"'"));
					 iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missTestResult."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}		
				logger.debug("sb ========================== >"+sb.toString());
				Query query =session.createSQLQuery(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session, instance)); 
				 logger.info(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
				 query.setFirstResult(pagging.getPageSize() * (pagging.getPageNo() - 1));
				 query.setMaxResults(pagging.getPageSize());
				 List result = query.list();
				 logger.debug(" result ========================== >"+result);
					int size_result = result.size();
					for (int j = 0; j < size_result; j++) {
						Object[] obj = (Object[]) result.get(j);
						th.co.aoe.makedev.missconsult.xstream.MissTestResult missTestResult = new th.co.aoe.makedev.missconsult.xstream.MissTestResult();
						th.co.aoe.makedev.missconsult.xstream.MissCandidate candidate =new th.co.aoe.makedev.missconsult.xstream.MissCandidate();
						candidate.setMcaUsername(obj[9] != null ? obj[9] + "" : "");
						candidate.setMcaFirstName(obj[10] != null ? obj[10] + "" : "");
						candidate.setMcaLastName(obj[11] != null ? obj[11] + "" : "");
						candidate.setMcaPosition(obj[12] != null ? obj[12] + "" : "");
						candidate.setMcaDepartment(obj[13] != null ? obj[13] + "" : "");
						missTestResult.setMtrTestDate(obj[4] != null ?(java.sql.Date)obj[4]:null);
						missTestResult.setMtrResultCode(obj[8] != null ? obj[8] + "" : "");
						missTestResult.setMtrStatus(obj[7] != null ? obj[7] + "" : "");
						candidate.setPagging(null);
						missTestResult.setMissCandidate(candidate);
						missTestResult.setPagging(null);
						
						List<String> axisValues = new ArrayList<String>(4);
						axisValues.add("?");
						axisValues.add("?");
						axisValues.add("?");
						axisValues.add("?");
						missTestResult.setAxisValues(axisValues);
						
						/*missTestResult.setm
						ownerresultDTO.setKpiOwnerKey(obj[0] != null ? new BigDecimal(
								obj[0] + "") : null);
						ownerresultDTO.setKpiOwnerName(obj[1] != null ? obj[1] + "" : "");*/
						result.set(j, missTestResult);
					}
				// List l = query.list();   
				 transList.add(result); 
			 	 transList.add(size); 
				return transList;
			} catch (Exception re) {
				//re.printStackTrace();
				re.printStackTrace();
				logger.error("find by property name failed", re);
				 
			}
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissTestResult(MissTestResult transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissTestResult(MissTestResult persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Override
	public int processMissTestResult(MissTestResult persistentInstance,String userid,String rootPath) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		int returnRecord=0;
		try{
		Long meId=persistentInstance.getMeId();
		Long msId=persistentInstance.getMsId();
		session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj=query.uniqueResult(); 	 
		MissCandidate missCandidate=null;
		Long mcaId=null; 
		if(obj!=null){		
			missCandidate=(MissCandidate)obj;
			mcaId=missCandidate.getMcaId();
			//logger.debug("xxxxxxxxxx="+missCandidate.getMcaId().intValue());
			query=session.createQuery(" select missSeriesAttach from MissSeriesAttach missSeriesAttach where missSeriesAttach.msatRef1=:msatRef1" +
					" and missSeriesAttach.msatRef2=:msatRef2 and missSeriesAttach.msatModule=:msatModule");
			query.setParameter("msatRef1", msId);
			query.setParameter("msatRef2", meId);
			query.setParameter("msatModule", "evaluation");
			obj=query.uniqueResult();
			if(obj!=null){
				th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach missSeriesAttach = 
						(th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach)obj;
				missSeriesAttach.getMsatFileName();
				 String filePath=rootPath+missSeriesAttach.getMsatPath();
				  String pathOutPut= setAnswer(session,filePath,msId,meId,mcaId);
				  String code=getCode(pathOutPut);
				  query=session.createQuery("update MissTestResult missTestResult " +
							" set missTestResult.mtrResultCode =:mtrResultCode " +
							 
							" where missTestResult.missCandidate.mcaId=:mcaId and " +
							" missTestResult.meId=:meId and " +
							" missTestResult.msId=:msId ");
					query.setParameter("mcaId", mcaId); 
					query.setParameter("meId", meId);
					query.setParameter("msId", msId);
					query.setParameter("mtrResultCode", code);
					returnRecord=query.executeUpdate();
			}
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if (session != null) {
				session = null;
			} 
	}
		return returnRecord;
	}
	private String setAnswer(Session session ,String filePath,Long msId,Long meId,Long mcaId){
		//หนังสือยินยอมสละสิทธิ์การโอนรถยนต์คันแรกภายใน
		  FileInputStream fileIn = null;
	        FileOutputStream fileOut = null;
	        String[] extensions=filePath.split("\\.");
	        String outPut="";
	        try
	        {
	            try {
					fileIn = new FileInputStream(filePath);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            POIFSFileSystem fs=null;
				try {
					fs = new POIFSFileSystem(fileIn);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            HSSFWorkbook wb=null;
				try {
					wb = new HSSFWorkbook(fs);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*Workbook wb=null;
				try {
					wb = WorkbookFactory.create(fs);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
	            HSSFSheet sheet = wb.getSheetAt(1);
	          
	            StringBuffer sb=new StringBuffer(" select count(*) from MISS_CONSULT_EXAM2.MISS_QUESTION QUESTION " +
	            		" where QUESTION.ME_ID="+meId.intValue()+"");
	            Query query=  session.createSQLQuery(sb.toString());
	            int size=((java.math.BigInteger)query.uniqueResult()).intValue();
	            sb.setLength(0);
	            sb.append(" select QUESTION.MQ_NO,CHOICE.MC_NO from MISS_CONSULT_EXAM2.MISS_QUESTION QUESTION LEFT JOIN" +
	            		" MISS_CONSULT_EXAM2.MISS_TEST TEST ON QUESTION.MQ_ID = TEST.MQ_ID LEFT JOIN" +
	            		" MISS_CONSULT_EXAM2.MISS_CHOICE CHOICE ON TEST.MC_ID = CHOICE.MC_ID" +
	            		" WHERE TEST.MCA_ID="+mcaId.intValue()+" AND TEST.MS_ID="+msId.intValue()+" AND TEST.ME_ID="+meId.intValue()+" ORDER BY QUESTION.MQ_ID" );
	            /*select QUESTION.MQ_NO,CHOICE.MC_NO from MISS_CONSULT_EXAM2.MISS_QUESTION QUESTION LEFT JOIN
 MISS_CONSULT_EXAM2.MISS_TEST TEST ON QUESTION.MQ_ID = TEST.MQ_ID LEFT JOIN 
MISS_CONSULT_EXAM2.MISS_CHOICE CHOICE ON TEST.MC_ID = CHOICE.MC_ID
 WHERE TEST.MCA_ID=22 AND TEST.MS_ID=9 AND TEST.ME_ID=14 ORDER BY QUESTION.MQ_ID  */
	           query=  session.createSQLQuery(sb.toString());
	        
	            List result = query.list();
	        	int size_result = result.size();
	        	Map answerMap=new HashMap();
	        	for (int j = 0; j < size_result; j++) {
	        		Object[] obj = (Object[]) result.get(j);
	        		//logger.debug("obj class="+obj[1].getClass()+",, "+((java.lang.Integer)obj[0]).toString());
	        		if(obj[1]!=null){
	        			answerMap.put(((java.lang.Integer)obj[0]).toString(), ((java.lang.Integer)obj[1]).toString());
	        		}else{
	        			answerMap.put(((java.lang.Integer)obj[0]).toString(), "0");
	        		}
	        		
				}
	        	  HSSFCell cell_question =null;
	        	  HSSFCell cell_answer =null;
	        	  HSSFRow row = null;//
	        	/*   NumberFormat    format  =    NumberFormat.getNumberInstance();
	                format.setGroupingUsed(false);*/
	            for(int i=1;i<=size;i++){
	            	  row=sheet.getRow(i);
	            	  cell_question= row.getCell(0);
	            	  cell_answer= row.getCell(1);
	            	   //System.out.println(""+(format.format(cell_code.getNumericCellValue())));
	            	  int question_no=(int)cell_question.getNumericCellValue();
	            	 Object obj_value=   answerMap.get(question_no+"");
	            	 logger.debug("obj_value xxxxxxxxxxxxxx == "+obj_value.toString());
	            	 if(obj_value!=null){
	            		 cell_answer.setCellValue(Integer.parseInt((String)obj_value));
	            	 }else{
	            		 cell_answer.setCellValue(0);
	            	 }
	            	/*  cell_question.getNumericCellValue()
	            	  cell.setCellValue(1);*/
	            }
	            
				
	          
	           
	           // cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	          

	            HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
	            // Write the output to a file
	          outPut=extensions[0]+"_"+msId.intValue()+"_"+meId.intValue()+"_"+mcaId.intValue()+"."+extensions[1];
	            try {
					fileOut = new FileOutputStream(outPut);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					wb.write(fileOut);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
	        } finally {
	            if (fileOut != null)
					try {
						fileOut.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            if (fileIn != null)
					try {
						fileIn.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        }
	        return outPut;
	}
	public String getCode(String filename){ 
    	FileInputStream fileIn = null;
        //FileOutputStream fileOut = null;
        String code =null;
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
          Sheet sheet1 = wb.getSheetAt(0);          // getConfig
          Row row_code= sheet1.getRow(1);
          Cell cell_code  =row_code.getCell(0);
          String columnReference=cell_code.getStringCellValue();
//         System.out.println(columnReference);
         sheet1 = wb.getSheetAt(1); //get Code
      //   sheet1.s
          CellReference cr = new CellReference(columnReference);
       //   System.out.println(cr.getRow()+","+cr.getCol()+","+cr.getSheetName());
          row_code= sheet1.getRow(cr.getRow());
          cell_code  =row_code.getCell(cr.getCol());
          code=cell_code.getStringCellValue();
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
     return code;
	}
	@Override
	public Long saveOrUpdateMissTestResult(String userid,
			MissTestResult missTestResult) throws DataAccessException {
		// TODO Auto-generated method stub
		MissCandidate missCandidate = null;
		Long returnId  = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){		
			missCandidate=(MissCandidate)obj;
			logger.debug("xxxxxxxxxx="+missCandidate.getMcaId().intValue());
			missTestResult.setMissCandidate(missCandidate);
			query=session.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.missCandidate.mcaId=:mcaId and " +
					" missTestResult.meId=:meId and "+
					" missTestResult.msId=:msId  ");
			query.setParameter("mcaId", missCandidate.getMcaId());
			query.setParameter("meId", missTestResult.getMeId());
			query.setParameter("msId", missTestResult.getMsId());
			
			/*
			java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());*/
			List list=query.list();
			if(list!=null && list.size()>0){//update 
			
				logger.debug("size="+list.size());
				logger.debug("MCA_ID="+missTestResult.getMissCandidate().getMcaId());
				logger.debug("ME_ID="+missTestResult.getMeId());
				logger.debug("MS_ID="+missTestResult.getMsId());
				// CHECK % OF ANSWER
				/*SELECT * FROM MISS_CONSULT_EXAM2.MISS_TEST 
				where MS_ID=9 AND ME_ID=14 AND MCA_ID=22

				SELECT COUNT(*) FROM MISS_CONSULT_EXAM2.MISS_QUESTION
				WHERE ME_ID=14*/
				query=session.createQuery("select count(*) from MissTest missTest where missTest.id.missCandidate.mcaId=:mcaId and " +
					" missTest.id.missExam.meId=:meId and "+
					" missTest.id.missSery.msId=:msId  ");
				query.setParameter("mcaId", missCandidate.getMcaId());
				query.setParameter("meId", missTestResult.getMeId());
				query.setParameter("msId", missTestResult.getMsId());
				
				int missTestSize=  ((java.lang.Long)query.uniqueResult()).intValue();
				
				query=session.createQuery("select count(*) from MissQuestion missQuestion where  " + 
						" missQuestion.missExam.meId=:meId  "); 
					query.setParameter("meId", missTestResult.getMeId()); 
				int missQuestionSize=  ((java.lang.Long)query.uniqueResult()).intValue();
				if((missTestSize*100)/missQuestionSize<90){
					 missTestResult.setMtrStatus("0");
				}
				//session.update(missTestResultUpdate);
				String startTimesql="";
						if(missTestResult.getMtrStartTime()!=null)
							startTimesql=" missTestResult.mtrStartTime =:mtrStartTime , ";
				String endTimesql="";
						if(missTestResult.getMtrEndTime()!=null)
							endTimesql=", missTestResult.mtrEndTime =:mtrEndTime  ";
				query=session.createQuery("update MissTestResult missTestResult " +
					//	" set missTestResult.mtrResultCode =:mtrResultCode ," +
					" set " +
						startTimesql+
					//	" missTestResult.mtrTestDate =:mtrTestDate ," +
						" missTestResult.mtrStatus =:mtrStatus " +
						endTimesql+
						" where missTestResult.missCandidate.mcaId=:mcaId and " +
						" missTestResult.meId=:meId and " +
						" missTestResult.msId=:msId ");
				
				query.setParameter("mcaId", missCandidate.getMcaId()); 
				query.setParameter("meId", missTestResult.getMeId());
				query.setParameter("msId", missTestResult.getMsId());
				// 0=start test(Not finished ) ,1=test finish(Finished),2 =send response(Responsed)
				query.setParameter("mtrStatus", missTestResult.getMtrStatus());  
				if(startTimesql.length()>0)
					query.setParameter("mtrStartTime", missTestResult.getMtrStartTime());
				if(endTimesql.length()>0)
					query.setParameter("mtrEndTime", missTestResult.getMtrEndTime());  
				//query.setParameter("mtrResultCode", missTestResult.getMtrResultCode()); 
				//query.setParameter("mtrTestDate", missTestResult.getMtrTestDate());
				returnId = Long.parseLong((query.executeUpdate())+"");
				
				// update status candidate
				if(missTestResult.getMtrStatus()!=null && missTestResult.getMtrStatus().equals("2")){ // finish test
					query=session.createQuery("update MissCandidate missCandidate " +
								" set missCandidate.mcaStatus =:mcaStatus " + 
								" where missCandidate.mcaId=:mcaId ");
					query.setParameter("mcaStatus", "1");
					query.setParameter("mcaId", missCandidate.getMcaId());
					query.executeUpdate();
				}
			}else{ //save
				try{
					obj = session.save(missTestResult);
				
					if(obj!=null){
						//returnId =(th.co.aoe.makedev.missconsult.hibernate.bean.MissTestPK) obj;
						returnId=1l;
					}
				} finally {
						if (session != null) {
							session = null;
						} 
				}
			}
    
		}
		// TODO Auto-generated method stub
		return returnId;
	
	}
	@Override
	public int startMissTestResult(String userid,
			MissTestResult missTestResult) throws DataAccessException {
		// TODO Auto-generated method stub

		MissCandidate missCandidate = null;
		MissExam missExam = null;
		Long returnId  = null;
		int timelimit=0;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj=query.uniqueResult(); 
	

		// period of 1 year and 7 days
		
		if(obj!=null){		
			missCandidate=(MissCandidate)obj;
			 query=session.createQuery(" select missExam from MissExam missExam where missExam.meId=:meId");
			 query.setParameter("meId", missTestResult.getMeId());
			 Object obj2=query.uniqueResult();
			 if(obj2!=null){	
				 missExam=(MissExam)obj2;
				 timelimit= missExam.getMeTimeLimit().intValue();
				 timelimit=timelimit*60; //standardSeconds
				 missTestResult.setMissCandidate(missCandidate);
					query=session.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.missCandidate.mcaId=:mcaId and " +
							" missTestResult.meId=:meId and "+
							" missTestResult.msId=:msId  ");
					query.setParameter("mcaId", missCandidate.getMcaId());
					query.setParameter("meId", missTestResult.getMeId());
					query.setParameter("msId", missTestResult.getMsId());
					 
					List list=query.list();
					if(list!=null && list.size()>0){//update 
						MissTestResult testResult=(MissTestResult)list.get(0);
						Long time=testResult.getMtrStartTime().getTime();
						java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
						//Long time=testResult.getMtrStartTime().getTime();
						DateTime start = new DateTime(time);
						DateTime end = new DateTime(now.getTime());
						logger.debug(" ================== old time"+timelimit);
						//start=start.minusMinutes(timelimit);
						Interval interval = new Interval(start, end);
						logger.debug(" ================== interval time"+interval.toDuration().getStandardMinutes());
						timelimit=timelimit-(int)interval.toDuration().getStandardSeconds();
						
					}else{ //save
						try{
							obj = session.save(missTestResult);
						
							if(obj!=null){
								//returnId =(th.co.aoe.makedev.missconsult.hibernate.bean.MissTestPK) obj;
								returnId=1l;
								// update status candidate
								 
									query=session.createQuery("update MissCandidate missCandidate " +
												" set missCandidate.mcaStatus =:mcaStatus " + 
												" where missCandidate.mcaId=:mcaId ");
									query.setParameter("mcaStatus", "1");
									query.setParameter("mcaId", missCandidate.getMcaId());
									query.executeUpdate();
								
							}
						} finally {
								if (session != null) {
									session = null;
								} 
						}
						
					}
			 }
			
    
		}
		logger.debug("timelimit="+timelimit);
		// TODO Auto-generated method stub
		return timelimit;
	
	}
	@Override
	public int checkMissTestResult(String userid,
			MissTestResult missTestResult) throws DataAccessException {
		// TODO Auto-generated method stub
		MissCandidate missCandidate = null;
		int tested=0; // 0=not yet test finish, 1=  test finish
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj=query.uniqueResult(); 
	
			 if(obj!=null){	
				 missCandidate=(MissCandidate)obj;
					query=session.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.missCandidate.mcaId=:mcaId and " +
							" missTestResult.msId=:msId  ");
					query.setParameter("mcaId", missCandidate.getMcaId());
					query.setParameter("msId", missCandidate.getMissSery().getMsId());
					 
					@SuppressWarnings("unchecked")
					List<MissTestResult> list=(List<MissTestResult>)query.list();
					if(list!=null && list.size()>0){// check all exam tested  
						for (MissTestResult result : list) {
							if(result.getMtrEndTime()==null){
								tested=0;
								break;
							}
							tested=1;
						}
					}
			 }
		logger.debug("tested="+tested);
		// TODO Auto-generated method stub
		return tested;
	}
}