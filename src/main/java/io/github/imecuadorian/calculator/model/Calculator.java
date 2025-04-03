package io.github.imecuadorian.calculator.model;

import lombok.*;

import java.util.*;

@Getter
@NoArgsConstructor
public class Calculator implements Operations {

    private final Generic<Double, ?> values = new Generic<>();

    public void setValues(Double... values) {
        this.values.setArray(values);
    }

    @Override
    public double add() {
        return Arrays.stream(values.getArray()).mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public double subtract() {
        return Arrays.stream(values.getArray()).reduce(0.0, (a, b) -> a - b);
    }

    @Override
    public double multiply() {
        return Arrays.stream(values.getArray()).reduce(1.0, (a, b) -> a * b);
    }

    @Override
    public double divide() throws ArithmeticException {
        return Arrays.stream(values.getArray()).reduce(1.0, (a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return a / b;
        });
    }

    @Override
    public void memory(double value) {
        this.values.setT1(value);
    }

    @Override
    public boolean hasMemory() {
        return this.values.getT1() != null;
    }

    @Override
    public double getMemory() {
        if (hasMemory()) {
            return this.values.getT1();
        } else {
            throw new IllegalStateException("No value in memory.");
        }
    }
}
