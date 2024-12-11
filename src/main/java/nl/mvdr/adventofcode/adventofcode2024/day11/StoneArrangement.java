package nl.mvdr.adventofcode.adventofcode2024.day11;

import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

record StoneArrangement(List<Long> stones) {
    
    static StoneArrangement parse(String line) {
        
        List<Long> stones = Stream.of(line.split(" "))
                .map(Long::valueOf)
                .toList();
        return new StoneArrangement(stones);
    }

    StoneArrangement blink(int blinks) {
        StoneArrangement result = this;
        for (int i = 0; i != blinks; i++) {
            result = result.blink();
        }
        return result;
    }

    
    private StoneArrangement blink() {
        List<Long> newStones = stones.stream()
                .map(StoneArrangement::blinkStone)
                .flatMap(LongStream::boxed)
                .toList();
        return new StoneArrangement(newStones);
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
