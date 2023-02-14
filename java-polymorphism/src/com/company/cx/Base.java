package com.company.cx;

// 基类：Base
public class Base {
    // 推断为构造函数
    // 原因：没有返回值，返回值也不是void；推断public Base(){}是父类Base的构造函数
    public Base() {
        g();
    }

    public void f() {
        System.out.println("Base f方法");
    }

    public void g() {
        System.out.println("Base g方法");
    }
}

// 派生类Derived
class Derived extends Base {
    public void f() {
        System.out.println("Derived类的f方法");
    }

    public void g() {
        System.out.println("Derived类的g方法");
    }
}
