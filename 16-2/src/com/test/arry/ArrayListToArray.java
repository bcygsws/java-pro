package com.test.arry;

import java.util.ArrayList;

// Inter,new Integer()和int的比较,见链接：https://www.cnblogs.com/gtea/p/13620406.html
// 理解-128的来由：https://blog.csdn.net/u010867670/article/details/88869042
// 负数的补码求补码，首先最高位保持不变，对这个补码再次求补码，就得到该数字的原码
// https://www.cnblogs.com/superlucky/p/9406535.html
public class ArrayListToArray {
	public static void main(String[] args) {
		//ArrayList al=new <String> ArrayList() 尖括号是泛型的意思，表示动态列表中只能存储String类型的的
		ArrayList al = new ArrayList();
		// 向al中添加多个对象
		// new Interger(int)已经被弃用了
		al.add(new Integer(1));
		al.add(new Integer(2));
		al.add(new Integer(3));
		al.add(new Integer(4));
		// 输出ArrayList中的内容
		System.out.println("ArrayList中的内容，al:" + al);// ArrayList中的内容，al:[1, 2, 3, 4]
		// 得到对象数组
		// ArrayList的实例arraylist,通过toArray()方法将实例转化为数组
		Object ia[] = al.toArray();
		// 输出对象数组
		System.out.println("输出对象数组ia:" + ia);
		// 计算数组中的内容
		int sum = 0;
		for (int i = 0; i < al.size(); i++) {
			// java中不支持运算符重载，报错运算符 '+' 不能应用于 'int'、'java.lang.Object'
			// sum += al.get(i);
			// Integer类的常用方法，转化为int类型，Integer.intValue()
			// 方式一：
			// sum += ((Integer) ia[i]).intValue();
			// 方式2：注意parseInt(String s),为静态方法，不是实例，而是Integer直接调用；另外注意其参数是字符串类型
			sum += Integer.parseInt(ia[i].toString());
		}
		//	输出累加和
		System.out.println("累加和sum:" + sum);
		//	测试Integer、 new Integer()、 和int的用法差异
		// Integer io1 = 128;
		// int io2 = 128;
		// Integer io3 = Integer.valueOf(128);
		// Integer io4 = new Integer(128);
		// System.out.println(io1 == io2);// true ,和int比较，Integer,new Integer都会“拆箱”
		// System.out.println(io1 == io3);// false,超出-128——127的范围，Integer会返回return new Integer()
		// System.out.println(io3 == io4);// false,两个new Integer(128)在堆中创建了两个实例，内存地址不同
		// System.out.println(io2 == io4);// 同int比较的拆箱操作

		Integer io1 = 127;
		int io2 = 127;
		Integer io3 = Integer.valueOf(127);
		Integer io4 = new Integer(127);
		System.out.println(io1 == io2);// true ,和int比较，Integer,new Integer都会“拆箱”
		System.out.println(io1 == io3);// true,在-128——127的范围内，Integer.valueOf(127)从缓存中取值，
		// 等价于Integer io3=127;因此返回true
		System.out.println(io3 == io4);// false, Integer io3=127,new Integer(127) 一个在常量池中创建内存，一个在
		// 堆中分配内存地址,肯定是false
		System.out.println(io2 == io4);// 同int比较的拆箱操作

	}

}
// 数据类型强制转换
// 		　　int a = 256 ; //1 0000 0000
// 		　　byte b = a ; //去高位 得到 0000 0000
// 		　　//b=0
// 		　　// a=255时   1111 1111  对于byte来说是负数这是一个负数的补码  ===> 原码 ： 1000 0001   b= -1
// 		　　在java中大数据强转位小范围数据类型：去高位
// 		　　浮点型转为整型：去小数位，再去高位
