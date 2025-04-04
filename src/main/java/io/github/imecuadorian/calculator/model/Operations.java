package io.github.imecuadorian.calculator.model;

public interface Operations {

    double add();
    double subtract();
    double multiply();
    double divide() throws ArithmeticException;
    void memory(double value);
    boolean hasMemory();
    double getMemory();

}
