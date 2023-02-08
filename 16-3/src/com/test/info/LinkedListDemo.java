package com.test.info;

import java.util.LinkedList;

/*数组和链表的区别和联系
https://blog.csdn.net/m0_37631322/article/details/81777855
ArrayList和LinkedList的区别和联系
http://blog.itpub.net/31543790/viewspace-2564119/
Array和Linked一个是数组数据结构、一个是链表数据接口，它们都是对List接口的实现
1.当随机访问列表时（get和set），ArrayList比LinkedList效率更高
2.当进行添加或删除列表操作时，LinkedList比Arraylist效率高
3.ArrayList自由性较低，使用时需要设置固定大小的容量，使用很方便；LinkedList自由性高，使用不便
4.ArrayList的控件消耗主要在预留一定的存储空间上；Linkedlist的控件消耗主要是在存储结点和结点指针上
*/
public class LinkedListDemo {
	public static void main(String[] args) {
		//	创建LinkedList对象
		LinkedList ll = new LinkedList();
		//	向链接列表中加入元素
		ll.add("F");
		ll.add("B");
		ll.add("D");
		ll.add("E");
		ll.add("C");
		// 在链表的最后一个位置添加元素 Z
		ll.addLast("Z");
		// 在链表的第一个位置添加元素A
		ll.addFirst("A");
		// 在链表的第二个位置(索引为1,索引从0开始)加入元素A2
		ll.add(1, "A2");
		System.out.println("输出此时的链接列表：" + ll);// 输出此时的链接列表：[A, A2, F, B, D, E, C, Z]
		//	从链接列表ll中移除元素，依然是两种方式：直接移除元素，另外根据索引来移除元素
		ll.remove("F");
		ll.remove(2);
		// 输出此时的链接列表：
		System.out.println("删除操作后的链接列表:" + ll);
		// 移除第一个和最后一个元素
		ll.removeFirst();
		ll.removeLast();
		//	输出此时的链接列表
		System.out.println("再次删除后的链接列表：" + ll);
		// 取值并设定值
		Object val = ll.get(2);
		// 向下转型，val声明了Object,顶级父类
		ll.set(2, (String) val + "Changed");
		// 打印改变后的链接列表
		System.out.println("改变后的链接列表：" + ll);
	}
}
