/**
 * 题目：一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * 思路：因为n级台阶，第一步有n种跳法：跳1级、跳2级、到跳n级
 *			跳1级，剩下n-1级，则剩下跳法是f(n-1)
 *			跳2级，剩下n-2级，则剩下跳法是f(n-2)
 *			所以f(n) = f(n-1) + f(n-2) + ··· + f(1)
 *			因为f(n-1) = f(n-2) + f(n-3) + ··· + f(1)
 *			所以f(n) = 2 * f(n-1)
 */

//递归
public class Solution {
	public int JumpFloorII(int target) {
		if(target <= 2)
			return target;
		return 2 * JumpFloorII(target-1);
	}
}

//迭代
public class Solution {
	public int JumpFloorII(int target) {
		if (target <= 2) 
			return target;
		int num = 2;
		while(target-- > 2){
			num = 2 * num;//此处可用移位运算符实现，也可用Math.pow()函数实现
		}
	}
}

//动态规划
public class Solution {
	public int JumpFloorII(int target) {
		int [] array = new int[target+1];
		array[0] = 0;
		array[1] = 1;
		for(int i = 2; i <= target; i++) {
			array[i] = 2 * array[i-1];
		}
		return array[target];
	}
}
