package Model_Statements;

import Model.PrgState;
import Model_Expressions.Exp;

public class wH implements IStmt {
	private String var_name;
	private Exp exp;
	
	public wH(String var_name, Exp exp) {
		super();
		this.var_name = var_name;
		this.exp = exp;
	}

	@Override
	public PrgState execute(PrgState state) throws Exception {
		Integer address=state.getSymTable().lookup(var_name);
		Integer val=exp.eval(state.getSymTable(), state.getHeap());
		if(state.getHeap().lookup(address) != null)
			state.getHeap().update(address, val);
		return null;
	}
	
	@Override
	public String toString() {
		return " wH(" + var_name + " , "+exp+") ";
	}

}
