package Model_DataStructures;
import java.util.ArrayList;
import java.util.Stack;
import Exception.MyDataStructureException;

public class MyStack<T> implements MyIStack<T> {

	private Stack<T> stack ;
	
	public MyStack() {
		super();
		this.stack = new Stack<T>() ;
	}

	@Override
	public void push(T v) {
		stack.push(v);
		
	}

	@Override
	public T pop() throws MyDataStructureException {
		if(this.isEmpty())
			throw new MyDataStructureException("Data structure Exception: Stack in empty.");
		return stack.pop();
	}

	@Override
	public T top() throws MyDataStructureException {
		if(this.isEmpty())
			throw new MyDataStructureException("Stack in empty.");
		return stack.peek();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public String toString() {
		String res=new String("");
		Stack<T> stack1 = stack;
		ArrayList<T> arr=new ArrayList<T>();
		for(T el:stack1)
			arr.add(el);
		
        for(int i = arr.size() - 1; i >= 0; i--)
        {
            res+=arr.get(i)+"\n";
        }
		return res;
	}

	@Override
	public Stack<T> getContent() {
		return stack;
	}
	

}
