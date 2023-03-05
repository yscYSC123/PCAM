package com.xxx.pcam.dao;

import com.xxx.pcam.base.BaseMapper;
import com.xxx.pcam.base.BaseQuery;
import com.xxx.pcam.vo.Booking;

import java.util.List;

public interface ClientBookingMapper extends BaseMapper<Booking,Integer> {
    public List<Booking> selectMyCon(BaseQuery baseQuery);
}