package com.lxy;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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

   // get millis of date using joda time
    public long getMillis(String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dateTime = formatter.parseDateTime(date);
        return dateTime.getMillis();
    }

    public long getMillisOfDateObject(DateTime date) {
        return date.getMillis();
    }
}
