package com.my.test;

/**
 * java-inheritance
 *
 * @author baochengyi
 * @date 2023/2/16 0:38
 * @description
 */
final public class Person {
    // 1.final修饰符定义常量，常量的值一经赋值，就不能再修改
    final int a = 3;

    public void test() {
        // 报错：无法将值赋给 final 变量 'a'
        // a = 4;
    }
}

// 代码报错：无法从final 'com.my.test.Person' 继承
// 2. Person定义的是final类，final类不能继承
// class Men extends Person {
//
// }
/*
 * 参考文档：https://blog.csdn.net/qq_58710208/article/details/120410867
 *  六.执行顺序
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