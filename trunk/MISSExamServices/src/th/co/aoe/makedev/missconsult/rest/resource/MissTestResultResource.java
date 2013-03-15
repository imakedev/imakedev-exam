package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.MissTestResultService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissTestResultResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissTestResultService missTestResultService;
	private com.thoughtworks.xstream.XStream xstream;
	private static 	final String[] ignore_id=new String[]{"missCandidate"};
	private static 	final String[] ignore_id_candidate=new String[]{"missAccount","missSery","missCareerMaster","missIndustryMaster"}; 
	private static 	final String[] ignore_id_account=new String[]{"missTheme","missIndustryMaster"};
	 
	 
	public MissTestResultResource() {
		super();
		logger.debug("into constructor MissTestResultResource");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doInit() throws ResourceException {
		// TODO Auto-generated method stub
		super.doInit();
		logger.debug("into doInit");
	}
	
	@Override
	protected Representation post(Representation entity, Variant variant)
			throws ResourceException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		logger.debug("into Post MissTestResultResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissTestResult.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissTestResult xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissTestResult();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissTestResult) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					if(xbpsTerm.getMissCandidate()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate missCandidate = new th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate();
						BeanUtils.copyProperties(xbpsTerm.getMissCandidate(),missCandidate,ignore_id_candidate); 
						if(xbpsTerm.getMissCandidate().getMissAccount()!=null){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount missAccount = new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount();
							BeanUtils.copyProperties(xbpsTerm.getMissCandidate().getMissAccount(),missAccount,ignore_id_account); 
							missCandidate.setMissAccount(missAccount);
						}
						bpsTerm.setMissCandidate(missCandidate);
					}
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult ntcCalendarReturn = missTestResultService.findMissTestResultById(bpsTerm.getMtrId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissTestResult> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTestResult>(1);
								th.co.aoe.makedev.missconsult.xstream.MissTestResult xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissTestResult();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn,ignore_id);	
								Long mcaId=null;
								Long msId=ntcCalendarReturn.getMsId();
								/*Long meId=ntcCalendarReturn.getMeId();*/
								if(ntcCalendarReturn.getMissCandidate()!=null){
									mcaId=ntcCalendarReturn.getMissCandidate().getMcaId();
									th.co.aoe.makedev.missconsult.xstream.MissCandidate missCandidate = new th.co.aoe.makedev.missconsult.xstream.MissCandidate();
									
									BeanUtils.copyProperties(ntcCalendarReturn.getMissCandidate(),missCandidate,ignore_id_candidate); 
									if(ntcCalendarReturn.getMissCandidate().getMissAccount()!=null){
										th.co.aoe.makedev.missconsult.xstream.MissAccount missAccount = new th.co.aoe.makedev.missconsult.xstream.MissAccount();
										BeanUtils.copyProperties(ntcCalendarReturn.getMissCandidate().getMissAccount(),missAccount,ignore_id_account); 
										missCandidate.setMissAccount(missAccount);
									}
									xntcCalendarReturn.setMissCandidate(missCandidate);
								}
								/*
								int lieScore=0;
							    int a_Score=0;
							    int c_Score=0;
							    int totalScore=0;
							    */
								if(mcaId!=null && msId!=null ){
									List<th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow> missTestShows=missTestResultService.findMissTestShow(mcaId, msId );
									if(missTestShows!=null && missTestShows.size()>0){
										List<th.co.aoe.makedev.missconsult.xstream.MissTestShow> xmissTestShows =new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTestShow>(missTestShows.size());
										for (th.co.aoe.makedev.missconsult.hibernate.bean.MissTestShow missTestShow : missTestShows) {
											th.co.aoe.makedev.missconsult.xstream.MissTestShow xmissTestShow =new 
													th.co.aoe.makedev.missconsult.xstream.MissTestShow();
											xmissTestShow.setMtsColumn(missTestShow.getId().getMtsColumn());
											xmissTestShow.setMtsValue(missTestShow.getMtsValue());
											xmissTestShows.add(xmissTestShow);
										}
										xntcCalendarReturn.setMissTestShows(xmissTestShows);
									}
								}
								
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missTestResultService.saveOrUpdateMissTestResult(xbpsTerm.getUserid(),bpsTerm)).intValue();
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_START)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTestResultService.startMissTestResult(xbpsTerm.getUserid(),bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_CHECK)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTestResultService.checkMissTestResult(xbpsTerm.getUserid(),bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_UPDATE_TIME_OUT)){ 
							int updateRecord=missTestResultService.updateTimeOut(xbpsTerm.getMissCandidate().getMcaId(),xbpsTerm.getMsId());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTestResultService.updateMissTestResult(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_DELETE)){
							int updateRecord=missTestResultService.deleteMissTestResult(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_PROCESS)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTestResultService.processMissTestResult(bpsTerm,xbpsTerm.getUserid(),xbpsTerm.getRootPath());
						  return	returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}	 
						else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_UPDATE_STATUS)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTestResultService.updateStatus(xbpsTerm.getMtrId(), xbpsTerm.getColumn(), xbpsTerm.getValue());
						  return	returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_UPDATE_STATUS_LIST)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTestResultService.updateStatus(xbpsTerm.getMtrIds(), xbpsTerm.getColumn(), xbpsTerm.getValue());
						  return	returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						
						else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missTestResultService.searchMissTestResult(bpsTerm,xbpsTerm.getMtrIds(),xbpsTerm.getRoleMC(),page);
							if (result != null && result.size() == 3) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTestResult> xntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTestResult>) result
										.get(0);
								String faqs_size = (String) result.get(1);
								logger.debug("YYYYYYYYYYYYYYYYY "+xntcCalendars);
								VResultMessage vresultMessage = new VResultMessage();

								//List<th.co.aoe.makedev.missconsult.xstream.MissTestResult> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTestResult>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								 
								List resultListObj=new ArrayList(2);
								resultListObj.add(xntcCalendars);
								resultListObj.add(result.get(2));
								vresultMessage.setResultListObj(resultListObj);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						}
						
					} else {
					}
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.debug(" into Finally Call");
			try {
				if (in != null)
					in.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	
	}

	
	@Override
	protected Representation get(Variant variant) throws ResourceException {
		return null;
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissTestResult> getxMissTestResultObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissTestResult> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTestResult>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult missTestResult : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissTestResult xmissTestResult =new th.co.aoe.makedev.missconsult.xstream.MissTestResult ();
			BeanUtils.copyProperties(missTestResult, xmissTestResult,ignore_id);
			xmissTestResult.setPagging(null); 
			if(missTestResult.getMissCandidate()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissCandidate xmissCandidate = new th.co.aoe.makedev.missconsult.xstream.MissCandidate();
				BeanUtils.copyProperties(missTestResult.getMissCandidate(),xmissCandidate,ignore_id_candidate); 
				xmissCandidate.setPagging(null);
				xmissTestResult.setMissCandidate(xmissCandidate);
			}
			xntcCalendars.add(xmissTestResult);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissTestResult xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissTestResult> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTestResult>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissTestResultService getMissTestResultService() {
		return missTestResultService;
	}

	public void setMissTestResultService(MissTestResultService missTestResultService) {
		this.missTestResultService = missTestResultService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	  
}