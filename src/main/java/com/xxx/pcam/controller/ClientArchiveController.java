package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.query.BookingQuery;
import com.xxx.pcam.query.UserQuery;
import com.xxx.pcam.service.AdminDoctorService;
import com.xxx.pcam.service.ClientArchiveService;
import com.xxx.pcam.utils.LoginUserUtil;
import com.xxx.pcam.vo.Booking;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("clientArchive")
public class ClientArchiveController extends BaseController {

    @Resource
    private AdminDoctorService adminDoctorService;
    @Resource
    private ClientArchiveService clientArchiveService;

    /**
     * 进入预约页面
     */
    @RequestMapping("subAdd")
    public String subAdd(){
        return "client/booking/subAdd";
    }

    /**
     * 进入我的预约页面
     */
    @RequestMapping("subMy")
    public String subMy(){
        return "client/booking/subMy";
    }

    /**
     * 展示可预约的咨询师列表
     * @param userQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(UserQuery userQuery){
        return adminDoctorService.queryDoctorByParams(userQuery);
    }

    /**
     * 进入预约申请
     */
    @RequestMapping("toBooking")
    public String toBooking(){
        return "client/booking/toBooking";
    }

    /**
     * 添加
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo subAdd(Booking booking,HttpServletRequest request) {
        Integer clientId = LoginUserUtil.releaseUserIdFromCookie(request);
        booking.setClientId(clientId);
        booking.setStatus(0);
        clientArchiveService.subAdd(booking);
        return success("数据添加成功！");
    }

    /**
     * 展示我的的预约列表
     * @param bookingQuery
     * @return
     */
    @RequestMapping("list1")
    @ResponseBody
    public Map<String, Object> selectByParams(BookingQuery bookingQuery){
        return clientArchiveService.queryByParams(bookingQuery);
    }
}
