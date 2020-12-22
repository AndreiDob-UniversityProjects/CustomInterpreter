package Repository;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import Exception.MyException;
import Model.PrgState;
import Model_DataStructures.MyList;

public class Repository implements IRepository {

	private MyList<PrgState> arr;
	private static boolean erase;
	private String logFilePath;
	
	public String getLogFilePath() {
		return logFilePath;
	}

	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}

	public Repository(PrgState prg,String file) {
		super();
		this.arr =new MyList<PrgState>();
		logFilePath=file;
		erase=true;
		arr.add_back(prg);
	}

	@Override
	public void logPrgStateExec(PrgState prg)  {
	
		if(erase) {
			PrintWriter writer;
			try {
				writer = new PrintWriter(logFilePath);
			} catch (FileNotFoundException e) {
				throw new MyException(e.getMessage());
			}
			writer.print("");
			writer.close();
			erase=false;
		}
		try (PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true))))
		{
			logFile.append(prg.toString());
		} catch (Exception e) {
			 String s=prg.toString();
			 throw(new RuntimeException("Log file exception: "+e.getMessage()));
		}
		
		
		
		
	}

	@Override
	public List<PrgState> getPrgList() {
		return arr.getList();
	}

	@Override
	public void setPrgList(List<PrgState> l) {
		arr.setList(l);
	}

	@Override
	public MyList<PrgState> getArray() {
		return arr;
	}

	@Override
	public PrgState getById(Integer id) {
		for(PrgState prg:arr.getList())
			if(prg.getId()==id)
				return prg;
		return null;
	}

}
