package th.co.aoe.makedev.missconsult.hibernate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissDataChart;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissDataChartPK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptAttitudeDetectorReport;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptAttitudeDetectorReportPK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptDominance;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptEvalBehavioralValue;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptEvalBehavioralValuePK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptPlusWorkWheelMessage;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptPlusWorkWheelMessagePK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptTraitsDetector;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissEptTraitsDetectorPK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissExam;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSery;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblemPK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUsePK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult;
import th.co.aoe.makedev.missconsult.managers.MissTestResultService;
import th.co.aoe.makedev.missconsult.xstream.MissCareerMaster;
import th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissTestResult extends HibernateCommon implements
		MissTestResultService {
	private static String schema = "";
	private static final Logger logger = Logger
			.getLogger(ServiceConstant.LOG_APPENDER);
	// private static final SimpleDateFormat format = new
	// SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static final SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static ResourceBundle bundle;
	static {
		bundle = ResourceBundle.getBundle("jdbc");
		schema = bundle.getString("schema");
	}
	private SessionFactory sessionAnnotationFactory;

	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}

	public void setSessionAnnotationFactory(
			SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}

	@Transactional(readOnly = true)
	public MissTestResult findMissTestResultById(Long mtrId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissTestResult missTestResult = null;
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.mtrId=:mtrId");
		query.setParameter("mtrId", mtrId);
		Object obj = query.uniqueResult();
		if (obj != null) {
			missTestResult = (MissTestResult) obj;
		}
		return missTestResult;
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List findMissTestShow(Long mcaId, Long msId) {
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" select missTestShow from MissTestShow missTestShow where missTestShow.id.mcaId=:mcaId"
						+ " and  missTestShow.id.msId=:msId  and  missTestShow.id.mtsType='2' order by missTestShow.mtsOrder asc ");
		query.setParameter("mcaId", mcaId);
		query.setParameter("msId", msId);
		// query.setParameter("meId", meId);
		return query.list();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public Long saveMissTestResult(MissTestResult transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Long returnId = null;
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

	private int getSize(Session session, MissTestResult instance,int roleMC)
			throws Exception {
		try {
			Long msId = instance.getMsId();
			MissCandidate missCandidate = instance.getMissCandidate();
			String mcaUsername = missCandidate != null ? missCandidate
					.getMcaUsername() : null;
			String mcaFirstName = missCandidate != null ? missCandidate
					.getMcaFirstName() : null;
			String mcaLastName = missCandidate != null ? missCandidate
					.getMcaLastName() : null;
			String mcaPosition = missCandidate != null ? missCandidate
					.getMcaPosition() : null;
			String mcaDepartMent = missCandidate != null ? missCandidate
					.getMcaDepartment() : null;
			String mtrStartTime = "";
			if (instance.getMtrStartTime() != null) {
				mtrStartTime = format.format(instance.getMtrStartTime());
			}
			String mtrEndTime = "";
			if (instance.getMtrEndTime() != null) {
				mtrEndTime = format.format(instance.getMtrEndTime());
			}

			String maName = (missCandidate != null && missCandidate
					.getMissAccount() != null) ? missCandidate.getMissAccount()
					.getMaName() : null;
			Long maId= (missCandidate != null && missCandidate
							.getMissAccount() != null) ? missCandidate.getMissAccount()
							.getMaId() : null;
			// StringBuffer sb =new
			// StringBuffer(" select count(missTestResult) from MissTestResult missTestResult ");

			StringBuffer sb = new StringBuffer(
					"select count(*) from ( select result.MTR_ID,result.MCA_ID,result.MS_ID,result.ME_ID,result.MTR_TEST_DATE,"
							+ " result.MTR_START_TIME,result.MTR_END_TIME,result.MTR_STATUS,"
							+ " result.MTR_RESULT_CODE,candidate.MCA_USERNAME,candidate.MCA_FIRST_NAME,candidate.MCA_LAST_NAME, "
							+ " candidate.MCA_POSITION ,candidate.MCA_DEPARTMENT ,account.MA_NAME from "
							+ " "
							+ schema
							+ ".MISS_TEST_RESULT  as result left join "
							+ " "
							+ schema
							+ ".MISS_CANDIDATE candidate on result.MCA_ID=candidate.MCA_ID "
							+ " left join "
							+ schema
							+ ".MISS_ACCOUNT  account on candidate.MA_ID=account.MA_ID"
							+ "  ");
			boolean iscriteria = false;
			if(roleMC !=1){ // company
				//sb.append( " where  ( result.MTR_HIDE_STATUS !='0' or result.MTR_HIDE_STATUS is null )  "); 
				//iscriteria = true;
				sb.append( " where  ( result.MTR_HIDE_STATUS ='1' or result.MTR_HIDE_STATUS is null )  ");
				sb.append (" and account.MA_ID ="+ maId+ "");
				iscriteria=true;
			}else{ // admin
				//sb.append( " where  ( result.MTR_HIDE_STATUS ='1' )  ");
				//sb.append (" and account.MA_ID ="+ maId+ "");
				//iscriteria=true;
			}
		
			if (msId != null && ( msId ==-1 || msId > 0 ) ) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and result.MS_ID=" + msId + "")
						: (" where result.MS_ID=" + msId + ""));
				iscriteria = true;
			}
			if (mcaUsername != null && mcaUsername.trim().length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and LOWER(candidate.MCA_USERNAME) like '%"
						+ mcaUsername.trim().toLowerCase() + "%'")
						: (" where LOWER(candidate.MCA_USERNAME) like '%"
								+ mcaUsername.trim().toLowerCase() + "%'"));
				iscriteria = true;
			}
			if (mcaFirstName != null && mcaFirstName.trim().length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and LOWER(candidate.MCA_FIRST_NAME) like '%"
						+ mcaFirstName.trim().toLowerCase() + "%'")
						: (" where LOWER(candidate.MCA_FIRST_NAME) like '%"
								+ mcaFirstName.trim().toLowerCase() + "%'"));
				iscriteria = true;
			}
			if (mcaLastName != null && mcaLastName.trim().length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and LOWER(candidate.MCA_LAST_NAME) like '%"
						+ mcaLastName.trim().toLowerCase() + "%'")
						: (" where LOWER(candidate.MCA_LAST_NAME) like '%"
								+ mcaLastName.trim().toLowerCase() + "%'"));
				iscriteria = true;
			}
			if (mcaPosition != null && mcaPosition.trim().length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and LOWER(candidate.MCA_POSITION) like '%"
						+ mcaPosition.trim().toLowerCase() + "%'")
						: (" where LOWER(candidate.MCA_POSITION) like '%"
								+ mcaPosition.trim().toLowerCase() + "%'"));
				iscriteria = true;
			}
			if (mcaDepartMent != null && mcaDepartMent.trim().length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and LOWER(candidate.MCA_DEPARTMENT) like '%"
						+ mcaDepartMent.trim().toLowerCase() + "%'")
						: (" where LOWER(candidate.MCA_DEPARTMENT) like '%"
								+ mcaDepartMent.trim().toLowerCase() + "%'"));
				iscriteria = true;
			}
			if (maName != null && maName.trim().length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and LOWER(account.MA_NAME) like '%"
						+ maName.trim().toLowerCase() + "%'")
						: (" where LOWER(account.MA_NAME) like '%"
								+ maName.trim().toLowerCase() + "%'"));
				iscriteria = true;
			}
			if (mtrStartTime.length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and result.MTR_START_TIME > '"
						+ mtrStartTime.trim() + "'")
						: (" where result.MTR_START_TIME > '"
								+ mtrStartTime.trim() + "'"));
				iscriteria = true;
			}
			if (mtrEndTime.length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and result.MTR_END_TIME < '"
						+ mtrEndTime.trim() + "'")
						: (" where result.MTR_END_TIME < '" + mtrEndTime.trim() + "'"));
				iscriteria = true;
			}
			sb.append("  ) as x ");
			Query query = session.createSQLQuery(sb.toString());
			return ((java.math.BigInteger) query.uniqueResult()).intValue();
			// return 0;
		} catch (HibernateException re) {
			logger.error("HibernateException", re);
			throw re;
		} catch (Exception e) {
			logger.error("Exception", e);
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public List searchMissTestResult(MissTestResult instance, String mtrIds,int roleMC,
			Pagging pagging,boolean showAll ) throws DataAccessException {
		ArrayList transList = new ArrayList();
		Session session = sessionAnnotationFactory.getCurrentSession();
		Long msId = instance.getMsId();
		 
		try {
			/*
			 * Long megId = instance.getMegId(); String megName =
			 * instance.getMegName();
			 */
			// Long msId=instance.getMsId();
			MissCandidate missCandidate = instance.getMissCandidate();
			String mcaUsername = missCandidate != null ? missCandidate
					.getMcaUsername() : null;
			String mcaFirstName = missCandidate != null ? missCandidate
					.getMcaFirstName() : null;
			String mcaLastName = missCandidate != null ? missCandidate
					.getMcaLastName() : null;
			String mcaPosition = missCandidate != null ? missCandidate
					.getMcaPosition() : null;
			String mcaDepartMent = missCandidate != null ? missCandidate
					.getMcaDepartment() : null;
			String mtrStartTime = "";
			if (instance.getMtrStartTime() != null) {
				mtrStartTime = format.format(instance.getMtrStartTime());
			}
			String mtrEndTime = "";
			if (instance.getMtrEndTime() != null) {
				mtrEndTime = format.format(instance.getMtrEndTime());
			}

			String maName = (missCandidate != null && missCandidate
					.getMissAccount() != null) ? missCandidate.getMissAccount()
					.getMaName() : null;
			Long maId= (missCandidate != null && missCandidate
							.getMissAccount() != null) ? missCandidate.getMissAccount()
							.getMaId() : null; 
			StringBuffer sb = new StringBuffer(
					" select result.MTR_ID,result.MCA_ID,result.MS_ID,result.ME_ID,result.MTR_TEST_DATE,"
							+ " result.MTR_START_TIME,result.MTR_END_TIME,result.MTR_STATUS,"
							+ " result.MTR_RESULT_CODE,candidate.MCA_USERNAME,candidate.MCA_FIRST_NAME,candidate.MCA_LAST_NAME, "
							+ " candidate.MCA_POSITION ,candidate.MCA_DEPARTMENT ,account.MA_NAME ,"
							+ " result.MTR_RESPONDED_STATUS , " +
							" candidate.MCA_GENDER,candidate.MCA_EMAIL, candidate.MCA_TITLE ,candidate.MCA_TITLE_TYPE ," +
							" candidate.MCA_PHONE ,industry.MIM_NAME,career.MCM_NAME ," +
							" (YEAR(CURDATE())-YEAR(candidate.mca_birth_date))-" +
							"  (RIGHT(CURDATE(),5)<RIGHT(candidate.mca_birth_date,5)) AS age " +
							" from "
							+ " "
							+ schema
							+ ".MISS_TEST_RESULT  as result left join "
							+ " "
							+ schema
							+ ".MISS_CANDIDATE candidate on result.MCA_ID=candidate.MCA_ID "
							+ " left join "
							+ schema
							+ ".MISS_ACCOUNT  account on candidate.MA_ID=account.MA_ID"
							+ " left join "
							+ schema
							+ ".MISS_INDUSTRY_MASTER  industry on candidate.MIM_ID=industry.MIM_ID"
							+ " left join "
							+ schema
							+ ".MISS_CAREER_MASTER  career on candidate.MCM_ID=career.MCM_ID"
							+
							 
							// " left join "+schema+".MISS_SERIES_ATTACH  attach on (result.MS_ID=attach.MSAT_REF1 AND attach.MSAT_MODULE='template' ) "
							// +
							"  ");
			/*
			 * where result.MCA_ID=21 and result.MTR_START_TIME > '2012-06-20'
			 * and result.MTR_START_TIME < '2012-06-20 23:59:59'
			 */
			boolean iscriteria = false; 
			if(roleMC !=1){// company
				//sb.append( " where  ( result.MTR_HIDE_STATUS !='0' or result.MTR_HIDE_STATUS is null )  ");
				//iscriteria=true;
				if(!(mtrIds!=null && mtrIds.length()>0)){
					sb.append( " where  ( result.MTR_HIDE_STATUS ='1' or result.MTR_HIDE_STATUS is null )  ");
					sb.append (" and account.MA_ID ="+ maId+ "");
					iscriteria=true;
				}
				
			}else{ //admin
				/*sb.append( " where  ( result.MTR_HIDE_STATUS ='1' )  ");
				sb.append (" and account.MA_ID ="+ maId+ "");
				iscriteria=true;*/
			}
			// SimpleDateFormat format = new
			// SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			if (msId != null && ( msId ==-1 || msId > 0 ) ) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and result.MS_ID=" + msId + "")
						: (" where result.MS_ID=" + msId + ""));
				iscriteria = true;
			}

			if (mtrIds != null && mtrIds.trim().length() > 0 && !mtrIds.trim().equals("-1")) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and result.MTR_ID in (" + mtrIds + ")")
						: (" where result.MTR_ID in (" + mtrIds + ")"));
				iscriteria = true;
			}
			if (mcaUsername != null && mcaUsername.trim().length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and LOWER(candidate.MCA_USERNAME) like '%"
						+ mcaUsername.trim().toLowerCase() + "%'")
						: (" where LOWER(candidate.MCA_USERNAME) like '%"
								+ mcaUsername.trim().toLowerCase() + "%'"));
				iscriteria = true;
			}
			if (mcaFirstName != null && mcaFirstName.trim().length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and LOWER(candidate.MCA_FIRST_NAME) like '%"
						+ mcaFirstName.trim().toLowerCase() + "%'")
						: (" where LOWER(candidate.MCA_FIRST_NAME) like '%"
								+ mcaFirstName.trim().toLowerCase() + "%'"));
				iscriteria = true;
			}
			if (mcaLastName != null && mcaLastName.trim().length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and LOWER(candidate.MCA_LAST_NAME) like '%"
						+ mcaLastName.trim().toLowerCase() + "%'")
						: (" where LOWER(candidate.MCA_LAST_NAME) like '%"
								+ mcaLastName.trim().toLowerCase() + "%'"));
				iscriteria = true;
			}
			if (mcaPosition != null && mcaPosition.trim().length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and LOWER(candidate.MCA_POSITION) like '%"
						+ mcaPosition.trim().toLowerCase() + "%'")
						: (" where LOWER(candidate.MCA_POSITION) like '%"
								+ mcaPosition.trim().toLowerCase() + "%'"));
				iscriteria = true;
			}
			if (mcaDepartMent != null && mcaDepartMent.trim().length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and LOWER(candidate.MCA_DEPARTMENT) like '%"
						+ mcaDepartMent.trim().toLowerCase() + "%'")
						: (" where LOWER(candidate.MCA_DEPARTMENT) like '%"
								+ mcaDepartMent.trim().toLowerCase() + "%'"));
				iscriteria = true;
			}
			if (maName != null && maName.trim().length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and LOWER(account.MA_NAME) like '%"
						+ maName.trim().toLowerCase() + "%'")
						: (" where LOWER(account.MA_NAME) like '%"
								+ maName.trim().toLowerCase() + "%'"));
				iscriteria = true;
			}
			if (mtrStartTime.length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and result.MTR_START_TIME > '"
						+ mtrStartTime.trim() + "'")
						: (" where result.MTR_START_TIME > '"
								+ mtrStartTime.trim() + "'"));
				iscriteria = true;
			}
			if (mtrEndTime.length() > 0) {
				// criteria.add(Expression.eq("megId", megId));
				sb.append(iscriteria ? (" and result.MTR_END_TIME < '"
						+ mtrEndTime.trim() + "'")
						: (" where result.MTR_END_TIME < '" + mtrEndTime.trim() + "'"));
				iscriteria = true;
			}
			if (pagging.getSortBy() != null && pagging.getSortBy().length() > 0) {
				sb.append(" order by " + pagging.getOrderBy()
						+ " " + pagging.getSortBy().toLowerCase());
			} 
			//logger.debug("sb ========================== >" + sb.toString());
			// get header
			Query 	query = session
					.createQuery("select issEvaluationConfig from MissEvaluationConfig issEvaluationConfig "
							+ " where issEvaluationConfig.id.mecType='2' "
							+ " and issEvaluationConfig.id.msId="
							+ msId.intValue()
							+ " "
							+ " and issEvaluationConfig.columnIsShow='1' order by issEvaluationConfig.mecOrder asc ");

			List<th.co.aoe.makedev.missconsult.hibernate.bean.MissEvaluationConfig> missEvaluationConfigs = query
					.list();
			// java.util.ArrayList<th.co.aoe.makedev.missconsult.xstream.MissEvaluationConfig>
			// xmissEvaluationConfig=null;
			List<String> axisHeaders = null;
			if (missEvaluationConfigs != null
					&& missEvaluationConfigs.size() > 0) {
				// xmissEvaluationConfig=new
				// java.util.ArrayList<th.co.aoe.makedev.missconsult.xstream.MissEvaluationConfig>(missEvaluationConfigs.size());
				axisHeaders = new ArrayList<String>(
						missEvaluationConfigs.size());
				for (th.co.aoe.makedev.missconsult.hibernate.bean.MissEvaluationConfig missEvaluationConfig : missEvaluationConfigs) {
					// th.co.aoe.makedev.missconsult.xstream.MissEvaluationConfig
					// evaluationConfig =new
					// th.co.aoe.makedev.missconsult.xstream.MissEvaluationConfig();
					// evaluationConfig.setColumnCode(missEvaluationConfig.getId().getColumnCode());
					// xmissEvaluationConfig.add(evaluationConfig);
					axisHeaders.add(missEvaluationConfig.getId()
							.getColumnCode());
				}
			} else {
				// xmissEvaluationConfig=new
				// java.util.ArrayList<th.co.aoe.makedev.missconsult.xstream.MissEvaluationConfig>();
				axisHeaders = new ArrayList<String>(0);
			}
			// end get header
			query = session.createSQLQuery(sb.toString());
			// set pagging.
			String size = String.valueOf(getSize(session, instance,roleMC));
			logger.debug(" first Result="
					+ (pagging.getPageSize() * (pagging.getPageNo() - 1)));

			if(!showAll){
			query.setFirstResult(pagging.getPageSize()
					* (pagging.getPageNo() - 1));
			query.setMaxResults(pagging.getPageSize());
			}
			List result = query.list();
			logger.debug(" result ========================== >" + result);
			int size_result = result.size();
			for (int j = 0; j < size_result; j++) {
				Object[] obj = (Object[]) result.get(j);
				th.co.aoe.makedev.missconsult.xstream.MissTestResult missTestResult = new th.co.aoe.makedev.missconsult.xstream.MissTestResult();
				th.co.aoe.makedev.missconsult.xstream.MissCandidate candidate = new th.co.aoe.makedev.missconsult.xstream.MissCandidate();
				int mtrId = obj[0] != null ? ((java.lang.Integer) (obj[0]))
						.intValue() : 0;
				int mcaId_query = obj[1] != null ? ((java.lang.Integer) (obj[1]))
						.intValue() : 0;
				int msId_query = obj[2] != null ? ((java.lang.Integer) (obj[2]))
						.intValue() : 0;
				int meId_query = obj[3] != null ? ((java.lang.Integer) (obj[3]))
						.intValue() : 0;
				missTestResult.setMtrId(Long.valueOf(mtrId));
				missTestResult.setMsId(Long.valueOf(msId_query));
				missTestResult.setMeId(Long.valueOf(meId_query));
				candidate.setMcaId(Long.valueOf(mcaId_query));
				candidate.setMcaUsername(obj[9] != null ? obj[9] + "" : "");
				candidate.setMcaFirstName(obj[10] != null ? obj[10] + "" : "");
				candidate.setMcaLastName(obj[11] != null ? obj[11] + "" : "");
				candidate.setMcaPosition(obj[12] != null ? obj[12] + "" : "");
				candidate.setMcaDepartment(obj[13] != null ? obj[13] + "" : "");
				candidate.setMcaGender(obj[16] != null ? obj[16] + "" : "");
				candidate.setMcaEmail(obj[17] != null ? obj[17] + "" : "");
				candidate.setMcaTitle(obj[18] != null ? obj[18] + "" : "");
				candidate.setMcaTitleType(obj[19] != null ? obj[19] + "" : "");
				candidate.setMcaPhone(obj[20] != null ? obj[20] + "" : "");
				MissCareerMaster missCareerMaster =new MissCareerMaster();
				MissIndustryMaster missIndustryMaster=new MissIndustryMaster();
				missIndustryMaster.setMimName(obj[21] != null ? obj[21] + "" : "");
				missCareerMaster.setMcmName(obj[22] != null ? obj[22] + "" : "");
				candidate.setAge(obj[23] != null ? obj[23] + "" : "");
				
				candidate.setMissIndustryMaster(missIndustryMaster);
				candidate.setMissCareerMaster(missCareerMaster);
				
				/*" candidate.MCA_GENDER,candidate.MCA_EMAIL, candidate.MCA_TITLE ,candidate.MCA_TITLE_TYPE ," +
				" candidate.MCA_PHONE ,industry.MIM_NAME,career.MCM_NAME ," +
				" (YEAR(CURDATE())-YEAR(candidate.mca_birth_date))-" +
				"  (RIGHT(CURDATE(),5)<RIGHT(candidate.mca_birth_date,5)) AS age " + */
 
				missTestResult
						.setMtrTestDate(obj[4] != null ? (java.sql.Date) obj[4]
								: null);
				missTestResult
						.setMtrStartTime(obj[5] != null ? (java.sql.Timestamp) obj[5]
								: null);
				missTestResult
						.setMtrEndTime(obj[6] != null ? (java.sql.Timestamp) obj[6]
								: null);
				missTestResult.setMtrResultCode(obj[8] != null ? obj[8] + ""
						: "");
				missTestResult.setMtrStatus(obj[7] != null ? obj[7] + "" : "");
				missTestResult.setMtrRespondedStatus(obj[15] != null ? obj[15]
						+ "" : "");
				// missTestResult.setMsatPath(obj[14] != null ? obj[14] + "" :
				// "");
				candidate.setPagging(null);
				missTestResult.setMissCandidate(candidate);
				missTestResult.setPagging(null);

				// get missTestShow
				// query =session.createQuery("select ");

				query = session
						.createQuery("select missTestShow from MissTestShow missTestShow "
								+ " where missTestShow.id.mtsType='2' and missTestShow.id.mcaId="
								+ mcaId_query
								+ " and missTestShow.id.msId="
								+ msId_query +
								// " and missTestShow.id.meId="+meId_query+" " +
								"and missTestShow.columnIsShow='1' order by missTestShow.mtsOrder ");
				List<th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow> missTestShowResult = query
						.list();
				List<String> axisValues =null;
				if (missTestShowResult != null && missTestShowResult.size() > 0) {
					axisValues= new ArrayList<String>(
							missTestShowResult.size());
					for (th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow missTestShow : missTestShowResult) {
						axisValues.add(missTestShow.getMtsValue());
					}
					missTestResult.setAxisValues(axisValues);
				}else{
					axisValues = new ArrayList<String>(
							axisHeaders.size());
					for(int z=0;z<axisHeaders.size();z++){
						axisValues.add(""); 
					}
					missTestResult.setAxisValues(axisValues);
					//axisHeaders
				}

				/*
				 * missTestResult.setm ownerresultDTO.setKpiOwnerKey(obj[0] !=
				 * null ? new BigDecimal( obj[0] + "") : null);
				 * ownerresultDTO.setKpiOwnerName(obj[1] != null ? obj[1] + "" :
				 * "");
				 */
				result.set(j, missTestResult);
			}
			// List l = query.list();
			
            //System.out.println("result="+result.size());
			transList.add(result);
			transList.add(size);
			transList.add(axisHeaders);
			return transList;
		} catch (Exception re) {
			// re.printStackTrace();
			re.printStackTrace();
			logger.error("find by property name failed", re);

		}
		return transList;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public int updateMissTestResult(MissTestResult transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(),
				transientInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public int deleteMissTestResult(MissTestResult persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(),
				persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	@Override
	public int processMissTestResult(MissTestResult persistentInstance,
			String userid, String rootPath) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		int returnRecord = 0;
		try {
			Long meId = persistentInstance.getMeId();
			Long msId = persistentInstance.getMsId();
			session = sessionAnnotationFactory.getCurrentSession();
			Query query = session
					.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
			query.setParameter("mcaUsername", userid);
			Object obj = query.uniqueResult();
			MissCandidate missCandidate = null;
			Long mcaId = null;
			if (obj != null) {
				missCandidate = (MissCandidate) obj;
				mcaId = missCandidate.getMcaId();
				// logger.debug("xxxxxxxxxx="+missCandidate.getMcaId().intValue());
				query = session
						.createQuery(" select missSery from MissSery "
								+ " missSery where missSery.msId=:msId  "); ;
				query.setParameter("msId", msId);
				Object missSeryObj=query.uniqueResult();
				MissSery missSery =null;
				if(missSeryObj!=null)
					missSery=(MissSery)missSeryObj;
				
				if(missSery.getMsExporting()!=null && missSery.getMsExporting().equals("1")){ 
					  query = session
							.createQuery(" select missReportAttach from MissReportAttach missReportAttach "
									+ " where missReportAttach.id.msId=:msId order by missReportAttach.id.msOrder , missReportAttach.id.mraLang "  );
									//+ " and missReportAttach.matHotlink=:matHotlink ");
					query.setParameter("msId", msId);  
					@SuppressWarnings("rawtypes")
					List list = query.list();
					//String  reportPath=  bundle.getString("reportTemplatePath")+ missReportAttach.getMraPath(); 
					String code ="";
					if (list.size() > 0) {  
						 query=session.createSQLQuery(" select  ((year(curdate()) - year(candidate.MCA_BIRTH_DATE))" +
						 		" - (right(curdate(), 5) < right(candidate.MCA_BIRTH_DATE, 5))) AS age " +
						 		"   from  "+ServiceConstant.SCHEMA+".MISS_CANDIDATE candidate where  candidate.MCA_ID="+missCandidate.getMcaId());
						 List<java.math.BigInteger> list_age=query.list();
						 String age="";
						 for (java.math.BigInteger objects : list_age) {
							 //for (int i = 0; i < objects.length; i++) {
								 age= objects.intValue()+"";
							// } 
						 }
						 for (int i = 0; i < list.size(); i++) {
							 th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach missReportAttach = 
									 (th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach) list.get(i);
							String filePath = "/opt/attach/reportTemplate/" + missReportAttach.getMraPath(); 
							String pathOutPut = setAnswerByXLS(session, filePath, msId,
									meId, mcaId);
							  code = getCodeByXLS(session, pathOutPut, mcaId, msId,i); 
						}
						query = session
								.createQuery("update MissTestResult missTestResult "
										+ " set missTestResult.mtrResultCode =:mtrResultCode "
										+ " , missTestResult.mtrAge =:age "
										+ " , missTestResult.mtrVersion ='4.1.1.2' "
										+
										" where missTestResult.missCandidate.mcaId=:mcaId and "
										+
										// " missTestResult.meId=:meId and " +
										" missTestResult.msId=:msId ");
						query.setParameter("mcaId", mcaId);
						query.setParameter("age", age);
						// query.setParameter("meId", meId);
						query.setParameter("msId", msId);
						query.setParameter("mtrResultCode", code);
						returnRecord = query.executeUpdate();
					} 
				}else{
					query = session
							.createQuery(" select missSeriesAttach from MissSeriesAttach missSeriesAttach where missSeriesAttach.msatRef1=:msatRef1"
									+
									// " and missSeriesAttach.msatRef2=:msatRef2 " +
									" and missSeriesAttach.msatModule=:msatModule");
					query.setParameter("msatRef1", msId);
					// query.setParameter("msatRef2", meId);
					query.setParameter("msatModule", "evaluation");
					obj = query.uniqueResult();
					if (obj != null) {
						th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach missSeriesAttach = (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach) obj;
						missSeriesAttach.getMsatFileName();
						String filePath = rootPath + missSeriesAttach.getMsatPath();
						String pathOutPut = setAnswer(session, filePath, msId,
								meId, mcaId);
						// String code=getCode(session,pathOutPut,mcaId,msId,meId);
						String code = getCode(session, pathOutPut, mcaId, msId);
						logger.debug("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx=" + code);
						query = session
								.createQuery("update MissTestResult missTestResult "
										+ " set missTestResult.mtrResultCode =:mtrResultCode "
										+
										" where missTestResult.missCandidate.mcaId=:mcaId and "
										+
										// " missTestResult.meId=:meId and " +
										" missTestResult.msId=:msId ");
						query.setParameter("mcaId", mcaId);
						// query.setParameter("meId", meId);
						query.setParameter("msId", msId);
						query.setParameter("mtrResultCode", code);
						returnRecord = query.executeUpdate();
					}
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session = null;
			}
		}
		return returnRecord;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String setAnswer(Session session, String filePath, Long msId,
			Long mdeId, Long mcaId) {
		FileInputStream fileIn = null;
		FileOutputStream fileOut = null;
		String[] extensions = filePath.split("\\.");
		String outPut = "";
		try {
			try {
				fileIn = new FileInputStream(filePath);
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
			HSSFWorkbook wb = null;
			try {
				wb = new HSSFWorkbook(fs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * Workbook wb=null; try { wb = WorkbookFactory.create(fs); } catch
			 * (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			Query query = session
					.createQuery(" select missSeriesMap from MissSeriesMap missSeriesMap "
							+ "where missSeriesMap.id.msId=:msId order by missSeriesMap.msmOrder asc ");
			query.setParameter("msId", msId);
			List<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap> missSeriesMaps = query
					.list();
			int sheet_index = 1;
			for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap missSeriesMap : missSeriesMaps) {
				StringBuffer sb = new StringBuffer();
				sb.setLength(0);
				sb.append(" select QUESTION.MQ_NO,CHOICE.MC_NO from "
						+ schema
						+ ".MISS_QUESTION QUESTION LEFT JOIN"
						+ " "
						+ schema
						+ ".MISS_TEST TEST ON QUESTION.MQ_ID = TEST.MQ_ID LEFT JOIN"
						+ " "
						+ schema
						+ ".MISS_CHOICE CHOICE ON  (TEST.MC_NO = CHOICE.MC_NO and "
						+ " TEST.MQ_ID=CHOICE.MQ_ID ) " + " WHERE TEST.MCA_ID="
						+ mcaId.intValue() + " AND TEST.MS_ID="
						+ msId.intValue() + "" + " AND TEST.ME_ID="
						+ missSeriesMap.getId().getMeId().intValue()
						+ " ORDER BY QUESTION.MQ_ID");
				query = session.createSQLQuery(sb.toString());

				List result = query.list();
				int size_result = result.size();
				Map answerMap = new HashMap();
				for (int j = 0; j < size_result; j++) {
					Object[] obj = (Object[]) result.get(j);
					// logger.debug("obj class="+obj[1].getClass()+",, "+((java.lang.Integer)obj[0]).toString());
					if (obj[1] != null) {
						answerMap.put(((java.lang.Integer) obj[0]).toString(),
								((java.lang.Integer) obj[1]).toString());
					} else {
						answerMap.put(((java.lang.Integer) obj[0]).toString(),
								"0");
					}

				}
				HSSFCell cell_question = null;
				HSSFCell cell_answer = null;
				HSSFRow row = null;//
				/*
				 * NumberFormat format = NumberFormat.getNumberInstance();
				 * format.setGroupingUsed(false);
				 */
				sheet_index++;
				Sheet sheet1_0 = wb.getSheetAt(0);
				Row row_code = sheet1_0.getRow(4);
				Cell cell_code = row_code.getCell(sheet_index - 2);
				 Pattern pattern = Pattern.compile("([\\w\\!\\:\\|]+)");
				 //String s="[4!C9:C96|4!D9:D96][5!C9:C96|5!D9:D96]";
				 String columnReference = cell_code.getStringCellValue();
				 columnReference=columnReference.replaceAll(" ", "");
				 //[5!B9:B96|5!C9:C96]
			     Matcher m = pattern.matcher(columnReference);
			     List<String> values=new ArrayList<String>();
			        while (m.find()) {
			        	values.add(m.group());
			        } 
				String[] question_answer=values.get(0).split("\\|");
				 
				String[] questions_sheets = question_answer[0].split("!"); 
				String[] questions_columns = questions_sheets[1].split(":");
				
				String[] answers_sheets = question_answer[1].split("!");
				String[] answers_columns = answers_sheets[1].split(":");
				
				HSSFSheet sheet = wb.getSheetAt(Integer.parseInt(answers_sheets[0]));
				// HSSFCell cell =null;
				CellReference questions_cr = new CellReference(questions_columns[0]);
				//CellReference questions_cr2 = new CellReference(questions_columns[1]);
				
				CellReference answers_cr = new CellReference(answers_columns[0]);
				CellReference answers_cr2 = new CellReference(answers_columns[1]);
				
				int start = answers_cr.getRow();
				int end = answers_cr2.getRow();
				int column = answers_cr.getCol();

				int question_column = questions_cr.getCol();
				// cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// for(int i=1;i<=size;i++){
				
				//System.out.println("start->"+start+",end->"+end+",column->"+column);
				for (int i = start; i <= end; i++) {
					row = sheet.getRow(i);
					// cell_question= row.getCell(0);
					//cell_question = row.getCell(column - 2);
					//cell_question = row.getCell(column - 1);
					cell_question = row.getCell(question_column);
					
					cell_answer = row.getCell(column);
					int question_no = (int) cell_question.getNumericCellValue();
					Object obj_value = answerMap.get(question_no + "");
					//System.out.println("question_no->"+question_no+",obj_value->"+obj_value+",cell_answer->"+cell_answer);
					// logger.debug("obj_value xxxxxxxxxxxxxx == "+obj_value);
					if (obj_value != null) {
						cell_answer.setCellValue(Integer
								.parseInt((String) obj_value));
					} else {
						cell_answer.setCellValue(0);
					}
					/*
					 * cell_question.getNumericCellValue() cell.setCellValue(1);
					 */
				}

			}

			// cell.setCellType(HSSFCell.CELL_TYPE_STRING);

			HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
			// Write the output to a file
			// outPut=extensions[0]+"_"+msId.intValue()+"_"+meId.intValue()+"_"+mcaId.intValue()+"."+extensions[1];
			outPut = extensions[0] + "_" + msId.intValue() + "_"
					+ mcaId.intValue() + "." + extensions[1];
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String setAnswerByXLS(Session session, String filePath, Long msId,
			Long mdeId, Long mcaId) {
		FileInputStream fileIn = null;
		FileOutputStream fileOut = null;
		String[] extensions = filePath.split("\\.");
		String outPut = "";
		try {
			try {
				fileIn = new FileInputStream(filePath);
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
			HSSFWorkbook wb = null;
			try {
				wb = new HSSFWorkbook(fs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * Workbook wb=null; try { wb = WorkbookFactory.create(fs); } catch
			 * (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			Query query = session
					.createQuery(" select missSeriesMap from MissSeriesMap missSeriesMap "
							+ "where missSeriesMap.id.msId=:msId order by missSeriesMap.msmOrder asc ");
			query.setParameter("msId", msId);
			List<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap> missSeriesMaps = query
					.list();
			int sheet_index = 1;
			for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap missSeriesMap : missSeriesMaps) {
				StringBuffer sb = new StringBuffer();
				sb.setLength(0);
				sb.append(" select QUESTION.MQ_NO,CHOICE.MC_NO from "
						+ schema
						+ ".MISS_QUESTION QUESTION LEFT JOIN"
						+ " "
						+ schema
						+ ".MISS_TEST TEST ON QUESTION.MQ_ID = TEST.MQ_ID LEFT JOIN"
						+ " "
						+ schema
						+ ".MISS_CHOICE CHOICE ON  (TEST.MC_NO = CHOICE.MC_NO and "
						+ " TEST.MQ_ID=CHOICE.MQ_ID ) " + " WHERE TEST.MCA_ID="
						+ mcaId.intValue() + " AND TEST.MS_ID="
						+ msId.intValue() + "" + " AND TEST.ME_ID="
						+ missSeriesMap.getId().getMeId().intValue()
						+ " ORDER BY QUESTION.MQ_ID");
				query = session.createSQLQuery(sb.toString());

				List result = query.list();
				int size_result = result.size();
				Map answerMap = new HashMap();
				for (int j = 0; j < size_result; j++) {
					Object[] obj = (Object[]) result.get(j);
					// logger.debug("obj class="+obj[1].getClass()+",, "+((java.lang.Integer)obj[0]).toString());
					if (obj[1] != null) {
						answerMap.put(((java.lang.Integer) obj[0]).toString(),
								((java.lang.Integer) obj[1]).toString());
					} else {
						answerMap.put(((java.lang.Integer) obj[0]).toString(),
								"0");
					}

				}
				HSSFCell cell_question = null;
				HSSFCell cell_answer = null;
				HSSFRow row = null;//
				/*
				 * NumberFormat format = NumberFormat.getNumberInstance();
				 * format.setGroupingUsed(false);
				 */
				sheet_index++;
				Sheet sheet1_0 = wb.getSheetAt(0);
				Row row_code = sheet1_0.getRow(4);
				Cell cell_code = row_code.getCell(sheet_index - 2);
				 Pattern pattern = Pattern.compile("([\\w\\!\\:\\|]+)");
				 //String s="[4!C9:C96|4!D9:D96][5!C9:C96|5!D9:D96]";
				 String columnReference = cell_code.getStringCellValue();
				 columnReference=columnReference.replaceAll(" ", "");
				 //[5!B9:B96|5!C9:C96]
			     Matcher m = pattern.matcher(columnReference);
			     List<String> values=new ArrayList<String>();
			        while (m.find()) {
			        	values.add(m.group());
			        } 
				String[] question_answer=values.get(0).split("\\|");
				 
				String[] questions_sheets = question_answer[0].split("!"); 
				String[] questions_columns = questions_sheets[1].split(":");
				
				String[] answers_sheets = question_answer[1].split("!");
				String[] answers_columns = answers_sheets[1].split(":");
				
				HSSFSheet sheet = wb.getSheetAt(Integer.parseInt(answers_sheets[0]));
				// HSSFCell cell =null;
				CellReference questions_cr = new CellReference(questions_columns[0]);
				//CellReference questions_cr2 = new CellReference(questions_columns[1]);
				
				CellReference answers_cr = new CellReference(answers_columns[0]);
				CellReference answers_cr2 = new CellReference(answers_columns[1]);
				
				int start = answers_cr.getRow();
				int end = answers_cr2.getRow();
				int column = answers_cr.getCol();

				int question_column = questions_cr.getCol();
				// cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// for(int i=1;i<=size;i++){
				
				//System.out.println("start->"+start+",end->"+end+",column->"+column);
				for (int i = start; i <= end; i++) {
					row = sheet.getRow(i);
					// cell_question= row.getCell(0);
					//cell_question = row.getCell(column - 2);
					//cell_question = row.getCell(column - 1);
					cell_question = row.getCell(question_column);
					
					cell_answer = row.getCell(column);
					int question_no = (int) cell_question.getNumericCellValue();
					Object obj_value = answerMap.get(question_no + "");
					//System.out.println("question_no->"+question_no+",obj_value->"+obj_value+",cell_answer->"+cell_answer);
					// logger.debug("obj_value xxxxxxxxxxxxxx == "+obj_value);
					if (obj_value != null) {
						cell_answer.setCellValue(Integer
								.parseInt((String) obj_value));
					} else {
						cell_answer.setCellValue(0);
					}
					/*
					 * cell_question.getNumericCellValue() cell.setCellValue(1);
					 */
				}

			}

			// cell.setCellType(HSSFCell.CELL_TYPE_STRING);

			HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
			// Write the output to a file
			// outPut=extensions[0]+"_"+msId.intValue()+"_"+meId.intValue()+"_"+mcaId.intValue()+"."+extensions[1];
			outPut = extensions[0] + "_" + msId.intValue() + "_"
					+ mcaId.intValue() + "." + extensions[1];
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

	// public String getCode(Session session,String filename,Long mcaId,Long
	// msId,Long meId){
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public String getCode(Session session, String filename, Long mcaId,
			Long msId) {
		FileInputStream fileIn = null;
		// FileOutputStream fileOut = null;
		String code = null;
		try {
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
			Workbook wb = null;
			try {
				wb = new HSSFWorkbook(fs);
				// wb = new XSSFWorkbook(fs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Sheet sheet1 = wb.getSheetAt(0); // getConfig
			Row row_code = sheet1.getRow(1);
			Cell cell_code = null;

			if (row_code != null) {
				cell_code = row_code.getCell(0);

				String columnReference = cell_code.getStringCellValue();
				String[] columns = columnReference.split("!");
				// HSSFCell cell =null;
				/*
				 * CellReference cr = new CellReference(columns[0]);
				 * CellReference cr2 = new CellReference(columns[1]); int
				 * start=cr.getRow(); int end=cr2.getRow(); int
				 * column=cr.getCol();
				 */

				// sheet1 = wb.getSheetAt(1); //get Code
				sheet1 = wb.getSheetAt(Integer.parseInt(columns[0])); // get
																		// sheet
				CellReference cr = new CellReference(columns[1]);
				row_code = sheet1.getRow(cr.getRow());
				cell_code = row_code.getCell(cr.getCol());
				/*
				 * switch (cell.getCellType()) { case Cell.CELL_TYPE_STRING:
				 * value = cell.getStringCellValue(); //
				 * Cell.CELL_TYPE_NUMERIC: }
				 */
				logger.debug("  CELL_TYPE_NUMERIC=" + Cell.CELL_TYPE_NUMERIC);
				logger.debug("  CELL_TYPE_STRING=" + Cell.CELL_TYPE_STRING);
				logger.debug("  CELL_TYPE_FORMULA=" + Cell.CELL_TYPE_FORMULA);

				// logger.debug("  cell_code.getCellType()="+cell_code.getCellType());
				if (cell_code.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					code = cell_code.getNumericCellValue() + "";
				} else if (cell_code.getCellType() == Cell.CELL_TYPE_STRING) {
					code = cell_code.getStringCellValue();
				} else if (cell_code.getCellType() == Cell.CELL_TYPE_FORMULA) {
					FormulaEvaluator evaluator = wb.getCreationHelper()
							.createFormulaEvaluator();
					int type = evaluator.evaluateInCell(cell_code)
							.getCellType();
					logger.debug("  type=" + type);
					if (type == Cell.CELL_TYPE_NUMERIC) {
						code = cell_code.getNumericCellValue() + "";
					} else if (type == Cell.CELL_TYPE_STRING) {
						code = cell_code.getStringCellValue();
					}
					// code=cell_code.getNumericCellValue()+"";
				}
			}
			NumberFormat format = NumberFormat.getNumberInstance();
			format.setGroupingUsed(false);
			// get config
			Sheet sheet1_0 = wb.getSheetAt(0);

			int endRow = sheet1_0.getLastRowNum();
			Row r = null;
			List<th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow> missTestShows = new ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow>();
			int index = 1;
			StringBuffer sb = new StringBuffer();
			for (int i = 7; i <= endRow; i++) {
				r = sheet1_0.getRow(i);
				sb.setLength(0);
				if (r.getCell(2).getBooleanCellValue()) { // 1=true,0=false;
					sb.append("1");
				} else
					sb.append("0");
				// if(r.getCell(2).getBooleanCellValue()){
				th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow missTestShow = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow();
				th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShowPK pk = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShowPK();
				pk.setMcaId(mcaId);
				pk.setMsId(msId);
				// remove pk --> meId
				// pk.setMeId(meId);

				String[] columns = r.getCell(1).getStringCellValue().split("!");
				Sheet sheet1_1 = wb.getSheetAt(Integer.parseInt(columns[0]));
				CellReference cr2 = new CellReference(columns[1]);
				row_code = sheet1_1.getRow(cr2.getRow());
				cell_code = row_code.getCell(cr2.getCol());

				pk.setMtsColumn(r.getCell(0).getStringCellValue());
				pk.setMtsType("2");
				String value = "";
				if (cell_code.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					value = format.format(cell_code.getNumericCellValue());
				} else if (cell_code.getCellType() == Cell.CELL_TYPE_STRING) {
					value = cell_code.getStringCellValue();
				} else if (cell_code.getCellType() == Cell.CELL_TYPE_FORMULA) {
					FormulaEvaluator evaluator = wb.getCreationHelper()
							.createFormulaEvaluator();
					int type = evaluator.evaluateInCell(cell_code)
							.getCellType();
					if (type == Cell.CELL_TYPE_NUMERIC) {
						value = format.format(cell_code.getNumericCellValue());
					} else if (type == Cell.CELL_TYPE_STRING) {
						value = cell_code.getStringCellValue();
					} else if (type == Cell.CELL_TYPE_ERROR) {
						// value=cell_code.getErrorCellValue();
					}
					// code=cell_code.getNumericCellValue()+"";
				}
				missTestShow.setMtsValue(value);
				missTestShow.setId(pk);
				missTestShow.setMtsOrder(Long.valueOf(index++));
				missTestShow.setColumnIsShow(sb.toString());
				missTestShows.add(missTestShow);

				// }
			}
			Query query = session
					.createQuery("delete MissTestShow missTestShow "
							+ " where missTestShow.id.mcaId=:mcaId and " +
							// " missTestShow.id.meId=:meId and " +
							" missTestShow.id.msId=:msId ");
			query.setParameter("mcaId", mcaId);
			// query.setParameter("meId", meId);
			query.setParameter("msId", msId);
			query.executeUpdate();
			for (th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow missTestShow : missTestShows) {
				session.save(missTestShow);
			}

			 
			// get mtrId
			query = session
					.createQuery(" select missTestResult from MissTestResult "
							+ " missTestResult where missTestResult.missCandidate.mcaId=:mcaId and "
							+
							" missTestResult.msId=:msId  ");
			query.setParameter("mcaId", mcaId);
			query.setParameter("msId", msId);
			query.list();
			@SuppressWarnings("rawtypes")
			List list = query.list();
			Long mtrId = null;
			if (list != null && list.size() > 0) {
				MissTestResult result = (MissTestResult) list
						.get(0);
				mtrId = result.getMtrId();
			}
			
			//get MissSery
			/*query = session
					.createQuery(" select missSery from MissSery "
							+ " missSery where missSery.msId=:msId  "); ;
			query.setParameter("msId", msId);
			Object missSeryObj=query.uniqueResult();
			MissSery missSery =null;
			if(missSeryObj!=null)
				missSery=(MissSery)missSeryObj;
			
			if(missSery.getMsExporting()!=null && missSery.getMsExporting().equals("1")){
				
			}else{*/
				// set Data Chart
				Sheet sheet0_Data = wb.getSheetAt(0);
				Row row_code_Data = sheet0_Data.getRow(1);
				if (row_code_Data != null) {
					Cell cell_code_Data = row_code_Data.getCell(1);
					if (cell_code_Data != null) {
						String columnReference = cell_code_Data
								.getStringCellValue();
						if (columnReference != null && columnReference.length() > 0) {
							String[] sheets = columnReference.split("!");
							String[] columns = sheets[1].split(":");
							// HSSFSheet sheet =
							// wb.getSheetAt(Integer.parseInt(sheets[0]));
							// HSSFCell cell =null;
							CellReference cr = new CellReference(columns[0]);
							CellReference cr2 = new CellReference(columns[1]);
							int start = cr.getRow();
							int end = cr2.getRow();
							int column = cr.getCol();
							Sheet sheet1_Data = wb.getSheetAt(Integer
									.parseInt(sheets[0]));
							List<MissDataChart> chartDatas = new ArrayList<MissDataChart>();
							 
							Row row = null;//
							Cell cell = null;
							if (mtrId != null)
								for (int i = start; i <= end; i++) {
									row = sheet1_Data.getRow(i);
									cell = row.getCell(column);
									String key = cell.getStringCellValue();
									cell = row.getCell(column + 1);
									String chartType = cell.getStringCellValue();
									cell = row.getCell(column + 2);
									String chartDataStr = cell.getStringCellValue();
									String mdcType=null;
									MissDataChart chartData = new MissDataChart();
									chartData.setMdcData(chartDataStr);
									// chartData.setMdcType();

									MissDataChartPK chartDataPK = new MissDataChartPK();
									chartDataPK.setMdcKey(key);
									chartDataPK.setMtrId(mtrId);
									// Long mcaId,Long msId){
									chartDataPK.setMdcSwfName(chartType + ".swf");
									if(chartType.equalsIgnoreCase("Radar"))
										mdcType="PowerCharts" ;
									else if(chartType.equalsIgnoreCase("HLED") || chartType.equalsIgnoreCase("HlinearGauge")){
										mdcType="FusionWidgets" ;
									}
									chartData.setId(chartDataPK);
									chartData.setMdcType(mdcType);
									chartDatas.add(chartData);
								}
							for (th.co.aoe.makedev.missconsult.hibernate.bean.MissDataChart missDataChart : chartDatas) {
								//System.out.println(" into save MissDataChart->"+missDataChart.getMdcData());
								session.saveOrUpdate(missDataChart);
							}
						}
					}
				}
			//} 
			
			// check EPT && EPT PLUS
			if(msId.intValue()==12 || msId.intValue()==21){
				 setEPTData(session,wb,msId,mtrId);
			}

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
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public String getCodeByXLS(Session session, String filename, Long mcaId,
			Long msId,int _index) {
		FileInputStream fileIn = null;
		// FileOutputStream fileOut = null;
		String code = null;
		try {
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
			Workbook wb = null;
			try {
				wb = new HSSFWorkbook(fs);
				// wb = new XSSFWorkbook(fs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Sheet sheet1 = wb.getSheetAt(0); // getConfig
			Row row_code = sheet1.getRow(1);
			Cell cell_code = null;

			if (row_code != null) {
				cell_code = row_code.getCell(0);

				String columnReference = cell_code.getStringCellValue();
				String[] columns = columnReference.split("!");
				// HSSFCell cell =null;
				/*
				 * CellReference cr = new CellReference(columns[0]);
				 * CellReference cr2 = new CellReference(columns[1]); int
				 * start=cr.getRow(); int end=cr2.getRow(); int
				 * column=cr.getCol();
				 */

				// sheet1 = wb.getSheetAt(1); //get Code
				sheet1 = wb.getSheetAt(Integer.parseInt(columns[0])); // get
																		// sheet
				CellReference cr = new CellReference(columns[1]);
				row_code = sheet1.getRow(cr.getRow());
				cell_code = row_code.getCell(cr.getCol());
				/*
				 * switch (cell.getCellType()) { case Cell.CELL_TYPE_STRING:
				 * value = cell.getStringCellValue(); //
				 * Cell.CELL_TYPE_NUMERIC: }
				 */
				logger.debug("  CELL_TYPE_NUMERIC=" + Cell.CELL_TYPE_NUMERIC);
				logger.debug("  CELL_TYPE_STRING=" + Cell.CELL_TYPE_STRING);
				logger.debug("  CELL_TYPE_FORMULA=" + Cell.CELL_TYPE_FORMULA);

				// logger.debug("  cell_code.getCellType()="+cell_code.getCellType());
				if (cell_code.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					code = cell_code.getNumericCellValue() + "";
				} else if (cell_code.getCellType() == Cell.CELL_TYPE_STRING) {
					code = cell_code.getStringCellValue();
				} else if (cell_code.getCellType() == Cell.CELL_TYPE_FORMULA) {
					FormulaEvaluator evaluator = wb.getCreationHelper()
							.createFormulaEvaluator();
					int type = evaluator.evaluateInCell(cell_code)
							.getCellType();
					logger.debug("  type=" + type);
					if (type == Cell.CELL_TYPE_NUMERIC) {
						code = cell_code.getNumericCellValue() + "";
					} else if (type == Cell.CELL_TYPE_STRING) {
						code = cell_code.getStringCellValue();
					}
					// code=cell_code.getNumericCellValue()+"";
				}
			}
			NumberFormat format = NumberFormat.getNumberInstance();
			format.setGroupingUsed(false);
			// get config
			Sheet sheet1_0 = wb.getSheetAt(0);

			int endRow = sheet1_0.getLastRowNum();
			Row r = null;
			List<th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow> missTestShows = new ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow>();
			int index = 1;
			StringBuffer sb = new StringBuffer();
			for (int i = 7; i <= endRow; i++) {
				r = sheet1_0.getRow(i);
				sb.setLength(0);
				if (r.getCell(2).getBooleanCellValue()) { // 1=true,0=false;
					sb.append("1");
				} else
					sb.append("0");
				// if(r.getCell(2).getBooleanCellValue()){
				th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow missTestShow = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow();
				th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShowPK pk = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShowPK();
				pk.setMcaId(mcaId);
				pk.setMsId(msId);
				// remove pk --> meId
				// pk.setMeId(meId);

				String[] columns = r.getCell(1).getStringCellValue().split("!");
				Sheet sheet1_1 = wb.getSheetAt(Integer.parseInt(columns[0]));
				CellReference cr2 = new CellReference(columns[1]);
				row_code = sheet1_1.getRow(cr2.getRow());
				cell_code = row_code.getCell(cr2.getCol());

				pk.setMtsColumn(r.getCell(0).getStringCellValue());
				pk.setMtsType("2");
				String value = "";
				if (cell_code.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					value = format.format(cell_code.getNumericCellValue());
				} else if (cell_code.getCellType() == Cell.CELL_TYPE_STRING) {
					value = cell_code.getStringCellValue();
				} else if (cell_code.getCellType() == Cell.CELL_TYPE_FORMULA) {
					FormulaEvaluator evaluator = wb.getCreationHelper()
							.createFormulaEvaluator();
					int type = evaluator.evaluateInCell(cell_code)
							.getCellType();
					if (type == Cell.CELL_TYPE_NUMERIC) {
						value = format.format(cell_code.getNumericCellValue());
					} else if (type == Cell.CELL_TYPE_STRING) {
						value = cell_code.getStringCellValue();
					} else if (type == Cell.CELL_TYPE_ERROR) {
						// value=cell_code.getErrorCellValue();
					}
					// code=cell_code.getNumericCellValue()+"";
				}
				missTestShow.setMtsValue(value);
				missTestShow.setId(pk);
				missTestShow.setMtsOrder(Long.valueOf(index++));
				missTestShow.setColumnIsShow(sb.toString());
				missTestShows.add(missTestShow);

				// }
			}
			//System.out.println("delete ");
			Query query = null;
		if(_index==0){
			  query = session
					.createQuery("delete MissTestShow missTestShow "
							+ " where missTestShow.id.mcaId=:mcaId and " +
							// " missTestShow.id.meId=:meId and " +
							" missTestShow.id.msId=:msId ");
			query.setParameter("mcaId", mcaId);
			// query.setParameter("meId", meId);
			query.setParameter("msId", msId);
			query.executeUpdate();
			for (th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow missTestShow : missTestShows) {
				/*System.out.println("missTestShow.getId().getMcaId()->"+missTestShow.getId().getMcaId());
				System.out.println("missTestShow.getId().getMsId()->"+missTestShow.getId().getMsId());
				System.out.println("missTestShow.getId().getMtsColumn()->"+missTestShow.getId().getMtsColumn());
				System.out.println("missTestShow.getId().getMtsType()->"+missTestShow.getId().getMtsType());*/
				session.save(missTestShow);
			}
		}
			 
			// get mtrId
			query = session
					.createQuery(" select missTestResult from MissTestResult "
							+ " missTestResult where missTestResult.missCandidate.mcaId=:mcaId and "
							+
							" missTestResult.msId=:msId  ");
			query.setParameter("mcaId", mcaId);
			query.setParameter("msId", msId);
			query.list();
			@SuppressWarnings("rawtypes")
			List list = query.list();
			Long mtrId = null;
			if (list != null && list.size() > 0) {
				MissTestResult result = (MissTestResult) list
						.get(0);
				mtrId = result.getMtrId();
			}
			
			//get MissSery
			/*query = session
					.createQuery(" select missSery from MissSery "
							+ " missSery where missSery.msId=:msId  "); ;
			query.setParameter("msId", msId);
			Object missSeryObj=query.uniqueResult();
			MissSery missSery =null;
			if(missSeryObj!=null)
				missSery=(MissSery)missSeryObj;
			
			if(missSery.getMsExporting()!=null && missSery.getMsExporting().equals("1")){
				
			}else{*/
				// set Data Chart
				/*Sheet sheet0_Data = wb.getSheetAt(0);
				Row row_code_Data = sheet0_Data.getRow(1);
				if (row_code_Data != null) {
					Cell cell_code_Data = row_code_Data.getCell(1);
					if (cell_code_Data != null) {
						String columnReference = cell_code_Data
								.getStringCellValue();
						if (columnReference != null && columnReference.length() > 0) {
							String[] sheets = columnReference.split("!");
							String[] columns = sheets[1].split(":");
							// HSSFSheet sheet =
							// wb.getSheetAt(Integer.parseInt(sheets[0]));
							// HSSFCell cell =null;
							CellReference cr = new CellReference(columns[0]);
							CellReference cr2 = new CellReference(columns[1]);
							int start = cr.getRow();
							int end = cr2.getRow();
							int column = cr.getCol();
							Sheet sheet1_Data = wb.getSheetAt(Integer
									.parseInt(sheets[0]));
							List<MissDataChart> chartDatas = new ArrayList<MissDataChart>();
							 
							Row row = null;//
							Cell cell = null;
							if (mtrId != null)
								for (int i = start; i <= end; i++) {
									row = sheet1_Data.getRow(i);
									cell = row.getCell(column);
									String key = cell.getStringCellValue();
									cell = row.getCell(column + 1);
									String chartType = cell.getStringCellValue();
									cell = row.getCell(column + 2);
									String chartDataStr = cell.getStringCellValue();
									String mdcType=null;
									MissDataChart chartData = new MissDataChart();
									chartData.setMdcData(chartDataStr);
									// chartData.setMdcType();

									MissDataChartPK chartDataPK = new MissDataChartPK();
									chartDataPK.setMdcKey(key);
									chartDataPK.setMtrId(mtrId);
									// Long mcaId,Long msId){
									chartDataPK.setMdcSwfName(chartType + ".swf");
									if(chartType.equalsIgnoreCase("Radar"))
										mdcType="PowerCharts" ;
									else if(chartType.equalsIgnoreCase("HLED") || chartType.equalsIgnoreCase("HlinearGauge")){
										mdcType="FusionWidgets" ;
									}
									chartData.setId(chartDataPK);
									chartData.setMdcType(mdcType);
									chartDatas.add(chartData);
								}
							for (th.co.aoe.makedev.missconsult.hibernate.bean.MissDataChart missDataChart : chartDatas) {
								//System.out.println(" into save MissDataChart->"+missDataChart.getMdcData());
								session.saveOrUpdate(missDataChart);
							}
						}
					}
				}*/
		 

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
	private void setEPTData(Session session,Workbook wb,Long msId,Long mtrId){
		try{
		Sheet sheet0_Data=null;
		Row row_code_Data=null;
		if(msId.intValue()==12){ // EPT
			sheet0_Data = wb.getSheetAt(2); // Evaluation of behavioral
			 row_code_Data = sheet0_Data.getRow(1);
			if (row_code_Data != null) {
				Cell cell_code_Data = row_code_Data.getCell(0);
				//Cell cell_code_Data2 = row_code_Data.getCell(0);
				if (cell_code_Data != null) { 
					String columnReference = cell_code_Data
							.getStringCellValue();
					if (columnReference != null && columnReference.length() > 0) {
						String[] sheets = columnReference.split("!");
						String[] columns = sheets[1].split(":");
						CellReference cr = new CellReference(columns[0]);
						CellReference cr2 = new CellReference(columns[1]);
						int start = cr.getRow();
						int end = cr2.getRow();
						int column = cr.getCol();
						Sheet sheet1_Data = wb.getSheetAt(Integer
								.parseInt(sheets[0]));
						List<MissEptEvalBehavioralValue> values = new ArrayList<MissEptEvalBehavioralValue>();
						
						Row row = null;//
						Cell cell = null;
						if (mtrId != null)
							for (int i = start; i <= end; i++) {
								row = sheet1_Data.getRow(i);
								cell = row.getCell(column);
								Long meebgId= (new Double(cell.getNumericCellValue()+"")).longValue();
								 
								cell = row.getCell(column + 1);
								int meebvOrder=(new Double(cell.getNumericCellValue()+"")).intValue();
								
								cell = row.getCell(column + 2);
								int meebvValue=0;
								if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
									meebvValue= (new Double(cell.getNumericCellValue()+"")).intValue();
								} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
									meebvValue= (new Double(cell.getStringCellValue())).intValue();
								} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
									FormulaEvaluator evaluator = wb.getCreationHelper()
											.createFormulaEvaluator();
									int type = evaluator.evaluateInCell(cell)
											.getCellType();
									if (type == Cell.CELL_TYPE_NUMERIC) {
										meebvValue= (new Double(cell.getNumericCellValue()+"")).intValue();
									} else if (type == Cell.CELL_TYPE_STRING) {
										meebvValue= (new Double(cell.getStringCellValue())).intValue();
									} else if (type == Cell.CELL_TYPE_ERROR) {
									}
								
								}
								MissEptEvalBehavioralValue value = new MissEptEvalBehavioralValue();
								
								value.setMeebvValue(meebvValue);
								
								MissEptEvalBehavioralValuePK pk =new MissEptEvalBehavioralValuePK();
								pk.setMtrId(mtrId);
								pk.setMeebvOrder(meebvOrder);
								pk.setMeebgId(meebgId);
								value.setId(pk);
								values.add(value);
							}
						for (th.co.aoe.makedev.missconsult.hibernate.bean.MissEptEvalBehavioralValue value : values) {
							session.saveOrUpdate(value);
						}
					}
				}
			}
			
			MissEptDominance dominance=new MissEptDominance();
			dominance.setMtrId(mtrId);
			//sheet0_Data = wb.getSheetAt(2); // Dominance
			 row_code_Data = sheet0_Data.getRow(4);
			if (row_code_Data != null) {
				Cell cell_code_Data = row_code_Data.getCell(0);
				if (cell_code_Data != null) {
					String columnReference = cell_code_Data
							.getStringCellValue();
					if (columnReference != null && columnReference.length() > 0) {
						String[] sheets = columnReference.split("!");
					//	String[] columns = sheets[1].split(":");
					CellReference cr = new CellReference(sheets[1]);
					/*	CellReference cr2 = new CellReference(columns[1]);*/
						/*int start = cr.getRow();
						int end = cr2.getRow();*/
						//int column = cr.getCol();
						Sheet sheet1_Data = wb.getSheetAt(Integer
								.parseInt(sheets[0]));
						
						Row row = sheet1_Data.getRow(cr.getRow());//
						Cell cell =  row.getCell(cr.getCol());
						
						dominance.setMepDominance(cell.getStringCellValue());
					
					}
				}
			}
			// get Sub-Dominance
			 row_code_Data = sheet0_Data.getRow(7);
				if (row_code_Data != null) {
					Cell cell_code_Data = row_code_Data.getCell(0);
					if (cell_code_Data != null) {
						String columnReference = cell_code_Data
								.getStringCellValue();
						if (columnReference != null && columnReference.length() > 0) {
							String[] sheets = columnReference.split("!");
							//String[] columns = sheets[1].split(":");
							CellReference cr = new CellReference(sheets[1]);
							//CellReference cr2 = new CellReference(columns[1]);
							/*int start = cr.getRow();
							int end = cr2.getRow();
							int column = cr.getCol();*/
							Sheet sheet1_Data = wb.getSheetAt(Integer
									.parseInt(sheets[0]));
							
							Row row = sheet1_Data.getRow(cr.getRow());//
							Cell cell =  row.getCell(cr.getCol()); 
							dominance.setMepSubDominance(cell.getStringCellValue()); 
						}
					}
				}
				session.saveOrUpdate(dominance);
			
			 
			sheet0_Data = wb.getSheetAt(3); // Attitude Detector
			 row_code_Data = sheet0_Data.getRow(1);
			if (row_code_Data != null) {
				Cell cell_code_Data = row_code_Data.getCell(0);
				if (cell_code_Data != null) {
					String columnReference = cell_code_Data
							.getStringCellValue();
					if (columnReference != null && columnReference.length() > 0) {
						String[] sheets = columnReference.split("!");
						String[] columns = sheets[1].split(":");
						CellReference cr = new CellReference(columns[0]);
						CellReference cr2 = new CellReference(columns[1]);
						int start = cr.getRow();
						int end = cr2.getRow();
						int column = cr.getCol();
						Sheet sheet1_Data = wb.getSheetAt(Integer
								.parseInt(sheets[0]));
						List<MissEptAttitudeDetectorReport> reports = new ArrayList<MissEptAttitudeDetectorReport>();
						
						Row row = null;//
						Cell cell = null;
						if (mtrId != null)
							for (int i = start; i <= end; i++) {
								row = sheet1_Data.getRow(i);
								cell = row.getCell(column); 
								 
								String meadrDetail=null;
								String meadrKey=null;
								String meadrTopic=null; 
								 
								String mepwwmLang;
								int mepwwmOrder;
								
								mepwwmOrder= (new Double(cell.getNumericCellValue()+"")).intValue();
								 
								cell = row.getCell(column + 1);
								meadrKey=cell.getStringCellValue();
								
								cell = row.getCell(column + 2);
								meadrTopic=cell.getStringCellValue();
								
								cell = row.getCell(column + 3);
								meadrDetail=cell.getStringCellValue(); 
								 
								cell = row.getCell(column + 4);
								mepwwmLang=(new Double(cell.getNumericCellValue()+"")).intValue()+"";
								
								/*cell = row.getCell(column + 2);
								int meebvValue=0;
								if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
									mepwwmOrder= (new Double(cell.getNumericCellValue()+"")).intValue();
								} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
									meebvValue= (new Double(cell.getStringCellValue())).intValue();
								} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
									FormulaEvaluator evaluator = wb.getCreationHelper()
											.createFormulaEvaluator();
									int type = evaluator.evaluateInCell(cell)
											.getCellType();
									if (type == Cell.CELL_TYPE_NUMERIC) {
										meebvValue= (new Double(cell.getNumericCellValue()+"")).intValue();
									} else if (type == Cell.CELL_TYPE_STRING) {
										meebvValue= (new Double(cell.getStringCellValue())).intValue();
									} else if (type == Cell.CELL_TYPE_ERROR) {
									}
								
								}*/
								MissEptAttitudeDetectorReport report = new MissEptAttitudeDetectorReport(); 
								
								report.setMeadrDetail(meadrDetail);
								report.setMeadrKey(meadrKey);
								report.setMeadrTopic(meadrTopic);
								 
								MissEptAttitudeDetectorReportPK pk = new MissEptAttitudeDetectorReportPK();
								  
								pk.setMtrId(mtrId);
								pk.setMeadrLang(mepwwmLang);
								pk.setMeadrOrder(mepwwmOrder);
								 
								report.setId(pk);
								reports.add(report);
							}
						for (th.co.aoe.makedev.missconsult.hibernate.bean.MissEptAttitudeDetectorReport report : reports) {
							session.saveOrUpdate(report);
						}
					}
				}
			}
			sheet0_Data = wb.getSheetAt(4); // Traits Detector
			 row_code_Data = sheet0_Data.getRow(1);
			if (row_code_Data != null) {
				Cell cell_code_Data = row_code_Data.getCell(0);
				if (cell_code_Data != null) {
					String columnReference = cell_code_Data
							.getStringCellValue();
					if (columnReference != null && columnReference.length() > 0) {
						String[] sheets = columnReference.split("!");
						String[] columns = sheets[1].split(":");
						CellReference cr = new CellReference(columns[0]);
						CellReference cr2 = new CellReference(columns[1]);
						int start = cr.getRow();
						int end = cr2.getRow();
						int column = cr.getCol();
						Sheet sheet1_Data = wb.getSheetAt(Integer
								.parseInt(sheets[0]));
						List<MissEptTraitsDetector> detectors = new ArrayList<MissEptTraitsDetector>();
						
						Row row = null;//
						Cell cell = null;
						if (mtrId != null)
							for (int i = start; i <= end; i++) {
								row = sheet1_Data.getRow(i);
								cell = row.getCell(column); 
								 
								String metdName=null;
								Double metdValue=null;  
 
								String metdLang;
								int metdOrder;  
								metdOrder= (new Double(cell.getNumericCellValue()+"")).intValue();
								 
								cell = row.getCell(column + 1);
								metdName=cell.getStringCellValue();
								
								cell = row.getCell(column + 2);
								metdValue=cell.getNumericCellValue(); 
								
								
								cell = row.getCell(column + 3);
								metdLang= (new Double(cell.getNumericCellValue()+"")).intValue()+"";
								
								/*cell = row.getCell(column + 2);
								int meebvValue=0;
								if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
									mepwwmOrder= (new Double(cell.getNumericCellValue()+"")).intValue();
								} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
									meebvValue= (new Double(cell.getStringCellValue())).intValue();
								} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
									FormulaEvaluator evaluator = wb.getCreationHelper()
											.createFormulaEvaluator();
									int type = evaluator.evaluateInCell(cell)
											.getCellType();
									if (type == Cell.CELL_TYPE_NUMERIC) {
										meebvValue= (new Double(cell.getNumericCellValue()+"")).intValue();
									} else if (type == Cell.CELL_TYPE_STRING) {
										meebvValue= (new Double(cell.getStringCellValue())).intValue();
									} else if (type == Cell.CELL_TYPE_ERROR) {
									}
								
								}*/
								MissEptTraitsDetector detector = new MissEptTraitsDetector(); 
								
								detector.setMetdName(metdName);
								detector.setMetdValue(metdValue); 
								MissEptTraitsDetectorPK pk = new MissEptTraitsDetectorPK(); 
								
								pk.setMtrId(mtrId);
								pk.setMetdLang(metdLang);
								pk.setMetdOrder(metdOrder);
								 
								detector.setId(pk);
								detectors.add(detector);
							}
						for (th.co.aoe.makedev.missconsult.hibernate.bean.MissEptTraitsDetector detector : detectors) {
							session.saveOrUpdate(detector);
						}
					}
				}
			}
		}else if(msId.intValue()==21){ //EPT Plus
			sheet0_Data = wb.getSheetAt(2); // Evaluation of behavioral
			 row_code_Data = sheet0_Data.getRow(1);
			if (row_code_Data != null) {
				Cell cell_code_Data = row_code_Data.getCell(0);
				if (cell_code_Data != null) {
					String columnReference = cell_code_Data
							.getStringCellValue();
					if (columnReference != null && columnReference.length() > 0) {
						String[] sheets = columnReference.split("!");
						String[] columns = sheets[1].split(":");
						CellReference cr = new CellReference(columns[0]);
						CellReference cr2 = new CellReference(columns[1]);
						int start = cr.getRow();
						int end = cr2.getRow();
						int column = cr.getCol();
						Sheet sheet1_Data = wb.getSheetAt(Integer
								.parseInt(sheets[0]));
						List<MissEptEvalBehavioralValue> values = new ArrayList<MissEptEvalBehavioralValue>();
						
						Row row = null;//
						Cell cell = null;
						if (mtrId != null)
							for (int i = start; i <= end; i++) {
								row = sheet1_Data.getRow(i);
								cell = row.getCell(column);
								Long meebgId= (new Double(cell.getNumericCellValue()+"")).longValue();
								 
								cell = row.getCell(column + 1);
								int meebvOrder=(new Double(cell.getNumericCellValue()+"")).intValue();
								
								cell = row.getCell(column + 2);
								int meebvValue=0;
								if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
									meebvValue= (new Double(cell.getNumericCellValue()+"")).intValue();
								} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
									meebvValue= (new Double(cell.getStringCellValue())).intValue();
								} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
									FormulaEvaluator evaluator = wb.getCreationHelper()
											.createFormulaEvaluator();
									int type = evaluator.evaluateInCell(cell)
											.getCellType();
									if (type == Cell.CELL_TYPE_NUMERIC) {
										meebvValue= (new Double(cell.getNumericCellValue()+"")).intValue();
									} else if (type == Cell.CELL_TYPE_STRING) {
										meebvValue= (new Double(cell.getStringCellValue())).intValue();
									} else if (type == Cell.CELL_TYPE_ERROR) {
									}
								
								}
								MissEptEvalBehavioralValue value = new MissEptEvalBehavioralValue();
								value.setMeebvValue(meebvValue);
								MissEptEvalBehavioralValuePK pk =new MissEptEvalBehavioralValuePK();
								pk.setMtrId(mtrId);
								pk.setMeebvOrder(meebvOrder);
								pk.setMeebgId(meebgId);
								value.setId(pk);
								values.add(value);
							}
						for (th.co.aoe.makedev.missconsult.hibernate.bean.MissEptEvalBehavioralValue value : values) {
							session.saveOrUpdate(value);
						}
					}
				}
			}
			
			sheet0_Data = wb.getSheetAt(3); // Work Wheel
			 row_code_Data = sheet0_Data.getRow(1);
			if (row_code_Data != null) {
				Cell cell_code_Data = row_code_Data.getCell(0);
				if (cell_code_Data != null) {
					String columnReference = cell_code_Data
							.getStringCellValue();
					if (columnReference != null && columnReference.length() > 0) {
						String[] sheets = columnReference.split("!");
						String[] columns = sheets[1].split(":");
						CellReference cr = new CellReference(columns[0]);
						CellReference cr2 = new CellReference(columns[1]);
						int start = cr.getRow();
						int end = cr2.getRow();
						int column = cr.getCol();
						Sheet sheet1_Data = wb.getSheetAt(Integer
								.parseInt(sheets[0]));
						List<MissEptPlusWorkWheelMessage> messages = new ArrayList<MissEptPlusWorkWheelMessage>();
						
						Row row = null;//
						Cell cell = null;
						if (mtrId != null)
							for (int i = start; i <= end; i++) {
								row = sheet1_Data.getRow(i);
								cell = row.getCell(column); 
								
								String mepwwmCharecter1=null;
								String mepwwmCharecter2=null;
								String mepwwmPercent=null;
								String mepwwmRole=null;
								String mepwwmSample=null;
								String mepwwmType=null;
								double mepwwmValue;
								 
								String mepwwmLang;
								int mepwwmOrder;
								mepwwmOrder= (new Double(cell.getNumericCellValue()+"")).intValue();
								 
								cell = row.getCell(column + 1);
								mepwwmType=cell.getStringCellValue();
								
								cell = row.getCell(column + 2);
								mepwwmRole=cell.getStringCellValue();
								
								cell = row.getCell(column + 3);
								mepwwmPercent=cell.getStringCellValue();
								
								cell = row.getCell(column + 4);
								mepwwmValue=Double.parseDouble(cell.getStringCellValue());
								
								
								cell = row.getCell(column + 5);
								mepwwmCharecter1=cell.getStringCellValue();
								
								cell = row.getCell(column + 6);
								mepwwmCharecter2=cell.getStringCellValue();
								
								cell = row.getCell(column + 7);
								mepwwmSample=cell.getStringCellValue();
								
								cell = row.getCell(column + 8);
								mepwwmLang=(new Double(cell.getNumericCellValue()+"")).intValue()+"";
								
								/*cell = row.getCell(column + 2);
								int meebvValue=0;
								if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
									mepwwmOrder= (new Double(cell.getNumericCellValue()+"")).intValue();
								} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
									meebvValue= (new Double(cell.getStringCellValue())).intValue();
								} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
									FormulaEvaluator evaluator = wb.getCreationHelper()
											.createFormulaEvaluator();
									int type = evaluator.evaluateInCell(cell)
											.getCellType();
									if (type == Cell.CELL_TYPE_NUMERIC) {
										meebvValue= (new Double(cell.getNumericCellValue()+"")).intValue();
									} else if (type == Cell.CELL_TYPE_STRING) {
										meebvValue= (new Double(cell.getStringCellValue())).intValue();
									} else if (type == Cell.CELL_TYPE_ERROR) {
									}
								
								}*/
								MissEptPlusWorkWheelMessage message = new MissEptPlusWorkWheelMessage();
								message.setMepwwmCharecter1(mepwwmCharecter1);
								message.setMepwwmCharecter2(mepwwmCharecter2);
								message.setMepwwmPercent(mepwwmPercent);
								message.setMepwwmRole(mepwwmRole);
								message.setMepwwmSample(mepwwmSample);
								message.setMepwwmType(mepwwmType);
								message.setMepwwmValue(mepwwmValue);
								 
								MissEptPlusWorkWheelMessagePK pk = new MissEptPlusWorkWheelMessagePK();
								pk.setMtrId(mtrId);
								pk.setMepwwmLang(mepwwmLang);
								pk.setMepwwmOrder(mepwwmOrder);
								 
								message.setId(pk);
								messages.add(message);
							}
						for (th.co.aoe.makedev.missconsult.hibernate.bean.MissEptPlusWorkWheelMessage message : messages) {
							session.saveOrUpdate(message);
						}
					}
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	@Override
	public Long saveOrUpdateMissTestResult(String userid,
			MissTestResult missTestResult) throws DataAccessException {
		// TODO Auto-generated method stub
		MissCandidate missCandidate = null;
		Long returnId = null;
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj = query.uniqueResult();
		if (obj != null) {
			try {
				boolean isIncomplete = false;
				missCandidate = (MissCandidate) obj;
				logger.debug("xxxxxxxxxx="
						+ missCandidate.getMcaId().intValue());
				missTestResult.setMissCandidate(missCandidate);
				query = session
						.createQuery(" select missTestResult from MissTestResult "
								+ " missTestResult where missTestResult.missCandidate.mcaId=:mcaId and "
								+
								// " missTestResult.meId=:meId and "+
								" missTestResult.msId=:msId  ");
				query.setParameter("mcaId", missCandidate.getMcaId());
				// query.setParameter("meId", missTestResult.getMeId());
				query.setParameter("msId", missTestResult.getMsId());

				/*
				 * java.sql.Timestamp timeStampStartDate = new
				 * java.sql.Timestamp(new Date().getTime());
				 */
				@SuppressWarnings("rawtypes")
				List list = query.list();
				if (list != null && list.size() > 0) {// update
					MissTestResult result = (MissTestResult) list.get(0);
					logger.debug("size=" + list.size());
					logger.debug("MCA_ID="
							+ missTestResult.getMissCandidate().getMcaId());
					// logger.debug("ME_ID="+missTestResult.getMeId());
					logger.debug("MS_ID=" + missTestResult.getMsId());
					// CHECK % OF ANSWER
					/*
					 * SELECT * FROM "+schema+".MISS_TEST where MS_ID=9 AND
					 * ME_ID=14 AND MCA_ID=22
					 * 
					 * SELECT COUNT(*) FROM "+schema+".MISS_QUESTION WHERE
					 * ME_ID=14
					 */
					query = session
							.createQuery("select count(*) from MissTest missTest where missTest.id.missCandidate.mcaId=:mcaId and "
									+
									// " missTest.id.missExam.meId=:meId and "+
									" missTest.id.missSery.msId=:msId  ");
					query.setParameter("mcaId", missCandidate.getMcaId());
					// query.setParameter("meId", missTestResult.getMeId());
					query.setParameter("msId", missTestResult.getMsId());

					int missTestSize = ((java.lang.Long) query.uniqueResult())
							.intValue();
					// SELECT * FROM MISS_CONSULT_EXAM3.MISS_SERIES_MAP where
					// ms_id=21
					query = session
							.createQuery("select missSeriesMap from MissSeriesMap missSeriesMap where"
									+ "  missSeriesMap.id.msId=:msId ");
					query.setParameter("msId", missTestResult.getMsId());
					String meIds = "";
					@SuppressWarnings("unchecked")
					List<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap> maps = query
							.list();
					int msId_index = 0;
					int msId_size = maps.size();
					for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap missSeriesMap : maps) {
						meIds = meIds
								+ (missSeriesMap.getId().getMeId().intValue() + "")
								+ ((msId_index != (msId_size - 1)) ? (",") : "");
						msId_index++;
					}
					if (msId_index > 0) {
						query = session
								.createQuery("select count(*) from MissQuestion missQuestion"
										+ " where  missQuestion.missExam.meId in ("
										+ meIds + ")" + "");
						// query.setParameter("meId", missTestResult.getMeId());
						int missQuestionSize = ((java.lang.Long) query
								.uniqueResult()).intValue();
						if ((missTestSize * 100) / missQuestionSize < 90) {
							missTestResult.setMtrStatus("0");
							isIncomplete = true;
						}
					} else {
						missTestResult.setMtrStatus("0");
						isIncomplete = true;
					}

					// session.update(missTestResultUpdate);
					String startTimesql = "";
					if (missTestResult.getMtrStartTime() != null)
						startTimesql = " missTestResult.mtrStartTime =:mtrStartTime , ";
					String endTimesql = "";
					if (missTestResult.getMtrEndTime() != null)
						endTimesql = ", missTestResult.mtrEndTime =:mtrEndTime  ";
					query = session
							.createQuery("update MissTestResult missTestResult "
									+
									// " set missTestResult.mtrResultCode =:mtrResultCode ,"
									// +
									" set "
									+ startTimesql
									+
									// " missTestResult.mtrTestDate =:mtrTestDate ,"
									// +
									" missTestResult.mtrStatus =:mtrStatus "
									+ endTimesql
									+ " where missTestResult.missCandidate.mcaId=:mcaId and "
									+
									// " missTestResult.meId=:meId and " +
									" missTestResult.msId=:msId ");

					query.setParameter("mcaId", missCandidate.getMcaId());
					// query.setParameter("meId", missTestResult.getMeId());
					query.setParameter("msId", missTestResult.getMsId());
					// 0=start test(Not finished ) ,1=test finish(Finished),2
					// =send response(Responsed)
					query.setParameter("mtrStatus",
							missTestResult.getMtrStatus());
					if (startTimesql.length() > 0)
						query.setParameter("mtrStartTime",
								missTestResult.getMtrStartTime());
					if (endTimesql.length() > 0)
						query.setParameter("mtrEndTime",
								missTestResult.getMtrEndTime());
					// query.setParameter("mtrResultCode",
					// missTestResult.getMtrResultCode());
					// query.setParameter("mtrTestDate",
					// missTestResult.getMtrTestDate());
					// returnId = Long.parseLong((query.executeUpdate())+"");
					query.executeUpdate();
					returnId = result.getMtrId();
					// update status candidate
					if (missTestResult.getMtrStatus() != null
							&& missTestResult.getMtrStatus().equals("2")) { // finish
																			// test
						query = session
								.createQuery("update MissCandidate missCandidate "
										+ " set missCandidate.mcaStatus =:mcaStatus "
										+ " where missCandidate.mcaId=:mcaId ");
						query.setParameter("mcaStatus", "1");
						query.setParameter("mcaId", missCandidate.getMcaId());
						query.executeUpdate();
					}
				} else { // save
					obj = session.save(missTestResult);

					if (obj != null) {
						returnId = (Long) obj;
						// returnId=1l;
					}
					/*
					 * try{ obj = session.save(missTestResult);
					 * 
					 * if(obj!=null){ returnId =(Long) obj; //returnId=1l; } }
					 * finally { if (session != null) { session = null; } }
					 */
				}

				if (isIncomplete) {
					MissSeryProblem problem = new MissSeryProblem();
					MissSeryProblemPK pk = new MissSeryProblemPK();
					pk.setMcaId(missCandidate.getMcaId());
					pk.setMsId(missTestResult.getMsId());
					java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(
							new Date().getTime());
					DateTime datetime = new DateTime(
							timeStampStartDate.getTime());
					pk.setMspDateTime(timeStampStartDate);
					problem.setMspWeek(Long.valueOf(datetime.weekOfWeekyear()
							.get()));
					problem.setId(pk);
					session.save(problem);
				}
			} finally {
				if (session != null) {
					session = null;
				}
			}
		}
		// TODO Auto-generated method stub
		return returnId;

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	@Override
	public int startMissTestResult(String userid, MissTestResult missTestResult)
			throws DataAccessException {
		// TODO Auto-generated method stub

		MissCandidate missCandidate = null;
		MissExam missExam = null;
	//	Long returnId = null;
		int timelimit = 0;
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj = query.uniqueResult();

		// period of 1 year and 7 days
		try {
			if (obj != null) {
				missCandidate = (MissCandidate) obj;
				query = session
						.createQuery(" select missExam from MissExam missExam"
								+ " where missExam.meId=:meId");
				query.setParameter("meId", missTestResult.getMeId());
				Object obj2 = query.uniqueResult();
				if (obj2 != null) {
					missExam = (MissExam) obj2;
					timelimit = missExam.getMeTimeLimit().intValue();
					timelimit = timelimit * 60; // standardSeconds
					missTestResult.setMissCandidate(missCandidate);
					query = session
							.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.missCandidate.mcaId=:mcaId and "
									+
									// " missTestResult.meId=:meId
									"  " + " missTestResult.msId=:msId  ");
					query.setParameter("mcaId", missCandidate.getMcaId());
					// query.setParameter("meId", missTestResult.getMeId());
					query.setParameter("msId", missTestResult.getMsId());

					@SuppressWarnings("rawtypes")
					List list = query.list();
					if (list != null && list.size() > 0) {// update
						MissTestResult testResult = (MissTestResult) list
								.get(0);
						Long time = testResult.getMtrStartTime().getTime();
						java.sql.Timestamp now = new java.sql.Timestamp(
								new Date().getTime());
						// Long time=testResult.getMtrStartTime().getTime();
						DateTime start = new DateTime(time);
						DateTime end = new DateTime(now.getTime());
						logger.debug(" ================== old time" + timelimit);
						// start=start.minusMinutes(timelimit);
						Interval interval = new Interval(start, end);
						logger.debug(" ================== interval time"
								+ interval.toDuration().getStandardMinutes());
						timelimit = timelimit
								- (int) interval.toDuration()
										.getStandardSeconds();

					} else { // save
						// try{
						missTestResult.setMeId(null);
						obj = session.save(missTestResult);

						if (obj != null) {
							// returnId
							// =(th.co.aoe.makedev.missconsult.hibernate.bean.MissTestPK)
							// obj;
						//	returnId = 1l;
							// update status candidate

							query = session
									.createQuery("update MissCandidate missCandidate "
											+ " set missCandidate.mcaStatus =:mcaStatus "
											+ " where missCandidate.mcaId=:mcaId ");
							query.setParameter("mcaStatus", "1");
							query.setParameter("mcaId",
									missCandidate.getMcaId());
							query.executeUpdate();

						}

					}

					java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(
							new Date().getTime());
					DateTime datetime = new DateTime(
							timeStampStartDate.getTime());

					MissSeryUse seryUse = new MissSeryUse();
					MissSeryUsePK pk = new MissSeryUsePK();
					pk.setMcaId(missCandidate.getMcaId());
					pk.setMsId(missTestResult.getMsId());
					pk.setMsuDdateTime(timeStampStartDate);
					seryUse.setId(pk);
					seryUse.setMsuWeek(Long.valueOf(datetime.weekOfWeekyear()
							.get()));
					session.saveOrUpdate(seryUse);
				}
			}
		} finally {
			if (session != null) {
				session = null;
			}
		}
		// Save Sery Use

		// }

		// }
		logger.debug("timelimit=" + timelimit);
		// TODO Auto-generated method stub
		return timelimit;

	}

	@Override
	public int checkMissTestResult(String userid, MissTestResult missTestResult)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissCandidate missCandidate = null;
		int tested = 0; // 0=not yet test finish, 1= test finish
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj = query.uniqueResult();

		if (obj != null) {
			missCandidate = (MissCandidate) obj;
			query = session
					.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.missCandidate.mcaId=:mcaId and "
							+ " missTestResult.msId=:msId  ");
			query.setParameter("mcaId", missCandidate.getMcaId());
			query.setParameter("msId", missCandidate.getMissSery().getMsId());

			@SuppressWarnings("unchecked")
			List<MissTestResult> list = (List<MissTestResult>) query.list();
			if (list != null && list.size() > 0) {// check all exam tested
				for (MissTestResult result : list) {
					//check time out
					if(result.getMtrStatus()!=null && result.getMtrStatus().equals("3")){
						tested = 3;
						break;
					}
					if (result.getMtrEndTime() == null) {
						tested = 0;
						break;
					}
					tested = 1;
				}
			}
		}
		logger.debug("tested=" + tested);
		// TODO Auto-generated method stub
		return tested;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	@Override
	public int updateStatus(Long mtrId, String column, String value)
			throws DataAccessException {
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery("update MissTestResult missTestResult "
						+ " set missTestResult." + column + " =:" + column
						+ " " + " where missTestResult.mtrId=:mtrId  ");
		query.setParameter("mtrId", mtrId);
		query.setParameter(column, value);
		return query.executeUpdate();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	@Override
	public int updateStatus(String mtrId, String column, String value)
			throws DataAccessException {
		// String mtrIds[] =mtrId.split(",");
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query = null;
		// for (int i = 0; i < mtrIds.length; i++) {
		query = session.createQuery("update MissTestResult missTestResult "
				+ " set missTestResult." + column + " =:" + column + " "
				+ " where missTestResult.mtrId in (" + mtrId + ")");
		// query.setParameter("mtrId", mtrId);
		query.setParameter(column, value);
		return query.executeUpdate();
		// }
		// return 0;
	}

	@Override
	public int updateTimeOut(Long mcaId, Long msId) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		int updateRecord=0;
		Query query = session
				.createQuery(" select missTestResult from MissTestResult missTestResult " +
						" where missTestResult.missCandidate.mcaId=:mcaId and missTestResult.msId=:msId order by missTestResult.mtrId desc ");
		query.setParameter("mcaId", mcaId);
		query.setParameter("msId", msId);
		@SuppressWarnings("rawtypes")
		List list = query.list();  
		Long mtrId=null;
		if(list!=null && list.size()>0){
			mtrId= ((MissTestResult)list.get(0)).getMtrId();;
		}
		if(mtrId!=null){
			query = session.createQuery("update MissTestResult missTestResult "
					+ " set missTestResult.mtrStatus='3' "
					+ " where missTestResult.mtrId in (" + mtrId + ")");
			updateRecord=query.executeUpdate();
		}
		  
		return updateRecord;
	}
}