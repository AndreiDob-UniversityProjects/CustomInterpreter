package Model_DataStructures;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
	private List<T> list;
	
	@Override
	public String toString() {
		String res=new String();
		res="[";
		for(T el:list)
			res+=el.toString()+" \n";
		res+="]";
		return res;
	}

	public MyList() {
		super();
		this.list  = new ArrayList<T>();
	}

	@Override
	public void add_back(T el) {
		list.add(list.size(), el);
	}

	@Override
	public void removeByIndex(int index) {
		list.remove(index);
	}

	@Override
	public void remove(T el) {
		list.remove(el);
	}

	@Override
	public List<T> getList() {
		return list;
	}

	@Override
	public T getItem(int index) {
		return list.get(index);
	}

	@Override
	public void setList(List l) {
		list=l;
		
	}

}
