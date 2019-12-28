package nl.mvdr.adventofcode.adventofcode2019.day21;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 21 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/21">Springdroid Adventure</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpringdroidAdventurePart2 extends SpringdroidAdventure {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringdroidAdventurePart2.class);

    @Override
    String getSpringscript() {
        // !A || (!C && D && (!B || !F || H))
        return """
               NOT B J
               NOT F T
               OR T J
               OR H J
               AND D J
               NOT C T
               AND T J
               NOT A T
               OR T J
               RUN
               """;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpringdroidAdventurePart2 instance = new SpringdroidAdventurePart2();

        String result = instance.solve("input-day21-2019.txt");

        LOGGER.info(result);
    }
}
 