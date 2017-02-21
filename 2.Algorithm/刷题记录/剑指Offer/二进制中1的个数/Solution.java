/**
 * 题目：输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 * 错误思路：因为本题考虑了负数的情况，所以用移位操作符时，右移会在高位补1，会使程序陷入死循环，因此不考虑移位运算。
 * 思路一：对n的二进制数的每一位都与1进行&运算，来计算1的个数
 * 思路二：一个整数不为0，那么这个整数至少有一位是1。如果我们把这个整数减1，那么原来处在整数最右边的1就会变为0，
 *		   原来在1后面的所有的0都会变成1(如果最右边的1后面还有0的话)。其余所有位将不会受到影响。
 */
 
//思路一
public class Solution {
	public int NumberOf1(int n) {
		int flag = 1;
		int count = 0;
		while(flag != 0) {
			if ((n & flag) != 0)
				count ++;
			flag = flag << 1;
		}
		return count;
	}
}

//思路二
public class Solution {
	public int NumberOf1(int n) {
		int count = 0;
		while(n != 0) {
			n = (n-1) & n;
			count ++;
		}
		return count;
	}
}
