package designpattern.singletonpattern;

public class ConcurrentSingleTon {
    private ConcurrentSingleTon() {}

    static{
    	System.out.println("�ⲿ���ʼ����");
    }
    
    private static class LazyHolder {
    	 static{
    	    	System.out.println("�ڲ����ʼ����");
    	    }
    	// �����˽��û��ʲô���� 
        private static final ConcurrentSingleTon INSTANCE = new ConcurrentSingleTon();
    }

    public static ConcurrentSingleTon getInstance() {
    	// ��Χ����ֱ�ӷ����ڲ��ࣨ�����Ƿ��Ǿ�̬�ģ���˽�б���  
    	System.out.println("����������");
        return LazyHolder.INSTANCE;
    }
    
    
   public static void otherStaticMethod()
   {
	   System.out.println("����������");
   }
}