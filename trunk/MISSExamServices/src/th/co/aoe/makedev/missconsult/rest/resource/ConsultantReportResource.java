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
import th.co.aoe.makedev.missconsult.managers.ConsultantReportService;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class ConsultantReportResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private ConsultantReportService consultantReportService;
	private com.thoughtworks.xstream.XStream xstream; 
	public ConsultantReportResource() {
		super();
		logger.debug("into constructor ConsultantReportResource");
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
		logger.debug("into Post ConsultantReportResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.ConsultantReport.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.ConsultantReport xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.ConsultantReport();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.ConsultantReport) ntcCalendarObj;
				if (xbpsTerm != null) { 
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.CONSULTANT_REPORT_FIND)){
							xbpsTerm = consultantReportService.findConsultantReport(xbpsTerm.getMode(),xbpsTerm.getMcontactId(),xbpsTerm.getMonth(),xbpsTerm.getYear());
						VResultMessage vresultMessage = new VResultMessage();
							if(xbpsTerm!=null){
								List<th.co.aoe.makedev.missconsult.xstream.ConsultantReport> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.ConsultantReport>(1);
								xbpsTerm.setPagging(null);							 
								xntcCalendars.add(xbpsTerm);
								vresultMessage.setResultListObj(xntcCalendars);
							}
							return getRepresentation(entity, vresultMessage, xstream);
						} else if(serviceName.equals(ServiceConstant.CONSULTANT_REPORT_GET_SALES)){
							xbpsTerm = consultantReportService.findSales(xbpsTerm.getQuery());
						VResultMessage vresultMessage = new VResultMessage();
							if(xbpsTerm!=null){
								List<th.co.aoe.makedev.missconsult.xstream.ConsultantReport> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.ConsultantReport>(1);
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
	public ConsultantReportService getConsultantReportService() {
		return consultantReportService;
	}

	public void setConsultantReportService(ConsultantReportService consultantReportService) {
		this.consultantReportService = consultantReportService;
	}

	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

}
