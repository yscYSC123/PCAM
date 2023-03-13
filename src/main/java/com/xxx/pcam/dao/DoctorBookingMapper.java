package com.xxx.pcam.dao;

import com.xxx.pcam.base.BaseMapper;
import com.xxx.pcam.base.BaseQuery;
import com.xxx.pcam.vo.Consultation;

import java.util.List;

public interface DoctorBookingMapper extends BaseMapper<Consultation,Integer> {
    public List<Consultation> selectByParams1(BaseQuery baseQuery);
    public List<Consultation> selectByParams2(BaseQuery baseQuery);
}