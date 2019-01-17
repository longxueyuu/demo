package com.lxy.inherit;

/**
 * Created by lxy on 19/12/2017.
 */
public class SomeBody extends Person {

    public void say() {

        System.out.println("say in Somebody");
    }

    @Override
    public void scream() {
        System.out.println(this.getClass().getName());
        System.out.println("screaming in Somebody");
    }
}
