package com.xxx.pcam.dao;

import com.xxx.pcam.base.BaseMapper;
import com.xxx.pcam.vo.Doctor;
import com.xxx.pcam.vo.User;

public interface AdminDoctorMapper extends BaseMapper<Doctor,Integer> {
    //通过用户名查询用户记录，返回用户对象
    public User queryUserByName(String userName);

}