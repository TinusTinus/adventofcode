package nl.mvdr.adventofcode.adventofcode2020.day13;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 13 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/13">Shuttle Search</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ShuttleSearchPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShuttleSearchPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return the ID of the earliest bus we can take to the airport multiplied by the number of minutes we'll need to wait for that bus
     */
    @Override
    public int solve(Stream<String> linesStream) {
        List<String> lines = linesStream.collect(Collectors.toList());
        int earliestTimestamp = Integer.parseInt(lines.get(0));
        int busId = Stream.of(lines.get(1).split(","))
                .filter(id -> !"x".equals(id))
                .mapToInt(Integer::parseInt)
                .boxed()
                .min(Comparator.comparing(i -> Integer.valueOf(earliestDepartureAfter(i.intValue(), earliestTimestamp))))
                .orElseThrow()
                .intValue();
        
        int timestamp = earliestDepartureAfter(busId, earliestTimestamp);
        int waitTime = timestamp - earliestTimestamp;
        
        return busId * waitTime;
    }
    
    /**
     * Finds the earliest departure time for the given bus, after the given timestamp.
     * 
     * That is, the smallest multiple of the given bus ID which is at least the given timestamp
     * 
     * @param busId bud ID; also its departure frequency
     * @param timestamp timestamp
     * @return earliest departure time
     */
    private static int earliestDepartureAfter(int busId, int timestamp) {
        int result = 0;
        while (result <= timestamp) {
            result += busId;
        }
        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ShuttleSearchPart1 instance = new ShuttleSearchPart1();

        String result = instance.solve("input-day13-2020.txt");

        LOGGER.info(result);
    }
}
 