package nl.mvdr.adventofcode.adventofcode2024.day22;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

record SecretNumber(long number, List<Long> priceChanges) {

    SecretNumber(long number) {
        this(number, List.of());
    }
    
    static long nextSecretNumber(long secretNumber) {
        return new SecretNumber(secretNumber).next().number();
    }
    
    static long mix(long secretNumber, long value) {
        return secretNumber ^ value;
    }
    
    static long prune(long secretNumber) {
        return secretNumber % 16777216;
    }
    
    static long getSecretNumber(long initialNumber, int iterations) {
        long secretNumber = initialNumber;
        for (int i = 0; i != iterations; i++) {
            secretNumber = nextSecretNumber(secretNumber);
        }
        return secretNumber;
    }
    
    private static long price(long secretNumber) {
        return secretNumber % 10;
    }
    
    private long nextSecretNumber() {
        var temp = Math.multiplyExact(number, 64);
        var result = mix(number, temp);
        result = prune(result);
        
        temp = Math.divideExact(result, 32);
        result = mix(result, temp);
        result = prune(result);
        
        temp = Math.multiplyExact(result, 2048);
        result = mix(result, temp);
        result = prune(result);
        
        return result;
    }
    
    SecretNumber next() {
        var nextSecretNumber = nextSecretNumber();
        
        var nextPriceChanges = new ArrayList<>(priceChanges);
        if (nextPriceChanges.size() == 4) {
            nextPriceChanges.removeFirst();
        }
        var priceChange = price(nextSecretNumber) - price();
        nextPriceChanges.add(Long.valueOf(priceChange));
        
        
        return new SecretNumber(nextSecretNumber, nextPriceChanges);
    }
    
    long price() {
        return price(number);
    }
    
    final List<SecretNumber> generateAll() {
        return Stream.iterate(this, SecretNumber::next)
                .limit(2000)
                .toList();
    }
}
