package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.query.UserQuery;
import com.xxx.pcam.service.AdminDoctorService;
import com.xxx.pcam.vo.Doctor;
import com.xxx.pcam.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("doctor")
public class AdminDoctorController extends BaseController {
    @Resource
    private AdminDoctorService adminDoctorService;

    static String upload = "";

    /**
     * 进入用户列表页面
     */
    @RequestMapping("index")
    public String index(){
        return "doctor/doctor";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(UserQuery userQuery){
        return adminDoctorService.queryDoctorByParams(userQuery);
    }

    /**
     * 添加
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addDoctor(Doctor doctor) {
        doctor.setImg(upload);
        adminDoctorService.addDoctor(doctor);
        return success("数据添加成功！");
    }

    /**
     * 进入添加/修改用户数据页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toDoctorPage")
    public String toDoctorPage(Integer id, HttpServletRequest request) {
        // 判断Id是否为空
        if (id != null) {
            // 通过ID查询营销机会数据
            User doctor = adminDoctorService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("doctor",doctor);
        }

        return "doctor/add_update";
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return com.xxx.pcam.base.ResultInfo
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteDoctor(Integer[] ids) {
        // 调用Service层的删除方法
        adminDoctorService.deleteBatch(ids);
        return success("营销机会数据删除成功！");
    }

    /**
     * 更新用户
     *
     * @param doctor
     * @return com.xxx.pcam.base.ResultInfo
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateDoctor(Doctor doctor) {
        doctor.setImg(upload);
        adminDoctorService.updateDoctor(doctor);
        return success("数据更新成功！");
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
            User user = adminDoctorService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("user",user);
        }
        return "doctor/setting";
    }

}
