package com.lxy.sort;

/**
 * Created by lxy on 2017/2/17.
 */
public class StableSort {

    public static void insertionSort(int[] array){
        for(int i = 1; i < array.length; i++)
        {
            int j = i - 1;
            int x = array[i];
            for(; j >= 0 && x < array[j]; j--)
            {
                array[j + 1] = array[j];
            }
            array[j + 1] = x;
        }
    }
}
