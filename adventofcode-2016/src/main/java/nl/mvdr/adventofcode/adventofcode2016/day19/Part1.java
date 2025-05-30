package nl.mvdr.adventofcode.adventofcode2016.day19;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
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
                LOGGER.info("Remaining elves: " + elves.size()); // TODO reduce log level to debug
            }
            
            if (currentElfIndex == 0) {
                // We can just remove all odd indices
                var size = elves.size();
                elves = IntStream.range(0, size)
                        .filter(i -> i % 2 == 0)
                        .mapToObj(elves::get)
                        .toList();
                
                if (size % 2 == 0) {
                    currentElfIndex = 0;
                } else {
                    currentElfIndex = elves.size() - 1;
                }
            } else if (currentElfIndex == elves.size() - 1) {
                elves = elves.subList(1, elves.size());
                currentElfIndex = 0;
            } else {
                throw new IllegalStateException();
//                elves.remove(currentElfIndex + 1);
//                currentElfIndex = (currentElfIndex + 1) % elves.size();
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
