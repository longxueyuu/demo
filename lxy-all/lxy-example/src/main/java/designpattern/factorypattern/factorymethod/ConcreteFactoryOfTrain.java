package designpattern.factorypattern.factorymethod;

public class ConcreteFactoryOfTrain implements AbstractFactory {

	@Override
	public AbstractProduct createProduct() {
		// TODO Auto-generated method stub
		return new ConcreteTrain();
	}

}
