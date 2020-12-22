package Model_Expressions;

import Model_DataStructures.MyIDictionary;
import Exception.MyExpressionException;
public class ArithExp extends Exp{
	 private Exp e1;
	 private Exp e2;
	 private String op; 
	 
	
	public ArithExp(Exp e1, String op,Exp e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
		this.op = op;
	}

	@Override
	public int eval(MyIDictionary<String, Integer> tbl,MyIDictionary<Integer, Integer> hp) throws MyExpressionException {
		if (op=="+") 
			return (e1.eval(tbl,hp)+e2.eval(tbl,hp));
		else if (op=="-") 
			return (e1.eval(tbl,hp)-e2.eval(tbl,hp));
		else if (op=="*") 
			return (e1.eval(tbl,hp)*e2.eval(tbl,hp));
		else if (op=="/") {
			if(e2.eval(tbl,hp)==0)
				throw new MyExpressionException("Expression exception: Cannot divide by 0");
			else
				return (e1.eval(tbl,hp)/e2.eval(tbl,hp));
		}
			
		else
			throw new MyExpressionException("Expression exception: Wrong operation");
	}

	@Override
	public String toString() {
		return e1 + op+ e2;
	}
	
	
}