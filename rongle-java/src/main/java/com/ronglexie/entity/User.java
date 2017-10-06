package com.ronglexie.entity;

/**
 * User实体
 *
 * @author ronglexie
 * @version 2017/10/6
 */
public class User {
    private String id ;
    private String name;
    private Integer age;

    public User(String id, String name, Integer age) {
        this.id = id;
        name = name;
        this.age = age;
    }

    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", Name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
