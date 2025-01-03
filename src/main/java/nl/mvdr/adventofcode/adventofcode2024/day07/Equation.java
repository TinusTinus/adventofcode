package nl.mvdr.adventofcode.adventofcode2024.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongBinaryOperator;
import java.util.stream.Stream;

record Equation(long testValue, List<Long> operands) {
    static Equation parse(String line) {
        String[] parts = line.split(": ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse as an equation: " + line);
        }
        long testValue = Long.parseLong(parts[0]);
        
        List<Long> operands = Stream.of(parts[1].split(" "))
                .map(Long::valueOf)
                .toList();
        if (operands.isEmpty()) {
            throw new IllegalArgumentException("Unable to parse operands: " + line);
        }
        
        return new Equation(testValue, operands);
    }
    
    boolean couldBeTrue(boolean allowConcatenation) {
        boolean result;
        if (operands.size() == 1) {
            result = testValue == operands.getFirst().longValue();
        } else {
            result = performAddition().couldBeTrue(allowConcatenation)
                    || performMultiplication().couldBeTrue(allowConcatenation)
                    || (allowConcatenation && performConcatenation().couldBeTrue(true));
        }
        
        return result;
    }
    
    private Equation performAddition() {
        return performOperation((i, j) -> i + j);
    }

    private Equation performMultiplication() {
        return performOperation((i, j) -> i * j);
    }
    
    private Equation performConcatenation() {
        return performOperation((i, j) -> Long.parseLong("" + i + j));
    }
    
    private Equation performOperation(LongBinaryOperator operation) {
        long newOperand = operation.applyAsLong(operands.get(0).longValue(), operands.get(1).longValue());
        
        List<Long> newOperands = new ArrayList<>();
        newOperands.add(Long.valueOf(newOperand));
        newOperands.addAll(operands.subList(2, operands.size()));
        
        return new Equation(testValue, newOperands);
    }
    
}
