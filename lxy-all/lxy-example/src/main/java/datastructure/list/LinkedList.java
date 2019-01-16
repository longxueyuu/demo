package datastructure.list;

import datastructure.node.GenericNode;


public class LinkedList<E> {
	private GenericNode<E> head = null;
	
	
	public Boolean add(E e)
	{
		GenericNode<E> p = head;
		if(p == null)
		{
			p = new GenericNode<E>();	
			p.data = e;
			p.next = null;
			head = p;
			return true;
		}
		while(p.next != null)
		{
			p = p.next;
		}
		p.next = new GenericNode<E>();
		p.next.data = e;
		p.next.next = null;
		return true;
	}
	
	public E get(int index)
	{
		int i = 0;
		GenericNode<E> p = head;
		while(p != null)
		{
			if(i == index)
			{
				return p.data;
			}else
			{
				i++;
				p = p.next;
			}
		}
		return null;
	}
	
	public int size()
	{
		int size = 0;
		GenericNode<E> p = head;
		while(p != null)
		{
			size++;
			p = p.next;
		}
		
		return size;
	}
	
	public Boolean remove()
	{
		head = null;
		return true;
	}
}
