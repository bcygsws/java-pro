package com.company.cx;

// 排序方法一：选择法排序
public class Main {

    public static void main(String[] args) {
        // write your code here
        // 输入一个数组
        int a[] = {12, 566, 77, 3562, 761, 345, 9};
        // 调用排序方法Sort
        sort(a);
        // 按顺序输出排序后的数组
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    static void sort(int[] arr) {
        // 定义一个索引变量flag和临时存储变量temp
        int flag = 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            flag = i;
            temp = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (temp > arr[j]) {
                    // flag为最小值所在索引
                    // temp存储此时的arr[j]值
                    flag = j;
                    temp = arr[j];
                }
            }
            if (flag != i) {
                arr[flag] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
