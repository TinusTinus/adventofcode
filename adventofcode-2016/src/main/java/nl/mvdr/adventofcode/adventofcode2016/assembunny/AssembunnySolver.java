package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.Map;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.IntSolver;

public class AssembunnySolver implements IntSolver {

    private final Map<Register, Integer> initialRegisters;
    
    public AssembunnySolver(Map<Register, Integer> initialRegisters) {
        this.initialRegisters = initialRegisters;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var program = Program.parse(lines, initialRegisters);
        var endState = program.execute();
        return endState.registers().get(Register.A).intValue();
    }
}
