package com.jane.das.commons.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;

public class CopyBeanUtils extends BeanUtils {
    /**
     * 把前端传递过来的source和从数据库里面取出的对象target进行合并最后放到target中
     * @param source 前端传递过来的对象
     * @param target   从数据库里面取出的对象(最后更新的时候用这个对象)
     * @throws BeansException
     *
     */
    public static void copyProperties(Object source, Object target) throws BeansException {
        if (null != target && null != target) {
            Class<?> actualEditable = target.getClass();   /// 获得目的对象的类装载器
            PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable); // 获得属性数组
            for (PropertyDescriptor targetPd : targetPds) { // 遍历所有属性
                if (targetPd.getWriteMethod() != null) {  // 判断属性是否有set方法
                    // 获得源对象相对的属性
                    PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                    // 判断源对象的属性是否为空且不存在get方法
                    if (sourcePd != null && sourcePd.getReadMethod() != null) {
                        try {
                            // 获取源对象的get方法
                            Method readMethod = sourcePd.getReadMethod();
                            // 判断源对象的get方法权限是否是public
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                // 不是public,修改为public
                                readMethod.setAccessible(true);
                            }
                            // 获取其值
                            Object value = readMethod.invoke(source);
                            // 这里判断以下value是否为空 （当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等），
                            // 不为空时将源对象的值复制到目的对象
                            if (value != null) {
                                Method writeMethod = targetPd.getWriteMethod();
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                writeMethod.invoke(target, value); // 写入值
                            }
                        } catch (Throwable ex) {
                            throw new FatalBeanException("Could not copy properties from source to target", ex);
                        }
                    }
                }
            }
        }
    }
}
