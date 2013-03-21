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
import th.co.aoe.makedev.missconsult.managers.MissAccountSeriesMapService;
import th.co.aoe.makedev.missconsult.managers.MissAccountService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissAccountSeriesMapResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissAccountSeriesMapService missAccountSeriesMapService;
	private MissAccountService missAccountService;
	private com.thoughtworks.xstream.XStream xstream;
	private static final String[] id_ignore=new String[]{"id","missAccount","missSery"};
	public MissAccountSeriesMapResource() {
		super();
		logger.debug("into constructor MissAccountSeriesMapResource");
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
		logger.debug("into Post MissAccountSeriesMapResource 2");
		InputStream in = null;
		
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,id_ignore); 
				 
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap ntcCalendarReturn = missAccountSeriesMapService.findMissAccountSeriesMapById(bpsTerm.getId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap>(1);
								th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_SAVE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							Long masmId=missAccountSeriesMapService.saveMissAccountSeriesMap(bpsTerm,xbpsTerm.getMaId(),xbpsTerm.getMsId());
							int updateRecord=masmId.intValue();
							//xbpsTerm.setMaId(maId); 
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_UPDATE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missAccountSeriesMapService.updateMissAccountSeriesMap(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_DELETE)){
							int updateRecord=missAccountSeriesMapService.deleteMissAccountSeriesMap(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missAccountSeriesMapService.searchMissAccountSeriesMap(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissAccountSeriesMapObject(ntcCalendars);
								}
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						}else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_FIND_BY_MA_ID)){
							 
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> xntcCalendars =null;
								 
								//xntcCalendarReturn.setPagging(null);
								//List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> missAccountSeriesMapList =missAccountService.listMissAccountSeriesMapByMaId(bpsTerm.getMaId());
								List<th.co.aoe.makedev.missconsult.xstream.MissSery> missSeryList =missAccountSeriesMapService.listMissAccountSeriesMapByMaId(xbpsTerm.getMaId());
								if(missSeryList!=null && missSeryList.size()>0){
									int missSeryList_size=missSeryList.size();
									 xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap>(missSeryList_size);
									for (int i = 0; i < missSeryList_size; i++) {
										th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap xmissAccountSeriesMap=new th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap();
										xmissAccountSeriesMap.setMissSery(missSeryList.get(i));
										xntcCalendars.add(xmissAccountSeriesMap);
									}
								}
								//missAccountSeriesMapList.add(e)
								//xntcCalendarReturn.setMissAccountSeriesMapList(missAccountSeriesMapList);
							/*	xntcCalendarReturn.setMissSeryList(missSeryList);
								xntcCalendars.add(xntcCalendarReturn);*/
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							 
						} else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_SERIES_MAP_FIND_BY_ROLE)){
							 
								VResultMessage vresultMessage = new VResultMessage();
								//List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> xntcCalendars =null;
								 
								//xntcCalendarReturn.setPagging(null);
								//List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> missAccountSeriesMapList =missAccountService.listMissAccountSeriesMapByMaId(bpsTerm.getMaId());
								List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> missSeryList =missAccountSeriesMapService.listMissAccountSeriesMapByRole(xbpsTerm.getMaId(),xbpsTerm.getRcId());
							/*	if(missSeryList!=null && missSeryList.size()>0){
									int missSeryList_size=missSeryList.size();
									 xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap>(missSeryList_size);
									for (int i = 0; i < missSeryList_size; i++) {
										th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap xmissAccountSeriesMap=new th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap();
										xmissAccountSeriesMap.setMissSery(missSeryList.get(i));
										xntcCalendars.add(xmissAccountSeriesMap);
									}
								}*/
								vresultMessage.setResultListObj(missSeryList);
								return getRepresentation(entity, vresultMessage, xstream);
							 
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
		logger.debug("into GET MissAccountSeriesMapResource");
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap();
	//	bpsTerm.setMegName("Aoe");
		List result = (List) missAccountSeriesMapService.searchMissAccountSeriesMap(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissAccountSeriesMapObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> getxMissAccountSeriesMapObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap missAccountSeriesMap : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap xmissAccountSeriesMap =new th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap ();
			BeanUtils.copyProperties(missAccountSeriesMap, xmissAccountSeriesMap,id_ignore);
			xmissAccountSeriesMap.setPagging(null);
			xntcCalendars.add(xmissAccountSeriesMap);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissAccountSeriesMapService getMissAccountSeriesMapService() {
		return missAccountSeriesMapService;
	}

	public void setMissAccountSeriesMapService(MissAccountSeriesMapService missAccountSeriesMapService) {
		this.missAccountSeriesMapService = missAccountSeriesMapService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	public MissAccountService getMissAccountService() {
		return missAccountService;
	}

	public void setMissAccountService(MissAccountService missAccountService) {
		this.missAccountService = missAccountService;
	}

	
}