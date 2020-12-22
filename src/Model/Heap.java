package Model;

import java.io.BufferedReader;

import Exception.MyDataStructureException;
import Model_DataStructures.MyDictionary;
import Model_DataStructures.MyIDictionary;

public class Heap extends MyDictionary<Integer,Integer> {

	private static int makeid=0;
	@Override
	public Integer add(Integer id, Integer value) {
		
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
	public Integer lookup(Integer key) throws MyDataStructureException {
		if(!map.containsKey(key))
			throw new MyDataStructureException("The address "+ key.toString()+" is not contained in the heap.\n");
		return map.get(key);
	}
	
	
	
	

}
