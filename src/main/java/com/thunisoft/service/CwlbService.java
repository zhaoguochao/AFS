package com.thunisoft.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.thunisoft.mapper.CwlbMapper;
import com.thunisoft.model.Financial;

/**
 * CwlbService
 *
 * @author zhaoguochao
 * @description CwlbService
 * @date 2020/9/10 13:17
 */
@Service
public class CwlbService {

    /**
     * cwlbMapper
     */
    @Resource
    private CwlbMapper cwlbMapper;

    /**
     * 获取财务列表
     *
     * @return List<Financial>
     */
    public List<Financial> getCwlbTableData() {

        return cwlbMapper.getCwlbTableData();
    }

    /**
     * 保存数据
     * @param financial financial
     */
    public void saveCwData(Financial financial) {

        cwlbMapper.saveCwData(financial);
    }
}
