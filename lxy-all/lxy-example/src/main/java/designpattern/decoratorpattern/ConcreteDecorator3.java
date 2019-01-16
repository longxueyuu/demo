package designpattern.decoratorpattern;

public class ConcreteDecorator3 extends Decorator {

	public ConcreteDecorator3(Component component) {
		super(component);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		super.doSomething();
		this.doAnotherThing();
	}
	
	private void doAnotherThing(){
		System.out.println("添加的功能D");
	}

}
