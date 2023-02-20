package com.nx.to;

/**
 * java_over
 *
 * @author baochengyi
 * @date 2023/2/19 23:13
 * @description
 */
public class OverrideTest {
    public static void main(String[] args) {
        MyDate t1 = new MyDate();
        // a.未在MyDate类中重写toString方法前，打印t1对象
        // System.out.println(t1);// com.nx.to.MyDate@3ac3fd8b
        // System.out.println(t1.toString());// com.nx.to.MyDate@3ac3fd8b

        // b.在MyDate类中重写toString()方法后，打印实例对象t1和t1.toString()的结果是：
        // new MyDate()时，该类的无参构造函数执行，该构造函数调用了它的一个重载方法MyDate(int year,int month,int day)
        System.out.println(t1);// 1970年1月1日
        System.out.println(t1.toString());// 1970年1月1日

        Student stu = new Student(1212, "张瑶");
        // a.在Student中重写toString()方法前
        // System.out.println(stu);// com.nx.to.Student@5315b42e
        // System.out.println(stu.toString());// com.nx.to.Student@5315b42e
        // b.在Student中重写toString()方法前
        System.out.println(stu);//姓名：张瑶 学号：1212
        System.out.println(stu.toString());// 姓名：张瑶 学号：1212
    }

}

class MyDate {
    private int year;
    private int month;
    private int day;

    // 构造方法
    public MyDate() {
        // 在构造函数的第一行，使用this关键字，调用有三个参数的构造函数
        this(1970, 1, 1);
    }

    // 构造方法的重载
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    // 重写Object类中的toString()方法后，观察打印对象t1和t1.toString()的变化
    public String toString() {
        return this.year + "年" + this.month + "月" + this.day + "日";
    }
}

class Student {
    private int no;
    private String name;

    public Student(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public String toString() {
        return "姓名：" + this.name + "\t学号：" + this.no;
    }
}

/*
 *
 * @ Java多态的表现形式：编译时多态 重载 和运行时多态 覆盖
 * 参考文档：https://blog.csdn.net/m0_61933976/article/details/125070919
 *
 * 一、重载：overload,重载是在一个；类中多态性的一种表现，它是在同一个类中定义了多个同名方法，通过定义参数的类型、个数和顺序来实现
 * 1.1 重载是通过方法的参数来区分的，包括参数的个数、类型和顺序
 * 1.2 重载不能通过方法的访问权限、返回值类型和异常类型来实现
 * 1.3 如果一个基类中的方法定义为了private，那么在派生类中对该方法不能达到重载的效果，只是定义了不同的方法（这种情形是重载失效或
 * 不能重载）
 * 注：private方法不能重载、覆盖、不能继承
 *
 * 二、覆盖:override或者重写 overwrite
 * 覆盖是指派生类函数覆盖基类函数。覆盖是对一个方法进行重写，以达到一定的作用
 *
 * 覆盖满足的判断：
 * a.父类和子类有继承关系
 * b.两相同：参数列表和方法名
 * c.一大：访问权限
 * d.一小：返回值类型
 * e.一少：被覆盖方法异常更少
 *
 * 另外：
 * 覆盖只使用非静态方法，静态方法覆盖没有意义
 * 构造函数不能继承，因此构造函数肯定不能覆盖
 * 2.1 派生类中的覆盖方法必须与基类的被覆盖方法有 相同的方法名和参数
 * 2.2 派生类中覆盖方法必须与基类中的被覆盖方法必须有相同返回值类型（如果是引用类型，可以范围更小） 相同或更小
 * 2.3 派生类中的覆盖方法必须与基类中的被覆盖方法必须有相同异常类型（或者是子类） 相同或更少的异常
 * 2.4 派生类中的覆盖方法必须有更高的访问权限  更大访问权限
 *
 * private方法不能覆盖，在子类中即使书写了满足条件的“覆盖方法”，只是新建了一个不同的方法
 *
 * 三、覆盖Object类中的toString()方法
 * 3.1 Ojbect类中toString()的默认实现是：
 *    public String toString() {
 *          return getClass().getName() + "@" + Integer.toHexString(hashCode());
 *    }
 * toString()没有重写时，输出的结果是：类名+@+一个16进制的哈希码
 *
 * 3.2 Object是任何类的基类，因此在创建的任何一个类中书写：public String toString()都是覆盖
 *
 *
 * */