package com.xxx.pcam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pcam.base.BaseService;
import com.xxx.pcam.dao.AnnouncementMapper;
import com.xxx.pcam.query.AnnouncementQuery;
import com.xxx.pcam.utils.AssertUtil;
import com.xxx.pcam.vo.Announcement;
import com.xxx.pcam.vo.Client;
import com.xxx.pcam.vo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AnnouncementService extends BaseService<Announcement,Integer> {

    @Resource
    private AnnouncementMapper announcementMapper;

    /**
     * 多条件分页查询 （返回的数据格式必须满足LayUi中数据表格要求的格式）
     *
     * @param announcementQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> queryByParams(AnnouncementQuery announcementQuery) {

        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(announcementQuery.getPage(), announcementQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Announcement> pageInfo = new PageInfo<>(announcementMapper.selectByParams(announcementQuery));

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
     * @param announcement
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Announcement announcement) {
        // IsActive 1是有效 0是无效
        announcement.setIsActive(1);
        // createDate创建时间 默认是系统当前时间
        announcement.setCreateTime(new Date());
        // 3. 执行添加操作，判断受影响的行数
        AssertUtil.isTrue(announcementMapper.insertSelective(announcement) != 1, "添加失败！");
    }

    /**
     * 更新公告
     * @param announcement
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Announcement announcement) {
        /* 1. 参数校验  */
        //  营销机会ID  非空，数据库中对应的记录存在
        AssertUtil.isTrue(null == announcement.getId(), "待更新记录不存在！");
        // 通过主键查询对象
        Announcement temp = announcementMapper.selectByPrimaryKey(announcement.getId());
        // 判断数据库中对应的记录存在
        AssertUtil.isTrue(temp == null, "待更新记录不存在！");
        /* 3. 执行更新操作，判断受影响的行数 */
        AssertUtil.isTrue(announcementMapper.updateByPrimaryKeySelective(announcement) != 1, "更新失败！");
    }
}
