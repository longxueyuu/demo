package datastructure.sort.internalsort;

import java.util.Random;

import util.CustomizePrint;

public class UnstableSort {

	
	/**
	 * 选择排序算法
	 * 
	 * @param array
	 */
	public static void selectionSort(int[] array)
	{
		
		for(int i = 0; i < array.length - 1; i++)
		{ 
			int k = i;// 第i趟排序， 假定第i个元素为最小值，与剩余n-i-1个比较获得n-i元素的最小值
			for(int j = i + 1; j < array.length; j++)
			{
				if(array[j] < array[k])
				{
					k = j;
				}
			}
			if(k != i)
			{
				int temp = array[k];
				array[k] = array[i];
				array[i] = temp;
			}	
			// CustomizePrint.printArray("第" + i + "趟排序结果", array);
		}
	}
	
	/**
	 * 谢尔排序算法
	 * 
	 * @param array
	 */
	public static void shellSort(int[] array)
	{
		int gap = array.length; // 间隔初始为数组长度的一半
		
		for(;gap > 1;)
		{
			gap = gap /2; // 间隔大于1时，每次减半
			for(int i = 0; i < gap; i++) // 在当前gap值，子序列数目为gap， 对每个子序列进行泡排序（也可对每个子序列采用其他排序方法）
			{
				int flag = 1; // flag 为1表示子序列有元素交换动作，为0表示无元素交换的动作
				for(; flag != 0;) // 无元素交换动作说明该子序列已经有序，结束该循环，进行下一个子序列的排序
				{
					flag = 0;
					for(int j = i; j + gap < array.length; j = j + gap)
					{
						if(array[j] > array[j + gap])
						{
							int temp = array[j];
							array[j] = array[j + gap];
							array[j + gap] = temp;
							flag = 1;
						}
					}
				}
			}
			//CustomizePrint.printArray("gap值为" + gap + "的排序结果", array);
		}
	}
	
	/**
	 * 快速排序算法
	 * 
	 * @param array
	 */
	public static void quickSort(int[] array, int low, int high)
	{
		int i = low;
		int j = high;
		if(low >= high) //递归出口
		{
			return;
		}
		int x = array[low];
		while(i < j){
			while(i < j && x < array[j]) j--;
			if(i < j)
			{
				array[i] = array[j];
				i++;
			}
			while(i < j && x > array[i]) i++;
			if(i < j)
			{
				array[j] = array[i];
				j--;
			}
		}
		array[i] = x;
		//CustomizePrint.printArray("递归排序结果", array);
		quickSort(array, low, i-1);
		quickSort(array, i+1, high);
	}
	
	/**
	 * 堆积排序算法
	 * 
	 * @param array
	 */
	public static  void heapSort(int[] array)
	{
		int n = array.length;
		int i;
		for(i = n/2 - 1; i >= 0; i--)// 将一个任意序列转换为一个堆积序列
		{
			adjustToHeapSequence(array, i, n);
		}
		for(int j = n-1; j > 0; j--)
		{
			int temp = array[j];
			array[j] = array[0];
			array[0] = temp;
			adjustToHeapSequence(array, 0, j);
			//CustomizePrint.printArray("第" + (n-j) + "趟排序结果", array);
		}
	}
	
	/**
	 * 将根节点的左右子树都是一个堆积的非堆积调整为一个堆积
	 * 
	 * @param array
	 * @param i the position of the root node in the array
	 * @param n the length of the array
	 */
	private static void adjustToHeapSequence(int[] array, int i, int n)
	{
		int temp = array[i];
		int j = 2 * (i+1);
		while(j <= n)
		{
			if(j < n && array[j-1] < array[j])
			{
				j++;
			}
			if(temp >= array[j-1])
			{
				break;
			}
			array[j/2 - 1] = array[j - 1];
			j = 2 * j;
		}
		array[j/2 - 1] = temp;
	}
	
	
	
	/**
	 * 随机化快速排序算法
	 * 
	 * @param array
	 * @param low
	 * @param high
	 */
	public static void randomizeQuickSort(int[] array, int low, int high)
	{
		if(low < high)
		{
			int position = randomizedPartition(array,low,high);
			randomizeQuickSort(array, low, position-1);
			randomizeQuickSort(array, position + 1, high);
		}
	}
	
	/**
	 * 随机化划分区域
	 * 
	 * @param array
	 * @param low
	 * @param high
	 * @return
	 */
	public static int randomizedPartition(int[] array, int low, int high)
	{
		int t = random(low,high);
		int temp = array[t];
		array[t] = array[low];
		array[low] = temp;
		return partition(array,low,high);
	}
	
	/**
	 * 以第一个数作为主元划分区域
	 * 
	 * @param array
	 * @param low
	 * @param high
	 * @return
	 */
	public static int partition(int[] array, int low, int high)
	{
		int baseElement = array[low];
		int i = low;
		for(int j = low + 1; j <= high; j++)
		{
			if(array[j] < baseElement)
			{
				i++;
				int temp = array[j];
				array[j] = array[i];
				array[i] = temp;
			}
		}
		int temp = array[i];
		array[i] = baseElement;
		array[low] = temp;
		return i;
	}
	
	/**
	 * produce a random number from low to high
	 * 
	 * @param low
	 * @param high
	 * @return
	 */
	private static int random(int low, int high)
	{
		Random rdm = new Random();
		return low + rdm.nextInt(high - low + 1);
	}
	
}


























