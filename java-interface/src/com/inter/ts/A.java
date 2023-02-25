package com.inter.ts;

/**
 * java-interface
 *
 * @author baochengyi
 * @date 2023/2/25 2:32
 * @description
 */
interface IShape {
    public static final int a = 10;
    int b = 15;

    // 3.接口中的静态方法【不能】重写
    public static void sleep() {
        System.out.println("接口中的静态方法！");
    }

    // 1.接口中的成员方法默认修饰时public abstract，哪怕不写public abstract关键字；在接口实现类中，抽象方法【必须】重写
    public abstract void draw();

    // 2.jdk1.8后成员方法可以在接口中写实现，但是必须有default修饰（这是默认方法），默认方法在接口实现类中【可以不】重写
    default public void eat() {
        System.out.println("接口中的默认方法！");
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

/*
 * 抽象类和接口
 * 参考文档：
 * https://blog.csdn.net/qq_62712350/article/details/126319408
 *
 *
 *
 * */
// 测试类
class Test {
    public static void main(String[] args) {
        StringTable st = new StringTable();
        st.chooseStr(new A());
        st.chooseStr(new B());

    }
}