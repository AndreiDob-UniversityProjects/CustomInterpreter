package Model_Statements;

import Model.Pair;
import Model.PrgState;
import Model_DataStructures.MyIList;

public class release implements IStmt {

	private String var;
	
	public release(String var) {
		super();
		this.var = var;
	}

	@Override
	public PrgState execute(PrgState state) throws Exception {
		Integer foundIndex=state.getSymTable().lookup(var);
		//lock
		state.getSemtable().lock();
		Pair<Integer,MyIList<Integer>> pair=state.getSemtable().lookup(foundIndex);
		//unlock
		state.getSemtable().unlock();
		
		if(pair.second.getList().contains(state.getId()))
				pair.second.remove(state.getId());
			else
				;
		
		return null;
	}

	@Override
	public String toString() {
		return "release (" + var + ")";
	}

	


}
