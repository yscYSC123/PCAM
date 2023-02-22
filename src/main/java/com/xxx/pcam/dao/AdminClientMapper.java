package com.xxx.pcam.dao;

import com.xxx.pcam.base.BaseMapper;
import com.xxx.pcam.vo.Client;
import com.xxx.pcam.vo.User;

public interface AdminClientMapper extends BaseMapper<Client,Integer> {
    //通过用户名查询用户记录，返回用户对象
    public User queryUserByName(String userName);
}