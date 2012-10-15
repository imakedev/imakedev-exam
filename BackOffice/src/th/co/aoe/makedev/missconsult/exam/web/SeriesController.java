// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:05:35 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SeriesController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.exam.form.SeriesForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.exam.utils.IMakeDevUtils;
import th.co.aoe.makedev.missconsult.xstream.MissManual;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesMap;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/series"})
@SessionAttributes(value={"seriesForm"})
public class SeriesController
{
	private static int PAGE_SIZE=20;
  /*  @Autowired
    public SeriesController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired SeriesController #######################");
        this.missExamService = missExamService;
    }*/

    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String init(Model model)
    {
        model.addAttribute("missExams", missExamService.listMissExam());
        SeriesForm seriesForm = new SeriesForm();
        seriesForm.getMissSery().getPagging().setPageSize(PAGE_SIZE);
        VResultMessage vresultMessage = missExamService.searchMissSery(seriesForm.getMissSery());
        model.addAttribute("missSeries", vresultMessage.getResultListObj());
        seriesForm.getPaging().setPageSize(PAGE_SIZE);
        seriesForm.setPageCount(IMakeDevUtils.calculatePage(seriesForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("seriesForm", seriesForm);
        return "exam/template/seriesSearch";
    }

    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doSearch(HttpServletRequest request, @ModelAttribute(value="seriesForm") SeriesForm seriesForm, BindingResult result, Model model)
    {
        String mode = seriesForm.getMode();
        String missExam_selectboxes[] = request.getParameterValues("missExam_selectbox");
        seriesForm.getMissSery().setMsSeriesName(seriesForm.getMsSeriesName());
        seriesForm.getMissSery().setMsUnitCost(seriesForm.getMsUnitCost());
        if(mode != null && mode.equals("deleteItems"))
        {
            seriesForm.getMissSery().setMsIds(seriesForm.getMsIdArray());
            missExamService.deleteMissSery(seriesForm.getMissSery(), "deleteMissSeryItems");
            seriesForm.getPaging().setPageNo(1);
        } else
        if(mode != null && mode.equals("delete")){
            missExamService.deleteMissSery(seriesForm.getMissSery(), "deleteMissSery");
            seriesForm.getPaging().setPageNo(1);
        }else
        if(mode != null && mode.equals("doBack"))
        {
            if(model.containsAttribute("seriesForm"))
                seriesForm = (SeriesForm)model.asMap().get("seriesForm");
            else
                seriesForm = new SeriesForm();
            missExam_selectboxes = seriesForm.getMissExam_selectbox();
        }
        model.addAttribute("missExams", missExamService.listMissExam());
        seriesForm.setMissExam_selectbox(missExam_selectboxes);
        seriesForm.getMissSery().setMeIds(missExam_selectboxes);
        seriesForm.getPaging().setPageSize(PAGE_SIZE);
        logger.debug((new StringBuilder("xxxx=seriesForm.getMissSery().getPagging()=")).append(seriesForm.getMissSery().getPagging()).toString());
        logger.debug((new StringBuilder("xxxx=seriesForm.getPaging()=")).append(seriesForm.getPaging()).toString());
        seriesForm.getMissSery().setPagging(seriesForm.getPaging());
        VResultMessage vresultMessage = missExamService.searchMissSery(seriesForm.getMissSery());
        String meIdArray = "";
        if(missExam_selectboxes != null && missExam_selectboxes.length > 0)
        {
            int meId_size = missExam_selectboxes.length;
            String meIds[] = missExam_selectboxes;
            for(int i = 0; i < meId_size; i++)
                if(i != meId_size - 1)
                    meIdArray = (new StringBuilder(String.valueOf(meIdArray))).append(meIds[i]).append(",").toString();
                else
                    meIdArray = (new StringBuilder(String.valueOf(meIdArray))).append(meIds[i]).toString();

        }
        seriesForm.setPageCount(IMakeDevUtils.calculatePage(seriesForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("meIdArray", meIdArray);
        model.addAttribute("missSeries", vresultMessage.getResultListObj());
        model.addAttribute("seriesForm", seriesForm);
        return "exam/template/seriesSearch";
    }

    @RequestMapping(value={"/item/{msId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getItem(@PathVariable String msId, Model model)
    {
        SeriesForm seriesForm = null;
        if(model.containsAttribute("seriesForm"))
            seriesForm = (SeriesForm)model.asMap().get("seriesForm");
        else
            seriesForm = new SeriesForm();
        seriesForm.setMode("edit");
        MissSery missSery = missExamService.findMissSeryById(Long.valueOf(Long.parseLong(msId)));
        MissManual missManual=missExamService.findMissManualById(Long.parseLong(msId));
        if(missManual!=null){
        	missSery.setManualFile(missManual.getMmFileName());
        	missSery.setManualFileHotlink(missManual.getMmHotlink());
        }
        MissSeriesAttach missSeriesAttach =missExamService.findMissSeriesAttachSearch("template",Long.parseLong(msId),null,null);
        if(missSeriesAttach!=null){
        	missSery.setTemplateFile(missSeriesAttach.getMsatFileName());
        	missSery.setTemplateFileHotlink(missSeriesAttach.getMsatHotlink());
        }
        MissSeriesAttach missSeriesEval =missExamService.findMissSeriesAttachSearch("evaluation",Long.parseLong(msId),null,null);
        if(missSeriesEval!=null){
        	missSery.setEvalFile(missSeriesEval.getMsatFileName());
        	missSery.setEvalFileHotlink(missSeriesEval.getMsatHotlink());
        }
        seriesForm.setMissSery(missSery);
        MissSeriesMap missSeriesMap = new MissSeriesMap();
        missSeriesMap.setMsId(Long.valueOf(Long.parseLong(msId)));
        VResultMessage vresultMessage = missExamService.searchMissSeriesMap(missSeriesMap);
        List missSeriesMaps = vresultMessage.getResultListObj();
        StringBuffer sb = new StringBuffer();
        int index = 0;
        /*private String msatFileName;
		private String msatHotlink;
		private String msatModule;
		private String msatPath;*/
      
        if(missSeriesMaps != null && missSeriesMaps.size() > 0)
        {
            int size = missSeriesMaps.size();
            for(Iterator iterator = missSeriesMaps.iterator(); iterator.hasNext();)
            {
            String msatFileName="noFile";
            String msatHotlink="noHotlink";
            String msatModule="noModule";
            String msatPath="noPath";
                MissSeriesMap entry = (MissSeriesMap)iterator.next();
               /* if(index != size - 1)
                    sb.append((new StringBuilder()).append(entry.getMeId()).append(",").toString());
                else
                    sb.append((new StringBuilder()).append(entry.getMeId()).toString());*/
               if(entry.getMsatFileName()!=null && entry.getMsatFileName().length()>0)
            	   msatFileName=entry.getMsatFileName();
               if(entry.getMsatHotlink()!=null && entry.getMsatHotlink().length()>0)
            	   msatHotlink=entry.getMsatHotlink();
               if(entry.getMsatModule()!=null && entry.getMsatModule().length()>0)
            	   msatModule=entry.getMsatModule();
               if(entry.getMsatPath()!=null && entry.getMsatPath().length()>0)
            	   msatPath=entry.getMsatPath();
                // meid|filename|hotlink|module|path
                if(index != size - 1)
                    sb.append(entry.getMeId()+"|"+msatFileName+"|"+msatHotlink+"|"+msatModule+"|"+msatPath+",");
                else
                    sb.append(entry.getMeId()+"|"+msatFileName+"|"+msatHotlink+"|"+msatModule+"|"+msatPath);
                index++;
            }

        }
        model.addAttribute("missSeriesMap", sb);
        model.addAttribute("missExams", missExamService.listMissExam());
        model.addAttribute("seriesForm", seriesForm);
        model.addAttribute("display", "display: none");
        return "exam/template/seriesManagement";
    }

    @RequestMapping(value={"/new"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm(Model model)
    {
        SeriesForm seriesForm = null;
        if(model.containsAttribute("seriesForm"))
            seriesForm = (SeriesForm)model.asMap().get("seriesForm");
        else
            seriesForm = new SeriesForm();
        seriesForm.setMissSery(new MissSery());
        seriesForm.setMode("new");
        model.addAttribute("missExams", missExamService.listMissExam());
        model.addAttribute("display", "display: none");
        return "exam/template/seriesManagement";
    }

    @RequestMapping(value={"/action"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doAction(HttpServletRequest request, @ModelAttribute(value="seriesForm") SeriesForm seriesForm, BindingResult result, Model model)
    {
        String mode = seriesForm.getMode();
        String message = "";
        String missExam_mapping[] = request.getParameterValues("missExam_mapping");
       // logger.debug((new StringBuilder("missExam_mapping=")).append(missExam_mapping).toString());
        String missSeriesMap = request.getParameter("missSeriesMap");
        //logger.debug((new StringBuilder("missSeriesMap=")).append(missSeriesMap).toString());
        Map checkDuplicate = new HashMap();
        List list = new ArrayList();
        if(missSeriesMap != null && missSeriesMap.length() > 0)
        {
            String ids[] = missSeriesMap.split(",");
            for(int i = 0; i < ids.length; i++)
            {
                String values[] = ids[i].split("_");
                if(!checkDuplicate.containsKey(values[1]))
                {
                    checkDuplicate.put(values[1], values[1]);
                    list.add(values[1]);
                }
            }

        }
      //  StringBuffer sb = new StringBuffer();
        String meIds[] = (String[])list.toArray(new String[list.size()]);
        seriesForm.getMissSery().setMeIds(meIds);
       // int meIds_size = meIds.length;
       /* for(int i = 0; i < meIds_size; i++)
        {
          //  logger.debug((new StringBuilder(" xxx ")).append(meIds[i]).toString());
            
            if(i != meIds_size - 1)
                sb.append(meIds[i]+",");
            else
                sb.append(meIds[i]);
        }*/

      //  model.addAttribute("missSeriesMap", sb);
        Long id =null;
        if(mode != null)
            if(mode.equals("new"))
            {
                id = missExamService.saveMissSery(seriesForm.getMissSery());
                seriesForm.getMissSery().setMsId(id);
                seriesForm.setMode("edit");
                message = "Save success !";
            } else
            if(mode.equals("edit"))
            {
                missExamService.updateMissSery(seriesForm.getMissSery());
                id=seriesForm.getMissSery().getMsId();
                message = "Update success !";
            }
        
        MissSeriesMap missSeriesMapObj = new MissSeriesMap();
        missSeriesMapObj.setMsId(id);
        VResultMessage vresultMessage = missExamService.searchMissSeriesMap(missSeriesMapObj);
        List missSeriesMaps = vresultMessage.getResultListObj();
        StringBuffer sb = new StringBuffer();
        int index = 0;
        /*private String msatFileName;
		private String msatHotlink;
		private String msatModule;
		private String msatPath;*/
      
        if(missSeriesMaps != null && missSeriesMaps.size() > 0)
        {
            int size = missSeriesMaps.size();
            for(Iterator iterator = missSeriesMaps.iterator(); iterator.hasNext();)
            {
            String msatFileName="noFile";
            String msatHotlink="noHotlink";
            String msatModule="noModule";
            String msatPath="noPath";
                MissSeriesMap entry = (MissSeriesMap)iterator.next();
               /* if(index != size - 1)
                    sb.append((new StringBuilder()).append(entry.getMeId()).append(",").toString());
                else
                    sb.append((new StringBuilder()).append(entry.getMeId()).toString());*/
               if(entry.getMsatFileName()!=null && entry.getMsatFileName().length()>0)
            	   msatFileName=entry.getMsatFileName();
               if(entry.getMsatHotlink()!=null && entry.getMsatHotlink().length()>0)
            	   msatHotlink=entry.getMsatHotlink();
               if(entry.getMsatModule()!=null && entry.getMsatModule().length()>0)
            	   msatModule=entry.getMsatModule();
               if(entry.getMsatPath()!=null && entry.getMsatPath().length()>0)
            	   msatPath=entry.getMsatPath();
                // meid|filename|hotlink|module|path
                if(index != size - 1)
                    sb.append(entry.getMeId()+"|"+msatFileName+"|"+msatHotlink+"|"+msatModule+"|"+msatPath+",");
                else
                    sb.append(entry.getMeId()+"|"+msatFileName+"|"+msatHotlink+"|"+msatModule+"|"+msatPath);
                index++;
            }

        }
        model.addAttribute("missSeriesMap", sb);
        model.addAttribute("message", message);
        model.addAttribute("display", "display: block");
        model.addAttribute("missExams", missExamService.listMissExam());
        model.addAttribute("seriesForm", seriesForm);
        return "exam/template/seriesManagement";
    }

    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
