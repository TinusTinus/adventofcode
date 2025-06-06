package nl.mvdr.adventofcode.adventofcode2024.day19;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public long solve(Stream<String> linesStream) {
        var lines = linesStream.toList();
        
        var towelPatterns = Stream.of(lines.getFirst().split(", "))
                .map(ColorSequence::parse)
                .collect(Collectors.toSet());
        
        return lines.stream()
                .skip(2)
                .map(ColorSequence::parse)
                .filter(design -> design.isPossible(towelPatterns))
                .count();
    }
    
    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day19-2024.txt");

        LOGGER.info(result);
    }
}
 