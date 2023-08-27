package com.lv.application.project.impl;

import cn.hutool.core.lang.JarClassLoader;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.reflect.Reflection;
import com.lv.application.project.ClassService;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.mapper.Mapper;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.module.Configuration;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/31 8:59 PM
 */
@Log4j2
@Service
public class ClassServiceImpl implements ClassService {


    @Override
    public void resolve(String className) {
        String fullPath = className.substring(0, className.indexOf(StrUtil.DOT));
        fullPath = fullPath.replaceAll(StrUtil.SLASH, StrUtil.DOT);



        try {
            Class<?> forName = Class.forName(fullPath);

            Method[] methods = forName.getMethods();
            for (Method method : methods) {
            }

            if (forName.getAnnotation(Service.class)!=null){
                System.out.println("Service"+ "  "+className);
            }
            if (forName.getAnnotation(Component.class)!=null){
                System.out.println("Component"+ "  "+className);
            }
            if (forName.getAnnotation(RestController.class)!=null){
                System.out.println("Controller"+ "  "+className);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
