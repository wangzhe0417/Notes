/**
 * 题目：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 *		 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 *		 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 *		 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * 
 * 思路一：顺序遍历数组，遇到array[i] > array[i+1]，则array[i+1]为最小值。
 * 思路二：使用二分查找，
 *
 */

//思路一实现
import java.util.ArrayList;
public class Solution {
	public int minNumberInRotateArray(int [] array) {
		if(array.length == 0)
			return 0;
		for(int i = 0; i < array.length; i++){
			if(array[i] > array[i+1])
				return array[i+1];
		}
		return array[0];//循环完了都没出现下降现象，所以是非递减排序的数组，第一个元素最小。
	}
}

//思路二实现
import java.utl.ArrayList;
public class Solution {
	public int minNumberInRotateArray(int [] array) {
		int left = 0;
		int right = array.length - 1;
		int middle = (left + right) / 2;
		if(array.length == 0)
			return 0;
		while(right - left > 1 ) {
			if(array[middle] >= array[left] )
				left = middle;
			if(array[middle] <= array[right])
				right = middle;
			middle = (left + right) / 2;
		}
		return array[right];
	}
}
