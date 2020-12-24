package nl.mvdr.adventofcode.adventofcode2020.day13;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 13 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/13">Shuttle Search</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ShuttleSearchPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShuttleSearchPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return the ID of the earliest bus we can take to the airport multiplied by the number of minutes we'll need to wait for that bus
     */
    @Override
    public long solve(Stream<String> linesStream) {
        Map<Integer, Integer> busIds = getBusIds(linesStream);
        return solve(busIds);
    }

    /**
     * Gets the bus ids from the input.
     * 
     * @param linesStream puzzle input
     * @return map of indexes in the timetable to bus ids; unspecified bus ids ("x"s in the input) are not included
     */
    private Map<Integer, Integer> getBusIds(Stream<String> linesStream) {
        String[] busIds = linesStream.skip(1L)
                .findFirst()
                .orElseThrow()
                .split(",");
        
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i != busIds.length; i++) {
            if (!"x".equals(busIds[i])) {
                int busId = Integer.parseInt(busIds[i]);
                result.put(Integer.valueOf(i), Integer.valueOf(busId));
            }
        }
        return result;
    }
    
    /**
     * Solver methods.
     * 
     * @param busIds bus ids by index
     * @return timestamp
     */
    private long solve(Map<Integer, Integer> busIds) {
        Iterator<Entry<Integer, Integer>> entryIterator = busIds.entrySet().iterator();
        
        long timestamp = 0L;
        
        Entry<Integer, Integer> entry = entryIterator.next();
        long increment = entry.getValue().longValue();
        while (entryIterator.hasNext()) {
            entry = entryIterator.next();
            int index = entry.getKey().intValue();
            int busId = entry.getValue().intValue();
            while ((timestamp + index) % busId != 0) {
                timestamp = timestamp + increment;
            }
            increment = increment * busId;
        }
        return timestamp;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ShuttleSearchPart2 instance = new ShuttleSearchPart2();

        String result = instance.solve("input-day13-2020.txt");

        LOGGER.info(result);
    }
}
 