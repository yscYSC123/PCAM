package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.exceptions.ParamsException;
import com.xxx.pcam.model.UserModel;
import com.xxx.pcam.query.CountQuery;
import com.xxx.pcam.service.AdminDoctorService;
import com.xxx.pcam.service.AdminUserService;
import com.xxx.pcam.utils.LoginUserUtil;
import com.xxx.pcam.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class AdminUserController extends BaseController{

    @Resource
    private AdminUserService adminUserService;


    static String upload = "";

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
     * 用户退出
     * @param request
     * @param response
     */
    @PostMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        // 删除会话信息
        request.getSession().invalidate();

        // 删除JSESSIONID cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
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
    public ResultInfo updatePwd(HttpServletRequest request,String oldPwd,String newPwd,String repeatPwd, HttpServletResponse response){
        ResultInfo resultInfo = new ResultInfo();

       try {
            // 获取cookie中的userId
            Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
            // 调用Service层修改密码方法
            adminUserService.updatePwd(userId, oldPwd, newPwd, repeatPwd);
           // 删除会话信息
           request.getSession().invalidate();

           // 删除JSESSIONID cookie
           Cookie[] cookies = request.getCookies();
           if (cookies != null) {
               for (Cookie cookie : cookies) {
                   if ("JSESSIONID".equals(cookie.getName())) {
                       cookie.setMaxAge(0);
                       cookie.setPath("/");
                       response.addCookie(cookie);
                       break;
                   }
               }
           }

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

    @RequestMapping("upload")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile file) {
        upload = AdminDoctorService.upload(file);
        HashMap<Object, Object> temp = new HashMap<>();
        temp.put("src",upload);
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("data",temp);
        return map;
    }

    /**
     * 更新用户
     *
     * @param user
     * @return com.xxx.pcam.base.ResultInfo
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(User user) {
        if (upload != "") {
            user.setImg(upload);
        }
        adminUserService.updateUser(user);
        return success("数据更新成功！");
    }

    @PostMapping("count")
    @ResponseBody
    public User count(CountQuery countQuery) {
        // 调用Service层的count方法
        return  adminUserService.count(countQuery);
    }

}
