package nl.mvdr.adventofcode.adventofcode2016.day22;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

public class Part2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public int solve(Stream<String> lines) {
        
        Set<Grid> grids = Set.of(Grid.parse(lines));
        
        int steps = 0;
        
        while(!grids.stream().anyMatch(Grid::isGoalDataAccessible)) {
            grids = grids.parallelStream()
                    .flatMap(Grid::step)
                    .collect(Collectors.toSet());
            steps++;
            LOGGER.debug("After {} steps: {} grids", steps, grids.size());
        }
        
        return steps;
    }
    
    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day22.txt");

        LOGGER.info(result);
    }
}
