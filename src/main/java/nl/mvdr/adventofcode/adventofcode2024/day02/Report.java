package nl.mvdr.adventofcode.adventofcode2024.day02;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Report(List<Integer> levels) {
    
    static Report parse(String line) {
        List<Integer> levels = Stream.of(line.split(" "))
              .map(Integer::valueOf)
              .toList();
        return new Report(levels);
    }
    
    boolean isSafe() {
        return isIncreasingGradually() || isDecreasingGradually();
    }
    
    private boolean isIncreasingGradually() {
        return IntStream.range(0, levels.size() - 1)
                .map(i -> levels.get(i + 1).intValue() - levels.get(i).intValue())
                .allMatch(difference -> 1 <= difference && difference <= 3);
    }
    
    private boolean isDecreasingGradually() {
        return IntStream.range(0, levels.size() - 1)
                .map(i -> levels.get(i).intValue() - levels.get(i + 1).intValue())
                .allMatch(difference -> 1 <= difference && difference <= 3);
    }
}
