package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.service.AdminUserService;
import com.xxx.pcam.utils.LoginUserUtil;
import com.xxx.pcam.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {

    @Resource
    private AdminUserService adminUserService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("register")
    public String register(){
        System.out.println(1);
        return "register";
    }

    @RequestMapping("main")
    public String main(HttpServletRequest request){

        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //查询用户对象，设置session作用域
        User user = adminUserService.selectByPrimaryKey(userId);
        request.getSession().setAttribute("user",user);

        return "admin/main";
    }

    @RequestMapping("main1")
    public String main1(HttpServletRequest request){

        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //查询用户对象，设置session作用域
        User user = adminUserService.selectByPrimaryKey(userId);
        request.getSession().setAttribute("user",user);

        return "main1";
    }
    @RequestMapping("main2")
    public String main2(HttpServletRequest request){

        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //查询用户对象，设置session作用域
        User user = adminUserService.selectByPrimaryKey(userId);
        request.getSession().setAttribute("user",user);

        return "client/main2";
    }

}
