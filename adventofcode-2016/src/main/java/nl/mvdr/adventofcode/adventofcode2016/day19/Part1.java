package nl.mvdr.adventofcode.adventofcode2016.day19;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public int solve(Stream<String> lines) {
        var line = lines.findFirst().orElseThrow();
        var startingElves = Integer.parseInt(line);
        
        List<Integer> elves = IntStream.range(1, startingElves + 1)
                .boxed()
                .toList();
        
        var currentElfIndex = 0;
        
        while (1 < elves.size()) {
            if (currentElfIndex == 0) {
                LOGGER.debug("Remaining elves: {}", elves.size());
            }
            
            if (currentElfIndex == 0) {
                boolean even = elves.size() % 2 == 0;
                
                // Just remove all odd indices
                elves = IntStream.range(0, elves.size())
                        .filter(i -> i % 2 == 0)
                        .mapToObj(elves::get)
                        .toList();
                
                if (even) {
                    currentElfIndex = 0;
                } else {
                    currentElfIndex = elves.size() - 1;
                }
            } else if (currentElfIndex == elves.size() - 1) {
                elves = elves.subList(1, elves.size());
                currentElfIndex = 0;
            } else {
                throw new IllegalStateException("Should not occur");
            }
        }
        
        return elves.getFirst().intValue();
    }

    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day19.txt");

        LOGGER.info(result);
    }
}
