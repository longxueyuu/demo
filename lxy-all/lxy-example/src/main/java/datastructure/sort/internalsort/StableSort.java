package datastructure.sort.internalsort;

import datastructure.node.GenericNode;
import datastructure.node.Node;
import datastructure.queue.Queue;

public class StableSort {
	
	/**
	 * 插入排序算法
	 * 
	 * @param array
	 */
	public static void insertionSort(int[] array)
	{
		
		for(int i = 1; i < array.length; i++)
		{
			int temp = array[i];
			int j;
			for(j = i - 1; j >= 0 && temp < array[j]; j--)
			{
					array[j+1] = array[j];
			}
			array[j+1] = temp;
			//CustomizePrint.printArray("第" + i + "趟排序结果", array);
		}
	}
	
	/**
	 * 冒泡排序算法
	 * 
	 * @param array
	 */
	public static void bubblesort(int[] array)
	{
		int flag = 1; // 当flag = 1时, 表示在一趟排序有元素交换，为0则表示没有元素交换，而没有元素交换则表示排序完成
		for(int i = 0; i < array.length - 1 && flag == 1; i++)
		{
			flag = 0;
			for(int j = 0; j < array.length - i - 1; j++)
			{
				if(array[j] > array[j+1])
				{
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					flag = 1;
				}
			}
			//CustomizePrint.printArray("第" + i + "趟排序结果", array);
		}
	}
	
	/**
	 * 二路归并排序递归算法
	 * 
	 * @param array
	 * @param low
	 * @param high
	 */
	public static void mergingSort(int[] array, int low, int high)
	{
		int mid = (low + high) / 2;
		if(low < high)
		{
			mergingSort(array, low, mid);
			mergingSort(array, mid + 1, high);
			merge(array, low, mid, high);
			//CustomizePrint.printArray("子序列长度为" + (mid-low+1) + "排序结果", array);
		}
	}
	
	/**
	 * 二路归并排序非递归算法
	 * 
	 * @param array
	 */
	public static void mergingSort(int[] array)
	{
		int t = 1;
		int n = array.length;
		while(t < n)
		{
			mPass(array, t);
			// CustomizePrint.printArray("子序列长度为" + t + "排序结果", array);
			t *= 2;
		}
	}
	
	/**
	 * 归并两个子序列算法
	 * 
	 * @param src  the array needed to be merged
	 * @param s  the first subsequence start position of the src
	 * @param u  the first subsequence end position of the src
	 * @param v  the second sequence end position of src
	 */
	private static void merge(int[] src, int s, int u, int v)
	{
		/* 将有序的src[s..u]和src[u+1..v]归并为有序的src[s..v] */
		int[] dst = new int[src.length];
		int i,j,q;
		i = s;
		j = u + 1;
		q = s;
		while(i <= u && j <= v)
		{
			if(src[i] <= src[j])
			{
				dst[q++] = src[i++];
			}else{
				dst[q++] = src[j++];
			}
		}
		while(i <= u) // 将src[s..u]中剩余的元素src[i..u]复制到dst中
		{
			dst[q++] = src[i++];
		}
		while(j <= v) // 将src[u+1..v]中剩余的元素src[j..v]复制到dst中
		{
			dst[q++] = src[j++];
		}
		
		for(int k = s; k <= v; k++) // 将归并后的dst复制到src中,只复制归并的两个子序列
		{
			src[k] = dst[k];
		}
	}
	
	/**
	 * 一趟归并扫描算法
	 * 
	 * @param src
	 * @param t the length of subsequence
	 */
	private static void mPass(int[] src, int t)
	{
		int i = 0;
		int n = src.length-1;
		while(n-i+1 >= 2*t)
		{
			merge(src, i, i+t-1, i+2*t-1);
			i = i + 2 * t;
		}
		if(n-i+1 > t) // 剩下的元素个数大于t，则将剩下两个不等长序列归并
		{
			merge(src, i, i+t-1, n);
		}else{ //剩下的元素个数小于等于t，则将剩下的元素直接复制到前一个子序列后面
//			for(int j = i; j <= n; j++)
//			{
//				dst[j] = src[j];
//			}
		}
	}
	
	/**
	 * 基数排序算法
	 * 
	 * @param array
	 * @param r
	 * @param d
	 */
	public static void radixSort(int[] array, int r)
	{
		int d = getMaxDigit(array,r);
		Node front = null, rear = null, p = null; // front为总队队头指针，rear为总队队尾指针
		Node[] head = new Node[r]; // 各分队队头指针
		Node[] end = new Node[r]; // 各分队队尾指针
		int x;
		front = transferToQueue(array); // 将数组转换为一个队列
		for(int j = 1; j <= d; j++)
		{
			while(front != null) // 将总队各节点加入到各个分队
			{
				p = front;
				x = get(p,j); // 分解p结点中数据的第j位返回
				if(head[x] == null)
				{
					head[x] = p;
				}else{
					end[x].next = p;
				}
				end[x] = p;
				front = front.next;
			}
			
			int k = 0;
			while(head[k] == null) //查找第1个非空分队队号
			{
				k++;
			}
			front = head[k];
			rear = end[k];
			head[k] = null; // 该分队加入总队后，置空分队
			for(int m = k + 1; m < r; m++) // 依次把其余各非空分队插入总队
			{
				if(head[m] != null) 
				{
					rear.next = head[m];
					rear = end[m];
					head[m] = null;
				}
			}
			rear.next = null; // 总队队尾的next置空
		}
		
		for(int n = 0; n < array.length; n++) // 将队列还原为数组
		{
			if(front == null)
			{
				break;
			}
			array[n] = front.data;
			front = front.next;
		}
	}
	
	/**
	 * 获取数组中最大元素的位数
	 * 
	 * @param array
	 * @param r
	 * @return
	 */
	private static int getMaxDigit(int[] array, int r)
	{
		int k = 0;
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] > array[k]) 
			{
				k = i;
			}
		}
		return Integer.toString(array[k], r).length();
	}
	
	/**
	 * 将数组转换为一个队列
	 * 
	 * @param array
	 * @return  return the reference of the queue first node
	 */
	private static Node transferToQueue(int[] array)
	{
		Node front = null, rear = null, p = null;
		for(int i = 0; i < array.length; i++)
		{
			p = new Node();
			p.data = array[i];
			p.next = null;
			if(front == null)
			{
				front = p;
			}else{
				rear.next = p;
			}
			rear = p;
		}
		return front;
	}
	
	/**
	 * 分离出一个整数的第i位数
	 * 
	 * @param node
	 * @param i
	 * @return
	 */
	private static int get(Node node, int i)
	{
		int x = -1;
		int number = node.data;
		for(int j = i; j > 0; j--)
		{
			x = number % 10;
			number = number / 10;
		}
		return x;
	}
	
}
























