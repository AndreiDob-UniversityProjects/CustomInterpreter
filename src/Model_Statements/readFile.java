package Model_Statements;

import java.io.BufferedReader;
import java.io.IOException;

import Exception.MyException;
import Exception.MyExpressionException;
import Model.IFileTable;
import Model.PrgState;
import Model_DataStructures.MyIDictionary;
import Model_Expressions.Exp;

public class readFile  implements IStmt  {
	private Exp exp;
	private String var_name;
	public readFile(Exp exp, String var_name) {
		super();
		this.exp = exp;
		this.var_name = var_name;
	}
	
	
	@Override
	public PrgState execute(PrgState state) throws Exception {
		IFileTable tbl=state.getFiletbl();
		MyIDictionary<String, Integer> symtbl=state.getSymTable();
		Integer id;
		try {
			id = exp.eval(symtbl,state.getHeap());
			BufferedReader bf=tbl.getPair(id).second;
			Integer val;
			String line=bf.readLine();
			if(line== null)
				val=0;
			else 
				val=Integer.parseInt(line);
			if(symtbl.isDefined(var_name))
				symtbl.update(var_name,val);
			else
				symtbl.add(var_name, val);
			
		} catch (MyExpressionException e) {
			throw new MyException(e.getMessage());
		} catch (IOException e) {
			throw new MyException("IO Error: "+e.getMessage());
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "readFile [exp=" + exp + ", var_name=" + var_name + "]";
	}
	
}
