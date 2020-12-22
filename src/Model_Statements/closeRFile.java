package Model_Statements;

import java.io.BufferedReader;
import java.io.IOException;

import Exception.MyException;
import Exception.MyExpressionException;
import Model.IFileTable;
import Model.PrgState;
import Model_DataStructures.MyIDictionary;
import Model_Expressions.Exp;
import Model_Expressions.VarExp;

public class closeRFile implements IStmt {

	VarExp exp;
	public closeRFile(VarExp exp) {
		super();
		this.exp = exp;
	}
	
	@Override
	public PrgState execute(PrgState state) throws Exception {
		IFileTable tbl=state.getFiletbl();
		MyIDictionary<String, Integer> symtbl=state.getSymTable();
		Integer id;
		try {
			id = exp.eval(state.getSymTable(),state.getHeap());
			BufferedReader bf=tbl.getPair(id).second;
			bf.close();
			tbl.removeFile(id);
			//state.getSymTable().remove(exp.getId());
		} catch (MyExpressionException e) {
			throw new MyException(e.getMessage());
		} catch (IOException e) {
			throw new MyException("IO Error: "+e.getMessage());
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return "closeRFile [exp=" + exp + "]";
	}

}
