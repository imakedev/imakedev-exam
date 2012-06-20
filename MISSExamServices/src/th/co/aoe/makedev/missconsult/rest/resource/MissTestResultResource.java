package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.log4j.Logger;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSery;
import th.co.aoe.makedev.missconsult.managers.MissTestResultService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissTestResultResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissTestResultService missTestResultService;
	private com.thoughtworks.xstream.XStream xstream;
	private static 	final String[] ignore_id=new String[]{"missCandidate"};
	private static 	final String[] ignore_id_candidate=new String[]{"missAccount","missSery"}; 
	 
	 
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
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missTestResultService.saveOrUpdateMissTestResult(xbpsTerm.getUserid(),bpsTerm)).intValue();
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_START)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTestResultService.startMissTestResult(xbpsTerm.getUserid(),bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTestResultService.updateMissTestResult(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_DELETE)){
							int updateRecord=missTestResultService.deleteMissTestResult(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_PROCESS)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTestResultService.processMissTestResult();
						  return	returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEST_RESULT_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missTestResultService.searchMissTestResult(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissTestResult> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTestResult>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissTestResultObject(ntcCalendars);
								}
								vresultMessage.setResultListObj(xntcCalendars);
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
		// TODO Auto-generated method stub
		logger.debug("test2"+variant.getMediaType()+","+MediaType.TEXT_PLAIN);
		logger.debug("into GET MissTestResultResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult ntcCalendarReturn = missTestResultService.findMissTestResultById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissTestResult> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTestResult>(1);
			th.co.aoe.makedev.missconsult.xstream.MissTestResult xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissTestResult();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missTestResultService.updateMissTestResult(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missTestResultService.saveMissTestResult(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missTestResultService.deleteMissTestResult(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult();
		//bpsTerm.setMegName("Aoe");
		List result = (List) missTestResultService.searchMissTestResult(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissTestResult> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTestResult>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissTestResultObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
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