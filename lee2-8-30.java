//233
//617. Merge Two Binary Trees
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {//尽可能地使用本函数而不要另外建立辅助函数
        int val = 0;
        if(t1 == null && t2 == null){
            return null;
        }else if(t1 == null){
            return t2;
        }else if(t2 == null){
            return t1;
        }else{
            val = t1.val + t2.val;
        }
        TreeNode res = new TreeNode(val);
        //如果不使用res.left，则变量结果无法回传
        res.left = mergeTrees(t1.left, t2.left);//t1.left, t2.left是否可能为空？
        res.right = mergeTrees(t1.right, t2.right);
        return res;
    }
}


//461. Hamming Distance
//常规方法
public class Solution {
    public int hammingDistance(int x, int y) {
        int res = 0;
        while(x != 0 || y != 0){
            if(x % 2 != y % 2){
                res++;
            }
            x /=2;
            y /=2;
        }
        return res;
    }
}
//sp
public class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);//累计二进制中1的个数
    }
}


//258. Add Digits不使用循环和递归，找规律，每九个一循环
public class Solution {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}

//202. Happy Number
public class Solution {
    public boolean isHappy(int n) {
        Set<Integer> had = new HashSet<>();
        while(n != 1){
            if(had.contains(n)){
                return false;
            }else{
                had.add(n);
            }
            int temp = n;
            int sum = 0;
            while(temp > 0){
                sum += Math.pow(temp % 10, 2);
                temp /= 10;
            }
            n = sum;
        }
        return true;
    }
}


//263. Ugly Number
public class Solution {
    public boolean isUgly(int num) {
        if(num <= 0){
            return false;
        }else if(num == 1){
            return true;
        }else{
            while(num != 1){//注意此处为1，而不是0
                if(num % 2 == 0){
                    num /= 2;
                }else if(num % 3 == 0){
                    num /= 3;
                }else if(num % 5 == 0){
                    num /= 5;
                }else{
                    return false;
                }
            }
            return true;
        }
    }
}

//264. Ugly Number II 第n个数； 如果为小于100有多少个，则依然用此法，当出现第一个大于100的数时 结束循环。
//此法时间复杂度过大，超时，通常使用子数组遍历测试的都过于复杂，不要使用
public class Solution {
    Set<Integer> sta = new HashSet<>();
    public int nthUglyNumber(int n) {
        int sum = 0;
        int i = 0;
        for(; sum != n; i++){
            if(isUgly(i)){
                sum++;
            }
        }
        return i - 1;
    }
    public boolean isUgly(int num) {
        if(num <= 0){
            return false;
        }else if(num == 1){
            return true;
        }else{
            while(num != 1){
                if(num % 2 == 0){
                    num /= 2;
                    if(sta.contains(num)) return true;
                }else if(num % 3 == 0){
                    num /= 3;
                    if(sta.contains(num)) return true;
                }else if(num % 5 == 0){
                    num /= 5;
                    if(sta.contains(num)) return true;
                }else{
                    return false;
                }
            }
            sta.add(num);
            return true;
        }
    }
}
//YES
public class Solution {
    public int nthUglyNumber(int n) {
        int k2 = 0;
        int k3 = 0;
        int k5 = 0;
        int[] res = new int[n];
        res[0] = 1;
        for(int i = 1; i < n; i++){
            res[i] = Math.min(res[k2] * 2, Math.min(res[k3] * 3, res[k5] * 5));
            if(res[i] == res[k2] * 2){
                k2++;
            }
            if(res[i] == res[k3] * 3){
                k3++;
            }   
            if(res[i] == res[k5] * 5){
                k5++;
            }
        }
        return res[n - 1];
    }
}


//204. Count Primes
//Count the number of prime numbers less than a non-negative number, n. 小于n的有多少个质数
public class Solution {
    public int countPrimes(int n) {
        boolean[] comp = new boolean[n];
        int sum = 0;
        for(int i = 2; i < n; i++){
            if(comp[i] == false){
                sum++;
            }
            for(int j = 2; j * i < n; j++){
                comp[i * j] = true;
            }
        }
        return sum;
    }
}

//507. Perfect Number
//my
public class Solution {
    public boolean checkPerfectNumber(int num) {
        if(num <= 1) return false;
        int sum = 1;
        int len =  num / 2;
        for(int i = 2; i < len; i++){
            if(num % i == 0){
                sum += i;
                int temp = num / i;
                sum += temp;
                len = temp;
            }
        }
        return sum == num;
    }
}
//batter
public class Solution {
    public boolean checkPerfectNumber(int num) {
        if(num <= 1) return false;
        int sum = 1;
        for(int i = 2; i < Math.sqrt(num); i++){//更改了循环结束条件
            if(num % i == 0){
                sum += i;
                int temp = num / i;
                sum += temp;
            }
        }
        return sum == num;
    }
}

//9. Palindrome Number
//Determine whether an integer is a palindrome. Do this without extra space.
public class Solution {//前提是不使用额外空间
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        int s = 0;
        int y = x;
        while(x > 0){//求全部的反再比较，比较简单，但是时间稍长
            s = s * 10 + x % 10;
            x /= 10;
        }
        return s == y;
    }
}

//234. Palindrome Linked List
//Given a singly linked list, determine if it is a palindrome.
//时间复杂度O(n)，空间复杂度O(n)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        Stack<ListNode> sta = new Stack<>();
        Queue<ListNode> sta2 = new LinkedList<>();
        while(head != null){
            sta.push(head);
            sta2.add(head);
            head = head.next;
        }
        while(!sta.isEmpty()){
            if(sta2.poll().val != sta.pop().val){
                return false;
            }
        }
        return true;
    }
}

//66. Plus One
//Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
public class Solution {
    public int[] plusOne(int[] digits) {
        int s = 1;
        int len = digits.length;
        for(int i = len - 1; i >= 0; i--){
            int temp = digits[i] + s;
            digits[i] = temp % 10;
            s = temp / 10;
        }
        if(s != 0){
            int[] res = new int[len + 1];
            res[0] = s;
            for(int i = 0; i < len; i++){
                res[i + 1] = digits[i];
            }
            return res;
        }
        return digits;
    }
}

//67. Add Binary
//Given two binary strings, return their sum (also a binary string).
public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder ra = new StringBuilder(a);
        ra = ra.reverse();
        StringBuilder rb = new StringBuilder(b);
        rb = rb.reverse();
        StringBuilder res = new StringBuilder();
        int lena = a.length();
        int lenb = b.length();
        int s = 0;
        int i = 0;
        while(i < lena && i < lenb){
            int temp = ra.charAt(i) - '0' + rb.charAt(i) - '0' + s;
            res.append(temp % 2);
            s = temp / 2;
            i++;
        }
        while(i < lena){
            int temp = ra.charAt(i) - '0' + s;
            res.append(temp % 2);
            s = temp / 2;
            i++;
        }  
        while(i < lenb){
            int temp = rb.charAt(i) - '0' + s;
            res.append(temp % 2);
            s = temp / 2;
            i++;
        } 
        if(s != 0){
            res.append(s);
        }
        return res.reverse().toString();
    }
}


//2. Add Two Numbers
//You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode f = res;//注意此处保存链表首，便于返回
        int s = 0;
        while(l1 != null && l2 != null){
            int temp = l1.val + l2.val + s;
            res.next = new ListNode(temp % 10);
            s = temp / 10;
            res = res.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
            int temp = l1.val + s;
            res.next = new ListNode(temp % 10);
            s = temp / 10;
            res = res.next;
            l1 = l1.next;            
        }
        while(l2 != null){
            int temp = l2.val + s;
            res.next = new ListNode(temp % 10);
            s = temp / 10;
            res = res.next;
            l2 = l2.next;            
        }
        if(s != 0){
            res.next = new ListNode(s);
        }
        return f.next;
    }
}

//415. Add Strings 与67题方法完全一样，只是进制不一样
public class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder ra = new StringBuilder(num1);
        ra = ra.reverse();
        StringBuilder rb = new StringBuilder(num2);
        rb = rb.reverse();
        StringBuilder res = new StringBuilder();
        int lena = num1.length();
        int lenb = num2.length();
        int s = 0;
        int i = 0;
        while(i < lena && i < lenb){
            int temp = ra.charAt(i) - '0' + rb.charAt(i) - '0' + s;
            res.append(temp % 10);
            s = temp / 10;
            i++;
        }
        while(i < lena){
            int temp = ra.charAt(i) - '0' + s;
            res.append(temp % 10);
            s = temp / 10;
            i++;
        }  
        while(i < lenb){
            int temp = rb.charAt(i) - '0' + s;
            res.append(temp % 10);
            s = temp / 10;
            i++;
        } 
        if(s != 0){
            res.append(s);
        }
        return res.reverse().toString();
    }
}


//153. Find Minimum in Rotated Sorted Array 
//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//关于二分搜索 http://www.cppblog.com/converse/archive/2009/10/05/97905.html
//注意边界条件和可能的溢出
//解法1 双闭区间
public class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;//[]双闭区间
        while(left <= right){//left == right 是合法的
            int mid = left + (right - left) / 2;//防止溢出
            if(nums[left] > nums[mid]){
                right = mid;//根据实际情况选择是否加1，right是可以取到的，mid可能是最小值，所以right=mid
            }else if(nums[right] < nums[mid]){
                left = mid + 1;//根据实际情况判断边界条件
            }else{
                return nums[left];
            }
        }
        return nums[left];
    }
}
//解法二 双开区间
public class Solution {
    public int findMin(int[] nums) {
        int left = -1;//双开区间
        int right = nums.length;
        while(left + 1 <= right - 1){
            int mid = left + (right - left) / 2;
            if(nums[left + 1] > nums[mid]){//注意不能越界
                right = mid + 1;//边界条件按规律和实际情况判断
            }else if(nums[right - 1] < nums[mid]){
                left = mid;
            }else{
                return nums[left + 1];
            }
        }
        return nums[left + 1];
    }
}

//33. Search in Rotated Sorted Array
//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
public class Solution {
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        int min = 0;
        int sta = 0;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[left] > nums[mid]){
                right = mid;
            }else if(nums[right] < nums[mid]){
                left = mid + 1;
            }else{
                break;
            }
        }
        min = nums[left];//首先找到最小值或者最大值分成两部分，再在左或右寻找
        sta = left;
        if(target >= min && target <= nums[nums.length - 1]){
            left = sta;
            right = nums.length - 1;
        }else{
            left = 0;
            right = sta - 1;
        }
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}


//81. Search in Rotated Sorted Array II
public class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){//开闭区间规则依然使用
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){//以mid值和target相比较来划分条件
                if(nums[right] > nums[mid]){//通过短点分析来进一步判断是在哪个区间
                    right = mid - 1;
                }else if(nums[right] == nums[mid]){//对于存在重复的数字，如果端点相等，则去除端点值
                    right--;
                }else{
                    if(nums[left] <= target){
                        right = mid - 1;
                    }else{
                        left = mid + 1;
                    }
                }
            }else if(nums[mid] < target){//两种情况互为镜像
                if(nums[left] < nums[mid]){
                    left = mid + 1;
                }else if(nums[left] == nums[mid]){
                    left++;
                }else{
                    if(nums[right] >= target){
                        left = mid + 1;
                    }else{
                        right = mid - 1;
                    }
                }
            }else{
                return true;
            }
        }
        return false;
    }
}


//367. Valid Perfect Square
//Given a positive integer num, write a function which returns True if num is a perfect square else False.
//二分搜索法
public class Solution {
    public boolean isPerfectSquare(int num) {
        long left = 0;
        long right = num;
        while(left <= right){
            long mid = left + (right - left) / 2;//注意此处的数据类型为long，不然会发生溢出，导致错误
            if(mid * mid > num){
                right = mid - 1;
            }else if(mid * mid < num){
                left = mid + 1;
            }else{
                return true;
            }
        }
        return false;
    }
}

//牛顿法
public class Solution {
    public boolean isPerfectSquare(int num) {
        long x = num;//注意此处的数据类型为long
        while(x * x > num){
            x = (x + num / x) / 2;
        }
        return x * x == num;
    }
}


//69. Sqrt(x)  ##对于求开方的题目，首选牛顿法
//Implement int sqrt(int x).不能刚好为整数的，需要舍入
//牛顿法
public class Solution {
    public int mySqrt(int x) {
        long num = x;
        while(num * num > x){
            num = (num + x / num) / 2;
        }
        return (int)num;
    }
}

//二分搜索法
public class Solution {
    public int mySqrt(int x) {
        long left = 0;
        long right = x;
        while(left <= right){
            long mid = left + (right - left) / 2;
            if(mid * mid > x){
                right = mid - 1;
            }else if(mid * mid < x){
                left = mid + 1;
            }else{
                return (int)mid;
            }
        }
        long res = left + (right - left) / 2 - 1;//注意此处要减一
        return (int)res;
    }
} 


//50. Pow(x, n)
//Implement pow(x, n).
//递归方法 超时
public class Solution {
    public double myPow(double x, int n) {
        if(n == 0){
            return 1.0;
        }else if(n < 0){
            return 1 / (myPow(x, -(n + 1)) * x);
        }else{
            return x * myPow(x, n - 1);
        }
    }
}

//二分法 加 递归
public class Solution {
    public double myPow(double x, int n) {
        if(n == 0){
            return 1.0;
        }else if(n < 0){
            return 1 / (myPow(x, -(n + 1)) * x);//Integer.MIN_VALUE 不可以直接取负值，会发生溢出
        }else{
            double half = myPow(x, n / 2);//以x*x为单元，进行累乘，计算为log(n)
            return n % 2 == 0 ? half * half : x * half * half;//奇偶返回值相差一次相乘
        }
    }
}


//167. Two Sum II - Input array is sorted
//Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new  int[2];
        int left = 0;
        int right = numbers.length - 1;
        while(left < right){//用两个指针分别向中间移动
            int sum = numbers[left] + numbers[right];
            if(sum < target){
                left++;
            }else if(sum > target){
                right--;
            }else{
                res[0] = left + 1;
                res[1] = right + 1;
                return res;
            }
        }
        return res;
    }
}


//1. Two Sum
//Given an array of integers, return indices of the two numbers such that they add up to a specific target.
//You may assume that each input would have exactly one solution, and you may not use the same element twice.
public class Solution {//遍历枚举 O(n^2)
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        int[] res = new int[2];
        for(int i = 0; i < len; i++){
            for(int j = 1; j + i < len; j++){
                if(nums[i] + nums[i + j] == target){
                    res[0] = i;
                    res[1] = i + j;
                    return res;
                }
            }
        }
        return res;
    }
}

//使用HashMap数据结构，简化计算
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new  int[2];
        Map<Integer, Integer> index = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(index.containsKey(target - nums[i])){
                res[0] = index.get(target - nums[i]);
                res[1] = i;
                return res;
            }else{
                index.put(nums[i], i);
            }
        }
        return res;

    }
}



//35. Search Insert Position
//Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
public class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;//左闭右开区间
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return left;
    }
}

public class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;//左闭右闭
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return left;
    }
}


//374. Guess Number Higher or Lower
/* The guess API is defined in the parent class GuessGame.
@param num, your guess
@return -1 if my number is lower, 1 if my number is higher, otherwise return 0
   int guess(int num); */

public class Solution extends GuessGame {
 public int guessNumber(int n) {
     int left = 1;
     int right = n;
     while(left <= right){
         int mid = left + (right - left) / 2;
         if(guess(mid) == -1){
             right = mid - 1;
         }else if(guess(mid) == 1){
             left = mid + 1;
         }else{
             return mid;
         }
     }
     return left;
 }
}


//278. First Bad Version
/* The isBadVersion API is defined in the parent class VersionControl.
boolean isBadVersion(int version); */

public class Solution extends VersionControl {
public int firstBadVersion(int n) {
  int left = 1;
  int right = n;
  while(left <= right){
      int mid = left + (right - left) / 2;
      if(isBadVersion(mid)){
          right = mid - 1;
          
      }else{
          left = mid + 1;
      }
  }
  return left;
}
}


//34. Search for a Range
//Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
//Your algorithm's runtime complexity must be in the order of O(log n).
public class Solution {//两个二分搜索
    public int[] searchRange(int[] nums, int target) {
        
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if(nums.length == 0) return res;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }
        }
        if(left < nums.length && nums[left] == target){//寻址一个索引时必须先判断索引是否有效
            res[0] = left;
        }else{
            return res;
        }
        
        left = 0;
        right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] <= target){
                left = mid + 1;
            }
        }
        res[1] = right;
        return res;
        
        
    }
}



//349. Intersection of Two Arrays
//Given two arrays, write a function to compute their intersection.
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> had = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for(int i = 0; i < nums1.length; i++){
            had.add(nums1[i]);
        }
        for(int i = 0; i < nums2.length; i++){
            if(had.contains(nums2[i]) && !res.contains(nums2[i])){
                res.add(nums2[i]);
            }
        }
        int[] res2 = new int[res.size()];
        int i = 0;
        for(int c : res){
            res2[i++] = c;
        }
        return res2;
    }
}


//350. Intersection of Two Arrays II
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> res = new LinkedList<>();
        int point1 = 0;
        int point2 = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        while(point1 < len1 && point2 < len2){
            if(nums1[point1] > nums2[point2]){
                point2++;
            }else if(nums1[point1] < nums2[point2]){
                point1++;
            }else{
                res.add(nums1[point1]);
                point1++;
                point2++;
            }
        }
        int[] res2 = new int[res.size()];
        int i = 0;
        for(int c : res){
            res2[i++] = c;
        }
        return res2;
    }
}


//441. Arranging Coins
/*You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.*/
//一般
public class Solution {
    public int arrangeCoins(int n) {
        int i = 1;
        while(n >= i){
            n -= i;
            i++;
        }
        return i - 1;
    }
}

//根据等差数列性质推到
public class Solution {
    public int arrangeCoins(int n) {
        return (int) (Math.sqrt(2*(long)n+0.25) - 0.5);//注意其中的数据类型转换，避免溢出
    }
}


//475. Heaters
/*Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.*/
public class Solution {
    public int findRadius(int[] houses, int[] heaters) {//两个指针
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int len1 = houses.length;
        int len2 = heaters.length;
        int p1 = 0;
        int p2 = 0;
        int min = Integer.MIN_VALUE;
        while(p1 < len1){
            if(p2 == len2 - 1){
                min = Math.max(min, Math.abs(houses[p1] - heaters[p2]));
                p1++;
            }else if(Math.abs(houses[p1] - heaters[p2]) >= Math.abs(houses[p1] - heaters[p2 + 1])){
                p2++;
            }else if(Math.abs(houses[p1] - heaters[p2]) < Math.abs(houses[p1] - heaters[p2 + 1])){
                min = Math.max(min, Math.abs(houses[p1] - heaters[p2]));
                p1++;
            }
        }
        return min;
    }
}  


//162. Find Peak Element
//复杂度O(n),遍历搜索
public class Solution {
    public int findPeakElement(int[] nums) {
        int[] res = new int[nums.length + 1];
        res[0] = 1;
        for(int i = 1; i < nums.length; i++){
            res[i] = nums[i] - nums[i - 1];
        }
        res[res.length - 1] = -1;
        for(int i = 0; i < res.length - 1; i++){
            if(res[i] > 0 && res[i + 1] < 0){
                return i;
            }
        }
        return 0;
    }
}
//二分搜索
public class Solution {
    public int findPeakElement(int[] nums) {
         int left = 0;
         int right = nums.length - 1;
         while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[mid + 1]){//利用中间值相互的比较，而不是左右端点值
                right = mid;
            }else{
                left = mid + 1;
            }
         }
        return left;
    }
}
//
public class Solution {
    public int findPeakElement(int[] nums) {
         int left = 0;
         int right = nums.length - 1;
         while(left <= right){
            int mid = left + (right - left) / 2;
            if(mid == nums.length - 1){
                return mid;
            }else if(nums[mid] > nums[mid + 1]){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
         }
        return left;
    }
}


//300. Longest Increasing Subsequence
/*Given an unsorted array of integers, find the length of longest increasing subsequence.*/
//动态规划O(n^2)
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            res[i] = 1;
        }
        int max = 1;
        for(int i = 1; i < nums.length; i++){
            for(int j = i - 1; j >= 0; j--){
                if(nums[j] < nums[i]){
                    res[i] = Math.max(res[i], res[j] + 1);
                    max = Math.max(max, res[i]);
                }
            }
        }
        return max;
    }
}
//二分搜索，O(nlogn)
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int max = 0;
        int[] had = new int[len];//用额外的数组于顺序记录递增子序列，单调递增的
        for(int i = 0; i < len; i++){
            int index = Arrays.binarySearch(had, 0, max, nums[i]);//搜索用于插入或者替换的索引位置
            if(index < 0){
                index = -(index + 1);//如果没有找到则返回 -(插入点位置)-1
            }
            had[index] = nums[i];
            if(index == max){//如果索引等于当前的最大长度，说明这个值被添加到了末尾，则序列长度加一
                max++;
            }
        }
        return max;
    }
}


//74. Search a 2D Matrix
/*Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]*/
//两次使用二分搜索
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row == 0) return false;
        int cul = matrix[0].length;
        int left = 0;
        int right = row - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(matrix[mid][cul - 1] < target){
                left = mid + 1;
            }else if(matrix[mid][cul - 1] > target){
                right = mid;//如果没有减一，则相应的条件也要去掉等于号
            }else{
                return true;
            }
        }
        int line = left;
        left = 0;
        right = cul - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(matrix[line][mid] > target){
                right = mid - 1;
            }else if(matrix[line][mid] < target){
                left = mid + 1;
            }else{
                return true;
            }
        }
        return false;
    }
}

//当成一维数组来二分搜索
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row == 0) return false;
        int cul = matrix[0].length;
        int left = 0 ;
        int right = row * cul - 1;//将行与列统一
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(matrix[mid / cul][mid % cul] > target){
                right = mid - 1;
            }else if(matrix[mid / cul][mid % cul] < target){
                left = mid + 1;
            }else{
                return true;
            }
        }
        return false;
    }
}

