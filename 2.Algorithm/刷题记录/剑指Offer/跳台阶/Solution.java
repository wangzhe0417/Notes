/**
 * 题目：一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * 思路：跳上n阶台阶有f(n)种跳法，跳上n-1阶有f(n-1)种，则f(n)=f(n-1)+f(n-2),即为斐波那契数列
 *		而对斐波那契数列则有三种方法；递归，迭代和动态规划
 */

//递归实现
public class Solution {
    public int JumpFloor(int target) {
    	if(target <= 2)
    		return target;
    	return JumpFloor(target-1) + JumpFloor(target-2);
    }
}

//迭代
public class Solution {
    public int JumpFloor(int target) {
    	if (target < 0)
    		return 0; 
    	if(target <= 2)
    		return target;
    	int fn1 = 1;
    	int fn2 = 2;
    	while(target-- > 2){
    		fn2 = fn1 + fn2;
    		fn1 = fn2 - fn1;
    	}
    	return fn2;
    }
}

//动态规划
public class Solution {
	public int JumpFloor(int target) {
        if(target <= 2) {
			return target;
		}
		int [] array = new int[target+1];
		array[0] = 0;
		array[1] = 1;
		array[2] = 2;
		for(int i = 3; i <= target; i ++){
			array[i] = array[i - 1] + array[i - 2];
		}
		return array[target];
	}
}