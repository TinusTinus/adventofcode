package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.Optional;

record RotateBasedOnPositionOperation(char x) implements ScramblerOperation {

    private static final String PREFIX = "rotate based on position of letter ";
    
    static Optional<ScramblerOperation> parse(String line) {
        Optional<ScramblerOperation> result;
        if (line.startsWith(PREFIX)) {
            var x = line.substring(PREFIX.length()).charAt(0);
            
            result = Optional.of(new RotateBasedOnPositionOperation(x));
        } else {
            result = Optional.empty();
        }
        return result;
    }
    
    @Override
    public String apply(String input) {
        var index = input.indexOf(x);
        
        var rotationAmount = 1 + index;
        if (4 <= index) {
            rotationAmount += 1;
        }
        rotationAmount = rotationAmount % input.length();
        
        return new RotateOperation(RotationDirection.RIGHT, rotationAmount).apply(input);
    }

}
