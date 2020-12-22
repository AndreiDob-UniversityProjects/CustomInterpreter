package Exception;

public class MyControllerException extends Exception{
    public MyControllerException(String message) {
    super(message);
}
    public void printEx() {
        System.out.println(this.getMessage());
    }
}

