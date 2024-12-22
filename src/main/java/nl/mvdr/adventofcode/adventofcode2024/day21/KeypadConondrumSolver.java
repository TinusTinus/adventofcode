package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LongSolver;

class KeypadConondrumSolver implements LongSolver {

    private final int robots;

    KeypadConondrumSolver(int robots) {
        this.robots = robots;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        return lines.mapToLong(this::calculateComplexity)
                .sum();
    }
    
    private long calculateComplexity(String code) {
        return Math.multiplyExact(findFewestButtonPresses(code), getNumericCode(code));
    }
    
    private long findFewestButtonPresses(String code) {
        var codeButtons = code.chars()
                .mapToObj(c -> NumericKeypadButton.of((char)c))
                .toList();
        return  Keypad.NUMERIC.fewestButtonPresses(codeButtons, NumericKeypadButton.KEY_A, robots);
    }
    
    private static int getNumericCode(String code) {
        return Integer.parseInt(code.substring(0, code.length() - 1), 10);
    }
}
 