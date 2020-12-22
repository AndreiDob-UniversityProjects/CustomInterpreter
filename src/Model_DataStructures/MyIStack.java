package Model_DataStructures;
import java.util.Stack;

import Exception.MyDataStructureException;

public interface MyIStack<T> {

	public void push(T v);
	public T pop() throws MyDataStructureException;
	public T top() throws MyDataStructureException;
	public boolean isEmpty();
	public Stack<T> getContent();
	
}
