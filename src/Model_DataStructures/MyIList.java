package Model_DataStructures;

import java.util.List;

public interface MyIList<T> {
	public void add_back(T el);
	public void removeByIndex(int index);
	public void remove(T el);
	public List<T> getList();
	public void setList(List l);
	public T getItem(int index);
}
