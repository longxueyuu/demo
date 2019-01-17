package com.lxy.inherit;

/**
 * Created by lxy on 19/12/2017.
 */
public abstract class Person {

    public void sing() {
        System.out.println(this.getClass().getName());
        scream();
    }


    public abstract void scream();

}
