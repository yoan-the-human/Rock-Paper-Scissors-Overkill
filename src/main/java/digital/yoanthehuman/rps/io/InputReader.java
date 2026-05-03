package digital.yoanthehuman.rps.io;

import java.util.Scanner;

public class InputReader {
    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readRawInput() {
        return scanner.nextLine();
    }
}