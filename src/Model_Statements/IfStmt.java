package Model_Statements;

import Model.PrgState;
import Model_DataStructures.MyIDictionary;
import Model_DataStructures.MyIStack;
import Model_Expressions.Exp;
import Exception.MyExpressionException;
import Exception.MyStmtExecException;

public class IfStmt implements IStmt {
	private Exp exp;
	private IStmt thenS;
	private IStmt elseS;
	
	public IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
		super();
		this.exp = exp;
		this.thenS = thenS;
		this.elseS = elseS;
	}

	@Override
	public PrgState execute(PrgState state) {
		MyIStack<IStmt> stk=state.getExeStack();
		MyIDictionary<String,Integer> symTbl= state.getSymTable();
		try {
			if(exp.eval(symTbl,state.getHeap())!=0)
				stk.push(thenS);
			else
				stk.push(elseS);
		} catch (MyExpressionException e) {
			throw new MyStmtExecException("Statement exception: "+e.getMessage());
		}
		return null;
	}

	@Override
	public String toString() {
		return  "IF("+ exp.toString()+") THEN(" +thenS.toString()+")ELSE("+elseS.toString()+")";
	}

}
