package designpattern.strategypattern;

public class StrategyClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Add add = new Add();
		Minus minus = new Minus();
		Multiply multiply = new Multiply();
		Divide divide = new Divide();
		
		Environment evm = new Environment(add);
		
		System.out.println(evm.calculate(10, 5));
		
		evm.setStrategy(minus);
		System.out.println(evm.calculate(10, 5));
		
		evm.setStrategy(multiply);
		System.out.println(evm.calculate(10, 5));
		
		evm.setStrategy(divide);
		System.out.println(evm.calculate(10, 5));
	}

}
