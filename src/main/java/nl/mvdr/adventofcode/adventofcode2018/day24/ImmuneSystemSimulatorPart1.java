package nl.mvdr.adventofcode.adventofcode2018.day24;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 24 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/24">Immune System Simulator 20XX</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ImmuneSystemSimulatorPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImmuneSystemSimulatorPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return how many units the winning army would have
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        Set<Group> groups = Group.parse(inputFilePath);
        
        groups.stream()
                .sorted(Comparator.comparing(Group::getArmy).thenComparing(Group::getId))
                .map(Group::toString)
                .forEach(LOGGER::debug);
        
        return null; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ImmuneSystemSimulatorPart1 solver = new ImmuneSystemSimulatorPart1();
        String solution = solver.solve("input-day24-2018.txt");
        LOGGER.info(solution);
    }
}
