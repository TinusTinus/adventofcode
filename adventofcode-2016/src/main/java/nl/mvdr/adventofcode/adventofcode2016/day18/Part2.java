package nl.mvdr.adventofcode.adventofcode2016.day18;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.Solver;

public class Part2 implements Solver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public String solve(String inputfile) {
        
        return new LikeARogueSolver(400_000).solve(inputfile);
    }

    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day18.txt");

        LOGGER.info(result);
    }
}
