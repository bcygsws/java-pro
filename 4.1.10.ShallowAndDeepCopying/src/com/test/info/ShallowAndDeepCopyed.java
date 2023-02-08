package com.test.info;

import java.util.Date;

// 1.对象的深浅复制代码类似，只是深复制要将对象所在类中的引用类型的成员也要调用一下clone()方法
// 2.区别：浅复制,只是复制了值,而所有对其他对象的引用仍然指向原对象；深复制则更进一步，把复制对象所引用的对象
// 也进了复制
/*
 *
 * 总结对象浅（深）复制的步骤
 * 1.实现复制（clone）的类,需要实现cloneable接口
 * 2.重写clone方法（浅复制重写就可以了，深复制中类中的引用类型的成员也要调用一下clone方法）
 *
 * */
class Obj implements Cloneable {
	private Date birth = new Date();

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public void changedDate() {
		this.birth.setMonth(4);
	}
	// 覆盖（或者方法重写）的要求：四同 一不低，同包不同包
	// 四同：函数名、参数列表、返回值类型（Java 5要求必须相同，Java 7以上可以不同，但是要求必须是父类返回值
	// 的派生类）以及基类和派生类的异常类型
	// 一不低 子类重写方法的访问权限要不低于（ 高于或者等于）父类中该方法的权限
	// 子类和父类在同一包中，子类可以重写父类的所有方法，但是要排除声明为private和final的方法
	// 子类和父类不在同一包中（原因是：不同包了，子类想访问，必须是public或者protected了，否则无法访问其他包中的类
	// ）,那么子类只能重写父类中声明为public和protected的方法，父类是final方法除外

	public Object clone() {
		Obj o = null;
		try {
			o = (Obj) super.clone();
		} catch (CloneNotSupportedException e) {
			// stack 堆栈 Trace描述，轨迹
			// 异常对象的一个方法printStackTrace（）其含义是在命令行打印异常信息在程序中出现的位置和原因
			e.printStackTrace();
		}
		// 引用类型的成员变量存在，也需要进行调用clone方法
		// 注意：不能写成o.birth=(Date)this.birth.clone();
		// this.birth把值写死了，而使用this.getBirth()可以动态跟随birth值的变化
		o.birth = (Date) this.getBirth().clone();
		return o;
	}

}

public class ShallowAndDeepCopyed {
	// psvm+Enter键，输入main方法
	public static void main(String[] args) {
		// 实例化Obj的对象a
		Obj a = new Obj();
		Obj b = (Obj) a.clone();
		System.out.println("输出原来对象a中的时间：" + a.getBirth());
		//	更改b日期，观测会不会影响a?
		b.changedDate();
		System.out.println("输出深复制后的对象b的时间：" + b.getBirth());
	}
}
// 重写规则：https://www.runoob.com/java/java-override-overload.html
/* 方法的重写规则
1.  参数列表与被重写方法的参数列表必须完全相同。

2.  返回类型与被重写方法的返回类型可以不相同，但是必须是父类返回值的派生类（java5 及更早版本返回类型要一样，
java7 及更高版本可以不同）。

3.  访问权限不能比父类中被重写的方法的访问权限更低。例如：如果父类的一个方法被声明为 public，那么在子类中重写
该方法就不能声明为 protected。

4.  父类的成员方法只能被它的子类重写。

5。  声明为 final 的方法不能被重写。

6.  声明为 static 的方法不能被重写，但是能够被再次声明。

7.  子类和父类在同一个包中，那么子类可以重写父类所有方法，除了声明为 private 和 final 的方法。

8.  子类和父类不在同一个包中，那么子类只能够重写父类的声明为 public 和 protected 的非 final 方法。

9. 重写的方法能够抛出任何非强制异常，无论被重写的方法是否抛出异常。但是，重写的方法不能抛出新的强制性异常，
或者比被重写方法声明的更广泛的强制性异常（类似返回值类型，范围要比父类中的强制性异常范围小），反之则可以。

10. 构造方法不能被重写。

如果不能继承一个类，则不能重写该类的方法。
*/
