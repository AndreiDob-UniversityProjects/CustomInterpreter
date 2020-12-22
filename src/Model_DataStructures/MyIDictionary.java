package Model_DataStructures;
import java.util.List;
import java.util.Map;

import Exception.MyDataStructureException;

public interface MyIDictionary<K,V> {
	public K add(K key, V value);
	public void remove(K key);
	public void update(K key,V value);
	public V lookup(K key) throws MyDataStructureException;
	public  boolean isDefined(K id);
	public K findKey(V value);
	public Map<K,V> getContent();
	public void setContent( Map<K,V> m );
	public MyIDictionary<K,V> clone();
	
	public void lock();
	public void unlock();
	
}
