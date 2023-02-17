/**
 * @Description: 面向对象基本特征之继承
 * @author: baochengyi
 * @date: 2023.02.15
 * @param null
 * @return null
 **/
package com.my.test;

class Sort {
    // 返回：返回排序后的数组
    // 定义插入排序函数inserted
    public void inserted(int[] a) {
        if (a != null) {
            // 1.for循环，外循环控制趟数
            for (int i = 1; i < a.length; i++) {
                // 2.标记第0个元素的值和索引
                int temp = a[i];
                int j = i;
                // 3.选择语句，预测试，a[j-1]和a[j]的大小；如果不满足就不必执行下面的循环了，减少代码执行的内存消耗
                if (a[j - 1] > temp) {
                    while (j >= 1 && a[j - 1] > temp) {
                        a[j] = a[j - 1];
                        j--;
                    }
                }
                a[j] = temp;
            }
        }
    }
}

// 测试Java的基本特征；继承
public class Inheritance {
    public static void main(String[] args) {
        System.out.println("hello world");
        Sort s = new Sort();
        // insertedSort函数，传入一个数组，实现升序排序
        int[] a = {13, 5, 16, 7, 23};
        // 打印排序后的数组
        s.inserted(a);
        for (int j = 0; j < a.length; j++) {
            System.out.print(a[j] + "\t");
        }
        // 增强版for循环写法
        // for (int j : a) {
        //     System.out.print(j + "\t");
        // }
        Son son = new Son();
        System.out.println("");
        son.tell();
        Person p1 = new Person("张红", "女性", 18);
        System.out.println("---------------");
        Person p2 = new Person("陶喆", "男性", 19);
        System.out.println("***************");
        Cat c1 = new Cat("小猫", "雄性", 3);
        System.out.println("---------------");
        Cat c2 = new Cat("囡囡", "雌性", 2);
        // 四、五 super关键字和构造函数
        MyDerived md = new MyDerived();
        md.method();

    }
}
/**
 * @ 面向对象的特征-继承
 * 参考文档1：https://www.cnblogs.com/zxdongcopyright/p/16387287.html
 * 参考文档2：https://blog.csdn.net/qq_58710208/article/details/120410867
 * 文档2很重要，涉及Java中三大基本特征的东西：理解记忆，重要
 *
 * 一、继承
 * 继承是一种联结类的层次结构，允许和鼓励类的重用。子类继承父类的特性
 * 1.新类称为原始类的派生类，原始类称为新类的基类
 * 父类 原始类 基类
 * 子类 新类   派生类
 * 2.子类继承了父类的方法和实例变量，同时派生类还可以改变或增加自己的方法，以适合开发的需要
 *
 * 面向对象的优点
 * 1.提高软件的开发效率（组合或继承都开发方式，提高了代码的复用，面向对象非常接近人的正常思维）
 * 2.提高软件的鲁棒性
 * 鲁棒性,是控制论的一个术语，鲁棒性是指一个控制系统在某些参数的摄动下，保持性能相对稳定的特性
 * 面向对象程序可以重用大量可以复用并且经过长期测试的代码，这提高代码的鲁棒性
 * 3.提高软件的可维护性
 * 软件有非常成熟的设计模式，使得代码结构清晰
 * 同时，代码的可读性非常好
 *
 * 二、继承和组合的区别
 * Vehicle 歪扣 读音，车辆，交通工具
 * Car 汽车，是交通工具的一种,继承关系，is-a关系
 * Tire 轮胎，一辆汽车有多个轮胎，汽车和轮胎是组合关系，has-a关系
 *
 * 继承关系
 * class Vehicle {
 *
 * }
 * class Car extends Vehicle{
 *
 * }
 *
 * 组合关系
 * class Tire{
 *
 * }
 * class Car extends Vehicle{
 * // 轮胎不是汽车，汽车中有多个轮胎
 * private Tire t=new Tire();
 * }
 * 三、使用场景
 * 1.除非两个类是确定的继承关系，否则不要使用继承来实现代码的重用，继承会增加程序的维护难度和成本
 * 2.不要为了实现多态而使用继承，如果两个类之间没有is-a的关系，使用组合和接口能够实现同样的功能
 * 3.非必要条件不要使用继承，而应使用组合
 *
 * 四、继承的注意事项
 * 1.Java中只能单继承，即一个类至多只有一个父类；但是，可以通过接口实现多继承的效果
 * 2.非public和protected的类，不能继承
 * 3.子类和父类中有相同的成员变量时，子类中的成员变量会覆盖父类中的成员变量，子类不会继承父类中的成员变量
 * 4.子类和父类中有相同的方法名和参数列表（参数个数、类型、顺序）时，子类中的方法会覆盖父类中的方法，子类不会继承父类的方法
 *
 * IDEA bug：
 * bug1:
 * 剪切和粘贴ctrl+v/ctrl+x失效
 * 解决办法；
 * 参考文档：https://dandelioncloud.cn/article/details/1529603728915316737
 * 菜单栏-工具-将Vim Emulator前面的 √ 去掉就可以了
 *
 * bug2:
 * IDEA 文件头注释：只设置了Java
 * 解决办法：
 * 参考文档：https://www.jianshu.com/p/e748cf3e5799
 *
 * bug3:
 * 快捷键：// + tab键
 * 可以生成一个空的头文件块注释
 *
 * bug4:
 * 快捷键：块注释 ctrl+shift+A
 *
 * bug5:
 * IDEA 空行shift+alt+p，块注释中出现了p
 * 解决办法：
 * https://blog.csdn.net/qq_15674695/article/details/90896602
 * 代码样式-Java-其他，将“在其他空行中生成p” 前面的√ 去掉就好了
 */