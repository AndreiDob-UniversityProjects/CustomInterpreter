package Exception;

public class MyExpressionException extends Exception {
    public MyExpressionException(String message) {
        super(message);
    }
    public void printEx() {
        System.out.println(this.getMessage());
    }
}