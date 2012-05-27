package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.MissExamGroupService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissExamGroupResource	extends BaseResource {
		private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
		private MissExamGroupService missExamGroupService;
		private com.thoughtworks.xstream.XStream xstream;
		 
		public MissExamGroupResource() {
			super();
			logger.debug("into constructor MissExamGroupResource");
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
			logger.debug("into Post MissExamGroupResource 2");
			InputStream in = null;
			try {
				in = entity.getStream();
				xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissExamGroup.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
				th.co.aoe.makedev.missconsult.xstream.MissExamGroup xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissExamGroup();
				Object ntcCalendarObj = xstream.fromXML(in);
				if (ntcCalendarObj != null) {
					xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissExamGroup) ntcCalendarObj;
					if (xbpsTerm != null) {
						th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup();
						BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
						
						if (xbpsTerm.getServiceName() != null
								&& !xbpsTerm.getServiceName().equals("")) {
							logger.debug(" BPS servicename = "
									+ xbpsTerm.getServiceName());
							String serviceName = xbpsTerm.getServiceName();
							if(serviceName.equals(ServiceConstant.MISS_EXAM_GROUP_FIND_BY_ID)){
								th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup ntcCalendarReturn = missExamGroupService.findMissExamGroupById(bpsTerm.getMegId());
							logger.debug(" object return ="+ntcCalendarReturn);
								if(ntcCalendarReturn!=null){
									VResultMessage vresultMessage = new VResultMessage();
									List<th.co.aoe.makedev.missconsult.xstream.MissExamGroup> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamGroup>(1);
									th.co.aoe.makedev.missconsult.xstream.MissExamGroup xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissExamGroup();
									BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
									xntcCalendarReturn.setPagging(null);
									xntcCalendars.add(xntcCalendarReturn);
									vresultMessage.setResultListObj(xntcCalendars);
									return getRepresentation(entity, vresultMessage, xstream);
								}
							} 
							if(serviceName.equals(ServiceConstant.MISS_EXAM_GROUP_SAVE)){
								java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
								int updateRecord=(missExamGroupService.saveMissExamGroup(bpsTerm)).intValue();
								returnUpdateRecord(entity,xbpsTerm,updateRecord);
							}
							else if(serviceName.equals(ServiceConstant.MISS_EXAM_GROUP_UPDATE)){
								java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
								int updateRecord=missExamGroupService.updateMissExamGroup(bpsTerm);
								returnUpdateRecord(entity,xbpsTerm,updateRecord);
							}
							else if(serviceName.equals(ServiceConstant.MISS_EXAM_GROUP_DELETE)){
								int updateRecord=missExamGroupService.deleteMissExamGroup(bpsTerm);
								returnUpdateRecord(entity,xbpsTerm,updateRecord);
							}
							else if(serviceName.equals(ServiceConstant.MISS_EXAM_GROUP_SEARCH)){
								Pagging page = xbpsTerm.getPagging(); 
								List result = (List) missExamGroupService.searchMissExamGroup(bpsTerm,page);
								if (result != null && result.size() == 2) {
									java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup>) result
											.get(0);
									String faqs_size = (String) result.get(1);
//									 
									VResultMessage vresultMessage = new VResultMessage();

									List<th.co.aoe.makedev.missconsult.xstream.MissExamGroup> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamGroup>();
									if (faqs_size != null && !faqs_size.equals(""))
										vresultMessage.setMaxRow(faqs_size);
									if (ntcCalendars != null && ntcCalendars.size() > 0) {
										xntcCalendars = getxMissExamGroupObject(ntcCalendars);
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
		protected Representation post(Representation entity)
				throws ResourceException {
			// TODO Auto-generated method stub
			logger.debug("into Post MissExamGroupResource");
			InputStream in = null;
			try {
				in = entity.getStream();
				xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissExamGroup.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
				th.co.aoe.makedev.missconsult.xstream.MissExamGroup xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissExamGroup();
				Object ntcCalendarObj = xstream.fromXML(in);
				if (ntcCalendarObj != null) {
					xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissExamGroup) ntcCalendarObj;
					if (xbpsTerm != null) {
						th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup();
						BeanUtils.copyProperties(bpsTerm, xbpsTerm); 
						
						if (xbpsTerm.getServiceName() != null
								&& !xbpsTerm.getServiceName().equals("")) {
							logger.debug(" BPS servicename = "
									+ xbpsTerm.getServiceName());
							String serviceName = xbpsTerm.getServiceName();
							if(serviceName.equals(ServiceConstant.MISS_EXAM_GROUP_FIND_BY_ID)){
								th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup ntcCalendarReturn = missExamGroupService.findMissExamGroupById(bpsTerm.getMegId());
								if(ntcCalendarReturn!=null){
									VResultMessage vresultMessage = new VResultMessage();
									List<th.co.aoe.makedev.missconsult.xstream.MissExamGroup> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamGroup>(1);
									th.co.aoe.makedev.missconsult.xstream.MissExamGroup xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissExamGroup();
									BeanUtils.copyProperties(xntcCalendarReturn, ntcCalendarReturn);								
									
									xntcCalendars.add(xntcCalendarReturn);
									vresultMessage.setResultListObj(xntcCalendars);
									export(entity, vresultMessage, xstream);
								}
							} 
							if(serviceName.equals(ServiceConstant.MISS_EXAM_GROUP_SAVE)){
								java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
								int updateRecord=(missExamGroupService.saveMissExamGroup(bpsTerm)).intValue();
								returnUpdateRecord(entity,xbpsTerm,updateRecord);
							}
							else if(serviceName.equals(ServiceConstant.MISS_EXAM_GROUP_UPDATE)){
								java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
								int updateRecord=missExamGroupService.updateMissExamGroup(bpsTerm);
								returnUpdateRecord(entity,xbpsTerm,updateRecord);
							}
							else if(serviceName.equals(ServiceConstant.MISS_EXAM_GROUP_DELETE)){
								int updateRecord=missExamGroupService.deleteMissExamGroup(bpsTerm);
								returnUpdateRecord(entity,xbpsTerm,updateRecord);
							}
							else if(serviceName.equals(ServiceConstant.MISS_EXAM_GROUP_SEARCH)){
								Pagging page = xbpsTerm.getPagging(); 
								List result = (List) missExamGroupService.searchMissExamGroup(bpsTerm,page);
								if (result != null && result.size() == 2) {
									java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup>) result
											.get(0);
									String faqs_size = (String) result.get(1);
//									 
									VResultMessage vresultMessage = new VResultMessage();

									List<th.co.aoe.makedev.missconsult.xstream.MissExamGroup> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamGroup>();
									if (faqs_size != null && !faqs_size.equals(""))
										vresultMessage.setMaxRow(faqs_size);
									if (ntcCalendars != null && ntcCalendars.size() > 0) {
										xntcCalendars = getxMissExamGroupObject(ntcCalendars);
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
			logger.debug("test2"+variant.getMediaType()+","+MediaType.TEXT_PLAIN);
			logger.debug("into GET MissExamGroupResource");
			// Representation result = null;
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup ntcCalendarReturn = missExamGroupService.findMissExamGroupById(new Long(1));
			 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
		        VResultMessage vresultMessage = new VResultMessage();
				List<th.co.aoe.makedev.missconsult.xstream.MissExamGroup> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamGroup>(1);
				th.co.aoe.makedev.missconsult.xstream.MissExamGroup xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissExamGroup();
				BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
				xntcCalendarReturn.setPagging(null);
			 
				xntcCalendars.add(xntcCalendarReturn);
				vresultMessage.setResultListObj(xntcCalendars);
				ntcCalendarReturn.setMegName("Aoe update");
				int updateRecord=missExamGroupService.updateMissExamGroup(ntcCalendarReturn);*/
				/* th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup ();
				xntcCalendarReturn_save.setMegName("save new");
				logger.debug("xxx="+updateRecord);
				missExamGroupService.saveMissExamGroup(xntcCalendarReturn_save);*/
				//returnUpdateRecord(entity,xbpsTerm,updateRecord);
				 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup ();
				 xntcCalendarReturn_delete.setMegId(new Long(3));
				missExamGroupService.deleteMissExamGroup(xntcCalendarReturn_delete);*/
				//return getRepresentation(null, vresultMessage, xstream);
			Pagging page =new Pagging(); 
			th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup();
			bpsTerm.setMegName("Aoe");
			List result = (List) missExamGroupService.searchMissExamGroup(bpsTerm,page);
			VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissExamGroup> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamGroup>();
			if (result != null && result.size() == 2) {
				java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup>) result
						.get(0);
				String faqs_size = (String) result.get(1);
//				 
			

			
				if (faqs_size != null && !faqs_size.equals(""))
					vresultMessage.setMaxRow(faqs_size);
				if (ntcCalendars != null && ntcCalendars.size() > 0) {
					xntcCalendars = getxMissExamGroupObject(ntcCalendars);
				}
			}
				vresultMessage.setResultListObj(xntcCalendars);
				return getRepresentation(null, vresultMessage, xstream);
		}
		private List<th.co.aoe.makedev.missconsult.xstream.MissExamGroup> getxMissExamGroupObject(
				java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup> ntcCalendars) {
			List<th.co.aoe.makedev.missconsult.xstream.MissExamGroup> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamGroup>(
					ntcCalendars.size());
			for (th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup missExamGroup : ntcCalendars) {
				th.co.aoe.makedev.missconsult.xstream.MissExamGroup xmissExamGroup =new th.co.aoe.makedev.missconsult.xstream.MissExamGroup ();
				BeanUtils.copyProperties(missExamGroup, xmissExamGroup);
				xmissExamGroup.setPagging(null);
				xntcCalendars.add(xmissExamGroup);
			}
			return xntcCalendars;
		} 
		private void returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissExamGroup xbpsTerm,int updateRecord){
			VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissExamGroup> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExamGroup>(1);
			xbpsTerm.setUpdateRecord(updateRecord);
			xbpsTerms.add(xbpsTerm);
			vresultMessage.setResultListObj(xbpsTerms);
			export(entity, vresultMessage, xstream);
		}
	 
		public MissExamGroupService getMissExamGroupService() {
			return missExamGroupService;
		}

		public void setMissExamGroupService(MissExamGroupService missExamGroupService) {
			this.missExamGroupService = missExamGroupService;
		}
		public com.thoughtworks.xstream.XStream getXstream() {
			return xstream;
		}

		public void setXstream(com.thoughtworks.xstream.XStream xstream) {
			this.xstream = xstream;
		}

		
}
