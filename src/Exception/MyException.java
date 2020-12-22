package Exception;

public class MyException extends RuntimeException {
	public MyException(String message) {
        super(message);
    }
    public void printEx() {
    	System.out.println(this.getMessage());
    }

}
