package datastructure.main;

import util.CustomizePrint;
import datastructure.sort.internalsort.StableSort;
import datastructure.sort.internalsort.UnstableSort;

public class SortMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] array = new int[]{3,2,1,4,6,5,8,7,9,10,13,14,7,100,15,1,56};
		StableSort.insertionSort(array);
		CustomizePrint.printArray("²åÈëÅÅĞò", array);
		
		int[] array2 = new int[]{3,2,1,4,6,5,8,7,9,10,13,14,7,100,15,1,56};
		StableSort.bubblesort(array2);
		CustomizePrint.printArray("Ã°ÅİÅÅĞò", array2);
		
		int[] array3 = new int[]{3,2,1,4,6,5,8,7,9,10,13,14,7,100,15,1,56};
		UnstableSort.selectionSort(array3);
		CustomizePrint.printArray("Ñ¡ÔñÅÅĞò", array3);
		
		int[] array4 = new int[]{3,2,1,4,6,5,8,7,9,10,13,14,7,100,15,1,56};
		//array4 = new int[]{49,38,65,97,76,13,27,49};
		UnstableSort.shellSort(array4);
		CustomizePrint.printArray("Ğ»¶ûÅÅĞò", array4);
		
		int[] array5 = new int[]{3,2,1,4,6,5,8,7,9,10,13,14,7,100,15,1,56};
		//array5 = new int[]{49,38,65,97,76,13,27,49};
		UnstableSort.quickSort(array5, 0, array5.length-1);
		CustomizePrint.printArray("¿ìËÙÅÅĞò", array5);
		
		
		int[] array6 = new int[]{3,2,1,4,6,5,8,7,9,10,13,14,7,100,15,1,56};
		array6 = new int[]{49,38,65,97,76,13,27,49};
		StableSort.mergingSort(array6);
		CustomizePrint.printArray("¶şÂ·¹é²¢·Çµİ¹éÅÅĞò", array6);
		
		int[] array7 = new int[]{3,2,1,4,6,5,8,7,9,10,13,14,7,100,15,1,56};
		//array7 = new int[]{49,38,65,97,76,13,27,49};
		StableSort.mergingSort(array7, 0, array7.length-1);
		CustomizePrint.printArray("¶şÂ·¹é²¢µİ¹éÅÅĞò", array7);
		
		int[] array8 = new int[]{3,2,1,4,6,5,8,7,9,10,13,14,7,100,15,1,56};
		//array8 = new int[]{49,38,65,97,76,13,27,49};
		UnstableSort.heapSort(array8);
		CustomizePrint.printArray("¶Ñ»ıÅÅĞò", array8);
		
		int[] array9 = new int[]{3,2,1,4,6,5,8,7,9,10,13,14,7,100,15,1,56,1099};
		//array9 = new int[]{49,38,65,97,76,13,27,49};
		StableSort.radixSort(array9, 10);
		System.out.println("length: " + array9.length);
		CustomizePrint.printArray("»ùÊıÅÅĞò", array9);
		
		int[] array10 = new int[]{3,2,1,4,6,5,8,7,9,10,13,14,7,100,15,1,66,56,1099};
		UnstableSort.partition(array10, 0, array10.length - 1);
		System.out.println("length: " + array10.length);
		CustomizePrint.printArray("partition", array10);
		
		int[] array11 = new int[]{3,2,1,4,6,5,8,7,9,10,13,14,7,100,15,1,66,56,1099};
		UnstableSort.randomizeQuickSort(array11, 0, array11.length - 1);
		System.out.println("length: " + array11.length);
		CustomizePrint.printArray("Ëæ»ú»¯¿ìËÙÅÅĞò", array11);
		

	}

}


















