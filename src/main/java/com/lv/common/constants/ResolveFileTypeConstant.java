package com.lv.common.constants;

/**
 * 能够解析的文件类型
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/31 8:44 PM
 */
public interface ResolveFileTypeConstant {

    String JAVA = "class";
    String XML = "xml";
    String YAML = "yaml";
    String YML = "yml";
    String PROPERTIES = "properties";

    /**
     * 获取能够解析的所有文件类型
     * @return 文件类型数组
     */
    static String[] getAllType() {
        return new String[]{JAVA, XML, YAML, YML, PROPERTIES};
    }

}
