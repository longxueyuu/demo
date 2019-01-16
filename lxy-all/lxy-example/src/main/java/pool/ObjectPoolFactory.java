package pool;

import org.apache.commons.pool2.KeyedObjectPool;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.apache.thrift.transport.TTransport;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lxy on 2017/3/1.
 */
public class ObjectPoolFactory {

    Map<String, KeyedObjectPool> poolMap = new ConcurrentHashMap<>();

    GenericKeyedObjectPoolConfig config = new GenericKeyedObjectPoolConfig();


    public void initPool(){
        KeyedObjectPool objectPool = new GenericKeyedObjectPool<String, TTransport>(new PooledThriftObjectFactory(), config);
        poolMap.put("IP", objectPool);
    }

    public TTransport getConnection(String key) throws Exception {
        KeyedObjectPool objectPool = poolMap.get(key);
        TTransport transport = (TTransport)objectPool.borrowObject(key);
        return transport;
    }

}
