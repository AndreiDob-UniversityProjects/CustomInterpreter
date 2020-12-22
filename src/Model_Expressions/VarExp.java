package Model_Expressions;
import Exception.MyDataStructureException;
import Model_DataStructures.MyIDictionary;
import  Exception.MyExpressionException;

public class VarExp extends Exp{
	 String id;
	
	public String getId() {
		return id;
	}

	public VarExp(String id) {
		super();
		this.id = id;
	}

	@Override
	public int eval(MyIDictionary<String, Integer> tbl,MyIDictionary<Integer, Integer> hp) throws MyExpressionException {
		try {
			return tbl.lookup(id);
		} catch (MyDataStructureException e) {
			throw new MyExpressionException("Expression exception: "+ e.getMessage());
		}
	}

	@Override
	public String toString() {
		return " " + id + " ";
	}
	
	 
}
