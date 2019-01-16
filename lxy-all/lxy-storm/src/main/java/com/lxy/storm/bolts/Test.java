package com.lxy.storm.bolts;

import org.apache.commons.lang.StringUtils;

/**
 * Created by lxy on 2017/1/15.
 */
class Base {
    static {
        System.out.println("static base");
    }
    Base(){
        System.out.println("Constructor Base");
    }
}

public class Test extends Base{
    static {
        System.out.println("static Test");
    }

    Test()
    {
        System.out.println("Constructor Test");
    }
    public static  void main(String[] args){
        System.out.println("main");
//        Test test = new Test();
//        test.test();
        Integer[] a = new Integer[]{1, 2, 3, 4, 5};
        String str = StringUtils.join(a, ",");
        System.out.println(str);

    }
    public static void test(){
        System.out.print("test");
    }

}
