package nl.mvdr.adventofcode.adventofcode2019.day21;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 21 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/21">Springdroid Adventure</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpringdroidAdventurePart1 extends SpringdroidAdventure {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringdroidAdventurePart1.class);

    @Override
    String getSpringscript() {
        // !A || (!C && D)
        return """
               NOT C J
               AND D J
               NOT A T
               OR T J
               WALK
               """;
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
 