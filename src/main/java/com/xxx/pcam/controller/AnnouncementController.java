package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.query.AnnouncementQuery;
import com.xxx.pcam.service.AnnouncementService;
import com.xxx.pcam.utils.LoginUserUtil;
import com.xxx.pcam.vo.Announcement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("announcement")
public class AnnouncementController extends BaseController {

    @Resource
    private AnnouncementService announcementService;

    /**
     * 进入用户列表页面
     */
    @RequestMapping("index")
    public String index(){
        return "admin/announcement/announcement";
    }

    /**
     * 分页多条件查询
     * @param announcementQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(AnnouncementQuery announcementQuery){
        return announcementService.queryByParams(announcementQuery);
    }

    /**
     * 进入添加/修改页面
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toPage")
    public String toPage(Integer id, HttpServletRequest request) {
        // 判断Id是否为空
        if (id != null) {
            // 通过ID查询营销机会数据
            Announcement announcement = announcementService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("announcement",announcement);
        }

        return "admin/announcement/add_update";
    }

    /**
     * 添加
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo add(Announcement announcement, HttpServletRequest request) {
        Integer createrId = LoginUserUtil.releaseUserIdFromCookie(request);
        announcement.setCreaterId(createrId);
        // 判断Id是否为空
        announcementService.add(announcement);
        return success("数据添加成功！");
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteClient(Integer[] ids) {
        // 调用Service层的删除方法
        announcementService.deleteBatch(ids);
        return success("数据删除成功！");
    }

    /**
     * 更新公告
     *
     * @param announcement
     * @return com.xxx.pcam.base.ResultInfo
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Announcement announcement) {
        // 调用Service层的添加方法
        announcementService.update(announcement);
        return success("数据更新成功！");
    }

    /**
     * 主页公告显示
     * @return
     * @throws SQLException
     */
    @PostMapping("show")
    @ResponseBody
    public List<String> show() throws SQLException {
        // 调用Service层的添加方法
        return announcementService.show();
    }

}
