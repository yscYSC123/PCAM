package com.xxx.pcam.dao;

import com.xxx.pcam.base.BaseMapper;
import com.xxx.pcam.query.UserQuery;
import com.xxx.pcam.vo.Client;
import com.xxx.pcam.vo.User;

import java.util.List;

public interface AdminClientMapper extends BaseMapper<Client,Integer> {
    //通过用户名查询用户记录，返回用户对象
    public User queryUserByName(String userName);

    public List<Client> selectByParams1(UserQuery userQuery);

    public List<Client> selectByParams2(UserQuery userQuery);
}