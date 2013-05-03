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
import th.co.aoe.makedev.missconsult.managers.MissDepartmentMasterService;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissDepartmentMasterResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissDepartmentMasterService missDepartmentMasterService;
	private com.thoughtworks.xstream.XStream xstream;
	
	public MissDepartmentMasterResource() {
		super();
		logger.debug("into constructor MissDepartmentMasterResource");
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
		logger.debug("into Post MissDepartmentMasterResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissDepartmentMaster bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissDepartmentMaster();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					 
						
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						 if(serviceName.equals(ServiceConstant.DEPARTMENT_MASTER_LIST)){
							//Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missDepartmentMasterService.listMissDepartmentMaster();
							if (result != null && result.size() >0) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissDepartmentMaster> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissDepartmentMaster>) result;
										
								String faqs_size = ntcCalendars.size()+"";
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissDepartmentMasterObject(ntcCalendars);
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
	private List<th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster> getxMissDepartmentMasterObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissDepartmentMaster> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissDepartmentMaster missDepartmentMaster : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster xmissDepartmentMaster =new th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster ();
			BeanUtils.copyProperties(missDepartmentMaster, xmissDepartmentMaster); 
			 
			xmissDepartmentMaster.setPagging(null);
			xntcCalendars.add(xmissDepartmentMaster);
		}
		return xntcCalendars;
	} 
	/*private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissDepartmentMaster>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
	}*/
 
	public MissDepartmentMasterService getMissDepartmentMasterService() {
		return missDepartmentMasterService;
	}

	public void setMissDepartmentMasterService(MissDepartmentMasterService missDepartmentMasterService) {
		this.missDepartmentMasterService = missDepartmentMasterService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

}
