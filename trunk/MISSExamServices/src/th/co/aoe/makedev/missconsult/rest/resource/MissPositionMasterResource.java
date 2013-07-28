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
import th.co.aoe.makedev.missconsult.managers.MissPositionMasterService;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissPositionMasterResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissPositionMasterService missPositionMasterService;
	private com.thoughtworks.xstream.XStream xstream;
	private static final String[] ignore_id={"missDepartmentMaster"};

	public MissPositionMasterResource() {
		super();
		logger.debug("into constructor MissPositionMasterResource");
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
		logger.debug("into Post MissPositionMasterResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissPositionMaster.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissPositionMaster xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissPositionMaster();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissPositionMaster) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissPositionMaster bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissPositionMaster();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					if(xbpsTerm.getMissDepartmentMaster()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissDepartmentMaster missDepartmentMaster = new th.co.aoe.makedev.missconsult.hibernate.bean.MissDepartmentMaster();
						BeanUtils.copyProperties(xbpsTerm.getMissDepartmentMaster(),missDepartmentMaster); 
						bpsTerm.setMissDepartmentMaster(missDepartmentMaster);
					}
						
					
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						 if(serviceName.equals(ServiceConstant.POSITION_MASTER_LIST)){
							//Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missPositionMasterService.listMissPositionMaster();
							if (result != null && result.size() >0) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissPositionMaster> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissPositionMaster>) result;
										
								String faqs_size = ntcCalendars.size()+"";
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissPositionMaster> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissPositionMaster>();
								if (faqs_size != null && faqs_size.length()!=0)
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissPositionMasterObject(ntcCalendars);
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
	private List<th.co.aoe.makedev.missconsult.xstream.MissPositionMaster> getxMissPositionMasterObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissPositionMaster> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissPositionMaster> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissPositionMaster>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissPositionMaster missPositionMaster : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissPositionMaster xmissPositionMaster =new th.co.aoe.makedev.missconsult.xstream.MissPositionMaster ();
			BeanUtils.copyProperties(missPositionMaster, xmissPositionMaster,ignore_id); 
			if(missPositionMaster.getMissDepartmentMaster()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster xmissDepartmentMaster = new th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster();
				BeanUtils.copyProperties(missPositionMaster.getMissDepartmentMaster(),xmissDepartmentMaster); 
				xmissPositionMaster.setMissDepartmentMaster(xmissDepartmentMaster);
			}
			xmissPositionMaster.setPagging(null);
			xntcCalendars.add(xmissPositionMaster);
		}
		return xntcCalendars;
	} 
	/*private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissPositionMaster xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissPositionMaster> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissPositionMaster>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
	}*/
 
	public MissPositionMasterService getMissPositionMasterService() {
		return missPositionMasterService;
	}

	public void setMissPositionMasterService(MissPositionMasterService missPositionMasterService) {
		this.missPositionMasterService = missPositionMasterService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

}
