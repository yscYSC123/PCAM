package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.service.ArticleService;
import com.xxx.pcam.vo.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("userArticle")
public class UserArticleController extends BaseController {

    @Resource
    private ArticleService articleService;

    @RequestMapping("index")
    public String index() {
        return "doctor/article";
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

        return "doctor/look";
    }

}