//根据右下角的值进行比较
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row == 0) return false;
        int cul = matrix[0].length;
        int r = 0;
        int c = cul - 1;
        while(r < row && c >= 0){
            if(matrix[r][c] > target){
                c--;
            }else if(matrix[r][c] < target){
                r++;
            }else{
                return true;
            }
        }
        return false;
    }
}


//240. Search a 2D Matrix II
/*Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]*/
////根据右下角的值进行比较,与上题完全一样
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row == 0) return false;
        int cul = matrix[0].length;
        int r = 0;
        int c = cul - 1;
        while(r < row && c >= 0){
            if(matrix[r][c] > target){
                c--;
            }else if(matrix[r][c] < target){
                r++;
            }else{
                return true;
            }
        }
        return false;
    }
}



//392. Is Subsequence
//用两个指针
public class Solution {
    public boolean isSubsequence(String s, String t) {
        int lens = s.length();
        int lent = t.length();
        int ps = 0;
        int pt = 0;
        while(ps < lens && pt < lent){
            if(s.charAt(ps) == t.charAt(pt)){
                ps++;
                pt++;
            }else{
                pt++;
            }
        }
        return ps == lens;
    }
}


//287. Find the Duplicate Number
/*Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.*/

//二分搜索 时间复杂度O(nlogn),空间复杂度O(1)
public class Solution {
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int left = 1;
        int right = len - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            int n = 0;
            for(int c : nums){
                if(c <= mid) n++;//每次遍历累计小于中间值的个数，如果大于中间值，则重复的值要比此时的mid大
            }
            if(n > mid){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
}
//环形链路法
public class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];//慢的走一步，快的走两步
        int fast = nums[nums[0]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;//快慢相遇时，将快的从头开始
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;//再次相遇时即为寻找到
    }
}



//209. Minimum Size Subarray Sum
/*Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.*/
//两个指针 时间复杂度O(n)
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int right = 0;
        int len = nums.length;
        if(len == 0) return 0;
        int min = Integer.MAX_VALUE;
        int sum = nums[0];
        while(right < len){
            if(sum >= s){
                min = Math.min(min, right - left + 1);
                sum -= nums[left];
                left++;//左右指针区间的和如果大于s，则left向右移，如果小于s，right向右移
            }else{
                right++;
                if(right >= len) break;
                sum += nums[right];
                
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
//二分搜索 时间复杂度O(nlogn)
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        int[] sum = new int[len + 1];
        sum[1] = nums[0];
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < len; i++){
            sum[i + 1] = sum[i] + nums[i];//先累加和
        }
        for(int i = 1; i < sum.length; i++){
            int left = i;
            int right = sum.length - 1;
            while(left <= right){
                int mid = left + (right - left) / 2;
                if(sum[mid] - sum[i - 1] >= s){//寻找累加和大于s的一段，并比较记录，如果大于s，right左移，否则left右移
                    min = Math.min(min, mid - i + 1);//起始位置始终为i，不随left，right改变
                    right = mid - 1;//距离是mid与i的距离
                }else{
                    left = mid + 1;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}



//4. Median of Two Sorted Arrays
/*There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).*/
//合并两个数组，并排序，再根据奇偶性找出中位数时间O(n+m),空间O(n+m)
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        if(len == 0) return 0.0;
        int[] nums = new int[len1 + len2];
        for(int i = 0; i < len1; i++){
            nums[i] = nums1[i];
        }
        for(int i = 0; i < len2; i++){
            nums[i + len1] = nums2[i];
        }
        Arrays.sort(nums);
        double res = 0.0;
        if(len % 2 == 0){
            res += nums[len / 2] + nums[len / 2 - 1];
            return res / 2;
        }else{
            res +=  nums[len / 2];
            return res;
        }
        
    }
}



//29. Divide Two Integers
/*Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.*/
public class Solution {
    public int divide(int dividend, int divisor) {
        long a = dividend;
        long b = divisor;
        if(divisor == 0) return Integer.MAX_VALUE;
        a = Math.abs(a);
        b = Math.abs(b);
        long res = 0;
        while(a >= b){
            long c = b;
            for(int i = 0; c <= a;){
                a -= c;
                res += 1 << i++;//将减数每次变为原来的2倍，超出时，再从头开始
                c = c << 1;
            }
        }
        
        if((dividend < 0 && divisor < 0)||(dividend > 0 && divisor > 0)){
            return res > Integer.MAX_VALUE ? 2147483647 : (int)res;
        }else{
            return (int)-res;
        }
    }
}



//15. 3Sum
//枚举法 超时O(n^3)
public class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> temp = new LinkedList<>();
    Set<String> had = new HashSet<>();
    public List<List<Integer>> threeSum(int[] nums) {
        help(nums, 0, 0);
        return res;
    }
    
    public void help(int[] nums, int sum, int start){
        if(temp.size() == 3){
            if(sum == 0){
                String t = "";
                List<Integer> tt = new LinkedList<>(temp);
                Collections.sort(tt);
                for(int c : tt){
                    t += c;
                }
                if(!had.contains(t)){
                    had.add(t);
                    res.add(new LinkedList<>(tt));                    
                }
            }
            return;
        }else{
            for(int i = start; i < nums.length; i++){
                temp.add(nums[i]);
                help(nums, sum + nums[i], i + 1);
                temp.remove(temp.size() - 1);
            }
        }
        return;
    }
}

//O(n^2) 超时
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        
        for(int i = 0, len = nums.length; i < len; i++){
            int left = i + 1;
            int right = len - 1;
            while(left < right){
                if(nums[left] + nums[right] > -nums[i]){
                    right--;
                }else if(nums[left] + nums[right] < -nums[i]){
                    left++;
                }else{
                    List<Integer> temp = Arrays.asList(nums[i], nums[left], nums[right]);
                    if(!res.contains(temp)){//被动去重复
                        res.add(new LinkedList<>(temp));
                    }
                    left++;
                    right--;
                }
            }
        }
        return res;
    }
}

//主动去重复O(n^2) 此方法OK
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        
        for(int i = 0, len = nums.length; i < len; i++){//先固定一个位置，然后指针从两端往中间移动，并且主动去除重复元素，而不是等到满足条件后再查找是否存在
            if(nums[i] > 0) break;
            if(i >= 1 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = len - 1;
            while(left < right){
                if(nums[left] + nums[right] > -nums[i]){
                	while(left < right && nums[right] == nums[right - 1]) right--;
                    right--;
                }else if(nums[left] + nums[right] < -nums[i]){
                	while(left < right && nums[left] == nums[left + 1]) left++;
                    left++;
                }else{
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    left++;
                    while(left < right && nums[right] == nums[right - 1]) right--;
                    right--;
                }
            }
        }
        return res;
    }
}



//18. 4Sum
/*Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note: The solution set must not contain duplicate quadruplets.*/
//与上题思路完全一致
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for(int j = 0; j < nums.length; j++){
            if(j >= 1 && nums[j] == nums[j - 1]) continue;
            for(int i = j + 1, len = nums.length; i < len; i++){
                if(i >= 1 && i - 1 != j && nums[i] == nums[i - 1]) continue;//此处与15题略有区别，当j选取时，i不能跳过此时的值
                int left = i + 1;
                int right = len - 1;
                while(left < right){
                    if(nums[left] + nums[right] + nums[i] + nums[j] > target){
                    	while(left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                    }else if(nums[left] + nums[right] + nums[i] + nums[j] < target){
                    	while(left < right && nums[left] == nums[left + 1]) left++;
                        left++;
                    }else{
                        res.add(Arrays.asList(nums[j], nums[i], nums[left], nums[right]));
                        while(left < right && nums[left] == nums[left + 1]) left++;
                        left++;
                        while(left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                    }
                }
            }
        }
        return res;
    }
}



//454. 4Sum II
/*Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.*/
//时间复杂度O(n^2) 空间复杂度O(n^2) 用空间换时间
public class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    	//使用两个Map来统计A与B的和，C与D的和，再通过Map寻找是否相加为target
        Map<Integer, Integer> E = new HashMap<>();
        Map<Integer, Integer> F = new HashMap<>();
        for(int i : A){
            for(int j : B){
                if(E.containsKey(i + j)){
                    E.put(i + j, E.get(i + j) + 1);
                }else{
                    E.put(i + j, 1);
                }
            }
        }
        for(int i : C){
            for(int j : D){
                if(F.containsKey(i + j)){
                    F.put(i + j, F.get(i + j) + 1);
                }else{
                    F.put(i + j, 1);
                }
            }
        } 
        int res = 0;
        for(Map.Entry<Integer, Integer> e : E.entrySet()){
            if(F.containsKey(-e.getKey())){
                res += e.getValue() * F.get(-e.getKey());
            }
        }
        return res;
    }
}

//步骤简化版
public class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> E = new HashMap<>();
        for(int i : A){
            for(int j : B){
                if(E.containsKey(i + j)){
                    E.put(i + j, E.get(i + j) + 1);
                }else{
                    E.put(i + j, 1);
                }
            }
        }
        int res = 0;
        for(int i : C){
            for(int j : D){
                if(E.containsKey(-i - j)){
                    res += E.get(- i - j);
                }
            }
        } 
        return res;
    }
}



//378. Kth Smallest Element in a Sorted Matrix
/*Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.*/
//方法1 ：转换为一维数组后排序直接按索引寻址
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length;
        int cul = matrix[0].length;
        int[] m = new int[row * cul];
        int i = 0;
        for(int[] c : matrix){
            for(int cc : c)
                m[i++] = cc;
        }
        Arrays.sort(m);
        return m[k - 1];
    }
}

//方法2：使用优先队列，自动排序，（适当结合数据结构会有比较好的效果）
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        Queue<Integer> had = new PriorityQueue<>();
        for(int[] cc : matrix){
            for(int c : cc){
                had.add(c);
            }
        }
        int i = 1;
        while(i != k){
            had.poll();
            i++;
        }
        return had.poll();
    }
}



//373. Find K Pairs with Smallest Sums
/*You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]*/
//方法1：使用优先队列，重新定义比较方式
public class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Queue<int[]> had = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] s1, int[] s2){
                if(s1[0] + s1[1] > s2[0] + s2[1]){
                    return 1;
                }else if(s1[0] + s1[1] < s2[0] + s2[1]){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        for(int a : nums1){
            for(int b : nums2){
                int[] temp = new int[2];
                temp[0] = a;
                temp[1] = b;
                had.add(temp);
            }
        }
        List<int[]> res = new LinkedList<>();
        int i = 0;
        while(i < k){
            int[] t = had.poll();
            if(t != null)
                res.add(t);
            i++;
        }
        return res;
    }
}



//154. Find Minimum in Rotated Sorted Array II
/*Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.*/
//对于存在重复数字的旋转数组，尤其要注意相等的情况（多打草稿，多记笔记）
//最坏为O(n) 平均O(lgn)
public class Solution {
    public int findMin(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[left]){
                right = mid;
            }else if(nums[mid] > nums[right]){
                left = mid + 1;
            }else if(nums[mid] == nums[left]){
                if(nums[mid] == nums[right]){
                    left++;
                }else{
                    right--;
                }
            }else if(nums[mid] == nums[right]){//每一个if之后都可以进一步缩小条件范围，
                right--;
            }else{
                return nums[left];
            }
        }
        return nums[left];
    }
}

//简化版
public class Solution {
    public int findMin(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[left]){
                right = mid;
            }else if(nums[mid] > nums[right]){
                left = mid + 1;
            }else{
                right--;
            }
        }
        return nums[left];
    }
}




//354. Russian Doll Envelopes
/*You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).*/
//排序后再动态规划时间复杂度O(n^2)
public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,new Comparator<int[]>(){
            public int compare(int[] s1, int[] s2){
                if(s1[0] > s2[0]){
                    return 1;
                }else if(s1[0] < s2[0]){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        int len = envelopes.length;
        if(len == 0) return 0;
        int[] res = new int[len];
        for(int i = 0; i < len; i++){
            res[i] = 1;
        }
        int max = 1;
        for(int i = 1; i < len; i++){
            for(int j = i - 1; j >= 0; j--){
                if(envelopes[i][1] > envelopes[j][1] && envelopes[i][0] > envelopes[j][0] && res[i] < res[j] + 1){
                    res[i] = res[j] + 1;
                    max = Math.max(max, res[i]);
                }
            }
        }
        return max;
    }
}

//时间复杂度O(nlogn)
public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,new Comparator<int[]>(){
            public int compare(int[] s1, int[] s2){
                if(s1[0] > s2[0]){
                    return 1;
                }else if(s1[0] < s2[0]){
                    return -1;
                }else{//关键点：当首位相等时，第二位按递减排序
                    if(s1[1] > s2[1]){
                        return -1;
                    }else if(s1[1] < s2[1]){
                        return 1;
                    }else{
                        return 0;
                    }
                }
            }
        });
        int len = envelopes.length;
        if(len == 0) return 0;
        int[] res = new int[len];//与LIS相同，用一个数组记录
        int max = 0;
        for(int i = 0; i < len; i++){
            int target = envelopes[i][1];
            int index = Arrays.binarySearch(res, 0, max, target);
            if(index < 0){
                index = -(index + 1);
            } 
            res[index] = target;
            if(index == max){
                max++;
            }
            
        }
        return max;
    }
}



//436. Find Right Interval
/*Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

Note:
You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.
Example 1:
Input: [ [1,2] ]

Output: [-1]

Explanation: There is only one interval in the collection, so it outputs -1.
Example 2:
Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]

Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.*/
//先排序，排序后用二分搜索找最邻近的右值，最后用Map查找对应的索引
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        Interval[] v = new Interval[intervals.length];
        System.arraycopy(intervals, 0, v, 0, intervals.length);
        //Arrays.sort(intervals);
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                if(i1.start > i2.start){
                    return 1;
                }else if(i1.start < i2.start){
                    return -1;
                }else{
                    if(i1.end > i2.end){
                        return 1;
                    }else if(i1.end < i2.end){
                        return -1;
                    }else{
                        return 0;
                    }
                }
            }
        });
        Map<Interval, Integer> had = new HashMap<>();
        for(int i = 0; i < v.length; i++){
            had.put(v[i], i);
        }
        int[] res = new int[v.length];
        for(int i = 0, len = v.length; i < len; i++){
            int target = v[i].end;
            int left = 0;
            int right = len - 1;
            if(target > intervals[len - 1].start){
                res[i] = -1;
                continue;
            }
            while(left < right){
                int mid = left + (right - left) / 2;
                if(intervals[mid].start > target){
                    right = mid;
                }else if(intervals[mid].start < target){
                    left = mid + 1;
                }else{
                    left = mid;
                    right = mid;
                }
            }
            res[i] = had.get(intervals[right]);
        }
        return res;
    }
}



//274. H-Index
//方法1: 时间复杂度O(n) 先排序，再从右往左寻找
public class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int len = citations.length;
        for(int i = len; i > 0; i--){
            if(citations[len - i] >= i){
                return i;
            }
        }
        return 0;
    }
}

//方法2：用数组记录每个引用次数有多少篇文章，再从右往左累加
public class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] had = new int[len + 1];
        for(int i = 0; i < len; i++){
            had[citations[i] <= len ? citations[i] : len]++;
        }
        int sum = 0;
        for(int i = len; i >=0; i--){
            sum += had[i];
            if(sum >= i){
                return i;
            }
        }
        return 0;
    }
}

//方法3：先排序，再用二分搜索法 找到第一个引用次数大于位置的值
public class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int len = citations.length;
        if(len == 0) return 0;
        int left = 0;
        int right = len - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(citations[mid] >= len - mid){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return Math.min(len - right, citations[right]);
    }
}
//275. H-Index II
//方法1：从右到左遍历O(n)
public class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        for(int i = len; i > 0; i--){
            if(citations[len - i] >= i){
                return i;
            }
        }
        return 0;
    }
}
//方法2：对于已经有序的数组 直接使用二分搜索法O(logn)
public class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        if(len == 0) return 0;
        int left = 0;
        int right = len - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(citations[mid] >= len - mid){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return Math.min(len - right, citations[right]);
    }
}



//110. Balanced Binary Tree
/*Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归解法
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int depleft = dep(root.left);
        int depright = dep(root.right);
        return Math.abs(depleft - depright) > 1 ? false : isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int dep(TreeNode root){
        if(root == null){
            return 0;
        }else{
            return 1 + Math.max(dep(root.left), dep(root.right));
        }
    }
}



//104. Maximum Depth of Binary Tree
//简单算法用迭代未必比递归快
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归解法
public class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }else{
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }
}

//方法2：深度优先 DFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        Stack<TreeNode> sta = new Stack<>();
        Stack<Integer> value = new Stack<>();
        sta.push(root);
        value.push(1);
        int max = 0;
        while(!sta.isEmpty()){
            TreeNode r = sta.pop();
            int v = value.pop();
            max = Math.max(max, v);
            if(r.left != null){
                sta.push(r.left);
                value.push(v + 1);
            }
            if(r.right != null){
                sta.push(r.right);
                value.push(v + 1);
            }
        }
        return max;
    }
}

//方法3：广度优先 BFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> sta = new LinkedList<>();
        Queue<Integer> value = new LinkedList<>();
        sta.offer(root);
        value.offer(1);
        int max = 0;
        while(!sta.isEmpty()){
            TreeNode r = sta.poll();
            int v = value.poll();
            max = Math.max(max, v);
            if(r.left != null){
                sta.offer(r.left);
                value.offer(v + 1);
            }
            if(r.right != null){
                sta.offer(r.right);
                value.offer(v + 1);
            }
        }
        return max;
    }
}



//111. Minimum Depth of Binary Tree
/*Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.*/
//递归解法
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//此题需要注意的是，到叶节点的最小深度，而不是任意节点都可以，不能是根节点，而上题求最大深度时，本身就一定是叶节点，不需要再特别考虑
//方法1： 递归
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null){
            return 1;
        }else if(root.left == null && root.right != null){
            return 1 + minDepth(root.right);
        }else if(root.right == null && root.left != null){
            return 1 + minDepth(root.left);
        }else{
            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        }
    }
}


//方法2：迭代
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        Stack<TreeNode> sta = new Stack<>();
        Stack<Integer> val = new Stack<>();
        sta.push(root);
        val.push(1);
        int min = Integer.MAX_VALUE;
        while(!sta.isEmpty()){
            TreeNode s = sta.pop();
            int v = val.pop();
            if(s.left == null && s.right == null){
                min = Math.min(min, v);
            }else if(s.left == null){
                sta.push(s.right);
                val.push(v + 1);
            }else if(s.right == null){
                sta.push(s.left);
                val.push(v + 1);
            }else{
                sta.push(s.left);
                val.push(v + 1);
                sta.push(s.right);
                val.push(v + 1);                
            }
        }
        return min;
    }
}


//102. Binary Tree Level Order Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：使用两个队列和一个boolean标志，间隔往队列里添加，以此保证按层广度优先搜索  按照每层顺序，逐层添加
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        boolean flag = true;
        Queue<TreeNode> t = new LinkedList<>();
        Queue<TreeNode> f = new LinkedList<>();
        if(root == null) return res;
        t.offer(root);
        while(!t.isEmpty() || !f.isEmpty()){
            if(flag){
                List<Integer> temp = new LinkedList<>();
                while(!t.isEmpty()){
                    TreeNode r = t.poll();
                    if(r != null){
                        if(r.left != null) f.offer(r.left);
                        if(r.right != null) f.offer(r.right);
                        temp.add(r.val);
                    }
                }
                res.add(temp);
                flag = false;
            }else{
                List<Integer> temp = new LinkedList<>();
                while(!f.isEmpty()){
                    TreeNode r = f.poll();
                    if(r != null){
                        if(r.left != null) t.offer(r.left);
                        if(r.right != null) t.offer(r.right);
                        temp.add(r.val);
                    }
                }
                res.add(temp);   
                flag = true;
            }
        }
        return res;
    }
}

//方法2,3：用变量对应记录所在层数，然后直接在List中寻址加入，不必按照每层依次顺序，既可以DFS也可以BFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//BFS
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> sta = new LinkedList<>();
        Queue<Integer> val = new LinkedList<>();
        if(root == null) return res;
        sta.offer(root);
        val.offer(0);
        while(!sta.isEmpty()){
            TreeNode r = sta.poll();
            int v = val.poll();
            if(v >= res.size()){
                res.add(new LinkedList<>());
            }
            res.get(v).add(r.val);
            if(r.left != null) {
                sta.offer(r.left);
                val.offer(v + 1);
            }
            if(r.right != null) {
                sta.offer(r.right);
                val.offer(v + 1);
            }
        }
        return res;
    }
}

//DFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Stack<TreeNode> sta = new Stack<>();
        Stack<Integer> val = new Stack<>();
        if(root == null) return res;
        sta.push(root);
        val.push(0);
        while(!sta.isEmpty()){
            TreeNode r = sta.pop();
            int v = val.pop();
            if(v >= res.size()){
                res.add(new LinkedList<>());
            }
            res.get(v).add(r.val);
            if(r.right != null) {//此处注意顺序
                sta.push(r.right);
                val.push(v + 1);
            }
            if(r.left != null) {
                sta.push(r.left);
                val.push(v + 1);
            }
        }
        return res;
    }
}

//方法4：递归
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) return res;
        help(root, 0);
        return res;
    }
    
    public void help(TreeNode root, int height){
        if(height >= res.size()){
            res.add(new LinkedList<>());
        }
        res.get(height).add(root.val);
        if(root.left != null){
            help(root.left, height + 1);
        }
        if(root.right != null){
            help(root.right, height + 1);
        }
    }
}


//103. Binary Tree Zigzag Level Order Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：方法2 ： 方法3，方法4：只需在上一题的最后将奇数行的list倒序即可
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        boolean flag = true;
        Queue<TreeNode> t = new LinkedList<>();
        Queue<TreeNode> f = new LinkedList<>();
        if(root == null) return res;
        t.offer(root);
        while(!t.isEmpty() || !f.isEmpty()){
            if(flag){
                List<Integer> temp = new LinkedList<>();
                while(!t.isEmpty()){
                    TreeNode r = t.poll();
                    if(r != null){
                        if(r.left != null) f.offer(r.left);
                        if(r.right != null) f.offer(r.right);
                        temp.add(r.val);
                    }
                }
                res.add(temp);
                flag = false;
            }else{
                List<Integer> temp = new LinkedList<>();
                while(!f.isEmpty()){
                    TreeNode r = f.poll();
                    if(r != null){
                        if(r.left != null) t.offer(r.left);
                        if(r.right != null) t.offer(r.right);
                        temp.add(r.val);
                    }
                }
                res.add(temp);   
                flag = true;
            }
        }
        for(int i = 0; i < res.size(); i++){//加上倒序
            if(i % 2 != 0){
                Collections.reverse(res.get(i));
            }
        }
        return res;
    }
}

