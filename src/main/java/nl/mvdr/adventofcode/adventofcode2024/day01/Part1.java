package nl.mvdr.adventofcode.adventofcode2024.day01;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part1 extends HistorianHysteriaSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public int solve(List<Integer> leftList, List<Integer> rightList) {
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
 