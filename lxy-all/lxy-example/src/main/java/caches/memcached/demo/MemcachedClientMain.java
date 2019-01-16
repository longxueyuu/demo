package caches.memcached.demo;

//import com.danga.MemCached.MemCachedClient;
//import com.danga.MemCached.SockIOPool;
//
//public class MemcachedClientMain {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//
//				String[] server = {"192.168.234.101:11211"};
//				//初始化SockIOPool,管理memcached连接池
//				SockIOPool pool = SockIOPool.getInstance();
//				pool.setServers(server);
//
//				pool.setFailover(true);
//
//				pool.setInitConn(10);
//
//				pool.setMinConn(5);
//
//				pool.setMaxConn(100);
//
//				pool.setMaintSleep(30);
//
//
//				pool.setNagle(false);
//
//				pool.setSocketTO(3000);
//
//				pool.setAliveCheck(true);
//
//				pool.initialize();
//
//				//建立memcachedclient 对象
//				MemCachedClient client = new MemCachedClient();
//
//
//				for(int i=0;i<5;i++){
//					  /*将对象加入到memcached缓存*/
//				  boolean success = client.set("" + i, "Hello!");
//					  /*从memcached缓存中按key值取对象*/
//					           String result = (String) client.get("" + i);
//					           System.out.println(String.format("set( %d ): %s", i, success));
//					            System.out.println(String.format("get( %d ): %s", i, result));
//
//				}
//
////				for(int i=0;i<10;i++){
////					client.delete(""+i);
////				}
//
//			}
//
//}
