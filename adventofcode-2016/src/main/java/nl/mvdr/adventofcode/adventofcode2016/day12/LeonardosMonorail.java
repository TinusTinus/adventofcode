package nl.mvdr.adventofcode.adventofcode2016.day12;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.IntSolver;

class LeonardosMonorail implements IntSolver {

    private final State startState;
    
    public LeonardosMonorail(State startState) {
        this.startState = startState;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var program = Program.parse(lines);
        var endState = program.execute(startState);
        return endState.registers().get(Register.A).intValue();
    }
}
