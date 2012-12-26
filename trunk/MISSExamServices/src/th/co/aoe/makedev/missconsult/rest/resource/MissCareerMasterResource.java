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
import th.co.aoe.makedev.missconsult.managers.MissCareerMasterService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissCareerMasterResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissCareerMasterService missCareerMasterService;
	private com.thoughtworks.xstream.XStream xstream;

	public MissCareerMasterResource() {
		super();
		logger.debug("into constructor MissCareerMasterResource");
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
		logger.debug("into Post MissCareerMasterResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissCareerMaster.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissCareerMaster xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissCareerMaster();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissCareerMaster) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissCareerMaster bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissCareerMaster();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						 if(serviceName.equals(ServiceConstant.CAREER_MASTER_LIST)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missCareerMasterService.listMissCareerMaster(xbpsTerm.getMcmRef());
							VResultMessage vresultMessage = new VResultMessage();
							if (result != null && result.size()>0) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissCareerMaster> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissCareerMaster>) result;
										
								String faqs_size = result.size()+"";
//								 
								

								List<th.co.aoe.makedev.missconsult.xstream.MissCareerMaster> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCareerMaster>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissCareerMasterObject(ntcCalendars);
								}
								vresultMessage.setResultListObj(xntcCalendars); 
							} 
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
	return null;
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissCareerMaster> getxMissCareerMasterObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissCareerMaster> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissCareerMaster> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCareerMaster>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissCareerMaster missCareerMaster : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissCareerMaster xmissCareerMaster =new th.co.aoe.makedev.missconsult.xstream.MissCareerMaster ();
			BeanUtils.copyProperties(missCareerMaster, xmissCareerMaster);
			xmissCareerMaster.setPagging(null);
			xntcCalendars.add(xmissCareerMaster);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissCareerMaster xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissCareerMaster> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCareerMaster>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissCareerMasterService getMissCareerMasterService() {
		return missCareerMasterService;
	}

	public void setMissCareerMasterService(MissCareerMasterService missCareerMasterService) {
		this.missCareerMasterService = missCareerMasterService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}


}
