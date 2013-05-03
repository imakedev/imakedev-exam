package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.MissSystemUseService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissSystemUseResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissSystemUseService missSystemUseService;
	private com.thoughtworks.xstream.XStream xstream;
	private static 	final String[] ignore_id=new String[]{"id"};
	 
	public MissSystemUseResource() {
		super();
		logger.debug("into constructor MissSystemUseResource");
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
		logger.debug("into Post MissSystemUseResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissSystemUse.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissSystemUse xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissSystemUse();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissSystemUse) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUse bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUse();
					 
					java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
					DateTime datetime=new DateTime(timeStampStartDate.getTime()); 
					
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUsePK pk =new th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUsePK();
					pk.setMsystemType(xbpsTerm.getMsystemType());
					pk.setMsystemUserId(xbpsTerm.getMsystemUserId());
					pk.setMsystemDateTimeLogin(timeStampStartDate);
					
					bpsTerm.setId(pk);
					bpsTerm.setMsystemWeek(Long.valueOf(datetime.weekOfWeekyear().get())); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_SYSTEM_USE_SAVE)){
						//	java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
						//	bpsTerm.setMaRegisterDate(timeStampStartDate);
							Long maId=missSystemUseService.saveMissSystemUse(bpsTerm);
							int updateRecord=maId.intValue();
							//xbpsTerm.setMaId(maId);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SYSTEM_USE_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missSystemUseService.searchMissSystemUse(bpsTerm,page);
							if (result != null && result.size() == 2) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUse> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUse>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissSystemUse> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSystemUse>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissSystemUseObject(ntcCalendars);
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
		logger.debug("into GET MissSystemUseResource");
	
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUse bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUse();
		@SuppressWarnings("rawtypes")
		List result = (List) missSystemUseService.searchMissSystemUse(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSystemUse> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSystemUse>();
		if (result != null && result.size() == 2) {
			@SuppressWarnings("unchecked")
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUse> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUse>) result
					.get(0);
			String faqs_size = (String) result.get(1);
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissSystemUseObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissSystemUse> getxMissSystemUseObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUse> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissSystemUse> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSystemUse>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSystemUse missSystemUse : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissSystemUse xmissSystemUse =new th.co.aoe.makedev.missconsult.xstream.MissSystemUse ();
			BeanUtils.copyProperties(missSystemUse, xmissSystemUse);
			xmissSystemUse.setPagging(null);
			xntcCalendars.add(xmissSystemUse);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissSystemUse xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSystemUse> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSystemUse>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissSystemUseService getMissSystemUseService() {
		return missSystemUseService;
	}

	public void setMissSystemUseService(MissSystemUseService missSystemUseService) {
		this.missSystemUseService = missSystemUseService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}


}
