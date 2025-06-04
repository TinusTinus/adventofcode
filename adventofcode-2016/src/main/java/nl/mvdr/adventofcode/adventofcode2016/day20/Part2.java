package nl.mvdr.adventofcode.adventofcode2016.day20;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.range.LongRange;
import nl.mvdr.adventofcode.solver.LongSolver;

public class Part2 implements LongSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public long solve(Stream<String> lines) {
        var ranges = lines.map(LongRange::parse)
                .toList();
        
        ranges = LongRange.reduce(ranges);
        
        var result = ranges.getFirst().min();
        
        for (var i = 0; i != ranges.size() - 1; i++) {
            result = result + ranges.get(i + 1).min() - ranges.get(i).max() - 1;
        }
        
        result = result + 4294967295L - ranges.getLast().max();
        
        return result;
    }

    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day20.txt");

        LOGGER.info(result);
    }
}
