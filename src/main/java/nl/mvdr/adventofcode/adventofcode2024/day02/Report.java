package nl.mvdr.adventofcode.adventofcode2024.day02;

import java.util.List;
import java.util.stream.Stream;

record Report(List<Integer> levels) {
    
    static Report parse(String line) {
        List<Integer> levels = Stream.of(line.split(" "))
              .map(Integer::valueOf)
              .toList();
        return new Report(levels);
    }
    
    boolean isSafe(boolean problemDampener) {
        return isIncreasingGradually(levels.getFirst().intValue(), 1, problemDampener)
                || isDecreasingGradually(levels.getFirst().intValue(), 1, problemDampener)
                // If the problem dampener is enabled, we could also remove the first element
                || (problemDampener && isIncreasingGradually(levels.get(1).intValue(), 2, false))
                || (problemDampener && isDecreasingGradually(levels.get(1).intValue(), 2, false));
    }
    
    private boolean isIncreasingGradually(int previousLevel, int index, boolean badLevelTolerated) {
        boolean result;
        if (index == levels.size()) {
            result = true;
        } else {
            int currentLevel = levels.get(index).intValue();
            int difference = currentLevel - previousLevel;
            if (1 <= difference && difference <= 3) {
                result = isIncreasingGradually(currentLevel, index + 1, badLevelTolerated);
            } else if (badLevelTolerated) {
                // remove current level
                result = isIncreasingGradually(previousLevel, index + 1, false);
            } else {
                result = false;
            }
        }
        return result;
    }
    
    private boolean isDecreasingGradually(int previousLevel, int index, boolean badLevelTolerated) {
        boolean result;
        if (index == levels.size()) {
            result = true;
        } else {
            int currentLevel = levels.get(index).intValue();
            int difference = previousLevel - currentLevel;
            if (1 <= difference && difference <= 3) {
                result = isDecreasingGradually(currentLevel, index + 1, badLevelTolerated);
            } else if (badLevelTolerated) {
                // remove current level
                result = isDecreasingGradually(previousLevel, index + 1, false);
            } else {
                result = false;
            }
        }
        return result;
    }
}
