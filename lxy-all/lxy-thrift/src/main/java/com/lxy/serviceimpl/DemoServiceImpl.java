package com.lxy.serviceimpl;

import com.lxy.proto.com.lxy.service.DemoSevice;
import org.apache.thrift.TException;

/**
 * Created by lxy on 2017/2/8.
 */
public class DemoServiceImpl implements DemoSevice.Iface{
    @Override
    public String say(String name) throws TException {
        return name;
    }

    @Override
    public long add(long a, long b) throws TException {
        return a + b;
    }
}
