/**
 *题目：请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy
 *      则经过替换之后的字符串为We%20Are%20Happy
 *
 *思路：如果使用replaceAll方法，则只需要一行代码str.toString.replaceAll(" ", "%20");
 *      如果不使用replaceAll方法，则从前往后遍历，得到空格总数并计算新字符串的长度；
 *      然后从后往前替换，从而防止同一个字符多次移动。
 */

public class Solution{
	public String replaceSpace(StringBuffer str) {
		int sumSpace = 0; 
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == ' ')
				sumSpace ++;
		}
		//计算所需长度并扩容
		int newLength = str.length() + sumSpace * 2;
		str.setLength(newLength);
		
		int oldIndex = str.length() - 1;
		int newIndex = newLength - 1;
		while(oldIndex >= 0 && oldIndex < newIndex){
			if(str.charAt(oldIndex) != ' '){
				str.setCharAt(newIndex--, str.charAt(oldIndex));
			}else{
				str.setCharAt(newIndex--, '0');
				str.setCharAt(newIndex--, '2');
				str.setCharAt(newIndex--, '%');
			}
			oldIndex --;
		}
		return str.toString();
	}
}
