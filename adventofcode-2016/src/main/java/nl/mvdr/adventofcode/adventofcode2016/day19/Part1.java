package nl.mvdr.adventofcode.adventofcode2016.day19;

import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part1 extends ElephantNamedJosephSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    protected int solve(int startingElves) {
        List<Integer> elves = IntStream.range(1, startingElves + 1)
                .boxed()
                .toList();
        
        while (1 < elves.size()) {
            boolean odd = elves.size() % 2 == 1;
            
            elves = IntStream.range(0, elves.size())
                    .filter(i -> i % 2 == 0)
                    .mapToObj(elves::get)
                    .toList();
            
            if (odd) {
                elves = elves.subList(1, elves.size());
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
