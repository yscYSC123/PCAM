package com.xxx.pcam.service;

import com.xxx.pcam.base.BaseService;
import com.xxx.pcam.dao.BookingMapper;
import com.xxx.pcam.utils.AssertUtil;
import com.xxx.pcam.vo.Booking;
import com.xxx.pcam.vo.Doctor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ClientArchiveService extends BaseService<Booking,Integer> {

    @Resource
    private BookingMapper bookingMapper;

    /**
     * 预约申请
     * @param booking
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void subAdd(Booking booking) {
        // createDate创建时间 默认是系统当前时间
        booking.setApplyTime(new Date());
        // 3. 执行添加操作，判断受影响的行数
        AssertUtil.isTrue(bookingMapper.insertSelective(booking) != 1, "添加营销机会失败！");

    }
}
