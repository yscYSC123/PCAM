package com.xxx.pcam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pcam.base.BaseService;
import com.xxx.pcam.dao.MessageMapper;
import com.xxx.pcam.query.MessageQuery;
import com.xxx.pcam.utils.AssertUtil;
import com.xxx.pcam.vo.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageService extends BaseService<Message,Integer> {

    @Resource
    private MessageMapper messageMapper;

    /**
     * 多条件分页查询 （返回的数据格式必须满足LayUi中数据表格要求的格式）
     *
     * @param messageQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> queryByParams(MessageQuery messageQuery) {

        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(messageQuery.getPage(), messageQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Message> pageInfo = new PageInfo<>(messageMapper.selectByParams(messageQuery));

        // 设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        // 设置分页好的列表
        map.put("data",pageInfo.getList());

        return map;
    }

    /**
     * 标记
     * @param message
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void mark(Message message) {
        /* 1. 参数校验  */
        //  营销机会ID  非空，数据库中对应的记录存在
        AssertUtil.isTrue(null == message.getId(), "待更新记录不存在！");
        // 通过主键查询对象
        Message temp = messageMapper.selectByPrimaryKey(message.getId());
        // 判断数据库中对应的记录存在
        AssertUtil.isTrue(temp == null, "待更新记录不存在！");
        /* 3. 执行更新操作，判断受影响的行数 */
        AssertUtil.isTrue(messageMapper.updateByPrimaryKeySelective(message) != 1, "更新失败！");
    }

    /**
     * 添加
     * @param message
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Message message) {
        // IsActive 1是有效 0是无效
        message.setIsActive(1);
        // createDate创建时间 默认是系统当前时间
        message.setSendTime(new Date());
        // 3. 执行添加操作，判断受影响的行数
        AssertUtil.isTrue(messageMapper.insertSelective(message) != 1, "添加失败！");
    }
}
