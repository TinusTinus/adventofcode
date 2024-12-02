package nl.mvdr.adventofcode.adventofcode2024.day02;

import java.util.ArrayList;
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
    
    boolean isSafe(boolean problemDampener) {
        return isIncreasingGradually() || isDecreasingGradually()
                || (problemDampener && IntStream.range(0, levels.size())
                        .mapToObj(this::removeLevel)
                        .anyMatch(report -> report.isSafe(false)));
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
    
    private Report removeLevel(int index) {
        List<Integer> newLevels = new ArrayList<>(levels);
        newLevels.remove(index);
        return new Report(newLevels);
    }
}
