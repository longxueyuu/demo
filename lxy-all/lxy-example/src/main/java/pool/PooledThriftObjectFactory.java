package pool;

import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.thrift.transport.TTransport;

/**
 * Created by lxy on 2017/3/1.
 */
public class PooledThriftObjectFactory implements KeyedPooledObjectFactory<String, TTransport>{
    @Override
    public PooledObject<TTransport> makeObject(String key) throws Exception {
        return null;
    }

    @Override
    public void destroyObject(String key, PooledObject<TTransport> p) throws Exception {

    }

    @Override
    public boolean validateObject(String key, PooledObject<TTransport> p) {
        return false;
    }

    @Override
    public void activateObject(String key, PooledObject<TTransport> p) throws Exception {

    }

    @Override
    public void passivateObject(String key, PooledObject<TTransport> p) throws Exception {

    }
}
