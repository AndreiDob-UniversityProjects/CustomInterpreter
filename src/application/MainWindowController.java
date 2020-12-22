package application;

import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import Controller.Controller;
import Model.FileTable;
import Model.Heap;
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
import Model_Statements.closeRFile;
import Model_Statements.openRFile;
import Model_Statements.readFile;
import Repository.IRepository;
import Repository.Repository;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindowController implements Observer {
	
	@FXML
	private Button onebtn;
	
	@FXML
	private ListView<Integer> output;
	
	@FXML
	private TableView<Map.Entry<Integer,Integer>> heap;
	@FXML
	private TableColumn<Map.Entry<Integer,Integer>,Integer> address;
	@FXML
	private TableColumn<Map.Entry<Integer,Integer>,Integer> value;
	
	@FXML
	private TableView<Map.Entry<Integer,Pair<String,BufferedReader>>> filetable;
	@FXML
	private TableColumn<Map.Entry<Integer,Pair<String,BufferedReader>>,Integer> identifier;
	@FXML
	private TableColumn<Map.Entry<Integer,Pair<String,BufferedReader>>,String> filename;
	
	@FXML
	private TableView<Map.Entry<Integer, Pair<Integer,MyIList<Integer>>>> semtable;
	@FXML
	private TableColumn<Map.Entry<Integer, Pair<Integer,MyIList<Integer>>>,Integer> index;
	@FXML
	private TableColumn<Map.Entry<Integer, Pair<Integer,MyIList<Integer>>>,Integer> maxvalue;
	@FXML
	private TableColumn<Map.Entry<Integer, Pair<Integer,MyIList<Integer>>>,String> valuelist;
	
	@FXML
	private TextField numberprg;
	
	@FXML
	private ListView<Integer> pid;
	
	@FXML
	private TableView<Map.Entry<String,Integer>> symtable;
	@FXML
	private TableColumn<Map.Entry<String,Integer>,String> name;
	@FXML
	private TableColumn<Map.Entry<String,Integer>,Integer> varvalue;
	
	@FXML
	private ListView<String> exestack;
	
	private Controller ctrl;
	private int crtprg=new Integer(0);
	
	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}
	
	/*public MainWindowController() {
		super();
		crtprg= new Integer(0);
		updateGUI();
	}*/
	
	public MainWindowController() {
		super();
		//updateGUI();
		// TODO Auto-generated constructor stub
	}
	
	public void initGUI() {
		address.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getKey()).asObject()) ;
		value.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getValue()).asObject()) ;
		
		identifier.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getKey()).asObject()) ;
		filename.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().first));
		
		//change here
		index.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getKey()).asObject()) ;
		maxvalue.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getValue().first).asObject());
		valuelist.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().second.toString()));
		
		name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
		varvalue.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getValue()).asObject()) ;
		
	    pid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	
	    pid.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
	    	  
	            @Override
	            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
	                if(newValue!=null)
	                	crtprg=newValue;
	                updateGUI();
	            }
	        });
	    crtprg=ctrl.getRepo().getArray().getList().get(0).getId();
	}
	
	
	public void updateGUI() {
		
		int i=ctrl.getRepo().getArray().getList().size();
		numberprg.setText(Integer.toString(ctrl.getRepo().getArray().getList().size()));
		
		if(ctrl.getRepo().getById(crtprg)==null)
			crtprg=ctrl.getRepo().getArray().getList().get(0).getId();
		
		ObservableList<Integer> o_out = FXCollections.observableArrayList(ctrl.getRepo().getArray().getItem(0).getOut().getList());
		output.setItems(o_out);
		
		ObservableList<Map.Entry<Integer,Integer>> o_heap = FXCollections.observableArrayList(ctrl.getRepo().getArray().getItem(0).getHeap().getContent().entrySet());
		heap.setItems(o_heap);
		
		ObservableList<Map.Entry<Integer,Pair<String,BufferedReader>>> o_file = FXCollections.observableArrayList(ctrl.getRepo().getArray().getItem(0).getFiletbl().getDict().getContent().entrySet());
		filetable.setItems(o_file);
		
		//change here
		//System.out.println(ctrl.getRepo().getArray().getItem(0).getSemtable().getContent().entrySet().toString());
		ObservableList<Map.Entry<Integer, Pair<Integer,MyIList<Integer>>>> o_sem = FXCollections.observableArrayList(ctrl.getRepo().getArray().getItem(0).getSemtable().getContent().entrySet());
		semtable.setItems(o_sem);
		semtable.refresh();
		//System.out.println(semtable.getItems().toString());
	    
		ArrayList<Integer> arr=new ArrayList<Integer>();
		for(PrgState prg:ctrl.getRepo().getArray().getList())
			arr.add(prg.getId());
		ObservableList<Integer> o_pid = FXCollections.observableArrayList(arr);
		pid.setItems(o_pid);
		
		ObservableList<Map.Entry<String,Integer>> o_sym = FXCollections.observableArrayList(ctrl.getRepo().getById(crtprg).getSymTable().getContent().entrySet());
		symtable.setItems(o_sym);
		
		ArrayList<String> arr1=new ArrayList<String>();
		for(IStmt s:ctrl.getRepo().getById(crtprg).getExeStack().getContent())
			arr1.add(s.toString());
		Collections.reverse(arr1);
		ObservableList<String> o_exe= FXCollections.observableArrayList(arr1);
		exestack.setItems(o_exe);
		
	}
	
	@FXML
	public void oneButton(ActionEvent event) throws Exception  {
		try {
			List<PrgState> prgList=ctrl.getRepo().getPrgList();
			 if(prgList.size() > 0) {
				 ctrl.allStep();
			 }
			 
			 updateGUI();
			 if(prgList.size()==0) {
				 onebtn.setDisable(true);
			 }
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		updateGUI();
		
	}

	

}
