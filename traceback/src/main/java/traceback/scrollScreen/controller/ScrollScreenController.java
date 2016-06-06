package traceback.scrollScreen.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import traceback.common.Pager;
import traceback.scrollScreen.model.ActionDisplay;
import traceback.scrollScreen.service.KillRecordService;
import traceback.synchronization.model.KillRecord;


@Controller
public class ScrollScreenController
{
    Logger  logger = LogManager.getLogger(ScrollScreenController.class);
    
    @Autowired
    KillRecordService killRecordService;
    
    //in multi lines
    @RequestMapping("/scrollInit")
    public ModelAndView  list(Pager pager)
    {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        if(pager == null)
        {
            pager = new Pager();
        }
        
        List<ActionDisplay> actionDispalyList = killRecordService.queryActions(pager,paramMap);
        
        ModelAndView view = new ModelAndView("scrollScreen");
        view.addObject("actionDispalyList", actionDispalyList);
        view.addObject("pager",pager);
        return view;
    }

    
    @RequestMapping("/scrollInit2")
    public ModelAndView  listAll()
    {
        List<ActionDisplay> actionDispalyList = killRecordService.queryAllActions();
        
        ModelAndView view = new ModelAndView("scrollScreen_jpage");
        view.addObject("actionDispalyList", actionDispalyList);
        return view;
    }
    
    
    
    @RequestMapping("/scrollListRecord")
    public ModelAndView  listKillRecord(String pagesize)
    {
        if(StringUtils.isEmpty(pagesize))
        {
            pagesize = "5";
        }
        List<KillRecord> recordList = killRecordService.queryKillRecord();
        
        for(KillRecord record :recordList)
        {
            record.setMostRecent(recentDate(recentDate(record.getKillTime(),record.getCoarseSegmentTime()),recentDate(record.getBonePackTime(),record.getLegPackTime())));
        }
        
        ModelAndView view = new ModelAndView("scrollScreenRecord");
        view.addObject("recordList", recordList);
        view.addObject("pagesize",pagesize);
        return view;
    }
    
    
    private Date recentDate(Date first,Date second)
    {
        if(first == null)
        {
            return second;
        }
        else if(second == null)
        {
            return first;
        }
        else
        {
            return  first.getTime() >second.getTime() ? first:second;           
        }
    }
    

    public KillRecordService getKillRecordService()
    {
        return killRecordService;
    }

    public void setKillRecordService(KillRecordService killRecordService)
    {
        this.killRecordService = killRecordService;
    }
}
