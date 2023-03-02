package com.inter.ts;

import java.util.Objects;

interface IShape {
    public static final int a = 10;
    // 接口中变量，默认是public static final修饰的；成员方法默认是public abstract修饰的
    int b = 15;// 编译时，会添加默认修饰符；方法也是一样，会为没实现的方法添加修饰符;既然是final常量，声明后要立即赋初值

    // 3.接口中的静态方法【不能】重写
    public static void sleep() {
        System.out.println("接口中的静态方法！");
    }

    // 1.接口中的成员方法默认修饰必须有abstract，哪怕不写abstract关键字，编译时也会自动添加abstract修饰；
    // 在接口实现类中，抽象方法【必须】重写
    abstract void draw();

    // 2.jdk1.8后成员方法可以在接口中写实现，但是必须有default修饰（这是默认方法），默认方法在接口实现类中【可以不】重写
    default public void eat() {
        System.out.println("接口中的默认方法！");
    }


}

// 实现多个接口达到“多重继承”的目的
interface C {
    void sleep();
}

interface D {
    void play();
}

// 接口实现多继承和多态简单案例
interface IRun {
    void run();
}

interface ISwimming {
    void swimming();
}

interface IFly {
    void fly();
}

/*
 * 抽象类和接口
 * 参考文档：
 * https://blog.csdn.net/qq_62712350/article/details/126319408
 * 一、抽象类
 * 1.1 抽象类用abstract修饰，抽象方法也用abstract修饰；抽象类可以有自己的普通成员
 * 1.2 抽象类中可以声明抽象方法，但是不能实现（抽象类中的普通成员可以实现）
 * 1.3 抽象方法不能使用private修饰符，为了遵守重写的规则（private方法不能重写）
 * 1.4 抽象类不能实例化
 * 1.5 抽象类可以有构造方法，为了方便子类调用抽象类的成员
 * 1.6 抽象类是为了继承，抽象类中定义的抽象方法，必须在抽象类子类中重写，否则该子类还是抽象类
 * 1.7 如果一个抽象类A继承另一个抽象类B,抽象类B中抽象方法可以不重写；但是如果有其他类继承抽象类A,
 * 那么抽象类B中的抽象方法还必须被重写
 *
 *
 * 二、接口
 * 2.1 接口用interface修饰，接口实际上一种引用类型
 * 2.2 接口中变量默认是public static final，编译接口中变量时，会主动加上默认修饰符。因为它是final常量，声明时必须为
 * 成员变量赋初值（抽象类中普通成员则不必）
 * 2.3 抽象方法必须用abstract修饰，不能用final、static、native和synchronized修饰；即使不写abstract关键字，也会默认
 * 添加abstract
 * 2.4 接口不能实例化（和抽象类一样）
 * 2.5 抽象方法必须被实现，抽象类中的静态方法不能被实现；抽象类中的普通成员方法,在接口实现类中肯定不能实现（static不谈重写）;
 * 但是在jdk1.8以后，普通成员方法可以实现了，但是必须为它加上default修饰，同时默认方法可以不在接口实现类中重写
 * 2.6 接口中不能有构造方法，但是接口可以实现implements其他接口
 * 2.7 一个类只能继承自一个父类，但可以实现多个接口，格式：implements C,D
 * Long Term Support=LTS 长期支持版本
 *
 * 三、抽象类和接口的区别
 * 3.1 抽象类可以包含普通成员，接口中不可以包含普通成员
 * 3.2 抽象类用来继承，使用extends由其子类继承；接口是用来实现的，使用implements关键字
 * 3.3 抽象类可以实现一个或多个接口，但是接口不能继承抽象类；接口可以使用extends关键字继承一个或多个父类接口
 * 参考文档：https://jingyan.baidu.com/article/359911f5aa0e4116ff03063e.html
 * 3.4 一个子类只能继承一个抽象类，一个子类可以实现多个接口
 *
 * 四、Object类
 *  4.1 Object是所有其他类的父类，可以理解为定义任何类都省略了extends Object这些代码
 *  4.2 Object类中有一些特殊方法：重写toString和重写equals方法
 *  关于toString()方法的重写，前已详细讨论过
 *  实例：演示重写equals方法
 *
 *
 *
 *
 *
 * */
// 验证3.3 抽象类可以实现多个接口，但是接口却不能继承抽象类；接口可以使用extends关键字继承一个或多个接口
interface inter1 {
    void fun1();
}

interface inter2 extends inter1 {
    void fun2();
}

interface inter3 extends inter1, inter2 {
    void fun3();
}

/**
 * java-interface
 *
 * @author baochengyi
 * @date 2023/2/25 2:32
 * @description
 */
// 抽象类中可以像普通类一样，定义自己的普通成员变量和成员方法，成员方法可以实现；
abstract class ChouXiang {
    // 1.普通成员
    public int a;

    // 抽象方法：public abstract修饰，且没有方法体，识别它是抽象方法see,继承子类必须实现see方法，否则继承的子类
    // 仍然是抽象方法
    public abstract void see();

    // 2.普通成员变量
    public void func() {
        System.out.println("抽象类中可以定义普通的成员变量和成员方法");
    }
}

// 抽象类是为了继承
class Son extends ChouXiang {
    // 要重写see方法，如果不在当前子类中重写see方法，那么Son类仍然是抽象类
    public void see() {
        System.out.println("抽象类子类的实现方法");
    }
}

public class A implements IShape {
    @Override
    public void draw() {
        System.out.println("普通类A类中重写了draw方法");
    }

