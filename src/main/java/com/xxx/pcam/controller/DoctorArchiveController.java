package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.query.BookingQuery;
import com.xxx.pcam.service.DoctorArchiveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
     * 展示可预约的咨询师列表
     * @param bookingQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(BookingQuery bookingQuery){
        return doctorArchiveService.queryByParams(bookingQuery);
    }

}
