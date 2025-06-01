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
        List<Integer> firstHalf = IntStream.range(1, startingElves / 2 + 1)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        List<Integer> secondHalf = IntStream.range(startingElves / 2 + 1, startingElves + 1)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        while (!secondHalf.isEmpty()) {
            Integer currentElf = firstHalf.removeFirst();
            if (firstHalf.size() == secondHalf.size()) {
                firstHalf.removeLast();
            } else {
                secondHalf.removeFirst();
            }
            secondHalf.addLast(currentElf);
            
            // Balance the lists
            Integer temp = secondHalf.removeFirst();
            firstHalf.addLast(temp);
        }
        return firstHalf.getFirst().intValue();
    }

    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day19.txt");

        LOGGER.info(result);
    }
}
