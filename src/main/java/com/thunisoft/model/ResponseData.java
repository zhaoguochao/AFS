package com.thunisoft.model;

import lombok.Data;

/**
 * ResultData
 *
 * @author zhaoguochao
 * @description 返回值实体类
 * @date 2020/9/9 17:41
 */
@Data
public class ResponseData<T> {
    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 状态码
     */
    private String code;

    /**
     * 返回内容
     */
    private T resultData;

    /**
     * 不带参构造函数
     * @author Liu Xi
     * @date 2019年9月12日 下午5:04:15
     *
     */
    public ResponseData() {
    }

    /**
     * 构造函数 - 成功 - 不带返回内容
     * @author Liu Xi
     * @date 2019年9月12日 下午5:04:40
     *
     * @param success 是否成功
     */
    public ResponseData(boolean success) {
        this.success = success;
    }

    /**
     * 构造函数 - 成功 - 带返回内容
     * @author Liu Xi
     * @date 2019年9月12日 下午5:04:40
     *
     * @param success 是否成功
     * @param resultData 返回内容
     */
    public ResponseData(boolean success, T resultData) {
        this.success = success;
        this.resultData = resultData;
    }

    /**
     * 构造函数 - 失败 - 不带返回内容
     * @author Liu Xi
     * @date 2019年9月12日 下午5:04:40
     *
     * @param success 是否成功
     * @param msg 返回信息
     */
    public ResponseData(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    /**
     * 构造函数 - 失败 - 带返回内容
     * @author Liu Xi
     * @date 2019年9月12日 下午5:04:40
     *
     * @param success 是否成功
     * @param msg 返回信息
     * @param resultData 返回内容
     */
    public ResponseData(boolean success, String msg, T resultData) {
        this.success = success;
        this.msg = msg;
        this.resultData = resultData;
    }

    /**
     *
     * 构造函数- 代码值-信息
     * @author mingdf
     * @date 2019年9月17日 下午5:17:25
     *
     * @param code 代码值
     * @param msg 返回信息
     */
    public ResponseData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     *
     * 构造函数  - 代码值-带返回内容
     * @author mingdf
     * @date 2019年9月17日 下午5:17:25
     *
     * @param code 代码值
     * @param msg 返回信息
     * @param resultData 返回内容
     */
    public ResponseData(String code, String msg, T resultData) {
        this.code = code;
        this.msg = msg;
        this.resultData = resultData;
    }

    /**
     * 构造函数 - 带返回内容
     *
     * @author zhaoguochao
     * @date 2020年4月14日 下午4:38:38
     *
     * @param success 是否成功
     * @param code 代码值
     * @param msg 返回信息
     * @param resultData 返回内容s
     */
    public ResponseData(boolean success, String code, String msg, T resultData) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.resultData = resultData;
    }
}
