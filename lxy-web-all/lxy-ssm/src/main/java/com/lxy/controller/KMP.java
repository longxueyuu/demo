package com.lxy.kotlin.controller;

import java.util.Arrays;

/**
 * Created by lxy on 2017/5/20.
 */
public class KMP {
    public static void main(String[] args)
    {
        String str = "aaaaaa";

        System.out.println(Arrays.toString(KMP.getNext(str)));


        String str2 = "ABCDABCE";
        System.out.println(Arrays.toString(KMP.getNext(str2)));
    }

    public static int[] getNext(String str)
    {
        int len = str.length();
        int[] next = new int[len];

        next[0] = -1;
        int k = -1;
        int j = 0;
        while(j < len - 1)
        {
            if(k == -1 || str.charAt(j) == str.charAt(k))
            {
                k++;
                j++;
                next[j] = k;
            }else{
                k = next[k];
            }
        }
        return next;
    }
}
