package com.company.cn;

public class Student {
    private String name;// 姓名
    private int id;// id
    private char sex;// 性别

    // public 成员方法任何类中都可以访问，这次Application中就不会报错了
    public String getName() {
        return this.name;
    }

    // 给name id sex设置值
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getSex() {
        return this.sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}