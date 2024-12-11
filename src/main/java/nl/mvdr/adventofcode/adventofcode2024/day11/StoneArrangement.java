package nl.mvdr.adventofcode.adventofcode2024.day11;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record StoneArrangement(List<Integer> stones) {
    
    static StoneArrangement parse(String line) {
        
        List<Integer> stones = Stream.of(line.split(" "))
                .map(Integer::valueOf)
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
        List<Integer> newStones = stones.stream()
                .map(StoneArrangement::blinkStone)
                .flatMap(IntStream::boxed)
                .toList();
        return new StoneArrangement(newStones);
    }
    
    private static IntStream blinkStone(int stoneNumber) {
        IntStream result;
        if (stoneNumber == 0) {
            result = IntStream.of(1);
        } else {
            String stoneNumberString = String.valueOf(stoneNumber);
            int digits = stoneNumberString.length();
            if (digits % 2 == 0) {
                int firstNumber = Integer.parseInt(stoneNumberString.substring(0, digits / 2));
                int secondNumber = Integer.parseInt(stoneNumberString.substring(digits / 2, stoneNumberString.length()));
                result = IntStream.of(firstNumber, secondNumber);
            } else {
                result = IntStream.of(2024 * stoneNumber);
            }
        }
        return result;
    }
}
