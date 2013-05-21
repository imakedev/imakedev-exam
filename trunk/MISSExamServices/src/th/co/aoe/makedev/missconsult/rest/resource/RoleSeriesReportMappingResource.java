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
import th.co.aoe.makedev.missconsult.managers.RoleSeriesReportMappingService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class RoleSeriesReportMappingResource  extends BaseResource {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private RoleSeriesReportMappingService roleSeriesReportMappingService;
	private com.thoughtworks.xstream.XStream xstream;
	private static 	final String[] ignore_id=new String[]{"missAccount","missSery"};
	 
	public RoleSeriesReportMappingResource() {
		super();
		logger.debug("into constructor RoleSeriesReportMappingResource");
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
		logger.debug("into Post RoleSeriesReportMappingResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping) ntcCalendarObj;
				if (xbpsTerm != null) {
				
					 
					th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesReportMapping bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesReportMapping();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					/*if(xbpsTerm.getMissSery()!=null && xbpsTerm.getMissSery().getMsId()!=null && xbpsTerm.getMissSery().getMsId().intValue()!=0){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissSery missSery=new th.co.aoe.makedev.missconsult.hibernate.bean.MissSery();
						missSery.setMsId(xbpsTerm.getMissSery().getMsId());
						bpsTerm.setMissSery(missSery);
					}*/
				
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						/*if(serviceName.equals(ServiceConstant.ROLE_SERIES_MAPPING_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesReportMapping ntcCalendarReturn = roleSeriesReportMappingService.findRoleSeriesReportMappingById(bpsTerm.getRcId());
						logger.debug(" object return ="+ntcCalendarReturn);
						VResultMessage vresultMessage = new VResultMessage();
							if(ntcCalendarReturn!=null){
								List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping>(1);
								th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn,ignore_id);	
								xntcCalendarReturn.setPagging(null);
								
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
							}
							return getRepresentation(entity, vresultMessage, xstream);
						} */
						/*if(serviceName.equals(ServiceConstant.ROLE_SERIES_MAPPING_SAVE)){
						//	java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							Long rcId=0l;
							
							rcId=(roleSeriesReportMappingService.saveRoleSeriesReportMapping(bpsTerm));
							xbpsTerm.setRcId(rcId);
						
							return returnUpdateRecord(entity,xbpsTerm,rcId.intValue());
						}
						else*/  
						if(serviceName.equals(ServiceConstant.ROLE_SERIES_REPORT_MAPPING_UPDATE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
						/*	System.out.println("xbpsTerm.getMsOrders()->"+xbpsTerm.getMsOrders());
							System.out.println("xbpsTerm.getMsOrders().length()->"+xbpsTerm.getMsOrders().length);*/
							int updateRecord=roleSeriesReportMappingService.updateRoleSeriesReportMapping(xbpsTerm.getRcId(),xbpsTerm.getMsId(),xbpsTerm.getMsOrders());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						 
						else if(serviceName.equals(ServiceConstant.ROLE_SERIES_REPORT_MAPPING_LIST_BY_RC_ID)){
							@SuppressWarnings("unchecked")
							List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping> xntcCalendars = roleSeriesReportMappingService.listRoleSeriesReportMappingByrcId(xbpsTerm.getRcId(),xbpsTerm.getMsId());
							VResultMessage vresultMessage = new VResultMessage();
							vresultMessage.setResultListObj(xntcCalendars);
							return getRepresentation(entity, vresultMessage, xstream);
						}
						
						/*	else if(serviceName.equals(ServiceConstant.ROLE_SERIES_MAPPING_DELETE)){
							int updateRecord=roleSeriesReportMappingService.deleteRoleSeriesReportMapping(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
					else if(serviceName.equals(ServiceConstant.ROLE_SERIES_MAPPING_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 							
							@SuppressWarnings("rawtypes")
							List result = (List) roleSeriesReportMappingService.searchRoleSeriesReportMapping(bpsTerm,page);
							if (result != null && result.size() == 2) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesReportMapping> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesReportMapping>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxRoleSeriesReportMappingObject(ntcCalendars);
								}
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

	
	 
	private List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping> getxRoleSeriesReportMappingObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesReportMapping> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesReportMapping missManual : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping xmissManual =new th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping ();
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
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public RoleSeriesReportMappingService getRoleSeriesReportMappingService() {
		return roleSeriesReportMappingService;
	}

	public void setRoleSeriesReportMappingService(RoleSeriesReportMappingService roleSeriesReportMappingService) {
		this.roleSeriesReportMappingService = roleSeriesReportMappingService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}


}
