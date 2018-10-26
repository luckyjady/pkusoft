package com.zzq.licm.controller;

import com.zzq.licm.po.Msg;
import com.zzq.licm.service.MsgService;
import com.zzq.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class MsgController {

    @Autowired
    private MsgService msgService;

    private static final Logger logger = LoggerFactory.getLogger(MsgController.class);

    @RequestMapping("/licm/addMsg")
    @ResponseBody
    public JsonResult addMsg(Msg msgEntry ){
        try {
            Date date = new Date();
            msgEntry.setReplyTime( date);
            msgService.insertSelective(msgEntry);
            return new JsonResult(true,"保存成功！");
        }catch (Exception e){
            logger.error("信息保存失败！"+ e.getMessage() );
            e.printStackTrace();
            return new JsonResult(false,"");
        }
    }

    @RequestMapping("/licm/getMsgById/{id}")
    @ResponseBody
    public JsonResult getMsgById(@PathVariable("id")String id){
        try {
            Msg msg = msgService.selectMsgById(id);
            return new JsonResult(true,msg);
        } catch (Exception e) {
            logger.error("查询失败！");
            return new JsonResult(false,"");
        }
    }

}
