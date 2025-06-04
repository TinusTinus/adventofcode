package nl.mvdr.adventofcode.adventofcode2016.day20;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.range.LongRange;
import nl.mvdr.adventofcode.solver.LongSolver;

public class Part1 implements LongSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public long solve(Stream<String> lines) {
        var ranges = lines.map(LongRange::parse)
                .toList();
        
        ranges = LongRange.reduce(ranges);
        
        return ranges.getFirst().max() + 1;
    }

    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day20.txt");

        LOGGER.info(result);
    }
}