//方法5,6，7,8：每隔一行从0开始插入
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        boolean flag = true;
        Queue<TreeNode> t = new LinkedList<>();
        Queue<TreeNode> f = new LinkedList<>();
        if(root == null) return res;
        t.offer(root);
        while(!t.isEmpty() || !f.isEmpty()){
            if(flag){
                List<Integer> temp = new LinkedList<>();
                while(!t.isEmpty()){
                    TreeNode r = t.poll();
                    if(r != null){
                        if(r.left != null) f.offer(r.left);
                        if(r.right != null) f.offer(r.right);
                        temp.add(r.val);
                    }
                }
                res.add(temp);
                flag = false;
            }else{
                List<Integer> temp = new LinkedList<>();
                while(!f.isEmpty()){
                    TreeNode r = f.poll();
                    if(r != null){
                        if(r.left != null) t.offer(r.left);
                        if(r.right != null) t.offer(r.right);
                        temp.add(0,r.val);//此处变化
                    }
                }
                res.add(temp);   
                flag = true;
            }
        }
        return res;
    }
}

//递归
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return res;
        help(root, 0);
        return res;
    }
    
    public void help(TreeNode root, int height){
        if(height >= res.size()){
            res.add(new LinkedList<>());
        }
        if(height % 2 == 0){//此处加上判断
            res.get(height).add(root.val);
        }else{
            res.get(height).add(0, root.val);
        }
        
        if(root.left != null){
            help(root.left, height + 1);
        }
        if(root.right != null){
            help(root.right, height + 1);
        }
    }
}



//107. Binary Tree Level Order Traversal II
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//将102题的答案逆序即可
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Stack<TreeNode> sta = new Stack<>();
        Stack<Integer> val = new Stack<>();
        if(root == null) return res;
        sta.push(root);
        val.push(0);
        while(!sta.isEmpty()){
            TreeNode r = sta.pop();
            int v = val.pop();
            if(v >= res.size()){
                res.add(new LinkedList<>());
            }
            res.get(v).add(r.val);
            if(r.right != null) {//此处注意顺序
                sta.push(r.right);
                val.push(v + 1);
            }
            if(r.left != null) {
                sta.push(r.left);
                val.push(v + 1);
            }
        }
        Collections.reverse(res);
        return res; 
    }
}



//112. Path Sum
/*Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.*/
//方法1：迭代 既可以DFS也可以BFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        Stack<TreeNode> sta = new Stack<>();
        Stack<Integer> value = new Stack<>();
        if(root == null) return false;
        sta.push(root);
        value.push(root.val);
        while(!sta.isEmpty()){
            TreeNode r = sta.pop();
            int v = value.pop();
            if(v == sum && r.left == null && r.right == null) return true;
            if(r.left != null){
                sta.push(r.left);
                value.push(v + r.left.val);
            }
            if(r.right != null){
                sta.push(r.right);
                value.push(v + r.right.val);
            }
        }
        return false;
    }
}

//方法2：递归
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    boolean f = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        help(root, root.val, sum);
        return f == true ? true : false;
    }
    
    public void help(TreeNode root, int s, int sum){
        if(s == sum && root.left == null && root.right == null){
            f = true;
            return;
        }
        if(root.left != null){
           help(root.left, s + root.left.val, sum);
        }
        if(root.right != null){
            help(root.right, s + root.right.val, sum);
        }
    }
}

//方法3：递归 尽量使用本函数，不要新建辅助函数
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(sum == root.val && root.left == null && root.right == null){
            return true;
        }else{
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }
}



//113. Path Sum II
/*Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    List<List<Integer>> res = new LinkedList<>();
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) return res;
        List<Integer> temp = new LinkedList<>();
        temp.add(root.val);
        help(root, root.val, sum, temp);
        return res;
        
    }
    
    public void help(TreeNode root, int s, int sum, List<Integer> temp){
        if(s == sum && root.left == null && root.right == null){
            res.add(new LinkedList<>(temp));
        }
        if(root.left != null){
            temp.add(root.left.val);
            help(root.left, s + root.left.val, sum, temp);
            temp.remove(temp.size() - 1);
        }
        if(root.right != null){
            temp.add(root.right.val);
            help(root.right, s + root.right.val, sum, temp);
            temp.remove(temp.size() - 1);
        }
    }
}



//437. Path Sum III
/*You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归
public class Solution {
    int res = 0;
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        help1(root, sum);
        return res;
    }
    public void help1(TreeNode root, int sum){
        if(root == null) return;
        help2(root, root.val, sum);
        help1(root.left, sum);
        help1(root.right, sum);
    }
    
    public void help2(TreeNode root, int s, int sum){
        if(s == sum){
            res++;
        }
        if(root.left != null){
            help2(root.left, s + root.left.val, sum);
        }
        if(root.right != null){
            help2(root.right, s + root.right.val, sum);
        }
    }
}

//方法2：递归 简化
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int res = 0;
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        help2(root, root.val, sum);//其本身也要递归
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return res;
    }
    
    public void help2(TreeNode root, int s, int sum){
        if(s == sum){
            res++;
        }
        if(root.left != null){
            help2(root.left, s + root.left.val, sum);
        }
        if(root.right != null){
            help2(root.right, s + root.right.val, sum);
        }
    }
}


//129. Sum Root to Leaf Numbers
/*Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归，原理与上题类似，先记录路径，再处理
public class Solution {
    List<Integer> node = new LinkedList<>();
    int res = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null) return res;
        node.add(root.val);
        help(root);
        return res;
    }
    public void help(TreeNode root){
        if(root.left == null && root.right == null){
            int s = 0;
            for(int c : node){
                s = s * 10 + c;
            }
            res += s;
        }else{
            if(root.left != null){
                node.add(root.left.val);
                help(root.left);
                node.remove(node.size() - 1);
            }
            if(root.right != null){
                node.add(root.right.val);
                help(root.right);
                node.remove(node.size() - 1);
            }
        }
    }
}

//方法2：递归简化版
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int sumNumbers(TreeNode root) {
        return help(root, 0);
    }
    
    public int help(TreeNode root, int sum){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return sum * 10 + root.val;
        }else{
            return help(root.left, sum * 10 + root.val) + help(root.right, sum * 10 + root.val);
        }
    }
}



//108. Convert Sorted Array to Binary Search Tree 从数组恢复二叉树
/*Given an array where elements are sorted in ascending order, convert it to a height balanced BST.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归
public class Solution {//类似于二分搜索法，条件和指针移动几乎一致
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
        return help(nums, 0, nums.length - 1);
    }
    
    public TreeNode help(int[] nums, int start, int end){
        if(start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = help(nums, start, mid - 1);
        root.right = help(nums, mid + 1, end);
        return root;
    }
}

//方法2：迭代
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        if(len == 0) return null;
        int left = 0;
        int right = len - 1;
        
        TreeNode res = new TreeNode(0);
        Stack<TreeNode> sta = new Stack<>();//每个递归中携带的可变量都需要创建stack来保存
        sta.push(res);
        Stack<int[]> had = new Stack<>();
        had.push(new int[]{left, right});
        while(!sta.isEmpty()){
            TreeNode root = sta.pop();
            int[] index = had.pop();
            left = index[0];
            right = index[1];
            int mid = left + (right - left) /2;
            root.val = nums[mid];
            if(left <= mid - 1){
                root.left = new TreeNode(0);
                sta.push(root.left);
                had.push(new int[]{left, mid - 1});                
            }
            if(mid + 1 <= right){
                root.right = new TreeNode(0);
                sta.push(root.right);
                had.push(new int[]{mid + 1, right});                
            }
        }
        return res;
    }
}



//109. Convert Sorted List to Binary Search Tree
/*Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归，快慢指针找中点
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        return help(head);
    }
    
    public TreeNode help(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        while(fast != null && fast.next != null && fast.next.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
            TreeNode root = new TreeNode(slow.val);
            if(slow.next != null) root.right = help(slow.next);
            if(slow != head) {
                pre.next = null;
                root.left = help(head);
            }
            return root;
    }
    
}

//方法2：递归，简化版
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        while(fast != null && fast.next != null && fast.next.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        if(slow.next != null) root.right = sortedListToBST(slow.next);
        if(slow != head) {
            pre.next = null;
            root.left = sortedListToBST(head);
        }
        return root;
    }
    
}



//226. Invert Binary Tree
/*Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}

//方法2：迭代 （迭代似乎更慢。。。）
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> sta = new LinkedList<>();
        if(root == null) return null;
        sta.offer(root);
        while(!sta.isEmpty()){
            TreeNode r = sta.poll();
            TreeNode temp = r.right;
            r.right = r.left;
            r.left = temp;
            if(r.left != null) sta.offer(r.left);
            if(r.right != null) sta.offer(r.right);
        }
        return root;
    }
}


//563. Binary Tree Tilt 注意题意
/*Given a binary tree, return the tilt of the whole tree.

The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.

Example:
Input: 
         1
       /   \
      2     3
Output: 1
Explanation: 
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int sum = 0;
    public int findTilt(TreeNode root) {
        if(root == null) return 0;
        help(root);
        return sum;
    }
    public int help(TreeNode root){
        if(root == null) return 0;
        int left = help(root.left);
        int right = help(root.right);
        sum += Math.abs(left - right);
        return root.val + left + right;
    }
}



//105. Construct Binary Tree from Preorder and Inorder Traversal 已知中序和先序
/*Given preorder and inorder traversal of a tree, construct the binary tree.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归
public class Solution {//中序遍历的主要作用就是确定左右子树的长度，便于先序遍历分割
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if(len == 0) return null;
        Map<Integer, Integer> sta = new HashMap<>();//用Map记录索引值，便于查找
        for(int i = 0; i < len; i++){
            sta.put(inorder[i], i);
        }
        return help(preorder, sta, 0, len - 1, 0, len - 1);
    }
    //////////////////////////////////////////////////////////////     先序遍历的索引                         中序遍历的索引
    public TreeNode help(int[] preorder, Map<Integer, Integer> sta, int start, int end, int left, int right){
        if(start > end || start >= preorder.length) return null;
        TreeNode root = new TreeNode(preorder[start]);
        int index = sta.get(preorder[start]);
        int lenleft = index - left;
        int lenright = right - index;
        root.left = help(preorder, sta, start + 1, start + lenleft, left, index - 1);
        root.right = help(preorder, sta, start + lenleft + 1, start + lenleft + lenright, index + 1, right);
        return root;
    }
}

//方法2：迭代
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if(len == 0) return null;
        Map<Integer, Integer> ind = new HashMap<>();
        for(int i = 0; i < len; i++){
            ind.put(inorder[i], i);
        }
        Stack<TreeNode> sta = new Stack<>();
        Stack<int[]> had = new Stack<>();
        TreeNode res = new TreeNode(0);
        sta.push(res);
        had.push(new int[]{0, len - 1, 0, len - 1});
        while(!sta.isEmpty()){
            TreeNode root = sta.pop();
            int[] temp = had.pop();
            int start = temp[0];
            int end = temp[1];
            int left = temp[2];
            int right = temp[3];
            if(start > end) continue;
            root.val = preorder[start];
            int index = ind.get(preorder[start]);
            int lenleft = index - left;
            int lenright = right - index;
            if(left <= index - 1){
                root.left = new TreeNode(0);
                sta.push(root.left);
                had.push(new int[]{start + 1, start + lenleft, left, index - 1});                
            }
            if(index + 1 <= right){
                root.right = new TreeNode(0);
                sta.push(root.right);
                had.push(new int[]{start + lenleft + 1, end, index + 1, right});                
            }

        }
        return res;
    }
}



//106. Construct Binary Tree from Inorder and Postorder Traversal
/*Given inorder and postorder traversal of a tree, construct the binary tree.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归
public class Solution {
    Map<Integer, Integer> sta = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        if(len == 0) return null;
        for(int i = 0; i < len; i++){
            sta.put(inorder[i], i);
        }
        return help(sta, postorder, 0, len - 1, 0, len - 1);
    }
    //                                                                  后序遍历索引                                           中序遍历索引
    public TreeNode help(Map<Integer, Integer> sta, int[] postorder, int start, int end, int left, int right){
        if(start > end) return null;
        TreeNode root = new TreeNode(postorder[end]);
        int index = sta.get(postorder[end]);
        int lenleft = index - left;
        int lenright = right - index;
        root.right = help(sta, postorder, end - lenright, end - 1, index + 1, right);
        root.left = help(sta, postorder, start, start + lenleft - 1, left, index - 1);
        return root;
    }
}

//方法2：迭代
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = postorder.length;
        if(len == 0) return null;
        Map<Integer, Integer> ind = new HashMap<>();
        for(int i = 0; i < len; i++){
            ind.put(inorder[i], i);
        }
        Stack<TreeNode> sta = new Stack<>();
        Stack<int[]> had = new Stack<>();
        TreeNode res = new TreeNode(0);
        sta.push(res);
        had.push(new int[]{0, len - 1, 0, len - 1});
        while(!sta.isEmpty()){
            TreeNode root = sta.pop();
            int[] temp = had.pop();
            int start = temp[0];
            int end = temp[1];
            int left = temp[2];
            int right = temp[3];
            if(start > end) continue;
            root.val = postorder[end];
            int index = ind.get(postorder[end]);
            int lenleft = index - left;
            int lenright = right - index;
            if(left <= index - 1){
                root.left = new TreeNode(0);
                sta.push(root.left);
                had.push(new int[]{start, start + lenleft - 1, left, index - 1});                
            }
            if(index + 1 <= right){
                root.right = new TreeNode(0);
                sta.push(root.right);
                had.push(new int[]{end - lenright, end - 1, index + 1, right});                
            }

        }
        return res;
    }
}



//101. Symmetric Tree
/*Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归 求出树的中序遍历，再判断是否为回文序列，每个节点还要包含树的高度，防止特殊情况，
public class Solution {
    List<String> res = new LinkedList<>();
    public boolean isSymmetric(TreeNode root) {
        help(root, 0);
        List<String> res1 = new LinkedList<>(res);
        Collections.reverse(res);
        return res.equals(res1) ? true : false;
    }
    
    public void help(TreeNode root, int height){
        if(root == null){
            return;
        }
        help(root.left, height + 1);
        res.add(root.val + "" + height);//要加上树高
        help(root.right, height + 1);
    }
}

//方法2：迭代 思路与方法1相同
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    List<String> res = new LinkedList<>();
    public boolean isSymmetric(TreeNode root) {
        Stack<TreeNode> sta = new Stack<>();
        Stack<Integer> height = new Stack<>();
        int i = 0;
        while(root != null || !sta.isEmpty()){
            while(root != null){
                sta.push(root);
                height.push(i++);
                root = root.left;
            }
            root = sta.pop();
            i = height.pop();
            res.add(root.val + "" + i);
            root = root.right;
            i++;
        }
        List<String> res1 = new LinkedList<>(res);
        Collections.reverse(res);
        return res.equals(res1) ? true : false;
    }
}

//方法3：直接递归比较
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return help(root.left, root.right);
    }
    
    public boolean help(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null){
            return true;
        }else if(root1 != null && root2 != null){
            if(root1.val == root2.val){
                return help(root1.left, root2.right) && help(root1.right, root2.left);
            }else{
                return false;
            }
            
        }else{
            return false;
        }
    }
}

//方法4： 方法3的迭代形式
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        Stack<TreeNode[]> sta = new Stack<>();
        sta.push(new TreeNode[]{root.left, root.right});
        while(!sta.isEmpty()){
            TreeNode[] temp = sta.pop();
            if(temp[0] == null && temp[1] == null){
                continue;
            }else if(temp[0] != null && temp[1] != null){
                if(temp[0].val == temp[1].val){
                    sta.push(new TreeNode[]{temp[0].left, temp[1].right});
                    sta.push(new TreeNode[]{temp[0].right, temp[1].left});
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }
}



//100. Same Tree
/*Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }else if(p != null && q != null){
            if(p.val == q.val){
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}



//404. Sum of Left Leaves
/*Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归
public class Solution {
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        help(root);
        return sum;
    }
    
    public void help(TreeNode root){
        if(root == null) return;
        if(root.left == null){
            help(root.right);
        }else if(root.left.right != null || root.left.left != null){
            help(root.left);
            help(root.right);
        }else{
            sum += root.left.val;
            help(root.right);
        }
    }
}

//方法2：迭代 （为何迭代比递归还要慢？ 数据结构不对？）
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        Stack<TreeNode> sta = new Stack<>();
        sta.push(root);
        while(!sta.isEmpty()){
            root = sta.pop();
            if(root == null) continue;
            if(root.left == null){
                sta.push(root.right);
            }else if(root.left.right != null || root.left.left != null){
                sta.push(root.left);
                sta.push(root.right);
            }else{
                sum += root.left.val;
                sta.push(root.right);
            }
        }
        return sum;
    }
}

//235. Lowest Common Ancestor of a Binary Search Tree
/*Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {//根据二叉搜索树本身的性质，找到使p、q分居root两侧的结点，
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while((root.val - p.val) * (root.val - q.val) > 0){
            root = root.val - p.val > 0 ? root.left : root.right;
        }
        return root;
    }
}



//543. Diameter of Binary Tree
/*Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归计算每一个根节点所能组成的最大长度
public class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        int len = help(root);
        return Math.max(len, Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)));
    }
    
    public int help(TreeNode root){
        int left = 0;
        int right = 0;
        Stack<TreeNode> sta = new Stack<>();
        Stack<Integer> hei = new Stack<>();
        TreeNode temp = root.left;
        int h = 1;
        while(temp != null || !sta.isEmpty()){
            while(temp != null){
                sta.push(temp);
                hei.push(h++);
                temp = temp.left;
            }
            temp = sta.pop();
            h = hei.pop();
            left = Math.max(left, h);
            temp = temp.right;
            h++;
        }
        
        temp = root.right;
        h = 1;
        sta = new Stack<>();
        hei = new Stack<>();
        while(temp != null || !sta.isEmpty()){
            while(temp != null){
                sta.push(temp);
                hei.push(h++);
                temp = temp.left;
            }
            temp = sta.pop();
            h = hei.pop();
            right = Math.max(right, h);
            temp = temp.right;
            h++;
        }
        return left + right;
    }
}

//方法2：递归直接求深度
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int sum = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        help(root);
        return sum;
    }
    public int help(TreeNode root){
        if(root == null) return 0;
        int left = help(root.left);
        int right = help(root.right);
        sum = Math.max(sum, left + right);
        return Math.max(left, right) + 1;//递归求深度
    }
}



//98. Validate Binary Search Tree
/*Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：迭代，利用中序遍历
public class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> sta = new Stack<>();
        TreeNode pre = null;
        while(root != null || !sta.isEmpty()){
            while(root != null){
                sta.push(root);
                root = root.left;
            }
            root = sta.pop();
            if(pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }
}

//方法2：递归
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        return help(root);
    }
    
    public boolean help(TreeNode root){
        if(root == null) return true;
        boolean left = help(root.left);
        if(pre != null && root.val <= pre.val) return false;
        pre = root;
        boolean right = help(root.right);
        return left && right;
    }
}



//94. Binary Tree Inorder Traversal 经典先序遍历
/*Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,3,2].*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：迭代，典型的中序遍历
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> sta = new Stack<>();
        while(root != null || !sta.isEmpty()){
            while(root != null){
                sta.push(root);
                root = root.left;
            }
            root = sta.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}

//方法2：递归
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    List<Integer> res = new LinkedList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return res;
    }
    public void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        res.add(root.val);
        inorder(root.right);
    }
}


//144. Binary Tree Preorder Traversal 经典的先序遍历
/*Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：迭代
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> sta = new Stack<>();
        while(root != null || !sta.isEmpty()){
            while(root != null){
                res.add(root.val);
                sta.push(root);
                root = root.left;
            }
            root = sta.pop();
            root = root.right;
        }
        return res;
    }
}

//方法2：递归
public class Solution {
    List<Integer> res = new LinkedList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        help(root);
        return res;
    }
    public void help(TreeNode root){
        if(root == null) return;
        res.add(root.val);
        help(root.left);
        help(root.right);
    }
}



//145. Binary Tree Postorder Traversal 经典后序遍历、
/*Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：递归
public class Solution {
    List<Integer> res = new LinkedList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        help(root);
        return res;
    }
    public void help(TreeNode root){
        if(root == null) return;
        help(root.left);
        help(root.right);
        res.add(root.val);
    }
}

//方法2：迭代
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> sta = new Stack<>();
        while(root != null || !sta.isEmpty()){
            while(root != null){
                res.add(root.val);
                sta.push(root);
                root = root.right;
            }
            root = sta.pop();
            root = root.left;
        }
        Collections.reverse(res);
        return res;
    }
}


//230. Kth Smallest Element in a BST
/*Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ? k ? BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：先序遍历，然后直接寻址k-1
public class Solution {
    List<Integer> res = new LinkedList<>();
    public int kthSmallest(TreeNode root, int k) {
        help(root);
        return res.get(k - 1);
    }
    public void help(TreeNode root){
        if(root == null) return;
        help(root.left);
        res.add(root.val);
        help(root.right);
    }
}

//方法2：迭代，思路同方法1
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> sta = new Stack<>();
        while(root != null || !sta.isEmpty()){
            while(root != null){
                sta.push(root);
                root = root.left;
            }
            root = sta.pop();
            res.add(root.val);
            root = root.right;
        }
        return res.get(k - 1);
    }
}

//方法3：类似于二分搜索
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if(k > numNode(root.left) + 1){//如果左子数的结点加上root大于k，那么继续在左子树中寻找
            return kthSmallest(root.right, k - numNode(root.left) - 1);//相应的k要减去左子树和root
        }else if(k < numNode(root.left) + 1){//如果小于k，则继续在右子树中寻找
            return kthSmallest(root.left, k);
        }else{
            return root.val;
        }
    }
    
    public int numNode(TreeNode root){
        if(root == null) return 0;
        return 1 + numNode(root.left) + numNode(root.right);
    }
}



//572. Subtree of Another Tree
/*Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.*/
//方法1：递归判断
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null){
            return t == null ? true : false;
        }else if(help(s, t)){
            return true;
        }else{
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }
    }
    
    public boolean help(TreeNode s, TreeNode t){//递归比较是否相同
        if(s == null && t == null){
            return true;
        }else if(s != null && t != null){
            if(s.val != t.val){
                return false;
            }else{
                return help(s.left, t.left) && help(s.right, t.right);
            }
        }else{
            return false;
        }

    }
}



