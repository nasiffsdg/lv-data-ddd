package com.lv.common.utils;

import java.util.regex.Pattern;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/8/27 6:46 PM
 */
public final class MatcherUtil {
    public static boolean isEmailStrValid(String str) {
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(str).matches();
    }
}
