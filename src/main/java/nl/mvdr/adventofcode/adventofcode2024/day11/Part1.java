package nl.mvdr.adventofcode.adventofcode2024.day11;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    private final int numberOfBlinks;
    
    Part1(int numberOfBlinks) {
        this.numberOfBlinks = numberOfBlinks;
    }
    
    public Part1() {
        this(25);
    }
    
    @Override
    public long solve(Stream<String> lines) {
        var line = lines.findFirst().orElseThrow();
        var arrangement = StoneArrangement.parse(line);
        return arrangement.blink(numberOfBlinks).stones().size();
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day11-2024.txt");

        LOGGER.info(result);
    }
}
 