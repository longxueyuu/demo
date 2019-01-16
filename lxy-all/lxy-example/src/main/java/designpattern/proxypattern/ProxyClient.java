package designpattern.proxypattern;

public class ProxyClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractSubject abstractSubject = new ProxySubject();
		//ProxySubject abstractSubject = new ProxySubject();
		
		abstractSubject.request();
	}

}
