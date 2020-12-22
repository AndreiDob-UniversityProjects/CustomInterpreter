package Model;

import java.io.BufferedReader;

import Model_DataStructures.MyIDictionary;

public interface GenericTable<K,V> {
	public K addEntry(V val);
	public boolean entryExists(K key);
	public void removeEntry(K key);
	public V getPair(K key) throws Exception;
	public MyIDictionary<K,V> getDict();
}
