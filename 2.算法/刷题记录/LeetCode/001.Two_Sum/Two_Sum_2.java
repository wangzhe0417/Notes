/*************************************************************************
	> File Name: Two_Sum.java
	> Author: wangzhe
	> Mail: 582165168@qq.com 
	> Created Time: 六  3/19 13:57:29 2016
 ************************************************************************/
/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution.
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * UPDATE (2016/2/13):
 * The return format had been changed to zero-based indices. Please read the above updated description carefully.
 */
public class Solution{
	public int[] twoSum(int[] nums,int target){
		HashMap<Integer, Integer> map = new HashMap();
		for(int i = 0; i < nums.length; i ++){
			if(map.containsKey(nums[i])){
				int[] result = new int[2];
				result[0] = map.get(nums[i]);
				result[1] = i;
				return result;
			}else{
				map.put(target - nums[i], i);
			}
		}
		return null;
	}
}
