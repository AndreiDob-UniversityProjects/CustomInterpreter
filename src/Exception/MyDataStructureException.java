package Exception;

public class MyDataStructureException extends Exception{

    public MyDataStructureException(String message) {
        super(message);
    }
    public void printEx() {
        System.out.println(this.getMessage());
    }
}

