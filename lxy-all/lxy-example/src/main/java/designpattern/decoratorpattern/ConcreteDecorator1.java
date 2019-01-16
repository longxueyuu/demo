package designpattern.decoratorpattern;

public class ConcreteDecorator1 extends Decorator {

	public ConcreteDecorator1(Component component) {
		super(component);
	}

	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		super.doSomething();
		this.doAnotherThing();
	}
	
	private void doAnotherThing(){
		System.out.println("添加的功能B");
	}
	
	

}
