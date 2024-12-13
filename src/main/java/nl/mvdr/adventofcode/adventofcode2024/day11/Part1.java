package nl.mvdr.adventofcode.adventofcode2024.day11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part1 extends PlutonianPebblesSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    public Part1() {
        super(25);
    }
    
    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day11-2024.txt");

        LOGGER.info(result);
    }
}
 