package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.MissReactiveLogService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissReactiveLogResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissReactiveLogService missReactiveLogService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	 
	public MissReactiveLogResource() {
		super();
		logger.debug("into constructor MissReactiveLogResource");
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
		logger.debug("into Post MissReactiveLogResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissReactiveLog.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissReactiveLog xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissReactiveLog();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissReactiveLog) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog();
					
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_REACTIVE_LOG_SAVE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
						//	bpsTerm.setMaRegisterDate(timeStampStartDate);
							Long maId=missReactiveLogService.saveMissReactiveLog(bpsTerm);
							int updateRecord=maId.intValue();
							//xbpsTerm.setMaId(maId);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_REACTIVE_LOG_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missReactiveLogService.searchMissReactiveLog(bpsTerm,page);
							if (result != null && result.size() == 2) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissReactiveLog> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissReactiveLog>();
								if (faqs_size != null && faqs_size.length()!=0)
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissReactiveLogObject(ntcCalendars);
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
		logger.debug("into GET MissReactiveLogResource");
	
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog();
		@SuppressWarnings("rawtypes")
		List result = (List) missReactiveLogService.searchMissReactiveLog(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissReactiveLog> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissReactiveLog>();
		if (result != null && result.size() == 2) {
			@SuppressWarnings("unchecked")
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog>) result
					.get(0);
			String faqs_size = (String) result.get(1);
			if (faqs_size != null && faqs_size.length()!=0)
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissReactiveLogObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissReactiveLog> getxMissReactiveLogObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissReactiveLog> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissReactiveLog>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog missReactiveLog : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissReactiveLog xmissReactiveLog =new th.co.aoe.makedev.missconsult.xstream.MissReactiveLog ();
			BeanUtils.copyProperties(missReactiveLog, xmissReactiveLog);
			xmissReactiveLog.setPagging(null);
			xntcCalendars.add(xmissReactiveLog);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissReactiveLog xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissReactiveLog> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissReactiveLog>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissReactiveLogService getMissReactiveLogService() {
		return missReactiveLogService;
	}

	public void setMissReactiveLogService(MissReactiveLogService missReactiveLogService) {
		this.missReactiveLogService = missReactiveLogService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}


}
