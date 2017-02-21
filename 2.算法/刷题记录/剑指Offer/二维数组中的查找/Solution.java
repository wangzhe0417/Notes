/**
 *题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 *		请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *思路：根据数组有序的规律，可以从左下角或右上角开始搜索。
 *		当从右上角开始搜索时，target > array[row][col]则向下移，即row++;
 *		target < array[row][col]则向左移，即col--。
 *
 *PS:二维数组a[][]的行长度为a.length,列长度为a[0].length。
 */
public class Solution{
	public boolean Find(int target, int [][] array){
		int row = 0;//行号
		int col = array[0].length - 1;//列号
		while(row <= array.length - 1 && col >= 0){
			if(target < array[row][col]){
				col --;
			}else if(target > array[row][col]){
				row ++;
			}else{
				return true;
			}
		}
		return false;
	}
}
