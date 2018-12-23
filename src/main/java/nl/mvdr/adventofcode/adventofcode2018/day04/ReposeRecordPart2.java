package nl.mvdr.adventofcode.adventofcode2018.day04;

import java.util.Comparator;
import java.util.Map;

/**
 * Solution to the day 4 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/4">Repose Record</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ReposeRecordPart2 extends ReposeRecord {
    
    @Override
    protected String solve(Map<Integer, Guard> guards) {
        Comparator<Guard> guardComparator = Comparator.comparing(guard -> guard.getNumberOfTimesAsleep(guard.computeMostAsleepMinute()));
        
        Guard guard = guards.values().stream()
                .max(guardComparator)
                .get();
        
        return "" + guard.getId() * guard.computeMostAsleepMinute();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ReposeRecordPart2 instance = new ReposeRecordPart2();

        String result = instance.solve("input-day04-2018.txt");

        System.out.println(result);
    }
}
