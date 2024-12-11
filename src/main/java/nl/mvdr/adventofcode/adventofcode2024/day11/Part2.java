package nl.mvdr.adventofcode.adventofcode2024.day11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part2 extends PlutonianPebblesSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    public Part2() {
        super(75);
    }

    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day11-2024.txt");

        LOGGER.info(result);
    }
}
 