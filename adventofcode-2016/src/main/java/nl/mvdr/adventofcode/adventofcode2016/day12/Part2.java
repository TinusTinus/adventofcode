package nl.mvdr.adventofcode.adventofcode2016.day12;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.adventofcode2016.assembunny.Register;
import nl.mvdr.adventofcode.adventofcode2016.assembunny.State;
import nl.mvdr.adventofcode.solver.IntSolver;

public class Part2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public int solve(Stream<String> lines) {
        State startState = new State().setRegister(Register.C, 1).setInstructionPointer(0);
        return new LeonardosMonorail(startState).solve(lines);
    }
    
    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day12.txt");

        LOGGER.info(result);
    }
}
