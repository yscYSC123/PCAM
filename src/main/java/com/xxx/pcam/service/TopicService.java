package com.xxx.pcam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pcam.base.BaseService;
import com.xxx.pcam.dao.TopicMapper;
import com.xxx.pcam.query.TopicQuery;
import com.xxx.pcam.utils.AssertUtil;
import com.xxx.pcam.vo.Topic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class TopicService extends BaseService<Topic,Integer> {

    @Resource
    private TopicMapper topicMapper;

    /**
     * 多条件分页查询 （返回的数据格式必须满足LayUi中数据表格要求的格式）
     *
     * @param topicQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> queryByParams(TopicQuery topicQuery) {

        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(topicQuery.getPage(), topicQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Topic> pageInfo = new PageInfo<>(topicMapper.selectByParams(topicQuery));

        // 设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        // 设置分页好的列表
        map.put("data",pageInfo.getList());

        return map;
    }

    /**
     * 添加
     * @param topic
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Topic topic) {
        topic.setTopicTime(new Date());
        AssertUtil.isTrue(topicMapper.insertSelective(topic) != 1, "添加失败！");
    }

    /**
     * 修改
     * @param topic
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Topic topic) {
        /* 1. 参数校验  */
        //  营销机会ID  非空，数据库中对应的记录存在
        AssertUtil.isTrue(null == topic.getId(), "待更新记录不存在！");
        // 通过主键查询对象
        Topic temp = topicMapper.selectByPrimaryKey(topic.getId());
        // 判断数据库中对应的记录存在
        AssertUtil.isTrue(temp == null, "待更新记录不存在！");
        /* 3. 执行更新操作，判断受影响的行数 */
        AssertUtil.isTrue(topicMapper.updateByPrimaryKeySelective(topic) != 1, "更新失败！");
    }

}
