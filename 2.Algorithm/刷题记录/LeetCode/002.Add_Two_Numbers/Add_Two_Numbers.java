/*************************************************************************
	> File Name: Add_Two_Numbers.java
	> Author: wangzhe
	> Mail: 582165168@qq.com 
	> Created Time: 六  3/19 14:18:17 2016
 ************************************************************************/
/**
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 */

/**
 *   Definition for singly-linked list.
 *   public class ListNode {
 *       int val;
 *       ListNode next;
 *       ListNode(int x) { val = x; }
 *   }
 */
public class Solution {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = l1;
			int sum = 0;
			while(l1 != null || l2 != null ){
				sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val);
				if(sum >= 10){
					sum = sum % 10;
					l1.val = sum;
					if(l1.next == null){
						l1.next = new ListNode(0);
					}
					l1.next.val = l1.next.val + 1;
				}else{
					l1.val = sum;
				}
				l1 = (l1 == null) ? l1 : l1.next;
				l2 = (l2 == null) ? l2 : l2.next;
			}
			return head;
	}
}
