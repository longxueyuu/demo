package javaclass.bignumber;

import com.sun.org.apache.regexp.internal.CharacterArrayCharacterIterator;

import java.math.BigInteger;

/**
 * Created by lxy on 2017/3/26.
 */
public class BigIntegerTest {
    public static void main(String[] args){
        String str1 = "99999999919999999991999999999";
        String str2 = "99999999919999999991999999999";


        long start = System.nanoTime();
        BigInteger a = new BigInteger(str1);
        BigInteger b = new BigInteger(str2);
        BigInteger c = a.add(b);
        long end = System.nanoTime();
        System.out.println((end - start) + " " + c.toString());

        start = System.nanoTime();
        String result = BigIntegerTest.addBigInteger(str1, str2);
        end = System.nanoTime();
        System.out.println((end - start) + " " + result);


    }

    public static String addBigInteger(String a, String b){
        int[] result = new int[a.length() > b.length() ? a.length() + 1 : b.length() + 1];
        int i = 0, j = 0;
        int carry = 0;
        while(i < a.length()){
            int sum = Character.digit(a.charAt(a.length() - i - 1), 10) + Character.digit(b.charAt(b.length() - j - 1), 10) + carry;
            if(sum >= 10)
            {
                carry = 1;
            }
            int real = sum % 10;
            result[a.length() - i] = real;
            i++;
            j++;
        }
        if(carry > 0)
        {
            result[0] = carry;
        }
        StringBuilder sb = new StringBuilder(result.length);
        for(int x : result){
            sb.append(x);
        }
        return sb.toString();
    }

}
