package View;

import java.util.Scanner;
import Exception.MyControllerException;
import Exception.MyDataStructureException;
import Controller.Controller;

/*public class UI {
	private Controller ctrl;

	public UI(Controller ctrl) {
		super();
		this.ctrl = ctrl;
	}
	
	private void inputMenu() {
		Scanner S=new Scanner(System.in);
		System.out.println("Insert id");
		//id=S.nextInt();
	}
	
	private void selectMenu() {
		System.out.println("Existing programs:");
		System.out.println("1. v=2;Print(v) ");
		System.out.println("2. a=2+3*5;b=a+1;Print(b)");
		System.out.println("3. a=2-2;(If a Then v=2 Else v=3);Print(v)");
		int id;
		Scanner S=new Scanner(System.in);
		System.out.println("Insert id");
		id=S.nextInt();
		//ctrl.selectProgram(id);
	}
	
	private void oneStepMenu() throws Exception  {
		try {
			ctrl.oneStep(ctrl.getRepo().getCrtPrg());
		} catch (MyDataStructureException e) {
			e.printEx();
		}
	}
	
	private void allStepMenu() throws Exception {
		try {
			ctrl.allStep();
		} catch (MyControllerException e) {
			e.printEx();
		}
	}
	private void toggleDisplay()
	{
		ctrl.setDisplay(!ctrl.getDisplay());
		System.out.println("Display: "+ ctrl.getDisplay());
	}
	
	private void showMenu() {
		System.out.println("Menu:");
		System.out.println("1. Input program");
		System.out.println("2. Select already existing program");
		System.out.println("3. One step evaluation of a program");
		System.out.println("4. Complete evaluation of a program");
		System.out.println("5. Toggle display");
	}
	public void start() {
		ctrl.setLogPath("C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\log.txt");
		while (true) {
			try {
				showMenu();
				int id;
				Scanner S = new Scanner(System.in);
				System.out.println("Insert id: ");
				id = S.nextInt();
				if (id == 1)
					inputMenu();
				else if (id == 2)
					selectMenu();
				else if (id == 3)
					oneStepMenu();
				else if (id == 4)
					allStepMenu();
				else if (id == 5)
					toggleDisplay();

			}
			catch(Exception e){
				System.out.println("Exception: " + e.getMessage());
			}
		}
		
	}
}*/
