package com.lxy.jvm;

/**
 * Created by lxy on 30/01/2018.
 */
public class Parent {

    public void d() {
        System.out.println("parent d()");
        b();
    }

    public void a(){
        System.out.println("parent a()");
        b();
    }

    public void b(){
        System.out.println("parent b()");
    }
}
