package com.pro.test;

// 注意1：main方法并不一定程序执行时的第一个模块？静态模块位置在入口函数main方法之前
// 注意2：静态块不管顺序如何，都会在main()方法之前执行
class Father {
    void print() {
        System.out.println("Father类中print方法");
    }
}

public class Test extends Father {
//      静态块在类被加载时调用
//    static {
//        System.out.println("静态块不论书写位置如何都会在入口函数之前执行");
//    }

    public static void main(String[] args) {
        System.out.println("你好");
//        向上转型
        Father c = new Test();
        c.print();
    }

    static {
        System.out.println("静态块不论书写位置如何都会在入口函数之前执行");
    }
}
/*
 * 静态方法和类对象方法
 * java中使用static关键字修饰的方法，叫做静态方法；不用static修饰的方法，是属于具体类型的方法（实例方法）
 *
 * 1.引用实例方法，可以使用对象.实例方法或者类名.实例方法来调用
 * 2.static方法不能被覆盖
 * 3.static方法只能访问static方法（和static成员），不能访问非static方法，但非static方法可以访问静态方法
 * 4.static方法是属于整个类的，它在内存中随着类的定义而被分配和装载，而非static定义的方法是属于某个对象的方法
 * 在这个对象创建时，在对象的内存中拥有这个方法的专用代码段
 *
 * */