//538. Convert BST to Greater Tree
/*Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：按照右中左的顺序遍历，并不断累加
public class Solution {
    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> sta = new Stack<>();
        TreeNode res = root;
        int pre = 0;
        while(root != null || !sta.isEmpty()){
            while(root != null){
                sta.push(root);
                root = root.right;
            }
            root = sta.pop();
            root.val += pre;
            pre = root.val;
            root = root.left;
        }
        return res;
    }
}

//方法2：递归方法
public class Solution {
    int pre = 0;
    public TreeNode convertBST(TreeNode root) {
        TreeNode res = root;
        help(root);
        return res;
    }
    
    public void help(TreeNode root){
        if(root == null) return;
        help(root.right);
        root.val += pre;
        pre = root.val;
        help(root.left);
    }
}



//637. Average of Levels in Binary Tree
/*Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：将每一层存放，再累加求和求平均，递归
public class Solution {
    List<List<Integer>> sta = new LinkedList<>();
    public List<Double> averageOfLevels(TreeNode root) {
        help(root, 0);
        List<Double> res = new LinkedList<>();
        for(List<Integer> s : sta){
            double sum = 0;//注意数据类型，避免溢出
            double num = 0;
            for(int c : s){
                sum += c;
                num++;
            }
            res.add(sum * 1.0 / num);
        }
        return res;
    }
    
    public void help(TreeNode root, int lev){
        if(root == null) return;
        if(sta.size() <= lev){
            sta.add(new LinkedList<>());
        }
        sta.get(lev).add(root.val);
        help(root.left, lev + 1);
        help(root.right, lev + 1);
    }
}

//方法2：迭代解法
public class Solution {
    List<List<Integer>> sta = new LinkedList<>();
    public List<Double> averageOfLevels(TreeNode root) {
        
        Stack<TreeNode> had = new Stack<>();
        Stack<Integer> lev = new Stack<>();
        int l = 0;
        while(root != null || !had.isEmpty()){
            while(root != null){
                if(sta.size() <= l){
                    sta.add(new LinkedList<>());
                }
                had.push(root);
                lev.push(l++);
                root = root.left;
            }
            root = had.pop();
            l = lev.pop();
            sta.get(l).add(root.val);
            l++;
            root = root.right;
        }
        
        List<Double> res = new LinkedList<>();
        for(List<Integer> s : sta){
            double sum = 0;
            double num = 0;
            for(int c : s){
                sum += c;
                num++;
            }
            res.add(sum * 1.0 / num);
        }
        return res;
    }
}

//方法3：迭代，BFS，记录每层的数量，保证只遍历一次
public class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new LinkedList<>();
        Queue<TreeNode> had = new LinkedList<>();
        if(root == null) return res;
        had.offer(root);
        while(!had.isEmpty()){
            int num = had.size();
            double sum = 0;
            for(int i = 0; i < num; i++){//将每一层全部计数遍历
                root = had.poll();
                sum += root.val;
                if(root.left != null)
                    had.offer(root.left);
                if(root.right != null)
                    had.offer(root.right);
            }
            res.add(sum / num);
        }
        return res;
    }
}



//96. Unique Binary Search Trees
/*Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3*/
//若数组仅有一个元素1，则只有一种BST，单个节点，f(1)=1;  
//若数组有两个元素1，2，则有两种可能，f(2)=f(0)*f(1)+f(1)*f(0);  
//若数组有三个元素1，2，3，则有f(3)=f(0)*f(2)+f(1)*f(1)+f(2)*f(0)  
//由此可以得到递推公式：f(i)=f(0)*f(i-1)+...+f(k-1)*f(i-k)+...+f(i-1)*f(0)  
//还可以直接使用卡特兰数的通项公式直接求出
public class Solution {
    public int numTrees(int n) {
        int[] res = new int[n + 1];
        if(n <= 1) return 1;
        res[0] = 1;
        res[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < i; j++){
                res[i] += res[j] * res[i - j - 1];
            }
        }
        return res[n];
    }
}


//95. Unique Binary Search Trees II
/*Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//递归地求出左右子树的集合，再一一组合，最后加入root节点
public class Solution {
    List<TreeNode> res = new LinkedList<>();
    public List<TreeNode> generateTrees(int n) {
        if(n < 1) return res;
        
        return help(1, n);
    }
    
    public List<TreeNode> help(int start, int end){
        List<TreeNode> temp = new LinkedList<>();
        if(start > end){
            temp.add(null);
            return temp;
        }
        
        for(int i = start; i <= end; i++){
            List<TreeNode> rootleft = help(start, i -1);//递归组合左右子树
            List<TreeNode> rootright = help(i + 1, end);
            for(TreeNode l : rootleft)//组合
                for(TreeNode r : rootright){
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    temp.add(root);
                }
        }
        return temp;
    }
}



//199. Binary Tree Right Side View
/*Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Queue<TreeNode> had = new LinkedList<>();
        if(root == null) return res;
        had.offer(root);
        while(!had.isEmpty()){
            int num = had.size();//从右往左加入queue中，每层的第一个节点即为所求
            for(int i = 0; i < num; i++){
                root = had.poll();
                if(i == 0) res.add(root.val);
                if(root.right != null)
                    had.offer(root.right);
                if(root.left != null)
                    had.offer(root.left);                
            }

        }
        return res;
    }
}



//116. Populating Next Right Pointers in Each Node
/*For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL*/
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
//方法1：时间复杂度O(n)，空间复杂度O(n) 时间复杂度过大
public class Solution {
    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> had = new LinkedList<>();
        if(root == null) return;
        had.offer(root);
        while(!had.isEmpty()){
            int num = had.size();
            TreeLinkNode pre = null;
            for(int i = 0; i < num; i++){
                root = had.poll();
                if(i == 0){
                    pre = root;//保留上一次的结点
                }
                if(i > 0){
                    pre.next = root;//pre结点的next即为本次节点，第一个节点和最后一个节点不同
                    pre = root;
                }
                if(i == num - 1) root.next = null;
                if(root.left != null) had.offer(root.left);
                if(root.right != null) had.offer(root.right);                
            }

        }
    }
}

//方法2：时间复杂度O(n)，空间复杂度O(1) 前提是完美二叉搜索树
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;   
        while(root != null){
            TreeLinkNode rootleft = root;
            while(rootleft != null){//逐层搜索替换
                if(rootleft.left != null) rootleft.left.next = rootleft.right;
                if(rootleft.right != null && rootleft.next != null) rootleft.right.next = rootleft.next.left;
                rootleft = rootleft.next;
            }  
            root = root.left;
        }
    }
}


//117. Populating Next Right Pointers in Each Node II
/*Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL*/
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
//与上题相同
public class Solution {
    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> had = new LinkedList<>();
        if(root == null) return;
        had.offer(root);
        while(!had.isEmpty()){
            int num = had.size();
            TreeLinkNode pre = null;
            for(int i = 0; i < num; i++){
                root = had.poll();
                if(i == 0){
                    pre = root;
                }
                if(i > 0){
                    pre.next = root;
                    pre = root;
                }
                if(i == num - 1) root.next = null;
                if(root.left != null) had.offer(root.left);
                if(root.right != null) had.offer(root.right);                
            }

        }
    }
}




//501. Find Mode in Binary Search Tree
/*For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    List<Integer> res = new LinkedList<>();
    int max = 0;
    int now = 0;
    int pre = 0;
    public int[] findMode(TreeNode root) {
        help(root);
        int i = 0;
        int num = res.size();
        int[] r = new int[num];
        for(int c : res){
            r[i++] = c;
        }
        return r;
    }
    
    public void help(TreeNode root){
        if(root == null) return;
        help(root.left);
        if(res.size() == 0){
            now = 1;
            max = 1;
            res.add(root.val);
            pre = root.val;
        }else{
            if(root.val == pre){
                now++;
            }else{
                now = 1;
                pre = root.val;
            }
            if(now > max){
                max = now;
                res = new LinkedList<>();
                res.add(pre);
            }else if(now == max){
                res.add(pre);
            }
        }
        help(root.right);
    }
}



//606. Construct String from Binary Tree
/*Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())", 
but you need to omit all the unnecessary empty parenthesis pairs. 
And it will be "1(2(4))(3)".
Example 2:
Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example, 
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//递归
public class Solution {
    
    public String tree2str(TreeNode t) {
        if(t == null) return "";
        return help(t).toString();
    }
    
    public StringBuilder help(TreeNode root){
        StringBuilder temp = new StringBuilder();
        temp.append(root.val);
        if(root.left != null){//注意左子树的特殊
            temp.append("(" + help(root.left) + ")");
        }else{
            if(root.right != null){
                temp.append("()");
            }
        }
        if(root.right != null){
            temp.append("(" + help(root.right) + ")");
        }
        return temp;
    }
}



//114. Flatten Binary Tree to Linked List
/*Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：迭代，自底向上逐层迭代
public class Solution {
    public void flatten(TreeNode root) {
        Stack<TreeNode> had = new Stack<>();
        while(root != null || !had.isEmpty()){
            while(root != null){
                had.push(root);
                root = root.left;
            }
            root = had.pop();
            if(root.left != null){
                TreeNode temp = root.right;
                root.right = root.left;
                root.left = null;
                while(root.right != null){
                    root = root.right;
                }
                root.right = temp;
            }
            root = root.right;
        }
    }
}

//方法2：递归，方法同上
public class Solution {
    public void flatten(TreeNode root) {
        help(root);
        return;
    }
    public void help(TreeNode root){
        if(root == null) return;
        help(root.left);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        while(root.right != null){
            root = root.right;
        }
        root.right = temp;
        help(root.right);
    }
}

//方法3：也不完全是后序遍历（右左中）
public class Solution {
    TreeNode pre = null;
    public void flatten(TreeNode root) {
        help(root);
        return;
    }
    public void help(TreeNode root){
        if(root == null) return;
        help(root.right);
        help(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }
}



//515. Find Largest Value in Each Tree Row
/*You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：按层遍历，取每层最大值
public class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Queue<TreeNode> had = new LinkedList<>();
        if(root == null) return res;
        had.offer(root);
        while(!had.isEmpty()){
            int num = had.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < num; i++){
                root = had.poll();
                max = Math.max(root.val, max);
                if(root.left != null) had.offer(root.left);
                if(root.right != null) had.offer(root.right);   
            }
            res.add(max);
        }
        return res;
    }
}

//513. Find Bottom Left Tree Value
/*Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2: 
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：按层遍历，每层只取第一个值（通用方法，往往不是最巧妙的办法，但更有价值）
public class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> had = new LinkedList<>();
        had.offer(root);
        int max = 0;
        while(!had.isEmpty()){
            int num = had.size();
            for(int i = 0; i < num; i++){
                root = had.poll();
                if(i == 0) max = root.val;
                if(root.left != null) had.offer(root.left);
                if(root.right != null) had.offer(root.right);   
            }
        }
        return max;
    }
}

//方法2：每层，从右往左遍历，则最后一个即为所求
public class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> had = new LinkedList<>();
        had.offer(root);
        while(!had.isEmpty()){
            root = had.poll();
            if(root.right != null) had.offer(root.right);  
            if(root.left != null) had.offer(root.left); 
        }
        return root.val;
    }
}



//236. Lowest Common Ancestor of a Binary Tree
/*Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：通过单独的辅助函数查找，每次都要自顶向下重复查找，时间复杂度高
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        int ps = help(root.right, p);
        int qs = help(root.left, q);
        if(root == p || root == q || ps + qs != 1){//一定是节点相比较，而不能是节点值比较！！！
            return root;
        }else if(ps == 1){
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return lowestCommonAncestor(root.left, p, q);
        }
    }
    
    public int help(TreeNode root, TreeNode s){
        if(root == null) return 0;
        if(root == s){
            return 1;
        }else{
            return help(root.left, s) + help(root.right, s);
        }
    }
}

//方法2：自底向上递归查找，速度更快
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == q || root == p) return root;
        TreeNode left = lowestCommonAncestor(root.left, p , q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) return root;//分居两侧
        return left == null ? right : left;//如只有一边找到，则找到的一定是最靠近根节点的，就是最近的公共点
    }
}



//508. Most Frequent Subtree Sum
/*Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：遍历所有节点求和，并将每个节点值和出现次数用map记录，最后遍历寻找最大值，再遍历加入list中，最后转到int[]中
public class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    int max = 0;//尽量避免多次遍历
    public int[] findFrequentTreeSum(TreeNode root) {
        help(root);
        List<Integer> had = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == max){
                had.add(entry.getKey());
            }
        }
        int[] res = new int[had.size()];
        for(int i = 0; i < res.length; i++){
            res[i] = had.get(i);
        }
        return res;
    }
    
    public int help(TreeNode root){
        if(root == null) return 0;
        int temp = root.val + help(root.left) + help(root.right);
        if(map.containsKey(temp)){
            map.put(temp, map.get(temp) + 1);
        }else{
            map.put(temp, 1);
        }
        max = Math.max(max, map.get(temp));
        return temp;
    }
}



//173. Binary Search Tree Iterator
/*Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.*/
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：使用优先队列保存所有节点值，再不断取出即可
public class BSTIterator {
    PriorityQueue<Integer> had = new PriorityQueue<>();
    public BSTIterator(TreeNode root) {
        help(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return had.size() == 0 ? false : true;
    }

    /** @return the next smallest number */
    public int next() {
        return had.poll();
    }
    public void help(TreeNode root){
        if(root == null) return;
        had.add(root.val);
        help(root.left);
        help(root.right);
        
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
//方法2：使用堆栈
public class BSTIterator {
    Stack<TreeNode> had = new Stack<>();
    TreeNode root;
    public BSTIterator(TreeNode root) {
        this.root = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(root == null && had.isEmpty()) return false;//相当于把通常的DFS分解为hasnext()和next()
        return true;
    }

    /** @return the next smallest number */
    public int next() {
        while(root != null){
            had.push(root);
            root = root.left;
        }
        root = had.pop();
        int res = root.val;//本质上还是中序遍历
        root = root.right;
        return res;
    }
}



//337. House Robber III
/*The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：
public class Solution {
    public int rob(TreeNode root) {
        return help(root)[1];
    }
    
    public int[] help(TreeNode root){
        int[] temp = new int[]{0, 0};
        if(root == null) return temp;
        int[] left = help(root.left);
        int[] right = help(root.right);
        temp[0] = left[1] + right[1];//本节点不取，所能到达的最大值
        temp[1] = Math.max(left[0] + right[0] + root.val, temp[0]);//本节点可取（可能取）所能到的最大值
        return temp;
    }
}

//方法2：
public class Solution {
    public int rob(TreeNode root) {
        int[] res = help(root);
        return Math.max(res[1], res[0]);
    }
    
    public int[] help(TreeNode root){
        int[] temp = new int[]{0, 0};
        if(root == null) return temp;
        int[] left = help(root.left);
        int[] right = help(root.right);
        temp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);//本节点不取所能到的最大值
        temp[1] = left[0] + right[0] + root.val;//本节点取（一定取）所能到的最大值
        return temp;
    }
}



//99. Recover Binary Search Tree
/*Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：迭代法
public class Solution {
    TreeNode fi = null;//保存第一个错误的点
    TreeNode se = null; //保存第二个错误的点
    boolean foundfi = false;//保存是否找到第一个点
    
    public void recoverTree(TreeNode root) {
        find(root);
        int temp = fi.val;//直接将两个错误的点交换，保存值而不是TreeNode，否则因为引用，改变会影响temp的值
        fi.val = se.val;
        se.val = temp;
    }
    
    public void find(TreeNode root){
        TreeNode pre = null;
        Stack<TreeNode> had = new Stack<>();
        while(root != null || !had.isEmpty()){
            while(root != null){
                had.push(root);
                root = root.left;
            }
            root = had.pop();
            if(foundfi == false){//第一次找到错误的值时，pre为出错点
                if(pre != null && pre.val > root.val){
                    fi = pre;
                    
                    foundfi = true;
                }
            }
            if(foundfi){//第二次找到时候，root为错误的点，不能加else，找到第一个的同时要找第二个，防止相邻的两点交换
                if(pre.val > root.val){
                    se = root;
                }
            }
            pre = root;
            root = root.right;
        }
    }
}

//方法2：递归
public class Solution {
    TreeNode fi = null;
    TreeNode se = null; 
    TreeNode pre = null;
    boolean foundfi = false;
    
    public void recoverTree(TreeNode root) {
        find(root);
        int temp = fi.val;
        fi.val = se.val;
        se.val = temp;
    }
    
    public void find(TreeNode root){
        if(root == null) return;
        find(root.left);
        if(!foundfi && pre != null && pre.val > root.val){
            fi = pre;
            foundfi = true;
        }
        if(foundfi && pre.val > root.val){
            se = root;
      
        }
        pre = root;
        find(root.right);
    }
}




//124. Binary Tree Maximum Path Sum
/*Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//方法1：
public class Solution {
    int max = Integer.MIN_VALUE;//注意全局最大值和help()返回值的区别
    public int maxPathSum(TreeNode root) {
        help(root);
        return max;
    }
    
    public int help(TreeNode root){//help函数的功能为，找到以root为根节点到某一个叶子节点的最大值
        if(root == null) return 0;
        int left = help(root.left);
        int right = help(root.right);
        max = Math.max(Math.max(left, 0) + Math.max(right, 0) + root.val, max);//全局最大值是[[left,0],[right,0]]+root.val
        root.val += Math.max(Math.max(left, right), 0);//[left,right,0]的最大值加上root.val本身，就是help的返回值
        return root.val;
    }
}



//279. Perfect Squares
/*Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.*/
//方法1：动态规划。res[i]记录组成i所需要的最少的完美平方数，先找出小于i的最大完美平方数，则res[i]=res[i-j*j]+1;res[i-j*j]已经计算过
public class Solution {
    public int numSquares(int n) {
        int[] res = new int[n + 1];
        res[0] = 0;
        for(int i = 1; i <= n; i++){
            int min = Integer.MAX_VALUE;
            int j = 1;
            while(i >= j * j){//找出小于i的最大完美平方数，从1开始寻找
                min = Math.min(res[i - j * j] + 1, min);
                j++;
            }
            res[i] = min;
        }0
        return res[n];
    }
}




//494. Target Sum
/*You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.*/
//方法1：递归遍历所有可能的符号情况
public class Solution {
    int res = 0;
    int num = 0;
	public  int findTargetSumWays(int[] nums, int S) {
		help(nums, S, 0, 0);
		return res;
	}

	public  void help(int[] nums, int S, int start, int sum) {
		if (num == nums.length) {
			if (sum == S)
				res++;
			return;
		}
		num++;
		help(nums, S, start + 1, sum + nums[start]);
		help(nums, S, start + 1, sum - nums[start]);
		num--;
	}
}




//28. Implement strStr()
/*Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.*/
//方法1：注意边界条件，调用前先判断
public class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack.equals(needle)) return 0;
        int len = needle.length();
        if(len == 0) return 0;
        for(int i = 0, lenh = haystack.length(); i < lenh; i++){
            if(haystack.charAt(i) == needle.charAt(0) && i + len <= lenh && haystack.substring(i, i + len).equals(needle)){
                return i;
            }
        }
        return -1;
    }
}

//方法2：实现的就是indexOf功能，查找第一次出现的字符串位置
public class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}



//459. Repeated Substring Pattern
/*Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"

Output: True

Explanation: It's the substring "ab" twice.*/
//方法1:先寻找重复单元，再将重复单元重复相应次数，与原字符串相比较
public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for(int i = 1; i < len; i++){
            if(len % i == 0){
                StringBuilder temp = new StringBuilder();
                int time = 0;
                while(time < len / i){
                    temp.append(s.substring(0, i));
                    time++;
                }
                if(s.equals(temp.toString())) return true;
            }
        }
        return false;
    }
}

//方法2：从长子串向短子串搜索，且只搜索长度过半的子串，减少搜索时间
public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for(int i = len / 2; i > 0; i--){
            if(len % i == 0){
                StringBuilder temp = new StringBuilder();
                int time = 0;
                int ss = len / i;
                while(time < ss){
                    temp.append(s.substring(0, i));
                    time++;
                }
                if(s.equals(temp.toString())) return true;
            }
        }
        return false;
    }
}


//20. Valid Parentheses
/*Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.*/
//方法1：使用堆栈
public class Solution {
    public boolean isValid(String s) {
        Stack<String> sta = new Stack<>();
        for(int i = 0, len = s.length(); i < len; i++){
            String now = s.charAt(i) + "";
            if(now.equals("(") || now.equals("[") || now.equals("{")){
                sta.push(now);
            }else{
                if(sta.isEmpty()) return false;
                String temp = sta.pop();
                if(now.equals(")") && !temp.equals("(")) return false;
                if(now.equals("]") && !temp.equals("[")) return false;
                if(now.equals("}") && !temp.equals("{")) return false;
            }
        }
        return sta.isEmpty() ? true : false;//最后必须判断堆栈是否为空
    }
}



//14. Longest Common Prefix
/*Write a function to find the longest common prefix string amongst an array of strings.*/
//方法1：先找到最短的字符串长度，再根据长度从长到短遍历，遇到不符合的立即跳出
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        int minlen = Integer.MAX_VALUE;
        for(String s : strs){
            minlen = Math.min(minlen, s.length());
        }
        for(int len = minlen; len > 0; len--){
            String pre = strs[0].substring(0, len);
            int i = 1, num = strs.length;
            for(; i < num; i++){
                if(!strs[i].substring(0, len).equals(pre)) break;
            }
            if(i == num) return pre;
        }
        return "";
    }
}



