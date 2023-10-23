package com.example.autoconfigure;


public class ExampleBizService {
    private ExampleProperties prop;

    public ExampleBizService(ExampleProperties prop) {
        this.prop = prop;
    }

    public ExampleProperties getExampleProp() {
        return this.prop;
    }

    public String getBizDesc() {
        return this.prop.getName() + ":" + this.prop.getDescription();
    }
}
