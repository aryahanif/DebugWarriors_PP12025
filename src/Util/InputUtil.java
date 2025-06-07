package Util;

import java.util.Scanner;

public class InputUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int inputInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka. Silakan coba lagi.");
            }
        }
    }
}