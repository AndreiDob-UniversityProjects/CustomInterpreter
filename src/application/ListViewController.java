package application;

import java.io.IOException;
import java.util.ArrayList;

import javax.print.DocFlavor.URL;

import Controller.Controller;
import Model.FileTable;
import Model.Heap;
import Model.IFileTable;
import Model.Pair;
import Model.PrgState;
import Model.SemaphorTable;
import Model_DataStructures.MyDictionary;
import Model_DataStructures.MyIDictionary;
import Model_DataStructures.MyIList;
import Model_DataStructures.MyIStack;
import Model_DataStructures.MyList;
import Model_DataStructures.MyStack;
import Model_Expressions.ArithExp;
import Model_Expressions.BoolExp;
import Model_Expressions.ConstExp;
import Model_Expressions.VarExp;
import Model_Expressions.rH;
import Model_Statements.AssignStmt;
import Model_Statements.CompStmt;
import Model_Statements.Fork;
import Model_Statements.IStmt;
import Model_Statements.IfStmt;
import Model_Statements.New;
import Model_Statements.PrintStmt;
import Model_Statements.WhileStmt;
import Model_Statements.aquire;
import Model_Statements.closeRFile;
import Model_Statements.newSemaphore;
import Model_Statements.openRFile;
import Model_Statements.readFile;
import Model_Statements.release;
import Model_Statements.wH;
import Repository.IRepository;
import Repository.Repository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class ListViewController {
	
	 @FXML
	 private ListView<Controller> listView;
	 @FXML
	 private Label label;
	 
	 private Stage mainStage;
	 
	 @FXML
	 public void initialize() {
		 listView.setItems(getCtrList());
		// To set selection model
	      listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	 
	      // Focus
	      //listView.getFocusModel().focus(2);
	      
	      listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Controller>() {
	    	  
	            @Override
	            public void changed(ObservableValue<? extends Controller> observable, Controller oldValue, Controller newValue) {
	                //label.setText("selected program:"+ newValue.getRepo().getArray().getItem(0).getExeStack());
	                listSelect(newValue);
	            }
	        });
	 }
	
	 
	 private void listSelect(Controller ctrl) {
	try {
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainwindow.fxml"));
        StackPane secondaryLayout = (StackPane)loader.load();
        MainWindowController controller=loader.getController();
        
		controller.setCtrl(ctrl);
		controller.initGUI();
		controller.updateGUI();
         ctrl.addObserver(controller);
         Scene secondScene = new Scene(secondaryLayout, 800, 700);

         // New window (Stage)
         Stage newWindow = new Stage();
         newWindow.setTitle("Second Stage");
         newWindow.setScene(secondScene);

         // Set position of second window, related to primary window.
         newWindow.setX(mainStage.getX() + 50);
         newWindow.setY(mainStage.getY() - 130);

         newWindow.show();
	 } catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 private ObservableList<Controller> getCtrList() {
		 
		 	Heap heap=new Heap();
			MyIDictionary<Integer,Integer> heap2=new Heap();
			
			String testFilePath="C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\test.in";
			
			IStmt ex1=new CompStmt(new AssignStmt("a", new ArithExp(new ConstExp(2),"-", new ConstExp(2))),new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
			Controller ctr1 = genCtrl(ex1,"C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\log1.txt");
			
			IStmt ex2=new CompStmt( new AssignStmt("var_f",new ConstExp(0)),new CompStmt(new openRFile("var_f",testFilePath),new CompStmt(new readFile(new VarExp("var_f"),"var_c"),new PrintStmt(new VarExp("var_c")))));
			Controller ctr2 = genCtrl(ex2,"C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\log2.txt");
			
			IStmt ex3= new CompStmt(new CompStmt( new AssignStmt("var_f",new ConstExp(0)),new CompStmt(new readFile(new VarExp("var_f"),"var_c"),new PrintStmt(new VarExp("var_c")))),new closeRFile(new VarExp("var_f")) );
			Controller ctr3 = genCtrl(ex3,"C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\log3.txt");
	 
			IStmt ex4=new CompStmt( new AssignStmt("v",new ConstExp(10)),new CompStmt(new New("v",new ConstExp(20)),new CompStmt(new New("a",new ConstExp(22)),new PrintStmt(new rH("a")))));
			IStmt ex5=new CompStmt( new AssignStmt("v",new ConstExp(10)),new CompStmt(new New("v",new ConstExp(20)),new CompStmt(new AssignStmt("a",new ConstExp(50)),new PrintStmt(new rH("c")))));
			IStmt ex6=new CompStmt( new AssignStmt("v",new ConstExp(10)),new CompStmt(new AssignStmt("a",new ConstExp(10)),new CompStmt(new wH("v",new ConstExp(55)),new PrintStmt(new rH("v")))));
			IStmt ex7=new CompStmt( new AssignStmt("v",new ConstExp(10)),new CompStmt(new New("v",new ConstExp(20)),new CompStmt(new New("a",new ConstExp(22)),new CompStmt(new AssignStmt("a",new ConstExp(33)),new PrintStmt(new rH("a"))))));
			Controller ctr4 = genCtrl(ex4,"C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\log4.txt");
			Controller ctr5 = genCtrl(ex5,"C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\log4.txt");
			Controller ctr6 = genCtrl(ex6,"C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\log4.txt");
			Controller ctr7 = genCtrl(ex7,"C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\log4.txt");
			
			IStmt ex8=new PrintStmt(new ArithExp(new ConstExp(10),"+",new BoolExp(new ConstExp(6),"<",new ConstExp(6))));
			Controller ctr8 = genCtrl(ex8,"C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\log4.txt");
			
			IStmt ex9=new CompStmt(new AssignStmt("v",new ConstExp(6)),new CompStmt(new WhileStmt(new ArithExp(new VarExp("v"),"-",new ConstExp(4)),new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp(new VarExp("v"),"-",new ConstExp(1))))),new PrintStmt(new VarExp("v"))));
			Controller ctr9 = genCtrl(ex9,"C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\log4.txt");
			
			//ex2
			String testFilePath2="C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\test2.in";
			IStmt ex15=new CompStmt( new AssignStmt("var_f",new ConstExp(0)),new CompStmt(new openRFile("var_f",testFilePath2),new CompStmt(new readFile(new VarExp("var_f"),"var_c"),new PrintStmt(new VarExp("var_c")))));
			
			IStmt ex10_fork=new CompStmt(new wH("a",new ConstExp(30)),new CompStmt(new AssignStmt("v",new ConstExp(32)),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new rH("a")))));
			
			IStmt ex10=new CompStmt(new AssignStmt("v",new ConstExp(10)) ,new CompStmt(new PrintStmt(new VarExp("v")) ,new CompStmt( new Fork(ex2),new CompStmt(new Fork(ex15),new CompStmt( new PrintStmt(new VarExp("v")),new PrintStmt(new VarExp("v")))))));
			Controller ctr10 = genCtrl(ex10,"C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\log4.txt");
			
			IStmt ex11=new CompStmt(new AssignStmt("v",new ConstExp(10)) ,new CompStmt(new PrintStmt(new VarExp("v")) ,new CompStmt(new Fork(ex15),new CompStmt( new PrintStmt(new VarExp("v")),new PrintStmt(new VarExp("v"))))));
			Controller ctr11 = genCtrl(ex11,"C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\log4.txt");
			
			//change
			
			IStmt fork1=new CompStmt(new aquire("cnt"),new CompStmt(new wH("v1",new ArithExp(new rH("v1"),"*",new ConstExp(10))),new CompStmt(new PrintStmt(new rH("v1")),new release("cnt"))));
						
			IStmt ex12=new CompStmt(new New("v1",new ConstExp(1)),new CompStmt(new newSemaphore("cnt",new rH("v1")),new CompStmt(new Fork(fork1),new Fork(fork1))));
			Controller ctr12 = genCtrl(ex12,"C:\\Users\\Andrei D\\Desktop\\MyInterFXSemaphore\\src\\log.txt");
			
			
			
	      // To Creating a Observable List
	     ObservableList<Controller> ctrls = FXCollections.observableArrayList(ctr1,ctr2,ctr3,ctr4,ctr10,ctr12);
	     return ctrls;
	  }
	 
	 public void setMainStage(Stage primaryStage) {
			mainStage = primaryStage;
		}
	 
	 static Controller genCtrl(IStmt statement,String logPath) {
			MyIStack<IStmt> st=new MyStack<IStmt>();
			MyIDictionary<String,Integer> dict= new MyDictionary<String,Integer>();
			MyIList<Integer> lst=new MyList<Integer>();
			IFileTable tbl=new FileTable();
			MyIDictionary<Integer,Integer> heap=new Heap();
			//change
			MyDictionary<Integer, Pair<Integer,MyIList<Integer>>> semtable=new SemaphorTable();
			
			PrgState prg1 = new PrgState(st,dict,lst,tbl,heap,semtable, statement);
			IRepository repo1 = new Repository(prg1,logPath);
			Controller ctr1 = new Controller(repo1);
			return ctr1;
		}
	 
	 
}
