package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.MissIndustryMasterService;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissIndustryMasterResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissIndustryMasterService missIndustryMasterService;
	private com.thoughtworks.xstream.XStream xstream;

	public MissIndustryMasterResource() {
		super();
		logger.debug("into constructor MissIndustryMasterResource");
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
		logger.debug("into Post MissIndustryMasterResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissIndustryMaster bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissIndustryMaster();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						 if(serviceName.equals(ServiceConstant.INDUSTRY_MASTER_LIST)){
							//Pagging page = xbpsTerm.getPagging(); 
							 @SuppressWarnings("rawtypes")
							List result = (List) missIndustryMasterService.listMissIndustryMaster();
							if (result != null && result.size() >0) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissIndustryMaster> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissIndustryMaster>) result;
										
								String faqs_size = ntcCalendars.size()+"";
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster>();
								if (faqs_size != null && faqs_size.length()!=0)
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissIndustryMasterObject(ntcCalendars);
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
	return null;
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster> getxMissIndustryMasterObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissIndustryMaster> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissIndustryMaster missIndustryMaster : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster xmissIndustryMaster =new th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster ();
			BeanUtils.copyProperties(missIndustryMaster, xmissIndustryMaster);
			xmissIndustryMaster.setPagging(null);
			xntcCalendars.add(xmissIndustryMaster);
		}
		return xntcCalendars;
	} 
	/*private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
	}*/
 
	public MissIndustryMasterService getMissIndustryMasterService() {
		return missIndustryMasterService;
	}

	public void setMissIndustryMasterService(MissIndustryMasterService missIndustryMasterService) {
		this.missIndustryMasterService = missIndustryMasterService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}


}
