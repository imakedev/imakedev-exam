package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.ServiceReportService;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class ServiceReportResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private ServiceReportService serviceReportService;
	private com.thoughtworks.xstream.XStream xstream; 
	public ServiceReportResource() {
		super();
		logger.debug("into constructor ServiceReportResource");
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
		logger.debug("into Post ServiceReportResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.ServiceReport.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.ServiceReport xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.ServiceReport();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.ServiceReport) ntcCalendarObj;
				if (xbpsTerm != null) { 
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.SERVICE_REPORT_FIND)){
							xbpsTerm = serviceReportService.findServiceReport(xbpsTerm.getMode(),xbpsTerm.getMonth(),xbpsTerm.getYear());
						//logger.debug(" object return ="+ntcCalendarReturn);
						VResultMessage vresultMessage = new VResultMessage();
							if(xbpsTerm!=null){
								List<th.co.aoe.makedev.missconsult.xstream.ServiceReport> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.ServiceReport>(1);
								xbpsTerm.setPagging(null);							 
								xntcCalendars.add(xbpsTerm);
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
	public ServiceReportService getServiceReportService() {
		return serviceReportService;
	}

	public void setServiceReportService(ServiceReportService serviceReportService) {
		this.serviceReportService = serviceReportService;
	}

	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}


}
