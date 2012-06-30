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
import th.co.aoe.makedev.missconsult.managers.MissSeriesMapService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissSeriesMapResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissSeriesMapService missSeriesMapService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	public MissSeriesMapResource() {
		super();
		logger.debug("into constructor MissSeriesMapResource");
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
		logger.debug("into Post MissSeriesMapResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissSeriesMap.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissSeriesMap xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissSeriesMap();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissSeriesMap) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap();
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMapPK pk =new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMapPK();
					pk.setMeId(xbpsTerm.getMeId());
					pk.setMsId(xbpsTerm.getMsId());
					bpsTerm.setMsmOrder(xbpsTerm.getMsmOrder());
					bpsTerm.setId(pk);
					//BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_SERIES_MAP_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap ntcCalendarReturn = missSeriesMapService.findMissSeriesMapById(bpsTerm.getId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap>(1);
								th.co.aoe.makedev.missconsult.xstream.MissSeriesMap xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissSeriesMap();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_SERIES_MAP_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missSeriesMapService.saveMissSeriesMap(bpsTerm)).intValue();
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_MAP_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missSeriesMapService.updateMissSeriesMap(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_MAP_DELETE)){
							int updateRecord=missSeriesMapService.deleteMissSeriesMap(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_MAP_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missSeriesMapService.searchMissSeriesMap(bpsTerm,page);
							if (result != null && result.size() == 2) {
								/*java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap>) result
										.get(0);*/
								List<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap> xntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								//List<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								/*if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissSeriesMapObject(ntcCalendars);
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

	@Override
	protected Representation get(Variant variant) throws ResourceException {
		// TODO Auto-generated method stub
		logger.debug("test2"+variant.getMediaType()+","+MediaType.TEXT_PLAIN);
		logger.debug("into GET MissSeriesMapResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap ntcCalendarReturn = missSeriesMapService.findMissSeriesMapById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap>(1);
			th.co.aoe.makedev.missconsult.xstream.MissSeriesMap xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissSeriesMap();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missSeriesMapService.updateMissSeriesMap(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missSeriesMapService.saveMissSeriesMap(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missSeriesMapService.deleteMissSeriesMap(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap();
		//bpsTerm.setMegName("Aoe");
		List result = (List) missSeriesMapService.searchMissSeriesMap(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissSeriesMapObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap> getxMissSeriesMapObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap missSeriesMap : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissSeriesMap xmissSeriesMap =new th.co.aoe.makedev.missconsult.xstream.MissSeriesMap ();			
			th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMapPK pk =missSeriesMap.getId();
			xmissSeriesMap.setMeId(pk.getMeId());
			xmissSeriesMap.setMsId(pk.getMsId());
			xmissSeriesMap.setMsmOrder(missSeriesMap.getMsmOrder()); 
			//BeanUtils.copyProperties(missSeriesMap, xmissSeriesMap);
			xmissSeriesMap.setPagging(null);
			xntcCalendars.add(xmissSeriesMap);
		}
		return xntcCalendars;
	} 
	private void returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissSeriesMap xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		export(entity, vresultMessage, xstream);
	}
 
	public MissSeriesMapService getMissSeriesMapService() {
		return missSeriesMapService;
	}

	public void setMissSeriesMapService(MissSeriesMapService missSeriesMapService) {
		this.missSeriesMapService = missSeriesMapService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	 
}