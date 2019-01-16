package designpattern.singletonpattern;

public class ConcurrentSingleTon {
    private ConcurrentSingleTon() {}

    static{
    	System.out.println("外部类初始化！");
    }
    
    private static class LazyHolder {
    	 static{
    	    	System.out.println("内部类初始化！");
    	    }
    	// 这里的私有没有什么意义 
        private static final ConcurrentSingleTon INSTANCE = new ConcurrentSingleTon();
    }

    public static ConcurrentSingleTon getInstance() {
    	// 外围类能直接访问内部类（不管是否是静态的）的私有变量  
    	System.out.println("单例创建！");
        return LazyHolder.INSTANCE;
    }
    
    
   public static void otherStaticMethod()
   {
	   System.out.println("其他方法！");
   }
}