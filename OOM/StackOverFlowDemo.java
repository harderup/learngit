package OutOfMemoryError;

public class StackOverFlowDemo {

    public static void stackOverFlow(){
        stackOverFlow();
    }
    public static void main(String[] args) {
        stackOverFlow();
    }
}
