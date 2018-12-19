package nl.mvdr.adventofcode.adventofcode2018.day18;

/**
 * Solution to the day 18 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/18">Settlers of the North Pole</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SettlersPart2 extends Settlers {

    /** Constructor. */
    public SettlersPart2() {
        super(1000000000);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SettlersPart2 instance = new SettlersPart2();

        String result = instance.solve("input-day18-2018.txt");

        System.out.println(result);
    }
}
