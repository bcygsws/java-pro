package com.company.faf;

public class Main {

    public static void main(String[] args) {
        // write your code here
        // 调用插入排序函数insertedSorting
        int arr[] = {38, 65, 97, 76, 13, 27, 49};
        insertedSorting(arr);
        // 输出这个数组
        for (int j = 0; j < arr.length; j++) {
            System.out.println(arr[j]);
        }
    }


    // 排序方法二：插入排序
    static void insertedSorting(int a[]) {
        // 1.判别不是空数组
        if (a != null) {
            // 2.外层循环
            for (int i = 1; i < a.length; i++) {
                int temp = a[i], j = i;
                // if选择语句，筛选的含义，避免无效的循环
                if (a[j - 1] > temp) {
                    // 内部while循环
                    while (j >= 1 && a[j - 1] > temp) {
                        a[j] = a[j - 1];
                        j--;
                    }
                }
                // 3.假定的最小的那个元素temp，赋给移动的j，以完成本轮外循环
                a[j] = temp;
            }
        }
    }

}
