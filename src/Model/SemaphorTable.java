package Model;

import java.util.List;
import java.util.concurrent.Semaphore;

import Exception.MyDataStructureException;
import Model_DataStructures.MyDictionary;
import Model_DataStructures.MyIList;

public class SemaphorTable extends MyDictionary<Integer, Pair<Integer,MyIList<Integer>>> {
	
	private static int makeid=0;
	static Semaphore semaphore = new Semaphore(1);
	
	@Override
	public void lock()
	{
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public void unlock()
	{
		semaphore.release();
	}
	
	@Override
	public Integer add(Integer id,Pair<Integer,MyIList<Integer>> value) {
		
		if(id==null)
		{
			makeid++;
			map.put(makeid, value);
		}
		else
			map.put(id, value);
		return makeid;
	}
	@Override
	public String toString() {
		return "heap: \n"+super.toString();
	}
	
	@Override
	public Pair<Integer,MyIList<Integer>> lookup(Integer key) throws MyDataStructureException {
		if(!map.containsKey(key))
			throw new MyDataStructureException("The address "+ key.toString()+" is not contained in the SemaphoreTable.\n");
		return map.get(key);
	}
	
}
