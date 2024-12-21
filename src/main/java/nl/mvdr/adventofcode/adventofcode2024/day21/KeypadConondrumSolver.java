package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.IntSolver;

class KeypadConondrumSolver implements IntSolver {

    private final int intermediateRobots;

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
        var firstRobot = Robot.createFirstRobot(code);
        
        var fewestButtonPresses = firstRobot.fewestButtonPresses();
        
        var intermediateRobot = Robot.createIntermediateRobot(fewestButtonPresses);
        
        for (int i = 0; i != intermediateRobots; i++) {
            fewestButtonPresses = intermediateRobot.fewestButtonPresses();
            intermediateRobot = Robot.createIntermediateRobot(fewestButtonPresses);
        }
        
        return fewestButtonPresses.size();
    }
    
    private static int getNumericCode(String code) {
        return Integer.parseInt(code.substring(0, code.length() - 1), 10);
    }
}
 