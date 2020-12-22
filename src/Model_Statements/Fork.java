package Model_Statements;

import Model.PrgState;
import Model_DataStructures.MyDictionary;
import Model_DataStructures.MyIDictionary;
import Model_DataStructures.MyIStack;
import Model_DataStructures.MyStack;

public class Fork implements IStmt {
	IStmt stmt;
	public Fork(IStmt stmt) {
		super();
		this.stmt = stmt;
	}
	
	@Override
	public PrgState execute(PrgState state) throws Exception {
		
		MyIDictionary<String,Integer> dict= state.getSymTable().clone();
		MyIStack<IStmt> stk=new MyStack<IStmt>();
		PrgState prg = new PrgState(stk,dict,state.getOut(),state.getFiletbl(),state.getHeap(),state.getSemtable(), stmt);
		
		return prg;
	}

	@Override
	public String toString() {
		return "fork ( " + stmt + " )";
	}

}
