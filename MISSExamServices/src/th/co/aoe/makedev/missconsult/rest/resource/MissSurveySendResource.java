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
import th.co.aoe.makedev.missconsult.managers.MissSurveySendService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissSurveySendResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissSurveySendService missSurveySendService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	public MissSurveySendResource() {
		super();
		logger.debug("into constructor MissSurveySendResource");
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
		logger.debug("into Post MissSurveySendResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissSurveySend.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissSurveySend xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissSurveySend();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissSurveySend) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_SURVEY_SEND_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend ntcCalendarReturn = missSurveySendService.findMissSurveySendById(bpsTerm.getMssId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissSurveySend> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSurveySend>(1);
								th.co.aoe.makedev.missconsult.xstream.MissSurveySend xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissSurveySend();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_SURVEY_SEND_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missSurveySendService.saveMissSurveySend(bpsTerm)).intValue();
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SURVEY_SEND_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missSurveySendService.updateMissSurveySend(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SURVEY_SEND_DELETE)){
							int updateRecord=missSurveySendService.deleteMissSurveySend(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SURVEY_SEND_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missSurveySendService.searchMissSurveySend(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissSurveySend> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSurveySend>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissSurveySendObject(ntcCalendars);
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
	protected Representation post(Representation entity)
			throws ResourceException {
		// TODO Auto-generated method stub
		logger.debug("into Post MissSurveySendResource");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissSurveySend.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissSurveySend xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissSurveySend();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissSurveySend) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend();
					BeanUtils.copyProperties(bpsTerm, xbpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_SURVEY_SEND_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend ntcCalendarReturn = missSurveySendService.findMissSurveySendById(bpsTerm.getMssId());
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissSurveySend> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSurveySend>(1);
								th.co.aoe.makedev.missconsult.xstream.MissSurveySend xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissSurveySend();
								BeanUtils.copyProperties(xntcCalendarReturn, ntcCalendarReturn);								
								
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								export(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_SURVEY_SEND_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missSurveySendService.saveMissSurveySend(bpsTerm)).intValue();
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SURVEY_SEND_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missSurveySendService.updateMissSurveySend(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SURVEY_SEND_DELETE)){
							int updateRecord=missSurveySendService.deleteMissSurveySend(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SURVEY_SEND_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missSurveySendService.searchMissSurveySend(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissSurveySend> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSurveySend>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissSurveySendObject(ntcCalendars);
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
		logger.debug("into GET MissSurveySendResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend ntcCalendarReturn = missSurveySendService.findMissSurveySendById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissSurveySend> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSurveySend>(1);
			th.co.aoe.makedev.missconsult.xstream.MissSurveySend xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissSurveySend();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missSurveySendService.updateMissSurveySend(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missSurveySendService.saveMissSurveySend(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missSurveySendService.deleteMissSurveySend(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend();
		//bpsTerm.setMegName("Aoe")
		List result = (List) missSurveySendService.searchMissSurveySend(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSurveySend> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSurveySend>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissSurveySendObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissSurveySend> getxMissSurveySendObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissSurveySend> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSurveySend>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend missSurveySend : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissSurveySend xmissSurveySend =new th.co.aoe.makedev.missconsult.xstream.MissSurveySend ();
			BeanUtils.copyProperties(missSurveySend, xmissSurveySend);
			xmissSurveySend.setPagging(null);
			xntcCalendars.add(xmissSurveySend);
		}
		return xntcCalendars;
	} 
	private void returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissSurveySend xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSurveySend> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSurveySend>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		export(entity, vresultMessage, xstream);
	}
 
	public MissSurveySendService getMissSurveySendService() {
		return missSurveySendService;
	}

	public void setMissSurveySendService(MissSurveySendService missSurveySendService) {
		this.missSurveySendService = missSurveySendService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

		
}