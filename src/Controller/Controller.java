package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import Exception.MyControllerException;
import Exception.MyDataStructureException;
import Exception.MyException;
import Model.FileTable;
import Model.IFileTable;
import Model.Pair;
import Model.PrgState;
import Model_DataStructures.MyDictionary;
import Model_DataStructures.MyIDictionary;
import Model_DataStructures.MyIList;
import Model_DataStructures.MyIStack;
import Model_DataStructures.MyList;
import Model_DataStructures.MyStack;
import Model_Expressions.ArithExp;
import Model_Expressions.ConstExp;
import Model_Expressions.VarExp;
import Model_Statements.AssignStmt;
import Model_Statements.CompStmt;
import Model_Statements.IStmt;
import Model_Statements.IfStmt;
import Model_Statements.PrintStmt;
import Repository.IRepository;
import Repository.Repository;

public class Controller extends Observable {
	
	@Override
	public String toString() {
		return repo.getArray().getItem(0).getExeStack().toString();
	}
	
	private IRepository repo;
	private boolean display;
	private ExecutorService executor;
	//private ArrayList<IStmt> arr;
	
	public IRepository getRepo() {
		return repo;
	}
	
	
	
	public Controller(IRepository repo) {
		super();
		this.repo = repo;
		this.display = true;
		this.executor=Executors.newFixedThreadPool(2);
	}

	/*public void selectProgram(int i)
	{
		MyIStack<IStmt> st=new MyStack<IStmt>();
		MyIDictionary<String,Integer> dict= new MyDictionary<String,Integer>();
		MyIList<Integer> lst=new MyList<Integer>();
		IFileTable tbl=new FileTable();
		
		//PrgState prg=new PrgState(st,dict,lst,tbl,arr.get(i-1));
		//repo= new Repository(prg);
	}*/
	
	public boolean getDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}
	public void setLogPath(String path)
	{
		repo.setLogFilePath(path);
	}
	
	/*public  PrgState oneStep(PrgState state) throws Exception {
		 MyIStack<IStmt> stk=state.getExeStack();
		 IStmt crtStmt = stk.pop();
		 //if(display)
		///	 System.out.println(state);
		 return crtStmt.execute(state);
	}*/
	
	private Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,Map<Integer,Integer> heap){
		return heap.entrySet().stream()
			 .filter(e->symTableValues.contains(e.getKey()))
			 .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	public void closeFiles(Collection<Integer> symTableValues,Map<Integer,Pair<String,BufferedReader>> filetable) {
		filetable.entrySet().stream()
			.forEach(e->{
				try {
					if(!symTableValues.contains(e.getKey()))
						e.getValue().second.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					throw new MyException(e1.getMessage());
				}
			});
			
	}
	
	List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
		return inPrgList.stream()
				 .filter(p -> p.isNotCompleted())
				 .collect(Collectors.toList());
	}
	
	public void oneStepForAllPrg(List<PrgState> prgList) throws Exception {
		 //before the execution, print the PrgState List into the log file
		 prgList.forEach(prg ->repo.logPrgStateExec(prg));

		//RUN concurrently one step for each of the existing PrgStates
		 //-----------------------------------------------------------------------
		 //prepare the list of callables
		 List<Callable<PrgState>> callList = prgList.stream()
				 .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
				 .collect(Collectors.toList());

		 //start the execution of the callables
		 //it returns the list of new created PrgStates (namely threads)
		 List<PrgState> newPrgList = executor.invokeAll(callList). stream()
				 . map(future -> { try { return future.get();}
								   catch (Exception e) {
											throw new RuntimeException("Controller exception: Data structure exception: "+e.getMessage());
								 }})
				 .filter(p -> p!=null)
				 .collect(Collectors.toList());
		 //add the new created threads to the list of existing threads
		 prgList.addAll(newPrgList);
		 //------------------------------------------------------------------------------
		 
		 //after the execution, print the PrgState List into the log file
		 prgList.forEach(prg ->repo.logPrgStateExec(prg));
		// prgList.get(0).getExeStack().isEmpty()
		 //prgList.forEach(prg ->if(prg.get));
		 
		 //display(prgList);
		 
		// setChanged(); 
	     //notifyObservers();
	     
		 //Save the current programs in the repository
		 repo.setPrgList(prgList);
		 
		 
		}

	public void allStep() throws Exception {
		 executor = Executors.newFixedThreadPool(2);
		 //remove the completed programs
		 List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
		// while(prgList.size() > 0){
			 Collection<Integer> symTableValues=new ArrayList<Integer>();
			 prgList.forEach(prg->symTableValues.addAll( prg.getSymTable().getContent().values()));
			 
			 prgList.get(0).getHeap().setContent(conservativeGarbageCollector( symTableValues,prgList.get(0).getHeap().getContent()));
			 oneStepForAllPrg(prgList);
			 
			 //for debugging purposes
			 //display(prgList);
			 
			//close all the files from the programs that have finished
			 for(int i=0;i<prgList.size();i++)
				 if(!prgList.get(i).isNotCompleted())
					 for (int v:prgList.get(i).getSymTable().getContent().values())
						 if(prgList.get(i).getFiletbl().getDict().getContent().containsKey(v))
							 prgList.get(i).getFiletbl().removeFile(v);
			 //remove the completed programs		 
			 prgList=removeCompletedPrg(repo.getPrgList());
			// Collection<Integer> symTableValues2=new ArrayList<Integer>();
			// prgList.forEach(prg->symTableValues.addAll( prg.getSymTable().getContent().values()));
			// closeFiles(symTableValues,prgList.get(0).getFiletbl().getDict().getContent());
			 
		// }
		 executor.shutdownNow();
		 //HERE the repository still contains at least one Completed Prg
		 // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
		 //setPrgList of repository in order to change the repository
		 
		 List<PrgState> tmpList= repo.getPrgList();
		 Collection<Integer> symTableValues2=new ArrayList<Integer>();
		 tmpList.forEach(prg->symTableValues2.addAll( prg.getSymTable().getContent().values()));
		 closeFiles(symTableValues2,tmpList.get(0).getFiletbl().getDict().getContent());
		 // update the repository state
		 
		 //display(prgList);
		 setChanged(); 
	     notifyObservers();
	     
	     repo.setPrgList(prgList);
		 }
	/*public void allStep1() throws Exception {
		
		PrgState prg = repo.getCrtPrg();
		if(prg.getExeStack().isEmpty())
			throw new MyControllerException("Controller exception: Program has already finished execution");
		try {
			while (!prg.getExeStack().isEmpty()){
					oneStep(prg);
					prg.getHeap().setContent(conservativeGarbageCollector( prg.getSymTable().getContent().values(), prg.getHeap().getContent()));
					repo.logPrgStateExec();
					if(display)
						displayPrgState();

			}
		} catch (MyDataStructureException e) {
			throw new MyControllerException("Controller exception: Data structure exception: "+e.getMessage());
		}finally {
			closeFiles(prg.getFiletbl().getDict().getContent());
		}
			
		 
	}
	*/
	public void display(List<PrgState> prgList) {
		prgList.forEach(prg ->System.out.println(prg)); 
		System.out.println(" ");
		
	}
}
