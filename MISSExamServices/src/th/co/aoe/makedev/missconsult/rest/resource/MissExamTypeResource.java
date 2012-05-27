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
import th.co.aoe.makedev.missconsult.managers.MissExamTypeService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissExamTypeResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissExamTypeService missExamTypeService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	public MissExamTypeResource() {
		super();
		logger.debug("into constructor MissExamTypeResource");
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
		logger.debug("into Post MissExamTypeResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissExamType.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissExamType xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissExamType();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissExamType) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_EXAM_TYPE_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType ntcCalendarReturn = missExamTypeService.findMissExamTypeById(bpsTerm.getMetId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissExamType> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamType>(1);
								th.co.aoe.makedev.missconsult.xstream.MissExamType xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissExamType();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_EXAM_TYPE_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missExamTypeService.saveMissExamType(bpsTerm)).intValue();
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_TYPE_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missExamTypeService.updateMissExamType(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_TYPE_DELETE)){
							int updateRecord=missExamTypeService.deleteMissExamType(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_TYPE_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missExamTypeService.searchMissExamType(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissExamType> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamType>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissExamTypeObject(ntcCalendars);
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
		logger.debug("into Post MissExamTypeResource");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissExamType.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissExamType xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissExamType();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissExamType) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType();
					BeanUtils.copyProperties(bpsTerm, xbpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_EXAM_TYPE_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType ntcCalendarReturn = missExamTypeService.findMissExamTypeById(bpsTerm.getMetId());
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissExamType> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamType>(1);
								th.co.aoe.makedev.missconsult.xstream.MissExamType xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissExamType();
								BeanUtils.copyProperties(xntcCalendarReturn, ntcCalendarReturn);								
								
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								export(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_EXAM_TYPE_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missExamTypeService.saveMissExamType(bpsTerm)).intValue();
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_TYPE_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missExamTypeService.updateMissExamType(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_TYPE_DELETE)){
							int updateRecord=missExamTypeService.deleteMissExamType(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_TYPE_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missExamTypeService.searchMissExamType(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissExamType> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamType>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissExamTypeObject(ntcCalendars);
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
		logger.debug("into GET MissExamTypeResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType ntcCalendarReturn = missExamTypeService.findMissExamTypeById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissExamType> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamType>(1);
			th.co.aoe.makedev.missconsult.xstream.MissExamType xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissExamType();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missExamTypeService.updateMissExamType(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missExamTypeService.saveMissExamType(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missExamTypeService.deleteMissExamType(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType();
		//bpsTerm.setMegName("Aoe");
		List result = (List) missExamTypeService.searchMissExamType(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissExamType> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamType>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissExamTypeObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissExamType> getxMissExamTypeObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissExamType> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamType>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType missExamType : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissExamType xmissExamType =new th.co.aoe.makedev.missconsult.xstream.MissExamType ();
			BeanUtils.copyProperties(missExamType, xmissExamType);
			xmissExamType.setPagging(null);
			xntcCalendars.add(xmissExamType);
		}
		return xntcCalendars;
	} 
	private void returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissExamType xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissExamType> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamType>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		export(entity, vresultMessage, xstream);
	}
 
	public MissExamTypeService getMissExamTypeService() {
		return missExamTypeService;
	}

	public void setMissExamTypeService(MissExamTypeService missExamTypeService) {
		this.missExamTypeService = missExamTypeService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	 
}