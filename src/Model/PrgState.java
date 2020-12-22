package Model;

import java.util.List;

import Exception.MyDataStructureException;
import Model_DataStructures.MyDictionary;
import Model_DataStructures.MyIDictionary;
import Model_DataStructures.MyIList;
import Model_DataStructures.MyIStack;
import Model_Statements.IStmt;

public class PrgState {
	private static int staticid=0;
	private MyIStack<IStmt> exeStack;
	private MyIDictionary<String, Integer> symTable;
	private  MyIList<Integer> out;
	private IFileTable filetbl;
	private MyIDictionary<Integer,Integer> heap;
	private MyDictionary<Integer, Pair<Integer,MyIList<Integer>>> semtable;
	
	private  IStmt originalProgram; 
	private int id;
	
	public int getId() {
		return id;
	}

	public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Integer> symtbl,MyIList<Integer> ot,IFileTable tbl,MyIDictionary<Integer,Integer> hp,MyDictionary<Integer, Pair<Integer,MyIList<Integer>>> semtbl, IStmt prg){
		exeStack=stk;
		symTable=symtbl;
		out = ot;
		id=staticid;
		staticid++;
		filetbl=tbl;
		heap=hp;
		semtable=semtbl;
		//originalProgram=prg.deepCopy();//recreate the entire original prg
		stk.push(prg);
	 }
	
	public MyIDictionary<Integer, Integer> getHeap() {
		return heap;
	}

	public IFileTable getFiletbl() {
		return filetbl;
	}
	
	public MyIStack<IStmt> getExeStack() {
		return exeStack;
	}

	public void setExeStack(MyIStack<IStmt> exeStack) {
		this.exeStack = exeStack;
	}

	public MyIDictionary<String, Integer> getSymTable() {
		return symTable;
	}

	public void setSymTable(MyIDictionary<String, Integer> symTable) {
		this.symTable = symTable;
	}

	public MyIList<Integer> getOut() {
		return out;
	}

	public void setOut(MyIList<Integer> out) {
		this.out = out;
	}

	public IStmt getOriginalProgram() {
		return originalProgram;
	}

	public void setOriginalProgram(IStmt originalProgram) {
		this.originalProgram = originalProgram;
	}

	
/*	private PrgState deepCopy(){
		MyIStack<IStmt> exeStack=new MyIStack<IStmt>;
		MyIDictionary<String, Integer> symTable=new MyIDictionary<String, Integer>;
		MyIList<Integer> out;
	}*/

	public MyDictionary<Integer, Pair<Integer, MyIList<Integer>>> getSemtable() {
		return semtable;
	}

	public void setSemtable(MyDictionary<Integer, Pair<Integer, MyIList<Integer>>> semtable) {
		this.semtable = semtable;
	}

	public Boolean isNotCompleted() {
		return !exeStack.isEmpty();
	}
	
	public PrgState oneStep() throws Exception{
		if(exeStack.isEmpty()) 
				 throw new Exception("ProgramState Exception: Program stack is empty");
		IStmt crtStmt;
		try {
			crtStmt = exeStack.pop();
			return crtStmt.execute(this);
		} catch (MyDataStructureException e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public String toString() {
		return "-----------------Program id:" + id+"-------------------\n\nExeStack:\n" + exeStack + "\nSymTable:\n" + symTable + "\nOut:\n" + out+"\n"+filetbl+"\n"+heap+"\n"+semtable+"\n";
		//return heap.toString();
	}
	

}


