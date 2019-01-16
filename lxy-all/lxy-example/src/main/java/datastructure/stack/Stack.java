package datastructure.stack;

import datastructure.node.GenericNode;

public class Stack<E> {
	
	private GenericNode<E> top;
	
	public void init()
	{
		top = null;
	}
	
	public Boolean isEmpty()
	{
		if(top == null)
		{
			return true;
		}
		return false;
	}
	
	public Boolean push(E e)
	{
		if(top == null)
		{
			top = new GenericNode<E>();
			top.data = e;
			top.next = null;
			return true;
		}
		else{
			GenericNode<E> p = new GenericNode<E>();
			p.data = e;
			p.next = top;
			top = p;
			return true;
		}
	}
	
	public E pop()
	{
		if(top == null)
		{
			return null;
		}else{
			E data = top.data;
			top = top.next;
			return data;
		}
	}
	
	public E getTop()
	{
		if(top == null)
		{
			return null;
		}else{
			return top.data;
		}
	}
	
	public int size()
	{
		int size = 0;
		GenericNode<E> p = top;
		if(p == null)
		{
			return 0;
		}
		while(p != null)
		{
			size++;
			p = p.next;
		}
		
		return size;
	}
}





