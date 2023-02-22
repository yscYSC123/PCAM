package com.xxx.pcam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pcam.base.BaseService;
import com.xxx.pcam.dao.AdminDoctorMapper;
import com.xxx.pcam.query.UserQuery;
import com.xxx.pcam.utils.AssertUtil;
import com.xxx.pcam.utils.PhoneUtil;
import com.xxx.pcam.vo.Doctor;
import com.xxx.pcam.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminDoctorService extends BaseService<Doctor,Integer> {

    @Resource
    private AdminDoctorMapper adminDoctorMapper;

    /**
     * 多条件分页查询营销机会 （返回的数据格式必须满足LayUi中数据表格要求的格式）
     *
     * @param userQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> queryDoctorByParams(UserQuery userQuery) {

        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(userQuery.getPage(), userQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Doctor> pageInfo = new PageInfo<>(adminDoctorMapper.selectByParams(userQuery));

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
     * @param doctor
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addDoctor(Doctor doctor) {
        /* 1. 校验参数 */
        checkClientParams(doctor.getUserName(), doctor.getName(), doctor.getPhone(),doctor.getId());

        //默认咨询师
        doctor.setLevel(1);
        // IsActive 1是有效 0是无效
        doctor.setIsActive(1);
        // createDate创建时间 默认是系统当前时间
        doctor.setCreateDate(new Date());
        // updateDate 默认是系统当前时间
        doctor.setUpdateDate(new Date());
        // 3. 执行添加操作，判断受影响的行数
        AssertUtil.isTrue(adminDoctorMapper.insertSelective(doctor) != 1, "添加营销机会失败！");

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
        User doctor = adminDoctorMapper.queryUserByName(userName);
        // userName客户名称    非空
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名称不能为空！");
        //判断是否重复
        AssertUtil.isTrue(null != doctor && !(doctor.getId().equals(userId)), "用户名已存在，请重新输入！");
        // name联系人           非空
        AssertUtil.isTrue(StringUtils.isBlank(name),"真实姓名不能为空！");
        // Phone联系号码       非空
        AssertUtil.isTrue(StringUtils.isBlank(phone),"联系号码不能为空！");
        // Phone联系号码       非空，手机号码格式正确
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"联系号码格式不正确！");
    }

    /**
     * 更新用户
     * @param doctor
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDoctor(Doctor doctor) {
        /* 1. 参数校验  */
        //  营销机会ID  非空，数据库中对应的记录存在
        AssertUtil.isTrue(null == doctor.getId(), "待更新记录不存在！");
        // 通过主键查询对象
        User temp = adminDoctorMapper.selectByPrimaryKey(doctor.getId());
        // 判断数据库中对应的记录存在
        AssertUtil.isTrue(temp == null, "待更新记录不存在！");
        // 参数校验
        checkClientParams(doctor.getUserName(), doctor.getName(), doctor.getPhone(),doctor.getId());

        /* 2. 设置相关参数的默认值 */
        // updateDate更新时间  设置为系统当前时间
        doctor.setUpdateDate(new Date());
        /* 3. 执行更新操作，判断受影响的行数 */
        AssertUtil.isTrue(adminDoctorMapper.updateByPrimaryKeySelective(doctor) != 1, "更新营销机会失败！");
    }

    public static String upload(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 文件存放服务端的位置
                String rootPath = "E:\\images";
                File dir = new File(rootPath + File.separator);
                // 判断是否有此路径
                if (!dir.exists()) {
                    dir.mkdir();
                }
                // 通过时间戳命名文件
                long path = System.currentTimeMillis();
                // 写入文件的路径
                File serverFile = new File(dir.getAbsolutePath()+"/" + path + ".png");
                // 写文件到服务器
                file.transferTo(serverFile);
                return path + ".png";
            } catch (Exception e) {
                return "";
            }
        } else {
            return "";
        }
    }

}
