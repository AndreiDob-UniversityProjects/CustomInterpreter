package Model_Statements;

import java.util.List;

import Model.Pair;
import Model.PrgState;
import Model_DataStructures.MyIList;
import Model_DataStructures.MyList;
import Model_Expressions.Exp;

public class newSemaphore implements IStmt {

	private Exp exp;
	private String var_id;
	
	public newSemaphore( String var_id,Exp exp) {
		super();
		this.exp = exp;
		this.var_id = var_id;
	}
	
	@Override
	public PrgState execute(PrgState state) throws Exception {
		Integer number=exp.eval(state.getSymTable(),state.getHeap());
		MyIList<Integer> lst=new MyList<Integer>();
		Pair<Integer,MyIList<Integer>> pair=new Pair<Integer,MyIList<Integer>>(number,lst);
		
		//we lock
		state.getSemtable().lock();
		Integer table_address=state.getSemtable().add(null,pair);
		//we unlock
		state.getSemtable().unlock();
		if(state.getSymTable().isDefined(var_id))
			state.getSymTable().update(var_id, table_address);
		else 
			state.getSymTable().add(var_id, table_address);
		
		return null;
	}

	@Override
	public String toString() {
		return "newSemaphore ( " + var_id + ", " + exp + ")";
	}

}
