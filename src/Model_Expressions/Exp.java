package Model_Expressions;

import Model_DataStructures.MyIDictionary;
import  Exception.MyExpressionException;

public abstract class Exp {

	public abstract int eval(MyIDictionary<String, Integer> tbl,MyIDictionary<Integer, Integer> hp) throws MyExpressionException;
	
	}
