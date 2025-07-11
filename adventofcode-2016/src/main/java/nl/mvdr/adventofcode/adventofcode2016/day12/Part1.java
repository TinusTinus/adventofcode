package nl.mvdr.adventofcode.adventofcode2016.day12;

import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.adventofcode2016.assembunny.AssembunnySolver;
import nl.mvdr.adventofcode.adventofcode2016.assembunny.Register;
import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public int solve(Stream<String> lines) {
        Map<Register, Integer> startingRegisterValues = Map.of(
                Register.A, 0,
                Register.B, 0,
                Register.C, 0,
                Register.D, 0);
        return new AssembunnySolver(startingRegisterValues).solve(lines);
    }
    
    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day12.txt");

        LOGGER.info(result);
    }
}