//58. Length of Last Word
/*Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.*/
//方法1：分割后再计算长度
public class Solution {
    public int lengthOfLastWord(String s) {
        String[] ss = s.split(" ");
        return ss.length == 0 ? 0 : ss[ss.length - 1].length();
    }
}

//方法2
public class Solution {
    public int lengthOfLastWord(String s) {
        return s.trim().length()-s.trim().lastIndexOf(" ")-1;
    }
}



//49. Group Anagrams
/*Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]*/
//方法1：通过map保存对应位置，插入时直接寻址
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for(String s : strs){
            char[] temp = s.toCharArray();
            Arrays.sort(temp);
            String s1 = String.valueOf(temp);
            int index = 0;
            if(map.containsKey(s1)){
                index = map.get(s1);
            }else{
                index = map.size();
                map.put(s1, index);
            }
            if(res.size() <= index){
                res.add(new LinkedList<>());
            }
            res.get(index).add(s);
        }
        return res;
    }
}

//方法2：将阶段性值存储于map中，最后遍历取出
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new LinkedList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] temp = s.toCharArray();
            Arrays.sort(temp);
            String s1 = String.valueOf(temp);
            if(map.containsKey(s1)){
                map.get(s1).add(s);
            }else{
                List<String> list = new LinkedList<>();
                list.add(s);
                map.put(s1, list);
            }
        }
        for(List<String> r : map.values()){
            res.add(r);
        }
        return res;
    }
}

//242. Valid Anagram
/*Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.*/
public class Solution {
    public boolean isAnagram(String s, String t) {
        char[] s1 = s.toCharArray();
        Arrays.sort(s1);
        String s2 = String.valueOf(s1);
        char[] t1 = t.toCharArray();
        Arrays.sort(t1);
        String t2 = String.valueOf(t1);
        return s2.equals(t2);
    }
}



//344. Reverse String
/*Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".*/
public class Solution {
    public String reverseString(String s) {
        StringBuilder res = new StringBuilder(s);
        res = res.reverse();
        return res.toString();
    }
}



//125. Valid Palindrome
/*Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.*/
//方法1：two pointer 使用正则表达式
public class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while(left <= right){
            String tl = s.charAt(left) + "";
            String tr = s.charAt(right) + "";
            if(!tl.matches("[a-z0-9]")){
                left++;
            }else if(!tr.matches("[a-z0-9]")){
                right--;
            }else if(s.charAt(left) != s.charAt(right)){
                return false;
            }else{
                left++;
                right--;
            }
        }
        return true;
    }
}

//方法2：将正则表达式替换为手动比较，正则表达式较慢
public class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while(left <= right){
            char tl = s.charAt(left);
            char tr = s.charAt(right);
            if((tl - 'a' < 0 || tl - 'a' > 25) && (tl - '0' < 0 || tl - '0' > 9)){
                left++;
            }else if((tr - 'a' < 0 || tr - 'a' > 25) && (tr - '0' < 0 || tr - '0' > 9)){
                right--;
            }else if(s.charAt(left) != s.charAt(right)){
                return false;
            }else{
                left++;
                right--;
            }
        }
        return true;
    }
}

//方法3：使用Character.isLetterOrDigit方法
public class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left <= right){
            char tl = s.charAt(left);
            char tr = s.charAt(right);
            if(!Character.isLetterOrDigit(tl)){
                left++;
            }else if(!Character.isLetterOrDigit(tr)){
                right--;
            }else if(Character.toLowerCase(tl) != Character.toLowerCase(tr)){
                return false;
            }else{
                left++;
                right--;
            }
        }
        return true;
    }
}



//541. Reverse String II
/*Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"*/
//方法1：依次选取第k个字符串，根据奇偶性决定是否翻转
public class Solution {
    public String reverseStr(String s, int k) {
        StringBuilder res = new StringBuilder();
        int f = 0;
        int len = s.length();
        while(res.length() < len){
            StringBuilder temp = new StringBuilder(s.substring(f * k, Math.min(len, f * k + k)));
            if(f % 2 == 0){
                temp = temp.reverse(); 
            }
            res.append(temp);
            f++;
        }
        return res.toString();
    }
}



//557. Reverse Words in a String III
/*Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"*/
//方法1：先整体翻转，再分割，最后逆序组合
public class Solution {
    public String reverseWords(String s) {
        StringBuilder s1 = new StringBuilder(s);
        s = s1.reverse().toString();
        String[] s2 = s.split(" ");
        StringBuilder res = new StringBuilder();
        for(int len = s2.length, i = len - 1; i >= 0; i--){
            res.append(s2[i]);
            if(i != 0) res.append(" ");
        }
        return res.toString();
    }
}


//6. ZigZag Conversion
/*The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".*/

class Solution {
    public String convert(String s, int numRows) {
    	//也可将String转换为char[]
    	//char[] ss = s.toCharArray();
        int len = s.length();
        if(len <= 1) return s;
        StringBuilder[] temp = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i++){
        	temp[i] = new StringBuilder();//如果不初始化会报错
        	
        }
        int en = 0;
        while(en < len){
            for(int i = 0; i < numRows && en < len; i++){
                temp[i].append(s.charAt(en++));
            }
            for(int i = numRows - 2; i >= 1 && en < len; i--){
                temp[i].append(s.charAt(en++));
            }
        }
        for(int i = 1; i < numRows; i++){
            temp[0].append(temp[i]);
        }
        return temp[0].toString();
    }
}


//345. Reverse Vowels of a String
/*Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".

*/

class Solution {
    public String reverseVowels(String s) {
        String Vowels = "aeiouAEIOU";
        int len = s.length();
        if(len <= 1) return s;
        StringBuilder ss = new StringBuilder(s);
        int left = 0;
        int right = len - 1;
        while(left < right){
            while(left < right && !Vowels.contains(s.charAt(left) + "")){
                left++;
            }
            while(left < right && !Vowels.contains(s.charAt(right) + "")){
                right--;
            }
            String temp = s.charAt(left) + "";
            ss.replace(left, left + 1, s.charAt(right) + "");
            ss.replace(right, right + 1, temp);
            left++;
            right--;
        }
        return ss.toString();
    }
}


//551. Student Attendance Record I
/*You are given a string representing an attendance record for a student. The record only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False*/
class Solution {
    public boolean checkRecord(String s) {
        int A = 0;
        int c = 0;
        for(int i = 0, len = s.length(); i < len; i++){
            if(s.charAt(i) == 'A'){
                A++;
                if(A == 2) return false;
                c = 0;
            }else if(s.charAt(i) == 'L'){
                c++;
                if(c == 3) return false;
            }else{
                c = 0;
            }
        }
        return true;
    }
}


//38. Count and Say
/*The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:

Input: 1
Output: "1"
Example 2:

Input: 4
Output: "1211"*/

//仔细分析题意，边界条件，注意测试
//遍历字符串，计数连续出现的字符，即可
class Solution {
    public String countAndSay(int n) {
        int num = 1;
        String now = "1";
        StringBuilder pre = new StringBuilder();
        while(num < n){
            int count = 1;
            char p = now.charAt(0);
            for(int i = 1; i < now.length(); i++){
                if(now.charAt(i) == p){
                    count++;
                }else{
                    pre.append(count);
                    pre.append(p);
                    count = 1;
                    p = now.charAt(i);
                }
            }
            pre.append(count);
            pre.append(p);
            now = pre.toString();
            pre = new StringBuilder();
            num++;
        }
        return now;
    }
}


//383. Ransom Note
/*Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true*/
//方法1：用26个字母数组计数（更快）
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] abc = new int[26];
        if(ransomNote.length() > magazine.length()) return false;
        for(int i = 0, len = magazine.length(); i < len; i++){
            abc[magazine.charAt(i) - 'a']++;
        }
        for(int i = 0, len = ransomNote.length(); i < len; i++){
            abc[ransomNote.charAt(i) - 'a']--;
            if(abc[ransomNote.charAt(i) - 'a'] < 0) return false;
        }
        return true;
    }
}

//方法2：用map数据结构
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length()) return false;
        Map<Character, Integer> abc = new HashMap<>();
        for(int i = 0, len = magazine.length(); i < len; i++){
            if(abc.containsKey(magazine.charAt(i))){
                abc.put(magazine.charAt(i), abc.get(magazine.charAt(i)) + 1);
            }else{
                abc.put(magazine.charAt(i), 1);
            }
        }
        for(int i = 0, len = ransomNote.length(); i < len; i++){
            if(abc.containsKey(ransomNote.charAt(i))){
                abc.put(ransomNote.charAt(i), abc.get(ransomNote.charAt(i)) - 1);
                if(abc.get(ransomNote.charAt(i)) < 0) return false;
            }else{
                return false;
            }
        }
        return true;
    }
}


//657. Judge Route Circle
class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        for(int i = 0, len = moves.length(); i < len; i++){
            char m = moves.charAt(i);
            if(m == 'R'){
                x++;
            }else if(m == 'L'){
                x--;
            }else if(m == 'U'){
                y++;
            }else{
                y--;
            }
        }
        return x == 0 && y == 0 ? true : false;
    }
}


//434. Number of Segments in a String
/*Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.*/

class Solution {
    public int countSegments(String s) {
        s = s.trim();//先去除收尾空格
        if(s.length() == 0) return 0;
        String[] ss = s.split("\\s+");//空格及多个空格,头部的空格会分成一个空的string
        return ss.length;
    }
}


//520. Detect Capital
/*Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.*/
class Solution {
    public boolean detectCapitalUse(String word) {
        if(word.charAt(0) >= 'a' && word.charAt(0) <= 'z'){//先对首字母判断
            for(int i = 1, len = word.length(); i < len; i++){
                if(word.charAt(i) < 'a') return false;
            }
            return true;
        }else{
            if(word.length() == 1){
                return true;
            }else{
                for(int i = 2, len = word.length(); i < len; i++){
                	//再将所有字母与第二个字母判断是否为同一类型
                    if((word.charAt(1) - 'Z' - 1) * (word.charAt(i) - 'Z' - 1) < 0) return false;
                }
                return true;
            }
        }
    }

}



//521. Longest Uncommon Subsequence I
/*Given a group of two strings, you need to find the longest uncommon subsequence of this group of two strings. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.

A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

The input will be two strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.*/
//只要两个字符串不相同，就不能通过任意一个导出另一个
class Solution {
    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
}



//3. Longest Substring Without Repeating Characters
/*Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.*/
//方法1：双指针，从左往右，不重复则right+1，重复则left+1
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if(len == 0) return 0;
        int max = 1;
        int left = 0;
        int right = left + 1;
        while(left < len && right < len){
            if(left < right && s.substring(left, right).contains(s.charAt(right) + "")){
                left++;
            }else{
                max = Math.max(max, right - left + 1);
                right++;
            }
        }
        return max;
    }
}

//方法2：用set数据结构
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if(len == 0) return 0;
        int max = 1;
        int left = 0;
        int right = left + 1;
        Set<Character> sta = new HashSet<>();
        sta.add(s.charAt(0));
        while(left < len && right < len){
            if(left < right && sta.contains(s.charAt(right))){
                sta.remove(s.charAt(left++));
            }else{
                max = Math.max(max, right - left + 1);
                sta.add(s.charAt(right++));
            }
        }
        return max;
    }
}

//方法3：用map数据结构
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if(len == 0) return 0;
        int max = 1;
        int left = 0;
        int right = left;
        Map<Character, Integer> sta = new HashMap<>();
        while(right < len){
            if(sta.containsKey(s.charAt(right))){
                left = Math.max(left, sta.get(s.charAt(right)) + 1);//此处注意，因为失效的没有删除，所以必须有比较
            }
            max = Math.max(max, right - left + 1);
            sta.put(s.charAt(right), right);
            right++;
        }
        return max;
    }
}

//5. Longest Palindromic Substring
/*Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:*/
//方法1：二维动态规划
class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len == 0) return s;
        int max = 1;
        String res = s.charAt(0) + "";
        boolean[][] f = new boolean[len][len];
        
        for(int i = len - 2; i >= 0; i--){
            for(int j = i + 1; j < len; j++){
                if(s.charAt(i) == s.charAt(j) && (f[i + 1][j - 1] || i + 1 >= j - 1)){
                    if(j - i + 1 > max){
                        max = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                    f[i][j] = true;
                }
            }
        }
        return res;
    }
}

//方法2：中心扩展法，以其中一个字母或空格为中心向两边扩展（有剪枝，比动态规划更快）
class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len <= 1) return s;
        double max = 1;
        String res = s.charAt(0) + "";
        for(double i = 0.5; i <= len - 1; i += 0.5){
            double left;
            double right;
            boolean f;
            if(Math.round(i) == i){
                left = i - 1;
                right = i + 1;
                f = true;
            }else{
                left = i - 0.5;
                right = i + 0.5;
                f = false;
            }
            while(left >= 0 && right < len){
                if(s.charAt((int)left) == s.charAt((int)right)){
                    if(right - left + 1 > max){
                        max = right - left + 1;
                        res = s.substring((int)left,(int) right + 1);
                    }
                    left--;
                    right++;
                }else{
                    break;
                }
            }
        }
        return res;
    }
}

//方法3：Manacher 解法还未理解 http://www.cnblogs.com/bitzhuwei/p/Longest-Palindromic-Substring-Part-II.html



//647. Palindromic Substrings
/*Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".*/
//方法1：动态规划，与第5题类似
class Solution {
    public int countSubstrings(String s) {
        int len = s.length();
        if(len == 0) return 0;
        int count = 0;
        boolean[][] f = new boolean[len][len];
        
        for(int i = len - 2; i >= 0; i--){
            for(int j = i + 1; j < len; j++){
                if(s.charAt(i) == s.charAt(j) && (f[i + 1][j - 1] || i + 1 >= j - 1)){
                    count++;
                    f[i][j] = true;
                }
            }
        }
        return count + len;
    }
}

//方法2：中心扩展法
class Solution {
    public int countSubstrings(String s) {
        int len = s.length();
        if(len <= 1) return len;
        int count = 0;
        for(double i = 0.5; i <= len - 1; i += 0.5){
            double left;
            double right;
            boolean f;
            if(Math.round(i) == i){
                left = i - 1;
                right = i + 1;
                f = true;
            }else{
                left = i - 0.5;
                right = i + 0.5;
                f = false;
            }
            while(left >= 0 && right < len){
                if(s.charAt((int)left) == s.charAt((int)right)){
                    count++;
                    left--;
                    right++;
                }else{
                    break;
                }
            }
        }
        return count + len;
    }
}

//方法3：


//17. Letter Combinations of a Phone Number
/*Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].*/
//方法1：递归，回溯法(better)
class Solution {
    List<String> res = new LinkedList<>();
    StringBuilder temp = new StringBuilder();
    String[] abc = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return res;
        help(digits, 0);
        return res;
    }
    
    public void help(String digits, int start){
        if(temp.length() == digits.length()){
            res.add(temp.toString());
            return;
        }
        for(int i = 0; i < abc[digits.charAt(start) - '0'].length(); i++){
            temp.append(abc[digits.charAt(start) - '0'].charAt(i));
            help(digits, start + 1);
            temp.delete(temp.length() - 1, temp.length());//可以把temp作为形参，这样就不用显式删除
        }
    }
}

//方法2：FIFO队列，从1个数开始，不断往上组合
//a b c
//ad ae af bd be bf cd ce cf
class Solution {
    LinkedList<String> res = new LinkedList<>();
    String[] abc = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return res;
        res.add("");
        for(int i = 0, len = digits.length(); i < len; i++){
            int number = digits.charAt(i) - '0';
            while(res.peek().length() == i){
                String temp = res.remove();
                for(int j = 0; j < abc[number].length(); j++){
                    res.add(temp + abc[number].charAt(j));
                }
            }
        }
        return res;
    }
}



//22. Generate Parentheses
/*Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]*/
//方法1：超时
class Solution {
    List<String> res = new LinkedList<>();
    public List<String> generateParenthesis(int n) {
        if(n == 0) return res;
        help(n, 1, "()");
        return res;
    }
    public void help(int n, int now, String temp){
        if(now >= n){
            if(!res.contains(temp)) res.add(new String(temp));
            return;
        }
        for(int i = 0, len = temp.length(); i < len; i++){
            if(i == 0){
                help(n, now + 1, "()" + temp);
            }
            if(i == len - 1){
                help(n, now + 1, temp + "()");
            }
            if(temp.charAt(i) == '('){
                help(n, now + 1, temp.substring(0, i + 1) + "()" + temp.substring(i + 1, len));
            }
        }
    }
}

//方法2：
class Solution {
    List<String> res = new LinkedList<>();
    public List<String> generateParenthesis(int n) {
        if(n == 0) return res;
        help("", n, n);
        return res;
    }
    public void help(String temp, int left, int right){
        if(right == 0){
            res.add(new String(temp));
            return;
        }
        if(left > 0){
            help(temp + "(", left - 1, right);
        }
        if(right > left){//确保每一时刻左括号的数量多于右括号，则一定是有效的
            help(temp + ")", left, right - 1);
        }
    }
}



//43. Multiply Strings
/*Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.*/

//方法1：大数据类型
class Solution {
    public String multiply(String num1, String num2) {
    	java.math.BigInteger res = new java.math.BigInteger("0"); 
        int len1 = num1.length();
        int len2 = num2.length();
        if(len1 == 0 || len2 == 0) return "0";
        for(int i = 0; i < len2; i++){
        	java.math.BigInteger temp =new java.math.BigInteger("0"); 
            int num = num2.charAt(i) - '0';
            for(int j = 0; j < len1; j++){
                int n = num1.charAt(j) - '0';
                temp = temp.multiply(new java.math.BigInteger("10")).add(new java.math.BigInteger(Integer.toString(num * n)));
            }
            
            res = res.multiply(new java.math.BigInteger("10")).add(temp);
        }
//        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
//        nf.setGroupingUsed(false); 
//        String r = nf.format(res) + "";
        
        return res.toString();
    }
}

//方法2：利用每两个个位数相乘乘积最多为两位数，一次来分别求出每一位上的数字
class Solution {
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        if(len1 == 0 || len2 == 0) return "0";
        int[] sum = new int[len1 + len2];//提前分配足够的空间，以空间换时间
        for(int i = len1 - 1; i >= 0; i--){
             int n1 = num1.charAt(i) - '0';
            for(int j = len2 - 1; j >= 0; j--){
                int n2 = num2.charAt(j) - '0';
                int temp = n1 * n2 + sum[i + j + 1];
                sum[i + j + 1] = temp % 10;//个位位置上的数
                sum[i + j] += temp / 10;//十位位置上的数（最高为一定不会溢出，除最高位，最低位外，其余均访问两次，不会造成某一位上有两位数而没有正确进位）
            }
        }
        StringBuilder res = new StringBuilder();
        for(int c : sum){
            if(c != 0 || res.length() != 0){//此处非常巧妙，从第一个非零开始的数进行连接
                res.append(c);
            }
        }
        return res.length() == 0 ? "0" : res.toString();
    }
}



//583. Delete Operation for Two Strings
/*Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".*/

//方法1：动态规划，找出最长公共子序列，再分别减去LCS长度相加即可
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] sta = new int[len1 + 1][len2 + 1];
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    sta[i][j] = sta[i - 1][j - 1] + 1;
                }else{
                    sta[i][j] = Math.max(sta[i - 1][j], sta[i][j - 1]);
                }
            }
        }
        return len1 + len2 - 2 * sta[len1][len2];
    }
}



//165. Compare Version Numbers
/*Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37*/

//所举例子带有很强的局限性和误导性，不要被例子所局限
//方法1：以“.”为分割，逐位比较，注意越界
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len1 = v1.length;
        int len2 = v2.length;
        int len = Math.max(len1, len2);
        for(int i = 0; i < len; i++){
            if(i < len1 && i < len2){
                if(Integer.parseInt(v1[i]) > Integer.parseInt(v2[i])){
                    return 1;
                }else if(Integer.parseInt(v1[i]) < Integer.parseInt(v2[i])){
                    return -1;
                }
            }else if(i < len1){
                if(Integer.parseInt(v1[i]) != 0){
                    return 1;
                }
            }else if(i < len2){
                if(Integer.parseInt(v2[i]) != 0){
                    return -1;
                }
            }
        }
        return 0;
    }
}

//方法2：原理类似，结构简化
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len1 = v1.length;
        int len2 = v2.length;
        int len = Math.max(len1, len2);
        for(int i = 0; i < len; i++){
            int n1 = i < len1 ? Integer.parseInt(v1[i]) : 0;
            int n2 = i < len2 ? Integer.parseInt(v2[i]) : 0;
            if(n1 > n2){
                return 1;
            }else if(n1 < n2){
                return -1;
            }
        }
        return 0;
    }
}



//556. Next Greater Element III
/*Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:
Input: 12
Output: 21
Example 2:
Input: 21
Output: -1*/

//方法1：
class Solution {
	public int nextGreaterElement(int n) {
		String s = n + "";
		for (int i = s.length() - 2; i >= 0; i--) {
			int min = Integer.MAX_VALUE;
			int sta = 0;
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) - '0' < s.charAt(j) - '0' && s.charAt(j) - '0' < min) {
					min = s.charAt(j) - '0';//找到逆序的数，并记录位置
					sta = j;
				}
			}
			if (min != Integer.MAX_VALUE) {
				StringBuilder res = new StringBuilder();
				res.append(s.substring(0, i));
				res.append(s.charAt(sta) - '0');
				StringBuilder res1 = new StringBuilder();
				res1.append(s.substring(i + 1, sta));
				res1.append(s.charAt(i) - '0');
				res1.append(s.substring(sta + 1, s.length()));
				char[] c = res1.toString().toCharArray();
				Arrays.sort(c);//剩余的数按升序排列
				for (char c1 : c) {
					res.append(c1);
				}
				if (Double.parseDouble(res.toString()) <= Integer.MAX_VALUE)
					return Integer.parseInt(res.toString());
			}
		}
		return -1;
	}
}


