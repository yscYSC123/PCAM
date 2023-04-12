package com.xxx.pcam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pcam.base.BaseService;
import com.xxx.pcam.dao.ArticleMapper;
import com.xxx.pcam.query.ArticleQuery;
import com.xxx.pcam.utils.AssertUtil;
import com.xxx.pcam.vo.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.*;

@Service
public class ArticleService extends BaseService<Article,Integer> {

    @Resource
    private ArticleMapper articleMapper;

    /**
     * 多条件分页查询 （返回的数据格式必须满足LayUi中数据表格要求的格式）
     *
     * @param articleQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> queryByParams(ArticleQuery articleQuery) {

        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(articleQuery.getPage(), articleQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Article> pageInfo = new PageInfo<>(articleMapper.selectByParams(articleQuery));

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
     * @param article
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Article article) {
        // createDate创建时间 默认是系统当前时间
        article.setCreateTime(new Date());
        article.setStatus(1);
        // 3. 执行添加操作，判断受影响的行数
        AssertUtil.isTrue(articleMapper.insertSelective(article) != 1, "添加失败！");
    }

    /**
     * 更新公告
     * @param article
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Article article) {
        /* 1. 参数校验  */
        //  营销机会ID  非空，数据库中对应的记录存在
        AssertUtil.isTrue(null == article.getId(), "待更新记录不存在！");
        // 通过主键查询对象
        Article temp = articleMapper.selectByPrimaryKey(article.getId());
        // 判断数据库中对应的记录存在
        AssertUtil.isTrue(temp == null, "待更新记录不存在！");
        /* 3. 执行更新操作，判断受影响的行数 */
        AssertUtil.isTrue(articleMapper.updateByPrimaryKeySelective(article) != 1, "更新失败！");
    }

}
