package designpattern.factorypattern.factorymethod;

public class FactoryMethodClient {
	public static void main(String[] args){
		AbstractFactory af = new ConcreteFactoryOfTrain();
		AbstractProduct aProduct = af.createProduct();
		aProduct.run();
		
		af = new ConcreteFactoryOfBus();
		aProduct = af.createProduct();
		aProduct.run();
		
		af = new ConcreteFactoryOfCar();
		aProduct = af.createProduct();
		aProduct.run();
	}
}
