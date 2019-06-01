package nl.mvdr.adventofcode.adventofcode2018.day11;

import java.util.Comparator;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 11 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/11">Chronal Charge</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalChargePart1 extends ChronalCharge {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalChargePart1.class);
    
    @Override
    protected Cell solve(int serialNumber) {
        return IntStream.range(0, 298)
                .mapToObj(Integer::valueOf)
                .flatMap(x -> IntStream.range(0, 298).mapToObj(y -> new Cell(x, y, serialNumber)))
                .max(Comparator.comparing(Cell::squareTotalPowerLevel))
                .get();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalChargePart1 instance = new ChronalChargePart1();

        String solution = instance.solve("input-day11-2018.txt");
        
        LOGGER.info(solution);
    }
}
