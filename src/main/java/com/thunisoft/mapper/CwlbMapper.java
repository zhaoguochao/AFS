package com.thunisoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.thunisoft.model.Financial;

/**
 * CwlbMapper
 *
 * @author zhaoguochao
 * @description CwlbMapper
 * @date 2020/9/10 13:20
 */
@Mapper
public interface CwlbMapper {

    /**
     * 获取财务列表
     *
     * @return List<Financial>
     */
    List<Financial> getCwlbTableData();

    /**
     * 保存数据
     *
     * @param financial financial
     */
    void saveCwData(Financial financial);

}
