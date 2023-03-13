package com.xxx.pcam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pcam.base.BaseService;
import com.xxx.pcam.dao.GuestbookMapper;
import com.xxx.pcam.query.GuestBookQuery;
import com.xxx.pcam.utils.AssertUtil;
import com.xxx.pcam.vo.Guestbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class GuestBookService extends BaseService<Guestbook,Integer> {

    @Resource
    private GuestbookMapper guestbookMapper;

    /**
     * 多条件分页查询 （返回的数据格式必须满足LayUi中数据表格要求的格式）
     *
     * @param guestBookQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> queryByParams(GuestBookQuery guestBookQuery) {

        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(guestBookQuery.getPage(), guestBookQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Guestbook> pageInfo = new PageInfo<>(guestbookMapper.selectByParams(guestBookQuery));

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
     * @param guestbook
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Guestbook guestbook) {
        // IsActive 1是有效 0是无效
        guestbook.setIsActive(1);
        // createDate创建时间 默认是系统当前时间
        guestbook.setCreateTime(new Date());
        // 3. 执行添加操作，判断受影响的行数
        AssertUtil.isTrue(guestbookMapper.insertSelective(guestbook) != 1, "添加失败！");
    }

    /**
     * 更新公告
     * @param guestbook
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Guestbook guestbook) {
        /* 1. 参数校验  */
        //  营销机会ID  非空，数据库中对应的记录存在
        AssertUtil.isTrue(null == guestbook.getId(), "待更新记录不存在！");
        // 通过主键查询对象
        Guestbook temp = guestbookMapper.selectByPrimaryKey(guestbook.getId());
        // 判断数据库中对应的记录存在
        AssertUtil.isTrue(temp == null, "待更新记录不存在！");
        /* 3. 执行更新操作，判断受影响的行数 */
        AssertUtil.isTrue(guestbookMapper.updateByPrimaryKeySelective(guestbook) != 1, "更新失败！");
    }
}
