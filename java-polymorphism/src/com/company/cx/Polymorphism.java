package com.company.cx;

// 多态代码的演示
public class Polymorphism {

    public static void main(String[] args) {
        // write your code here
        System.out.println("hello World!");
        /**
         *
         * @ 多态：成员变量是无法实现多态的，多态只有在方法中实现
         * 重载：编译时多态
         * 覆盖：运行时多态
         * 1.new Derived()时，先调用一下父类的构造方法，然后再调子类构造方法
         * 2.父类构造方法执行时，public Base(){
         *     g();
         * }
         * 父类的构造方法先执行一次，调用g()方法，g()方法是覆盖后的方法，打印的仍然是子类的g方法
         *
         * */
        // b是Base类型
        Base b = new Derived();// 向上转型
        // 多态：子类创建的对象赋值给父类型的变量，运行时仍然表现出子类的行为特征；这意味着同一个类型的对象调用同一个方法时，可能
        // 会表现出多种行为特征（若：Derived1()也继承自Base;那么Base b=new Derived1(); b.f();打印的结果就是Derived1类中的f方
        // 法，这就是多态的妙处）
        // new Derived()创建的是Derived类，b.f();b.g();都打印子类的Derived
        b.f();
        b.g();
        /**
         *
         * @ 打印结果：
         * hello World!
         * Derived类的g方法
         * Derived类的f方法
         * Derived类的g方法
         *
         * */
    }
}
