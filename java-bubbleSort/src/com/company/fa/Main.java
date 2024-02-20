package com.company.fa;

/**
 * 排序方式三：冒泡排序
 * 两种方式：
 * 第一趟把最小的数浮起来
 * 或第一趟把最大的数沉底
 */

public class Main {

	public static void main(String[] args) {
		// write your code here
		// 待排序的数组arr
		int[] arr = {38, 65, 97, 76, 13, 27, 49};
		bubbleSort(arr);
		// 输出数组中的元素
		// for语句改为增强的for循环
		for (int j : arr) {
			System.out.print(j + " ");
		}
	}

	// 排序方法三：冒泡排序
	static void bubbleSort(int[] a) {
		int len = a.length;
		int temp;
		for (int i = 0; i < len - 1; i++) {
			// 内循环总是比较两个相邻元素
			// 3.1 从后向前开始相邻元素比较---此种方式先排定了一个最小数；第一趟把最小的数浮起来
			// for (int j = len - 1; j > i; --j) {
			//     if (a[j] < a[j - 1]) {
			//         temp = a[j - 1];
			//         a[j - 1] = a[j];
			//         a[j] = temp;
			//     }
			// }

			// 3.2 从前向后，开始相邻元素比较---此种方式先排定了一个最大数；第一趟把最大的数沉底
			for (int j = 0; j < len - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}
}
/*
 * @排序三
 * 冒泡排序的评价：
 * 1.由于冒泡排序的性能较差，不建议在大型数据集上使用
 * 2.java jdk 提供了Arrays.sort() 或数据集排序Collections.sort()的排序方法
 * 参考文档：
 * https://cloud.tencent.com/developer/article/2332937
 *
 */
