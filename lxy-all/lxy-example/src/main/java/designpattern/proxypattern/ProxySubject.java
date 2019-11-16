package designpattern.proxypattern;

public  class ProxySubject extends AbstractSubject {

	AbstractSubject abstractSubject;// �����ɫ�ڲ�������ʵ��ɫ
	
	@Override
	public void request() {
		// TODO Auto-generated method stub
		
		preRequest();//����˽�з���
		
		if(null == abstractSubject){
			
			abstractSubject = new RealSubject();
		}	
		abstractSubject.request();//��ʵ��ɫ����ɵ�����
		
		postRequest();//����˽�з���
	}

	
	private void preRequest(){
		System.out.println("preRequest executed by ProxySubject!");
	
	}
	
	private void postRequest(){
		System.out.println("postRequest executed by ProxySubject!");
	}
}
