package nl.mvdr.adventofcode.adventofcode2024.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

abstract class HistorianHysteriaSolver implements IntSolver {

    @Override
    public int solve(Stream<String> lines) {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        
        lines.map(line -> line.split("   "))
                .forEach(intArray -> {
                    leftList.add(Integer.valueOf(intArray[0]));
                    rightList.add(Integer.valueOf(intArray[1]));
                });
        
        return solve(leftList, rightList);
    }
    
    abstract protected int solve(List<Integer> leftList, List<Integer> rightList);
}
