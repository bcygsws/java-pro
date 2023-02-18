package com.my.test;

/**
 * java-inheritance
 *
 * @author baochengyi
 * @date 2023/2/17 20:46
 * @description
 */
// 3.1 子类和父类中存在不同名的变量
public class Fat {
    int a;
    int b;
}

class Sn extends Fat {
    int c;

    public void method() {
        a = 1;// 子类中没有定义，访问的是父类中的a
        b = 2;// 子类中没有定义，访问的是父类的b
        c = 3;// 子类有定义，就近访问子类的
    }
}

// 3.2 子类和父类中存在同名和类型不同的变量
class Fat1 {
    int a;
    char b;
    int c;
}

class Sn1 extends Fat1 {
    int b;// 与父类同名，不同类型
    int c;// 与父类同名，相同类型

    public void method() {
        a = 10;// 子类中没有定义，访问的是父类中的a
        b = '1';//
        c = 30;//
        System.out.println(b);// 49
        System.out.println(c);// 30 访问的是子类的变量c
        System.out.println(b == super.b);// false
        System.out.println(c == super.c);// false
    }
}
/*
 *
 * @ 访问父类成员
 * 1.子类访问父类成员变量，子类中本身没有定义变量，则使用从父类中继承的变量
 * 2.子类访问父类成员变量，子类中定义了访问的变量，则直接使用子类中的变量
 * 3.子类访问父类的成员变量，子类和父类中都定义了同名变量，则父类中的同名的变量会被“隐藏”，就近原则使用子类中的变量
 *
 * */
