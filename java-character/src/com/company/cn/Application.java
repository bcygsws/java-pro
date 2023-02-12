package com.company.cn;

public class Application {

    public static void main(String[] args) {
        // write your code here
        Student s = new Student();
        // 1.报错：name在com.company.cn.Student中是private访问控制;name是Student类中的私有属性；私有属性不能够在当前类之外的
        // 地方直接调用
//         System.out.println(s.name);
        // 为此不能在Application类中直接使用实例s访问类成员
        // 2.在Student类中定义一个方法getName
        // getName方法访问控制符是public，任何类都可以访问，所以不会报错了
        // System.out.println(s.getName());// null
        // System.out.println(s.getId());// 0
        // System.out.println(s.getSex());// 空
        // 3.使用getX和setX修改和访问属性
        s.setName("张三丰");
        s.setId(1890);
        s.setSex('男');// char类型传值使用单引号，String字符串传值使用双引号
        System.out.println(s.getName());
        System.out.println(s.getId());
        System.out.println(s.getSex());
    }
}
/**
 * @ Java三大特性：封装
 * 体会封装的好处
 * 1.提高程序的安全性，保护数据
 * 2.隐藏代码的实现细节 【setX系列中处理传入的参数的逻辑，对Application是隐藏的】
 * 3.统一接口
 * 4.提高代码的可维护性
 */
