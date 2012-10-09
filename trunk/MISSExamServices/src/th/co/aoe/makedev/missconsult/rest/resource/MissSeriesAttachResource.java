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
import th.co.aoe.makedev.missconsult.managers.MissSeriesAttachService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissSeriesAttachResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissSeriesAttachService missSeriesAttachService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	public MissSeriesAttachResource() {
		super();
		logger.debug("into constructor MissSeriesAttachResource");
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
		logger.debug("into Post MissSeriesAttachResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_SERIES_ATTACH_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missSeriesAttachService.saveMissSeriesAttach(bpsTerm)).intValue();
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_ATTACH_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missSeriesAttachService.updateMissSeriesAttach(bpsTerm,xbpsTerm.getRootPath());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_ATTACH_DELETE)){
							int updateRecord=missSeriesAttachService.deleteMissSeriesAttach(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_ATTACH_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missSeriesAttachService.searchMissSeriesAttach(bpsTerm,page);
							//logger.debug("MISS_SERIES_ATTACH_SEARCH ==>"+result);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissSeriesAttachObject(ntcCalendars);
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
		logger.debug("into GET MissSeriesAttachResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach ntcCalendarReturn = missSeriesAttachService.findMissSeriesAttachById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach>(1);
			th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missSeriesAttachService.updateMissSeriesAttach(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missSeriesAttachService.saveMissSeriesAttach(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missSeriesAttachService.deleteMissSeriesAttach(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach();
		//bpsTerm.setMegName("Aoe");
		List result = (List) missSeriesAttachService.searchMissSeriesAttach(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissSeriesAttachObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach> getxMissSeriesAttachObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach missSeriesAttach : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach xmissSeriesAttach =new th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach ();			
			BeanUtils.copyProperties(missSeriesAttach, xmissSeriesAttach);
			xmissSeriesAttach.setPagging(null);
			xntcCalendars.add(xmissSeriesAttach);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		return getRepresentation(entity, vresultMessage, xstream);
	}

 
	public MissSeriesAttachService getMissSeriesAttachService() {
		return missSeriesAttachService;
	}

	public void setMissSeriesAttachService(MissSeriesAttachService missSeriesAttachService) {
		this.missSeriesAttachService = missSeriesAttachService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	 

}
