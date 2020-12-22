package Model_Statements;

import Model.PrgState;

public interface IStmt {
	
	 public PrgState execute(PrgState state) throws Exception;
	 
}

