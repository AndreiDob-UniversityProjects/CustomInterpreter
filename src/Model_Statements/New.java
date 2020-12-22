package Model_Statements;

import Model.PrgState;
import Model_Expressions.Exp;

public class New implements IStmt {

	private Exp exp;
	private String var_id;
	
	public New( String var_id,Exp exp) {
		super();
		this.exp = exp;
		this.var_id = var_id;
	}
	
	@Override
	public PrgState execute(PrgState state) throws Exception {
		Integer result=exp.eval(state.getSymTable(),state.getHeap());
		Integer heap_address=state.getHeap().add(null, result);
		if(state.getSymTable().isDefined(var_id))
			state.getSymTable().update(var_id, heap_address);
		else state.getSymTable().add(var_id, heap_address);
		
		return null;
	}

	@Override
	public String toString() {
		return "New ( " + var_id + ", " + exp + ")";
	}

}
