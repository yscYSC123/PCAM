package com.xxx.pcam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pcam.base.BaseService;
import com.xxx.pcam.dao.ClientBookingMapper;
import com.xxx.pcam.query.BookingQuery;
import com.xxx.pcam.utils.AssertUtil;
import com.xxx.pcam.vo.Booking;
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
public class ClientArchiveService extends BaseService<Booking,Integer> {

    @Resource
    private ClientBookingMapper clientBookingMapper;

    /**
     * 多条件分页查询营销机会 （返回的数据格式必须满足LayUi中数据表格要求的格式）
     *
     * @param bookingQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> queryByParams(BookingQuery bookingQuery) {

        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(bookingQuery.getPage(), bookingQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Booking> pageInfo = new PageInfo<>(clientBookingMapper.selectByParams(bookingQuery));

        // 设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        // 设置分页好的列表
        map.put("data",pageInfo.getList());

        return map;
    }
    public Map<String, Object> queryMyCon(BookingQuery bookingQuery) {

        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(bookingQuery.getPage(), bookingQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Booking> pageInfo = new PageInfo<>(clientBookingMapper.selectMyCon(bookingQuery));

        // 设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        // 设置分页好的列表
        map.put("data",pageInfo.getList());

        return map;
    }

    /**
     * 预约申请
     * @param booking
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void subAdd(Booking booking) {
        // createDate创建时间 默认是系统当前时间
        booking.setApplyTime(new Date());
        // 3. 执行添加操作，判断受影响的行数
        AssertUtil.isTrue(clientBookingMapper.insertSelective(booking) != 1, "添加失败！");

    }

    /**
     * 取消
     * @param booking
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void cancel(Booking booking) {
        /* 2. 设置相关参数的默认值 */
        // updateDate更新时间  设置为系统当前时间
        booking.setStatus(0);
        /* 3. 执行更新操作，判断受影响的行数 */
        AssertUtil.isTrue(clientBookingMapper.updateByPrimaryKeySelective(booking) != 1, "取消失败！");

    }
}
