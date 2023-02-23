package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.exceptions.ParamsException;
import com.xxx.pcam.model.UserModel;
import com.xxx.pcam.service.AdminUserService;
import com.xxx.pcam.utils.LoginUserUtil;
import com.xxx.pcam.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class AdminUserController extends BaseController{

    @Resource
    private AdminUserService adminUserService;

    /**
     * 用户登录
     * @param userName
     * @param userPwd
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName,String userPwd){
        ResultInfo resultInfo = new ResultInfo();

        try {
            //调用登录方法
            UserModel userModel = adminUserService.userLogin(userName,userPwd);
            //
            resultInfo.setResult(userModel);

        }catch (ParamsException p){
            resultInfo.setCode(p.getCode());
            resultInfo.setMsg(p.getMsg());
            p.printStackTrace();
        }catch (Exception e){
            resultInfo.setResult(500);
            resultInfo.setMsg("登录失败！");
        }

        return resultInfo;
    }

    /**
     * 用户注册
     */
    @PostMapping("toRegister")
    @ResponseBody
    public ResultInfo register(User user) {
        System.out.println(2);
        adminUserService.register(user);
        return success("注册成功！");
    }


    /**
     * 用户修改密码
     * @param request
     * @param oldPwd
     * @param newPwd
     * @param repeatPwd
     * @return
     */
    @PostMapping("updatePwd")
    @ResponseBody
    public ResultInfo updatePwd(HttpServletRequest request,String oldPwd,String newPwd,String repeatPwd){
        ResultInfo resultInfo = new ResultInfo();

       try {
            // 获取cookie中的userId
            Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
            // 调用Service层修改密码方法
            adminUserService.updatePwd(userId, oldPwd, newPwd, repeatPwd);

        } catch (ParamsException p) {
            resultInfo.setCode(p.getCode());
            resultInfo.setMsg(p.getMsg());
            p.printStackTrace();
        } catch (Exception e) {
            resultInfo.setCode(500);
            resultInfo.setMsg("修改密码失败！");
            e.printStackTrace();
        }

        return resultInfo;
    }

    /**
     * 进入修改密码的页面
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toPasswordPage")
    public String toPasswordPage() {

        return "admin/user/password";
    }

    /**
     * 进入个人信息的页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toSettingPage")
    public String toSettingPage(Integer id, HttpServletRequest request) {

        // 判断id是否为空，不为空表示更新操作，查询用户对象
        if (id != null) {
            // 通过id查询用户对象
            User user = adminUserService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("user",user);
        }
        return "admin/user/setting";
    }

}
