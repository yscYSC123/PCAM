package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.query.MessageQuery;
import com.xxx.pcam.service.AdminClientService;
import com.xxx.pcam.service.MessageService;
import com.xxx.pcam.utils.LoginUserUtil;
import com.xxx.pcam.vo.Message;
import com.xxx.pcam.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("message")
public class MessageController extends BaseController {

    @Resource
    private MessageService messageService;
    @Resource
    private AdminClientService adminClientService;

    /**
     * 进入收信箱页面
     */
    @RequestMapping("index1")
    public String index1(){
        return "mailbox/receiver";
    }

    /**
     * 进入发信箱页面
     */
    @RequestMapping("index2")
    public String index2(){
        return "mailbox/adminSend";
    }

    /**
     * 进入发信箱页面
     */
    @RequestMapping("index3")
    public String index3(){
        return "mailbox/clientSend";
    }

    /**
     * 进入发信箱页面
     */
    @RequestMapping("index4")
    public String index4(){
        return "mailbox/doctorSend";
    }

    /**
     * 分页多条件查询
     * @param messageQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(MessageQuery messageQuery,HttpServletRequest request){
        String name = LoginUserUtil.releaseUserNameFromCookie(request);
        messageQuery.setReceiverName(name);
        return messageService.queryByParams(messageQuery);
    }

    /**
     * 删除信息
     *
     * @param ids
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteClient(Integer[] ids) {
        // 调用Service层的删除方法
        messageService.deleteBatch(ids);
        return success("数据删除成功！");
    }

    /**
     * 标记
     *
     * @param message
     * @return com.xxx.pcam.base.ResultInfo
     */
    @PostMapping("mark")
    @ResponseBody
    public ResultInfo mark(Message message) {
        // 调用Service层的添加方法
        message.setIsRead(1);
        messageService.mark(message);
        return success("数据更新成功！");
    }

    /**
     * 进入回复页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toSendPage")
    public String toPage(Integer id, HttpServletRequest request) {
        // 判断Id是否为空
        if (id != null) {
            // 通过ID查询营销机会数据
            Message message = messageService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("message",message);
        }

        return "mailbox/toSendPage";
    }

    /**
     * 进入回复页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toSendPage1")
    public String toPage1(Integer id, HttpServletRequest request,Message message) {
        String name = LoginUserUtil.releaseUserNameFromCookie(request);
        // 判断Id是否为空
        if (id != null) {
            User send = adminClientService.name(name);
            // 通过ID查询营销机会数据
            User client = adminClientService.selectByPrimaryKey(id);
            message.setReceiverName(client.getUserName());
            message.setReceiverId(client.getId());
            message.setReceiver(client.getLevel());
            message.setSenderName(send.getUserName());
            message.setSenderId(send.getId());
            message.setSender(send.getLevel());
            // 将数据设置到请求域中
            request.setAttribute("message",message);

        }

        return "mailbox/toSendPage1";
    }

    /**
     * 添加
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo add(Message message) {
        message.setId(null);
        message.setIsRead(0);
        // 判断Id是否为空
        messageService.add(message);
        return success("数据添加成功！");
    }

}
