package com.my.test;

/**
 * java-inheritance
 *
 * @author baochengyi
 * @date 2023/2/16 0:38
 * @description
 */
// final public class Person {
//     // 1.final修饰符定义常量，常量的值一经赋值，就不能再修改
//     final int a = 3;
//
//     public void test() {
//         // 报错：无法将值赋给 final 变量 'a'
//         // a = 4;
//     }
// }

// 代码报错：无法从final 'com.my.test.Person' 继承
// 2. Person定义的是final类，final类不能继承
// class Men extends Person {
//
// }
public class Person {
    // 静态代码块
    static {
        System.out.println("静态代码块");
    }

    String name;
    String gender;
    int age;

    // 实例代码块
    {
        System.out.println("实例代码块");
    }

    public Person(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        System.out.println("构造函数调用");
    }
}

// 有继承的顺序
class Animal {
    static {
        System.out.println("父类Animal静态代码块");
    }

    String name;
    String gender;
    int age;

    // 父类的实例代码块
    {
        System.out.println("父类Animal的实例代码块");
    }

    public Animal(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        System.out.println("父类Animal的构造方法");
    }
}

class Cat extends Animal {
    // 静态代码块，随着类的定义，开始装载和分配；而且静态代码块只执行一次
    static {
        System.out.println("子类Cat静态代码块");
    }

    String name;
    String gender;
    int age;

    // 实例代码块在new构造函数调用之前执行，每new创建一次，执行一次
    {
        System.out.println("子类Cat的实例代码块");
    }

    public Cat(String name, String gender, int age) {
        // this.name = name;
        // this.gender = gender;
        // this.age = age;
        // 父类是有参构造函数，子类使用super调用一下，而且super(name,gender,age);放在第一行
        super(name, gender, age);
        System.out.println("子类Cat的构造函数调用");
    }
}
/*
 * 参考文档：https://blog.csdn.net/qq_58710208/article/details/120410867
 *  六.执行顺序
 *  无继承关系时执行顺序
 *  Person类演示
 *  大致顺序：
 * 静态代码块  随着类的定义而装载和分配
 * 实例代码块  在构造函数之前调用
 * 构造函数调用 构造函数调用
 *
 *
 * 有继承关系时执行顺序
 * 父类静态代码块
 * 子类静态代码块
 * 父类实例代码块
 * 父类构造函数
 * 子类实例代码块
 * 子类构造函数
 *
 *
 *  七.继承方式
 *  1.单继承,A继承B
 *  2.多层继承, A继承B,B继承C
 *  3.不同类继承同一个类，B继承A；C也继承A
 *  4.Java不支持多继承，可以使用接口达到“多继承”的效果
 *
 * 八.final关键字总结
 *  1.变量：final定义的变量，一经赋值，就不能够修改
 *  2.final类不能继承
 *  3.final定义的方法，子类也不能够覆盖
 *
 *
 * */