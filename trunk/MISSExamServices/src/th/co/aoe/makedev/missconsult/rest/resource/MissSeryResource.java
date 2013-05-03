package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.MissSeryService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissSeryResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissSeryService missSeryService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	public MissSeryResource() {
		super();
		logger.debug("into constructor MissSeryResource");
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
		logger.debug("into Post MissSeryResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissSery.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissSery xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissSery();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissSery) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSery bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSery();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_SERIES_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissSery ntcCalendarReturn = missSeryService.findMissSeryById(bpsTerm.getMsId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissSery> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>(1);
								th.co.aoe.makedev.missconsult.xstream.MissSery xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissSery();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_SERIES_SAVE)){
						//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missSeryService.saveMissSery(bpsTerm,xbpsTerm.getMeIds())).intValue();
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_UPDATE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							logger.debug("bpsTerm getMsSeriesName="+bpsTerm.getMsSeriesName());
							int updateRecord=missSeryService.updateMissSery(bpsTerm,xbpsTerm.getMeIds());
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_DELETE)){
							logger.debug(" id delete="+bpsTerm.getMsId());
							int updateRecord=missSeryService.deleteMissSery(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_ITEMS_DELETE)){
							String[] msIds=xbpsTerm.getMsIds().split(",");
							//logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
							int updateRecord=0;
							for (int i = 0; i <msIds.length; i++) {
								th.co.aoe.makedev.missconsult.hibernate.bean.MissSery item = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSery();
								item.setMsId(Long.parseLong(msIds[i]));
								updateRecord=missSeryService.deleteMissSery(item);
							}
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						} 
						
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_LIST)){
							@SuppressWarnings({ "unchecked" })
							java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSery> ntcCalendars=(ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSery>) missSeryService.listMissSery();
							List<th.co.aoe.makedev.missconsult.xstream.MissSery> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>();
							if (ntcCalendars != null && ntcCalendars.size() > 0) {
								xntcCalendars = getxMissSeryObject(ntcCalendars);
							}
							VResultMessage vresultMessage = new VResultMessage();
							vresultMessage.setResultListObj(xntcCalendars);
							return getRepresentation(entity, vresultMessage, xstream);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							
							@SuppressWarnings({ "rawtypes"})
							List result = (List) missSeryService.searchMissSery(bpsTerm,page,xbpsTerm.getMeIds());
							if (result != null && result.size() == 2) {
								/*java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSery> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSery>) result
										.get(0);*/
								@SuppressWarnings("unchecked")
								List<th.co.aoe.makedev.missconsult.xstream.MissSery> xntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

							//	List<th.co.aoe.makedev.missconsult.xstream.MissSery> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								/*if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissSeryObject(ntcCalendars);
								}*/
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

	/*@Override
	protected Representation post(Representation entity)
			throws ResourceException {
		// TODO Auto-generated method stub
		logger.debug("into Post MissSeryResource");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissSery.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissSery xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissSery();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissSery) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSery bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSery();
					BeanUtils.copyProperties(bpsTerm, xbpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_SERIES_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissSery ntcCalendarReturn = missSeryService.findMissSeryById(bpsTerm.getMsId());
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissSery> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>(1);
								th.co.aoe.makedev.missconsult.xstream.MissSery xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissSery();
								BeanUtils.copyProperties(xntcCalendarReturn, ntcCalendarReturn);								
								
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								export(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_SERIES_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missSeryService.saveMissSery(bpsTerm)).intValue();
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missSeryService.updateMissSery(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_DELETE)){
							int updateRecord=missSeryService.deleteMissSery(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missSeryService.searchMissSery(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSery> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSery>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissSery> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissSeryObject(ntcCalendars);
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
	}*/
	@SuppressWarnings("unchecked")
	@Override
	protected Representation get(Variant variant) throws ResourceException {
		// TODO Auto-generated method stub
		logger.debug("test2"+variant.getMediaType()+","+MediaType.TEXT_PLAIN);
		 
		Form responseHeaders = (Form) getResponse().getAttributes().get("org.restlet.http.headers");
		if (responseHeaders == null)
		{
		responseHeaders = new Form();
		getResponse().getAttributes().put("org.restlet.http.headers", responseHeaders);
		}
		responseHeaders.add("Access-Control-Allow-Origin", "*");

		logger.debug("into GET MissSeryResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissSery ntcCalendarReturn = missSeryService.findMissSeryById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissSery> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>(1);
			th.co.aoe.makedev.missconsult.xstream.MissSery xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissSery();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missSeryService.updateMissSery(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissSery  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissSery ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missSeryService.saveMissSery(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissSery  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissSery ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missSeryService.deleteMissSery(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissSery bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSery();
		//bpsTerm.setMegName("Aoe");
		@SuppressWarnings("rawtypes")
		List result = (List) missSeryService.searchMissSery(bpsTerm,page,null);
		VResultMessage vresultMessage = new VResultMessage();
		//List<th.co.aoe.makedev.missconsult.xstream.MissSery> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>();
		java.util.ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery> ntcCalendars =null;
		if (result != null && result.size() == 2) {
			ntcCalendars= (java.util.ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			/*if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissSeryObject(ntcCalendars);
			}*/
		}
			vresultMessage.setResultListObj(ntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissSery> getxMissSeryObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSery> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissSery> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSery missSery : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissSery xmissSery =new th.co.aoe.makedev.missconsult.xstream.MissSery ();
			BeanUtils.copyProperties(missSery, xmissSery);
			xmissSery.setPagging(null);
			xntcCalendars.add(xmissSery);
		}
		return xntcCalendars;
	} 
	private void returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissSery xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSery> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		export(entity, vresultMessage, xstream);
	}
 
	public MissSeryService getMissSeryService() {
		return missSeryService;
	}

	public void setMissSeryService(MissSeryService missSeryService) {
		this.missSeryService = missSeryService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	 
}