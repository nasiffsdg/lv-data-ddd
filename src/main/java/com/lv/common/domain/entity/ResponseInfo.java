package com.lv.common.domain.entity;

import com.lv.common.constants.ResponseCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


/**
 * 统一的返回数据
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/14 1:04 AM
 */
@Data
public class ResponseInfo {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;

    private ResponseInfo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ResponseInfo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseInfo(Integer code) {
        this.code = code;
    }


    /**
     * 响应成功
     * @param data 数据
     * @return 返回 json
     */
    public static ResponseInfo success(Object data) {
        return new ResponseInfo(ResponseCode.SUCCESS, StringUtils.EMPTY, data);
    }
    public static ResponseInfo success() {
        return new ResponseInfo(ResponseCode.SUCCESS);
    }
    public static ResponseInfo success(Object data, String msg) {
        return new ResponseInfo(ResponseCode.SUCCESS, msg, data);
    }


    /**
     * 前端显示失败消息
     * @param msg 失败消息
     */
    public static ResponseInfo fail(String msg) {
        return new ResponseInfo(ResponseCode.ERROR, msg);
    }

    public static ResponseInfo fail(Integer code, String msg) {
        return new ResponseInfo(code, msg);
    }

    /**
     * 转换
     * @param responseInfo 源结果
     * @param code 响应码
     * @param msg 响应信息
     * @param data 响应数据
     */
    public static void transfer(ResponseInfo responseInfo, Integer code, String msg, Object data){
        responseInfo.code = code;
        responseInfo.msg = msg;
        responseInfo.data = data;
    }
}
