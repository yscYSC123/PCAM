package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.query.PgtestQuery;
import com.xxx.pcam.service.PgtestService;
import com.xxx.pcam.vo.Pgtest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 心理测试控制器
 */
@Controller
@RequestMapping("pgtest")
public class PgtestController extends BaseController {

    @Resource
    private PgtestService pgtestService;

    @RequestMapping("pgtestView")
    public String topicView() {
        return "admin/topic/pgtest";
    }

    /**
     * 分页多条件查询
     * @param pgtestQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(PgtestQuery pgtestQuery){
        return pgtestService.queryByParams(pgtestQuery);
    }

    /**
     * 删除
     *
     * @param ids
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteClient(Integer[] ids) {
        // 调用Service层的删除方法
        pgtestService.deleteBatch(ids);
        return success("数据删除成功！");
    }
}
