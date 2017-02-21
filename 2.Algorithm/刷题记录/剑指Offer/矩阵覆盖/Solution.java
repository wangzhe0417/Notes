/**
 * 题目：我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 *
 * 思路：依旧可以转换成菲波那切数列，三种实现 递归、迭代、动态规划
 */

//递归
public class Solution {
	public int RectCover(int target) {
		if(target <= 2)
			return target;
		else
			return RectCover(target-1) + RectCover(target-2);
	}
}

//迭代
public class Solution {
	public int RectCover(int target) {
		if(target <= 2)
			return target;
		int fn1 = 1;
        int fn2 = 2;
        while(target-- > 2){
            fn2 = fn2 + fn1;
            fn1 = fn2 - fn1;
        }
        return fn2;
	}
}

//动态规划
public class Solution {
	public int RectCover(int target) {
        if(target <= 2)
            return target;
		int [] array = new int[target+1];
        array[0] = 0;
        array[1] = 1;
        array[2] = 2;
        for(int i = 3; i <= target; i++){
            array[i] = array[i-1] + array[i-2];
        }
        return array[target];
	}
}