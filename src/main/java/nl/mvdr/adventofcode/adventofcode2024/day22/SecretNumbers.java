package nl.mvdr.adventofcode.adventofcode2024.day22;

class SecretNumbers {
    private SecretNumbers() {
        // private constructor to prevent instantiation
    }
    
    static long nextSecretNumber(long secretNumber) {
        var temp = Math.multiplyExact(secretNumber, 64);
        var result = mix(secretNumber, temp);
        result = prune(result);
        
        temp = Math.divideExact(result, 32);
        result = mix(result, temp);
        result = prune(result);
        
        temp = Math.multiplyExact(result, 2048);
        result = mix(result, temp);
        result = prune(result);
        
        return result;
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
}
