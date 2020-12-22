package Model;

import java.io.BufferedReader;
import java.io.IOException;

public class Pair<A,B> {
	public A first;
	public B second;
	public Pair(A first, B second) {
		super();
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) {
		      return true;
		    } 
		if (!(o instanceof Pair)) {
		      return false;
		    }
	    Pair<?, ?> pp = (Pair<?, ?>)o;
		return pp.first==first; 
	}
	@Override
	public int hashCode() {
	    int prime = 31;
	    return prime + (second == null ? 0 : second.hashCode());    
	}
	@Override
	public String toString() {
		String s=second.toString();
		if(second instanceof BufferedReader)
			try {
				((BufferedReader) second).ready();
			} catch (IOException e) {
				s="BufferedReader--closed";
			}
		return "Pair( " + first + ", " + s + ")";
	}
	
}