//496. Next Greater Element I
/*You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.*/

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            res[i] = -1;
            for(int j = 0; j < nums2.length; j++){
                if(nums1[i] == nums2[j]){
                    for(int k = j + 1; k < nums2.length; k++){
                        if(nums2[k] > nums2[j]){
                            res[i] = nums2[k];
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return res;
    }
}



//503. Next Greater Element II
/*Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.*/

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        for(int i = 0; i < res.length; i++){
            res[i] = -1;
            boolean find = false;
            for(int j = i + 1; j < res.length; j++){
                if(nums[j] > nums[i]){
                    res[i] = nums[j];
                    find = true;
                    break;
                }
            }
            if(!find){
                for(int j = 0; j < i; j++){
                    if(nums[j] > nums[i]){
                        res[i] = nums[j];
                        break;
                    }
                }
            }
        }
        return res;
    }
}


//227. Basic Calculator II
/*Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5*/
//方法1：先算乘除，再算加减，注意数可能是多位的，注意判断，注意空格
class Solution {
    public int calculate(String s) {
        Stack<Character> sig = new Stack<>();
        Stack<Integer> num = new Stack<>();
        for(int i = 0, len = s.length(); i < len; i++){
            char temp = s.charAt(i);
            if(temp == '+' || temp == '-'){
                sig.push(temp);
            }else if(temp - '0' <= 9 && temp - '0' >= 0){
                int n = temp - '0';
                i++;
                while(i < len && s.charAt(i) - '0' <= 9 && s.charAt(i) - '0' >= 0){
                    n = n * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                num.push(n);
            }else if(temp == '*'){
                int a = num.pop();
                while(s.charAt(++i) == ' '){}
                int b = s.charAt(i) - '0';
                i++;
                while(i < len && s.charAt(i) - '0' <= 9 && s.charAt(i) - '0' >= 0){
                    b = b * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;                
                num.push(a * b);
            }else if(temp == '/'){
                int a = num.pop();
                while(s.charAt(++i) == ' '){}
                int b = s.charAt(i) - '0';
                i++;
                while(i < len && s.charAt(i) - '0' <= 9 && s.charAt(i) - '0' >= 0){
                    b = b * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                num.push(a / b);                
            }
        }
        Collections.reverse(sig);
        Collections.reverse(num);
        int res = 0;
        while(!sig.isEmpty()){
            if(sig.pop() == '+'){
                int a = num.pop();
                int b = num.pop();
                num.push(a + b);
            }else{
                int a = num.pop();
                int b = num.pop();
                num.push(a - b);
            }
        }
        return num.pop();
    }
}

//方法2：先分割
class Solution {
    public int calculate(String s) {
        Stack<Integer> num = new Stack<>();
        s = s.trim();
        String[] ss = s.split("\\D+");//现将数字单独分割出来
        int point = 0;
        num.push(Integer.parseInt(ss[point++]));
        for(int i = 0, len = s.length(); i < len; i++){
            char temp = s.charAt(i);
            if(temp == '+'){
                num.push(Integer.parseInt(ss[point++]));
            }else if(temp == '-'){
                num.push(Integer.parseInt(ss[point++]) * (-1));//将减操作符转为负号，最后直接相加即可
            }else if(temp == '*'){
                int a = num.pop();
                int b = Integer.parseInt(ss[point++]);
                num.push(a * b);
            }else if(temp == '/'){
                int a = num.pop();
                int b = Integer.parseInt(ss[point++]);
                num.push(a / b);
            }
        }
        int res = 0;
        for(int c : num){
            res += c;
        }
        return res;
    }
}



//151. Reverse Words in a String
/*Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".*/

public class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        if(s.length() == 0) return s;
        String[] ss = s.split(" +");
        StringBuilder res = new StringBuilder();
        for(int i = ss.length - 1; i >=0; i--){
            if(i != 0){
                res.append(ss[i]);
                res.append(" ");
            }else{
                res.append(ss[i]);
            }
        }
        return res.toString();
    }
}



//539. Minimum Time Difference
/*Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.

Example 1:
Input: ["23:59","00:00"]
Output: 1*/

//方法1：先将时间转换为分钟表示，再比较
class Solution {
    public int findMinDifference(List<String> timePoints) {
    	if(timePoints.size() >= 60 *24 || timePoints.size() == 0) return 0;//如果大于时间总数，则一定有重复的时间
        List<Integer> time = new LinkedList<>();
        for(String s : timePoints){
            String[] temp = s.split("\\:");
            time.add(Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]));
        }
        Collections.sort(time);
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < time.size(); i++){
            min = Math.min(min, time.get(i) - time.get(i - 1));
            if(min == 0) return 0;
        }
        if(time.get(0) < min){
            min = Math.min(min, 24 * 60 - time.get(time.size() - 1) + time.get(0));
        }
        return min;
    }
}


//537. Complex Number Multiplication
/*Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.*/
//方法1：分割之后分别求出实部和虚部
class Solution {
    public String complexNumberMultiply(String a, String b) {
        String[] aa = a.split("\\+");
        String[] bb = b.split("\\+");
        String ac = aa[1].split("i")[0];
        String bc = bb[1].split("i")[0];
        int r1 = Integer.parseInt(aa[0]) * Integer.parseInt(bb[0]);
        int r2 = Integer.parseInt(ac) * Integer.parseInt(bc) * (-1) + r1;
        int r3 = Integer.parseInt(aa[0]) * Integer.parseInt(bc) + Integer.parseInt(bb[0]) * Integer.parseInt(ac);
        return "" + r2 + "+" + r3 + "i";
    }
}


//93. Restore IP Addresses
/*Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

*/
//方法1：dfs回溯法，遍历每个"."可能出现的位置
class Solution {
    List<String> res = new LinkedList<>();
    public List<String> restoreIpAddresses(String s) {
        if(s.length() < 4 || s.length() > 12) return res;
        help(s, "", -1, 0);
        return res;
    }
    
    public void help(String s, String now, int sta, int num){
        if(num == 3){
            if(Double.parseDouble(s.substring(sta + 1, s.length())) <= 255 && isValid(now + s.substring(sta + 1, s.length()))){
                res.add(now + s.substring(sta + 1, s.length()));
            }
            return;
        }
        for(int i = sta + 1; i < s.length() - 1; i++){
            if(Double.parseDouble(s.substring(sta + 1, i + 1)) <= 255){
                help(s, now + s.substring(sta + 1, i + 1) + ".", i, num + 1);
            }else{
                break;
            }
        }
    }
    
    public boolean isValid(String ip){//用于判断ip地址是否合法，主要是前导零
        String[] ips = ip.split("\\.");
        for(int i = 0; i < 4; i++){
            String temp = ips[i];
            if(temp.length() > 1 && temp.charAt(0) == '0'){
                return false;
            }
        }
        return true;
    }
}



//76. Minimum Window Substring
/*Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".*/

//方法1：双指针，并用map记录字符出现次数，并直接记录等于0的数量
class Solution {
    public String minWindow(String s, String t) {
        int min = Integer.MAX_VALUE;
        String res = "";
        if(s.length() < t.length()) return res;
        int left = 0;
        int right = 0;
        Map<Character, Integer> sta = new HashMap<>();
        int numofzero = 0;
        char[] tt = t.toCharArray();
        for(char t2 : tt){
            if(sta.containsKey(t2)){
                sta.put(t2, sta.get(t2) + 1);
            }else{
                sta.put(t2, 1);
            }
        }
        while(right <= s.length()){
            if(numofzero == sta.size()){
                if(right - left < min){
                    min = right - left;
                    res = s.substring(left, right);
                    if(min == t.length()) break;
                }
                if(sta.containsKey(s.charAt(left))){
                    sta.put(s.charAt(left), sta.get(s.charAt(left)) + 1);
                    if(sta.get(s.charAt(left)) == 1){
                        numofzero--;
                    }   
                }
                left++;
            }else{
                if(right < s.length() && sta.containsKey(s.charAt(right))){
                    sta.put(s.charAt(right), sta.get(s.charAt(right)) - 1);
                    if(sta.get(s.charAt(right)) == 0){
                        numofzero++;
                    }
                }
                right++;
            }
        }
        return res;
    }
}

//方法2：原理同方法1，用set和数组代替map（速度更快）
class Solution {
    public String minWindow(String s, String t) {
        int min = Integer.MAX_VALUE;
        String res = "";
        if(s.length() < t.length()) return res;
        int left = 0;
        int right = 0;
        Set<Character> sta = new HashSet<>();
        int[] num = new int[58];
        int numofzero = 0;
        char[] tt = t.toCharArray();
        for(char t2 : tt){
            sta.add(t2);
            num[t2 - 'A']++;
            
        }
        while(right <= s.length()){
            if(numofzero == sta.size()){
                if(right - left < min){
                    min = right - left;
                    res = s.substring(left, right);
                    if(min == t.length()) break;
                }
                if(sta.contains(s.charAt(left))){
                    num[s.charAt(left) - 'A']++;
                    if(num[s.charAt(left) - 'A'] == 1){
                        numofzero--;
                    }   
                }
                left++;
            }else{
                if(right < s.length() && sta.contains(s.charAt(right))){
                    num[s.charAt(right) - 'A']--;
                    if(num[s.charAt(right) - 'A'] == 0){
                        numofzero++;
                    }
                }
                right++;
            }
        }
        return res;
    }
}

//方法3：双指针，且只用数组
class Solution {
    public String minWindow(String s, String t) {
        int min = Integer.MAX_VALUE;
        if(s.length() < t.length() || s.length() == 0 || t.length() == 0) return "";
        int left = 0;
        int right = 0;
        int rl = 0;
        int rr = 0;
        int[] num = new int[58];
        int numofzero = 0;
        char[] tt = t.toCharArray();
        for(char t2 : tt){
            if(num[t2 - 'A']++ == 0) numofzero++;//特定的等于0时，计数才改变，取代了set和map的部分功能
            
        }
        while(right <= s.length()){
            if(numofzero == 0){
                if(right - left < min){
                    min = right - left;
                    rl = left;
                    rr = right;
                    if(min == t.length()) break;
                }
                if(num[s.charAt(left) - 'A']++ == 0) numofzero++;
                left++;
            }else{
                if(right < s.length() && --num[s.charAt(right) - 'A'] == 0){
                    numofzero--;
                }
                right++;
            }
        }
        return s.substring(rl, rr);
    }
}



//567. Permutation in String
/*Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").*/

//方法1：与上题思路相同
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s2.length() < s1.length()) return false;
        int[] abc = new int[26];
        char[] c1 = s1.toCharArray();
        int nimOfZero = 0;
        for(char c : c1){
            if(abc[c - 'a']++ == 0) nimOfZero++;
        }
        int left = 0;
        int right;
        for(right = 0; right <= left + s1.length() - 1; right++){
            if(--abc[s2.charAt(right) - 'a'] == 0) nimOfZero--;
        }
        right--;//此处注意，如果不减一，会漏掉一个字母
        while(right < s2.length()){
            if(nimOfZero == 0){
                return true;
            }else if(right + 1 < s2.length()){
                if(abc[s2.charAt(left) - 'a']++ == 0) nimOfZero++;
                if(--abc[s2.charAt(right + 1) - 'a'] == 0) nimOfZero--;
            }
            left++;
            right++;
        }
        return false;
    }
}


//438. Find All Anagrams in a String
/*Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".*/

//方法1：原理同上题
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        if(s.length() < p.length()) return res;
        int[] abc = new int[26];
        char[] pp = p.toCharArray();
        int numOfZero = 0;
        for(char c : pp){
            if(abc[c - 'a']++ == 0) numOfZero++;
        }
        int left = 0;
        int right;
        for(right = 0; right <= left + p.length() - 1; right++){
            if(--abc[s.charAt(right) - 'a'] == 0) numOfZero--;
        }
        right--;
        while(right < s.length()){
            if(numOfZero == 0){
                res.add(left);
            }
            if(right + 1 < s.length()){
                if(abc[s.charAt(left) - 'a']++  == 0) numOfZero++;
                if(--abc[s.charAt(right + 1) - 'a']  == 0) numOfZero--;
            }
            left++;
            right++;
        }
        return res;
    }
}



//10. Regular Expression Matching
/*'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/
//方法1：逻辑较乱，东拼西凑
class Solution {
    boolean res = false;
    char pre;
    public boolean isMatch(String s, String p) {
        if(p.equals(".*") || s.equals(p)) return true;
        help(s, p, 0, 0);
        return res;
    }
    public boolean isNothing(String ss){
        for(int i = 0; i < ss.length(); i++){
            if(ss.charAt(i) != '*'){
                if(i + 1 >= ss.length() || ss.charAt(i + 1) != '*'){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void help(String s, String p, int sts, int stp){
        if(res) return;
        if(sts >= s.length() || stp >= p.length()){
            if(sts >= s.length() && stp >= p.length()){
                res = true;
            }
            if(sts >= s.length() && isNothing(p.substring(stp, p.length()))){
                res = true;
            }
            return;
        }
        if(p.charAt(stp) != '*'){
            if(stp + 1 < p.length() && p.charAt(stp + 1) == '*')
               help(s, p, sts, stp + 2);
        }
        if(p.charAt(stp) == s.charAt(sts)){
            help(s, p, sts + 1, stp + 1);
        }else if(p.charAt(stp) == '.'){
            if(stp + 1 < p.length() && p.charAt(stp + 1) == '*'){
                for(int i = 0; i + sts <= s.length(); i++){
                    help(s, p, sts + i, stp + 2);
                }
            }
            pre = s.charAt(sts);
            help(s, p, sts + 1, stp + 1);
            
        }else if(p.charAt(stp) == '*'){
            for(int i = 0; i + sts <= s.length(); i++){
                String temps = s.substring(sts, sts + i);
                StringBuilder tempp = new StringBuilder();
                for(int j = 0; j < i; j++){
                    if(p.charAt(stp - 1) == '.'){
                        tempp.append(pre);
                    }else{
                        tempp.append(p.charAt(stp - 1));
                    }
                }
                if(tempp.toString().equals(temps))
                    help(s, p, sts + i, stp + 1);
            }
        }

    }
}


//方法2：递归调用自身，不断缩减字符串长度
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.length() == 0 && p.length() == 0) return true;
        if(s.equals(p) || p.equals(".*")) return true;
        if(p.length() == 0){//p == 0
            return false;
        }else{//p != 0
            if(s.length() == 0){//s == 0
                return p.length() > 1 && p.charAt(1) == '*' && isMatch(s, p.substring(2));
            }else{//s != 0
                if(s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'){
                    if(p.length() > 1 && p.charAt(1) == '*'){
                        return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
                    }
                    return isMatch(s.substring(1), p.substring(1));
                }else if(p.length() > 1 && p.charAt(1) == '*'){
                    return isMatch(s, p.substring(2));
                }else{
                    return false;
                }
            }
        }
    }
}

//方法3：动态规划
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.length() == 0 && p.length() == 0) return true;
        if(s.equals(p) || p.equals(".*")) return true;
        boolean[][] res = new boolean[s.length() + 1][p.length() + 1];
        res[0][0] = true;
        for(int i = 0; i < p.length(); i++){
            if(p.charAt(i) == '*' && res[0][i - 1]){
                res[0][i + 1] = true;//先将所有s==0的值为true
            }
        }
        
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < p.length(); j++){
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
                    res[i + 1][j + 1] = res[i][j];
                }else if(p.charAt(j) == '*'){
                    if(p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.'){//前一位不相等时
                        res[i + 1][j + 1] = res[i + 1][j - 1];
                    }else{//前一位相等时.a*为..........empty..............a..............多个a
                        res[i + 1][j + 1] = res[i + 1][j - 1] || res[i][j + 1] || res[i + 1][j];
                    }
                }
            }
        }
        return res[s.length()][p.length()];
    }
}



//44. Wildcard Matching
/*Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false*/

//方法1：动态规划（比上题简单）
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.length() == 0 && p.length() == 0) return true;
        boolean[][] res = new boolean[s.length() + 1][p.length() + 1];
        res[0][0] = true;
        for(int i = 0; i < p.length(); i++){
            if(p.charAt(i) == '*' && res[0][i])
                res[0][i + 1] = true;
        }
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < p.length(); j++){
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
                    res[i + 1][j + 1] = res[i][j];
                }else if(p.charAt(j) == '*'){
                    res[i + 1][j + 1] = res[i + 1][j] || res[i][j] || res[i][j + 1];
                }else{
                    res[i + 1][j + 1] = false;
                }
            }
        }
        return res[s.length()][p.length()];
    }
}



//53. Maximum Subarray
/*Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.*/

//方法1：动态规划
class Solution {
    public int maxSubArray(int[] nums) {
        int[] res = new int[nums.length + 1];
        res[0] = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            res[i + 1] = Math.max(res[i], 0) + nums[i];
            max = Math.max(max, res[i + 1]);
        }
        return max;
    }
}



//30. Substring with Concatenation of All Words
/*You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].*/

//方法1：用map记录出现次数
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList<>();
        if(words.length == 0 || words[0].length() == 0) return res;
        int len = words[0].length();
        if(s.length() < words.length * len) return res;
        Map<String, Integer> sta = new HashMap<>();
        for(String w : words){
            if(sta.containsKey(w)){
                sta.put(w, sta.get(w) + 1);
            }else{
                sta.put(w, 1);
            }
        }
        
        for(int i = 0; i + words.length * len <= s.length(); i++){
            Map<String, Integer> abc = new HashMap<>(sta);
            int num = 0;
            for(int j = 0; j < words.length; j++){
                String temp = s.substring(i + j * len, i + j * len + len);
                if(abc.containsKey(temp)){
                    abc.put(temp, abc.get(temp) - 1);
                    if(abc.get(temp) == 0) num++;
                    if(abc.get(temp) < 0) break;
                }else{
                    break;
                }
            }
            if(abc.size() == num) res.add(i);
        }
        return res;
    }
}



///32. Longest Valid Parentheses
/*Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

*/
//方法1：动态规划
class Solution {
    public int longestValidParentheses(String s) {
        int len = s.length();
        if(len == 0) return 0;
        boolean[] mark = new boolean[len];
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == '('){
                mark[i] = false;
            }else{
                mark[i] = false;
                for(int j = i - 1; j >= 0; j--){
                    if(s.charAt(j) == '(' && mark[j] == false){
                        mark[j] = true;
                        mark[i] = true;
                        break;
                    }
                }
            }
        }
        int max = 0;
        int now = 0;
        for(boolean m : mark){//找连续最长的true
            if(m){
                now++;
                max = Math.max(max, now);
            }else{
                now = 0;
            }
        }
        return max;
    }
}



//214. Shortest Palindrome
/*Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".*/
//方法1：双指针，向两边移动，如果左指针小于0，则此时翻转右侧剩余字符串即可
class Solution {
    public String shortestPalindrome(String s) {
        if(s.length() == 0 || s.length() == 1) return s;
        int len = s.length();
        StringBuilder res = new StringBuilder(s);
        if(s.equals(res.reverse().toString())) return s;
        char[] ss = s.toCharArray();
        for(double i = (len - 1) >> 1; i >= 0; i -= 0.5){//从中间开始，每次移动0.5
            int left;
            int right;
            if(Math.round(i) == i){
                left = (int)(i - 1);
                right = (int)(i + 1);
            }else{
                left = (int)(i - 0.5);
                right = (int)(i + 0.5);
            }
            while(left >= 0 && ss[left] == ss[right]){
                left--;
                right++;
            }
            if(left < 0){
                StringBuilder temp = new StringBuilder(s.substring(right, len));
                temp = temp.reverse();
                return temp.toString() + s;
            }
        }
        return null;
    }
}

//方法2：KMP算法
class Solution {
    public String shortestPalindrome(String s) {
        if(s.length() == 0) return "";
        StringBuilder ss = new StringBuilder(s);
        ss = ss.reverse();
        return ss.substring(0, s.length() - Next(s)) + s;
    }
    
    public int Next(String s){
        StringBuilder s1= new StringBuilder(s);
        String ss = new StringBuilder(s).append("#").append(s1.reverse()).toString();//加上特殊符号，避免左边数组超出范围后与右边数组重叠
        int[] next = new int[ss.length()];
        for(int i = 1; i < ss.length(); i++){
            int j = next[i - 1];
            while(j >= 1 && ss.charAt(i) != ss.charAt(j)){
                j = next[j - 1];
            }
            if(j == 0){
                if(ss.charAt(i) == ss.charAt(0)){
                    next[i] = 1;
                }else{
                    next[i] = 0;
                }
            }else{
                    next[i] = j + 1;
           
            }
        }
        return next[ss.length() - 1];
    }
}

//KMP算法
public static int KMP(String s, String p){
	int lens = s.length();
	int lenp = p.length();
	int res = -1;
	int[] next = findNext(p);
	for(int i = 0; i < lens; i++){
		for(int j = 0; j < lenp && i < lens;){
			if(j == lenp - 1 && s.charAt(i) == p.charAt(j)){
				return i - j;
			}
			if(j == 0 && s.charAt(i) != p.charAt(j)){
				break;
			}
			if(s.charAt(i) == p.charAt(j)){
				j++;
				i++;
			}else{
				j = next[j - 1];
			}
		}
	}
	return res;
}

public static int[] findNext(String p){
	int lenp = p.length();
	int[] next = new int[lenp];
	for(int i = 1; i < lenp; i++){
		int j = next[i - 1];
		while(j >= 1 && p.charAt(i) != p.charAt(j)){
			j = next[j - 1];
		}
		if(j == 0){
			if(p.charAt(i) == p.charAt(j)){
				next[i] = 1;
			}else{
				next[i] = 0;
			}
		}else{
			next[i] = next[i - 1] + 1;
		}
	}
	return next;
}



//336. Palindrome Pairs
/*Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]*/

//方法1：用map建立索引，单次遍历数组
class Solution {
    List<List<Integer>>  res = new LinkedList<>();
    public List<List<Integer>> palindromePairs(String[] words) {
        int len = words.length;
        if(len == 0) return res;
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            map.put(words[i], i);
        }
        for(int i = 0; i < len; i++){
            String word = words[i];
            for(int j = 0; j <= word.length(); j++){
                String s1 = word.substring(0, j);//将一个单词分为前后两个部分
                String s2 = j == word.length() ? "" : word.substring(j);
                if(isP(s1)){
                    String temp = new StringBuilder(s2).reverse().toString();
                    if(map.containsKey(temp) && map.get(temp) != i){
                        List<Integer> r = new LinkedList<>();
                        r.add(map.get(temp));
                        r.add(i);
                        res.add(r);
                    }
                }
                if(isP(s2) && s2.length() != 0){//长度不能为0，否则出现重复
                    String temp = new StringBuilder(s1).reverse().toString();
                    if(map.containsKey(temp) && map.get(temp) != i){
                        List<Integer> r = new LinkedList<>();
                        r.add(i);
                        r.add(map.get(temp));
                        res.add(r);
                    }
                }
            }
        }
        return res;
    }
    
    public boolean isP(String s){
        StringBuilder t = new StringBuilder(s);
        return s.equals(t.reverse().toString());
    }
}



