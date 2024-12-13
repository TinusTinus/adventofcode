package nl.mvdr.adventofcode.adventofcode2024.day01;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part2 extends HistorianHysteriaSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public int solve(List<Integer> leftList, List<Integer> rightList) {
        return leftList.stream()
                .mapToInt(leftNumber -> leftNumber.intValue() * Collections.frequency(rightList, leftNumber))
                .sum();
    }

    public static void main(String[] args) {
        var instance = new Part2();
        var result = instance.solve("input-day01-2024.txt");
        LOGGER.info(result);
    }
}
 