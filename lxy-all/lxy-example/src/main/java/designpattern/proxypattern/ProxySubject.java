package designpattern.proxypattern;

public  class ProxySubject extends AbstractSubject {

	AbstractSubject abstractSubject;// 代理角色内部引用真实角色
	
	@Override
	public void request() {
		// TODO Auto-generated method stub
		
		preRequest();//代理私有方法
		
		if(null == abstractSubject){
			
			abstractSubject = new RealSubject();
		}	
		abstractSubject.request();//真实角色所完成的事情
		
		postRequest();//代理私有方法
	}

	
	private void preRequest(){
		System.out.println("preRequest executed by ProxySubject!");
	
	}
	
	private void postRequest(){
		System.out.println("postRequest executed by ProxySubject!");
	}
}
