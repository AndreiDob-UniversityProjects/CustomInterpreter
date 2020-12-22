package Model_DataStructures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import Exception.MyDataStructureException;

public class MyDictionary<K,V> implements MyIDictionary<K, V> {

	@Override
	public String toString() {
		String toret=new String("");
		Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        toret+=pair.getKey().toString() + " --> " + pair.getValue().toString()+"\n";
	    }
	    return toret;
	}

	protected Map<K, V> map ;
	public MyDictionary() {
		super();
		this.map =new HashMap<K,V>();
	}
	public MyDictionary(Map<K, V> map ) {
		super();
		this.map =map;
	}

	@Override
	public K add(K key, V value) {
		map.put(key, value);
		return key;
	}

	@Override
	public void remove(K key) {
		map.remove(key);
	}

	@Override
	public V lookup(K key) throws MyDataStructureException {
		if(!map.containsKey(key))
			throw new MyDataStructureException("The key "+ key.toString()+" is not contained in the dictionary.\n");
		return map.get(key);
	}

	@Override
	public  boolean isDefined(K id) {
		return map.containsKey(id)==true;
	}

	@Override
	public void update(K key, V value) {
		map.put(key, value);
		
	}

	@Override
	public K findKey(V value) {
		for (K o : map.keySet()) {
		      if (map.get(o).equals(value)) {
		        return o;
		      }
		}
		return null;
	}


	@Override
	public Map<K, V> getContent() {
		return map;
	}

	@Override
	public void setContent(Map<K,V> m) {
		map=m;
		
	}
	
	public MyIDictionary<K,V> clone(){
		Map<K, V> copy = new HashMap<K,V>();
		map.entrySet().stream().forEach(s->copy.put(s.getKey(), s.getValue()));
		return new MyDictionary(copy);
		
	}
	@Override
	public void lock() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		
	}
	

}
