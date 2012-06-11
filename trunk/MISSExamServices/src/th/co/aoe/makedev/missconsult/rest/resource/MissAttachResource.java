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
import th.co.aoe.makedev.missconsult.managers.MissAttachService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissAttachResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissAttachService missAttachService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	public MissAttachResource() {
		super();
		logger.debug("into constructor MissAttachResource");
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
		logger.debug("into Post MissAttachResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissAttach.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissAttach xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissAttach();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissAttach) ntcCalendarObj;
				if (xbpsTerm != null) {
				
					 
					th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_ATTACH_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach ntcCalendarReturn = missAttachService.findMissAttachById(bpsTerm.getMatModule(),bpsTerm.getMatRef());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAttach>(1);
								th.co.aoe.makedev.missconsult.xstream.MissAttach xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissAttach();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								
								
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
								 
								
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_ATTACH_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							Long matId=0l;
						
								matId=(missAttachService.saveMissAttach(bpsTerm));
							
							xbpsTerm.setMatId(matId);
						
							return returnUpdateRecord(entity,xbpsTerm,matId.intValue());
						}
						else if(serviceName.equals(ServiceConstant.MISS_ATTACH_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missAttachService.updateMissAttach(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						/*else if(serviceName.equals(ServiceConstant.MISS_ATTACH_ITEMS_DELETE)){
							String[] matIds=xbpsTerm.getMatIds().split(",");
							//logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
							int updateRecord=0;
							for (int i = 0; i <matIds.length; i++) {
								th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach item = new th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach();
								item.setMatId(Long.parseLong(matIds[i]));
								updateRecord=missAttachService.deleteMissAttach(item);
							}
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}*/
						else if(serviceName.equals(ServiceConstant.MISS_ATTACH_DELETE)){
							int updateRecord=missAttachService.deleteMissAttach(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ATTACH_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missAttachService.searchMissAttach(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAttach>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissAttachObject(ntcCalendars);
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
		logger.debug("into GET MissAttachResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach ntcCalendarReturn = missAttachService.findMissAttachById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAttach>(1);
			th.co.aoe.makedev.missconsult.xstream.MissAttach xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissAttach();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missAttachService.updateMissAttach(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missAttachService.saveMissAttach(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missAttachService.deleteMissAttach(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach();
		//bpsTerm.setMegName("Aoe");
		List result = (List) missAttachService.searchMissAttach(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAttach>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissAttachObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissAttach> getxMissAttachObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAttach>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach missAttach : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissAttach xmissAttach =new th.co.aoe.makedev.missconsult.xstream.MissAttach ();
			BeanUtils.copyProperties(missAttach, xmissAttach);
			xmissAttach.setPagging(null);
			
			xntcCalendars.add(xmissAttach);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissAttach xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissAttach> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAttach>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissAttachService getMissAttachService() {
		return missAttachService;
	}

	public void setMissAttachService(MissAttachService missAttachService) {
		this.missAttachService = missAttachService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

}
