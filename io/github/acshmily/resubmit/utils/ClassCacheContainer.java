package io.github.acshmily.resubmit.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: Huanghz
 * description: Class 缓存容器
 * date:Created in 15:05 2022/5/20
 * modifyBy:
 **/
public class ClassCacheContainer {
    /**
     * 添加缓存
     * @param clazz
     * @param fields
     */
    public static void addClassFields(Class clazz, Field[] fields){
        cacheClassFields.put(clazz,fields);
    }

    /**
     * 获取缓存
     * @param clazz
     * @return
     */
    public static Field[] getClassFields(Class clazz){
        return cacheClassFields.get(clazz);
    }

    /**
     * 判断缓存是否命中
     * @param clazz
     * @return
     */
    public static boolean existClassFields(Class clazz){
        return cacheClassFields.containsKey(clazz);
    }

    /**
     * 添加方法缓存
     * @param methodSignature
     * @param annotations
     */
    public static void addMethodSignature(Method methodSignature, Annotation[][] annotations){
        cacheMethodSignatures.put(methodSignature,annotations);
    }
    /**
     * 判断缓存是否命中
     * @param methodSignature
     * @return
     */
    public static boolean existMethodSignature(Method methodSignature){
        return cacheMethodSignatures.containsKey(methodSignature);
    }

    /**
     * 获取方法缓存
     * @param methodSignature
     * @return
     */
    public static Annotation[][] getMethodSignature(Method methodSignature){
        return cacheMethodSignatures.get(methodSignature);
    }

    private static final ConcurrentHashMap<Class, Field[]> cacheClassFields = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<Method, Annotation[][]> cacheMethodSignatures = new ConcurrentHashMap<>();
    private ClassCacheContainer(){}
}
