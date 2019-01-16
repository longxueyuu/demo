package com.lxy.zk.main;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.commons.io.Charsets;

/**
 * Created by lxy on 2016/12/17.
 */
public class MyZkSerializer implements ZkSerializer
{
    public Object deserialize(byte[] bytes) throws ZkMarshallingError
    {
        return new String(bytes, Charsets.UTF_8);
    }

    public byte[] serialize(Object obj) throws ZkMarshallingError
    {
        return String.valueOf(obj).getBytes(Charsets.UTF_8);
    }
}
