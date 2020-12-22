package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Exception.MyDataStructureException;
import Exception.MyException;
import Model_DataStructures.MyDictionary;
import Model_DataStructures.MyIDictionary;

public class FileTable implements IFileTable {

	private static int makeid=0;
	private MyIDictionary<Integer,Pair<String,BufferedReader>> dict;
	
	public MyIDictionary<Integer, Pair<String, BufferedReader>> getDict() {
		return dict;
	}

	public FileTable() {
		super();
		this.dict = new MyDictionary<Integer,Pair<String,BufferedReader>>() ;
	}

	@Override
	public String toString() {
		return "FileTable:\n " + dict + "\n";
	}

	@Override
	public Integer addFile(Pair<String,BufferedReader> p) {
		makeid++;
		dict.add(makeid, p);
		return makeid;
	}

	@Override
	public void removeFile(Integer id) {
		dict.remove(id);
	}

	@Override
	public boolean fileExists(String filename) {
		String testFilePath="C:\\Users\\Andrei D\\Documents\\Eclipse\\MyInterpreter\\src\\test.in";
		BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader(testFilePath));
			Pair<String, BufferedReader> pair=new Pair<String, BufferedReader>(filename,bf);
			Integer id =dict.findKey(pair);
			return id!=null;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Pair<String,BufferedReader> getPair(Integer id) throws Exception {
		try {
			return dict.lookup(id);
		} catch (MyDataStructureException e) {
			throw new MyException("File table:"+e.getMessage());//gives stack overflow with MyException
		}
	}

}
