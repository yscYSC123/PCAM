package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.query.BookingQuery;
import com.xxx.pcam.service.DoctorArchiveService;
import com.xxx.pcam.utils.LoginUserUtil;
import com.xxx.pcam.vo.Consultation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("doctorArchive")
public class DoctorArchiveController extends BaseController {

    @Resource
    private DoctorArchiveService doctorArchiveService;

    /**
     * 进入咨询申请页面
     */
    @RequestMapping("conAdd")
    public String conAdd(){
        return "doctor/consultation/conAdd";
    }

    /**
     * 进入咨询中页面
     */
    @RequestMapping("conMy")
    public String conMy(){
        return "doctor/consultation/conMy";
    }

    /**
     * 进入咨询记录页面
     */
    @RequestMapping("record")
    public String record(){
        return "doctor/consultation/record";
    }

    /**
     * 展示来访者列表
     * @param bookingQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(BookingQuery bookingQuery, HttpServletRequest request){
        Integer doctor = LoginUserUtil.releaseUserIdFromCookie(request);
        bookingQuery.setDoctorId(doctor);
        return doctorArchiveService.queryByParams(bookingQuery);
    }

    /**
     * 展示咨询中列表
     * @param bookingQuery
     * @return
     */
    @RequestMapping("list1")
    @ResponseBody
    public Map<String, Object> selectByParams1(BookingQuery bookingQuery,HttpServletRequest request){
        Integer doctor = LoginUserUtil.releaseUserIdFromCookie(request);
        bookingQuery.setDoctorId(doctor);
        return doctorArchiveService.queryByParams1(bookingQuery);
    }

    /**
     * 展示咨询中列表
     * @param bookingQuery
     * @return
     */
    @RequestMapping("list2")
    @ResponseBody
    public Map<String, Object> selectByParams2(BookingQuery bookingQuery,HttpServletRequest request){
        Integer doctor = LoginUserUtil.releaseUserIdFromCookie(request);
        bookingQuery.setDoctorId(doctor);
        return doctorArchiveService.queryByParams2(bookingQuery);
    }

    /**
     * 进入同意页面
     */
    @RequestMapping("toAgree")
    public String toBooking(){
        return "doctor/consultation/toAgree";
    }

    /**
     * 预约
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo conAdd(Consultation consultation) {
        consultation.setStatus(2);
        doctorArchiveService.conAdd(consultation);
        return success("数据添加成功！");
    }

    /**
     * 拒绝预约
     *
     * @param consultation
     * @return com.xxx.pcam.base.ResultInfo
     */
    @PostMapping("refuse")
    @ResponseBody
    public ResultInfo refuse(Consultation consultation) {
        // 调用Service层的添加方法
        doctorArchiveService.refuse(consultation);
        return success("数据更新成功！");
    }

    /**
     * 更新
     * @param consultation
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Consultation consultation) {
        // 调用Service层的添加方法
        doctorArchiveService.update(consultation);
        return success("数据更新成功！");
    }

}
