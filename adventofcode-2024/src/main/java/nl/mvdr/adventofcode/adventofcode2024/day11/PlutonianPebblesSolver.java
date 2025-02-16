package nl.mvdr.adventofcode.adventofcode2024.day11;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LongSolver;

class PlutonianPebblesSolver implements LongSolver {

    private final int numberOfBlinks;
    private Map<StoneNumberAndBlinkCount, Long> cache = new HashMap<>();
    
    protected PlutonianPebblesSolver(int numberOfBlinks) {
        this.numberOfBlinks = numberOfBlinks;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        var line = lines.findFirst().orElseThrow();
        return Stream.of(line.split(" "))
                .mapToLong(Long::parseLong)
                .map(stoneNumber -> countStones(stoneNumber, numberOfBlinks))
                .sum();
    }
    
    private long countStones(long stoneNumber, int blinks) {
        long result;
        if (blinks == 0) {
            result = 1L;
        } else {
            var cacheKey = new StoneNumberAndBlinkCount(stoneNumber, blinks);
            if (cache.containsKey(cacheKey)) {
                result = cache.get(cacheKey).longValue();
            } else {
                result = blinkStone(stoneNumber).map(s -> countStones(s, blinks - 1)).sum();
                cache.put(cacheKey, Long.valueOf(result));
            }
        }
        return result;
    }
    
    private static LongStream blinkStone(long stoneNumber) {
        LongStream result;
        if (stoneNumber == 0) {
            result = LongStream.of(1L);
        } else {
            String stoneNumberString = String.valueOf(stoneNumber);
            int digits = stoneNumberString.length();
            if (digits % 2 == 0) {
                long firstNumber = Long.parseLong(stoneNumberString.substring(0, digits / 2));
                long secondNumber = Long.parseLong(stoneNumberString.substring(digits / 2, stoneNumberString.length()));
                result = LongStream.of(firstNumber, secondNumber);
            } else {
                result = LongStream.of(Math.multiplyExact(2024L, stoneNumber));
            }
        }
        return result;
    }
}
 