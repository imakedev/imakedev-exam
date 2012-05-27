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
import th.co.aoe.makedev.missconsult.managers.MissTemplateService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissTemplateResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissTemplateService missTemplateService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	public MissTemplateResource() {
		super();
		logger.debug("into constructor MissTemplateResource");
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
		logger.debug("into Post MissTemplateResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissTemplate.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissTemplate xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissTemplate();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissTemplate) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_TEMPLATE_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate ntcCalendarReturn = missTemplateService.findMissTemplateById(bpsTerm.getMtId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissTemplate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTemplate>(1);
								th.co.aoe.makedev.missconsult.xstream.MissTemplate xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissTemplate();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_TEMPLATE_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missTemplateService.saveMissTemplate(bpsTerm)).intValue();
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEMPLATE_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTemplateService.updateMissTemplate(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEMPLATE_DELETE)){
							int updateRecord=missTemplateService.deleteMissTemplate(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEMPLATE_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missTemplateService.searchMissTemplate(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissTemplate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTemplate>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissTemplateObject(ntcCalendars);
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
		logger.debug("into Post MissTemplateResource");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissTemplate.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissTemplate xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissTemplate();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissTemplate) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate();
					BeanUtils.copyProperties(bpsTerm, xbpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_TEMPLATE_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate ntcCalendarReturn = missTemplateService.findMissTemplateById(bpsTerm.getMtId());
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissTemplate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTemplate>(1);
								th.co.aoe.makedev.missconsult.xstream.MissTemplate xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissTemplate();
								BeanUtils.copyProperties(xntcCalendarReturn, ntcCalendarReturn);								
								
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								export(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_TEMPLATE_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missTemplateService.saveMissTemplate(bpsTerm)).intValue();
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEMPLATE_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTemplateService.updateMissTemplate(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEMPLATE_DELETE)){
							int updateRecord=missTemplateService.deleteMissTemplate(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEMPLATE_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missTemplateService.searchMissTemplate(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissTemplate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTemplate>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissTemplateObject(ntcCalendars);
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
		logger.debug("into GET MissTemplateResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate ntcCalendarReturn = missTemplateService.findMissTemplateById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissTemplate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTemplate>(1);
			th.co.aoe.makedev.missconsult.xstream.MissTemplate xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissTemplate();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missTemplateService.updateMissTemplate(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missTemplateService.saveMissTemplate(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missTemplateService.deleteMissTemplate(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate();
		//bpsTerm.setMegName("Aoe");
		List result = (List) missTemplateService.searchMissTemplate(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissTemplate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTemplate>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissTemplateObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissTemplate> getxMissTemplateObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissTemplate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTemplate>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate missTemplate : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissTemplate xmissTemplate =new th.co.aoe.makedev.missconsult.xstream.MissTemplate ();
			BeanUtils.copyProperties(missTemplate, xmissTemplate);
			xmissTemplate.setPagging(null);
			xntcCalendars.add(xmissTemplate);
		}
		return xntcCalendars;
	} 
	private void returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissTemplate xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissTemplate> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTemplate>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		export(entity, vresultMessage, xstream);
	}
 
	public MissTemplateService getMissTemplateService() {
		return missTemplateService;
	}

	public void setMissTemplateService(MissTemplateService missTemplateService) {
		this.missTemplateService = missTemplateService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	 
}