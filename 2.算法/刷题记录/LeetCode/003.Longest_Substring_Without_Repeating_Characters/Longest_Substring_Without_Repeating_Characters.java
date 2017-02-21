/*************************************************************************
	> File Name: Longest_Substring_Without_Repeating_Characters.java
	> Author: wangzhe
	> Mail: 582165168@qq.com 
	> Created Time: 六  3/26 00:14:04 2016
 ************************************************************************/
/**
 * Given a string, find the length of the longest substring without repeating characters. 
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length * is 3. 
 * For "bbbbb" the longest substring is "b", with the length of 1.
 */
public class Solution{
	public int lengthOfLongestSubstring(String s){
		int result = 0;
		Map<Characters, Integer> map = new HashMap<>();
		for(int i = 0, int start = 0; i < s.length(); i ++){
			char c = s.charAt(i);
			if(map.containsKey(c)){
				start = Math.max(map.get(c) + 1, start);
			}
			result = Math.max(ret, i - start + 1);
			map.put(c, i);
		}
		return result;
	}
}
