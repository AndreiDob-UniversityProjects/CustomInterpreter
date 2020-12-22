package Model_Statements;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import Exception.MyException;
import Model.IFileTable;
import Model.Pair;
import Model.PrgState;
import Model_Expressions.VarExp;

public class openRFile implements IStmt {

	private String filename;
	private String var_id;
	public openRFile(String var_id,String filename) {
		super();
		this.filename = filename;
		this.var_id = var_id;
	}
	@Override
	public PrgState execute(PrgState state) {
		IFileTable tbl=state.getFiletbl();
		if(tbl.fileExists(filename)) {
			throw new MyException("File already exists in file table.");
		}
		else {
			try {
				BufferedReader bf=new BufferedReader(new FileReader(filename));
				int newValue=tbl.addFile(new Pair<String,BufferedReader>(filename, bf));
				state.getSymTable().update(var_id, newValue);
			} catch (Exception e) {
				throw new MyException("IO Error on opening "+filename);
			}
			
		}
		return null;
	}
	@Override
	public String toString() {
		return "openRFile("  + var_id + ","+ filename+")";
	}

}
