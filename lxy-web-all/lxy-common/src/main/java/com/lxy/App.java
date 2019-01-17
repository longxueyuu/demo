package com.lxy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Object[] a = new Object[10];
        if (a[5] == null) {
            System.out.println("a[x] is null");
        }

        System.out.println( "Hello World!" + a[0] );
    }
}
