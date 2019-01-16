package designpattern.strategypattern;

public class Environment {
	private StrategyPattern strategy;
	
	public Environment(StrategyPattern strategy){
		this.strategy = strategy;
	}
	
	public StrategyPattern getStrategy() {
		return strategy;
	}
	
	public void setStrategy(StrategyPattern strategy) {
		this.strategy = strategy;
	}

	public int calculate(int a, int b){
		
		return strategy.calculate(a, b);
	}
}
