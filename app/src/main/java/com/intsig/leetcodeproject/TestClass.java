package com.intsig.leetcodeproject;

import com.intsig.leetcodeproject.workbook.Solution;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Source;

/**
 * @author lingzhuang_bu
 * Description:
 * @date 2019/11/6
 */
public class TestClass {
    public static void main(String[] args) {
//        Solution solution = new Solution();
//        solution.getSanJiaoMaxMain();
//        TestClass testClass = new TestClass();
//        int reverse = testClass.reverse(1534236469);
//        int reverse2 = testClass.reverse2(1534236469);
//        System.out.println("反转后 = " + reverse2);
//        String s = Solution.longestCommonPrefix(new String[]{"aa", "a"});
//        Solution.mergeTwoLists(new Solution.ListNode(2),new Solution.ListNode(1));
//        Solution.ListNode listNodeA = new Solution.ListNode(1).setNext(new Solution.ListNode(2).setNext(new Solution.ListNode(3)));
//        Solution.ListNode listNodeB = new Solution.ListNode(4).setNext(new Solution.ListNode(5));
//        Solution.ListNode intersectionNode = Solution.getIntersectionNode(listNodeA, listNodeB);
//        int i = Solution.climbStairs(3);
//        int[] iii = {4, 5};
//        test(iii);
//        System.out.println(iii.length);
        Map<Integer, Long> map = new HashMap<>();
        for (Integer key : map.keySet()) {
            System.out.println("key = " + key + " value = " + map.get(key));
        }
    }

    private static void test(int[] iii) {
        iii = new int[]{1, 2, 3};
    }

    /**
     * {0, 0, 0, 0, 0},
     * {0, 1, 0, 0, 0},
     * {0, 1, 0, 0, 0},
     * {0, 0, 1, 1, 0},
     * {0, 0, 0, 0, 0}
     */
    public void getSanJiaoMaxMain() {
        int[][] testData = {{0, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 1, 0}, {0, 0, 0, 0, 0}};
        getSanJiaoMax(testData);
    }

    public void getSanJiaoMax(int[][] data) {
        int[][] countData = new int[data.length][data.length];
        for (int i = 0; i < countData.length; i++) {
            int[] countDatum = countData[i];
            for (int j = 0; j < countDatum.length; j++) {
                countData[i][j] = -1;
            }
        }
        int count = getCount(data, countData, data.length - 1, 0, 0);
        System.out.println("最大值 = " + count);
    }

    public int getCount(int[][] data, int[][] countData, int maxLine, int i, int j) {
        if (countData[i][j] != -1) {
            System.out.println("返回当前最大值 = " + countData[i][j]);
            return countData[i][j];
        }
        if (i + 1 == maxLine) {
            countData[i][j] = Math.max(data[i + 1][j], data[i + 1][j + 1]) + data[i][j];
        } else {
            int xia = getCount(data, countData, maxLine, i + 1, j);
            int youXia = getCount(data, countData, maxLine, i + 1, j + 1);
            countData[i][j] = Math.max(xia, youXia) + data[i][j];
        }
        System.out.println("行数 = " + i + " 列数 = " + j + " countData = " + countData[i][j]);

        return countData[i][j];
    }

    public int reverse(int x) {
        //2147483647,-2^31=-2147483648
        //1534236469
        int c;
        long result = 0;
        int remainder;
        boolean isMinus = false;
        if (x < 0) {
            isMinus = true;
            x = Math.abs(x);
        }
        while ((c = x / 10) > 0 | (remainder = x % 10) > 0) {
            result = result * 10 + remainder;
            x = c;
        }
        if ((result < Integer.MIN_VALUE || result > Integer.MAX_VALUE)) {
            return 0;
        }
        if (isMinus) {
            return (int) -result;
        } else {
            return (int) result;
        }
    }

    public int reverse2(int x) {
        long rs = 0;
        while (x != 0) {
            rs = rs * 10 + x % 10;
            x /= 10;
        }
        int i = (rs < Integer.MIN_VALUE || rs > Integer.MAX_VALUE) ? 0 : (int) rs;
        return i;
    }
}
