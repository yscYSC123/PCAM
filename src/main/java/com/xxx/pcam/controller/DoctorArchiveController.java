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
     * 进入同意页面
     */
    @RequestMapping("toAgree")
    public String toBooking(){
        return "doctor/consultation/toAgree";
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

}
