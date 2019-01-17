package com.lxy.jvm;

/**
 * Created by lxy on 30/01/2018.
 */
public class Son extends Parent {

    public void a(){
        System.out.println("Son a()");
        b();
    }

    public void b(){
        System.out.println("Son b()");
    }

    public void c() {
        System.out.println("Son c()");
        super.a();
    }
}
