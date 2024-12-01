package nl.mvdr.adventofcode.adventofcode2024.day01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public int solve(Stream<String> lines) {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        
        lines.map(line -> line.split("   "))
                .forEach(intArray -> {
                    leftList.add(Integer.valueOf(intArray[0]));
                    rightList.add(Integer.valueOf(intArray[1]));
                });
        
        leftList.sort(Comparator.naturalOrder());
        rightList.sort(Comparator.naturalOrder());
        
        return IntStream.range(0, leftList.size())
                .map(index -> Math.abs(leftList.get(index).intValue() - rightList.get(index).intValue()))
                .sum();
    }

    public static void main(String[] args) {
        var instance = new Part1();
        var result = instance.solve("input-day01-2024.txt");
        LOGGER.info(result);
    }
}
 