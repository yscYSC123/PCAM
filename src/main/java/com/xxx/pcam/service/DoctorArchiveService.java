package com.xxx.pcam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pcam.base.BaseService;
import com.xxx.pcam.dao.DoctorBookingMapper;
import com.xxx.pcam.query.BookingQuery;
import com.xxx.pcam.vo.Booking;
import com.xxx.pcam.vo.Consultation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class DoctorArchiveService extends BaseService<Consultation,Integer> {
    @Resource
    private DoctorBookingMapper doctorBookingMapper;

    /**
     * 多条件分页查询营销机会 （返回的数据格式必须满足LayUi中数据表格要求的格式）
     *
     * @param bookingQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> queryByParams(BookingQuery bookingQuery) {

        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(bookingQuery.getPage(), bookingQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Consultation> pageInfo = new PageInfo<>(doctorBookingMapper.selectByParams(bookingQuery));

        // 设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        // 设置分页好的列表
        map.put("data",pageInfo.getList());

        return map;
    }
}
