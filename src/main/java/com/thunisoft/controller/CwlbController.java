package com.thunisoft.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thunisoft.model.Financial;
import com.thunisoft.model.ResponseData;
import com.thunisoft.service.CwlbService;

import lombok.extern.slf4j.Slf4j;

/**
 * CwlbController
 *
 * @author zhaoguochao
 * @description 财务列表
 * @date 2020/9/10 11:26
 */
@RequestMapping("/cwlb")
@Controller
@Slf4j
public class CwlbController {
    /**
     * cwlbService
     */
    @Resource
    private CwlbService cwlbService;

    /**
     * 获取财务列表
     *
     * @return ResponseEntity
     */
    @PostMapping("/loadCwlbTable")
    public ResponseEntity getMessage() {
        List<Financial> finaData = cwlbService.getCwlbTableData();
        log.info("财务列表加载成功,列表数据为:{}", finaData);
        return ResponseEntity.ok().body(new ResponseData(true, "列表数据加载完成", finaData));
    }

    /**
     * 保存财务数据
     *
     * @return ResponseEntity
     */
    @PostMapping("/saveCwData")
    public ResponseEntity saveCwData(Financial financial) {
        try{
            cwlbService.saveCwData(financial);
            log.info("财务数据保存成功,保存的数据为:{}",financial);
        }catch (Exception e){
            log.error("保存财务数据异常,接收到的参数为:{}", financial, e);
            return ResponseEntity.ok().body(new ResponseData(false, "修改失败"));
        }
        return ResponseEntity.ok().body(new ResponseData(true, "修改成功"));
    }
}
