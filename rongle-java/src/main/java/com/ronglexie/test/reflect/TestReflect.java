package com.ronglexie.test.reflect;

import com.ronglexie.entity.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射基础知识
 *
 * @author ronglexie
 * @version 2017/10/6
 */
public class TestReflect {
    public static void main(String args[]){
        try {
            //获取Class对象
            Class clazz = Class.forName("com.ronglexie.entity.User");
            System.out.println("ClassSimpleName"+clazz.getSimpleName());

            //获取方法名
            String className = clazz.getName();
            System.out.println("className:"+className);

            //获取属性
            Field[] publicFields = clazz.getFields();//获取公开的属性
            Field[] fields = clazz.getDeclaredFields();//Declared获取所有的属性
            for (Field field : fields){
                System.out.println("Field:"+field);
            }

            //获取方法
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods){
                System.out.println("Method:"+method);
            }

            //获取构造器
            Constructor[] constructors = clazz.getDeclaredConstructors();
            for (Constructor c : constructors) {
                System.out.println("Constructor:"+c);
            }

            reflectUser(clazz);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 用反射调用方法、操作属性
     * @param clazz
     */

    public static void reflectUser(Class<User> clazz){
        try {
            //通过反射API调用构造方法
            User user = clazz.newInstance();//无参构造方法
            System.out.println("User"+user);

            Constructor<User> constructor = clazz.getDeclaredConstructor(String.class,String.class,Integer.class);//有参构造方法
            User u1 = constructor.newInstance("u1","u1",1);
            System.out.println("U1"+u1.toString());

            //通过反射API调用普通方法
            User u2 = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("setId",String.class);
            method.setAccessible(true);//设置此方法不需要做安全检查，可直接访问，用于访问私有方法
            method.invoke(u2,"u2");
            System.out.println("U2:"+u2.toString());

            //通过反射API操作属性
            User u3 = clazz.newInstance();
            Field field = clazz.getDeclaredField("name");
            field.setAccessible(true);//设置此属性不需要做安全检查，可直接访问，用于访问私有属性
            field.set(u3,"u3");
            System.out.println("U3:"+u3.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
