package Model_Statements;

import Model.Pair;
import Model.PrgState;
import Model_DataStructures.MyIList;

public class aquire implements IStmt {

	private String var;
	
	public aquire(String var) {
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
		Integer n=pair.second.getList().size();
		if(pair.first>n) 
			if(pair.second.getList().contains(state.getId()))
				;
			else
				pair.second.add_back(state.getId());
		else
			state.getExeStack().push(this);
		
		return null;
	}

	@Override
	public String toString() {
		return "aquire(" + var + ")";
	}

	
	
}
