package Model_Expressions;

import Exception.MyExpressionException;
import Model_DataStructures.MyIDictionary;

public class BoolExp extends Exp {

	private Exp e1;
	private Exp e2;
	private String op; 
	
	public BoolExp(Exp e1,String op, Exp e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
		this.op = op;
	}

	@Override
	public int eval(MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> hp)
			throws MyExpressionException {
		int n1= e1.eval(tbl,hp);
		int n2= e2.eval(tbl,hp);
		if (op=="<" && n1<n2) 
			return 1;
		else if (op=="<="&& n1<=n2) 
			return 1;
		else if (op==">"&& n1>n2) 
			return 1;
		else if (op==">="&& n1>=n2) 
			return 1;
		else if (op=="=="&& n1==n2) 
			return 1;
		else if (op=="!="&& n1!=n2) 
			return 1;	
		else if(op==">"||op==">="||op==">"||op=="<"||op=="<="||op=="=="||op=="!=")
			return 0;
		throw new MyExpressionException("Expression exception: Wrong operation");
	}
	
	@Override
	public String toString() {
		return e1 + op+ e2;
	}
	

}
