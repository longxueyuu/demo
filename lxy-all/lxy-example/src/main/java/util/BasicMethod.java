package util;

public class BasicMethod {
	
	/**
	 * 计算x的n次方
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	public static double power(double x, int n)
	{
		
		if(n == 0)
		{
			return 1;
		}
		if(n > 0)
		{
			if(n % 2 == 0)
			{
				return Math.pow(power(x, n/2), 2);
			}else{
				return Math.pow(power(x,(n-1)/2), 2) * x;
			}
		}else{
			n = Math.abs(n);
			if(n % 2 == 0)
			{
				return Math.pow(1/power(x, n/2), 2);
			}else{
				return Math.pow(1/power(x,(n-1)/2), 2) * (1/x);
			}
		}
	}
	
	/**
	 * 计算第n个斐波那契数
	 * 
	 * @param n
	 * @return
	 */
	public static int fibonacci(int n)
	{
		if(n == 0)
		{
			return 0;
		}
		if(n == 1)
		{
			return 1;
		}
		return fibonacci(n-1) + fibonacci(n-2);
	}
	
	/**
	 * 折半搜索
	 * 
	 * @param array
	 * @param low
	 * @param high
	 * @param x
	 * @return
	 */
	public static int binarySearch(int[] array, int low, int high, int x)
	{
		int mid = (low + high) / 2;
		if(low <= high)
		{
			if(array[mid] == x)
			{
				return mid;
			}
			if(array[mid] < x)
			{
				low = mid + 1;
				return binarySearch(array, low, high, x);
			}else{
				high = mid - 1;
				return binarySearch(array, low, high, x);
			}
		}
		return -1;
	}
	
	
}
