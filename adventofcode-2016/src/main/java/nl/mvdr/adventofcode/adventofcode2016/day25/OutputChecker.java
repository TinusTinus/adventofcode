package nl.mvdr.adventofcode.adventofcode2016.day25;

class OutputChecker {
    private int expectedNextValue;
    private int remainingValuesToCheck;
    private boolean done;
    
    OutputChecker() {
        expectedNextValue = 0;
        remainingValuesToCheck = 100;
        done = false;
    }
    
    void check(int output) {
        if (output == expectedNextValue) {
            expectedNextValue = switch (expectedNextValue) {
                case 0 -> 1;
                case 1 -> 0;
                default -> throw new IllegalStateException("Unexpected value found: " + expectedNextValue);
            };
            remainingValuesToCheck--;
            done = remainingValuesToCheck == 0;
        } else {
            done = true;
        }
    }
    
    boolean isDone() {
        return done;
    }
    
    boolean isSuccessful() {
        if (!done) {
            throw new IllegalStateException("Not done yet");
        }
        return remainingValuesToCheck == 0;
    }
}
