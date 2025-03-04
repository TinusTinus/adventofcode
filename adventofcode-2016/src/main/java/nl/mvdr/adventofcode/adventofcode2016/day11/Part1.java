package nl.mvdr.adventofcode.adventofcode2016.day11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part1 extends RadioisotopeThermoelectricGenerators {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);
    
    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day11-2016.txt");

        LOGGER.info(result);
    }
}
