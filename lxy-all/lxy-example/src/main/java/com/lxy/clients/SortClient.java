package com.lxy.clients;

import com.lxy.sort.StableSort;

/**
 * Created by lxy on 2017/2/17.
 */
public class SortClient {

    public static void main(String[] args){
        int[] array = {7, 19, 4, 21, 20, 3, 27, 35,100, 45};
        StableSort.insertionSort(array);
        printArray(array);

    }

    public static void printArray(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + ", ");
        }
        System.out.println("");
    }
}
