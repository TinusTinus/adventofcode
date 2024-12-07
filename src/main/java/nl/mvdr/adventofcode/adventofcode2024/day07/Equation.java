package nl.mvdr.adventofcode.adventofcode2024.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

record Equation(int testValue, List<Integer> operands) {
    static Equation parse(String line) {
        String[] parts = line.split(": ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse as an equation: " + line);
        }
        int testValue = Integer.parseInt(parts[0]);
        
        List<Integer> operands = Stream.of(parts[1].split(" "))
                .map(Integer::valueOf)
                .toList();
        if (operands.isEmpty()) {
            throw new IllegalArgumentException("Unable to parse operands: " + line);
        }
        
        return new Equation(testValue, operands);
    }
    
    boolean couldBeTrue() {
        boolean result;
        if (operands.size() == 1 && testValue == operands.getFirst().intValue()) {
            result = true;
        } else if (operands.size() == 1) {
            result = false;
        } else {
            result = performAddition().couldBeTrue() || performMultiplication().couldBeTrue();
        }
        
        return result;
    }
    
    private Equation performAddition() {
        return performOperation((i, j) -> i + j);
    }

    private Equation performMultiplication() {
        return performOperation((i, j) -> i * j);
    }
    
    private Equation performOperation(IntBinaryOperator operation) {
        int newOperand = operation.applyAsInt(operands.get(0).intValue(), operands.get(1).intValue());
        
        List<Integer> newOperands = new ArrayList<>();
        newOperands.add(Integer.valueOf(newOperand));
        newOperands.addAll(operands.subList(2, operands.size()));
        
        return new Equation(testValue, newOperands);
    }
    
}
