package com.lxy.jvm;

/**
 * Created by lxy on 30/01/2018.
 */
public class Client {

    public static void main(String[] args) {
        Son son = new Son();
        son.c();
        System.out.println("-----------------");

        Parent p = son;
        p.d();
        System.out.println("-----------------");
        p.a();
    }
}
