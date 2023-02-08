package com.test.classCollection;

import java.util.*;

public class ArrayListDemo {
	public static void main(String[] args) {
		ArrayList al = new ArrayList();
		System.out.println("al初始大小:" + al.size());
		al.add("C");
		al.add("A");
		al.add("E");
		al.add("B");
		al.add("D");
		al.add("F");
		System.out.println("添加元素后al的大小：" + al.size());
		// 把A2加到al的第二个位置(index为1)
		al.add(1, "A2");
		// A2加入后元素增多，动态数组的的长度
		System.out.println("添加A2后动态数组al的长度为：" + al.size());
		System.out.println("输出即时的al对象：" + al);// [C, A2, A, E, B, D, F]
		// 删除F和索引为2的元素，注意：remove()方法可以两用，参数可以是要删除的元素，也可以是待删除
		// 元素的索引
		al.remove("F");
		al.remove(2);
		//	删除后元素的长度，以及元输出元素列表
		System.out.println("删除F和索引为2的元素后动态数组的长度：" + al.size());
		System.out.println("输出al数组：" + al);


	}
}
