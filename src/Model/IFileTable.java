package Model;

import java.io.BufferedReader;

import Model_DataStructures.MyIDictionary;

public interface IFileTable {
	public Integer addFile(Pair<String,BufferedReader> p);
	public boolean fileExists(String filename);
	public void removeFile(Integer id);
	public Pair<String,BufferedReader> getPair(Integer id) throws Exception;
	public MyIDictionary<Integer, Pair<String, BufferedReader>> getDict();
}
