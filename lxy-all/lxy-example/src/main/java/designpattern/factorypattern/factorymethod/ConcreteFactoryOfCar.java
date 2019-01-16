package designpattern.factorypattern.factorymethod;

import designpattern.factorypattern.factorymethod.ConcreteCar;

public class ConcreteFactoryOfCar implements AbstractFactory {
	
	@Override
	public AbstractProduct createProduct() {
		// TODO Auto-generated method stub
		return new ConcreteCar();
	}

}
