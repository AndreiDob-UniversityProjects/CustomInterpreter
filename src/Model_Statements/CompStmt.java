package Model_Statements;

import Model.PrgState;
import Model_DataStructures.MyIStack;

public class CompStmt implements IStmt {
	 IStmt first;
	 IStmt snd;

	public CompStmt(IStmt first, IStmt snd) {
		super();
		this.first = first;
		this.snd = snd;
	}

	@Override
	public PrgState execute(PrgState state) {
		MyIStack<IStmt> stk=state.getExeStack();
		stk.push(snd);
		stk.push(first);
		return null;
	}

	@Override
	public String toString() {
		return " "+ first + " ; " + snd+" ";
	}
	
}

