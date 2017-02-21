/**
 * 问题：用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * 思路：元素从stack1入栈，从stack2出栈；向stack1入栈时直接入栈，从stack2出栈时，当stack2不为空则直接出栈，
 *		 当stack2同为空时，检查stack1是否为空，若不为空，则将stack1中元素全部顺序出栈入栈到stack2中，若为空，则抛异常。
 */

import java.util.Stack;

public class Solution {
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();

	public void push(int node){
		stack1.push(node);
	}

	public int pop(){
		if(stack2.isEmpty() && stack1.isEmpty()){
			throw new RumtimeException("The Queue is Empty!");
		}
		if(stack2.isEmpty()){
			while(!stack1.isEmpty()){
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}
