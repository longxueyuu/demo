package designpattern.factorypattern.factorymethod;

public class ConcreteFactoryOfBus implements AbstractFactory {

	@Override
	public AbstractProduct createProduct() {
		// TODO Auto-generated method stub
		return new ConcreteBus();
	}

}
