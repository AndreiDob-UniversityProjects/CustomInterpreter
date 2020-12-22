package Model_commands;

import java.util.List;

import Controller.Controller;
import Exception.MyDataStructureException;
import Exception.MyException;
import Model.PrgState;

public class OneStepExample extends RunExample {

	 private Controller ctr;
	public OneStepExample(String key, String desc, Controller ctr) {
		super(key, desc, ctr);
		this.ctr=ctr;
	}

	@Override
	public void execute() throws Exception {
		try{
			 List<PrgState> prgList=ctr.getRepo().getPrgList();
			 if(prgList.size() > 0) {
				 ctr.oneStepForAllPrg(prgList);
			 }
		 }
		 catch (MyDataStructureException e) {
			 throw new MyException(e.getMessage());
		} 
	}

}
