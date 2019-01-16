package designpattern.decoratorpattern;

public class Decorator implements Component {

	private Component component;
	
	public Decorator(Component component){
		this.component = component;
	}
	
	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		component.doSomething();
	}

}
