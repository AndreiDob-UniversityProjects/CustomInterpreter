package Model_Statements;

import Model.PrgState;
import Model_DataStructures.MyIDictionary;
import Model_DataStructures.MyIStack;
import Model_Expressions.Exp;
import Exception.MyExpressionException;
import Exception.MyStmtExecException;

public class AssignStmt implements IStmt {

	public AssignStmt(String id, Exp exp) {
		super();
		this.id = id;
		this.exp = exp;
	}

	private String id;
	private Exp exp;
	
	@Override
	public PrgState execute(PrgState state) {
		MyIStack<IStmt> stk=state.getExeStack();
		MyIDictionary<String,Integer> symTbl= state.getSymTable();
		int val = 0;
		try {
			val = exp.eval(symTbl,state.getHeap());
		} catch (MyExpressionException e) {
			throw new MyStmtExecException("Statement exception: "+e.getMessage());
		}

		if (symTbl.isDefined(id) )
			symTbl.update(id, val);
		else 
			symTbl.add(id,val);
		return null;
	}

	@Override
	public String toString() {
		return id + "=" + exp;
	}

}
