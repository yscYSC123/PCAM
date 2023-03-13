package com.xxx.pcam.service;

import com.xxx.pcam.base.BaseService;
import com.xxx.pcam.dao.AdminUserMapper;
import com.xxx.pcam.model.UserModel;
import com.xxx.pcam.query.CountQuery;
import com.xxx.pcam.utils.AssertUtil;
import com.xxx.pcam.utils.PhoneUtil;
import com.xxx.pcam.utils.UserIDBase64;
import com.xxx.pcam.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class AdminUserService extends BaseService<User,Integer> {

    @Resource
    private AdminUserMapper adminUserMapper;

    /**
     * 用户登录
     */
    public UserModel userLogin(String userName,String userPwd){
        checkLoginParam(userName,userPwd);
        User user = adminUserMapper.queryUserByName(userName);
        AssertUtil.isTrue(user == null,"该用户不存在！");

        //判断密码是否正确
        checkUserPwd(userPwd,user.getPwd());
        
        //返回改造对象
        return buildUserInfo(user);
    }

    /**
     * 用户注册
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void register(User user) {
        System.out.println(user);
        /* 1. 校验参数 */
        checkUserParams(user.getUserName(), user.getName(), user.getPhone(),user.getId());

        user.setLevel(2);
        // IsActive 1是有效 0是无效
        user.setIsActive(1);
        // createDate创建时间 默认是系统当前时间
        user.setCreateDate(new Date());
        // updateDate 默认是系统当前时间
        user.setUpdateDate(new Date());
        // 3. 执行添加操作，判断受影响的行数
        AssertUtil.isTrue(adminUserMapper.insertSelective(user) != 1, "用户注册失败！");
    }

    /**
     * 修改密码
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @param repeatPwd
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePwd(Integer userId,String oldPwd,String newPwd,String repeatPwd){
        User user = (User) adminUserMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(null == user,"待更新记录不存在！");

        //参数校验
        checkPwdParam(user,oldPwd,newPwd,repeatPwd);

        //设置用户的新密码
        user.setPwd(newPwd);

        //执行更新，判断受影响的行数
        AssertUtil.isTrue(adminUserMapper.updateByPrimaryKeySelective(user) < 1,"修改密码失败！");
    }

    private void checkPwdParam(User user, String oldPwd, String newPwd, String repeatPwd) {
        //  判断原始密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd), "原始密码不能为空！");
        // 判断原始密码是否正确（查询的用户对象中的用户密码是否原始密码一致）
        AssertUtil.isTrue(!user.getPwd().equals(oldPwd), "原始密码不正确！");

        // 判断新密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(newPwd), "新密码不能为空！");
        // 判断新密码是否与原始密码一致 （不允许新密码与原始密码）
        AssertUtil.isTrue(oldPwd.equals(newPwd),"新密码不能与原始密码相同！");

        // 判断确认密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd),"确认密码不能为空！");
        // 判断确认密码是否与新密码一致
        AssertUtil.isTrue(!newPwd.equals(repeatPwd), "确认密码与新密码不一致！");
    }

    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setName(user.getName());
        userModel.setLevel(user.getLevel());

        return userModel;
    }

    /**
     * 校验密码
     * @param userPwd
     * @param pwd
     */
    private void checkUserPwd(String userPwd,String pwd) {
        // 判断密码是否相等
        AssertUtil.isTrue(!userPwd.equals(pwd), "用户密码不正确！");
    }

    /**
     * 参数判断是否为空
     * @param userName
     * @param userPwd
     */
    private void checkLoginParam(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户姓名不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空！");
    }

    private void checkUserParams(String userName, String email, String phone,Integer userId) {
        // 判断用户名是否为空
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空！");
        // 判断用户名的唯一性
        // 通过用户名查询用户对象
        User temp = adminUserMapper.queryUserByName(userName);
        // 如果用户对象为空，则表示用户名可用；如果用户对象不为空，则表示用户名不可用
        // 如果是添加操作，数据库中无数据，只要通过名称查到数据，则表示用户名被占用
        // 如果是修改操作，数据库中有对应的记录，通过用户名查到数据，可能是当前记录本身，也可能是别的记录
        // 如果用户名存在，且与当前修改记录不是同一个，则表示其他记录占用了该用户名，不可用
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(userId)), "用户名已存在，请重新输入！");

        // 手机号 非空
        AssertUtil.isTrue(StringUtils.isBlank(phone), "用户手机号不能为空！");

        // 手机号 格式判断
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "手机号格式不正确！");
    }

    /**
     * 更新用户
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        /* 1. 参数校验  */
        //  营销机会ID  非空，数据库中对应的记录存在
        AssertUtil.isTrue(null == user.getId(), "待更新记录不存在！");
        // 通过主键查询对象
        User temp = adminUserMapper.selectByPrimaryKey(user.getId());
        // 判断数据库中对应的记录存在
        AssertUtil.isTrue(temp == null, "待更新记录不存在！");
        // 参数校验
        checkUserParams(user.getUserName(), user.getName(), user.getPhone(),user.getId());

        /* 2. 设置相关参数的默认值 */
        // updateDate更新时间  设置为系统当前时间
        user.setUpdateDate(new Date());
        /* 3. 执行更新操作，判断受影响的行数 */
        AssertUtil.isTrue(adminUserMapper.updateByPrimaryKeySelective(user) != 1, "更新用户失败！");
    }

    public User count(CountQuery countQuery) {
        return adminUserMapper.count(countQuery);
    }
}
