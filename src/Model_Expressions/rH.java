package Model_Expressions;

import Exception.MyDataStructureException;
import Exception.MyExpressionException;
import Model_DataStructures.MyIDictionary;

public class rH extends Exp {
	private String var_name;
	
	public rH(String var_name) {
		super();
		this.var_name = var_name;
	}
	
	@Override
	public int eval(MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> hp)throws MyExpressionException {
		try {
			Integer address=tbl.lookup(var_name);
			return hp.lookup(address);
		} catch (MyDataStructureException e) {
			throw new MyExpressionException("Expression exception: "+ e.getMessage());
		}
	}

	@Override
	public String toString() {
		return " rH(" + var_name + ") ";
	}
	
}
