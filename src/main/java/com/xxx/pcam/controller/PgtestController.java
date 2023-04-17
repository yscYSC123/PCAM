package com.xxx.pcam.controller;

import com.xxx.pcam.base.BaseController;
import com.xxx.pcam.base.ResultInfo;
import com.xxx.pcam.query.PgtestQuery;
import com.xxx.pcam.service.PgtestService;
import com.xxx.pcam.utils.LoginUserUtil;
import com.xxx.pcam.vo.Pgtest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 心理测试控制器
 */
@Controller
@RequestMapping("pgtest")
public class PgtestController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PgtestService pgtestService;

    @RequestMapping("pgtestView")
    public String pgtestView() {
        return "admin/topic/pgtest";
    }

    @RequestMapping("pgtestUserView")
    public String pgtestUserView() {
        return "client/topic/pgtest";
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
     * 分页多条件查询
     * @param pgtestQuery
     * @return
     */
    @RequestMapping("list1")
    @ResponseBody
    public Map<String, Object> selectByParams1(PgtestQuery pgtestQuery,HttpServletRequest request){
        String userName = LoginUserUtil.releaseUserNameFromCookie(request);
        pgtestQuery.setUserName(userName);
        return pgtestService.queryByParams1(pgtestQuery);
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

    @ResponseBody
    @RequestMapping("add")
    public Map<String, Object> addPgTest(@RequestParam("count") Integer count, HttpServletRequest request) {
        Map<String, Object> mapData = new HashMap<>();
        Pgtest pgTest = new Pgtest();
        if (count >= 90) {
            pgTest.setResult("典型的外向性格");
            pgTest.setPgtestTime(new Date());
            //从session中获取
            pgTest.setUserOp(LoginUserUtil.releaseUserNameFromCookie(request));
            pgTest.setScore(count);
            logger.info("------------------------------------");
            //插入数据库
            boolean insert = pgtestService.insert(pgTest);
            mapData.put("status", insert);
            return mapData;
        } else if (count > 70 && count < 90) {
            pgTest.setResult("稍外向性格");
            pgTest.setPgtestTime(new Date());
            //从session中获取
            pgTest.setUserOp(LoginUserUtil.releaseUserNameFromCookie(request));
            pgTest.setScore(count);
            logger.info("==========================================");
            //插入数据库
            boolean insert = pgtestService.insert(pgTest);
            mapData.put("status", insert);
            return mapData;
        } else if (count > 50 && count < 70) {
            pgTest.setResult("外内混合型性格");
            pgTest.setPgtestTime(new Date());
            //从session中获取
            pgTest.setUserOp(LoginUserUtil.releaseUserNameFromCookie(request));
            pgTest.setScore(count);
            logger.info("=================");
            //插入数据库
            boolean insert = pgtestService.insert(pgTest);
            mapData.put("status", insert);
            return mapData;
        } else if (count > 30 && count < 50) {
            pgTest.setResult("典型的内向性格");
            pgTest.setPgtestTime(new Date());
            //从session中获取
            pgTest.setUserOp(LoginUserUtil.releaseUserNameFromCookie(request));
            pgTest.setScore(count);
            logger.info("+++++++++++++++++++++");
            //插入数据库
            boolean insert = pgtestService.insert(pgTest);
            mapData.put("status", insert);
            return mapData;
        }else if (count >= 10 && count < 30) {
            pgTest.setResult("极端的内向性格");
            pgTest.setPgtestTime(new Date());
            //从session中获取
            pgTest.setUserOp(LoginUserUtil.releaseUserNameFromCookie(request));
            pgTest.setScore(count);
            logger.info("+++++++++++++++++++++");
            //插入数据库
            boolean insert = pgtestService.insert(pgTest);
            mapData.put("status", insert);
            return mapData;
        }
        mapData.put("status", false);
        return mapData;
    }

}
