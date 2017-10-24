package com.ronglexie.test.reflect;

import com.ronglexie.annotation.RongleField;
import com.ronglexie.annotation.RongleTable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 根据反射操作注解
 *
 * @author ronglexie
 * @version 2017/10/6
 */
public class TestReflectAnnotation {
    public static void main(String[] args) {

        try {
            Class clazz = Class.forName("com.bjsxt.test.annotation.RongleStudent");

            //获得类的所有有效注解
            Annotation[] annotations=clazz.getAnnotations();
            for (Annotation a : annotations) {
                System.out.println(a);
            }
            //获得类的指定的注解
            RongleTable st = (RongleTable) clazz.getAnnotation(RongleTable.class);
            System.out.println(st.value());

            //获得类的属性的注解
            Field f = clazz.getDeclaredField("studentName");
            RongleField rongleField = f.getAnnotation(RongleField.class);
            System.out.println(rongleField.columnName()+"--"+rongleField.type()+"--"+rongleField.length());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
