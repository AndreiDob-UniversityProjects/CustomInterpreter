package Model_Statements;

import Model.PrgState;
import Model_Expressions.Exp;

public class WhileStmt implements IStmt {

	private Exp exp;
	private IStmt st;
	
	
	public WhileStmt(Exp exp, IStmt st) {
		super();
		this.exp = exp;
		this.st = st;
	}


	@Override
	public PrgState execute(PrgState state) throws Exception {
		
		if(exp.eval(state.getSymTable(), state.getHeap())==0)
			return state;
		else {
			state.getExeStack().push(this);
			state.getExeStack().push(st);
			return null;
		}
	}


	@Override
	public String toString() {
		return "( while(" + exp + ") " + st + " )";
	}
	
}
