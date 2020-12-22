package Model_commands;

import Controller.Controller;
import Exception.MyControllerException;
import Exception.MyException;

public class RunExample extends Command {
	 private Controller ctr;
	 public RunExample(String key, String desc,Controller ctr){
	 super(key, desc);
	 this.ctr=ctr;
	 }
	 @Override
	 public void execute() throws Exception {
		 try{
			 ctr.allStep();
		 }
		 catch (MyControllerException e) {
			 throw new MyException(e.getMessage());
		 } 
	}
}
