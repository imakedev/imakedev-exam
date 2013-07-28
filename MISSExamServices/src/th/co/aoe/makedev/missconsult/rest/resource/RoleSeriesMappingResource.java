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
import th.co.aoe.makedev.missconsult.managers.RoleSeriesMappingService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class RoleSeriesMappingResource extends BaseResource {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private RoleSeriesMappingService roleSeriesMappingService;
	private com.thoughtworks.xstream.XStream xstream;
	private static 	final String[] ignore_id=new String[]{"missAccount","missSery"};
	 
	public RoleSeriesMappingResource() {
		super();
		logger.debug("into constructor RoleSeriesMappingResource");
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
		logger.debug("into Post RoleSeriesMappingResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping) ntcCalendarObj;
				if (xbpsTerm != null) {
				
					 
					th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					/*if(xbpsTerm.getMissSery()!=null && xbpsTerm.getMissSery().getMsId()!=null && xbpsTerm.getMissSery().getMsId().intValue()!=0){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissSery missSery=new th.co.aoe.makedev.missconsult.hibernate.bean.MissSery();
						missSery.setMsId(xbpsTerm.getMissSery().getMsId());
						bpsTerm.setMissSery(missSery);
					}*/
				
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						/*if(serviceName.equals(ServiceConstant.ROLE_SERIES_MAPPING_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping ntcCalendarReturn = roleSeriesMappingService.findRoleSeriesMappingById(bpsTerm.getRcId());
						logger.debug(" object return ="+ntcCalendarReturn);
						VResultMessage vresultMessage = new VResultMessage();
							if(ntcCalendarReturn!=null){
								List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping>(1);
								th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn,ignore_id);	
								xntcCalendarReturn.setPagging(null);
								
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
							}
							return getRepresentation(entity, vresultMessage, xstream);
						} */
						if(serviceName.equals(ServiceConstant.ROLE_SERIES_MAPPING_SAVE)){
						//	java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							Long rcId=0l;
							
							rcId=(roleSeriesMappingService.saveRoleSeriesMapping(bpsTerm));
							xbpsTerm.setRcId(rcId);
						
							return returnUpdateRecord(entity,xbpsTerm,rcId.intValue());
						}
						else if(serviceName.equals(ServiceConstant.ROLE_SERIES_MAPPING_UPDATE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=roleSeriesMappingService.updateRoleSeriesMapping(xbpsTerm.getRcId(),xbpsTerm.getMsIds());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						/*else if(serviceName.equals(ServiceConstant.ROLE_SERIES_MAPPING_ITEMS_DELETE)){
						 
							String[] mcaIds=xbpsTerm.getMmIds().split(",");
							//logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
							int updateRecord=0;
							for (int i = 0; i <mcaIds.length; i++) {
								th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping item = new th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping();
								item.setMmId(Long.parseLong(mcaIds[i]));
								updateRecord=roleSeriesMappingService.deleteRoleSeriesMapping(item);
							}
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}*/
						else if(serviceName.equals(ServiceConstant.ROLE_SERIES_MAPPING_LIST_BY_RC_ID)){
							@SuppressWarnings("unchecked")
							List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping> xntcCalendars = roleSeriesMappingService.listRoleSeriesMappingByrcId(xbpsTerm.getRcId());
							VResultMessage vresultMessage = new VResultMessage();
							vresultMessage.setResultListObj(xntcCalendars);
							return getRepresentation(entity, vresultMessage, xstream);
						}
						
						else if(serviceName.equals(ServiceConstant.ROLE_SERIES_MAPPING_DELETE)){
							int updateRecord=roleSeriesMappingService.deleteRoleSeriesMapping(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.ROLE_SERIES_MAPPING_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 							
							@SuppressWarnings("rawtypes")
							List result = (List) roleSeriesMappingService.searchRoleSeriesMapping(bpsTerm,page);
							if (result != null && result.size() == 2) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping>();
								if (faqs_size != null && faqs_size.length()!=0)
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxRoleSeriesMappingObject(ntcCalendars);
								}
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						}/*else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.xstream.MissAccount xntcCalendarReturn  = missAccountService.findMissAccountById(bpsTerm.getMaId());
						logger.debug(" object return ="+xntcCalendarReturn);
							if(xntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();ss
								List<th.co.aoe.makedev.missconsult.xstream.MissAccount> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccount>(1);
								 
								xntcCalendarReturn.setPagging(null);
								//List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> missAccountSeriesMapList =missAccountService.listMissAccountSeriesMapByMaId(bpsTerm.getMaId());
								List<th.co.aoe.makedev.missconsult.xstream.MissSery> missSeryList =missAccountService.listMissAccountSeriesMapByMaId(bpsTerm.getMaId());
								//missAccountSeriesMapList.add(e)
								//xntcCalendarReturn.setMissAccountSeriesMapList(missAccountSeriesMapList);
								xntcCalendarReturn.setMissSeryList(missSeryList);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} */
						
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
		logger.debug("into GET RoleSeriesMappingResource");
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping();
		//bpsTerm.setMegName("Aoe");
		@SuppressWarnings("rawtypes")
		List result = (List) roleSeriesMappingService.searchRoleSeriesMapping(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping>();
		if (result != null && result.size() == 2) {
			@SuppressWarnings("unchecked")
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && faqs_size.length()!=0)
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxRoleSeriesMappingObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping> getxRoleSeriesMappingObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping missManual : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping xmissManual =new th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping ();
			BeanUtils.copyProperties(missManual, xmissManual,ignore_id);
			xmissManual.setPagging(null);
			/*if(missManual.getMissSery()!=null && missManual.getMissSery().getMsId()!=null && missManual.getMissSery().getMsId().intValue()!=0){
				th.co.aoe.makedev.missconsult.xstream.MissSery missSery=new th.co.aoe.makedev.missconsult.xstream.MissSery();
				BeanUtils.copyProperties(missManual.getMissSery(), missSery);
				missSery.setPagging(null);
				xmissManual.setMissSery(missSery);
			}
		*/
			
			xntcCalendars.add(xmissManual);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public RoleSeriesMappingService getRoleSeriesMappingService() {
		return roleSeriesMappingService;
	}

	public void setRoleSeriesMappingService(RoleSeriesMappingService roleSeriesMappingService) {
		this.roleSeriesMappingService = roleSeriesMappingService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}



}
