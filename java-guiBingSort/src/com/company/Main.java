package com.company;

/**
 * @ 排序方法四：归并排序
 * 归并排序的过程是：采用的是“分治”的思想，将序列分割成许多的半子表，然后对半子表进行排序；将排序好的半子表合并成越来越大的序列
 * 递归和合并(大方向，先上后下，先左后右)
 */
public class Main {

	public static void main(String[] args) {
		// write your code here
		// 定义一个数组arrays
		int[] arrays = {36, 25, 48, 12, 25, 65, 43, 57};
		MergeSort(arrays, 0, arrays.length - 1);
		// 输出排序后的数组
		for (int i : arrays) {
			System.out.print(i + " ");
		}
	}

	// 1.归并排序方法MergeSort()
	public static void MergeSort(int[] arr, int p, int r) {
		// p<r这一判断条件非常重要：限制最小的序列长度至少为1
		if (p < r) {
			// 注意：q是int类型，(p+r)/2这个浮点数会向下取整
			int q = (p + r) / 2;
			// 打印一下p q r的值，观察递归的执行顺序
			System.out.println(p + " " + q + " " + r);
			// 递归的顺序：有点类似二叉树，从根节点开始MergeSort(arr,0,7),一路到MergeSort(arr,0,1)和MergeSort(arr,2,3);再返回到第二层MergeSort(arr,4,7)
			// 大的方向，从根节点开始，先左后右，先上后下，直到不满足p<r的判断条件；然后跳转，从第二层开始，依然先左后右，先上后下
			MergeSort(arr, p, q);
			MergeSort(arr, q + 1, r);
			Merge(arr, p, q, r);
		}
	}

	// 2.合并方法,Merge
	public static void Merge(int[] arr, int p, int q, int r) {
		// n1表示拆分的左序列元素个数，n2表示拆分的右序列元素个数
		int i, j, n1, n2, k;
		// n1左序列元素的个数，n2右序列元素的个数
		n1 = q - p + 1;
		n2 = r - q;
		// 定义两个数组L和R,存储临时元素
		int[] L = new int[n1];
		int[] R = new int[n2];
		// 得到左序列
		for (i = 0, k = p; i < n1; i++, k++) {
			L[i] = arr[k];
		}
		// 得到右序列
		for (j = 0, k = q + 1; j < n2; j++, k++) {
			R[j] = arr[k];
		}
		// 左、右序列元素之间的比较
		for (i = 0, j = 0, k = p; i < n1 && j < n2; k++) {
			if (L[i] > R[j]) {
				arr[k] = R[j];
				j++;
			} else {
				arr[k] = L[i];
				i++;
			}
		}
		// 左序列的补充归并
		if (i < n1) {
			for (j = i; j < n1; j++, k++) {
				arr[k] = L[j];
			}
		}
		// 右序列的补充归并
		if (j < n2) {
			for (i = j; i < n2; i++, k++) {
				arr[k] = R[i];
			}
		}

	}

}
