package nl.mvdr.adventofcode.adventofcode2016.day15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.Solver;

public class Part1 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public String solve(String inputfile) {
        return new TimingIsEverythingSolver(false).solve(inputfile);
    }

    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day15.txt");

        LOGGER.info(result);
    }
}
