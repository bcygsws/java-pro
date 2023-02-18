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

// 四、体会父类、子类super和Java中访问成员的就近原则
class MyBase {
    int a;
    int b;
    int c;

    public void method1() {
        System.out.println("我是MyBase父类method1方法");
    }

    public void method2() {
        System.out.println("我是MyBase父类method2方法");
    }
}

class MyDerived extends MyBase {
    int a;
    int b;
    int c;

    // 注意：子类中的method1中参数列表和父类中的method1参数列表不同，因此没有形成覆盖
    public void method1(int n) {
        System.out.println("我是MyDerived子类method1方法");
    }

    public void method2() {
        System.out.println("我是MyDerived子类method2方法");
    }

    public void method() {
        // 就近原则，子类自己定义了a b c变量，访问的是子类的a b c
        // 访问的是子类的a b c变量
        a = 10;
        b = 20;
        c = 30;
        // super关键字是用来访问父类成员的，
        // 变量：写法 super.变量名 方法：写法 super.方法名(无参或者有参)
        // 当super调用父类构造函数，方法名可以省略；直接super(参数)就可以了
        // 以下访问的是父类的a b c变量
        super.a = 15;
        super.b = 25;
        super.c = 35;
        method1();// 这个无参的method1,子类中没定义；推断访问的是父类的method1()方法
        method1(10); // 这个是有参的method1,就近原则，先排查子类中是否定义了该方法？子类恰好定义了这个方法，访问的是子类的method1方法
        method2();// 父类和子类的method2方法形成了覆盖关系，因此就近调用的一定是子类的method2方法
        super.method2();// 访问的是父类的method2方法

    }
}
/*
 * 参考文档：https://blog.csdn.net/qq_58710208/article/details/120410867
 * 一、继承语法
 * 写法：
 * 修饰符 class 子类名 extends 父类{
 *
 * }
 * 二、继承（概念拆开）
 * 2.1 子类继承父类的成员变量和成员方法
 * 2.2 同时，子类能改添加和修改自己的方法
 *
 * 三、父类成员访问
 * Fat类
 * Son类
 * Fat1类
 * Sn1类
 * 3.1 子类中引用的变量，子类没有定义，继承父类中的变量
 * 3.2 子类中引用的变量，子类中有定义，使用子类中的变量
 * 3.3 子类中引用的变量，在父类和子类之中都有定义，子类会隐藏父类中的变量；“就近原则”使用子类中的变量
 *
 * 四、关于是否使用super关键字调用(父类和子类中的【就近原则】)
 * 1.当父类提供了有参数的构造函数时(没有提供无参数构造函数)，无法为子类添加默认构造函数，需要在子类中显示定义构造函数，
 * 并且通过super显式调用一下，否则代码报错;
 * 而且要注意两点：
 * 1.1 【super显示调用必须是子类构造函数中的第一条语句】
 * 1.2 super只能在子类构造函数中第一条语句出现一次，不能和this同时出现
 * 1.3 super关键字只能出现在非静态方法中(构造函数或普通成员方法都行，但是必须是非静态方法中才能使用super关键字)，入口
 * 函数main是静态方法，里面是不能出现super关键字的
 *
 * 2.当父类提供了无参数构造函数时，子类可以不使用super关键字显式调用父类的构造函数（子类构造函数中隐藏了super()语句）；
 * 系统默认会调用父类的无参构造函数
 * 3.当父类和子类都没有提供构造函数时，系统就会为父类和子类各自生成一个无参构造函数
 * 4.如果有父类，会先执行父类的构造函数，然后再执行子类的构造函数
 *
 * 五、子类构造函数和父类构造函数
 * 构造函数：识别构造函数，和类同名，没有返回值，也不用void修饰；构造函数可以有0,1，甚至多个参数
 * 构造函数【不能】继承，构造函数也就不能覆盖，但构造函数可以重载
 * 默认构造函数的修饰符和类修饰符一样，比如：定义的类是public,默认生成的构造函数就是public
 *
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