//71. Simplify Path
/*Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"*/
//方法1：使用stack，根据路径内容决定是添加还是弹出
class Solution {
    public String simplifyPath(String path) {
        String[] paths = path.split("\\/");
        Stack<String> sta = new Stack<>();
        for(String s : paths){
            if(s.length() != 0 && !s.equals(".")){
                if(s.equals("..")){
                    if(sta.size() != 0){
                        sta.pop();
                    }
                }else{
                    sta.push(s);
                }
            }
        }
        Collections.reverse(sta);
        StringBuilder res = new StringBuilder();
        while(!sta.isEmpty()){
            res.append("/").append(sta.pop());
        }
        return res.length() == 0 ? "/" : res.toString();
    }
}



//91. Decode Ways
/*A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.

*/
//方法1：动态规划
class Solution {
    public int numDecodings(String s) {
        if(s.length() == 0 || s.charAt(0) == '0') return 0;
        int[] res = new int[s.length()];//多加一位可以避免i<0时的判断
        res[0] = 1;
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i - 1) == '0' && s.charAt(i) == '0'){
                return 0;
            }else if(s.charAt(i) == '0'){
                if(s.charAt(i - 1) - '0' >= 3) return 0;
                if(i - 1 >= 1){
                    res[i] = res[i - 2];
                }else{
                    res[i] = 1;
                }
            }else if(s.charAt(i - 1) == '0'){
                res[i] = res[i - 1];
            }else{
                if(Integer.parseInt(s.substring(i - 1, i + 1)) <= 26){//当前两位的和在0和26之间时，res[i] = res[i - 2] + res[i - 1] 这是算法关键
                    res[i] = i - 2 >= 0 ? res[i - 2] + res[i - 1] : res[i - 1] + 1;
                }else{
                    res[i] = res[i - 1];
                }
            }
        }
        return res[s.length() - 1];
    }
}


//680. Valid Palindrome II
/*Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.*/

//方法1：循环加部分递归，防止栈溢出
class Solution {
    int time = 0;
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) == s.charAt(right)){
                left++;
                right--;
            }else{
                if(time == 0){
                    time = 1;
                    return validPalindrome(s.substring(left, right)) || validPalindrome(s.substring(left + 1, right + 1));
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}


//72. Edit Distance
/*Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character*/
//方法1：动态规划
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] res = new int[len1 + 1][len2 + 1];
        for(int i = 0; i <= len1; i++){//预先赋值
            res[i][0] = i;
        }
        for(int i = 0; i <= len2; i++){
            res[0][i] = i;
        }
        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                int temp1 = 0;
                if(word1.charAt(i) == word2.charAt(j)){
                    temp1= res[i][j];//保持不变
                }else{
                    temp1 = res[i][j] + 1;//修改最后一个
                }
                int temp2 = Math.min(res[i + 1][j], res[i][j + 1]) + 1;//增加一个或删除一个
                res[i + 1][j + 1] = Math.min(temp1, temp2);//取所有可能操作的最小值
            }
        }
        return res[len1][len2];
    }
}



//97. Interleaving String
/*Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.*/
//方法1：动态规划，用递归方法会超时
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if(len1 == 0 && len2 == 0 && len3 == 0) return true;
        if(len1 + len2 != len3) return false;
        boolean[][] res = new boolean[len1 + 1][len2 + 1];
        res[0][0] = true;
        for(int i = 1; i <= len1; i++){//两种特殊情况先赋值
            res[i][0] = res[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for(int i = 1; i <= len2; i++){
            res[0][i] = res[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){//增加的这一位，可能是s1加入的，也可能是s2加入的
                res[i][j] = (res[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) || (res[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ? true : false;
            }
        }
        return res[len1][len2];
    }
}


//70. Climbing Stairs
/*You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?*/
//方法1：动态规划，可以选择走一步或两步，所以res[i] = res[i-1]+res[i-2]
class Solution {
    public int climbStairs(int n) {
        if(n <= 1) return 1;
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        for(int i = 2; i <= n; i++){
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];
    }
}

//方法2：动态规划的简化版
class Solution {
    public int climbStairs(int n) {
        if(n <= 1) return 1;
        int a = 1;
        int b = 2;
        for(int i = 3; i <= n; i++){
            int c = a + b;//动态规划实际上也只用了三个变量，辗转相加
            a = b;
            b = c;
        }
        return b;
    }
}



//678. Valid Parenthesis String
/*Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.*/

//方法1：递归，判断括号是否合法，只需要判断左右括号数量是否相等，且在任意时刻，右括号数不大于左括号数
class Solution {
    public boolean checkValidString(String s) {
        return isValid(s, 0, 0);
    }
    
    public boolean isValid(String s, int start, int count){
        if(count < 0) return false;
        for(int i = start; i < s.length(); i++){
            if(s.charAt(i) == '('){
                count++;
            }else if( s.charAt(i) == ')'){
                count--;
                if(count < 0) return false;
            }else{//如果是"*",则有三种可能
                return isValid(s, i + 1, count + 1) || isValid(s, i + 1, count - 1) || isValid(s, i + 1, count);
            }
        }
        return count == 0;
    }
}




//121. Best Time to Buy and Sell Stock
/*Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.*/
//方法1：记录到目前位置的最小值，再判断此位置值减去最小值的差是否比存在的更小
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < prices.length; i++){
            if(prices[i] < min){
                min = prices[i];
            }else{
                res = Math.max(res, prices[i] - min);
            }
        }
        return res;
    }
}

//122. Best Time to Buy and Sell Stock II
/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).*/
//方法1：贪心算法（题目本身没意义）
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > prices[i - 1]) res += prices[i] - prices[i - 1];
        }
        return res;
    }
}


//309. Best Time to Buy and Sell Stock with Cooldown
/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)*/

//方法1：递归，设定当前状态，再递归（超时）
class Solution {
    public int maxProfit(int[] prices) {
        return isHelp(prices, 0, 2, 0, 0);
    }
    
    public int isHelp(int[] prices, int s, int state, int pro, int last){
        if(s == prices.length){
            return pro;
        }
        if(state == 0){
            return Math.max(isHelp(prices, s + 1, 0, pro, last), isHelp(prices, s + 1, 1, pro + prices[s] - last, last));         
        }else if(state == 1){
            return isHelp(prices, s + 1, 2, pro, last);
        }else{
            return Math.max(isHelp(prices, s + 1, 2, pro, last), isHelp(prices, s + 1, 0, pro, prices[s]));
        }
    }
}

//方法2：动态规划
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] res = new int[len][3];
        for(int i = 0; i < len; i++){
            for(int j = i - 2; j >= 0; j--){
                res[i][0] = Math.max(res[i][0], res[j][1]);
            }
            for(int j = i - 1; j >= 0; j--){
                res[i][1] = Math.max(res[i][1], prices[i] - prices[j] + res[j][0]);
            }
            if(i == 0){
                res[i][2] = 0;
            }else{
                res[i][2] = Math.max(res[i - 1][0], Math.max(res[i - 1][1], res[i - 1][2]));
            }
                
        }
        return len == 0 ? 0 : Math.max(res[len - 1][0], Math.max(res[len - 1][1], res[len - 1][2]));
    }
}



//39. Combination Sum
/*Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]*/
//方法1：回溯法，递归实现
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> temp = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	Arrays.sort(candidates);
        isHelp(candidates, target, 0, 0);
        return res;
    }
    
    public void isHelp(int[] candidates, int target, int start, int sum){
        if(sum == target){
            res.add(new LinkedList<>(temp));
        }
        if(sum > target){
            return;
        }
        for(int i = start; i < candidates.length; i++){
            if(sum + candidates[i] > target){//适当剪枝后性能更好
                break;
            }
            temp.add(candidates[i]);
            isHelp(candidates, target, i, sum + candidates[i]);//每次起始位置和上一次相同，因为相同数字可以重复
            temp.remove(temp.size() - 1);
        }
    }
}

//40. Combination Sum II
/*Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]*/
//方法1：回溯法，递归实现
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> temp = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
         Arrays.sort(candidates);
        boolean[] mark = new boolean[candidates.length];
        isHelp(candidates, target, 0, 0, mark);
        return res;       
    }
    
    public void isHelp(int[] candidates, int target, int start, int sum, boolean[] mark){
        if(sum == target ){//如果此处用contains判断，时间复杂度太高
            res.add(new ArrayList<>(temp));
        }
        if(sum > target){
            return;
        }
        for(int i = start; i < candidates.length; i++){
            if(sum + candidates[i] > target){
                break;
            }
            if(i > 0 && candidates[i] == candidates[i - 1] && mark[i - 1] == false){continue;}//通过mark变量，保存每个值是否加入，当前一个值与现在相等，并且前一个已经加入时，才能加入现在这个，避免重复
            mark[i] = true;
            temp.add(candidates[i]);
            isHelp(candidates, target, i + 1, sum + candidates[i], mark);
            temp.remove(temp.size() - 1);
            mark[i] = false;
            

  
        }
    }
}


//216. Combination Sum III
/*Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]*/
//方法1：回溯，动态规划
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> temp = new ArrayList<>();
    int[] candidates = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    public List<List<Integer>> combinationSum3(int k, int n) {
        isHelp(candidates, k, n, 0, 0);
        return res;
    }
    public void isHelp(int[] candidates, int k, int target, int start, int sum){
        if(temp.size() == k){//注意判断个数
            if(sum == target)
                res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            if(sum + candidates[i] > target){
                break;
            }
            temp.add(candidates[i]);
            isHelp(candidates, k, target, i + 1, sum + candidates[i]);
            temp.remove(temp.size() - 1);
        }
    }
}



//377. Combination Sum IV
/*Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.*/
//方法1：回溯法，递归（超时）
class Solution {
    int res = 0;
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        isHelp(nums, target, 0);
        return res;
    }
    
    public void isHelp(int[] candidates, int target, int sum){
        if(sum >= target){
            if(sum == target) res++;
            return;
        }
        for(int i = 0; i < candidates.length; i++){
            if(sum + candidates[i] > target){
                break;
            }
            isHelp(candidates, target,sum + candidates[i]);
        }
    }
}

//方法2:动态规划
class Solution {
    public int combinationSum4(int[] nums, int target) {
    	Arrays.sort(nums);
        int[] res = new int[target + 1];
        res[0] = 1;
        for(int i = 1; i <= target; i++){
            for(int j = 0; j < nums.length; j++){
                if(nums[j] <= i){
                    res[i] += res[i - nums[j]];
                }else{//排序后可以适当剪枝
                	break;
                }
            }
        }
        return res[target];
    }
}



//77. Combinations
/*Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]*/
//方法1： 回溯法，递归
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> temp = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        isHelp(n, k , 1);
        return res;
    }
    
    public void isHelp(int n, int k, int start){
        if(temp.size() == k){
            res.add(new LinkedList<>(temp));
            return;
        }
        for(int i = start; i <= n; i++){
            temp.add(i);
            isHelp(n , k, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}



//46. Permutations
/*Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]*/
//方法1：回溯，递归
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> temp = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        boolean[] mark = new boolean[nums.length];
        isHelp(nums, mark, 0);
        return res;
    }
    
    public void isHelp(int[] nums, boolean[] mark, int size){
        if(size == nums.length){
            res.add(new LinkedList<>(temp));
            return;
        }
        
        for(int i = 0; i < nums.length; i++){
            if(mark[i] == false){
                mark[i] = true;
                temp.add(nums[i]);
                isHelp(nums, mark, size + 1);
                temp.remove(temp.size() - 1);
                mark[i] = false;
            }
        }
    }
}


//37. Sudoku Solver
//方法1：回溯法，递归实现
class Solution {
    boolean finish = false;
    public void solveSudoku(char[][] board) {
        isHelp(board, 0, 0);
    }
    
    public void isHelp(char[][] board, int sta, int sum){
        int row = sta / 9;//通过总位置分解出横纵坐标
    	int cul = sta % 9;
        if(sum == 81){
            finish = true;
            return;
        }
        if(row >= 9 || cul >= 9 || finish == true){
            return;
        }
        if(board[row][cul]  == '.'){
            int[] abc = new int[10];//先找出所有可以填的数字的集合
            for(int i = 0; i < 9; i++){
                if(board[row][i] != '.'){
                    abc[board[row][i] - '0']--;
                }
                if(board[i][cul] != '.'){
                    abc[board[i][cul] - '0']--;
                }
            }
            int r = row / 3;
            int c = cul / 3;
            for(int i = r * 3; i < (r + 1) * 3; i++){
                for(int j = c * 3; j < (c + 1) * 3; j++){
                    if(board[i][j] != '.'){
                        abc[board[i][j] - '0']--;
                    }
                }
            }
            for(int i = 1; i <= 9; i++){
                if(abc[i] == 0){
                    board[row][cul] = (char)(i + '0');
                    isHelp(board, sta + 1, sum + 1);
                    //为什么此处不能用
                    //isHelp(board, 行 + 1, 列，sum + 1);
                    //isHelp(board, 行， 列 + 1, sum + 1);
                    //这样的话是不可能完成全部遍历的，最大的遍历是17次，即从左上角到右下角
                    if(finish) return;
                }
            }
            board[row][cul] = '.';//回溯法在本次全部不通过时，一定要将状态重置
        }else{
            isHelp(board, sta + 1, sum + 1);
            if(finish) return;
        }

    }
}



//51. N-Queens
//方法1：回溯法，递归实现
class Solution {
    List<List<String>> res = new ArrayList<>();
    List<String> temp = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        boolean[][] mark = new boolean[n][n];
        isHelp(mark, n, 0);
        return res;
    }
    
    public void isHelp(boolean[][] mark, int n, int row){
        if(n == row){
            temp = new ArrayList<>();
            for(int i = 0; i < n; i++){
                StringBuilder s1 = new StringBuilder();
                for(int j = 0; j < n; j++){
                    s1.append(mark[i][j] == true ? "Q" : ".");
                }
                temp.add(s1.toString());
            }
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = 0; i < n; i++){
            if(isVaild(mark, row, i, n)){
                mark[row][i] = true;
                isHelp(mark, n, row + 1);
                mark[row][i] = false;
            }
        }
    }
    
    public boolean isVaild(boolean[][] mark, int row, int cul, int n){//判断此处落子是否合法
        for(int i = 0; i < row; i++){
            if(mark[i][cul]){
                return false;
            }
        }
        //注意剪枝，以后的行是不需要判断的，因为肯定没有
        int r = row;
        int c = cul;
        while(r < n && c < n && r >= 0 && c >= 0){
            if(mark[r][c]){
                return false;
            }
            r--;
            c++;
        }

        r = row;
        c = cul;
        while(r < n && c < n && r >= 0 && c >= 0){
            if(mark[r][c]){
                return false;
            }
            r--;
            c--;
        }
        return true;
    }
}


//52. N-Queens II
class Solution {
    int res = 0;
    public int totalNQueens(int n) {
        boolean[][] mark = new boolean[n][n];
        isHelp(mark, n, 0);
        return res;
    }
    public void isHelp(boolean[][] mark, int n, int row){
        if(n == row){
            res++;
            return;
        }
        for(int i = 0; i < n; i++){
            if(isVaild(mark, row, i, n)){
                mark[row][i] = true;
                isHelp(mark, n, row + 1);
                mark[row][i] = false;
            }
        }
    }

    public boolean isVaild(boolean[][] mark, int row, int cul, int n){
        for(int i = 0; i < row; i++){
            if(mark[i][cul]){
                return false;
            }
        }

        int r = row;
        int c = cul;
        while(r < n && c < n && r >= 0 && c >= 0){
            if(mark[r][c]){
                return false;
            }
            r--;
            c++;
        }

        r = row;
        c = cul;
        while(r < n && c < n && r >= 0 && c >= 0){
            if(mark[r][c]){
                return false;
            }
            r--;
            c--;
        }
        return true;
    }
}



//60. Permutation Sequence
/*The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.*/

//方法1：普通算数，打好草稿，一步步递推，如果不是求所有种类数，或者所有种类结果，不需要用回溯法来求解
class Solution {
    StringBuilder res = new StringBuilder();
    public String getPermutation(int n, int k) {
        List<Integer> number = new LinkedList<>();
        int[] mu = new int[n];
        for(int i = 1; i <= n; i++){
            number.add(i);
            if(i < n) mu[i] = i == 1 ? 1 : mu[i - 1] * i;
        }
        k--;
        while(n > 1){
            int m = mu[n - 1];
            int k1 = k / m;
            res.append(number.get(k1));
            number.remove(k1);
            k -= m * k1;
            n--;
        }
        res.append(number.get(0) + "");
        return res.toString();
    }
    
}



//31. Next Permutation
/*Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1*/
//方法1：先从右向左找到第一个减小的数a，再从右往左找到第一个比a大的数b，交换位置ab，再将a数往后全部翻转
//只要认真推演，都ok
class Solution {
    public void nextPermutation(int[] nums) {
        boolean find = false;
        int pre = Integer.MIN_VALUE;
        int sta = 0;
        for(int i = nums.length - 1; i >= 0; i--){
            if(nums[i] < pre){
                find = true;
                sta = i;
                break;
            }else{
                pre = nums[i];
            }
        }
        int left = 0;
        int right = nums.length - 1;
        if(find){
            int min = 0;
            for(int i = nums.length - 1; i >= 0; i--){
                if(nums[i] > nums[sta]){
                    min = i;
                    break;
                }
            }   
            int temp = nums[sta];
            nums[sta] = nums[min];
            nums[min] = temp;
            left = sta + 1;
        }
        while(left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;        
            left++;
            right--;
        }
        return;
    }
}



//47. Permutations II
/*Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]*/
//方法1：回溯法，递归实现
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> temp = new LinkedList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] mark = new boolean[nums.length];
        isHelp(nums, mark);
        return res;
    }
    
    public void isHelp(int[] nums, boolean[] mark){
        if(temp.size() == nums.length){
            res.add(new LinkedList<>(temp));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(mark[i] == false && (i == 0 || nums[i] != nums[i - 1] || mark[i - 1] == true)){//此处注意
                mark[i] = true;
                temp.add(nums[i]);
                isHelp(nums, mark);
                temp.remove(temp.size() - 1);
                mark[i] = false;
            }
        }
    }
}



//78. Subsets
/*Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]*/
//方法1：回溯法，递归实现
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> temp = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        isHelp(nums, 0);
        return res;
    }
    public void isHelp(int[] nums, int sta){
        if(temp.size() <= nums.length){
            res.add(new LinkedList<>(temp));
            if(temp.size() == nums.length) return;
        }
        for(int i = sta; i < nums.length; i++){
            temp.add(nums[i]);
            isHelp(nums,i + 1);
            temp.remove(temp.size() - 1);

        }
    }
}



//90. Subsets II
/*Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]*/
//方法1：回溯法，递归实现
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> temp = new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        isHelp(nums, 0);
        return res;
    }
    public void isHelp(int[] nums, int sta){
        res.add(new LinkedList<>(temp));
        for(int i = sta; i < nums.length; i++){
            if(i == sta || nums[i] != nums[i - 1]){//可以不需要用mark数组
                temp.add(nums[i]);
                isHelp(nums,i + 1);
                temp.remove(temp.size() - 1);  
            }
        }
    }
}



