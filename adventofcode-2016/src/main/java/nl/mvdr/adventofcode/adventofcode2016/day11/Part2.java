package nl.mvdr.adventofcode.adventofcode2016.day11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part2 extends RadioisotopeThermoelectricGenerators {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);
    
    @Override
    State addExtraItems(State initialState) {
        return initialState.add(new RadioisotopeThermoelectricGenerator(new Radioisotope("elerium")), Floor.FIRST)
                .add(new Microchip(new Radioisotope("elerium")), Floor.FIRST)
                .add(new RadioisotopeThermoelectricGenerator(new Radioisotope("dilithium")), Floor.FIRST)
                .add(new Microchip(new Radioisotope("dilithium")), Floor.FIRST);
    }
    
    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day11-2016.txt");

        LOGGER.info(result);
    }
}
