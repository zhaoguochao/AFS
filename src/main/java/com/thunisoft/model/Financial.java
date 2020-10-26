package com.thunisoft.model;

import java.sql.Timestamp;

import lombok.Data;

/**
 * Financial
 *
 * @author zhaoguochao
 * @description 财务主表实体类
 * @date 2020/9/10 13:13
 */
@Data
public class Financial {

    /**
     * id
     */
    private String id;

    /**
     * xh
     */
    private String xh;

    /**
     * name
     */
    private String name;

    /**
     * phone
     */
    private String phone;

    /**
     * createtime
     */
    private Timestamp createtime;

    /**
     * address
     */
    private String address;

    /**
     * price
     */
    private String price;


}