//79. Word Search
/*Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.*/
//方法1：回溯法，递归实现
class Solution {
    public boolean exist(char[][] board, String word) {
        if(board.length == 0){
            return word.length() == 0 ? true : false;
        }
        boolean[][] mark = new boolean[board.length][board[0].length];
        for(int i = 0;i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){//每一个位置都要作为起始位置
                if(board[i][j] == word.charAt(0) && isHelp(board, word, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isHelp(char[][] board, String word, int row, int cul, int sta){
        if(sta == word.length()){
            return true;
        }
        if(row < 0 || cul < 0 || row >= board.length || cul >= board[0].length){
            return false;
        }
        if(board[row][cul] == word.charAt(sta)){
            board[row][cul] = '#';//将其内容改为特殊符号，省去额外的数组记录
            boolean t = isHelp(board, word, row, cul + 1, sta + 1) || isHelp(board, word, row, cul - 1, sta + 1) || isHelp(board, word, row + 1, cul, sta + 1) || isHelp(board, word, row - 1, cul, sta + 1);
            board[row][cul] = word.charAt(sta);
            return t;
        }else{
            return false;
        }
    }
}



//130. Surrounded Regions
/*Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X*/

//方法1：典型BFS算法，从边缘开始搜索岛屿并标记，最后将所有未遍历到的岛屿变为‘X’
class Solution {
    public void solve(char[][] board) {
        int row = board.length;
        if(row == 0) return;
        int cul = board[0].length;
       Queue<int[]> queue = new LinkedList<>();
        boolean[][] mark = new boolean[row][cul];
        for(int i = 0; i < cul; i++){
            if(board[0][i] == 'O' && mark[0][i] == false){
          
                queue.add(new int[]{0, i});
            }
            if(board[row - 1][i] == 'O' && mark[row - 1][i] == false){
      
                queue.add(new int[]{row - 1, i});
            }
        }
        for(int i = 0; i < row; i++){
            if(board[i][0] == 'O' && mark[i][0] == false){
                
                queue.add(new int[]{i, 0});
            }
            if(board[i][cul - 1] == 'O' && mark[i][cul - 1] == false){
    
                queue.add(new int[]{i, cul - 1});
            }
        }
        while(!queue.isEmpty()){
            int[] sta = queue.remove();
            mark[sta[0]][sta[1]] = true;
            if(sta[0] - 1 >= 0 && mark[sta[0] - 1][sta[1]] == false && board[sta[0] - 1][sta[1]] == 'O'){
                queue.add(new int[]{sta[0] - 1, sta[1]});
            }

            if(sta[0] + 1 < row && mark[sta[0] + 1][sta[1]] == false && board[sta[0] + 1][sta[1]] == 'O'){
                queue.add(new int[]{sta[0] + 1, sta[1]});
            }  

            if(sta[1] + 1 < cul && mark[sta[0]][sta[1] + 1] == false && board[sta[0]][sta[1] + 1] == 'O'){
                queue.add(new int[]{sta[0], sta[1] + 1});
            }
            if(sta[1] - 1 >= 0 && mark[sta[0]][sta[1] - 1] == false && board[sta[0]][sta[1] - 1] == 'O'){
                queue.add(new int[]{sta[0], sta[1] - 1});
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < cul; j++){
                if(mark[i][j] == false){
                    board[i][j] = 'X';
                }
            }
        }
        return;
    }
    
}

//方法2：基本思想与方法一相同，不使用mark数组，而是将board赋值特殊字符
class Solution {
    public void solve(char[][] board) {
        int row = board.length;
        if(row == 0) return;
        int cul = board[0].length;
       Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < cul; i++){
            if(board[0][i] == 'O'){
          
                queue.add(new int[]{0, i});
            }
            if(board[row - 1][i] == 'O'){
      
                queue.add(new int[]{row - 1, i});
            }
        }
        for(int i = 0; i < row; i++){
            if(board[i][0] == 'O'){
                
                queue.add(new int[]{i, 0});
            }
            if(board[i][cul - 1] == 'O'){
    
                queue.add(new int[]{i, cul - 1});
            }
        }
        while(!queue.isEmpty()){
            int[] sta = queue.remove();
            board[sta[0]][sta[1]] = '#';
            if(sta[0] - 1 >= 0 && board[sta[0] - 1][sta[1]] == 'O'){
                queue.add(new int[]{sta[0] - 1, sta[1]});
            }

            if(sta[0] + 1 < row && board[sta[0] + 1][sta[1]] == 'O'){
                queue.add(new int[]{sta[0] + 1, sta[1]});
            }  

            if(sta[1] + 1 < cul && board[sta[0]][sta[1] + 1] == 'O'){
                queue.add(new int[]{sta[0], sta[1] + 1});
            }
            if(sta[1] - 1 >= 0 && board[sta[0]][sta[1] - 1] == 'O'){
                queue.add(new int[]{sta[0], sta[1] - 1});
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < cul; j++){
                if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }else{
                    board[i][j] = 'X';
                }
            }
        }
        return;
    }
    
}

//方法3：dfs（栈溢出，递归深度过大）
class Solution {
    public void solve(char[][] board) {
        int row = board.length;
        if(row == 0) return;
        int cul = board[0].length;
        for(int i = 0; i < cul; i++){
            if(board[0][i] == 'O'){
                dfs(board, row, cul, 0, i);
            }
            if(board[row - 1][i] == 'O'){
      
                dfs(board, row, cul, row - 1, i);
            }
        }
        for(int i = 0; i < row; i++){
            if(board[i][0] == 'O'){
                
                dfs(board, row, cul, i, 0);
            }
            if(board[i][cul - 1] == 'O'){
    
                dfs(board, row, cul, i, cul - 1);
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < cul; j++){
                if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }else{
                    board[i][j] = 'X';
                }
            }
        }
        return;
    }
    public void dfs(char[][] board, int row, int cul, int r, int c){
        board[r][c] = '#';
        if(r - 1 >= 0 && board[r - 1][c] == 'O'){
            dfs(board, row, cul, r - 1, c);
        }
        if(r + 1 < row && board[r + 1][c] == 'O'){
            dfs(board, row, cul, r + 1, c);
        }      
        if(c - 1 >= 0 && board[r][c - 1] == 'O'){
            dfs(board, row, cul, r, c - 1);
        }
        if(c + 1 < cul && board[r][c + 1] == 'O'){
            dfs(board, row, cul, r, c + 1);
        } 
    }
}

//方法4：dfs循环实现，速度并没有比bfs快
class Solution {
    public void solve(char[][] board) {
        int row = board.length;
        if(row == 0) return;
        int cul = board[0].length;
       Stack<int[]> queue = new Stack<>();
        for(int i = 0; i < cul; i++){
            if(board[0][i] == 'O'){
          
                queue.push(new int[]{0, i});
            }
            if(board[row - 1][i] == 'O'){
      
                queue.push(new int[]{row - 1, i});
            }
        }
        for(int i = 0; i < row; i++){
            if(board[i][0] == 'O'){
                
                queue.push(new int[]{i, 0});
            }
            if(board[i][cul - 1] == 'O'){
    
                queue.push(new int[]{i, cul - 1});
            }
        }
        while(!queue.isEmpty()){
            int[] sta = queue.pop();
            board[sta[0]][sta[1]] = '#';
            if(sta[0] - 1 >= 0 && board[sta[0] - 1][sta[1]] == 'O'){
                queue.push(new int[]{sta[0] - 1, sta[1]});
            }

            if(sta[0] + 1 < row && board[sta[0] + 1][sta[1]] == 'O'){
                queue.push(new int[]{sta[0] + 1, sta[1]});
            }  

            if(sta[1] + 1 < cul && board[sta[0]][sta[1] + 1] == 'O'){
                queue.push(new int[]{sta[0], sta[1] + 1});
            }
            if(sta[1] - 1 >= 0 && board[sta[0]][sta[1] - 1] == 'O'){
                queue.push(new int[]{sta[0], sta[1] - 1});
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < cul; j++){
                if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }else{
                    board[i][j] = 'X';
                }
            }
        }
        return;
    }
    
}

//方法5：加权UF算法（必须加权，否则超时，总体速度不快，不如bfs）
class Solution {
    int[] size;
    public void solve(char[][] board) {
        int row = board.length;
        if(row == 0) return;
        int cul = board[0].length;
        int[] id = new int[row * cul];
        size = new int[row * cul];
        boolean[] mark = new boolean[row * cul]; 
        for(int i = 0; i < id.length; i++){
            int r = i / cul;
            int c = i % cul;
            id[i] = i;//必须首先遍历一次，初始化所有id[]
            size[i] = 1;
            if(board[r][c] == 'O' && (r == 0 || r == row - 1 || c == 0 || c == cul - 1)){
                mark[i] = true;
            }

        }
        for(int i = 0; i < id.length; i++){
            int r = i / cul;
            int c = i % cul;
            if(board[r][c] == 'O' && (r == 0 || r == row - 1 || c == 0 || c == cul - 1)){
                mark[i] = true;
            }
            if(r - 1 >= 0 && board[r - 1][c] == board[r][c]){//向右和向上（横纵各取一个方向即可）
                union(id, mark, i - cul, i);
            }
            if(c + 1 < cul && board[r][c] == board[r][c + 1]){
                union(id, mark, i, i + 1);
            }            
        }
        for(int i = 0; i < id.length; i++){
            int r = i / cul;
            int c = i % cul;
            if(board[r][c] == 'O' && mark[find(id, i)] == false){
                board[r][c] = 'X';
            }
        }
        return;
    }
    
    public int find(int[] id, int x){//寻找根节点
        while(x != id[x]){
            x = id[x];
        }
        return x;
    }
    
    public void union(int[] id, boolean[] mark, int x, int y){//合并两个连通分量
        int rx = find(id, x);
        int ry = find(id, y);
        if(rx != ry){
            if(size[rx] > size[ry]){
                size[rx] += size[ry];
                id[ry] = rx;
            }else{
                size[ry] += size[rx];
                id[rx] = ry;
            }
            
        }
        mark[ry] = mark[rx] || mark[ry];//两个缺一不可，因为mark[]是用来标记跟的性质，每个树的大小不同，连接方式也不同，所以要将每个数的根都赋值更新
        mark[rx] = mark[rx] || mark[ry];
    }
    
}




//200. Number of Islands
/*Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3*/

//方法1：dfs,递归实现
class Solution {
    public int numIslands(char[][] grid) {
        int row = grid.length;
        if(row == 0) return 0;
        int cul = grid[0].length;
        if(cul == 0) return 0;
        int res = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < cul; j++){
                if(grid[i][j] == '1'){
                    res++;
                    dfs(grid, row, cul, i, j);
                }
            }
        }
        return res;
    }
    
    public void dfs(char[][] grid, int row, int cul, int r, int c){
        grid[r][c] = '2';
        if(r - 1 >= 0 && grid[r - 1][c] == '1'){
            dfs(grid, row, cul, r - 1, c);
        }
        if(r + 1 < row && grid[r + 1][c] == '1'){
            dfs(grid, row, cul, r + 1, c);
        }
        if(c - 1 >= 0 && grid[r][c - 1] == '1'){
            dfs(grid, row, cul, r, c - 1);
        }
        if(c + 1 < cul && grid[r][c + 1] == '1'){
            dfs(grid, row, cul, r, c + 1);
        }
    }
    //此写法更加简洁！合并冗余判断
//    public void dfs(char[][] grid, int row, int cul, int r, int c){
//        if(r < 0 || c < 0 || r >= row || c >= cul || grid[r][c] != '1') return;
//        grid[r][c] = '2';
//        dfs(grid, row, cul, r - 1, c);
//        dfs(grid, row, cul, r + 1, c);
//        dfs(grid, row, cul, r, c - 1);
//        dfs(grid, row, cul, r, c + 1);
//
//    }
}

//方法2：bfs，（超时）
class Solution {
    public int numIslands(char[][] grid) {
        int row = grid.length;
        if(row == 0) return 0;
        int cul = grid[0].length;
        if(cul == 0) return 0;
        int res = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < cul; j++){
                if(grid[i][j] == '1'){
                    res++;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    while(!queue.isEmpty()){
                        int[] sta = queue.remove();
                        grid[sta[0]][sta[1]] = '2';
                        if(sta[0] - 1 >= 0 && grid[sta[0] - 1][sta[1]] == '1'){
                            queue.add(new int[]{sta[0] - 1, sta[1]});
                        }
                        if(sta[0] + 1 < row && grid[sta[0] + 1][sta[1]] == '1'){
                            queue.add(new int[]{sta[0] + 1, sta[1]});
                        }
                        if(sta[1] - 1 >= 0 && grid[sta[0]][sta[1] - 1] == '1'){
                            queue.add(new int[]{sta[0], sta[1] - 1});
                        } 
                        if(sta[1] + 1 < cul && grid[sta[0]][sta[1] + 1] == '1'){
                            queue.add(new int[]{sta[0], sta[1] + 1});
                        }                        
                    }
                }
            }
        }
        return res;
    }
}

//方法3：dfs，迭代实现
class Solution {
    public int numIslands(char[][] grid) {
        int row = grid.length;
        if(row == 0) return 0;
        int cul = grid[0].length;
        if(cul == 0) return 0;
        int res = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < cul; j++){
                if(grid[i][j] == '1'){
                    res++;
                    Stack<int[]> queue = new Stack<>();
                    queue.push(new int[]{i, j});
                    while(!queue.isEmpty()){
                        int[] sta = queue.pop();
                        grid[sta[0]][sta[1]] = '2';
                        if(sta[0] - 1 >= 0 && grid[sta[0] - 1][sta[1]] == '1'){
                            queue.push(new int[]{sta[0] - 1, sta[1]});
                        }
                        if(sta[0] + 1 < row && grid[sta[0] + 1][sta[1]] == '1'){
                            queue.push(new int[]{sta[0] + 1, sta[1]});
                        }
                        if(sta[1] - 1 >= 0 && grid[sta[0]][sta[1] - 1] == '1'){
                            queue.push(new int[]{sta[0], sta[1] - 1});
                        } 
                        if(sta[1] + 1 < cul && grid[sta[0]][sta[1] + 1] == '1'){
                            queue.push(new int[]{sta[0], sta[1] + 1});
                        }                        
                    }
                }
            }
        }
        return res;
    }
}



//62. Unique Paths
/*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

*/
//方法1：二维动态规划
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[1][0] = 1;
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }
}

//方法2：一维动态规划
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                dp[j] += dp[j - 1];//本位加上前一位，相当于上面位置，加上左边位置，空间轮转
            }
        }
        return dp[n];
    }
}



//63. Unique Paths II
/*Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.*/
//方法1：一维动态规划
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int cul = obstacleGrid[0].length;
        int[] dp = new int[cul + 1];
        dp[1] = 1;
        for(int i = 0; i < row; i++){
            for(int j = 1; j <= cul; j++){
                if(obstacleGrid[i][j - 1] == 0){
                    dp[j] += dp[j - 1];
                }else{
                    
                    dp[j] = 0;
                }
                
            }
        }
        return dp[cul];
    }
}



//64. Minimum Path Sum
/*Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.*/
//方法1：一维动态规划
class Solution {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int cul = grid[0].length;
        int[] dp = new int[cul + 1];
        for(int i = 0; i <= cul; i++){
            dp[i] = Integer.MAX_VALUE;//dp需要注意值的初始化
        }
        dp[1] = 0;
        for(int i = 0; i < row; i++){
            for(int j = 1; j <= cul; j++){//取本位和前一位的较小值，加上本位上的实际值
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j - 1];
            }
        }
        return dp[cul];
    }
}



//198. House Robber
/*You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.*/
//方法1：二维动态规划，两行，分别保存偷和不偷这两种情况
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        int[][] res = new int[2][nums.length];
        res[0][0] = nums[0];
        res[1][0] = 0;
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            res[0][i] = res[1][i - 1] + nums[i];
            res[1][i] = max;
            max = Math.max(max, res[0][i]);
        }
        return Math.max(res[0][nums.length - 1], res[1][nums.length - 1]);
    }
}

//方法2：一维动态规划
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] res = new int[len + 2];
        for(int i = 2; i < len + 2; i++){//对dp数组的定义直接影响了，算法实现
            res[i] = Math.max(res[i - 1], res[i - 2] + nums[i - 2]);
        }
        return res[len + 1];
    }
}

//方法3：动态规划，轮转空间，使用空间O(1)
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] res = new int[2];
        for(int i = 0; i < len; i++){//真正需要的记录只有两个，空间轮转之后2个变量即可
            res[i % 2] = Math.max(res[i % 2] + nums[i], res[(i + 1) % 2]);//以奇偶作为轮转
        }
        return res[(len - 1) % 2];
    }
}



//213. House Robber II
/*After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.*/
//方法1：动态规划
class Solution {//（对题目多一点思考和分析，总是可以解决的）
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if(len <= 3){
            Arrays.sort(nums);
            return nums[len - 1];
        }
        //对第一个房间偷还是不偷分为两种情况
        int[] res = new int[2];
        for(int i = 1; i < len; i++){
            res[i % 2] = Math.max(res[i % 2] + nums[i], res[(i + 1) % 2]);
        }
        int no = res[(len - 1) % 2];
        res = new int[2];
        for(int i = 2; i < len - 1; i++){
            res[i % 2] = Math.max(res[i % 2] + nums[i], res[(i + 1) % 2]);
        } 
        int yes = res[(len - 2) % 2];
        return Math.max(no, yes + nums[0]);
    }
}



//152. Maximum Product Subarray
/*Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.*/
//方法1：动态规划
class Solution {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];
        int res = Integer.MIN_VALUE;//全局最大值
        int ma = 1;//局部最大值
        int mi = 1;//局部最小值
        //因为其符号具有全局性，所以必须同时保存局部最大和局部最小
        for(int i = 0; i < len; i++){
            int temp = ma;
            ma = Math.max(Math.max(ma * nums[i], mi * nums[i]), nums[i]);
            res = Math.max(res, ma);
            mi = Math.min(Math.min(temp * nums[i], mi * nums[i]), nums[i]);
        }
        return res;
    }
}




//120. Triangle
/*Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).*/
//方法1：动态规划
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.get(triangle.size() - 1).size();
        int[] res = new int[len + 1];//取最后一行数量的数组，并初始化为最大值
        for(int i = 0; i < len + 1; i++){
            res[i] = Integer.MAX_VALUE;
        }
        res[1] = 0;
        int m =  Integer.MAX_VALUE;
        for(List<Integer> list : triangle){//使用空间轮转，每次只是用正上方和正上方左一位两个数值
            for(int i = list.size(); i >= 1; i--){//从右往左遍历，否则靠左的数值会提前被覆盖
                res[i] = Math.min(res[i], res[i - 1]) + list.get(i - 1);
            }
        }
        Arrays.sort(res);
        return res[0];
    }
}



//139. Word Break
/*Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".*/
//方法1：动态规划
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0) return true;
        int len = s.length();
        boolean[] mark = new boolean[len + 1];
        mark[0] = true;
        for(int i = 1; i <= len; i++){
            for(int j = i - 1; j >= 0; j--){//向前寻找能够匹配的字符串末位置到此处位置组成的新字符串
                if(mark[j] == true && wordDict.contains(s.substring(j, i))){
                    mark[i] = true;//每次匹配成功记录位置
                    break;
                }
            }
        }
        return mark[len];
    }
}



//221. Maximal Square
/*Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.*/
//方法1：动态规划，举个例子把流程走一遍，基本算法思路就OK了
class Solution {
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        if(row == 0) return 0;
        int cul = matrix[0].length;
        if(cul == 0) return 0;
        int[][] res = new int[row + 1][cul + 1];
        int m = 0;
        for(int i = 1; i <= row; i++){
            for(int j = 1; j <= cul; j++){
                if(matrix[i - 1][j - 1] == '1'){
                    int t1 = res[i - 1][j];
                    int t2 = res[i][j - 1];
                    int t3 = res[i - 1][j - 1];
                    res[i][j] = Math.min(Math.min(t1, t2), t3) + 1;
                    m = Math.max(m, res[i][j]);  
                }
            }
        }
        return m * m;
    }
}



//132. Palindrome Partitioning II
/*Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.*/
//方法1：两次使用动态规划
class Solution {
    public int minCut(String s) {
        if(s == null || s.length() <= 1) return 0;
        int len = s.length();
        boolean[][] mark = new boolean[len][len];
        int[] res = new int[len + 1];
        for(int i = 0; i < len; i++)
            for(int j = 0; j < len; j++){
                if(i >= j) mark[i][j] = true;
            }
        res[len] = -1;
        for(int i = len - 1; i >= 0; i--){
            res[i] = res[i + 1] + 1;
            for(int j = i + 1; j < len; j++){
                if(s.charAt(i) == s.charAt(j) && mark[i + 1][j - 1] == true){
                    mark[i][j] = true;//先用动态规划求出从某位到某位是否可以组成回文序列
                    res[i] = Math.min(res[i], res[j + 1] + 1);//res[]记录从i到len所需的最小分割次数，每当j位可以组成回文序列时，更新res的值
                }
            }
        }  
        return res[0];
    }
}



//123. Best Time to Buy and Sell Stock III
/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).*/
//方法1：m[]数组从左往右记录到某位一次交易的最大值，f[]数组从右往左记录记录交易一次的最大值，最后m + f的最大值即使结果
class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        int len = prices.length;
        int[] f = new int[len];
        int max = Integer.MIN_VALUE;
        int min = 0;
        
        for(int i = len - 1; i >= 0; i--){
            if(max <= prices[i]){
                f[i] = 0;
                max = prices[i];
            }else{
                min = Math.min(min, prices[i] - max);
                f[i] = min;
            }
            
        }
        int res = 0;
        int[] m = new int[len + 1];
        min = Integer.MAX_VALUE;
        max = 0;
        for(int i = 0; i < len; i++){
            if(min >= prices[i]){
                m[i] = max;
                min = prices[i];
            }else{
                max = Math.max(max, prices[i] - min);
                m[i] = max;
            }
            if(i == len - 1)
                res = Math.max(res, m[i]);
            else
                res = Math.max(res, m[i] - f[i + 1]);
        }

        return res;
    }
}



//115. Distinct Subsequences
/*Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.*/
//方法1：二维动态规划
class Solution {
    public int numDistinct(String s, String t) {
        int ls = s.length();
        int lt = t.length();
        if(ls < lt) return 0;
        int[][] res = new int[ls + 1][lt + 1];
        for(int i = 0; i < ls; i++){
            res[i][0] = 1;
            for(int j = 0; j <= Math.min(i, lt - 1); j++){
                if(s.charAt(i) != t.charAt(j)){//如果两个值相等，则直接等于s退一位和t本位的值
                    res[i + 1][j + 1] = res[i][j + 1];
                }else{//如果两个值不等，则等于两种情况的值的和
                    res[i + 1][j + 1] = res[i][j] + res[i][j + 1];
                }
            }
        }
        return res[ls][lt];
    }
}



//523. Continuous Subarray Sum
/*Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.*/
//方法1：一维动态规划
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] sum = new int[len];
        
        for(int i = 0; i < len - 1; i++){
            sum[i] = nums[i];
            for(int j = i + 1; j < len; j++){
                sum[j] = sum[j - 1] + nums[j];
                if(sum[j] == 0) return true;  
                else if(k != 0 && sum[j]%k == 0) return true;  
            }
        }
        return false;
    }
}

//方法2：动态规划结合Map使用，记录余数的值以及其位置
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//此处重点
        int len = nums.length;
        int sum = 0;
        for(int i = 0; i < len; i++){
            sum += nums[i];
            if(k != 0){
                if(map.containsKey(sum % k)){
                    if(i >= 1)//保证至少是2个数
                        return true;
                }else{
                    map.put(sum % k, i);
                }
            }else{
                if(i >= 1 && sum == 0)
                    return true;
            }
        }
        return false;
    }
}



//560. Subarray Sum Equals K
/*Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2*/
//方法1：动态规划O(n)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] sum = new int[len];
        int res = 0;
        for(int i = 0; i < len; i++){
            sum[i] = nums[i];
            if(sum[i] == k) res++;
            for(int j = i + 1; j < len; j++){
                sum[j] = sum[j - 1] + nums[j];
                if(sum[j] == k) res++;
            }
        }
        return res;
    }
}

//方法2：Map，记录出现的值以及对应的次数
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        int sum = 0;
        int res = 0;
        map.put(0, 1);
        for(int i = 0; i < len; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                res += map.get(sum - k);
            }
            if(map.containsKey(sum)){
                map.put(sum, map.get(sum) + 1);
            }else{
                map.put(sum, 1);
            }
        }
        return res;
    }
}