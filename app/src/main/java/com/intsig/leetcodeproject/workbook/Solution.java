package com.intsig.leetcodeproject.workbook;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

/**
 * @author lingzhuang_bu
 * Description:
 * @date 2019/11/6
 */
public class Solution {
    public static String reverseWords(String s) {
        String[] split = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i < split.length - 1) {
                sb.append(new StringBuilder(split[i]).reverse().toString()).append(" ");
            } else {
                sb.append(new StringBuilder(split[i]).reverse().toString());
            }
        }
        return sb.toString();
    }

    public void reverseString(char[] s) {
        swap(0, s.length - 1, s);
    }

    public void swap(int start, int end, char[] s) {
        if (start >= end) {
            return;
        }

        char temp = s[start];
        s[start] = s[end];
        s[end] = temp;
        swap(start + 1, end - 1, s);
    }

    public void swapInt(int start, int end, Integer[] s) {
        if (start >= end) {
            return;
        }

        Integer temp = s[start];
        s[start] = s[end];
        s[end] = temp;
        swapInt(start + 1, end - 1, s);
    }

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }

        public ListNode setNext(ListNode next) {
            this.next = next;
            return this;
        }
    }


    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        int curNum = A[0];
        boolean downhill = false;
        for (int i = 1; i < A.length; i++) {
            int num = A[i];
            if (downhill) {
                //下降
                if (num >= curNum) {
                    return false;
                } else {
                    curNum = num;
                }
            } else {
                //上升
                if (num > curNum) {
                    curNum = num;
                } else if (num == curNum) {
                    return false;
                } else {
                    if (i == 1) {
                        return false;
                    } else {
                        downhill = true;
                        curNum = num;
                    }
                }
            }
        }
        return downhill;
    }

    public boolean validMountainArray2(int[] A) {
        int N = A.length;
        int i = 0;

        // walk up
        while (i + 1 < N && A[i] < A[i + 1]) {
            i++;
        }

        // peak can't be first or last
        if (i == 0 || i == N - 1) {
            return false;
        }

        // walk down
        while (i + 1 < N && A[i] > A[i + 1]) {
            i++;
        }

        return i == N - 1;
    }

    public void getSanJiaoMaxMain() {
        int[][] testData = {{7, 0, 0, 0, 0}, {3, 8, 0, 0, 0}, {8, 1, 0, 0, 0}, {2, 7, 4, 4, 0}, {4, 5, 2, 6, 5}};
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

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curListNode = head;
        while (curListNode != null) {
            ListNode nextTemp = curListNode.next;
            curListNode.next = prev;
            prev = curListNode;
            curListNode = nextTemp;
        }
        return prev;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int maxMoney = 0;
        for (int i = 1; i < prices.length; i++) {
            int temp = prices[i] - prices[i - 1];
            if (temp > 0) {
                maxMoney += temp;
            }
        }
        return maxMoney;
    }

    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int minNum = Integer.MAX_VALUE;
        int maxProduction = 0;
        for (int price : prices) {
            if (price < minNum) {
                minNum = price;
            } else if (price - minNum > maxProduction) {
                maxProduction = price - minNum;
            }
        }
        return maxProduction;
    }

    public int maxSubArray(int[] nums) {
        int maxNum = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            maxNum = Math.max(sum, maxNum);
        }
        return maxNum;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    class MinStack {

        // 数据栈
        private Stack<Integer> data;
        // 辅助栈
        private Stack<Integer> helper;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            data = new Stack<>();
            helper = new Stack<>();
        }

        public void push(int x) {
            data.add(x);
            if (helper.isEmpty() || helper.peek() >= x) {
                helper.add(x);
            } else {
                helper.add(helper.peek());
            }
        }

        public void pop() {
            if (data.isEmpty() || helper.isEmpty()) {
                throw new RuntimeException();
            } else {
                data.pop();
                helper.pop();
            }
        }

        public int top() {
            if (data.isEmpty()) {
                throw new RuntimeException();
            } else {
                return data.peek();
            }
        }

        public int getMin() {
            if (helper.isEmpty()) {
                throw new RuntimeException();
            } else {
                return helper.peek();
            }
        }
    }

    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            if (counts.containsKey(num)) {
                counts.put(num, counts.get(num) + 1);
            } else {
                counts.put(num, 1);
            }
        }
        return counts;
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 0) {
            if ((n & 1) != 0) {
                if (n == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            n = n >> 1;
        }
        return true;
    }

    public static String longestCommonPrefix(String[] strs) {
        String prefix = null;
        for (String str : strs) {
            if (prefix == null) {
                prefix = str;
            }
            int min = Math.min(str.length(), prefix.length());
            prefix = prefix.substring(0, min);
            for (int i = 0; i <= min - 1; i++) {
                char c = str.charAt(i);
                char c1 = prefix.charAt(i);
                if (c != c1) {
                    if (i > 0) {
                        prefix = prefix.substring(0, i);
                        break;
                    } else {
                        return "";
                    }
                }
            }
        }
        if (prefix == null) {
            return "";
        } else {
            return prefix;
        }
    }

    /**
     * 一个一个排序，读错了题意
     */
    public static ListNode mergeTwoListsErr(ListNode l1, ListNode l2) {
        if (l1 != null && l2 != null) {
            if (l1.next == null && l2.next == null) {
                l1.next = l2;
                return l1;
            } else {
                ListNode child = mergeTwoListsErr(l1.next, l2.next);
                l1.next = l2;
                l2.next = child;
                return l1;
            }
        } else {
            if (l1 == null) {
                return l2;
            } else {
                return l1;
            }
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else if (l1.val == l2.val) {
                l2.next = mergeTwoLists(l1.next, l2.next);
                l1.next = l2;
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
        if (l1 == null) {
            return l2;
        } else {
            return l1;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return false;
        }
        HashSet<Integer> integers = new HashSet<>();
        for (int num : nums) {
            if (integers.contains(num)) {
                return true;
            } else {
                integers.add(num);
            }
        }
        return false;
    }

    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int total1 = 1, total2 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = total1 + total2;
            total1 = total2;
            total2 = temp;
        }
        return total2;
    }


    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        } else if (s.length() == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            switch (charAt) {
                case '(':
                case '[':
                case '{':
                    stack.push(charAt);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        if (stack.peek().equals('(')) {
                            stack.pop();
                        } else {
                            return false;
                        }
                    }
                    break;
                case ']':
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        if (stack.peek().equals('[')) {
                            stack.pop();
                        } else {
                            return false;
                        }
                    }
                    break;
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        if (stack.peek().equals('{')) {
                            stack.pop();
                        } else {
                            return false;
                        }
                    }
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public int removeDuplicates(int[] nums) {
        int repeatCount = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                ++repeatCount;
            } else {
                //后面数字大于前面数字
                nums[i + 1 - repeatCount] = nums[i + 1];
            }
        }
        return nums.length - repeatCount;
    }

    public int singleNumber(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[0] = nums[0] ^ nums[i];
        }
        return nums[0];
    }

    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int temp = 0;
        while (true) {
            temp = temp * 10 + x % 10;
            x = x / 10;
            if (temp > x) {
                return temp / 10 == x;
            } else if (temp == x) {
                return true;
            }
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int spaceCount = nums1.length - m - n;
        for (int j = nums1.length - spaceCount; j >= 0; j--) {

        }
    }
}
