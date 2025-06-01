package nl.mvdr.adventofcode.adventofcode2016.day19;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

public class Part2 implements IntSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public int solve(Stream<String> lines) {
        var line = lines.findFirst().orElseThrow();
        var startingElves = Integer.parseInt(line);
        
        List<Integer> elves = IntStream.range(1, startingElves + 1)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        
        while (1 < elves.size()) {
            elves.remove(elves.size() / 2);
            
            var first = elves.remove(0);
            elves.add(first);
            
            if (elves.size() % 1_000 == 0) {
                var done = ((double)startingElves - elves.size()) / startingElves;
                LOGGER.info("{} % done", done * 100); // TODO debug
            }
        }
        
        return elves.getFirst().intValue();
    }

    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day19.txt");

        LOGGER.info(result);
    }
}
