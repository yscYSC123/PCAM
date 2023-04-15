package com.xxx.pcam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pcam.base.BaseService;
import com.xxx.pcam.dao.PgtestMapper;
import com.xxx.pcam.query.PgtestQuery;
import com.xxx.pcam.utils.AssertUtil;
import com.xxx.pcam.vo.Pgtest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class PgtestService extends BaseService<Pgtest,Integer> {

    @Resource
    private PgtestMapper pgtestMapper;

    /**
     * 多条件分页查询 （返回的数据格式必须满足LayUi中数据表格要求的格式）
     *
     * @param pgtestQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> queryByParams(PgtestQuery pgtestQuery) {

        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(pgtestQuery.getPage(), pgtestQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Pgtest> pageInfo = new PageInfo<>(pgtestMapper.selectByParams(pgtestQuery));

        // 设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        // 设置分页好的列表
        map.put("data",pageInfo.getList());

        return map;
    }
}
