package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.query.ArticleQuery;
import com.xxx.pcam.service.ArticleService;
import com.xxx.pcam.utils.LoginUserUtil;
import com.xxx.pcam.vo.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("article")
public class ArticleController extends BaseController {

    @Resource
    private ArticleService articleService;

    /**
     * 进入用户列表页面
     */
    @RequestMapping("index")
    public String index(){
        return "admin/article/article";
    }

    /**
     * 分页多条件查询
     * @param articleQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(ArticleQuery articleQuery){
        return articleService.queryByParams(articleQuery);
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
            Article article = articleService.selectByPrimaryKey(id);
            // 将数据设置到请求域中
            request.setAttribute("article",article);
        }

        return "admin/article/add_update";
    }

    /**
     * 添加
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo add(Article article) {
        // 判断Id是否为空
        articleService.add(article);
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
        articleService.deleteBatch(ids);
        return success("数据删除成功！");
    }

    /**
     * 更新公告
     *
     * @param article
     * @return com.xxx.pcam.base.ResultInfo
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Article article) {
        // 调用Service层的添加方法
        articleService.update(article);
        return success("数据更新成功！");
    }
}
