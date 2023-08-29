package com.lv.common.utils;

import java.util.Random;

/**
 * 随机生成Code工具类
 * @author QiangZai
 * @version 1.0
 * @date 2023/8/27 9:53 PM
 */
public class CodeGenerateUtils {

    /**
     * 随机六位生成验证码
     * @return 验证码
     */
    public static Integer generateValidateCode(){
        //生成随机数，最大为999999
        int code = new Random().nextInt(999999);
        if(code < 100000){
            //保证随机数为6位数字
            code = code + 100000;
        }
        return code;
    }
}
