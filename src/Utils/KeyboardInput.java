package Utils;

import java.util.Scanner;

public class KeyboardInput {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
