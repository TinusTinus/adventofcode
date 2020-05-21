package nl.mvdr.adventofcode.adventofcode2019.day24;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 24 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/24">Planet of Discord</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PlanetOfDiscordPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanetOfDiscordPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return the biodiversity rating for the first layout that appears twice 
     */
    @Override
    public int solve(Stream<String> lines) {
        Layout layout = Layout.parse(false, lines);
        
        Set<Layout> layouts = new HashSet<>();
        
        while (!layouts.contains(layout)) {
            layouts.add(layout);
            layout = layout.tick();
        }
        
        return layout.biodiversity();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        PlanetOfDiscordPart1 instance = new PlanetOfDiscordPart1();

        String result = instance.solve("input-day24-2019.txt");

        LOGGER.info(result);
    }
}
 