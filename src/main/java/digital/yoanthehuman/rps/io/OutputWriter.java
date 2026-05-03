package digital.yoanthehuman.rps.io;

public class OutputWriter {
    
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayPrompt(String message) {
        System.out.print(message + " ");
    }
}