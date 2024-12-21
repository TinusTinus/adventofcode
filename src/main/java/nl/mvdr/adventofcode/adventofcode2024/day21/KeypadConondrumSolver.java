package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.IntSolver;

class KeypadConondrumSolver implements IntSolver {

    private final int intermediateRobots;
    private final Keypad<NumericKeypadButton> numericKeypad = Keypad.create(NumericKeypadButton.class);
    private final Keypad<DirectionalKeypadButton> directionalKeypad = Keypad.create(DirectionalKeypadButton.class);

    KeypadConondrumSolver(int intermediateRobots) {
        this.intermediateRobots = intermediateRobots;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        return lines.mapToInt(this::calculateComplexity)
                .sum();
    }
    
    private int calculateComplexity(String code) {
        return Math.multiplyExact(findFewestButtonPresses(code), getNumericCode(code));
    }
    
    private int findFewestButtonPresses(String code) {
        var codeButtons = code.chars()
                .mapToObj(c -> NumericKeypadButton.of((char)c))
                .toList();
        var fewestButtonPresses = numericKeypad.fewestButtonPresses(codeButtons, NumericKeypadButton.KEY_A);
        
        for (int i = 0; i != intermediateRobots; i++) {
            fewestButtonPresses = directionalKeypad.fewestButtonPresses(fewestButtonPresses, DirectionalKeypadButton.A);
        }
        
        return fewestButtonPresses.size();
    }
    
    private static int getNumericCode(String code) {
        return Integer.parseInt(code.substring(0, code.length() - 1), 10);
    }
}
 