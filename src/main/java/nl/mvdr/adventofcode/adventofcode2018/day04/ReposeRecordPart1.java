package nl.mvdr.adventofcode.adventofcode2018.day04;

import java.util.Comparator;

/**
 * Solution to the day 4 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/4">Repose Record</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ReposeRecordPart1 extends ReposeRecord {
    
    /** Constructor. */
    public ReposeRecordPart1() {
        super(Comparator.comparing(Guard::getMinutesAsleep));
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ReposeRecordPart1 instance = new ReposeRecordPart1();

        String result = instance.solve("input-day04-2018.txt");

        System.out.println(result);
    }
}
