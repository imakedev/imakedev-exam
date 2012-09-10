package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.MissSeryUseService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissSeryUseResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissSeryUseService missSeryUseService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	 
	public MissSeryUseResource() {
		super();
		logger.debug("into constructor MissSeryUseResource");
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
		logger.debug("into Post MissSeryUseResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissSeryUse.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissSeryUse xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissSeryUse();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissSeryUse) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse();
					
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_SERY_USE_SAVE
)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
						//	bpsTerm.setMaRegisterDate(timeStampStartDate);
							Long maId=missSeryUseService.saveMissSeryUse(bpsTerm);
							int updateRecord=maId.intValue();
							//xbpsTerm.setMaId(maId);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERY_USE_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missSeryUseService.searchMissSeryUse(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissSeryUse> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeryUse>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissSeryUseObject(ntcCalendars);
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
		logger.debug("into GET MissSeryUseResource");
	
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse();
		List result = (List) missSeryUseService.searchMissSeryUse(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSeryUse> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeryUse>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse>) result
					.get(0);
			String faqs_size = (String) result.get(1);
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissSeryUseObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissSeryUse> getxMissSeryUseObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissSeryUse> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeryUse>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryUse missSeryUse : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissSeryUse xmissSeryUse =new th.co.aoe.makedev.missconsult.xstream.MissSeryUse ();
			BeanUtils.copyProperties(missSeryUse, xmissSeryUse);
			xmissSeryUse.setPagging(null);
			xntcCalendars.add(xmissSeryUse);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissSeryUse xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSeryUse> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeryUse>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissSeryUseService getMissSeryUseService() {
		return missSeryUseService;
	}

	public void setMissSeryUseService(MissSeryUseService missSeryUseService) {
		this.missSeryUseService = missSeryUseService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}


}
