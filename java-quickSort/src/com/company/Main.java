package com.company;

/**
 * @排序方法五：快速排序 快速排序和归并排序一样，也采用的是“分治”思想；每一趟将当前元素以index为界，切成两个子序列；左边序列的元素全部小于index
 * 而右边序列的元素全部大于index
 */
public class Main {

	public static void main(String[] args) {
		// write your code here
		// 定义一个数组arr
		int[] arr = {38, 65, 97, 76, 13, 27, 49};
		quickSort(arr, 0, arr.length - 1);
		for (int j : arr) {
			System.out.print(j + " ");
		}
	}

	// 快速排序方法quickSort()
	public static void quickSort(int[] a, int l, int h) {
		sorting(a, 0, a.length - 1);
	}

	// 快速排序方法
	public static void sorting(int[] b, int low, int high) {
		int i, j, index;
		// 如果low索引值大于等于 high，程序转向
		if (low >= high) return;
		i = low;
		j = high;
		index = b[i];
		//	大循环，控制i向右移动和j向左移动，以当前这趟的index值为基准；将整个序列切成两个子序列
		// 	index左边的序列元素，全部小于index;反之，index右边的序列元素，全部大于等于index
		while (i < j) {
			while (i < j && b[j] >= index) {
				j--;
			}
			// b[j]<index的情形，需要赋值给索引小的元素b[i++]，以实现正序排列
			if (i < j) {
				b[i++] = b[j];
			}
			while (i < j && b[i] < index) {
				i++;
			}
			//b[i]>=index的情形，此时需要将较大的b[i]赋值给b[j--],以实现正序排列
			if (i < j) {
				b[j--] = b[i];
			}
		}
		b[i] = index;
		//	迭代去掉b[i]这个元素，将原来序列拆分为两个序列
		sorting(b, low, i - 1);
		sorting(b, i + 1, high);
	}
}
