package Model_commands;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.Controller;
import Exception.MyException;

public class ChangeLogFile extends Command {
	
	private ArrayList<Controller> arr;
	private Integer change_key;
	private String new_logfile;
	
	public ChangeLogFile(String key, String description, ArrayList<Controller> arr) {
		super(key, description);
		this.arr = arr;
	}

	
	@Override
	public void execute() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Which file do you want to change? ");
		change_key=scanner.nextInt();
		if(change_key>arr.size())
			throw new MyException("Wrong number");
		System.out.println("Insert new log file path:");
		scanner.nextLine();//consumes the \n after the previous int
		new_logfile=scanner.nextLine();
		arr.get(change_key-1).setLogPath(new_logfile);

	}

}
