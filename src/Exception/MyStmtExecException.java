package Exception;

public class MyStmtExecException extends RuntimeException {
	
	public MyStmtExecException(String message) {
        super(message);
    }
    public void printEx() {
    	System.out.println(this.getMessage());
    }
}
