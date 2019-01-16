package util;

public class CustomizePrint {

	/**
	 * 打印一维数组
	 * 
	 * @param description
	 * @param array
	 */
	public static void printArray(String description, int array[])
	{
		
		System.out.print("\n" + description + ":");
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
		System.out.print("\n");
	}
	
	/**
	 * 打印二维数组
	 * 
	 * @param description
	 * @param array
	 */
	public static void print2DArray(String description, int[][] array)
	{
		System.out.println(description + "长度:" + array.length);
		for(int[] i : array)
		{
			for(int j : i)
			{
				System.out.print(j + "		");
			}
			System.out.print("\n");
		}
	}
}
