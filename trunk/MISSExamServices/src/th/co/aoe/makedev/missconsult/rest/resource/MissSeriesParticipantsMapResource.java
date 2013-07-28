/**
 * 
 */
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
import th.co.aoe.makedev.missconsult.managers.MissSeriesParticipantsMapService;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

/**
 * @author OS -> root
 * @author -> IMake
 * @project -> MISSExamServices
 * @time -> May 27, 2013 11:15:08 AM  
 */
public class MissSeriesParticipantsMapResource  extends BaseResource {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissSeriesParticipantsMapService missSeriesParticipantsMapService;
	private com.thoughtworks.xstream.XStream xstream;
	private static final String[] ignore_id={"id","missSery"};
	 
	public MissSeriesParticipantsMapResource() {
		super();
		logger.debug("into constructor MissSeriesParticipantsMapResource");
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
		logger.debug("into Post MissSeriesParticipantsMapResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesParticipantsMap bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesParticipantsMap();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					if(xbpsTerm.getMissSery()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissSery sery=new th.co.aoe.makedev.missconsult.hibernate.bean.MissSery();
						BeanUtils.copyProperties(xbpsTerm.getMissSery(),sery);
						bpsTerm.setMissSery(sery);
					}
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesParticipantsMapPK pk = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesParticipantsMapPK();
					pk.setMsId(xbpsTerm.getMsId());
					pk.setMspmGroupAmount(xbpsTerm.getMspmGroupAmount());
					pk.setMspmGroupName(xbpsTerm.getMspmGroupName());
					pk.setMspmOrder(xbpsTerm.getMspmOrder());
					bpsTerm.setId(pk);
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName(); 
						 if(serviceName.equals(ServiceConstant.MISS_SERIES_PARTICIPANTS_MAP_UPDATE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missSeriesParticipantsMapService.updateMissSeriesParticipantsMap(xbpsTerm.getMsId(), xbpsTerm.getMspmOrderArray(), xbpsTerm.getMspmGroupAmountArray(), xbpsTerm.getMspmGroupNameArray());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_PARTICIPANTS_MAP_DELETE)){
							
							missSeriesParticipantsMapService.deleteMissSeriesParticipantsMap(xbpsTerm.getMsId(),xbpsTerm.getMspmOrder());
							@SuppressWarnings("rawtypes")
							List result = (List) missSeriesParticipantsMapService.getMissSeriesParticipantsMap(xbpsTerm.getMsId(), 5); 
								VResultMessage vresultMessage = new VResultMessage(); 
								vresultMessage.setResultListObj(result);
								return getRepresentation(entity, vresultMessage, xstream);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_PARTICIPANTS_MAP_GET)){ 
							//Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missSeriesParticipantsMapService.getMissSeriesParticipantsMap(xbpsTerm.getMsId(), 5); 
								VResultMessage vresultMessage = new VResultMessage(); 
								vresultMessage.setResultListObj(result);
								return getRepresentation(entity, vresultMessage, xstream);
							
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERIES_PARTICIPANTS_MAP_LIST)){ 
							//Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missSeriesParticipantsMapService.listMissSeriesParticipantsMap(xbpsTerm.getMsId()); 
								VResultMessage vresultMessage = new VResultMessage(); 
								vresultMessage.setResultListObj(result);
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
		return null;
	}
	/*private List<th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap> getxMissSeriesParticipantsMapObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesParticipantsMap> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesParticipantsMap missSeriesParticipantsMap : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap xmissSeriesParticipantsMap =new th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap ();
			BeanUtils.copyProperties(missSeriesParticipantsMap, xmissSeriesParticipantsMap,ignore_id);
			if(missSeriesParticipantsMap.getMissSery()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissSery sery=new th.co.aoe.makedev.missconsult.xstream.MissSery();
				BeanUtils.copyProperties(missSeriesParticipantsMap.getMissSery(),sery);
				xmissSeriesParticipantsMap.setMissSery(sery);
			} 	
			th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesParticipantsMapPK pk_id  =missSeriesParticipantsMap.getId();
			xmissSeriesParticipantsMap.setMraLang(pk_id.getMraLang());
			xmissSeriesParticipantsMap.setMsId(pk_id.getMsId());
			xmissSeriesParticipantsMap.setMsOrder(pk_id.getMsOrder());
			 
			xmissSeriesParticipantsMap.setPagging(null);
			 
			xntcCalendars.add(xmissSeriesParticipantsMap);
		}
		return xntcCalendars;
	} */
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissSeriesParticipantsMapService getMissSeriesParticipantsMapService() {
		return missSeriesParticipantsMapService;
	}

	public void setMissSeriesParticipantsMapService(MissSeriesParticipantsMapService missSeriesParticipantsMapService) {
		this.missSeriesParticipantsMapService = missSeriesParticipantsMapService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}


}
