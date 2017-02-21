/**
 * 题目：现在要求输入一个整数n，请你输出斐波那契数列的第n项。（n<=39）
 * 
 * 思路一：递归，大量重复计算，会StackOverFlow
 * 思路二：迭代（使用fn1和fn2保存计算过程中的结果，并重复使用）
 * 思路三：动态规划（使用数组记录每一步计算结果）
 * 
 * PS：兔子问题中兔子每个月的出生数同为斐波那契数列问题。
 */

//递归实现
public class Solution {
	public int Fibonacci(int n) {
		if(n <= 2)
			return 1;
		else
			return Fibonacci(n-1) + Fibonacci(n-2);
	}
}
//迭代实现
public class Solution {
	public int Fibonacci(int n) {
		if(n <= 0)
			return 0;
		if(n <= 2)
			return 1;
		int fn1 = 1;
		int fn2 = 1;
		while(n-- > 2){
			fn2 = fn1 + fn2;
			fn1 = fn2 - fn1;
		}
		return fn2;
	}
}

//动态规划实现
public class Solution {
    public int Fibonacci(int n) {
        if(n <= 0) 
        	return 0;
    	if(n <= 2)
            return 1;
        int [] array = new int [n+1];
        array[0] = 0;
        array[1] = 1;
        for(int i = 2;i <= n; i++){
        	array[i] = array[i-1] + array[i-2];
        }
        return array[n];
    }
}

