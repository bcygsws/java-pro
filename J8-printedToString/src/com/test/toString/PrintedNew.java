package com.test.toString;

public class PrintedNew {
	// 定义静态成员标量i
	static int i = 6;

	public static void main(String[] args) {
		// 场景：println打印new的实例对象
		System.out.println("love\t" + new PrintedNew());
		PrintedNew pn = new PrintedNew();
		// pn.i++;
		// System.out.println("输出i的值：" + pn.i);// 7
		// 因为是静态变量,main方法直接调用测试
		i++;
		System.out.println("不实例化对象，直接输出i的值：" + i);
	}

	public String toString() {
		//	Object有toString()方法，所有类都是Object类的子类，自定义的PrintedNew类当然是Object的子类，相当于
		// 	重写了在子类中重写了toString()方法
		System.out.print("I\t");
		return "Java!";
	}
//	重要说明1：toString()方法是Object的方法，用于返回对象的字符串形式
//	例如 打印语句：Object obj1=new Object();System.out.println(obj1.toString())
//	打印结果：java.lang.Object@d716361
}
