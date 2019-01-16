package com.lxy.serviceimpl;

import com.lxy.proto.com.lxy.service.DemoSevice;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

/**
 * Created by lxy on 2018/09/12.
 */
public class DemoServiceAsyncImpl implements DemoSevice.AsyncIface{

    @Override
    public void say(String name, AsyncMethodCallback resultHandler) throws TException {
        resultHandler.onComplete(name);
    }

    @Override
    public void add(long a, long b, AsyncMethodCallback resultHandler) throws TException {
        resultHandler.onComplete((a + b) * 10);
    }
}
