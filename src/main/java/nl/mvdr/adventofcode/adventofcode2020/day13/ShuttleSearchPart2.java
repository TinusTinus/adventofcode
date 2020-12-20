package nl.mvdr.adventofcode.adventofcode2020.day13;

import java.util.Comparator;
import java.util.HashMap;
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

    private final long startingPoint;
    
    /** Constructor, intended for the actual puzzle input. */
    public ShuttleSearchPart2() {
        this(100000000000000L);
    }
    
    /**
     * Constructor, intended for examples / unit tests.
     * 
     * @param startingPoint starting point
     */
    ShuttleSearchPart2(long startingPoint) {
        super();
        this.startingPoint = startingPoint;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return the ID of the earliest bus we can take to the airport multiplied by the number of minutes we'll need to wait for that bus
     */
    @Override
    public long solve(Stream<String> linesStream) {
        Map<Integer, Integer> busIds = getBusIds(linesStream);
        
        Entry<Integer, Integer> maxBusIdEntry = busIds.entrySet()
                .stream()
                .max(Comparator.comparing(Entry::getValue))
                .orElseThrow();
        int maxBusId = maxBusIdEntry.getValue().intValue();
        int maxBusIdIndex = maxBusIdEntry.getKey().intValue();
        
        long timestamp = earliestDepartureAfter(maxBusId, startingPoint);
        while (!isMatchingTimestamp(timestamp - maxBusIdIndex, busIds)) {
            timestamp = timestamp + maxBusId;
        }
        
        return timestamp - maxBusIdIndex;
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
    private static long earliestDepartureAfter(int busId, long timestamp) {
        long result = timestamp;
        while (result % busId != 0) {
            result++;
        }
        return result;
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
     * Checks whether the given timestamp matches the requirement.
     * 
     * @param timestamp timestamp
     * @param busIds bus ids
     * @return whether the given timestamp matches the requirement
     */
    static boolean isMatchingTimestamp(long timestamp, Map<Integer, Integer> busIds) {
        return busIds.entrySet()
                .stream()
                .allMatch(entry -> matches(timestamp, entry.getKey().intValue(), entry.getValue().intValue()));
    }

    private static boolean matches(long timestamp, int index, int busId) {
        return (timestamp + index) % busId == 0;
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
 