package nl.mvdr.adventofcode.adventofcode2019.day21;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 21 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/21">Springdroid Adventure</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpringdroidAdventurePart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringdroidAdventurePart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return amount of hull damage
     */
    @Override
    public long solve(Stream<String> lines) {
        Program.parse(lines.findFirst().orElseThrow())
                .withAsciiInput("TODO")
                .execute();
        
        return 0L; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpringdroidAdventurePart1 instance = new SpringdroidAdventurePart1();

        String result = instance.solve("input-day21-2019.txt");

        LOGGER.info(result);
    }
}
 