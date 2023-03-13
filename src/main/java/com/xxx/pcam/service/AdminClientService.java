package com.xxx.pcam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pcam.base.BaseService;
import com.xxx.pcam.dao.AdminClientMapper;
import com.xxx.pcam.query.UserQuery;
import com.xxx.pcam.utils.AssertUtil;
import com.xxx.pcam.utils.PhoneUtil;
import com.xxx.pcam.vo.Client;
import com.xxx.pcam.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminClientService extends BaseService<Client,Integer> {

    @Resource
    private AdminClientMapper adminClientMapper;

    /**
     * 多条件分页查询 （返回的数据格式必须满足LayUi中数据表格要求的格式）
     *
     * @param userQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> queryClientByParams(UserQuery userQuery) {

        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(userQuery.getPage(), userQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Client> pageInfo = new PageInfo<>(adminClientMapper.selectByParams(userQuery));

        // 设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        // 设置分页好的列表
        map.put("data",pageInfo.getList());

        return map;
    }

    /**
     * 添加用户
     * @param client
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addClient(Client client) {
        /* 1. 校验参数 */
        checkClientParams(client.getUserName(), client.getName(), client.getPhone(),client.getId());

        // IsActive 1是有效 0是无效
        client.setIsActive(1);
        // createDate创建时间 默认是系统当前时间
        client.setCreateDate(new Date());
        // updateDate 默认是系统当前时间
        client.setUpdateDate(new Date());
        // 3. 执行添加操作，判断受影响的行数
        AssertUtil.isTrue(adminClientMapper.insertSelective(client) != 1, "添加失败！");

    }

    /**
     *  参数校验
     *      userName用户名称    非空
     *      name用户姓名           非空
     *      phone联系号码       非空，手机号码格式正确
     *
     * @param userName
     * @param name
     * @param phone
     * @return void
     */
    private void checkClientParams(String userName, String name, String phone,Integer userId) {
        // userName客户名称    判断是否用户存在
        User client = adminClientMapper.queryUserByName(userName);
        // userName客户名称    非空
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名称不能为空！");
        //判断是否重复
        AssertUtil.isTrue(null != client && !(client.getId().equals(userId)), "用户名已存在，请重新输入！");
        // name联系人           非空
        AssertUtil.isTrue(StringUtils.isBlank(name),"真实姓名不能为空！");
        // Phone联系号码       非空
        AssertUtil.isTrue(StringUtils.isBlank(phone),"联系号码不能为空！");
        // Phone联系号码       非空，手机号码格式正确
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"联系号码格式不正确！");
    }

    /**
     * 更新用户
     * @param client
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateClient(Client client) {
        /* 1. 参数校验  */
        //  营销机会ID  非空，数据库中对应的记录存在
        AssertUtil.isTrue(null == client.getId(), "待更新记录不存在！");
        // 通过主键查询对象
        User temp = adminClientMapper.selectByPrimaryKey(client.getId());
        // 判断数据库中对应的记录存在
        AssertUtil.isTrue(temp == null, "待更新记录不存在！");
        // 参数校验
        checkClientParams(client.getUserName(), client.getName(), client.getPhone(),client.getId());

        /* 2. 设置相关参数的默认值 */
        // updateDate更新时间  设置为系统当前时间
        client.setUpdateDate(new Date());
        /* 3. 执行更新操作，判断受影响的行数 */
        AssertUtil.isTrue(adminClientMapper.updateByPrimaryKeySelective(client) != 1, "更新失败！");

    }
}
