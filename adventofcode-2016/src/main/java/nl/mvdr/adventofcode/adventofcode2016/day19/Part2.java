package nl.mvdr.adventofcode.adventofcode2016.day19;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part2 extends ElephantNamedJosephSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    protected int solve(int startingElves) {
        List<Integer> elves = IntStream.range(1, startingElves + 1)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        
        while (1 < elves.size()) {
            elves.remove(elves.size() / 2);
            
            var first = elves.remove(0);
            elves.add(first);
        }
        
        return elves.getFirst().intValue();
    }

    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day19.txt");

        LOGGER.info(result);
    }
}
