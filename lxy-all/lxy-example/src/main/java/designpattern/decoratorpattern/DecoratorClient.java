package designpattern.decoratorpattern;

public class DecoratorClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Component component = new ConcreteComponent();
		
		Component component2 = new ConcreteDecorator1(component);
		Component component3 = new ConcreteDecorator2(component2);
		Component component4 = new ConcreteDecorator3(component3);
		
		component4.doSomething();
	}

}
