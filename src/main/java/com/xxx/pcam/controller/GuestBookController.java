package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.BaseQuery;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.query.GuestBookQuery;
import com.xxx.pcam.service.GuestBookService;
import com.xxx.pcam.utils.LoginUserUtil;
import com.xxx.pcam.vo.Guestbook;
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
@RequestMapping("guestbook")
public class GuestBookController extends BaseController {

    @Resource
    private GuestBookService guestBookService;

    /**
     * 进入用户列表页面
     */
    @RequestMapping("index")
    public String index(){
        return "admin/guestbook/guestbook";
    }

    /**
     * 进入用户列表页面
     */
    @RequestMapping("guestbook")
    public String guestbook(){
        return "doctor/guestbook";
    }

    /**
     * 分页多条件查询
     * @param guestBookQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(GuestBookQuery guestBookQuery){
        return guestBookService.queryByParams(guestBookQuery);
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
            Guestbook guestbook = guestBookService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("guestbook",guestbook);
        }

        return "admin/guestbook/add_update";
    }

    /**
     * 添加
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo add(Guestbook guestbook, HttpServletRequest request) {
        String creater = LoginUserUtil.releaseUserNameFromCookie(request);
        guestbook.setCreater(creater);
        // 判断Id是否为空
        guestBookService.add(guestbook);
        return success("数据添加成功！");
    }

    /**
     * 删除留言
     *
     * @param ids
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteClient(Integer[] ids) {
        // 调用Service层的删除方法
        guestBookService.deleteBatch(ids);
        return success("数据删除成功！");
    }

    /**
     * 更新公告
     *
     * @param guestbook
     * @return com.xxx.pcam.base.ResultInfo
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Guestbook guestbook) {
        // 调用Service层的添加方法
        guestBookService.update(guestbook);
        return success("数据更新成功！");
    }

    /**
     * 主页留言显示
     * @return
     * @throws SQLException
     */
    @PostMapping("show")
    @ResponseBody
    public List<String> show() throws SQLException {
        // 调用Service层的添加方法
        return guestBookService.show();
    }
}
