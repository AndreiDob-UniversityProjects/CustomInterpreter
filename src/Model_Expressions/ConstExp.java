package Model_Expressions;

import Model_DataStructures.MyIDictionary;

public class ConstExp extends Exp{
	
	 private int number;

	public ConstExp(int number) {
		super();
		this.number = number;
	}

	@Override
	public int eval(MyIDictionary<String, Integer> tbl,MyIDictionary<Integer, Integer> hp) {
		return number;
	}

	@Override
	public String toString() {
		return " " + number + " ";
	}
	 
}
