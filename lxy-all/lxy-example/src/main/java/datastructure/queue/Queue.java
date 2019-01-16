package datastructure.queue;

import datastructure.node.GenericNode;


public class Queue<E> {

	private GenericNode<E> front = null;
	private GenericNode<E> rear = null;
	
	public void init()
	{
		front = null;
		rear = null;
	}
	
	public Boolean isEmpty()
	{
		if(front == null)
		{
			return true;
		}else{
			return false;
		}
	}
	
	public Boolean add(E e)
	{
		GenericNode<E> q = new GenericNode<E>();
		q.data = e;
		q.next = null;
		if(front == null)
		{
			front = q;
		}else{
			rear.next = q;
		}
		rear = q;
		return true;
	}
	
	public E getFront()
	{
		if(front == null)
		{
			return null;
		}else{
			return front.data;
		}
	}
	
	public E deleteFront()
	{
		if(front == null)
		{
			return null;
		}else{
			E data = front.data;
			front = front.next;
			return data;
		}
	}
	
	public int size()
	{
		int size = 0;
		GenericNode<E> q = front;
		while(q != null)
		{
			size++;
			q = q.next;
		}
		return size;
	}
}
