package io.github.imecuadorian.calculator;

import io.github.imecuadorian.calculator.model.*;

import java.util.*;

public class Main {
    private static final Calculator CALCULATOR = new Calculator();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MENU = """
            1. Values
            2. Operations
            3. Exit
            """;

    private static final String SUB_MENU = """
            1. Add
            2. Subtract
            3. Multiply
            4. Divide
            5. Memory
            6. Exit
            """;
    private static double RESULT;

    public static void main(String[] args) {
        showMenu();
    }

    private static void showMenu() {
        do {
            System.out.println(MENU);
            System.out.print("Select an option: ");
            int option = SCANNER.nextInt();
            switch (option) {
                case 1 -> {
                    CALCULATOR.setValues(getValues());
                    System.out.println("Values set.");
                }
                case 2 -> showSubMenu();
                case 3 -> {
                    System.out.println("Exiting...");
                    SCANNER.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (true);
    }

    private static void showSubMenu() {
        if (CALCULATOR.getValues().getArray() == null) {
            System.out.println("No values set. Please set values first.");
            showMenu();
            return;
        }
        boolean exit = true;
        while (exit) {
            System.out.println(SUB_MENU);
            System.out.print("Select an operation: ");
            int option = SCANNER.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.println("Add values:");
                    RESULT = CALCULATOR.add();
                    System.out.println("Result: " + RESULT);
                    if (CALCULATOR.hasMemory()) {
                        System.out.println("Result in memory: " + CALCULATOR.getMemory());
                        System.out.println("Result: " + (CALCULATOR.getMemory() + RESULT));
                    }
                }
                case 2 -> {
                    System.out.println("Subtract values:");
                    RESULT = CALCULATOR.subtract();
                    System.out.println("Result: " + RESULT);
                    if (CALCULATOR.hasMemory()) {
                        System.out.println("Result in memory: " + CALCULATOR.getMemory());
                        System.out.println("Result: " + (CALCULATOR.getMemory() - RESULT));
                    }
                }
                case 3 -> {
                    System.out.println("Multiply values:");
                    RESULT = CALCULATOR.multiply();
                    System.out.println("Result: " + RESULT);
                    if (CALCULATOR.hasMemory()) {
                        System.out.println("Result in memory: " + CALCULATOR.getMemory());
                        System.out.println("Result: " + (CALCULATOR.getMemory() * RESULT));
                    }
                }
                case 4 -> {
                    try {
                        System.out.println("Divide values:");
                        RESULT = CALCULATOR.divide();
                        System.out.println("Result: " + RESULT);
                        if (CALCULATOR.hasMemory()) {
                            if (RESULT == 0) {
                                System.out.println("Error: Division by zero.");
                                return;
                            }
                            System.out.println("Result in memory: " + CALCULATOR.getMemory());
                            System.out.println("Result: " + (CALCULATOR.getMemory() / RESULT));
                        }
                    } catch (ArithmeticException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.println("Result: " + RESULT);
                    System.out.println("Do you want to save the result to memory? (y/n)");
                    if (SCANNER.next().equalsIgnoreCase("y")) {
                        CALCULATOR.memory(RESULT);
                        System.out.println("Result saved to memory.");
                    } else {
                        System.out.println("Result not saved.");
                    }
                    showMenu();
                }
                case 6 -> {
                    CALCULATOR.setValues();
                    showMenu();
                    exit = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static Double[] getValues() {
        System.out.println("Enter the values separated by semicolon (e.g. 1;2;3): ");
        String input = SCANNER.next();
        String[] inputValues = input.split(";");
        Double[] values = new Double[inputValues.length];
        for (int i = 0; i < inputValues.length; i++) {
            values[i] = Double.parseDouble(inputValues[i].trim());
        }
        return values;
    }


}
