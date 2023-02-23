package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.query.UserQuery;
import com.xxx.pcam.service.AdminClientService;
import com.xxx.pcam.vo.Client;
import com.xxx.pcam.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("adminClient")
public class AdminClientController extends BaseController {

    @Resource
    private AdminClientService adminClientService;

    /**
     * 进入用户列表页面
     */
    @RequestMapping("index")
    public String index(){
        return "admin/client/client";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(UserQuery userQuery){
        return adminClientService.queryClientByParams(userQuery);
    }

    /**
     * 添加
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addClient(Client client) {
        adminClientService.addClient(client);
        return success("数据添加成功！");
    }

    /**
     * 进入添加/修改用户数据页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toClientPage")
    public String toClientPage(Integer id, HttpServletRequest request) {
        // 判断Id是否为空
        if (id != null) {
            // 通过ID查询营销机会数据
            User client = adminClientService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("client",client);
        }

        return "admin/client/add_update";
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteClient(Integer[] ids) {
        // 调用Service层的删除方法
        adminClientService.deleteBatch(ids);
        return success("营销机会数据删除成功！");
    }

    /**
     * 更新用户
     *
     * @param client
     * @return com.xxx.pcam.base.ResultInfo
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateClient(Client client) {
        // 调用Service层的添加方法
        adminClientService.updateClient(client);
        return success("数据更新成功！");
    }


}
