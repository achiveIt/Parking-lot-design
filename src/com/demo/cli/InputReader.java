package com.demo.cli;

import java.util.Scanner;

public class InputReader {
    private Scanner scanner;

    public InputReader(){
        this.scanner = new Scanner(System.in);
    }

    public String readString(String input){
        System.out.print(input);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }
}
