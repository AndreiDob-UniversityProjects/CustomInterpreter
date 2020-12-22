package Model_Statements;

import Model.PrgState;
import Model_DataStructures.MyIList;
import Model_Expressions.Exp;
import Exception.MyException;
import Exception.MyExpressionException;

public class PrintStmt implements IStmt {

	private Exp exp;
	
	public PrintStmt(Exp exp) {
		super();
		this.exp = exp;
	}

	@Override
	public PrgState execute(PrgState state) {
		MyIList<Integer> out=state.getOut();
		try {
			out.add_back(exp.eval(state.getSymTable(),state.getHeap()));
		} catch (MyExpressionException e) {
			throw new MyException(e.getMessage());
		}
		return null;
	}

	@Override
	public String toString() {
		return "Print(" + exp + ")";
	}

}