    // A中的成员方法
    public void setA() {
        System.out.println("设置普通类A中的方法");
    }
}

class B implements IShape {
    @Override
    public void draw() {
        System.out.println("普通类B中重写了draw方法");
    }

    // B中的成员方法
    public void setB() {
        System.out.println("设置普通类B中的方法");
    }
}

// 接口+组合关系
// 字母类,A和B都是字母；字母类和A(或B)都是组合关系
class StringTable {
    public void chooseStr(IShape ishape) {
        ishape.draw();
        // 调用A和B类中的特殊方法（非接口中声明的抽象方法）
        // 本方法的参数是ishape,A类和B类都是接口IShape的实现类，需要用到向下转型；向下转型可能抛出类型转换异常（ClassCastException）
        // 和空指针异常(NullPointerException),需要使用instanceof作分支判断
        if (ishape instanceof A) {
            A a = (A) ishape;
            a.setA();
        } else if (ishape instanceof B) {
            B b = (B) ishape;
            b.setB();
        }

    }
}

class E implements C, D {
    @Override
    public void sleep() {
        System.out.println("他睡觉了");
    }

    @Override
    public void play() {
        System.out.println("他在玩");
    }
}

// 实现多个接口和继承
class Animal {
    public String name;
    public int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Dog extends Animal implements IRun, ISwimming {
    // 构造函数
    public Dog(String name, int age) {
        // 父类是有参构造函数，必须在当前子类的构造函数的第一行使用super关键字调用
        super(name, age);
    }

    @Override
    public void run() {
        // 直接使用父类中定义的成员变量name和age
        System.out.println(age + "岁的" + name + "在跑");
    }

    @Override
    public void swimming() {
        System.out.println(age + "岁的" + name + "在游泳");

    }
}

class Duck extends Animal implements IRun, ISwimming, IFly {
    public Duck(String name, int age) {
        super(name, age);
    }

    @Override
    public void run() {
        System.out.println(age + "岁的" + name + "在跑");

    }

    @Override
    public void swimming() {
        System.out.println(age + "岁的" + name + "在游泳");

    }

    @Override
    public void fly() {
        System.out.println(age + "岁的" + name + "会飞");

    }
}

// 机器人不是动物，不是Animal的子类，仍然可以实现多态
class Robot implements IRun {
    @Override
    public void run() {
        System.out.println("机器人也会跑");
    }
}

// 体会equals用法，对比==，观察两者之间的去呗
class Person {
    public String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    /*
     *
     * 重写Object的equals方法public boolean equals(Object obj){
     *   // 方法体
     * }
     *
     * 重写内容：
     * 1. obj==null时，return false
     * 2. this==obj时，return true
     * 3. if(!obj instanceof Person){
     *   return false;
     * }
     *
     * 4.然后，obj是Person的一个实例时，如果实例对象的值相等，就返回true。否则返回false
     * this.name.equals(obj.name)&&this.age==obj.age 等于true,返回true;否则返回false
     *
     * */

    @Override
    public boolean equals(Object o) {
        // 打印this,this是Person类的实例
        // 在java类中创建的每个实例，它们都是不同的，源于创建时，申请了不同的栈地址来存储它们（即使它们堆里的键值对完全相同）
        System.out.println(this);// com.inter.ts.Person@168edbe
        if (null == o) return false;
        if (this == o) return true;
        // 判断o引用是否为java堆内存中的一个Person实例
        if (!(o instanceof Person)) return false;
        // 执行到此处，就是引用o
        /*
         * 此处，为啥要将传入的o(默认的Object类型)向下转型？
         * 1.因为多态仅仅限于成员方法，成员变量是无法实现多态的
         * 2.为了访问Person类自己传入的name、age，必须将传入的o(Object类型)向下转型，成为person自己的实例对象；person实例对象调
         * 用自己的name和age值
         *
         * */
        Person person = (Person) o;
        if (this.age == person.age && this.name.equals(person.name)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
// 测试类

class Test {
    public static void main(String[] args) {
        StringTable st = new StringTable();
        st.chooseStr(new A());
        st.chooseStr(new B());
        // 静态成员中只能调用静态成员，因此test修饰成static
        test(new Dog("小花狗", 3));
        test(new Duck("可爱鸭", 2));
        test(new Robot());
        Person p1 = new Person("小红", 16);
        Person p2 = new Person("小红", 16);
        // 基本数据类型age使用==时
        int a = 10;
        int b = 10;
        System.out.println(a == b);// true
        // 引用类型比较使用==和equals方法都是false
        /*
         *
         * 一、对于基本类型
         *  == 比较的是变量的值，a和b的值相同
         *
         * 二、对于引用类型的比较
         * 1. == 比较的是两个对象的地址
         * 2. p1.equals(p2) 打印false,其实equals按照p1和p2的地址来比较的，p1和 p2是创建的两个不同的对象，它们栈存放不同的地址，它
         * 们的堆中存放对象的值
         *
         * 默认equals
         * 输出结果是：
         * true
         * false
         * false
         *
         * 在Person中重写equals方法后
         * 输出结果是：
         * true
         * false
         * true
         *
         * 重写后，将比较对象的地址，改成比较对象堆里面的键值对了，所以p1.equals(p2)结果从false变成了true
         * */
        System.out.println(p1 == p2);// false
        System.out.println(p1.equals(p2));// false
        // System.out.println(p1.equals(null));

    }

    // 在测试类中为接口多重继承，定义展示多态特性的函数
    public static void test(IRun irun) {
        // 调用run方法
        irun.run();
    }
}