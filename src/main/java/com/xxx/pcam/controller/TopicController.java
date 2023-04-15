package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.query.TopicQuery;
import com.xxx.pcam.service.TopicService;
import com.xxx.pcam.utils.LoginUserUtil;
import com.xxx.pcam.vo.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 心理测试控制器
 */
@Controller
@RequestMapping("topic")
public class TopicController extends BaseController {

    @Resource
    private TopicService topicService;

    @RequestMapping("topicView")
    public String topicView() {
        return "admin/topic/topic";
    }

    /**
     * 分页多条件查询
     * @param topicQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(TopicQuery topicQuery){
        return topicService.queryByParams(topicQuery);
    }

    /**
     * 进入添加/修改页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toPage")
    public String toPage(Integer id, HttpServletRequest request) {
        // 判断Id是否为空
        if (id != null) {
            // 通过ID查询营销机会数据
            Topic topic = topicService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("topic",topic);
        }

        return "admin/topic/add_update";
    }

    /**
     * 添加
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo add(Topic topic,HttpServletRequest request) {
        String userName = LoginUserUtil.releaseUserNameFromCookie(request);
        topic.setUserOp(userName);
        topicService.add(topic);
        return success("数据添加成功！");
    }

    /**
     * 删除
     *
     * @param ids
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteClient(Integer[] ids) {
        // 调用Service层的删除方法
        topicService.deleteBatch(ids);
        return success("数据删除成功！");
    }

    /**
     * 修改
     *
     * @param topic
     * @return com.xxx.pcam.base.ResultInfo
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Topic topic) {
        // 调用Service层的添加方法
        topicService.update(topic);
        return success("数据更新成功！");
    }
}
