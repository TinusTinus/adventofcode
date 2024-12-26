package nl.mvdr.adventofcode.adventofcode2024.day22;

record SecretNumber(long number) {

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
        return new SecretNumber(nextSecretNumber());
    }
    
    long price() {
        return number % 10;
    }
}
