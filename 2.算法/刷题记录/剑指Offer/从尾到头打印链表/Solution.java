/**
 * 题目：输入一个链表，从尾到头打印链表每个节点的值。
 *
 * 思路一：利用递归打印。
 * 思路二：利用栈先进后出特性，先顺序入栈，再顺序出栈打印。
 */

/**
 * public class ListNode {
 *     int val;
 *     ListNode next = null;
 *
 *     ListNode(int val) {
 *         this.val = val;
 *     }
 * }
 */ 

//思路一实现：递归
import java.util.ArrayList;

public class Solution {
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(listNode != null){
			if(listNode.next != null){
				list = printListFromTailToHead(listNode.next);//此处要返回，如果用this.printListFromTailToHead()则会出现list中只有listNode的头结点	
			}
			list.add(listNode.val);
		}
		return list;
	}
}

//思路二实现：栈
import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	if(listNode == null){
    		return list;
    	}
    	Stack<Integer> stack = new Stack<Integer>();
    	while(listNode != null){
    		stack.push(listNode.val);
    		listNode = listNode.next;
    	}

    	while(!stack.isEmpty()){
    		list.add(stack.pop());
    	}
    	return list;
    }
}
